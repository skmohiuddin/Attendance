package com.turningpoint.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler2 extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "coursedb2";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mycourses2";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "customer";

    // below variable id for our course duration column.
    private static final String DURATION_COL = "phone";
    private static final String NAME_COL1 = "date";
    private static final String NAME_COL2 = "pcs";
    private static final String NAME_COL3 = "weight";
    private static final String NAME_COL4 = "rate";
    private static final String NAME_COL5 = "deb";
    private static final String NAME_COL6 = "crd";
    private static final String NAME_COL7 = "thistime";
    private static final String NAME_COL8 = "sendornot";

    // below variable for our course description column.

    // creating a constructor for our database handler.
    public DBHandler2(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + NAME_COL1 + " TEXT,"
                + NAME_COL2 + " TEXT,"
                + NAME_COL3 + " TEXT,"
                + NAME_COL4 + " TEXT,"
                + NAME_COL5 + " TEXT,"
                + NAME_COL6 + " TEXT,"
                + NAME_COL7 + " TEXT,"
                + NAME_COL8 + " TEXT)";


        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }
    public void reset_table(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name='"+TABLE_NAME+"';");

        db.close();
    }
    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String a, String b,String c,String d) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, a);
        values.put(DURATION_COL, b);
        values.put(NAME_COL1, c);
        values.put(NAME_COL2, d);
        db.insert(TABLE_NAME, null, values);
    }
    public void deleteCourse(String d) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.execSQL("delete from "+ TABLE_NAME +" WHERE  date >?",new String[]{d});

    }
    public ArrayList<CourseModel3> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<CourseModel3> courseModalArrayList3 = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList3.add(new CourseModel3(
                        cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getString(2)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();

        return courseModalArrayList3;
    }


    public ArrayList<String> getAllPlayers(String id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " WHERE sendornot ='no'  AND id=? ORDER BY customer  ASC", new String[]{id});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {

            String name = cur.getString(1);
            String uname = cur.getString(2);
            String uname2 = cur.getString(3);
            String uname3 = cur.getString(4);
            String uname4 = cur.getString(5);
            String uname5 = cur.getString(6);
            String uname6 = cur.getString(7);
            String uname7 = cur.getString(8);
            String uname8 = cur.getString(9);
            array.add(name);
            array.add(uname);
            array.add(uname2);
            array.add(uname3);
            array.add(uname4);
            array.add(uname5);
            array.add(uname6);
            array.add(uname7);
            array.add(uname8);

        }
        return array;
    }

    public int getSumValuecount4(){
        int sum=0;
        int Total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*)  from  mycourses2 where date='0.5'", new String[]{});

        if(cursor.moveToFirst())
            sum = cursor.getInt(Total);
        ;
        return sum;
    }
    public int getSumValuecount5(){
        int sum=0;
        int Total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*)  from  mycourses2 where date='1.0'", new String[]{});

        if(cursor.moveToFirst())
            sum = cursor.getInt(Total);

        return sum;
    }
    public int getSumValuecount3(){
        int sum=0;
        int Total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum(phone)  from  mycourses2", new String[]{});

        if(cursor.moveToFirst())
            sum = cursor.getInt(Total);

        return sum;
    }
    public int getSumValuecount6(){
        int sum=0;
        int Total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select sum(pcs)  from  mycourses2", new String[]{});

        if(cursor.moveToFirst())
            sum = cursor.getInt(Total);

        return sum;
    }
    public int getSumValuecount7(String s){
        int sum=0;
        int Total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*)  from  mycourses2 where customer=?", new String[]{s});

        if(cursor.moveToFirst())
            sum = cursor.getInt(Total);

        return sum;
    }
    public int getSumValuecount8(){
        int sum=0;
        int Total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*)  from  mycourses2", new String[]{});

        if(cursor.moveToFirst())
            sum = cursor.getInt(Total);

        return sum;
    }
    public String getSumValuecount2(){
        String sum="0";
        int Total = 1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select *  from  mycourses2", new String[]{});

        if(cursor.moveToLast())
            sum = cursor.getString(Total);
        cursor.close();
        return sum;
    }
    public ArrayList<String> getAllPlayers2(String sendornot) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME
                + " WHERE weight !='' AND id !=? ORDER BY customer  ASC", new String[]{"1"});
        ArrayList<String> array = new ArrayList<String>();
        while (cur.moveToNext()) {

            String name = cur.getString(1);
            String uname = cur.getString(2);
            String uname2 = cur.getString(3);
            String uname3 = cur.getString(4);
            String uname4 = cur.getString(5);
            String uname5 = cur.getString(6);
            String uname6 = cur.getString(7);
            String uname7 = cur.getString(8);
            String uname8 = cur.getString(9);
            array.add(name);
            array.add(uname);
            array.add(uname2);
            array.add(uname3);
            array.add(uname4);
            array.add(uname5);
            array.add(uname6);
            array.add(uname7);
            array.add(uname8);

        }
        return array;
    }
    public void updateCourse(String originaltime,String sendornot) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.

        values.put(NAME_COL8, sendornot);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "customer=?", new String[]{originaltime});

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}