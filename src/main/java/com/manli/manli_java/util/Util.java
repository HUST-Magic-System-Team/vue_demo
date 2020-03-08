package com.manli.manli_java.util;

import java.util.regex.Pattern;

public class Util {


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


}

