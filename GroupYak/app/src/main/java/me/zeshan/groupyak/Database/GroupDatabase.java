package me.zeshan.groupyak.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupDatabase extends SQLiteOpenHelper {

    public Context con;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GroupsList";
    private static final String TABLE_CONTACTS = "groups";
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "groupID";

    public GroupDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.con = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + "ID INTEGER PRIMARY KEY   AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_ID + " TEXT)";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void deleteMessage(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, "ID = ?", new String[] { ID });
        db.close();
    }

    public void addGroup(String name, String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_ID, ID);

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public HashMap getGroups() {
        HashMap<String, String> map = new HashMap<>();
        try {
            String selectQuery = "SELECT * FROM ?";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, new String[] {TABLE_CONTACTS});

            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                    String ID = cursor.getString(cursor.getColumnIndex(KEY_ID));

                    map.put(name, ID);
                    cursor.moveToNext();
                }
            }

            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            return map;
        }
        return map;
    }

}
