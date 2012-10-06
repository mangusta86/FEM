package com.example.fem.DB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	public static final String DATABASE_NAME = "Model"; //$NON-NLS-1$

	private static String DB_PATH = "/data/data/com.example.fem/databases/";

	public static final int DATABASE_VERSION = 1;

	private static final String CREATE_TABLE_PROFILES = "create table profiles (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ ProfileDBAdapter.NAME
			+ " TEXT," //$NON-NLS-1$
			+ ProfileDBAdapter.SHAPE
			+ " TEXT," //$NON-NLS-1$
			+ ProfileDBAdapter.JP
			+ " FLOAT(53),"
			+ ProfileDBAdapter.JX
			+ " FLOAT(53),"
			+ ProfileDBAdapter.JY
			+ " FLOAT(53),"
			+ ProfileDBAdapter.A
			+ " FLOAT(53),"
			+ ProfileDBAdapter.VALUE1
			+ " FLOAT(53),"
			+ ProfileDBAdapter.VALUE2
			+ " FLOAT(53)," 
			+ ProfileDBAdapter.VALUE3 
			+ " FLOAT(53)," 
			+ ProfileDBAdapter.VALUE4 
			+ " FLOAT(53)," 
			+ ProfileDBAdapter.VALUE5 
			+ " FLOAT(53)" 
			+ ");"; 
			//$NON-NLS-1$ //$NON-NLS-2$

	private static final String CREATE_TABLE_ELEMENTS = "create table elements (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ ElementDBAdapter.NAME 
			+ " TEXT," //$NON-NLS-1$
			+ ElementDBAdapter.PROFILE 
			+ " TEXT," //$NON-NLS-1$
			+ ElementDBAdapter.MATERIAL 
			+ " TEXT,"  //$NON-NLS-1$  //$NON-NLS-2$
			+ ElementDBAdapter.X1
			+ " FLOAT(53),"
			+ ElementDBAdapter.Y1
			+ " FLOAT(53),"
			+ ElementDBAdapter.Z1
			+ " FLOAT(53),"
			+ ElementDBAdapter.X2
			+ " FLOAT(53),"
			+ ElementDBAdapter.Y2
			+ " FLOAT(53),"
			+ ElementDBAdapter.Z2
			+ " FLOAT(53)"
			+ ");";
	
	private static final String CREATE_TABLE_MATERIALS = "create table materials (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ MaterialDBAdapter.NAME 
			+ " TEXT," //$NON-NLS-1$
			+ MaterialDBAdapter.YOUNG 
			+ " FLOAT(53)," //$NON-NLS-1$
			+ MaterialDBAdapter.POISSON 
			+ " FLOAT(53)," 
			+ MaterialDBAdapter.DENSITY 
			+ " FLOAT(53)"
			+ ");"; //$NON-NLS-1$  //$NON-NLS-2$

	private static final String CREATE_TABLE_LOADS = "create table loads (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ LoadDBAdapter.TYPE 
			+ " TEXT," //$NON-NLS-1$
			+ LoadDBAdapter.CP1 
			+ " FLOAT(53)," //$NON-NLS-1$
			+ LoadDBAdapter.CP2 
			+ " FLOAT(53)," 
			+ LoadDBAdapter.DIRECTION 
			+ " TEXT," 
			+ LoadDBAdapter.INTENSITY
			+ " FLOAT(53)" 
			+ ");"; //$NON-NLS-1$  //$NON-NLS-2$
	
	private static final String CREATE_TABLE_BCS = "create table bcs (_id integer primary key autoincrement, " //$NON-NLS-1$
			+ BCDBAdapter.ELEMENT 
			+ " TEXT," //$NON-NLS-1$
			+ BCDBAdapter.NODE
			+ " TEXT," //$NON-NLS-1$
			+ BCDBAdapter.DISP
			+ " TEXT," 
			+ BCDBAdapter.ROT
			+ " TEXT"
			+ ");"; //$NON-NLS-1$  //$NON-NLS-2$
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

	// ////////////

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			DBHelper.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DATABASE_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DATABASE_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DATABASE_NAME;
		db = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	public DBAdapter open() throws SQLException {
		this.db = this.DBHelper.getWritableDatabase();
		return this;
	}

	public DBAdapter open2() throws SQLException {
		try {

			this.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			this.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}
		return this;

	}

	/**
	 * close the db return type: void
	 */
	public void close() {
		this.DBHelper.close();
	}

	// /////////////
	public class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_PROFILES);
			db.execSQL(CREATE_TABLE_ELEMENTS);
			db.execSQL(CREATE_TABLE_MATERIALS);
			db.execSQL(CREATE_TABLE_LOADS);
			db.execSQL(CREATE_TABLE_BCS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Adding any table mods to this guy here
		}

	}

}
