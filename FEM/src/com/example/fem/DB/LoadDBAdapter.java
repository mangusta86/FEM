package com.example.fem.DB;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoadDBAdapter {

	public static final String ROW_ID = "_id";
	public static final String TYPE = "type";
	public static final String CP1 = "cp1";
	public static final String CP2 = "cp2";
	public static final String DIRECTION = "direction";
	public static final String INTENSITY = "intensity";

	private static final String DATABASE_TABLE = "loads";

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

	public LoadDBAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Open the loads database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public LoadDBAdapter open() throws SQLException {
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
	 * Create a new load. If the load is successfully created return the new rowId
	 * for that load, otherwise return a -1 to indicate failure.
	 * 
	 * @param type
	 * @param cp1
	 * @param cp2
	 * @param direction
	 * @param intensity
	 * @return rowId or -1 if failed
	 */

	public long createLoad(String type, Double cp1, Double cp2, String direction,
			Double intensity) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(TYPE, type);
		initialValues.put(CP1, cp1);
		initialValues.put(CP2, cp2);
		initialValues.put(DIRECTION, direction);
		initialValues.put(INTENSITY, intensity);
		return this.mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the load with the given rowId
	 * 
	 * @param rowId
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteLoad(long rowId) {

		return this.mDb.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0; //$NON-NLS-1$
	}

	/**
	 * Return a Cursor over the list of all loads in the database
	 * 
	 * @return Cursor over all loads
	 */
	public Cursor getAllLoads() {
		
		//	new String[] { ROW_ID, NAME,YOUNG, POISSON }
		return this.mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the load that matches the given rowId
	 * 
	 * @param rowId
	 * @return Cursor positioned to matching load, if found
	 * @throws SQLException
	 *             if load could not be found/retrieved
	 */
	public Cursor getLoad(long rowId) throws SQLException {
		
		//new String[] { ROW_ID, NAME,YOUNG, POISSON }
		Cursor mCursor =

		this.mDb.query(true, DATABASE_TABLE, null, ROW_ID + "=" + rowId, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * Update the load.
	 * 
	 * @param rowId
	 * @param type
	 * @param cp1
	 * @param cp2
	 * @param direction
	 * @param intensity
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateLoad(long rowId,String type, Double cp1, Double cp2, String direction,
			Double intensity) {
		ContentValues args = new ContentValues();
		args.put(TYPE, type);
		args.put(CP1, cp1);
		args.put(CP2, cp2);
		args.put(DIRECTION, direction);
		args.put(INTENSITY, intensity);

		return this.mDb
				.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) > 0;
	}
}