package com.example.subtest.database;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractBaseDao<T> {

    public abstract long insert(SQLiteDatabase db, T target);

    public abstract void update(SQLiteDatabase db, T target);

    public abstract void delete(SQLiteDatabase db, T target);

    public abstract T load(SQLiteDatabase db, Integer id);

    public abstract List<T> list(SQLiteDatabase db, String orderBy, int offset, int limit);

    public abstract List<T> list(SQLiteDatabase db, String orderBy);
    
    public abstract void createTable(SQLiteDatabase db);

}
