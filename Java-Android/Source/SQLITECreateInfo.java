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

public final class SQLITECreateInfo {
    public String TableName;

    public List<String> Queries = new ArrayList<String>();

    public SQLITECreateInfo(String tableName, List<String> queries) {
        this.TableName = tableName;
        this.Queries = queries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SQLITECreateInfo that = (SQLITECreateInfo) o;

        if (TableName != null ? !TableName.equals(that.TableName) : that.TableName != null)
            return false;
        return Queries != null ? Queries.equals(that.Queries) : that.Queries == null;

    }

    @Override
    public int hashCode() {
        int result = TableName != null ? TableName.hashCode() : 0;
        result = 31 * result + (Queries != null ? Queries.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SQLITECreateInfo{" +
                "TableName='" + TableName + '\'' +
                ", Queries=" + Queries +
                '}';
    }
}
