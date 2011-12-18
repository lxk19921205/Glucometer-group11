package com.group11.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.group11.base.Mode;
import com.group11.hardware.Beeper;
import com.group11.hardware.CurrentStatus;
import com.group11.ui.DateArea;
import com.group11.ui.ProgressBarArea;
import com.group11.ui.ResultArea;
import com.group11.ui.StatusArea;

/**
 * the logical controller in Testing Mode, it judges what to do
 */
public class TestingModeLogic extends ModeLogic {

	public TestingModeLogic(StatusArea status, ResultArea result,
			ProgressBarArea progressBar, DateArea date, Context context,
			SharedPreferences preferences, Handler handler) {
		super(status, result, progressBar, date, context, preferences, handler);
	}

	@Override
	public void validateMode() {
		// TODO Auto-generated method stub
		super.validateMode();
	}

	@Override
	public void onShortClick() {
		//=====Undefined Action, ignored=====
	}

	@Override
	public void onLongClick() {
		//=====Undefined Action, ignored=====
	}

	@Override
	public void onDoubleClick() {
		//=====Undefined Action, ignored=====
	}

	@Override
	public void onStripInserted() {
		throw new IllegalStateException("it should never enter this method.");
	}

	@Override
	public void onStripPulledOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUSBConnected() {
		//=====Undefined Action, ignored=====
	}

	@Override
	public void onUSBDisconnected() {
		//=====Undefined Action, ignored=====
	}

	public void onStripValid() {
		//TODO
		
	}
	
	public void onStripInvalid() {
		//TODO
		startTestingMode();
		Beeper.get().doErrorBeep(context);
		statusArea.setModeBlinking(Mode.TESTING);
		statusArea.setVisible(true);
		//10s
		//ErrorEnding
	}
	
	public void onBloodSufficient() {
		//TODO
	}
	
	public void onBloodInsufficient() {
		//TODO
		//displayerror
		//ErrorEnding
	}
	
	public void onResultReady() {
		//TODO
	}
	
	public void startTestingMode(){
		CurrentStatus currentStatus = new CurrentStatus(preferences);
		currentStatus.setPowerOn(true);
		currentStatus.setCurrentMode(Mode.TESTING);
		currentStatus.commit();
	}
}
