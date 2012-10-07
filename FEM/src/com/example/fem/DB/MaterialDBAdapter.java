package com.example.fem.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MaterialDBAdapter {

	public static final String ROW_ID = "_id";
	public static final String NAME = "name";
	public static final String YOUNG = "young";
	public static final String POISSON = "poisson";
	public static final String DENSITY = "density";


	private static final String DATABASE_TABLE = "materials";

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

	public MaterialDBAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Open the materials database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public MaterialDBAdapter open() throws SQLException {
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
	 * Create a new material. If the car is successfully created return the new rowId
	 * for that car, otherwise return a -1 to indicate failure.
	 * 
	 * @param name
	 * @param young
	 * @param poisson
	 * @param density
	 * @return rowId or -1 if failed 
	 */

	public long createMaterial(String name, Double young, Double poisson,
			Double density) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, name);
		initialValues.put(YOUNG, young);
		initialValues.put(POISSON, poisson);
		initialValues.put(DENSITY, density);
		return this.mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Delete the material with the given rowId
	 * 
	 * @param rowId
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteMaterial(long rowId) {

		return this.mDb.delete(DATABASE_TABLE, ROW_ID + "=" + rowId, null) > 0; //$NON-NLS-1$
	}

	/**
	 * Return a Cursor over the list of all materials in the database
	 * 
	 * @return Cursor over all materials 
	 */
	public Cursor getAllMaterials() {
		
		//new String[] { ROW_ID, NAME,YOUNG, POISSON }
		return this.mDb.query(DATABASE_TABLE,null , null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the material that matches the given rowId
	 * 
	 * @param rowId
	 * @return Cursor positioned to matching material, if found
	 * @throws SQLException
	 *             if car could not be found/retrieved
	 */
	public Cursor getMaterial(long rowId) throws SQLException {
		
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
	 * Update the material.
	 * 
	 * @param rowId
	 * @param name
	 * @param young
	 * @param poisson
	 * @param density
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateMaterial(long rowId, String name, Double young, Double poisson,
			Double density) {
		ContentValues args = new ContentValues();
		args.put(NAME, name);
		args.put(YOUNG, young);
		args.put(POISSON, poisson);
		args.put(DENSITY, density);

		return this.mDb
				.update(DATABASE_TABLE, args, ROW_ID + "=" + rowId, null) > 0;
	}
	
	public String[] getArrayMatName() {
		
		Cursor C = this.mDb.query(DATABASE_TABLE, new String[]{ROW_ID, NAME} ,
				null, null, null, null, ROW_ID +" ASC");
					
		int i = 0;
		String[] matName = new String[C.getCount()];
		if (C != null && C.moveToNext()) {
				while (C.isAfterLast() == false) {
					matName[i] = C.getString(1);
					C.moveToNext();
					i = i + 1;
				}
		}
				C.close();

		return matName;
	}
	
}