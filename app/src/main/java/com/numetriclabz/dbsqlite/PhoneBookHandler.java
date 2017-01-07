package com.numetriclabz.dbsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PhoneBookHandler {

    private SQLiteHelper dbHelper;

    public PhoneBookHandler(Context context) {
        dbHelper = new SQLiteHelper(context);
    }


    int addContact(PhoneBook phoneBook) {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_NAME, phoneBook.getName());
        values.put(dbHelper.KEY_CONTACT, phoneBook.getPhoneNumber());
        values.put(dbHelper.KEY_TYPE, phoneBook.getType());

        long insertId = db.insert(dbHelper.TABLE_CONTACTS, null, values);
        db.close();
        return (int)insertId;
    }


    PhoneBook getContact(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(dbHelper.TABLE_CONTACTS, new String[] { dbHelper.KEY_ID,
                        dbHelper.KEY_NAME, dbHelper.KEY_CONTACT, dbHelper.KEY_TYPE }, dbHelper.KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PhoneBook phoneBook = new PhoneBook(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return phoneBook;
    }


    public List<PhoneBook> getAllContacts() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<PhoneBook> phoneBookList = new ArrayList<PhoneBook>();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_CONTACTS;

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setID(Integer.parseInt(cursor.getString(0)));
                phoneBook.setName(cursor.getString(1));
                phoneBook.setPhoneNumber(cursor.getString(2));
                phoneBook.setType(cursor.getString(3));

                phoneBookList.add(phoneBook);
            } while (cursor.moveToNext());
        }

        return phoneBookList;
    }


    public int updateContact(PhoneBook phoneBook) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_NAME, phoneBook.getName());
        values.put(dbHelper.KEY_CONTACT, phoneBook.getPhoneNumber());
        values.put(dbHelper.KEY_TYPE, phoneBook.getType());


        return db.update(dbHelper.TABLE_CONTACTS, values, dbHelper.KEY_ID + " = ?",
                new String[] { String.valueOf(phoneBook.getID()) });
    }


    public void deleteContact(PhoneBook phoneBook) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_CONTACTS, dbHelper.KEY_ID + " = ?",
                new String[] { String.valueOf(phoneBook.getID()) });
        db.close();
    }


    public int getContactsCount() {

        String countQuery = "SELECT  * FROM " + dbHelper.TABLE_CONTACTS;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}