// ====================================================
// EasySQLITE Copyright(C) 2017 EasySQLITE
// This program comes with ABSOLUTELY NO WARRANTY; This is free software, 
// and you are welcome to redistribute it under certain conditions; See 
// file LICENSE, which is part of this source code package, for details.
// ====================================================

package com.dentrax.easysqlite.source;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Furkan on 11/09/2013.
 */

public final class SQLITEUpgradeInfo {

    public int OldVersion;

    public int NewVersion;

    public List<String> Queries = new ArrayList<String>();

    public SQLITEUpgradeInfo(int oldVersion, int newVersion, List<String> queries) {
        this.OldVersion = oldVersion;
        this.NewVersion = newVersion;
        this.Queries = queries;
    }

    public boolean isUpgrade(){
        return this.OldVersion < this.NewVersion;
    }

    public boolean isDowngrade(){
        return this.OldVersion > this.NewVersion;
    }

    public List<String> getQueries(){
        return this.Queries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SQLITEUpgradeInfo that = (SQLITEUpgradeInfo) o;

        if (OldVersion != that.OldVersion) return false;
        if (NewVersion != that.NewVersion) return false;
        return Queries != null ? Queries.equals(that.Queries) : that.Queries == null;

    }

    @Override
    public int hashCode() {
        int result = OldVersion;
        result = 31 * result + NewVersion;
        result = 31 * result + (Queries != null ? Queries.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SQLITEUpgradeInfo{" +
                "OldVersion=" + OldVersion +
                ", NewVersion=" + NewVersion +
                ", UpgradeQueries=" + Queries +
                '}';
    }
}
