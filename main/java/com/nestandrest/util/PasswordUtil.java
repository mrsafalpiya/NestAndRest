package com.nestandrest.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for handling password encryption and decryption. Uses
 * AES/GCM/NoPadding encryption algorithm with PBKDF2WithHmacSHA256 for key
 * derivation.
 */
public class PasswordUtil {
	// Encryption algorithm specification
	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";

	// Security constants
	private static final int TAG_LENGTH_BIT = 128; // must be one of {128, 120, 112, 104, 96}
	private static final int IV_LENGTH_BYTE = 12;
	private static final int SALT_LENGTH_BYTE = 16;
	private static final Charset UTF_8 = StandardCharsets.UTF_8;

	/**
	 * Generates a random nonce (number used once) of specified length.
	 * 
	 * @param numBytes the length of the nonce in bytes
	 * @return a random byte array of specified length
	 */
	public static byte[] getRandomNonce(int numBytes) {
		byte[] nonce = new byte[numBytes];
		new SecureRandom().nextBytes(nonce);
		return nonce;
	}

	/**
	 * Generates an AES secret key of specified size.
	 * 
	 * @param keysize the key size in bits (typically 128, 192, or 256)
	 * @return a generated AES SecretKey
	 * @throws NoSuchAlgorithmException if AES algorithm is not available
	 */
	public static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keysize, SecureRandom.getInstanceStrong());
		return keyGen.generateKey();
	}

	/**
	 * Derives an AES 256-bit secret key from a password and salt using PBKDF2.
	 * 
	 * @param password the password characters
	 * @param salt     the salt bytes
	 * @return a derived AES secret key, or null if key generation fails
	 */
	public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			// iterationCount = 65536
			// keyLength = 256
			KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
			SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return secret;
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidKeySpecException ex) {
			Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * Encrypts a password using AES-GCM encryption with the employee ID as the
	 * basis for the key. The result is Base64 encoded and includes the IV and salt.
	 * 
	 * @param employee_id the employee ID to use for key generation
	 * @param password    the password to encrypt
	 * @return Base64 encoded encrypted password with IV and salt, or null if
	 *         encryption fails
	 */
	public static String encrypt(String employee_id, String password) {
		try {
			// 16 bytes salt
			byte[] salt = getRandomNonce(SALT_LENGTH_BYTE);

			// GCM recommended 12 bytes iv?
			byte[] iv = getRandomNonce(IV_LENGTH_BYTE);

			// secret key from password
			SecretKey aesKeyFromPassword = getAESKeyFromPassword(employee_id.toCharArray(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

			// ASE-GCM needs GCMParameterSpec
			cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] cipherText = cipher.doFinal(password.getBytes());

			// prefix IV and Salt to cipher text
			byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv)
					.put(salt).put(cipherText).array();

			// string representation, base64, send this string to other for decryption.
			return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Decrypts an encrypted password using the username as the basis for the key.
	 * 
	 * @param encryptedPassword the Base64 encoded encrypted password with IV and
	 *                          salt
	 * @param username          the username to use for key derivation
	 * @return the decrypted password as a string, or null if decryption fails
	 */
	public static String decrypt(String encryptedPassword, String username) {
		try {
			byte[] decode = Base64.getDecoder().decode(encryptedPassword.getBytes(UTF_8));

			// get back the iv and salt from the cipher text
			ByteBuffer bb = ByteBuffer.wrap(decode);

			byte[] iv = new byte[IV_LENGTH_BYTE];
			bb.get(iv);

			byte[] salt = new byte[SALT_LENGTH_BYTE];
			bb.get(salt);

			byte[] cipherText = new byte[bb.remaining()];
			bb.get(cipherText);

			// get back the aes key from the same password and salt
			SecretKey aesKeyFromPassword = PasswordUtil.getAESKeyFromPassword(username.toCharArray(), salt);

			Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

			cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

			byte[] plainText = cipher.doFinal(cipherText);

			return new String(plainText, UTF_8);
		} catch (Exception ex) {
			return null;
		}
	}
}