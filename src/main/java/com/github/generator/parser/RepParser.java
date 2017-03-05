package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.Rep;

/**
 * Created by Mostafa on 02/23/2017.
 */
public class RepParser extends AbstractParser {

    public RepParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expersion parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        Expersion firstParam = parseUntilNextComma();

        ensureNextTokenIs(Token.Type.COMMA);
        Token secondParam = ensureNextTokenIs(Token.Type.NUMBER);

        Token thirdParam = null;
        if( lexer.isLookaheadIgnoreWhitespace(',') ){
            ensureNextTokenIs(Token.Type.COMMA);
            thirdParam = ensureNextTokenIs(Token.Type.NUMBER);
        }

        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        if(thirdParam==null)
            return new Rep( firstParam , secondParam.getValueAsInt() );
        else
            return new Rep( firstParam , secondParam.getValueAsInt() , thirdParam.getValueAsInt() );

    }

}
