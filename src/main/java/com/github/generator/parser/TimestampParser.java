package com.github.generator.parser;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.functions.Timestamp;

/**
 * @author Mostafa Asgari
 * @since 5/8/17
 */
public class TimestampParser extends AbstractParser {

    public TimestampParser(Lexer lexer) {
        super(lexer);
    }

    public Expression parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);
        Token token = lexer.nextToken();

        if (token.getType() == Token.Type.R_PARENTHESE) {
            return new Timestamp();
        }

        Token from = ensureCurrentTokenIs(Token.Type.NUMBER);
        token = lexer.nextToken();
        if (token.getType() == Token.Type.R_PARENTHESE) {
            return new Timestamp(from.getValueAsLong());
        }
        else if( token.getType() == Token.Type.COMMA ) {
            Token to = ensureNextTokenIs(Token.Type.NUMBER);
            ensureNextTokenIs(Token.Type.R_PARENTHESE);
            return new Timestamp(from.getValueAsLong() , to.getValueAsLong());
        }
        else {
            throw new ParseException();
        }
    }
}
