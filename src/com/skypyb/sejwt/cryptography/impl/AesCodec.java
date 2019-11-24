package com.skypyb.sejwt.cryptography.impl;


import com.skypyb.sejwt.cryptography.Codec;
import com.skypyb.sejwt.cryptography.SymmetricCodec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 对称加密,这里填充方式选用 PKCS5Padding
 *
 * @author pyb
 * @time 2019-01-31
 */
public class AesCodec extends SymmetricCodec {

    public AesCodec() {
        super("AES/ECB/PKCS5PADDING");
    }

    @Override
    protected Key getKey(String secret) {
        KeyGenerator kgen = null;
        SecureRandom random = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        random.setSeed(secret.getBytes());
        kgen.init(128, random);
        Key key = kgen.generateKey();
        return key;
    }
}
