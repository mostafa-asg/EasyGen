package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.ranges.CharRange;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author Mostafa Asgari
 * @since 3/7/17
 */
public class MD5 extends Expersion {

    @Override
    public String generate() throws Exception {

        CharRange charRange = new CharRange('a','z');
        charRange.add(new CharRange('A','Z').add(new CharRange('0','9')) );

        String randomStr = new Rep( charRange , 1 , 256 ).generate();

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(randomStr));
        return String.format("%032x", new BigInteger(1, md5.digest()));
    }

}
