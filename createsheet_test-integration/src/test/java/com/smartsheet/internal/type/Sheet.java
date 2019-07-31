package com.smartsheet.internal.type;

public class Sheet {

    private String message;
    private int resultCode;
    private Result result;

    public Sheet() {
    }

    public Sheet(String message, int resultCode, Result result) {
        this.message = message;
        this.resultCode = resultCode;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
