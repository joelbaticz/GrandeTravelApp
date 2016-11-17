package com.example.joel.grandetravelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PackageItemDataSource {

    private static final String TAG = "ItemDataSource";

    private SQLiteDatabase database;
    private PackageItemSQLiteOpenHelper dbHelper;

    private static final String[] itemColumns =
    {
        PackageItemSQLiteOpenHelper.COLUMN_ID,
        PackageItemSQLiteOpenHelper.COLUMN_NAME,
        PackageItemSQLiteOpenHelper.COLUMN_THUMBNAILURL,
        PackageItemSQLiteOpenHelper.COLUMN_LOCATION,
        PackageItemSQLiteOpenHelper.COLUMN_DESCRIPTION,
        PackageItemSQLiteOpenHelper.COLUMN_PRICE,
        PackageItemSQLiteOpenHelper.COLUMN_ISACTIVE,
        PackageItemSQLiteOpenHelper.COLUMN_PROVIDERID,
        PackageItemSQLiteOpenHelper.COLUMN_RATING,
        PackageItemSQLiteOpenHelper.COLUMN_NUMBEROFFEEDBACKS,
    };

    //Constructor
    public PackageItemDataSource(Context context)
    {
        dbHelper = new PackageItemSQLiteOpenHelper(context);
    }

    //Open
    public void open()
    {
        try
        {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException sqle)
        {
            Log.d(TAG, "Could not open database: " + sqle.getMessage());
        }
    }

    public void close()
    {
        database.close();
    }

    //Insert
    public void insert(int id, String name, String thumbnailUrl, String location, String description, double price, boolean isActive, int providerId, double rating, int numberOfFeedbacks)
    {
        ContentValues values = new ContentValues();

        values.put(PackageItemSQLiteOpenHelper.COLUMN_ID, id);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_NAME, name);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_THUMBNAILURL, thumbnailUrl);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_LOCATION, location);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_DESCRIPTION, description);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_PRICE, price);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_ISACTIVE, isActive);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_PROVIDERID, providerId);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_RATING, rating);
        values.put(PackageItemSQLiteOpenHelper.COLUMN_NUMBEROFFEEDBACKS, numberOfFeedbacks);


        //If entering empty rows the system needs a column name
        //Otherwise just give it null
        long resultId = database.insert(PackageItemSQLiteOpenHelper.TABLE_PACKAGEITEM, null, values);

        Log.d(TAG, "Item " + id + " inserted.");

    }

    //Delete all
    public void deleteAll()
    {
        database.delete(PackageItemSQLiteOpenHelper.TABLE_PACKAGEITEM, null, null);
    }


    //Get all
    public List<PackageItem> getAll(){

        List<PackageItem> items = new ArrayList<>();

        Cursor cursor = database.query(PackageItemSQLiteOpenHelper.TABLE_PACKAGEITEM,
                itemColumns,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            items.add(cursorToItem(cursor));

            cursor.moveToNext();
        }

        cursor.close();

        return items;
    }

    public List<PackageItem> getAllByLocation(String location){

        List<PackageItem> items = new ArrayList<>();

        String whereClause = PackageItemSQLiteOpenHelper.COLUMN_LOCATION + " LIKE ?";
        String[] whereArgs = new String[] {
                "%"+location+"%"
        };

        Cursor cursor = database.query(PackageItemSQLiteOpenHelper.TABLE_PACKAGEITEM,
                itemColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null);



        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            items.add(cursorToItem(cursor));

            cursor.moveToNext();
        }

        cursor.close();

        return items;
    }

    /*
    private int id;
    private String name;
    private String thumbnailUrl;
    private String location;
    private String description;
    private double price;
    private boolean isActive;
    private int providerId;
    private double rating;
    private int numberOfFeedbacks;
    */

    public PackageItem cursorToItem(Cursor cursor)
    {
        PackageItem tempItem = new PackageItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getDouble(5), cursor.getInt(6) == 1, cursor.getInt(7), cursor.getDouble(8), cursor.getInt(9));

        //tempItem.setId(cursor.getLong(0));
        return tempItem;
    }

}
