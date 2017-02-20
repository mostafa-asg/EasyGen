package com.github.generator.parser;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */
public class FunctionTables {

    private static String[] functions = new String[] {
        "REP" , "PAD_LEFT" , "PAD_RIGHT"
    };

    public static boolean isAFunction(String functionName){
        for( String function : functions ){
            if( functionName.equals(function) )
                return true;
        }

        return false;
    }

}
