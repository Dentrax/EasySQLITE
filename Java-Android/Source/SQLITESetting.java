package com.dentrax.easysqlite.source;

import java.util.List;

/**
 * Created by Furkan on 11/09/2013.
 */

public final class SQLITESetting {

    public String DatabaseName;

    public int DatabaseVersion;

    public List<SQLITECreateInfo> CreateInfos;

    public List<SQLITEUpgradeInfo> UpgradeInfos;

    public SQLITESetting(String databaseName, int databaseVersion, List<SQLITECreateInfo> createInfos, List<SQLITEUpgradeInfo> upgradeInfos) {
        this.DatabaseName = databaseName;
        this.DatabaseVersion = databaseVersion;
        this.CreateInfos = createInfos;
        this.UpgradeInfos = upgradeInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SQLITESetting that = (SQLITESetting) o;

        if (DatabaseVersion != that.DatabaseVersion) return false;
        if (DatabaseName != null ? !DatabaseName.equals(that.DatabaseName) : that.DatabaseName != null)
            return false;
        if (CreateInfos != null ? !CreateInfos.equals(that.CreateInfos) : that.CreateInfos != null)
            return false;
        return UpgradeInfos != null ? UpgradeInfos.equals(that.UpgradeInfos) : that.UpgradeInfos == null;

    }

    @Override
    public int hashCode() {
        int result = DatabaseName != null ? DatabaseName.hashCode() : 0;
        result = 31 * result + DatabaseVersion;
        result = 31 * result + (CreateInfos != null ? CreateInfos.hashCode() : 0);
        result = 31 * result + (UpgradeInfos != null ? UpgradeInfos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SQLITESetting{" +
                "DatabaseName='" + DatabaseName + '\'' +
                ", DatabaseVersion=" + DatabaseVersion +
                ", CreateInfo=" + CreateInfos +
                ", UpgradeInfo=" + UpgradeInfos +
                '}';
    }
}
