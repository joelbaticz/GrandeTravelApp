package com.example.joel.grandetravelapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PackageItemSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "ItemSQLiteOpenHelper";

    //Database name and version
    private static final String DATABASE_NAME = "packageitem.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PACKAGEITEM = "packageitem";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_THUMBNAILURL = "thumbnailurl";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_ISACTIVE = "isactive";
    public static final String COLUMN_PROVIDERID = "providerid";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_NUMBEROFFEEDBACKS = "numberoffeedbacks";

    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String WHERE = "WHERE ";

    //Create table string
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_PACKAGEITEM + "("
            + COLUMN_ID + " integer primary key, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_THUMBNAILURL + " text, "
            + COLUMN_LOCATION + " text not null, "
            + COLUMN_DESCRIPTION + " text not null, "
            + COLUMN_PRICE + " double not null, "
            + COLUMN_ISACTIVE + " integer not null, "
            + COLUMN_PROVIDERID + " integer not null, "
            + COLUMN_RATING + " double not null, "
            + COLUMN_NUMBEROFFEEDBACKS + " integer not null);";

    //Customized constructor
    public PackageItemSQLiteOpenHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        //Drop existing table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PACKAGEITEM);

        //Recreate table
        onCreate(sqLiteDatabase);
        Log.d(TAG, "Database updated!");
    }




}
