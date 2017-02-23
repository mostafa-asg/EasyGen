package com.github.generator.parser;

import com.github.generator.expersions.functions.Date;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class ParserProvider {

    private static Map<String,IParser> parsers;

    public final static String REP = "REP";
    public final static String NEW_LINE = "NEW_LINE";
    public final static String DATE = "DATE";
    public final static String PAD_LEFT = "PAD_LEFT";
    public final static String PAD_RIGHT = "PAD_RIGHT";
    public final static String RANGE = "range";

    public static void init(Lexer lexer){
        parsers = new HashMap<String, IParser>();
        parsers.put(REP, new RepParser(lexer));
        parsers.put(NEW_LINE, new NewLineParser(lexer));
        parsers.put(DATE, new DateParser(lexer));
        parsers.put(RANGE, new RangeParser(lexer));
    }

    public static IParser getParser(String parserName){
        return parsers.get( parserName );
    }

}
