package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.Newline;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class NewLineParser extends AbstractParser {

    public NewLineParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expersion parse() throws ParseException {

        if( lexer.nextToken().getType() != Token.Type.L_PARENTHESE )
            throw new ParseException();

        if( lexer.nextToken().getType() != Token.Type.R_PARENTHESE )
            throw new ParseException();

        return new Newline();

    }

}
