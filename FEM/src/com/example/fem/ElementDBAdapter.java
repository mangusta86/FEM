package com.example.fem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ElementDBAdapter {

	public static final String ROW_ID = "_id";
	public static final String NAME = "name";
	public static final String PROFILE = "profile";
	public static final String MATERIAL = "material";
	//public static final String VALUE2 = "value2";
	//public static final String VALUE3 = "value3";

	private static final String DATABASE_TABLE = "elements";

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

	public ElementDBAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Open the cars database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public ElementDBAdapter open() throws SQLException {
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
	 * Create a new car. If the car is successfully created return the new rowId
	 * for that car, otherwise return a -1 to indicate failure.
	 * 
	 * @param name
	 * @param model
	 * @param year
	 * @return rowId or -1 if failed sistemare questa parte
	 */

	public long createProfile(String name, String profile, String material //,
			//Double value2, Double value3
			) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, name);
		initialValues.put(PROFILE, profile);
		initialValues.put(MATERIAL, material);
	//	initialValues.put(VALUE2, value2);
	//	initialValues.put(VALUE3, value3);
		return this.mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the car with the given rowId
	 * 
	 * @param rowId
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteProfile(long rowId) {

		return this.mDb.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0; //$NON-NLS-1$
	}

	/**
	 * Return a Cursor over the list of all cars in the database
	 * 
	 * @return Cursor over all cars sistemare
	 */
	public Cursor getAllElements() {

		
		//aggiungere alla String[] una colonna da return nel cursor ROW_ID
		return this.mDb.query(DATABASE_TABLE, new String[] { NAME
				}, null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the car that matches the given rowId
	 * 
	 * @param rowId
	 * @return Cursor positioned to matching car, if found
	 * @throws SQLException
	 *             if car could not be found/retrieved
	 */
	public Cursor getElement(long rowId) throws SQLException {

		Cursor mCursor =

		this.mDb.query(true, DATABASE_TABLE, new String[] { ROW_ID, NAME,
				PROFILE, MATERIAL }, ROW_ID + "=" + rowId, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * Update the car.
	 * 
	 * @param rowId
	 * @param name
	 * @param model
	 * @param year
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateElement(long rowId, String name, String profile,
			String material) {
		ContentValues args = new ContentValues();
		args.put(NAME, name);
		args.put(PROFILE, profile);
		args.put(MATERIAL, material);

		return this.mDb
				.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) > 0;
	}
}
