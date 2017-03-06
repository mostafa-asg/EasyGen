package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.Identity;

/**
 * @author Mostafa Asgari
 * @since 3/6/17
 */
public class IdentityParser extends AbstractParser {

    public IdentityParser(Lexer lexer) {
        super(lexer);
    }

    public Expersion parse() throws ParseException {
        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.R_PARENTHESE ){
            return Identity.getInstance();
        }
        else if ( token.getType() == Token.Type.NUMBER ){

            long seed = token.getValueAsLong();
            ensureNextTokenIs(Token.Type.COMMA);
            long increment = ensureNextTokenIs(Token.Type.NUMBER).getValueAsLong();
            ensureNextTokenIs(Token.Type.R_PARENTHESE);

            return Identity.getInstance(seed,increment);
        }

        throw new ParseException();
    }
}
