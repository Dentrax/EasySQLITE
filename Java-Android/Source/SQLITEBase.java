// ====================================================
// EasySQLITE Copyright(C) 2017 EasySQLITE
// This program comes with ABSOLUTELY NO WARRANTY; This is free software, 
// and you are welcome to redistribute it under certain conditions; See 
// file LICENSE, which is part of this source code package, for details.
// ====================================================

package com.dentrax.easysqlite.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Furkan on 11/09/2013.
 */

public abstract class SQLITEBase {

    protected Context m_context;

    private static final Object m_lockObject = new Object();

    protected SQLITEOpenHelper m_sqLiteOpenHelper;
    private SQLiteDatabase m_db;
    protected SQLITESetting m_sqlSetting;

    private static final ConcurrentHashMap<String, SQLITEOpenHelper> m_dbMap = new ConcurrentHashMap<String, SQLITEOpenHelper>();

    protected String m_db_name;
    protected int m_db_version;

    protected boolean m_isActive;

    public SQLITEBase(Context context, SQLITESetting setting) {
        this.m_db_name = setting.DatabaseName;
        this.m_db_version = setting.DatabaseVersion;
        String dbPath = context.getApplicationContext().getDatabasePath(m_db_name).getAbsolutePath();
        synchronized (m_lockObject) {
            this.m_sqLiteOpenHelper = m_dbMap.get(dbPath);
            if (this.m_sqLiteOpenHelper == null) {
                this.m_sqLiteOpenHelper = new SQLITEOpenHelper(context, m_db_name, m_db_version, this);
                m_dbMap.put(dbPath,this.m_sqLiteOpenHelper);
            }
            //For Instantly onCreate() method, add-> this.m_db = this.m_sqLiteOpenHelper.getWritableDatabase();
        }
        this.m_context = context.getApplicationContext();
        this.m_isActive = true;
    }

    public abstract void onCreate(SQLiteDatabase db);

    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    public void onOpen(SQLiteDatabase db){}

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void onConfigure(SQLiteDatabase db){}


    public synchronized SQLiteDatabase getDb(){
        return this.open();
    }

    public boolean isOpen(){
        return (this.m_db != null && this.m_db.isOpen());
    }

    public List<SQLITECreateInfo> getCreateInfos(){
        return this.m_sqlSetting.CreateInfos;
    }

    public List<SQLITEUpgradeInfo> getUpgradeInfos(){
        return this.m_sqlSetting.UpgradeInfos;
    }

    public List<String> getCreateQuaries(){
        List<SQLITECreateInfo> creates = this.getCreateInfos();
        List<String> totalQueries = new ArrayList<String>();

        for(int i = 0; i < creates.size(); i++){
            List<String> subQueries = creates.get(i).Queries;
            for(int j = 0; j < subQueries.size(); j++){
                String query = subQueries.get(j);
                totalQueries.add(query);
            }
        }

        return totalQueries;
    }

    public List<String> getUpgradeQueries(int oldVersion, int newVersion){
        List<SQLITEUpgradeInfo> upgrades = this.getUpgradeInfos();
        List<String> totalQueries = new ArrayList<String>();

        for(int i = 0; i < upgrades.size(); i++){
            SQLITEUpgradeInfo subUpgrade = upgrades.get(i);
            if(subUpgrade.OldVersion == oldVersion && subUpgrade.NewVersion == newVersion){
                List<String> subQueries = subUpgrade.Queries;
                for(int j = 0; j < subQueries.size(); j++){
                    String query = subQueries.get(j);
                    totalQueries.add(query);
                }
            }
        }

        return totalQueries;
    }

    public synchronized  boolean close(){
        if (this.m_isActive){
            this.m_sqLiteOpenHelper.removeConnection();
        }
        int count = this.m_sqLiteOpenHelper.getCounter();
        if (count==0){
            synchronized (this.m_lockObject){
                if (this.m_db.inTransaction()) this.m_db.endTransaction();
                if (this.m_db.isOpen()) this.m_db.close();
                this.m_db = null;
            }
        }
        this.m_isActive = false;
        return (count==0);
    }

    public synchronized SQLiteDatabase open(){
        if (!this.m_isActive){
            this.m_sqLiteOpenHelper.addConnection();
        }
        if (this.m_db==null || !this.m_db.isOpen()){
            synchronized (this.m_lockObject){
                this.m_db = this.m_sqLiteOpenHelper.getWritableDatabase();
            }
        }
        this.m_isActive = true;
        return this.m_db;
    }
}
