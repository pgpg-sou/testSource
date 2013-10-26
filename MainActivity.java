package app.ver.runsuke;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

	public TextView name1;
	public TextView name2;
	public TextView name3;
	public TextView minuteOfName1;
	public TextView minuteOfName2;
	public TextView minuteOfName3;
	public TextView secondOfName1;
	public TextView secondOfName2;
	public TextView secondOfName3;
	public TextView msecondOfName1;
	public TextView msecondOfName2;
	public TextView msecondOfName3;
	private List<String> mLap1 = new ArrayList<String>();
	private List<String> mLap2 = new ArrayList<String>();
	private List<String> mLap3 = new ArrayList<String>();
	private List<String> mResetLap1 = new ArrayList<String>();
	private List<String> mResetLap2 = new ArrayList<String>();
	private List<String> mResetLap3 = new ArrayList<String>();
	public TextView lap1;
	public TextView lap2;
	public TextView lap3;
	public Button separateStop1;
	public Button separateStop2;
	public Button separateStop3;
	private long mStartDate1;
	private long mStartDate2;
	private long mStartDate3;
	private long mRestart1;
	private long mRestart2;
	private long mRestart3;
	private int mMinute1;
	private int mMinute2;
	private int mMinute3;
	private int mSecond1;
	private int mSecond2;
	private int mSecond3;
	private int mm_second1;
	private int mm_second2;
	private int mm_second3;
	private Button mLapButton1;
	private Button mLapButton2;
	private Button mLapButton3;
	private Button mStart;
	private String mStateOfStopWatch1;
	private String mStateOfStopWatch2;
	private String mStateOfStopWatch3;
	private String mStateOfStartButton;
	private String mMode;

	public String[] runnerName = { "Name1", "Name2", "Name3" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name1 = (TextView) findViewById(R.id.name1);
		name2 = (TextView) findViewById(R.id.name2);
		name3 = (TextView) findViewById(R.id.name3);
		minuteOfName1 = (TextView) findViewById(R.id.minute1);
		minuteOfName2 = (TextView) findViewById(R.id.minute2);
		minuteOfName3 = (TextView) findViewById(R.id.minute3);
		secondOfName1 = (TextView) findViewById(R.id.second1);
		secondOfName2 = (TextView) findViewById(R.id.second2);
		secondOfName3 = (TextView) findViewById(R.id.second3);
		msecondOfName1 = (TextView) findViewById(R.id.msecond1);
		msecondOfName2 = (TextView) findViewById(R.id.msecond2);
		msecondOfName3 = (TextView) findViewById(R.id.msecond3);
		separateStop1 = (Button) findViewById(R.id.separateStop1);
		separateStop2 = (Button) findViewById(R.id.separateStop2);
		separateStop3 = (Button) findViewById(R.id.separateStop3);
		lap1 = (TextView) findViewById(R.id.history1);
		lap2 = (TextView) findViewById(R.id.history2);
		lap3 = (TextView) findViewById(R.id.history3);
		mLapButton1 = (Button) findViewById(R.id.lapButton1);
		mLapButton2 = (Button) findViewById(R.id.lapButton2);
		mLapButton3 = (Button) findViewById(R.id.lapButton3);
		mStateOfStopWatch1 = getResources().getString(R.string.comingSoon);
		mStateOfStopWatch2 = getResources().getString(R.string.comingSoon);
		mStateOfStopWatch3 = getResources().getString(R.string.comingSoon);
		mStateOfStartButton = getResources().getString(R.string.comingSoon);
		mStart = (Button) findViewById(R.id.start);
		StopWatch mStopWatch = new StopWatch();
		mMode = getResources().getString(R.string.SPLIT);

		mStart.setOnClickListener(mStopWatch);
		separateStop1.setOnClickListener(mStopWatch);
		separateStop2.setOnClickListener(mStopWatch);
		separateStop3.setOnClickListener(mStopWatch);
		mLapButton1.setOnClickListener(mStopWatch);
		mLapButton2.setOnClickListener(mStopWatch);
		mLapButton3.setOnClickListener(mStopWatch);
	}

	class StopWatch implements OnClickListener {
		private TimeMesure tm = new TimeMesure();

		@Override
		public void onClick(View v) {
			View button = (View) v;
			String stateOfButton;
			switch (button.getId()) {
			case R.id.start:
				if (mStateOfStartButton.equals(getResources().getString(R.string.alreadyStarted))) {

				} else if (mStateOfStartButton.equals(getResources().getString(R.string.reset))) {
					lap1.setText(getResources().getString(R.string.empty));
					lap2.setText(getResources().getString(R.string.empty));
					lap3.setText(getResources().getString(R.string.empty));
					mStateOfStopWatch1 = getResources().getString(R.string.comingSoon);
					mStateOfStopWatch2 = getResources().getString(R.string.comingSoon);
					mStateOfStopWatch3 = getResources().getString(R.string.comingSoon);
					separateStop1.setText(getResources().getString(R.string.start));
					separateStop2.setText(getResources().getString(R.string.start));
					separateStop3.setText(getResources().getString(R.string.start));
					minuteOfName1.setText(getResources().getString(R.string.zero));
					minuteOfName2.setText(getResources().getString(R.string.zero));
					minuteOfName3.setText(getResources().getString(R.string.zero));
					secondOfName1.setText(getResources().getString(R.string.zero));
					secondOfName2.setText(getResources().getString(R.string.zero));
					secondOfName3.setText(getResources().getString(R.string.zero));
					msecondOfName1.setText(getResources().getString(R.string.zero));
					msecondOfName2.setText(getResources().getString(R.string.zero));
					msecondOfName3.setText(getResources().getString(R.string.zero));
					mLap1.clear();
					mLap2.clear();
					mLap3.clear();
					mResetLap1.clear();
					mResetLap2.clear();
					mResetLap3.clear();
					
					mStart.setText(getResources().getString(R.string.start));
					mStateOfStartButton = getResources().getString(R.string.comingSoon);

					break;
				} else {
					separateStop1.setText(getResources().getString(R.string.stop));
					separateStop2.setText(getResources().getString(R.string.stop));
					separateStop3.setText(getResources().getString(R.string.stop));
					mStateOfStartButton = getResources().getString(R.string.alreadyStarted);
					mStateOfStopWatch1 = getResources().getString(R.string.alreadyStarted);
					mStateOfStopWatch2 = getResources().getString(R.string.alreadyStarted);
					mStateOfStopWatch3 = getResources().getString(R.string.alreadyStarted);

					mStart.setEnabled(false);
					mStartDate1 = System.currentTimeMillis();
					mStartDate2 = System.currentTimeMillis();
					mStartDate3 = System.currentTimeMillis();

					tm.start();
				}
				break;
			case R.id.separateStop1:
				stateOfButton = separateStop1.getText().toString();
				if (stateOfButton.equals(getResources().getString(R.string.resume))) {
					separateStop1.setText(getResources().getString(R.string.stop));
					mStateOfStopWatch1 = getResources().getString(R.string.alreadyStarted);
					mStartDate1 = System.currentTimeMillis() - mRestart1;
					tm.separateStart(1);
				} else if (stateOfButton.equals(getResources().getString(R.string.start))) {
					separateStop1.setText(getResources().getString(R.string.stop));
					mStateOfStopWatch1 = getResources().getString(R.string.alreadyStarted);
					mStartDate1 = System.currentTimeMillis();

					tm.separateStart(1);
				} else {
					separateStop1.setText(getResources().getString(R.string.resume));
					mStateOfStopWatch1 = getResources().getString(R.string.finish);
					Log.d("test", mStateOfStopWatch1);
					
					tm.separateStop(1);
				}
				break;
			case R.id.separateStop2:
				stateOfButton = separateStop2.getText().toString();
				if (stateOfButton.equals(getResources().getString(R.string.resume))) {
					separateStop2.setText(getResources().getString(R.string.stop));
					mStateOfStopWatch2 = getResources().getString(R.string.alreadyStarted);

					mStartDate2 = System.currentTimeMillis() - mRestart2;
					tm.separateStart(2);
				} else if (stateOfButton.equals(getResources().getString(R.string.start))) {
					separateStop2.setText(getResources().getString(R.string.stop));
					mStateOfStopWatch2 = getResources().getString(R.string.alreadyStarted);

					mStartDate2 = System.currentTimeMillis();
					tm.separateStart(2);
				} else {
					separateStop2.setText(getResources().getString(R.string.resume));
					mStateOfStopWatch2 = getResources().getString(R.string.finish);
					tm.separateStop(2);
				}
				break;

			case R.id.separateStop3:
				stateOfButton = separateStop3.getText().toString();
				if (stateOfButton.equals(getResources().getString(R.string.resume))) {
					separateStop3.setText(getResources().getString(R.string.stop));
					mStateOfStopWatch3 = getResources().getString(R.string.alreadyStarted);

					mStartDate3 = System.currentTimeMillis() - mRestart3;
					tm.separateStart(3);
				} else if (stateOfButton.equals(getResources().getString(R.string.start))) {
					separateStop3.setText(getResources().getString(R.string.stop));
					mStateOfStopWatch3 = getResources().getString(R.string.alreadyStarted);

					mStartDate3 = System.currentTimeMillis();
					tm.separateStart(3);
				} else {
					separateStop3.setText(getResources().getString(R.string.resume));
					mStateOfStopWatch3 = getResources().getString(R.string.finish);
					tm.separateStop(3);
				}
				break;
			case R.id.lapButton1:
				if (mStateOfStopWatch1.equals(getResources().getString(R.string.alreadyStarted))) {
					displayLaptime(lap1, 0, mMinute1, mSecond1, mm_second1, mLap1, mResetLap1);
				} else {

				}
				break;
			case R.id.lapButton2:
				if (mStateOfStopWatch2.equals(getResources().getString(R.string.alreadyStarted))) {
					displayLaptime(lap2, 1, mMinute2, mSecond2, mm_second2, mLap2, mResetLap2);
				} else {

				}
				break;
			case R.id.lapButton3:
				if (mStateOfStopWatch3.equals(getResources().getString(R.string.alreadyStarted))) {
					displayLaptime(lap3, 2, mMinute3, mSecond3, mm_second3, mLap3, mResetLap3);
				} else {

				}
				Log.d("test", mStateOfStopWatch3);
				break;
			}
		}
	}

	public void displayLaptime(TextView lap, int num, int mMinute, int mSecond, int mm_second, List<String> mLap,List<String> mResetLap) {
		Laptime lt = new Laptime();
		TransTools ti = new TransTools();

		lap.setMovementMethod(ScrollingMovementMethod.getInstance());
		mLap.add(lt.lapTimeDisplay(mMinute, mSecond, mm_second));
		List<Double> lapa = ti.transSecond(mLap);
		double rlp;
		if (mLap.size()-1 == 0) {
			rlp = lapa.get(lapa.size()-1);
			mResetLap.add(ti.transMinute(rlp));
		} else {
			rlp = lapa.get(lapa.size()-1) - lapa.get(lapa.size()-2);
			mResetLap.add(ti.transMinute(rlp));
		}
		lap.setText("");
		if (mMode.equals(getResources().getString(R.string.SPLIT))) {
			if (mLap.size() > 3) {
				for (int j = mLap.size() - 1; j > mLap.size() - 4; j--)
					lap.append(mLap.get(j) + "\n");
			} else {
				for (int j = mLap.size() - 1; j >= 0; j--) {
					lap.append(mLap.get(j) + "\n");
				}
			}
		} else {
			if (mLap.size() > 3) {
				for (int j = mLap.size() - 1; j > mLap.size() - 4; j--)
					lap.append(mResetLap.get(j) + "\n");
			} else {
				for (int j = mLap.size() - 1; j >= 0; j--) {
					lap.append(mResetLap.get(j) + "\n");
				}
			}

		}
	}

	public void updateOfName1() {
		mRestart1 = System.currentTimeMillis() - mStartDate1;
		mMinute1 = (int) ((((System.currentTimeMillis() - mStartDate1)) / 1000) / 60);
		mSecond1 = (int) ((((System.currentTimeMillis() - mStartDate1)) / 1000) % 60);
		mm_second1 = (int) (((System.currentTimeMillis() - mStartDate1) / 10) % 100);
		minuteOfName1.setText(String.format("%1$02d", mMinute1));
		secondOfName1.setText(String.format("%1$02d", mSecond1));
		msecondOfName1.setText(String.format("%1$02d", mm_second1));

	}

	public void updateOfName2() {
		mRestart2 = System.currentTimeMillis() - mStartDate2;
		mMinute2 = (int) ((((System.currentTimeMillis() - mStartDate2)) / 1000) / 60);
		mSecond2 = (int) ((((System.currentTimeMillis() - mStartDate2)) / 1000) % 60);
		mm_second2 = (int) (((System.currentTimeMillis() - mStartDate2) / 10) % 100);
		minuteOfName2.setText(String.format("%1$02d", mMinute2));
		secondOfName2.setText(String.format("%1$02d", mSecond2));
		msecondOfName2.setText(String.format("%1$02d", mm_second2));

	}

	public void updateOfName3() {
		mRestart3 = System.currentTimeMillis() - mStartDate3;
		mMinute3 = (int) ((((System.currentTimeMillis() - mStartDate3)) / 1000) / 60);
		mSecond3 = (int) ((((System.currentTimeMillis() - mStartDate3)) / 1000) % 60);
		mm_second3 = (int) (((System.currentTimeMillis() - mStartDate3) / 10) % 100);
		minuteOfName3.setText(String.format("%1$02d", mMinute3));
		secondOfName3.setText(String.format("%1$02d", mSecond3));
		msecondOfName3.setText(String.format("%1$02d", mm_second3));

	}

	public void checkState() {
		if (mStateOfStopWatch1
				.equals(getResources().getString(R.string.finish))
				&& mStateOfStopWatch2.equals(getResources().getString(R.string.finish))
				&& mStateOfStopWatch3.equals(getResources().getString(R.string.finish))) {
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
			this.removeMessages(0);// 既存のメッセージは削除
			if (this.watch1) {
				MainActivity.this.updateOfName1();// 自信が発したメッセージを取得してupdateを実行
				sendMessageDelayed(obtainMessage(0), 10);// 1ミリ秒後にメッセージを出力
			}
			if (this.watch2) {
				MainActivity.this.updateOfName2();// 自信が発したメッセージを取得してupdateを実行
				sendMessageDelayed(obtainMessage(0), 10);// 1ミリ秒後にメッセージを出力
			}
			if (this.watch3) {
				MainActivity.this.updateOfName3();// 自信が発したメッセージを取得してupdateを実行
				sendMessageDelayed(obtainMessage(0), 10);// 1ミリ秒後にメッセージを出力
			}
			if (this.check) {
				MainActivity.this.checkState();
				sendMessageDelayed(obtainMessage(0), 10);

			}
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
									
									if (inputName1.getText().toString().equals("")) {
										name1.setText(getResources().getString(	R.string.name1));
									} else {
										runnerName[0] = inputName1.getText().toString();
										name1.setText(inputName1.getText().toString());
									}
									
									if (inputName2.getText().toString().equals("")) {
										name2.setText(getResources().getString(	R.string.name2));
									} else {
										runnerName[1] = inputName2.getText().toString();

										name2.setText(inputName2.getText().toString());
									}
									if (inputName3.getText().toString().equals("")) {
										name3.setText(getResources().getString(	R.string.name3));
									} else {
										runnerName[2] = inputName3.getText().toString();
										name3.setText(inputName3.getText().toString());
									}
									
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

					lap[0] = mLap1.toString();
					lap[1] = mLap2.toString();
					lap[2] = mLap3.toString();
					sumTime[0] = mLap1.get(mLap1.size()-1);
					sumTime[1] = mLap2.get(mLap2.size()-1);
					sumTime[2] = mLap3.get(mLap3.size()-1);
					resetLap[0] = mResetLap1.toString();
					resetLap[1] = mResetLap2.toString();
					resetLap[2] = mResetLap3.toString();

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

	protected void showMessage(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}
