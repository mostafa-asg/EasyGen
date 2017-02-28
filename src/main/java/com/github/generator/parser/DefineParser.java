package com.github.generator.parser;

import com.github.generator.expersions.Expersion;

/**
 * @author Mostafa Asgari
 * @since 2/28/17
 */
public class DefineParser extends AbstractParser {

    public DefineParser(Lexer lexer) {
        super(lexer);
    }

    public Expersion parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        Token replaceToken = lexer.nextToken();

        ensureNextTokenIs(Token.Type.STRING , "AS");

        int currentPos = lexer.getCurrentPosition();
        int endPos = lexer.findFirstRightParenthese();
        String replaceWith = lexer.substring( currentPos , endPos );

        lexer.replcaeInput( endPos+1 , replaceToken.getValue() , replaceWith );
        lexer.seek( endPos+1 );

        return null;
    }
}
