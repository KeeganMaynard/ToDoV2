package com.keegan.android.todov2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasksDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASKS = "tasks";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String DUE_DATE = "due_date";

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String sqlCreate = "create table " + TABLE_TASKS + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + TASK;
        sqlCreate += " text, " + DUE_DATE + " text)";

        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_TASKS);
        onCreate(db);
    }

    public void insert(ToDo toDo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TASKS;
        sqlInsert += " values (null, '" + toDo.getTask();
        sqlInsert += "', '" + toDo.getDueDate() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_TASKS;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public ArrayList<ToDo> selectAll()
    {
        String sqlQuery = "select * from " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<ToDo> tasks = new ArrayList<>();
        while(cursor.moveToNext())
        {
            ToDo toDo = new ToDo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
            tasks.add(toDo);
        }
        db.close();
        return tasks;
    }
}
