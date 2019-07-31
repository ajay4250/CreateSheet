package com.smartsheet.internal.util;

import com.jayway.restassured.RestAssured;
import com.smartsheet.internal.type.Sheet;
import com.smartsheet.internal.verify.Verify;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import static com.jayway.restassured.RestAssured.given;
import static com.smartsheet.internal.util.Helper.jsonToString;

/**
 * @author ajay
 * Setup and teardown base class for the tests
 */

public class TestBaseSetup {
    protected static com.smartsheet.internal.type.Sheet createSheetsWS = null;
    protected static String baseURL = "https://api.smartsheet.com/2.0";
    protected static String SECURITY_TOKEN_NAME = "Authorization";
    protected static String SECURITY_TOKEN_VALUE = "Bearer" + " u4bz7dh9opdfdl4omfb06f16aa";
    public static Long folderId = null;
    public static Long workspaceId = null;

    //setup test data
    @BeforeTest(alwaysRun = true)
    public void setupBeforeTest() {
        RestAssured.urlEncodingEnabled = false;
        folderId = callCreateFolderWS().getResult().getId();
        workspaceId = callCreateWorkspaceWS().getResult().getId();
    }

    //delete test data
    @AfterTest
    public void tearDownAfterTest() {
        callDeleteFolderWS();
        callDeleteWorkspaceWS();
    }

    //Create folder
    private Sheet callCreateFolderWS() {
        return given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .body(jsonToString("createFolder.json")).log().all()
                .post(baseURL + "/home/folders").then().assertThat().statusCode(200).and().log().all()
                .and().extract().response().as(Sheet.class);
    }

    //create workspace
    private Sheet callCreateWorkspaceWS() {
        return given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .body(jsonToString("createWorkspace.json")).log().all()
                .post(baseURL + "/workspaces").then().assertThat().statusCode(200).and().log().all()
                .and().extract().response().as(Sheet.class);
    }

    //delete folder
    private Sheet callDeleteFolderWS() {
        return given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .delete(baseURL + "/folders/" + folderId).then().assertThat().statusCode(200).and().log().all()
                .and().extract().response().as(Sheet.class);
    }

    //delete workspace
    private Sheet callDeleteWorkspaceWS() {
        return given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .delete(baseURL + "/workspaces/" + workspaceId).then().assertThat().statusCode(200).and().log().all()
                .and().extract().response().as(Sheet.class);
    }
}
