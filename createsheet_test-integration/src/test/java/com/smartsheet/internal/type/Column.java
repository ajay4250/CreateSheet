package com.smartsheet.internal.type;

public class Column {

    private long id;
    private int version;
    private int width;
    private boolean validation;
    private String index;
    private String symbol;
    private String title;
    private String type;
    private boolean primary;

    public Column() {
    }

    public Column(long id, int version, int width, Boolean validation, String index, String symbol, String title, String type, boolean primary) {
        this.id = id;
        this.version = version;
        this.width = width;
        this.validation = validation;
        this.index = index;
        this.symbol = symbol;
        this.title = title;
        this.type = type;
        this.primary = primary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
