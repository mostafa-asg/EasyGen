package com.github.generator.parser;

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
            ParserProvider.TODAY,
            ParserProvider.DATE,
            ParserProvider.TAB ,
            ParserProvider.IDENTITY ,
            ParserProvider.MD2 ,
            ParserProvider.MD5 ,
            ParserProvider.SHA1 ,
            ParserProvider.SHA256 ,
            ParserProvider.SHA512 ,
            ParserProvider.SLEEP ,

            ParserProvider.CONSOLE ,
            ParserProvider.FILE ,
            ParserProvider.SOCKET
    };

    public static boolean isAFunction(String functionName){
        for( String function : functions ){
            if( functionName.equals(function) )
                return true;
        }

        return false;
    }

}
