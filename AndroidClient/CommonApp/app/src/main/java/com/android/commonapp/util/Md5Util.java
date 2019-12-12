package com.android.commonapp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Md5Util {
    private static final String TAG = Md5Util.class.getSimpleName();

    public static String byteToHexString(byte b) {
        if (b < 0)
            return STR_BYTE[255 + b + 1];
        else
            return STR_BYTE[b];
    }

    private static String[] STR_BYTE = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35", "36",
            "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55",
            "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74",
            "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93",
            "94", "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2",
            "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1",
            "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0",
            "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF"};

    private static String STR_LINE_SEPARATOR = System.getProperty("line.separator");

    private static String byteArrayToHexString(byte[] bs, int numInOneLine, boolean splitBySpace) {
        if (bs == null)
            return null;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bs.length; i++) {
            sb.append(byteToHexString(bs[i]));
            if ((numInOneLine != 0) && (((i + 1) % numInOneLine) == 0)) {
                sb.append(STR_LINE_SEPARATOR);
            } else if (i < bs.length - 1) {
                if (splitBySpace)
                    sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] md5 = md.digest();
            return byteArrayToHexString(md5, 100, false).toLowerCase(Locale.US);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getMD5Bytes(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFileMD5(String filepath) {
        FileInputStream fin = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fin = new FileInputStream(filepath);
            byte[] buffer = new byte[1024];
            int len = -1;
            while (-1 != (len = fin.read(buffer))) {
                md.update(buffer, 0, len);
            }
            byte[] md5 = md.digest();
            String md5String = byteArrayToHexString(md5, 100, false).toLowerCase(Locale.US);
            // return byteArrayToHexString(md5, 100,
            // false).toLowerCase(Locale.US);
//			Print.e(TAG, "md5String = " + md5String);
            return md5String;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fin) {
                try {
                    fin.close();
                } catch (IOException e) {
                }
                fin = null;
            }
        }
        return null;
    }
}
