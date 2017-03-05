package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.PadLeft;

/**
 * @author Mostafa Asgari
 * @since 3/5/17
 */
public class PadLeftParser extends AbstractParser {

    public PadLeftParser(Lexer lexer) {
        super(lexer);
    }

    public Expersion parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        Expersion firstParam = parseUntilNextComma() ;

        ensureNextTokenIs( Token.Type.COMMA );

        Token minLengthToken = ensureNextTokenIs(Token.Type.NUMBER);

        ensureNextTokenIs( Token.Type.COMMA );

        char fillWith;
        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.CHAR )
            fillWith = token.getValueAsChar();
        else if( token.getType() == Token.Type.SINGLE_QUOTES ){
            String strChar = lexer.readSingleQuoteString();
            if( strChar.length() != 1 )
                throw new ParseException();
            else
                fillWith = strChar.charAt(0);
        }
        else{
            throw new ParseException();
        }

        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        return new PadLeft( firstParam , minLengthToken.getValueAsInt() , fillWith );
    }
}
