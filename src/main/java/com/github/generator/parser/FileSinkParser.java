package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.sink.FileSink;

/**
 * @author Mostafa Asgari
 * @since 2/26/17
 */
public class FileSinkParser extends AbstractParser {

    public FileSinkParser(Lexer lexer) {
        super(lexer);
    }

    public Expersion parse() throws ParseException {

        ensureNextTokenIs(Token.Type.L_PARENTHESE);

        Expersion firstParam = null;
        int tempPos = lexer.getCurrentPosition();
        int nextCommaPos = lexer.findFirstCommaPosition();
        String newInput = lexer.substring(tempPos,nextCommaPos);
        firstParam = new Parser(new Lexer(newInput)).parse();

        ensureNextTokenIs(Token.Type.COMMA);
        Token filePathToken = ensureNextTokenIs(Token.Type.STRING);

        Token appendToken = new Token("false",Token.Type.STRING);
        if( lexer.isLookaheadIgnoreWhitespace(',') ) {
            ensureNextTokenIs(Token.Type.COMMA);
            appendToken = ensureNextTokenIs(Token.Type.STRING);
        }

        ensureNextTokenIs(Token.Type.R_PARENTHESE);

        return new FileSink(firstParam , filePathToken.getValue() , appendToken.getValueAsBoolean());
    }
}
