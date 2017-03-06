package com.github.generator.parser.padding;

import com.github.generator.expersions.Expersion;
import com.github.generator.parser.AbstractParser;
import com.github.generator.parser.Lexer;
import com.github.generator.parser.ParseException;
import com.github.generator.parser.Token;

/**
 * @author Mostafa Asgari
 * @since 3/6/17
 */
public abstract class PadParser extends AbstractParser {

    public PadParser(Lexer lexer) {
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

        return getPad(firstParam , minLengthToken.getValueAsInt() , fillWith);
    }

    public abstract Expersion getPad(Expersion firstParam , int minLength , char fillWith);
}
