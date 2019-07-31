package com.smartsheet.internal.type;

public class Error {
    private int errorCode;
    private String message;
    private String refId;

    public Error() {
    }

    public Error(int errorCode, String message, String refId) {
        this.errorCode = errorCode;
        this.message = message;
        this.refId = refId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
