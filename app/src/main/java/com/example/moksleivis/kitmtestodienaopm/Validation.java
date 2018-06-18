package com.example.moksleivis.kitmtestodienaopm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final String VALID_CREDENTIALS_REGEX ="^[A-Za-z]+ [A-Za-z]{5,50}$";
    private static final String VALID_YEAR_REGEX = "^[0-9]{4}$";
    private static final String VALID_TELEPHONE_NUMBER_REGEX = "^[0-9]{9}$";


    public static boolean isValidCredentials(String credentials){
        Pattern credentialsPattern = Pattern.compile(VALID_CREDENTIALS_REGEX);
        Matcher credentialsMatcher = credentialsPattern.matcher(credentials);
        return credentialsMatcher.find();
    }

    public static boolean isValidYear(String year){
        Pattern yearPattern = Pattern.compile(VALID_YEAR_REGEX);
        Matcher yearMatcher = yearPattern.matcher(year);
        return yearMatcher.find();
    }

    public static boolean isValidNumber(String telNum){
        Pattern telNumPattern = Pattern.compile(VALID_TELEPHONE_NUMBER_REGEX);
        Matcher telNumMatcher = telNumPattern.matcher(telNum);
        return telNumMatcher.find();
    }
}