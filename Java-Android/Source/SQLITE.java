// ====================================================
// EasySQLITE Copyright(C) 2017 EasySQLITE
// This program comes with ABSOLUTELY NO WARRANTY; This is free software, 
// and you are welcome to redistribute it under certain conditions; See 
// file LICENSE, which is part of this source code package, for details.
// ====================================================

package com.dentrax.easysqlite.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Furkan on 11/09/2013.
 */

public final class SQLITE extends SQLITEBase {

    public static final Boolean DEBUG_MODE = false;
    public static final Boolean CAN_RESETTABLE = false;

    public SQLITE(Context context, SQLITESetting setting) {
        super(context, setting);
    }

    public synchronized void onCreate(SQLiteDatabase db) {
        try {
            List<String> createQueries =  super.getCreateQuaries();
            if(!createQueries.isEmpty()){
                this.execQueryList(db, createQueries);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        List<String> upgradeQueries =  super.getUpgradeQueries(oldVersion, newVersion);

        if(!upgradeQueries.isEmpty()){
            this.execQueryList(db, upgradeQueries);
        }
    }

    protected void execQueryList(SQLiteDatabase db, List<String> queries){
        for (int i = 0; i < queries.size(); i++) {
            String query = queries.get(i).trim();
            if(!query.isEmpty()){
                db.execSQL(query);
            }
        }
    }

    public synchronized List<String> listTables(){
        List<String> tables = new ArrayList<String>();
        SQLiteDatabase db = this.getDb();
        Cursor c = null;
        try {
            if(this.isOpen()){
                c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
                if (c.moveToFirst()){
                    while ( !c.isAfterLast() ){
                        tables.add( c.getString( c.getColumnIndex("name")) );
                        c.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null){
                c.close();
            }
            if(db != null){
                db.close();
            }
        }
        return tables;
    }

    public synchronized Cursor queryCursor(String query){
        Cursor c = null;
        SQLiteDatabase db = this.m_sqLiteOpenHelper.getReadableDatabase();
        try {
            if(db.isOpen()){
                c = db.rawQuery(query, null);
                c.moveToFirst();
            }
        } catch (Exception e) {
            Log.v(query, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (c != null){
                c.close();
            }
            if(db != null){
                db.close();
            }
        }
        return c;
    }

    public synchronized Cursor query(String tableName, String[] columns){
        return this.query(tableName, columns, null, null, null, null, null, null);
    }

    public synchronized Cursor query(String tableName, String[] columns, String selection){
        return this.query(tableName, columns, selection, null, null, null, null, null);
    }

    public synchronized Cursor query(String tableName, String[] columns, String selection, String[] selectArgs){
        return this.query(tableName, columns, selection, selectArgs, null, null, null, null);
    }

    public synchronized Cursor query(String tableName, String[] columns, String selection, String[] selectArgs, String groupBy){
        return this.query(tableName, columns, selection, selectArgs, groupBy, null, null, null);
    }

    public synchronized Cursor query(String tableName, String[] columns, String selection, String[] selectArgs, String groupBy, String having){
        return this.query(tableName, columns, selection, selectArgs, groupBy, having, null, null);
    }

    public synchronized Cursor query(String tableName, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy){
        return this.query(tableName, columns, selection, selectArgs, groupBy, having, orderBy, null);
    }

    public synchronized Cursor query(String tableName, String[] columns, String selection, String[] selectArgs, String groupBy, String having, String orderBy, String limit ){
        SQLiteDatabase db = this.getDb();
        Cursor c = null;
        try {
            if(this.isOpen()){
                c = db.query(tableName, columns, selection, selectArgs, groupBy, having, orderBy, limit);
                return c;
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        }
        return c;
    }

    public synchronized void queryDelete(String tableName, String where){
        this.queryDelete(tableName, where, null);
    }

    public synchronized void queryDelete(String tableName, String where, String[] whereArgs){
        SQLiteDatabase db = this.getDb();
        try {
            if(this.isOpen()){
                db.delete(tableName, where, whereArgs);
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if(db != null){
                db.close();
            }
        }
    }

    public synchronized void queryInsert(String tableName, ContentValues values){
        SQLiteDatabase db = this.getDb();
        try {
            if(this.isOpen()){
                db.insert(tableName, null, values);
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if(db != null){
                db.close();
            }
        }
    }

    public synchronized void queryUpdate(String tableName, ContentValues values, String where){
        this.queryUpdate(tableName, values, null);
    }

    public synchronized void queryUpdate(String tableName, ContentValues values, String where, String[] whereArgs){
        SQLiteDatabase db = this.getDb();
        try {
            if(this.isOpen()){
                db.update(tableName, values, where, whereArgs);
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if(db != null){
                db.close();
            }
        }
    }

    public synchronized int getRowCount(String tableName) {
        String countQuery = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getDb();
        Cursor c = null;
        try {
            if(db.isOpen()){
                c = db.rawQuery(countQuery, null);
                int rowCount = c.getCount();
                return rowCount;
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (c != null){
                c.close();
            }
            if(db != null){
                db.close();
            }
        }
        return -1;
    }

    public synchronized List<String> getColumns(String tableName) {
        List<String> ar = null;
        Cursor c = null;
        SQLiteDatabase db = this.getDb();
        try {
            if(db.isOpen()){
                c = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 1", null);
                if (c != null) {
                    ar = new ArrayList<String>(Arrays.asList(c.getColumnNames()));
                }
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (c != null){
                c.close();
            }
            if(db != null){
                db.close();
            }
        }
        return ar;
    }

    public synchronized void resetTable(String tableName){
        SQLiteDatabase db = this.getDb();
        try {
            if(db.isOpen()){
                if(!SQLITE.CAN_RESETTABLE){
                  Log.e("[SQLITE::resetTables()]", "You don't have permission to reset the database!");
                  db.close();
                  return;
                }
                db.delete(tableName, null, null);
            }
        } catch (Exception e) {
            Log.v(tableName, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if(db != null){
                db.close();
            }
        }
    }
}
