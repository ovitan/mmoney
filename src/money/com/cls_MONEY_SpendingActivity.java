package money.com;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;

public class cls_MONEY_SpendingActivity extends Activity {

	private TextView tvDisplayDate;

	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spending);
		setCurrentDateOnView();
		addListenerOnButton();
	}

	public void openSecondActivity(View view) {
		Intent intent = new Intent(this, cls_MONEY_CollectionActivity.class);
		startActivity(intent);
	}

	public void setCurrentDateOnView() {

		tvDisplayDate = (TextView) findViewById(R.id.editText3);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		tvDisplayDate.setText(new StringBuilder().append(day).append("-")
				.append(month + 1).append("-").append(year));

	}

	public void addListenerOnButton() {

		tvDisplayDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}

		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:

			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			tvDisplayDate.setText(new StringBuilder().append(day).append("-")
					.append(month + 1).append("-").append(year).append(" "));

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.spending, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.cmdCollection) {
			Intent wIn_Intent = new Intent("money.com.cls_MONEY_CollectionActivity.CATEGORY");
			startActivity(wIn_Intent);
			finish();
		}
		if (id == R.id.cmdHistory) {
			Intent wIn_IntentH = new Intent(
					"money.com.cls_MONEY_HistoryActivity.CATEGORY");
			startActivity(wIn_IntentH);
			finish();
			return true;
		}
		if (id == R.id.cmdReport) {
			return true;
		}
		if (id == R.id.cmdAccount) {
			Intent wIn_Intent = new Intent("money.com.cls_MONEY_AccountActivity.CATEGORY");
			startActivity(wIn_Intent);
			finish();
			return true;
		}
		if (id == R.id.cmdBack) {
			return true;
		}
		if (id == R.id.cmdExit) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			toggleActionBar();
		}
		return true;
	}

	private void toggleActionBar() {
		ActionBar actionBar = getActionBar();

		if (actionBar != null) {
			if (actionBar.isShowing()) {
				actionBar.hide();
			} else {
				actionBar.show();
			}
		}
	}

}
