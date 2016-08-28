/*
 * 
 */
package com.lasso.util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

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
	 * @param __token the token
	 * @return the string
	 */
	public static String decode(String __token) {
		return EncryptionUtil.decode(__token, EncryptionUtil.DEFAULT_PASSWORD);
	}

	/**
	 * Decode.
	 *
	 * @param __token the token
	 * @param __password the password
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
	 * @param __input the input
	 * @return the string
	 */
	public static String encode(String __input) {
		return EncryptionUtil.encode(__input, EncryptionUtil.DEFAULT_PASSWORD);

	}

	/**
	 * Encode.
	 *
	 * @param __input the input
	 * @param __password the password
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
	 * @param __s the s
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
	 * Instantiates a new encryption utils.
	 */
	private EncryptionUtil() {
	}

	/***
	 * Copy of uniqid in php http://php.net/manual/fr/function.uniqid.php
	 * 
	 * @param prefix
	 * @param more_entropy
	 * @return
	 */
	public String uniqid(String prefix, boolean more_entropy) {
		long time = System.currentTimeMillis();
		String uniqid = "";
		if (!more_entropy) {
			uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
		}
		else {
			SecureRandom sec = new SecureRandom();
			byte[] sbuf = sec.generateSeed(8);
			ByteBuffer bb = ByteBuffer.wrap(sbuf);

			uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
			uniqid += "." + String.format("%.8s", "" + bb.getLong() * -1);
		}

		return uniqid;
	}

}
