package com.github.generator.parser;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.functions.Today;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class TodayParser extends AbstractParser {

    public TodayParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expression parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        Token token = lexer.nextToken();
        String parameter = null;

        if( token.getType() == Token.Type.SINGLE_QUOTES ){
            parameter = lexer.readSingleQuoteString();
        }
        else if( token.getType() == Token.Type.STRING ){
            parameter = token.getValue();
        }
        else if (token.getType() == Token.Type.R_PARENTHESE){
            return new Today();
        }

        ensureNextTokenIs(Token.Type.R_PARENTHESE);
        return new Today(parameter);
    }
}
