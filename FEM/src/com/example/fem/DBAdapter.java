package com.example.fem;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	public static final String DATABASE_NAME = "Model"; //$NON-NLS-1$

	public static final int DATABASE_VERSION = 1;

	private static final String CREATE_TABLE_PROFILES = "create table profiles (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ ProfileDBAdapter.NAME
			+ " TEXT," //$NON-NLS-1$
			+ ProfileDBAdapter.SHAPE
			+ " TEXT," //$NON-NLS-1$
			+ ProfileDBAdapter.VALUE1
			+ " FLOAT(53),"
			+ ProfileDBAdapter.VALUE2
			+ " FLOAT(53)," + ProfileDBAdapter.VALUE3 + " FLOAT(53)" + ");"; //$NON-NLS-1$ //$NON-NLS-2$

	private static final String CREATE_TABLE_ELEMENTS = "create table elements (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ ElementDBAdapter.NAME + " TEXT," //$NON-NLS-1$
			+ ElementDBAdapter.PROFILE + " TEXT," //$NON-NLS-1$
			+ ElementDBAdapter.MATERIAL + " TEXT" + ");"; //$NON-NLS-1$  //$NON-NLS-2$

	private static final String CREATE_TABLE_MATERIALS = "create table materials (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ MaterialDBAdapter.NAME + " TEXT," //$NON-NLS-1$
			+ MaterialDBAdapter.YOUNG + " TEXT," //$NON-NLS-1$
			+ MaterialDBAdapter.POISSON + " TEXT" + ");"; //$NON-NLS-1$  //$NON-NLS-2$

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	/**
	 * Constructor
	 * 
	 * @param ctx
	 */
	public DBAdapter(Context ctx) {
		this.context = ctx;
		this.DBHelper = new DatabaseHelper(this.context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_PROFILES);
			db.execSQL(CREATE_TABLE_ELEMENTS);
			db.execSQL(CREATE_TABLE_MATERIALS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Adding any table mods to this guy here
		}
	}

	/**
	 * open the db
	 * 
	 * @return this
	 * @throws SQLException
	 *             return type: DBAdapter
	 */
	public DBAdapter open() throws SQLException {
		this.db = this.DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * close the db return type: void
	 */
	public void close() {
		this.DBHelper.close();
	}

}
