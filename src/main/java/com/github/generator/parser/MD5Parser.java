package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.MD5;

/**
 * @author Mostafa Asgari
 * @since 3/7/17
 */
public class MD5Parser extends AbstractParser {

    public MD5Parser(Lexer lexer) {
        super(lexer);
    }

    public Expersion parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);
        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        return new MD5();
    }
}
