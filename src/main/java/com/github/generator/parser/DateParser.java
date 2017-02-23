package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.Date;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class DateParser extends AbstractParser {

    public DateParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expersion parse() throws ParseException {
        if( lexer.nextToken().getType() != Token.Type.L_PARENTHESE )
            throw new ParseException();

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.STRING ){

            String parameter = token.getValue();

            if( lexer.nextToken().getType() != Token.Type.R_PARENTHESE )
                throw new ParseException();

            return new Date(parameter);
        }
        else if ( token.getType() == Token.Type.R_PARENTHESE ){
            return new Date();
        }

        throw new ParseException();
    }
}
