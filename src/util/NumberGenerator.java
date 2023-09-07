package util;

import java.math.BigInteger;
import java.util.Random;

public class NumberGenerator {

    public static BigInteger randomBigInt(BigInteger max, BigInteger min) {
        Random random = new Random();
        int bitLength = max.bitLength();
        BigInteger result;

        do {
            result = new BigInteger(bitLength, random);
        } while (result.compareTo(max) >= 0);
        result = result.add(min);
        return result;
    }
}
