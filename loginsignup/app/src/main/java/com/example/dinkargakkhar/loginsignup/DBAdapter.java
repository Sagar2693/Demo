package com.example.dinkargakkhar.loginsignup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ID = "centennialid";
    static final String KEY_FNAME  ="firstname";
    static final String KEY_LNAME  ="lastname";
    static final String KEY_EMAIL  ="emailid";
    static final String KEY_DOB  ="dateofbirth";
    static final String KEY_HOBBY  ="hobby";
    static final String KEY_PASS="password";
    static final String KEY_PROGRAM  ="program";
    static final String KEY_DEPT ="department";
    static final String KEY_CAMPUS  ="campus";

    static final String KEY_POS  ="position";


    static final String TAG ="DBAdapter";

    static final String DATABASE_TABLE ="user";
    static final int DATABASE_VERSION=1;

    static final String DATABASE_NAME ="CentennialMeetup.db";

    private static final String createTable = "CREATE TABLE User (centennialid INTEGER PRIMARY KEY ,firstname TEXT, lastname TEXT, emailid TEXT, dateofbirth TEXT,hobby TEXT,password TEXT,program TEXT,department TEXT,campus TEXT,position TEXT)";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctxt)
    {
       this.context = ctxt;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(createTable);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.v(TAG, "Upgrading database from version" + oldVersion + "to" + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS user");
            onCreate(db);
        }
    }
        public  DBAdapter open() throws SQLException
        {
            db = DBHelper.getWritableDatabase();
            return this;
        }
    public void close(){ DBHelper.close();}

    public boolean insertUser(Integer id,String fname, String lname,String email,String dob,String hobby,String pass,String program, String dept, String campus,String pos)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID,id);
        initialValues.put(KEY_FNAME,fname);
        initialValues.put(KEY_LNAME,lname);
        initialValues.put(KEY_EMAIL,email);
        initialValues.put(KEY_DOB,dob);
        initialValues.put(KEY_HOBBY,hobby);
        initialValues.put(KEY_PASS,pass);
        initialValues.put(KEY_PROGRAM,program);
        initialValues.put(KEY_DEPT,dept);
        initialValues.put(KEY_CAMPUS,campus);
        initialValues.put(KEY_POS,pos);
         db.insert("User",null,initialValues);
        return true;

    }
    public Cursor getUser(String user,String pass)
    {
        return db.query("User", new String[] {KEY_ID, KEY_PASS,
        }, KEY_ID + " = ?" + " AND "+KEY_PASS +" = ?"  ,new String[]{user,pass}, null, null, null);
    }


    }


