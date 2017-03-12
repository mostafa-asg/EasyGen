package com.github.generator.parser.uniqueness;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.functions.uniqueness.Identity;
import com.github.generator.parser.AbstractParser;
import com.github.generator.parser.Lexer;
import com.github.generator.parser.ParseException;
import com.github.generator.parser.Token;

/**
 * @author Mostafa Asgari
 * @since 3/6/17
 */
public class IdentityParser extends AbstractParser {

    public IdentityParser(Lexer lexer) {
        super(lexer);
    }

    public Expression parse() throws ParseException {
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
