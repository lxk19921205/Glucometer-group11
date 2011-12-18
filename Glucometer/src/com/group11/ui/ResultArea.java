package com.group11.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.group11.R;
import com.group11.base.Unit;
import com.group11.util.Converter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * a class containing 3 number images and 1 point image plus the unit image
 * for displaying testing results on the screen
 */
public class ResultArea extends UIArea {

	private final ImageView firstImage;
	private final ImageView secondImage;
	private final ImageView pointImage;
	private final ImageView thirdImage;
	private final ImageView unitImage;
	
	private TimerTask unitBlinkingTask = null;
	
	public ResultArea(LinearLayout panel, ImageView first, ImageView second, ImageView point,
			ImageView third, ImageView unit) {
		super(panel);

		this.firstImage = first;
		this.secondImage = second;
		this.pointImage = point;
		this.thirdImage = third;
		this.unitImage = unit;
	}
	
	/**
	 * display a certain @param value in certain @param unit 
	 */
	public void displayResult(double value, Unit unit) {
		if (unit == Unit.L) {
			this.displayL(value);
		}
		else if (unit == Unit.DL) {
			this.displayDL(value);
		}
		this.unitImage.setVisibility(View.VISIBLE);
	}
	
	/**
	 * display an error code
	 */
	public void displayError(int errorCode) {
		this.pointImage.setVisibility(View.INVISIBLE);
		this.unitImage.setVisibility(View.INVISIBLE);
		this.firstImage.setVisibility(View.VISIBLE);
		this.secondImage.setVisibility(View.VISIBLE);
		this.thirdImage.setVisibility(View.VISIBLE);
		
		int[] numbers = Converter.toErrorCodeNumbers(errorCode);
		this.firstImage.setImageResource(getImageIDByValue(numbers[0]));
		this.secondImage.setImageResource(getImageIDByValue(numbers[1]));
		this.thirdImage.setImageResource(getImageIDByValue(numbers[2]));
	}
	
	private void displayL(double value) {
		this.unitImage.setImageResource(R.drawable.l);
		this.pointImage.setVisibility(View.VISIBLE);

		int[] numbers = Converter.toLNumbers(value);
		this.firstImage.setImageResource(getImageIDByValue(numbers[0]));
		this.secondImage.setImageResource(getImageIDByValue(numbers[1]));
		this.thirdImage.setImageResource(getImageIDByValue(numbers[2]));
	}
	
	private void displayDL(double value) {
		this.unitImage.setImageResource(R.drawable.dl);
		this.pointImage.setVisibility(View.INVISIBLE);

		int[] numbers = Converter.toDLNumbers(value);
		this.firstImage.setImageResource(getImageIDByValue(numbers[0]));
		this.secondImage.setImageResource(getImageIDByValue(numbers[1]));
		this.thirdImage.setImageResource(getImageIDByValue(numbers[2]));
	}
	
	private int getImageIDByValue(int value) {
		value %= 10;
		switch (value) {
		case 0:
			return R.drawable.number0;
		case 1:
			return R.drawable.number1;
		case 2:
			return R.drawable.number2;
		case 3:
			return R.drawable.number3;
		case 4:
			return R.drawable.number4;
		case 5:
			return R.drawable.number5;
		case 6:
			return R.drawable.number6;
		case 7:
			return R.drawable.number7;
		case 8:
			return R.drawable.number8;
		case 9:
			return R.drawable.number9;
		}
		return -1;
	}
	
	private void clearUnitBlinkingTask() {
		if (unitBlinkingTask != null) {
			unitBlinkingTask.cancel();
			unitBlinkingTask = null;
		}
	}
	
	private void initUnitBlinkingTask() {
		unitBlinkingTask = new TimerTask() {
			
			@Override
			public void run() {
				unitImage.post(new Runnable() {
					
					@Override
					public void run() {
						if (unitImage.getVisibility() == View.VISIBLE) {
							unitImage.setVisibility(View.INVISIBLE);
						}
						else {
							unitImage.setVisibility(View.VISIBLE);
						}
					}
				});
			}
		};
	}
	
	public void setUnitBlinking(boolean blinking) {
		this.clearUnitBlinkingTask();
		if (blinking) {
			this.initUnitBlinkingTask();
			new Timer().scheduleAtFixedRate(unitBlinkingTask, 0, 1000);
		}
	}

	/**
	 * only unit image is visible
	 */
	public void setOnlyDisplayUnit() {
		this.firstImage.setVisibility(View.INVISIBLE);
		this.secondImage.setVisibility(View.INVISIBLE);
		this.pointImage.setVisibility(View.INVISIBLE);
		this.thirdImage.setVisibility(View.INVISIBLE);
		this.unitImage.setVisibility(View.VISIBLE);
	}
}
