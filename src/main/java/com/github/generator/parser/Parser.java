package com.github.generator.parser;

import com.github.generator.expersions.SequenceExpersion;
import com.github.generator.expersions.terminals.StringTerminal;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */
public class Parser extends AbstractParser {

    public Parser(Lexer lexer){
        super(lexer);
        ParserProvider.init(lexer);
    }

    public SequenceExpersion parse() throws ParseException {

        SequenceExpersion result = new SequenceExpersion();

        while (true){

            Token token = lexer.nextToken();
            if( token.getType()==Token.Type.EOF )
                break;

            if( token.getType() == Token.Type.FUNCTION ){
                result.addExpersion( ParserProvider.getParser(token.getValue()).parse() );
            }
            else if( token.getType() == Token.Type.SINGLE_QUOTES ){
                result.addExpersion( new StringTerminal(lexer.readSingleQuoteString()) );
            }
            else if( token.getType() == Token.Type.STRING || token.getType() == Token.Type.CHAR )
                result.addExpersion( new StringTerminal(token.getValue()));
            else if( token.getType() == Token.Type.SINGLE_QUOTES ){
                result.addExpersion(new StringTerminal(lexer.consumeUntil('\'')));
                lexer.match('\'');
            }
            else if(token.getType()==Token.Type.L_BRACKET){
                result.addExpersion( ParserProvider.getParser(ParserProvider.RANGE).parse() );
            }
        }

        return result;
    }

}
