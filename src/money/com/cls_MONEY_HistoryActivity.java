package money.com;

import money.util.cls_MONEY_SQLiteData;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class cls_MONEY_HistoryActivity extends Activity {
	private cls_MONEY_SQLiteData dbHelper;
	private SimpleCursorAdapter dataAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);

		dbHelper = new cls_MONEY_SQLiteData(this);
		dbHelper.open();

		//dbHelper.InsertSomeAccount();
		//dbHelper.fncG_DeleteAllHisroty();
		//dbHelper.InsertSomeHistory();

		displayListView();

	}

	private void displayListView() {

		Cursor cursor = dbHelper.fncG_GetAllHistory();

		String[] columns = new String[] { cls_MONEY_SQLiteData.KEY_ROWID,
				cls_MONEY_SQLiteData.KEY_ACCOUNT,
				cls_MONEY_SQLiteData.KEY_CATE,
				cls_MONEY_SQLiteData.KEY_DESCRIPTION,
				cls_MONEY_SQLiteData.KEY_PRICE, cls_MONEY_SQLiteData.KEY_DATEH };

		int[] to = new int[] { R.id.code, R.id.name, R.id.continent,
				R.id.region, R.id.price, R.id.date };

		dataAdapter = new SimpleCursorAdapter(this, R.layout.list_history,
				cursor, columns, to, 0);

		ListView listView = (ListView) findViewById(R.id.lv_custom_list);

		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {

				Cursor cursor = (Cursor) listView.getItemAtPosition(position);

				String countryCode = cursor.getString(cursor
						.getColumnIndexOrThrow("_id"));
				Toast.makeText(getApplicationContext(), countryCode,
						Toast.LENGTH_SHORT).show();

			}
		});

	}
}
