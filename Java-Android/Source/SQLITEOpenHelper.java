// ====================================================
// EasySQLITE Copyright(C) 2017 EasySQLITE
// This program comes with ABSOLUTELY NO WARRANTY; This is free software, 
// and you are welcome to redistribute it under certain conditions; See 
// file LICENSE, which is part of this source code package, for details.
// ====================================================

package com.dentrax.easysqlite.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Furkan on 11/09/2013.
 */

public final class SQLITEOpenHelper extends SQLiteOpenHelper {

    private SQLITEBase m_db;
    private AtomicInteger counter = new AtomicInteger(0);

    public SQLITEOpenHelper(Context context, String db_name, int db_version, SQLITEBase db) {
        super(context, db_name, null, db_version);
        this.m_db = db;
    }

    public void addConnection(){
        counter.incrementAndGet();
    }

    public void removeConnection(){
        counter.decrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(SQLITE.DEBUG_MODE){
            Log.w("[DBSQLiteOpenHelper]", "Database create");
        }
        m_db.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(SQLITE.DEBUG_MODE){
            Log.w("[DBSQLiteOpenHelper]", "Upgrading from version " + oldVersion + " to " +newVersion + ", which will destroy all old data");
        }
        m_db.onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if(SQLITE.DEBUG_MODE){
            Log.w("[DBSQLiteOpenHelper]", "Database open");
        }
        m_db.onOpen(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(SQLITE.DEBUG_MODE){
            Log.w("[DBSQLiteOpenHelper]", "Downgrading from version " + oldVersion + " to " +newVersion + ", which will destroy all old data");
        }
        m_db.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        if(SQLITE.DEBUG_MODE){
            Log.w("[DBSQLiteOpenHelper]", "Database configure");
        }
        m_db.onConfigure(db);
    }
}
