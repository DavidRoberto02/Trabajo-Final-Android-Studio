package com.example.evidencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLite extends SQLiteOpenHelper {
    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table login(Username int primary key, Email text, password text )");
        db.execSQL("create table registro1(FECHA datetime primary key, day0 int, day1 int, day2 int, day3 int, day4 int, day5 int, RESULTADO text)");
        db.execSQL("create table registro2(FECHA1 datetime primary key, dayL int, dayM int, dayJ int, dayV int, dayS int, dayD int, RESULTADO1 text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
