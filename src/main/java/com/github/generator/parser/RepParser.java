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

        if( lexer.nextToken().getType() != Token.Type.L_PARENTHESE )
            throw new ParseException();

        Expersion firstParam = null;

        Token token = lexer.nextToken();
        if( token.getType() == Token.Type.L_BRACKET ){
            firstParam = ParserProvider.getParser( ParserProvider.RANGE ).parse();
        }
        else if (token.getType() == Token.Type.FUNCTION){
            firstParam = ParserProvider.getParser( token.getValue() ).parse();
        }

        if( lexer.nextToken().getType() != Token.Type.COMMA )
            throw new ParseException();

        Token secondParam = lexer.nextToken();
        if( secondParam.getType() != Token.Type.NUMBER ){
            throw new ParseException();
        }

        Token thirdParam = null;
        if( lexer.tryMatchThenConsume(',') ){
            thirdParam = lexer.nextToken();
            if( thirdParam.getType() != Token.Type.NUMBER ){
                throw new ParseException();
            }
        }

        if( lexer.nextToken().getType() != Token.Type.R_PARENTHESE )
            throw new ParseException();

        if(thirdParam==null)
            return new Rep( firstParam , secondParam.getValueAsInt() );
        else
            return new Rep( firstParam , secondParam.getValueAsInt() , thirdParam.getValueAsInt() );

    }

}
