package jp.idumo.java.console;

import jp.idumo.java.console.core.exec.ConsoleComponent;
import jp.idumo.java.console.core.exec.ConsoleWrapper;
import jp.idumo.java.console.core.util.ConsoleLogger;
import jp.idumo.java.console.receiptor.ConsoleViewReceiptor;
import jp.idumo.java.exception.IDUMOException;
import jp.idumo.java.parts.adapter.Number2GPSAdapter;
import jp.idumo.java.parts.handler.HotpepperHandler;
import jp.idumo.java.parts.handler.manifact.ArrayGetTopHandler;
import jp.idumo.java.parts.handler.raw.SortHandler;
import jp.idumo.java.parts.provider.NumberProvider;
import jp.idumo.java.util.LogManager;

public class ArrayGetTopTest extends ConsoleWrapper {
	public static void main(String[] args) {
		LogManager.DEBUG = true;
		LogManager.LOGGER = new ConsoleLogger();
		ArrayGetTopTest main = new ArrayGetTopTest();
		main.exec();
	}
	
	@Override
	public void init() {
		setExecutionWithComponent(new ConsoleComponent() {
			@Override
			public void onIdumoMakeFlowChart() throws IDUMOException {
				
				NumberProvider idumo0 = new NumberProvider(34.67);
				add(idumo0);
				NumberProvider idumo1 = new NumberProvider(135.52);
				add(idumo1);
				Number2GPSAdapter idumo2 = new Number2GPSAdapter();
				add(idumo2);
				HotpepperHandler idumo3 = new HotpepperHandler();
				add(idumo3);
				SortHandler idumo4 = new SortHandler("average");
				add(idumo4);
				ArrayGetTopHandler idumo5 = new ArrayGetTopHandler();
				add(idumo5);
				ConsoleViewReceiptor idumoR = new ConsoleViewReceiptor();
				add(idumoR);
				
				connect(idumo0, idumo2);
				connect(idumo1, idumo2);
				connect(idumo2, idumo3);
				connect(idumo3, idumo4);
				connect(idumo4, idumo5);
				connect(idumo5, idumoR);
				
			}
			
			@Override
			public void onIdumoPrepare() {
				setLoopCount(1);
				setSleepTime(1000);
			}
		});
	}
}
