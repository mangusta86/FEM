package com.example.fem.DB;


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
	public static final String X1 = "x1";
	public static final String Y1 = "y1";
	public static final String Z1 = "z1";
	public static final String X2 = "x2";
	public static final String Y2 = "y2";
	public static final String Z2 = "z2";
	
	private static final String DATABASE_TABLE = "elements";

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {


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
	 * Open the elements database. If it cannot be opened, try to create a new
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
	 * @param profile
	 * @param material
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @return rowId or -1 if failed
	 */

	public long createElement(String name, String profile, String material,
			Double x1, Double y1, Double z1, Double x2, Double y2, Double z2) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, name);
		initialValues.put(PROFILE, profile);
		initialValues.put(MATERIAL, material);
		initialValues.put(X1, x1);
		initialValues.put(Y1, y1);
		initialValues.put(Z1, z1);
		initialValues.put(X2, x2);
		initialValues.put(Y2, y2);
		initialValues.put(Z2, z2);
		
		return this.mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the element with the given rowId
	 * 
	 * @param rowId
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteProfile(long rowId) {

		return this.mDb.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0; //$NON-NLS-1$
	}

	/**
	 * Return a Cursor over the list of all elements in the database
	 * 
	 * @return Cursor over all elements
	 */
	public Cursor getAllElements() {
	
		return this.mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
	}
	
	/**
	 * Return a Cursor over the list of all elements in the database (query over names)
	 * 
	 * @return Cursor over all elements
	 */
	public Cursor getAllElementsNames() {

	
		return this.mDb.query(DATABASE_TABLE, new String[] { ROW_ID,NAME}, null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the element that matches the given rowId
	 * 
	 * @param rowId
	 * @return Cursor positioned to matching element, if found
	 * @throws SQLException
	 *             if element could not be found/retrieved
	 */
	public Cursor getElement(long rowId) throws SQLException {
		
		// new String[] { ROW_ID, NAME,PROFILE, MATERIAL }
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
	 * @param rowId
	 * @param name
	 * @param profile
	 * @param material
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
