package com.github.generator.expersions.functions.uniqueness;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Mostafa Asgari
 * @since 3/7/17
 */
public class SHA512 extends DigestExpersion {

    @Override
    protected String getDigest(String data) {
        return DigestUtils.sha512Hex(data);
    }
}
