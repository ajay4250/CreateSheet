package com.smartsheet.internal.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;

/**
 * @author ajay
 * Purpose of this class is to have all the helper methods in one place
 * Helper methods - to convert json to string
 */

public class Helper {

    public static String jsonToString(String resource) {
        String jsonBody = null;
        try {
            //Getting the json file path
            URL url = MethodHandles.lookup().lookupClass().getClassLoader().getResource(resource);
            File file = new File(url.getPath());

            //Converting json to string
            BufferedReader in = new BufferedReader(new FileReader(file));
            StringBuffer output = new StringBuffer();
            String st;
            while ((st = in.readLine()) != null) {
                output.append(st);
            }
            in.close();
            jsonBody = String.valueOf(output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonBody;
    }
}
