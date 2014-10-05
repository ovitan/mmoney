package money.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class cls_MONEY_AccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.account, menu);

		return true;
	}
	public void fncG_OpenActivity(View view) {
		Intent intent = new Intent(this, cls_MOENY_AccountList.class);
		startActivity(intent);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.cmdSpeding:
			Intent intent = new Intent(this, cls_MONEY_SpendingActivity.class);
			startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP));
			return true;
		case R.id.cmdHistory:
			Intent wIn_IntentH = new Intent(
					"money.com.cls_MONEY_HistoryActivity.CATEGORY");
			startActivity(wIn_IntentH);
			finish();
			return true;
		case R.id.cmdReport:
			Intent wIn_IntentR = new Intent(
					"money.com.cls_MONEY_ReportActivity.CATEGORY");
			startActivity(wIn_IntentR);
			finish();
			return true;
		case R.id.cmdCollection:
			Intent wIn_Intent = new Intent(
					"money.com.cls_MONEY_CollectionActivity.CATEGORY");
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
}
