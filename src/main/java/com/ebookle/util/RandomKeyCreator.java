package com.ebookle.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.08.13
 * Time: 6:09
 * To change this template use File | Settings | File Templates.
 */
public final class RandomKeyCreator {

    private final static Random random = new Random();

    public static String createRandomKey () {
        return createUserKey(random.nextInt(20) + 20);
    }

    private static String createUserKey (int passwordLength) {
        String valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        int validLength = valid.length();
        while (0 < passwordLength--) {
            result.append(
                    valid.charAt(
                            random.nextInt(validLength)));
        }
        return result.toString();
    }
}
