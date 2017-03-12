package com.github.generator.parser;

import com.github.generator.expersions.SequenceExpression;
import com.github.generator.expersions.terminals.StringTerminal;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */
public class Parser extends AbstractParser {

    private ParserProvider parserProvider;

    public Parser(Lexer lexer){
        super(lexer);
        parserProvider = new ParserProvider(lexer);
    }

    public SequenceExpression parse() throws ParseException {

        SequenceExpression result = new SequenceExpression();

        while (true){

            Token token = lexer.nextToken();
            if( token.getType()==Token.Type.EOF )
                break;

            if( token.getType() == Token.Type.FUNCTION ){

                if( token.getValue().equals( ParserProvider.DEFINE ) ){
                    parserProvider.getParser(token.getValue()).parse();
                }
                else {
                    result.addExpersion(parserProvider.getParser(token.getValue()).parse());
                }
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
                result.addExpersion( parserProvider.getParser(ParserProvider.RANGE).parse() );
            }
        }

        return result;
    }

}
