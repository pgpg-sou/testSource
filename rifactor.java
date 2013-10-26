package app.ver.runsuke;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public List<TextView> name = new ArrayList<TextView>();
	public List<TextView> minuteOfName = new ArrayList<TextView>();
	public List<TextView> secondOfName = new ArrayList<TextView>();
	public List<TextView> msecondOfName = new ArrayList<TextView>();
	private List<String> mLap1 = new ArrayList<String>();
	private List<String> mLap2 = new ArrayList<String>();
	private List<String> mLap3 = new ArrayList<String>();
	private List<List<String>> mLap= new ArrayList<List<String>>();
	private List<String> mResetLap1 = new ArrayList<String>();
	private List<String> mResetLap2 = new ArrayList<String>();
	private List<String> mResetLap3 = new ArrayList<String>();
	private List<List<String>> mResetLap = new ArrayList<List<String>>();
	public List<TextView> lap = new ArrayList<TextView>();
	public List<Button> separateStop = new ArrayList<Button>();
	private long[] mStartDate = new long[3];
	private long[] mRestart = new long[3];
	private int[] mMinute = new int[3];
	private int[] mSecond = new int[3];
	private int[] mm_second = new int[3];
	public List<Button> mLapButton = new ArrayList<Button>();
	private Button mStart;
	private String[] mStateOfStopWatch = new String[3];
	private String mStateOfStartButton;
	private String mMode;
	public String[] runnerName = { "Name1", "Name2", "Name3" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name.add(0, (TextView) findViewById(R.id.name1));
		name.add(1, (TextView) findViewById(R.id.name2));
		name.add(2, (TextView) findViewById(R.id.name3));
		minuteOfName.add(0, (TextView) findViewById(R.id.minute1));
		minuteOfName.add(1, (TextView) findViewById(R.id.minute2));
		minuteOfName.add(2, (TextView) findViewById(R.id.minute3));
		secondOfName.add(0, (TextView) findViewById(R.id.second1));
		secondOfName.add(1, (TextView) findViewById(R.id.second2));
		secondOfName.add(2, (TextView) findViewById(R.id.second3));
		msecondOfName.add(0,(TextView) findViewById(R.id.msecond1));
		msecondOfName.add(1, (TextView) findViewById(R.id.msecond2));
		msecondOfName.add(2, (TextView) findViewById(R.id.msecond3));
		separateStop.add(0, (Button) findViewById(R.id.separateStop1));
		separateStop.add(1, (Button) findViewById(R.id.separateStop2));
		separateStop.add(2, (Button) findViewById(R.id.separateStop3));
		lap.add(0, (TextView) findViewById(R.id.history1));
		lap.add(1, (TextView) findViewById(R.id.history2));
		lap.add(2, (TextView) findViewById(R.id.history3));
		mLapButton.add(0, (Button) findViewById(R.id.lapButton1));
		mLapButton.add(1, (Button) findViewById(R.id.lapButton2));
		mLapButton.add(2, (Button) findViewById(R.id.lapButton3));
		mLap.add(0, mLap1);
		mLap.add(1, mLap2);
		mLap.add(2, mLap3);
		mResetLap.add(0, mResetLap1);
		mResetLap.add(1, mResetLap2);
		mResetLap.add(2, mResetLap3);

		StopWatch mStopWatch = new StopWatch();

		for(int i = 0;i < 3;i++) {
			mStateOfStopWatch[i] = getResources().getString(R.string.comingSoon);
			separateStop.get(i).setOnClickListener(mStopWatch);
			mLapButton.get(i).setOnClickListener(mStopWatch);
		}
		mStateOfStartButton = getResources().getString(R.string.comingSoon);
		mStart = (Button) findViewById(R.id.start);
		mMode = getResources().getString(R.string.SPLIT);
		mStart.setOnClickListener(mStopWatch);
	}

	class StopWatch implements OnClickListener {
		private TimeMesure tm = new TimeMesure();

		@Override
		public void onClick(View v) {
			View button = (View) v;
			String stateOfButton = null;
			switch (button.getId()) {
			case R.id.start:
				if (mStateOfStartButton.equals(getResources().getString(R.string.alreadyStarted))) {

				} else if (mStateOfStartButton.equals(getResources().getString(R.string.reset))) {
					for(int i = 0;i < lap.size();i++) {
						lap.get(i).setText(getResources().getString(R.string.empty));
						separateStop.get(i).setText(getResources().getString(R.string.start));						
						minuteOfName.get(i).setText(getResources().getString(R.string.zero));
						secondOfName.get(i).setText(getResources().getString(R.string.zero));
						msecondOfName.get(i).setText(getResources().getString(R.string.zero));
						mStateOfStopWatch[i] = getResources().getString(R.string.comingSoon);
						mLap.get(i).clear();
						mResetLap.get(i).clear();
					}
					
					mStart.setText(getResources().getString(R.string.start));
					mStateOfStartButton = getResources().getString(R.string.comingSoon);

					break;
				} else {
					for(int i = 0;i < separateStop.size();i++) {
						separateStop.get(i).setText(getResources().getString(R.string.stop));
						mStateOfStopWatch[i] = getResources().getString(R.string.alreadyStarted);
						mStartDate[i] = System.currentTimeMillis();
					}
					mStateOfStartButton = getResources().getString(R.string.alreadyStarted);
					mStart.setEnabled(false);
					tm.start();
				}
				break;
			case R.id.separateStop1:
				transState(stateOfButton, 0, tm);
				break;
				
			case R.id.separateStop2:
				transState(stateOfButton, 1, tm);
				break;

			case R.id.separateStop3:
				transState(stateOfButton, 2, tm);
				break;
				
			case R.id.lapButton1:
				if (mStateOfStopWatch[0].equals(getResources().getString(R.string.alreadyStarted))) 
					displayLaptime(lap.get(0), 0, mLap.get(0), mResetLap1);
				break;
				
			case R.id.lapButton2:
				if (mStateOfStopWatch[1].equals(getResources().getString(R.string.alreadyStarted))) 
					displayLaptime(lap.get(1), 1, mLap.get(1), mResetLap2);
				break;
				
			case R.id.lapButton3:
				if (mStateOfStopWatch[2].equals(getResources().getString(R.string.alreadyStarted))) 
					displayLaptime(lap.get(2), 2, mLap.get(2), mResetLap3);
				break;
			}
		}
	}

	public void transState(String stateOfButton, int which, TimeMesure tm) {
		stateOfButton = separateStop.get(which).getText().toString();
		separateStop.get(which).setText(getResources().getString(R.string.stop));
		mStateOfStopWatch[which] = getResources().getString(R.string.alreadyStarted);

		if (stateOfButton.equals(getResources().getString(R.string.resume))) {
			mStartDate[which] = System.currentTimeMillis() - mRestart[which];
			tm.separateStart(which + 1);
			
		} else if (stateOfButton.equals(getResources().getString(R.string.start))) {
			mStartDate[which] = System.currentTimeMillis();
			tm.separateStart(which + 1);
			
		} else {
			separateStop.get(which).setText(getResources().getString(R.string.resume));
			mStateOfStopWatch[which] = getResources().getString(R.string.finish);
			tm.separateStop(which + 1);
		}	

	}
	
	public void displayLaptime(TextView lap, int num, List<String> mLap,List<String> mResetLap) {
		Laptime lt = new Laptime();
		TransTools ti = new TransTools();

		lap.setMovementMethod(ScrollingMovementMethod.getInstance());
		mLap.add(lt.lapTimeDisplay(mMinute[num], mSecond[num], mm_second[num]));
		List<Double> lapa = ti.transSecond(mLap);
		double rlp;
		if (mLap.size() - 1 == 0) {
			rlp = lapa.get(lapa.size()-1);
		} else {
			rlp = lapa.get(lapa.size()-1) - lapa.get(lapa.size() - 2);
		}
		mResetLap.add(ti.transMinute(rlp));
		lap.setText("");
		if (mMode.equals(getResources().getString(R.string.SPLIT))) {
			for (int j = mLap.size() - 1 ; j > mLap.size() - 4; j--) {
				if(j == -1) 
					break;
				lap.append(mLap.get(j) + "\n");
			}
		} else {
			for (int j = mLap.size() - 1; j > mLap.size() - 4; j--) {
				if(j == -1) 
					break;
				lap.append(mResetLap.get(j) + "\n");				
			}
		}
	}
	public void updateOfName(int which) {
		mRestart[which] = System.currentTimeMillis() - mStartDate[which];
		mMinute[which] = (int) ((((System.currentTimeMillis() - mStartDate[which])) / 1000) / 60);
		mSecond[which] = (int) ((((System.currentTimeMillis() - mStartDate[which])) / 1000) % 60);
		mm_second[which] = (int) (((System.currentTimeMillis() - mStartDate[which]) / 10) % 100);
		minuteOfName.get(which).setText(String.format("%1$02d", mMinute[which]));
		secondOfName.get(which).setText(String.format("%1$02d", mSecond[which]));
		msecondOfName.get(which).setText(String.format("%1$02d", mm_second[which]));
	}

	public void checkState() {
		if (mStateOfStopWatch[0].equals(getResources().getString(R.string.finish))
				&& mStateOfStopWatch[1].equals(getResources().getString(R.string.finish))
				&& mStateOfStopWatch[2].equals(getResources().getString(R.string.finish))) {
			mStart.setText(getResources().getString(R.string.reset));
			mStart.setEnabled(true);
			mStateOfStartButton = getResources().getString(R.string.reset);
		} else {
			mStart.setText(getResources().getString(R.string.start));
		}
	}

	class TimeMesure extends Handler {
		private boolean watch1;
		private boolean watch2;
		private boolean watch3;
		private final boolean check = true;
		private Message mMessage = new Message();

		public void start() {
			Log.d("test", "start");
			this.watch1 = true;
			this.watch2 = true;
			this.watch3 = true;
			handleMessage(mMessage);
		}

		public void separateStart(int number) {
			if(number == 1) {
				this.watch1 = true;
			} else if(number == 2) {
				this.watch2 = true;
			} else {
				this.watch3 = true;
			}
			handleMessage(mMessage);
		}
		public void separateStop(int number) {
			if(number == 1) {
				this.watch1 = false;
			} else if(number == 2) {
				this.watch2 = false;
			} else {
				this.watch3 = false;
			}
		}

		@Override
		public void handleMessage(Message msg) {
			this.removeMessages(0);
            if (this.watch1) {
                MainActivity.this.updateOfName(0);
            }
            if (this.watch2) {
                MainActivity.this.updateOfName(1);
            }
            if (this.watch3) { 
                MainActivity.this.updateOfName(2);
            }
			if (this.check) {
				MainActivity.this.checkState();
			}			
            sendMessageDelayed(obtainMessage(0), 10);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem mi) {
		int id = mi.getItemId();
		switch (id) {
		case R.id.previousSetting:
			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			View view = inflater.inflate(R.layout.previous_setting, null);
			final EditText inputName1 = (EditText) view.findViewById(R.id.inputName1);
			final EditText inputName2 = (EditText) view.findViewById(R.id.inputName2);
			final EditText inputName3 = (EditText) view.findViewById(R.id.inputName3);

			new AlertDialog.Builder(MainActivity.this)
					.setView(view)
					.setPositiveButton(
							getResources().getString(R.string.decide),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,int which) {
									setName(inputName1, name.get(0), 0);
									setName(inputName2, name.get(1), 1);
									setName(inputName3, name.get(2), 2);									
								}
							}).show();
			break;
		case R.id.save:
			String[] sumTime = new String[3];
			String[] lap = new String[3];
			String[] resetLap = new String[3];
			if (mStateOfStartButton.equals(getResources().getString(R.string.reset))) {
				if(mLap1.size() == 0 || mLap2.size() == 0 || mLap3.size() == 0) {
					showMessage("全員のラップをとってください");
				} else {
					Intent intent = new Intent(MainActivity.this,	RegisterData.class);
					for(int i = 0;i < 3;i++) {
						lap[i] = mLap.get(i).toString();
						sumTime[i] = mLap.get(i).get(mLap.get(i).size()-1);
						resetLap[i] = mResetLap.get(i).toString();
					}
					intent.putExtra("name", runnerName);
					intent.putExtra("lap", lap);
					intent.putExtra("rlp", resetLap);
					intent.putExtra("sumTime", sumTime);
					startActivity(intent);
				}
			} else {
				showMessage("タイム測定をおわらしてからsaveしてください");
			}
			break;
		case R.id.mode:
			if (mMode.equals(getResources().getString(R.string.SPLIT))) {
				mMode = getResources().getString(R.string.RLP);
			} else {
				mMode = getResources().getString(R.string.SPLIT);
			}
			break;
		}
		return true;
	}

	public void setName(EditText inputName, TextView name, int which) {
		if (inputName.getText().toString().equals("")) {
			name.setText(getResources().getString(R.string.name) + String.valueOf(which + 1));
		} else {
			runnerName[which] = inputName.getText().toString();
			name.setText(inputName.getText().toString());
		}
	}
	
	protected void showMessage(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}