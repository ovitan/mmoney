package money.com;

import java.text.DecimalFormat;

import money.util.cls_MONEY_SQLiteData;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
//import android.widget.Toast;
import android.widget.TextView;

public class cls_MONEY_HistoryActivity extends Activity {
	private cls_MONEY_SQLiteData dbHelper;
	private SimpleCursorAdapter dataAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);

		dbHelper = new cls_MONEY_SQLiteData(this);
		dbHelper.open();

		// dbHelper.InsertSomeAccount();
		// dbHelper.fncG_DeleteAllHisroty();
		// dbHelper.InsertSomeHistory();

		fncG_DisPlayView();

	}

	private void fncG_DisPlayView() {

		Cursor cursor = dbHelper.fncG_GetQueyAllHistory();

		String[] columns = new String[] { cls_MONEY_SQLiteData.KEY_NAME,
				cls_MONEY_SQLiteData.KEY_DESCRIPTION,
				cls_MONEY_SQLiteData.KEY_PRICE, cls_MONEY_SQLiteData.KEY_DATEH,
				cls_MONEY_SQLiteData.KEY_CATE };

		int[] to = new int[] { R.id.name, R.id.description, R.id.price,
				R.id.date, R.id.cate };

		dataAdapter = new SimpleCursorAdapter(this, R.layout.list_history,
				cursor, columns, to, 0);
		dataAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			public boolean setViewValue(View view, Cursor cursor,
					int columnIndex) {
				int wPrice = cursor.getColumnIndex("price");
				if (view.getId() == R.id.price) {
					((TextView) view).setText(fncG_FormatDouble("###,###,###",
							cursor.getDouble(wPrice)));
					return true;
				}
				return false;
			}
		});
		ListView listView = (ListView) findViewById(R.id.lv_custom_list);

		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {

//				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
//
//				String countryCode = cursor.getString(cursor
//						.getColumnIndexOrThrow("_id"));
//				Toast.makeText(getApplicationContext(), countryCode,
//						Toast.LENGTH_SHORT).show();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.history, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.cmdSpeding:
			Intent intent = new Intent(this, cls_MONEY_SpendingActivity.class);
			startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP));
			return true;
		case R.id.cmdCollection:
			Intent wIn_IntentH = new Intent(
					"money.com.cls_MONEY_CollectionActivity.CATEGORY");
			startActivity(wIn_IntentH);
			finish();
			return true;
		case R.id.cmdReport:
			Intent wIn_IntentR = new Intent(
					"money.com.cls_MONEY_ReportActivity.CATEGORY");
			startActivity(wIn_IntentR);
			finish();
			return true;
		case R.id.cmdAccount:
			Intent wIn_Intent = new Intent(
					"money.com.cls_MONEY_AccountActivity.CATEGORY");
			startActivity(wIn_Intent);
			finish();
			return true;
		case R.id.cmdBack:
			return true;
		case R.id.cmdExit:
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	/**
	 * Format String form Double
	 * 
	 * @param pattern
	 * @param value
	 * @return
	 */
	public String fncG_FormatDouble(String iStr_Pattern, double iDob_Value) {
		DecimalFormat myFormatter = new DecimalFormat(iStr_Pattern);
		String output = myFormatter.format(iDob_Value);
		return output;
	}
}
