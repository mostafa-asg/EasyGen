package com.github.generator.parser;

import com.github.generator.expersions.functions.Date;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class ParserProvider {

    private Map<String,IParser> parsers;

    public static final String REP = "REP";
    public static final String NEW_LINE = "NEW_LINE";
    public static final String DATE = "DATE";
    public static final String PAD_LEFT = "PAD_LEFT";
    public static final String PAD_RIGHT = "PAD_RIGHT";
    public static final String TAB = "TAB";
    public static final String FILE = "FILE";
    public static final String CONSOLE = "CONSOLE";
    public static final String RANGE = "range";

    public ParserProvider(Lexer lexer){
        parsers = new HashMap<String, IParser>();
        parsers.put(REP, new RepParser(lexer));
        parsers.put(NEW_LINE, new NewLineParser(lexer));
        parsers.put(DATE, new DateParser(lexer));
        parsers.put(RANGE, new RangeParser(lexer));
        parsers.put(FILE , new FileSinkParser(lexer));
        parsers.put(CONSOLE , new ConsoleSinkParser(lexer));
        parsers.put(TAB , new TabParser(lexer) );
    }

    public IParser getParser(String parserName){
        return parsers.get( parserName );
    }

}
