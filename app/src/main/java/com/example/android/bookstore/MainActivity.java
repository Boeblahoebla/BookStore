package com.example.android.bookstore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bookstore.data.ProductContract.ProductEntry;
import com.example.android.bookstore.data.ProductDbHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private ProductDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new ProductDbHelper(this);

        // insert data into the database
        insertDummyData();

        // read data from the database
        readData();

    }

    // method to fill the database with data
    private void insertDummyData(){

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and the product's attributes are the values.
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "Android 101");
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, 5);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 2);
        values.put(ProductEntry.COLUMN_SUPPLIER_NAME, "Udacity");
        values.put(ProductEntry.COLUMN_SUPPLIER_PHONE, 507468654);

        // Insert a new row in the database, returning the ID of that new row.
        // The first argument for db.insert() is the products table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for the new product.
        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);
    }

    // method to read the data from the database
    private void readData(){
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductEntry.COLUMN_SUPPLIER_PHONE};

        // Perform a query on the pets table
        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,      // The table to query
                projection,                  // The columns to return
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,             // Don't group the rows
                null,             // Don't filter by row groups
                null);          // The sort order

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
            int productNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int productPriceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
            int productQuantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(productNameColumnIndex);
                int currentProductPrice = cursor.getInt(productPriceColumnIndex);
                int currentProductQuantity = cursor.getInt(productQuantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                int currentSupplierPhone = cursor.getInt(supplierPhoneColumnIndex);
                // Display the values from each column of the current
                // row in the cursor in the TextView
                Log.d("databaseEntries", "ID = " + currentID);
                Log.d("databaseEntries", "Product Name = " + currentProductName);
                Log.d("databaseEntries", "Product Price = " + currentProductPrice);
                Log.d("databaseEntries", "Product Quantity = " + currentProductQuantity);
                Log.d("databaseEntries", "Supplier Name = " + currentSupplierName);
                Log.d("databaseEntries", "Supplier Phone = " + currentSupplierPhone);
            }
        } finally {
            // Always close the cursor when you're done reading from it.
            // This releases all its resources and makes it invalid.
            cursor.close();
        }
    }
}