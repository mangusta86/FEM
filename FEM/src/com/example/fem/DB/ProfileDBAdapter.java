package com.example.fem.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProfileDBAdapter {

	public static final String ROW_ID = "_id";
	public static final String NAME = "name";
	public static final String SHAPE = "shape";
	public static final String JP = "jp";
	public static final String JX = "jx";
	public static final String JY = "jy";
	public static final String A = "a";
	public static final String VALUE1 = "value1";
	public static final String VALUE2 = "value2";
	public static final String VALUE3 = "value3";
	public static final String VALUE4 = "value4";
	public static final String VALUE5 = "value5";

	private static final String DATABASE_TABLE = "profiles";

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		// private static final String DATABASE_NAME="ProfileList.db";
		// private static final int SCHEMA_VERSION=1;

		DatabaseHelper(Context context) {
			super(context, DBAdapter.DATABASE_NAME, null,
					DBAdapter.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */

	public ProfileDBAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Open the profiles database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public ProfileDBAdapter open() throws SQLException {
		this.mDbHelper = new DatabaseHelper(this.mCtx);
		this.mDb = this.mDbHelper.getWritableDatabase();
		return this;
	}

	/**
	 * close return type: void
	 */
	public void close() {
		this.mDbHelper.close();
	}

	/**
	 * Create a new profile. If the car is successfully created return the new rowId
	 * for that car, otherwise return a -1 to indicate failure.
	 * 
	 * @param name
	 * @param shape
	 * @param jp
	 * @param jx
	 * @param jy
	 * @param a
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @param value5
	 * 
	 * @return rowId or -1 if failed 
	 */

	public long createProfile(String name, String shape, Double jp, Double jx,
			Double jy, Double a, Double value1, Double value2, Double value3,
			 Double value4, Double value5) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, name);
		initialValues.put(SHAPE, shape);
		initialValues.put(JP, jp);
		initialValues.put(JX, jx);
		initialValues.put(JY, jy);
		initialValues.put(A, a);
		initialValues.put(VALUE1, value1);
		initialValues.put(VALUE2, value2);
		initialValues.put(VALUE3, value3);
		initialValues.put(VALUE4, value4);
		initialValues.put(VALUE5, value5);
		return this.mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the profile with the given rowId
	 * 
	 * @param rowId
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteProfile(long rowId) {

		return this.mDb.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0; //$NON-NLS-1$
	}

	/**
	 * Return a Cursor over the list of all profile in the database
	 * 
	 * @return Cursor over all profiles
	 */
	public Cursor getAllProfiles() {
		
		//new String[] { ROW_ID, NAME,SHAPE, VALUE1 }
		return this.mDb.query(DATABASE_TABLE, null, null, null, null, null, ROW_ID +" ASC");
	}

	/**
	 * Return a Cursor positioned at the element that matches the given rowId
	 * 
	 * @param rowId
	 * @return Cursor positioned to matching element, if found
	 * @throws SQLException
	 *             if car could not be found/retrieved
	 */
	public Cursor getProfile(long rowId) throws SQLException {
		// new String[] { ROW_ID, NAME,SHAPE, VALUE1 }
		Cursor mCursor =

		this.mDb.query(true, DATABASE_TABLE,null, ROW_ID + "=" + rowId, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * Update the element.
	 * 
	 * @param name
	 * @param shape
	 * @param jp
	 * @param jx
	 * @param jy
	 * @param a
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @param value5
	 * 
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateElement(long rowId, String name, String shape, Double jp, Double jx,
			Double jy, Double a, Double value1, Double value2, Double value3,
			 Double value4, Double value5) {
		ContentValues args = new ContentValues();
		args.put(NAME, name);
		args.put(SHAPE, shape);
		args.put(JP, jp);
		args.put(JX, jx);
		args.put(JY, jy);
		args.put(A, a);
		args.put(VALUE1, value1);
		args.put(VALUE2, value2);
		args.put(VALUE3, value3);
		args.put(VALUE4, value4);
		args.put(VALUE5, value5);

		return this.mDb
				.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) > 0;
	}
	/**
	 * Return a String[] with all the profile names
	 * 
	 * @return String[] with all the profile names, if found
	 */
public String[] getArrayProName() {
		
		Cursor C = this.mDb.query(DATABASE_TABLE, new String[]{ROW_ID, NAME} ,
				null, null, null, null, ROW_ID +" ASC");
					
		int i = 0;
		String[] proName = new String[C.getCount()];
		if (C != null && C.moveToNext()) {
				while (C.isAfterLast() == false) {
					proName[i] = C.getString(1);
					C.moveToNext();
					i = i + 1;
				}
		}
				C.close();

		return proName;
	}
}