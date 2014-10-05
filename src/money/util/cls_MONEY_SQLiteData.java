package money.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import money.model.cls_MONEY_Account;
import money.model.cls_MONEY_History;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class cls_MONEY_SQLiteData {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_CATE = "cate";
	public static final String KEY_NAME = "name";
	public static final String KEY_PRICE = "price";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_DATEH = "dateh";
	public static final String KEY_ACCOUNT = "idaccount";

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DATABASE_NAME = "Money";
	private static final String SQLITE_TABLE_ACCOUNT = "Account";
	private static final String SQLITE_TABLE_HISTORY = "History";
	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	private static final String DATABASE_CREATE_HISTORY = "CREATE TABLE if not exists "
			+ SQLITE_TABLE_HISTORY
			+ " ("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_ACCOUNT
			+ ","
			+ KEY_DESCRIPTION
			+ ","
			+ KEY_DATEH
			+ ","
			+ KEY_CATE
			+ ","
			+ KEY_PRICE + ", UNIQUE (" + KEY_ROWID + "));";

	private static final String DATABASE_CREATE_ACCOUNT = "CREATE TABLE if not exists "
			+ SQLITE_TABLE_ACCOUNT
			+ " ("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ KEY_NAME
			+ ","
			+ KEY_PRICE + ", UNIQUE (" + KEY_NAME + "));";

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_ACCOUNT);
			db.execSQL(DATABASE_CREATE_HISTORY);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE_ACCOUNT);
			db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE_HISTORY);
			onCreate(db);
		}
	}

	public cls_MONEY_SQLiteData(Context ctx) {
		this.mCtx = ctx;
	}

	public cls_MONEY_SQLiteData open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}

	/**
	 * Create Data Table Account
	 * 
	 * @param iStr_Name
	 * @param IDob_Price
	 * @return
	 */
	public long fncG_CreateAccount(cls_MONEY_Account iClc_Acount) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, iClc_Acount.getName());
		if (iClc_Acount.getPrice() > 0) {
			initialValues.put(KEY_PRICE, iClc_Acount.getPrice());
		}

		return mDb.insert(SQLITE_TABLE_ACCOUNT, null, initialValues);
	}

	/**
	 * Create Data Table History
	 * 
	 * @param iCls_History
	 * @return
	 */
	public long fncG_CreateHostory(cls_MONEY_History iCls_History) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ACCOUNT, iCls_History.getIdAccount());
		initialValues.put(KEY_CATE, iCls_History.getCate());
		initialValues.put(KEY_DESCRIPTION, iCls_History.getDesciption());
		initialValues.put(KEY_PRICE, iCls_History.getPrice());
		initialValues.put(KEY_DATEH,
				fncG_FormatDate(iCls_History.getDateHistory()));

		return mDb.insert(SQLITE_TABLE_HISTORY, null, initialValues);
	}

	/**
	 * Format Date
	 * 
	 * @param iStrDate
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public String fncG_FormatDate(Date iStrDate) {
		SimpleDateFormat wDt_Df = new SimpleDateFormat("dd/MM/yyyy");
		return wDt_Df.format(iStrDate);
	}

	/**
	 * Delete One History
	 * 
	 * @param iInt_Id
	 * @return
	 */
	public boolean fncG_DeleteHistory(int iInt_Id) {
		int wCk_Delete = 0;
		wCk_Delete = mDb.delete(SQLITE_TABLE_HISTORY, KEY_ROWID + " = "
				+ iInt_Id, null);
		return wCk_Delete > 0;
	}

	/**
	 * Delete All History
	 * 
	 * @return
	 */
	public boolean fncG_DeleteAllHisroty() {
		int wCk_Delete = 0;
		wCk_Delete = mDb.delete(SQLITE_TABLE_HISTORY, null, null);
		return wCk_Delete > 0;
	}

	/**
	 * Delete One Account
	 * 
	 * @param iInt_Id
	 * @return
	 */
	public boolean fncG_DeleteAccount(int iInt_Id) {

		int wCk_Delete = 0;
		wCk_Delete = mDb.delete(SQLITE_TABLE_ACCOUNT, KEY_ROWID + " = "
				+ iInt_Id, null);
		return wCk_Delete > 0;

	}

	/**
	 * Function Get All Account
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Cursor fncG_GetAllAccount() throws SQLException {
		Cursor mCursor = null;
		mCursor = mDb.query(true, SQLITE_TABLE_ACCOUNT, new String[] {
				KEY_ROWID, KEY_NAME, KEY_PRICE }, null, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/**
	 * Get All Account To Spending
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Cursor fncG_GetAccountSpending() throws SQLException {
		Cursor mCursor = null;
		mCursor = mDb.query(true, SQLITE_TABLE_ACCOUNT, new String[] {
				KEY_ROWID, KEY_NAME, KEY_PRICE }, KEY_PRICE + " > 0 ", null,
				null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/**
	 * Get All Date In History
	 * 
	 * @return
	 */
	public Cursor fncG_GetAllHistory_Date() {

		Cursor mCursor = mDb.query(SQLITE_TABLE_HISTORY,
				new String[] { KEY_DATEH }, null, null, null, null, KEY_DATEH
						+ " desc");

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * Get All In History
	 * 
	 * @return
	 */
	public Cursor fncG_GetAllHistory_ByDate(Date iDt_Date) {

		Cursor mCursor = mDb.query(SQLITE_TABLE_HISTORY, new String[] {
				KEY_ROWID, KEY_ACCOUNT, KEY_CATE, KEY_PRICE, KEY_DATEH,
				KEY_DESCRIPTION }, KEY_DATEH + " = " + iDt_Date, null, null,
				null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * Get All In History
	 * 
	 * @return
	 */
	public Cursor fncG_GetAllHistory() {

		Cursor mCursor = mDb.query(SQLITE_TABLE_HISTORY, new String[] {
				KEY_ROWID, KEY_ACCOUNT, KEY_CATE, KEY_PRICE, KEY_DATEH,
				KEY_DESCRIPTION }, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * Get All In History
	 * 
	 * @return
	 */
	public Cursor fncG_GetQueyAllHistory() {

		String sql = "SELECT History._id,Account .name,History.description,History.price,History.dateh, "
				+ " case History.cate when 0 then 'Spending' when "
				+ " 1 then 'Collecting' end as 'cate' FROM History,Account "
				+ " Where History.idaccount = Account ._id order by dateh desc";

		Cursor mCursor = mDb.rawQuery(sql, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public void InsertSomeAccount() {

		cls_MONEY_Account iClc_Acount1 = new cls_MONEY_Account("ATM", 5000000);
		cls_MONEY_Account iClc_Acount2 = new cls_MONEY_Account("BAG", 5000000);
		cls_MONEY_Account iClc_Acount3 = new cls_MONEY_Account("BANK", 5000000);
		fncG_CreateAccount(iClc_Acount1);
		fncG_CreateAccount(iClc_Acount2);
		fncG_CreateAccount(iClc_Acount3);

	}

	public void InsertSomeHistory() {
		Date i = new Date();
		cls_MONEY_History iH1 = new cls_MONEY_History(1, 0, 200000,
				"Play Game", i);
		fncG_CreateHostory(iH1);
		cls_MONEY_History iH2 = new cls_MONEY_History(1, 1, 200000, "Bank", i);
		fncG_CreateHostory(iH2);
		cls_MONEY_History iH3 = new cls_MONEY_History(1, 0, 400000, "Bank", i);
		fncG_CreateHostory(iH3);
		cls_MONEY_History iH4 = new cls_MONEY_History(1, 1, 300000, "BirthDay",
				i);
		fncG_CreateHostory(iH4);
		cls_MONEY_History iH5 = new cls_MONEY_History(1, 0, 300000, "Bank", i);
		fncG_CreateHostory(iH5);
	}
}
