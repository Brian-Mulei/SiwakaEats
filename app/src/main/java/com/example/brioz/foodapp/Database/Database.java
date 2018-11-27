package com.example.brioz.foodapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.brioz.foodapp.Model.order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="siwakaeats.db";
    private static final int DB_VER=1;
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<order> getcarts()
    {
        SQLiteDatabase db =getReadableDatabase();
        SQLiteQueryBuilder qb =new SQLiteQueryBuilder();

        String[] sqlselect ={"FoodID","FoodName","Quantity","Price","Discount",};
        String sqlTable="orderdetails";

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlselect,null,null,null,null,null);

        final List<order> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                result.add(new order(cursor.getString(cursor.getColumnIndex("FoodID")),
                        cursor.getString(cursor.getColumnIndex("FoodName")),
                        cursor.getString(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Discount"))
                ));
            }while (cursor.moveToNext());
            }
            return result;
        }

        public void addToCart(order order)
        {
            SQLiteDatabase db= getReadableDatabase();
            String query = String.format("INSERT INTO OrderDetails(FoodID,FoodName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                    order.getFoodID(),
                    order.getFoodName(),
                    order.getQuantity(),
                    order.getPrice(),
                    order.getDiscount());
            db.execSQL(query);

        }


    public void deleteCart()
    {
        SQLiteDatabase db= getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetails");
        db.execSQL(query);

    }


    }

