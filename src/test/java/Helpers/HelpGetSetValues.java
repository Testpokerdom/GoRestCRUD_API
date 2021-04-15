package Helpers;

public class HelpGetSetValues {

    private int convertedToString;
    private static String convertIntToString;

    public static String convertIntToString(int convertedToString){
        convertIntToString = Integer.toString(convertedToString);
        return convertIntToString;
    }
}
