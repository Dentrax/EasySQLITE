<h1 align="center">EasySQLite Source Repository</h1>

### EasySQLITE: An EASY and ADVANCED way to handle SQLITE!

[What It Is](#what-it-is)

[How To User](#how-to-use)

[About](#about)  

[Copyright & Licensing](#copyright--licensing)  

[Contributing](#contributing)  

[Contact](#contact) 

## What It Is

EasySQLITE for SQLite is a high quality, simple, open source tool to
create, design, and edit database files compatible with SQLite.

Controls and wizards are available for users to:

* Create and compact SQLITE controller singleton classes
* Create, define, modify, update and delete tables
* Easy way to create and control SQLITE instance insted of class inheritance for every SQLITE DB.

## How To Use

**Single line initializer : SQLITE sql = new SQLITE(CONTEXT, new SQLITESetting(DB_NAME, VERSION, SQLITECreateInfo, SQLITEUpgradeInfo));**


```c#
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
```

## About

EasySQLITE was created to serve three purposes:

1. To act as a library to handle SQLITE.

2. To provide a basic SQLITE Library for any project that requires a SQL database with uses SQLITE. 

3. Instead of writing long and complex code, it provides the simplest and easiest way.

**However, our true ultimate goal is to produce easy and life-saver libraries in any language!**

EasySQLITE was created by Furkan 'Dentrax' Türkal

 * <https://www.furkanturkal.com>
 
 ## Copyright & Licensing

The base project code is copyrighted by Furkan 'Dentrax' Türkal and
is covered by single licence.

All program code (i.e. C#, Java) is licensed under GPL v3.0 unless otherwise
specified.  Please see the "LICENSE" file for more information.

## Contributing

Please check the [CONTRIBUTING.md](CONTRIBUTING.md) file for contribution instructions and guidelines.

## Contact

You can contact Dentrax by email:
    furkan.turkal@hotmail.com
