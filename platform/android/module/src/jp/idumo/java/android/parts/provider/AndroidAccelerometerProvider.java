/**
 * Copyright (c) <2012>, <Hiroyoshi Houchi> All rights reserved.
 *
 * http://www.hixi-hyi.com/
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the  following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jp.idumo.java.android.parts.provider;

import jp.idumo.java.android.annotation.IDUMOAndroid;
import jp.idumo.java.android.component.sensor.AccelerometerSensor;
import jp.idumo.java.android.core.IfAndroidActivityController;
import jp.idumo.java.android.core.AndroidActivityResource;
import jp.idumo.java.android.core.IfAndroidController;
import jp.idumo.java.android.manifest.AndroidFeature;
import jp.idumo.java.android.model.AndroidAccelerometerModel;
import jp.idumo.java.annotation.IDUMOInfo;
import jp.idumo.java.annotation.IDUMOProvider;
import jp.idumo.java.model.FlowingData;
import jp.idumo.java.model.connect.ConnectDataType;
import jp.idumo.java.model.connect.SingleConnectDataType;
import jp.idumo.java.parts.IfSendable;
import jp.idumo.java.util.LogManager;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

/**
 * Android上の加速度センサの値を提供するProvider
 * 
 * @author Hiroyoshi HOUCHI
 * @version 2.0
 * 
 */
@IDUMOAndroid(features = AndroidFeature.SENSOR_ACCELEROMETOR)
@IDUMOProvider(send = AndroidAccelerometerModel.class)
@IDUMOInfo(author = "Hiroyoshi HOUCHI", display = "加速度センサ", summary = "Androidの加速度センサ")
public class AndroidAccelerometerProvider implements IfSendable, IfAndroidController, IfAndroidActivityController {
	
	private AccelerometerSensor	accel;
	
	public AndroidAccelerometerProvider() {
		accel = AccelerometerSensor.INSTANCE;
		// lazy initialize (method of registActivity)
	}
	
	@Override
	public boolean isReady() {
		return accel.isReady();
	}
	
	@Override
	public FlowingData onCall() {
		LogManager.log();
		FlowingData p = new FlowingData();
		AndroidAccelerometerModel data = new AndroidAccelerometerModel(accel.getX(), accel.getY(), accel.getZ());
		p.add(data);
		return p;
	}
	
	@Override
	public void onIdumoDestroy() {}
	
	@Override
	public void onIdumoPause() {
		accel.unregister();
	}
	
	@Override
	public void onIdumoRestart() {}
	
	@Override
	public void onIdumoResume() {
		accel.register();
	}
	
	@Override
	public void onIdumoStart() {}
	
	@Override
	public void onIdumoStop() {}
	
	@Override
	public ConnectDataType sendableType() {
		return new SingleConnectDataType(AndroidAccelerometerModel.class);
	}
	
	@Override
	public void registActivity(AndroidActivityResource activity) {
		if (!accel.isInit()) {
			SensorManager sensor = (SensorManager) activity.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
			accel.init(sensor);
		}
	}
	
}
