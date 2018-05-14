package com.example.android.bookstore.data;

import android.provider.BaseColumns;

/**
 * Created by DTPAdmin on 14/05/2018.
 * API Contract for the Book Store app
 */

public final class ProductContract {
    /**
     * Inner class that defines constant values for the Products database table.
     * Each entry in the table represents a single product.
     */
    public static final class ProductEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "Products";

        /**
         * Unique ID number for the product (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the product.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PRODUCT_NAME ="Product_name";

        /**
         * Price of the product.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_PRICE = "Price";

        /**
         * Quantity of the product.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_PRODUCT_QUANTITY = "Quantity";

        /**
         * Name of the supplier.
         *
         * Type: TEXT
         */
        public final static String COLUMN_SUPPLIER_NAME ="Supplier_name";

        /**
         * Phone number of the supplier.
         *
         * Type: BIGINT
         */
        public final static String COLUMN_SUPPLIER_PHONE ="Supplier_phone_number";
    }
}
