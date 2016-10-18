/*
 * 
 */
package com.lasso.util;

import java.lang.reflect.UndeclaredThrowableException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * The Class EncryptionUtil.
 *
 * @author Paul Mai
 */
public final class EncryptionUtil {

	/** The Constant DEFAULT_PASSWORD. */
	private static final String	DEFAULT_PASSWORD	= "phskyd5NjS";

	/** The Constant DIGITS_POWER. */
	private static final int[]	DIGITS_POWER
	// 0 1 2 3 4 5 6 7 8
	= { 1, 10, 100, 1000, 10000, 100000, 1000000,
			10000000, 100000000 };

	/** The Constant ITERATION_COUNT. */
	private final static int	ITERATION_COUNT		= 31;

	/** The Constant LOGGER. */
	private static final Logger	LOGGER				= Logger.getLogger(EncryptionUtil.class);

	/** The Random Constant SALT. */
	private static final byte[]	SALT				= { (byte) 0x24, (byte) 0x67, (byte) 0xD8,
			(byte) 0xF6, (byte) 0x83, (byte) 0xE4, (byte) 0xBB, (byte) 0x08 };

	/**
	 * Decode.
	 *
	 * @param __token
	 *        the token
	 * @return the string
	 */
	public static String decode(String __token) {
		return EncryptionUtil.decode(__token, EncryptionUtil.DEFAULT_PASSWORD);
	}

	/**
	 * Decode.
	 *
	 * @param __token
	 *        the token
	 * @param __password
	 *        the password
	 * @return the string
	 */
	public static String decode(String __token, String __password) {
		if (__token == null) {
			return null;
		}
		try {

			String _input = __token.replace("%0A", "\n").replace("%25", "%").replace('_', '/')
					.replace('-', '+');

			byte[] _dec = Base64.decodeBase64(_input.getBytes());

			KeySpec _keySpec = new PBEKeySpec(__password.toCharArray(), EncryptionUtil.SALT,
					EncryptionUtil.ITERATION_COUNT);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(EncryptionUtil.SALT,
					EncryptionUtil.ITERATION_COUNT);

			SecretKey _key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(_keySpec);

			Cipher _dcipher = Cipher.getInstance(_key.getAlgorithm());
			_dcipher.init(Cipher.DECRYPT_MODE, _key, paramSpec);

			byte[] _decoded = _dcipher.doFinal(_dec);

			String _result = new String(_decoded);
			return _result;

		}
		catch (Exception _e) {
			EncryptionUtil.LOGGER.error(_e.getMessage(), _e);
		}

		return null;
	}

	/**
	 * Encode.
	 *
	 * @param __input
	 *        the input
	 * @return the string
	 */
	public static String encode(String __input) {
		return EncryptionUtil.encode(__input, EncryptionUtil.DEFAULT_PASSWORD);

	}

	/**
	 * Encode.
	 *
	 * @param __input
	 *        the input
	 * @param __password
	 *        the password
	 * @return the string
	 */
	public static String encode(String __input, String __password) {
		if (__input == null) {
			throw new IllegalArgumentException();
		}
		try {

			KeySpec _keySpec = new PBEKeySpec(__password.toCharArray(), EncryptionUtil.SALT,
					EncryptionUtil.ITERATION_COUNT);
			AlgorithmParameterSpec _paramSpec = new PBEParameterSpec(EncryptionUtil.SALT,
					EncryptionUtil.ITERATION_COUNT);

			SecretKey _key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(_keySpec);

			Cipher _ecipher = Cipher.getInstance(_key.getAlgorithm());
			_ecipher.init(Cipher.ENCRYPT_MODE, _key, _paramSpec);

			byte[] _enc = _ecipher.doFinal(__input.getBytes());

			String _res = new String(Base64.encodeBase64(_enc));
			// escapes for url
			_res = _res.replace('+', '-').replace('/', '_').replace("%", "%25").replace("\n",
					"%0A");

			return _res;

		}
		catch (Exception _e) {
			EncryptionUtil.LOGGER.error(_e.getMessage(), _e);
		}

		return null;

	}

	/**
	 * Encode MD5.
	 *
	 * @param __s
	 *        the s
	 * @return the string
	 */
	public static String encodeMD5(final String __s) {
		try {
			// Create MD5 Hash
			MessageDigest _digest = java.security.MessageDigest.getInstance("MD5");
			_digest.update(__s.getBytes());
			byte[] _messageDigest = _digest.digest();

			// Create Hex String
			StringBuffer _hexString = new StringBuffer();
			for (byte _element : _messageDigest) {
				String _h = Integer.toHexString(0xFF & _element);
				while (_h.length() < 2) {
					_h = "0" + _h;
				}
				_hexString.append(_h);
			}
			return _hexString.toString();

		}
		catch (NoSuchAlgorithmException _e) {
			EncryptionUtil.LOGGER.error(_e.getMessage(), _e);
		}

		return null;
	}

	/**
	 * Generate TOTP.
	 *
	 * @return the int
	 */
	public static int generateTOTP() {
		return EncryptionUtil.generateTOTP("RuVTZYpBUf9bC58Ptrgp".getBytes());
	}

	/**
	 * Generate TOTP.
	 *
	 * @param key
	 *        the key
	 * @return the int
	 */
	public static int generateTOTP(byte[] key) {
		return EncryptionUtil.generateTOTP(key, System.currentTimeMillis() / 1000 / 30);
	}

	/**
	 * Generate TOTP.
	 *
	 * @param key
	 *        the key
	 * @param time
	 *        the time
	 * @return the int
	 */
	public static int generateTOTP(byte[] key, long time) {
		return EncryptionUtil.generateTOTP(key, time, 6);
	}

	/**
	 * Generate TOTP.
	 *
	 * @param key
	 *        the key
	 * @param time
	 *        the time
	 * @param digits
	 *        the digits
	 * @return the int
	 */
	public static int generateTOTP(byte[] key, long time, int digits) {
		return EncryptionUtil.generateTOTP(key, time, digits, "HmacSHA1");
	}

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 *
	 * @param key
	 *        : the shared secret
	 * @param time
	 *        : a value that reflects a time
	 * @param digits
	 *        : number of digits to return
	 * @param crypto
	 *        : the crypto function to use (HmacSHA1, HmacSHA256,
	 *        HmacSHA512)
	 * @return the int
	 * @return: digits
	 */

	public static int generateTOTP(byte[] key, long time, int digits, String crypto) {

		byte[] msg = ByteBuffer.allocate(8).putLong(time).array();
		byte[] hash = EncryptionUtil.hmacSha(crypto, key, msg);

		// put selected bytes into result int
		int offset = hash[hash.length - 1] & 0xf;

		int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16)
				| ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

		int otp = binary % EncryptionUtil.DIGITS_POWER[digits];

		return otp;
	}

	/**
	 * * Copy of uniqid in php http://php.net/manual/fr/function.uniqid.php
	 *
	 * @param __prefix
	 *        the prefix
	 * @param __more_entropy
	 *        the more entropy
	 * @return the string
	 */
	public static String uniqid(String __prefix, boolean __more_entropy) {
		long _time = System.currentTimeMillis();
		String _uniqid = "";
		if (!__more_entropy) {
			_uniqid = String.format("%s%08x%05x", __prefix, _time / 1000, _time);
		}
		else {
			SecureRandom _sec = new SecureRandom();
			byte[] _sbuf = _sec.generateSeed(8);
			ByteBuffer _bb = ByteBuffer.wrap(_sbuf);

			_uniqid = String.format("%s%08x%05x", __prefix, _time / 1000, _time);
			_uniqid += "." + String.format("%.8s", "" + _bb.getLong() * -1);
		}

		return _uniqid;
	}

	/**
	 * This method uses the JCE to provide the crypto algorithm. HMAC computes a
	 * Hashed Message Authentication Code with the crypto hash algorithm as a
	 * parameter.
	 *
	 * @param crypto
	 *        : the crypto algorithm (HmacSHA1, HmacSHA256, HmacSHA512)
	 * @param keyBytes
	 *        : the bytes to use for the HMAC key
	 * @param text
	 *        : the message or text to be authenticated
	 * @return the byte[]
	 */

	private static byte[] hmacSha(String crypto, byte[] keyBytes, byte[] text) {
		try {
			Mac hmac;
			hmac = Mac.getInstance(crypto);
			SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
			hmac.init(macKey);
			return hmac.doFinal(text);
		}
		catch (GeneralSecurityException gse) {
			throw new UndeclaredThrowableException(gse);
		}
	}

	/**
	 * Instantiates a new encryption utils.
	 */
	private EncryptionUtil() {
	}
}
