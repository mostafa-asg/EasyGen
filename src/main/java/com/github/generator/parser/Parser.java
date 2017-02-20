package com.github.generator.parser;

import com.github.generator.expersions.SequenceExpersion;
import com.github.generator.expersions.functions.ranges.LongRange;
import com.github.generator.expersions.terminals.StringTerminal;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */
public class Parser {

    private Lexer lexer;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public SequenceExpersion parse() throws ParseException {

        SequenceExpersion result = new SequenceExpersion();

        while (true){

            Token token = lexer.nextToken();
            if( token.getType()==Token.Type.EOF )
                break;

            if( token.getType() == Token.Type.STRING)
                result.addExpersion( new StringTerminal(token.getValue()));
            else if( token.getType() == Token.Type.SINGLE_QUOTES ){
                result.addExpersion(new StringTerminal(lexer.consumeUntil('\'')));
                lexer.match('\'');
            }
            else if(token.getType()==Token.Type.L_BRACKET){

                Token firstParam = lexer.nextToken();
                if( firstParam.getType() == Token.Type.NUMBER ){
                    if (lexer.nextToken().getType() != Token.Type.DOUBLE_DOT)
                        throw new ParseException();
                    Token secondParam = lexer.nextToken();
                    if( secondParam.getType() != Token.Type.NUMBER )
                        throw new ParseException();
                    if (lexer.nextToken().getType() != Token.Type.R_BRACKET)
                        throw new ParseException();
                    result.addExpersion( new LongRange(firstParam.getValueAsLong(),secondParam.getValueAsLong()) );
                }

            }
        }

        return result;
    }

}
