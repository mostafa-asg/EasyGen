package com.github.generator.parser.uniqueness;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.uniqueness.MD5;
import com.github.generator.parser.AbstractParser;
import com.github.generator.parser.Lexer;
import com.github.generator.parser.ParseException;
import com.github.generator.parser.Token;

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
