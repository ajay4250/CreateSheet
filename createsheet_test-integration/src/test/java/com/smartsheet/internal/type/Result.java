package com.smartsheet.internal.type;

import java.util.List;

public class Result {

    private String accessLevel;
    private List<Column> columns;
    private long id;
    private String name;
    private String permalink;

    public Result() {
    }

    public Result(String accessLevel, List<Column> columns, long id, String name, String permalink) {
        this.accessLevel = accessLevel;
        this.columns = columns;
        this.id = id;
        this.name = name;
        this.permalink = permalink;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
}
