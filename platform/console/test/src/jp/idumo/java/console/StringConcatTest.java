package jp.idumo.java.console;

import jp.idumo.java.console.core.exec.ConsoleComponent;
import jp.idumo.java.console.core.exec.ConsoleWrapper;
import jp.idumo.java.console.core.util.ConsoleLogger;
import jp.idumo.java.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.handler.StringConcatHandler_Prefix;
import jp.idumo.java.parts.handler.StringConcatHandler_Suffix;
import jp.idumo.java.parts.provider.StringProvider;
import jp.idumo.java.util.LogManager;

public class StringConcatTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		StringConcatTest main = new StringConcatTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				StringProvider idumo0 = new StringProvider("'str_test'");
				add(idumo0);
				StringConcatHandler_Prefix idumo1 = new StringConcatHandler_Prefix("pre-");
				add(idumo1);
				StringConcatHandler_Suffix idumo2 = new StringConcatHandler_Suffix("-suf");
				add(idumo2);
				ConsoleViewReceiptor idumop = new ConsoleViewReceiptor();
				add(idumop);
				
				connect(idumo0, idumo1);
				connect(idumo1, idumo2);
				connect(idumo2, idumop);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
