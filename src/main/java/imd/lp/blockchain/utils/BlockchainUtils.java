package imd.lp.blockchain.utils;

import imd.lp.blockchain.model.TypeCalc;

import java.security.MessageDigest;
import java.util.stream.IntStream;

/**
 * @apiNote utilitarian class.
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 */
public class BlockchainUtils {
    public static String applySHA(String input, TypeCalc calc) {
        try {
            MessageDigest digest = MessageDigest.getInstance(calc.getDesc());

            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            IntStream.range(0, hash.length).mapToObj(i -> Integer.toHexString(0xff & hash[i])).forEach(hex -> {
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            });

            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
