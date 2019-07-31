package com.smartsheet.internal.verify;

import com.smartsheet.internal.type.Sheet;
import org.testng.Assert;

/**
 * @author ajay
 * Verify the response from the web service
 */

public class Verify {

    public static void verifyData(Sheet sheet){

        Assert.assertEquals(sheet.getMessage(),"SUCCESS");
        Assert.assertEquals(sheet.getResult().getName(),"AjayTestSheet");
        Assert.assertEquals(sheet.getResult().getAccessLevel(),"OWNER");

    }
}
