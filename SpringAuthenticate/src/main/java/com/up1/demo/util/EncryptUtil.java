package com.up1.demo.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {

	/**
	 * Sign data with HMAC-SHA1
	 * @param privateKey
	 * @param inputToSign
	 * @return
	 */
	public static String buildHmacSignature(String privateKey, String inputToSign) {
		String lSignature = "None";
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(privateKey.getBytes(), "HmacSHA1");
			mac.init(secret);

			byte[] lDigest = mac.doFinal(inputToSign.getBytes());
			BigInteger lHash = new BigInteger(1, lDigest);
			lSignature = lHash.toString(16);
			if ((lSignature.length() % 2) != 0) {
				lSignature = "0" + lSignature;
			}
		} catch (NoSuchAlgorithmException lEx) {
			throw new RuntimeException("Problems calculating HMAC", lEx);
		} catch (InvalidKeyException lEx) {
			throw new RuntimeException("Problems calculating HMAC", lEx);
		}

		return lSignature;
	}
}
