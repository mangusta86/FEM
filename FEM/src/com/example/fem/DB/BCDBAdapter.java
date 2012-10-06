package com.example.fem.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BCDBAdapter {


		public static final String ROW_ID = "_id";
		public static final String ELEMENT = "element";
		public static final String NODE = "node";
		public static final String DISP = "disp";
		public static final String ROT = "rot";

		private static final String DATABASE_TABLE = "bcs";

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

		public BCDBAdapter(Context ctx) {
			this.mCtx = ctx;
		}

		/**
		 * Open the bcs database. If it cannot be opened, try to create a new
		 * instance of the database. If it cannot be created, throw an exception to
		 * signal the failure
		 * 
		 * @return this (self reference, allowing this to be chained in an
		 *         initialization call)
		 * @throws SQLException
		 *             if the database could be neither opened or created
		 */
		public BCDBAdapter open() throws SQLException {
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
		 * Create a new bc. If the bc is successfully created return the new rowId
		 * for that bc, otherwise return a -1 to indicate failure.
		 * 
		 * @param element
		 * @param node
		 * @param disp
		 * @param rot
		 * @return rowId or -1 if failed
		 */

		public long createProfile(String element, String node, String disp,
				String rot) {
			ContentValues initialValues = new ContentValues();
			initialValues.put(ELEMENT, element);
			initialValues.put(NODE, node);
			initialValues.put(DISP, disp);
			initialValues.put(ROT, rot);
			return this.mDb.insert(DATABASE_TABLE, null, initialValues);
		}

		/**
		 * Delete the bc with the given rowId
		 * 
		 * @param rowId
		 * @return true if deleted, false otherwise
		 */
		public boolean deleteBC(long rowId) {

			return this.mDb.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0; //$NON-NLS-1$
		}

		/**
		 * Return a Cursor over the list of all bcs in the database
		 * 
		 * @return Cursor over all bcs
		 */
		public Cursor getAllBCs() {
			
			//new String[] { ROW_ID, NAME,YOUNG, POISSON }
			return this.mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
		}

		/**
		 * Return a Cursor positioned at the bc that matches the given rowId
		 * 
		 * @param rowId
		 * @return Cursor positioned to matching bc, if found
		 * @throws SQLException
		 *             if car could not be found/retrieved
		 */
		public Cursor getBC(long rowId) throws SQLException {

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
		 * Update the bc.
		 * 
		 * @param rowId
		 * @param element
		 * @param node
		 * @param disp
		 * @param rot
		 * @return true if the note was successfully updated, false otherwise
		 */
		public boolean updateBC(long rowId, String element, String node,
				String disp, String rot) {
			ContentValues args = new ContentValues();
			args.put(ELEMENT, element);
			args.put(NODE, node);
			args.put(DISP, disp);
			args.put(ROT, rot);

			return this.mDb
					.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) > 0;
		}
	}