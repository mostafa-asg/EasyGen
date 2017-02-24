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

        Expersion firstParam = null;

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.L_BRACKET ){
            firstParam = ParserProvider.getParser( ParserProvider.RANGE ).parse();
        }
        else if (token.getType() == Token.Type.FUNCTION){
            firstParam = ParserProvider.getParser( token.getValue() ).parse();
        }

        ensureNextTokenIs(Token.Type.COMMA);

        Token secondParam = ensureNextTokenIs(Token.Type.NUMBER);

        Token thirdParam = null;
        if( lexer.tryMatch(',') ){
            thirdParam = ensureNextTokenIs(Token.Type.NUMBER);
        }

        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        if(thirdParam==null)
            return new Rep( firstParam , secondParam.getValueAsInt() );
        else
            return new Rep( firstParam , secondParam.getValueAsInt() , thirdParam.getValueAsInt() );

    }

}
