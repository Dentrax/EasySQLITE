package com.dentrax.easysqlite.examples;

import com.dentrax.easysqlite.source.SQLITE;
import com.dentrax.easysqlite.source.SQLITECreateInfo;
import com.dentrax.easysqlite.source.SQLITESetting;
import com.dentrax.easysqlite.source.SQLITEUpgradeInfo;

import java.util.ArrayList;
import java.util.List;

import static com.dentrax.easysqlite.MainActivity.CONTEXT;

/**
 * Created by Furkan on 11/09/2013.
 */

public final class Example1 {

    public Example1(){

        final List<SQLITECreateInfo> sql_createList = new ArrayList<SQLITECreateInfo>(){{
            //Create a new TABLE with these queries
            add(new SQLITECreateInfo("TABLE1_NAME", new ArrayList<String>(){{
                add("QUERY1");
                add("QUERY2");
            }}));

            //Create a new TABLE with these queries
            add(new SQLITECreateInfo("TABLE2_NAME", new ArrayList<String>(){{
                add("QUERY1");
                add("QUERY2");
            }}));
        }};

        final List<SQLITEUpgradeInfo> sql_upgradeList = new ArrayList<SQLITEUpgradeInfo>(){{
            //Run this queries when upgrade version 0 to 1
            add(new SQLITEUpgradeInfo(0, 1, new ArrayList<String>(){{
                add("QUERY1");
                add("QUERY2");
            }}));

            //Run this queries when upgrade version 1 to 2
            add(new SQLITEUpgradeInfo(1, 2, new ArrayList<String>(){{
                add("QUERY1");
                add("QUERY2");
            }}));
        }};

        //SQLITESetting class. Parameters : (DB_NAME, VERSION, List<SQLITECreateInfo>, List<SQLITEUpgradeInfo>)
        final SQLITESetting sql_setting = new SQLITESetting("DB_NAME", 1, sql_createList, sql_upgradeList);

        //Main initializer
        SQLITE sql = new SQLITE(CONTEXT, sql_setting);
    }
}
