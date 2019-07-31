package com.smartsheet.internal.functional;

import com.smartsheet.internal.type.Error;
import com.smartsheet.internal.type.Sheet;
import com.smartsheet.internal.util.TestBaseSetup;
import com.smartsheet.internal.verify.Verify;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static com.smartsheet.internal.util.Helper.jsonToString;

/**
 * @author ajay
 * Tests for create sheet api
 */

public class TestCreateSheet extends TestBaseSetup {


    @Test(testName = "Create sheet in sheets folder", groups = "CREATE_SHEET_VALID_SCENARIOS")
    public void Test00_CreateSheetInSheetsFolder() {
        String criteriaWS = "/sheets";
        createSheetsWS = callCreateSheetWS(criteriaWS, "createSheetRequest.json");
        Verify.verifyData(createSheetsWS);
        callDeleteSheetWS(criteriaWS + "/" + createSheetsWS.getResult().getId());

    }

    @Test(testName = "Create sheet in folder", groups = "CREATE_SHEET_VALID_SCENARIOS")
    public void Test01_CreateSheetInFolder() {
        String criteriaWS = "/folders/" + folderId + "/sheets";
        createSheetsWS = callCreateSheetWS(criteriaWS, "createSheetRequest.json");
        Verify.verifyData(createSheetsWS);
        callDeleteSheetWS("/sheets/" + createSheetsWS.getResult().getId());

    }

    @Test(testName = "Create sheet in workspace", groups = "CREATE_SHEET_VALID_SCENARIOS")
    public void Test02_CreateSheetInWorkspace() {
        String criteriaWS = "/workspaces/" + workspaceId + "/sheets";
        createSheetsWS = callCreateSheetWS(criteriaWS, "createSheetRequest.json");
        Verify.verifyData(createSheetsWS);
        callDeleteSheetWS("/sheets/" + createSheetsWS.getResult().getId());

    }

    @Test(testName = "Create sheet in sheets folder with no sheet name", groups = "CREATE_SHEET_INVALID_SCENARIOS")
    public void Test03_CreateSheetInSheetsFolderWithNoSheetName() {
        String criteriaWS = "/sheets";
        Error actualErrorMessage = given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, "Bearer u4bz7dh9opdfdl4omfb06f16aa")
                .body(jsonToString("createSheetNoName.json")).log().all()
                .post(baseURL + criteriaWS).then().assertThat().statusCode(400).and().log().all()
                .and().extract().response().as(Error.class);
        Assert.assertEquals(actualErrorMessage.getMessage(),"Required object attribute(s) are missing from your request: sheet.name.");

    }

    @Test(testName = "Create sheet in sheets folder with invalid security token", groups = "CREATE_SHEET_INVALID_SCENARIOS")
    public void Test04_CreateSheetInSheetsFolderWithInvalidSecurityToken() {
        String criteriaWS = "/sheets";
        Error actualErrorMessage = given().contentType("application/json")
                                          .header(SECURITY_TOKEN_NAME, "Bearer xyz")
                                          .body(jsonToString("createSheetRequest.json")).log().all()
                                          .post(baseURL + criteriaWS).then().assertThat().statusCode(401).and().log().all()
                                          .and().extract().response().as(Error.class);
        Assert.assertEquals(actualErrorMessage.getMessage(),"Your Access Token is invalid.");


    }

    @Test(testName = "Create sheet in sheets folder with no security token", groups = "CREATE_SHEET_INVALID_SCENARIOS")
    public void Test05_CreateSheetInSheetsFolderWithNoSecurityToken() {
        String criteriaWS = "/sheets";
        Error actualErrorMessage = given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, "")
                .body(jsonToString("createSheetRequest.json")).log().all()
                .post(baseURL + criteriaWS).then().assertThat().statusCode(403).and().log().all()
                .and().extract().response().as(Error.class);
        Assert.assertEquals(actualErrorMessage.getMessage(),"You are not authorized to perform this action.");


    }

    @Test(testName = "Create sheet in sheets folder with no content type", groups = "CREATE_SHEET_INVALID_SCENARIOS")
    public void Test06_CreateSheetInSheetsFolderWithNoContentType() {
        String criteriaWS = "/sheets";
        Error actualErrorMessage = given()
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .body(jsonToString("createSheetRequest.json")).log().all()
                .post(baseURL + criteriaWS).then().assertThat().statusCode(415).and().log().all()
                .and().extract().response().as(Error.class);
        Assert.assertEquals(actualErrorMessage.getMessage(),"Invalid Content-Type header. Media type not supported.");

    }

    @Test(testName = "Create sheet in sheets folder with invalid content type", groups = "CREATE_SHEET_INVALID_SCENARIOS")
    public void Test07_CreateSheetInSheetsFolderWithInvalidContentType() {
        String criteriaWS = "/sheets";
        Error actualErrorMessage = given().contentType("text/plain")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .body(jsonToString("createSheetRequest.json")).log().all()
                .post(baseURL + criteriaWS).then().assertThat().statusCode(415).and().log().all()
                .and().extract().response().as(Error.class);
        Assert.assertEquals(actualErrorMessage.getMessage(),"Invalid Content-Type header. Media type not supported.");
    }

    @Test(testName = "Create sheet in invalid folder", groups = "CREATE_SHEET_INVALID_SCENARIOS")
    public void Test08_CreateSheetInInvalidFolder() {
        String criteriaWS = "/folders/" + "xyz" + "/sheets";
        Error actualErrorMessage = given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .body(jsonToString("createSheetRequest.json")).log().all()
                .post(baseURL + criteriaWS).then().assertThat().statusCode(404).and().log().all()
                .and().extract().response().as(Error.class);
        Assert.assertEquals(actualErrorMessage.getMessage(),"Not Found");
    }

    @Test(testName = "Create sheet in invalid workspace", groups = "CREATE_SHEET_INVALID_SCENARIOS")
    public void Test09_CreateSheetInInvalidWorkspace() {
        String criteriaWS = "/workspaces/" + "xyz" + "/sheets";
        Error actualErrorMessage = given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .body(jsonToString("createSheetRequest.json")).log().all()
                .post(baseURL + criteriaWS).then().assertThat().statusCode(404).and().log().all()
                .and().extract().response().as(Error.class);
        Assert.assertEquals(actualErrorMessage.getMessage(),"Not Found");
    }

    private Sheet callCreateSheetWS(String queryParam, String resource) {
        return given().contentType("application/json")
                    .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                    .body(jsonToString(resource)).log().all()
                    .post(baseURL + queryParam).then().assertThat().statusCode(200).and().log().all()
                    .and().extract().response().as(Sheet.class);

    }

    private Sheet callDeleteSheetWS(String queryParam) {
        return given().contentType("application/json")
                .header(SECURITY_TOKEN_NAME, SECURITY_TOKEN_VALUE)
                .delete(baseURL + queryParam).then().assertThat().statusCode(200).and().log().all()
                .and().extract().response().as(Sheet.class);

    }

}
