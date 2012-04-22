package ru.goodsReview.api.util;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Artemij Chugreev
 * Date: 04.04.12
 * Time: 11:08
 * email: artemij.chugreev@gmail.com
 * skype: achugr
 */
public class FileUtil {

    public static String readFileAsString(String filePath) throws java.io.IOException, JSONException {
        byte[] buffer = new byte[(int) new File(filePath).length()];
        BufferedInputStream f = null;

        try {
            f = new BufferedInputStream(new FileInputStream(filePath));
            f.read(buffer);
        } finally {
            if (f != null) try { f.close(); } catch (IOException ignored) { }
        }
        return new String(buffer);
    }

}
