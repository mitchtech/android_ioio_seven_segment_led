package net.mitchtech.ioio;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.AbstractIOIOActivity;
import net.mitchtech.ioio.sevensegmentled.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SevenSegmentLedActivity extends AbstractIOIOActivity implements OnClickListener {
	
	Button[] mLedButtons;
	boolean mSegmentStates[];
	
	boolean zero[] = {true, false, false, false, false, false, false};
	boolean one[] = {true, true, true, false, true, true, false};
	boolean two[] = {false, true, false, false, false, false, true};
	boolean three[] = {false, true, false, false, true, false, false};
	boolean four[] = {false, false, true, false, true, true, false};
	boolean five[] = {false, false, false, true, true, false, false};
	boolean six[] = {false, false, false, true, false, false, false};
	boolean seven[] = {true, true, false, false, true, true, false};
	boolean eight[] = {false, false, false, false, false, false, false};
	boolean nine[] = {false, false, false, false, true, false, false};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mLedButtons = new Button[10];
		
		mLedButtons[0] = (Button) findViewById(R.id.btn0);
		mLedButtons[1] = (Button) findViewById(R.id.btn1);
		mLedButtons[2] = (Button) findViewById(R.id.btn2);
		mLedButtons[3] = (Button) findViewById(R.id.btn3);
		mLedButtons[4] = (Button) findViewById(R.id.btn4);
		mLedButtons[5] = (Button) findViewById(R.id.btn5);
		mLedButtons[6] = (Button) findViewById(R.id.btn6);
		mLedButtons[7] = (Button) findViewById(R.id.btn7);
		mLedButtons[8] = (Button) findViewById(R.id.btn8);
		mLedButtons[9] = (Button) findViewById(R.id.btn9);
		
		mSegmentStates = new boolean[7];
		
		for (int i = 0; i < mSegmentStates.length; i++) {
			mSegmentStates[i] = false;
		}
		
		for (int i = 0; i < mLedButtons.length; i++) {
			mLedButtons[i].setOnClickListener(this);
		}
	}

	class IOIOThread extends AbstractIOIOActivity.IOIOThread {
		private DigitalOutput[] mLedSegments;

		@Override
		protected void setup() throws ConnectionLostException {
			mLedSegments = new DigitalOutput[7];
			mLedSegments[0] = ioio_.openDigitalOutput(34, false);
			mLedSegments[1] = ioio_.openDigitalOutput(35, false);
			mLedSegments[2] = ioio_.openDigitalOutput(36, false);
			mLedSegments[3] = ioio_.openDigitalOutput(37, false);
			mLedSegments[4] = ioio_.openDigitalOutput(38, false);
			mLedSegments[5] = ioio_.openDigitalOutput(39, false);
			mLedSegments[6] = ioio_.openDigitalOutput(40, false);
		}

		@Override
		protected void loop() throws ConnectionLostException {
			for (int i = 0; i < mLedSegments.length; i++) {
				mLedSegments[i].write(mSegmentStates[i]);
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	protected AbstractIOIOActivity.IOIOThread createIOIOThread() {
		return new IOIOThread();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn0:
			mSegmentStates = zero;
			break;
		case R.id.btn1:
			mSegmentStates = one;
			break;
		case R.id.btn2:
			mSegmentStates = two;
			break;
		case R.id.btn3:
			mSegmentStates = three;
			break;
		case R.id.btn4:
			mSegmentStates = four;
			break;
		case R.id.btn5:
			mSegmentStates = five;
			break;
		case R.id.btn6:
			mSegmentStates = six;
			break;
		case R.id.btn7:
			mSegmentStates = seven;
			break;
		case R.id.btn8:
			mSegmentStates = eight;
			break;
		case R.id.btn9:
			mSegmentStates = nine;
			break;
		default:
			break;
		}		
	}
}