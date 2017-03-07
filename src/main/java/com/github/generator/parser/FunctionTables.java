package com.github.generator.parser;

import com.github.generator.expersions.Expersion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */
public class FunctionTables {

    private static String[] functions = new String[] {

            ParserProvider.DEFINE ,

            ParserProvider.REP ,
            ParserProvider.PAD_LEFT ,
            ParserProvider.PAD_RIGHT ,
            ParserProvider.NEW_LINE ,
            ParserProvider.DATE ,
            ParserProvider.TAB ,
            ParserProvider.IDENTITY ,
            ParserProvider.MD2 ,
            ParserProvider.MD5 ,
            ParserProvider.SHA1 ,
            ParserProvider.SHA256 ,
            ParserProvider.SHA512 ,

            ParserProvider.CONSOLE ,
            ParserProvider.FILE
    };

    public static boolean isAFunction(String functionName){
        for( String function : functions ){
            if( functionName.equals(function) )
                return true;
        }

        return false;
    }

}
