package com.whenguycan.kawori.common;

import java.security.MessageDigest;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author wangchenyu
 * 2016-11-16
 */
public class Encrypt {
	
	private static String ENCRYPT_ALGORITHM_AES = "AES";
	private static int ENCRYPT_KEY_LEN_128 = 128;
	private static String CIPHER_ALGORITHM_AES_ECB_PADDING = "AES/ECB/PKCS5Padding";

	private static String ENCRYPT_ALGORITHM = ENCRYPT_ALGORITHM_AES;
	private static int ENCRYPT_KEY_LEN = ENCRYPT_KEY_LEN_128;
	private static String CIPHER_ALGORITHM = CIPHER_ALGORITHM_AES_ECB_PADDING;
	
	public void init(String encrypt_alogrithm, Integer encrypt_key_len, String cipher_algorithm){
		//please use the correct, no more validate
		ENCRYPT_ALGORITHM = encrypt_alogrithm;
		ENCRYPT_KEY_LEN = encrypt_key_len;
		CIPHER_ALGORITHM = cipher_algorithm;
	}
	
	/**
	 * byte数组转16进制字符串
	 * @return
	 */
	private static String bytes2Hex(byte[] bytes){
		StringBuilder hex = new StringBuilder();
		for(byte b : bytes){
			String h = Integer.toHexString(b);
			if(b > 0 && b < 16){
				hex.append("0");
			}
			hex.append(h.length()==8?h.substring(6):h);
		}
		return hex.toString();
	}
	
	/**
	 * 16进制字符串转byte数组
	 * @return
	 */
	private static byte[] hex2Bytes(String hex){
		byte[] bytes = new byte[hex.length()/2];
		for(int i=0,len=bytes.length;i<len;i++){
			bytes[i] = (byte)Integer.parseInt(hex.substring(i*2, i*2+2), 16);
		}
		return bytes;
	}
	
	/**
	 * 随机生成密钥
	 * @return
	 */
	private static byte[] generateSecretKey(String encrypt_algorithm, int encrypt_key_len){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(encrypt_algorithm);
			kgen.init(encrypt_key_len);
			return kgen.generateKey().getEncoded();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加密(使用密钥)
	 * @return
	 */
	private static String encrypt(String password, String secretKey){
		byte[] passwordBytes = password.getBytes();
		byte[] secretKeyBytes = hex2Bytes(secretKey);
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKeyBytes, ENCRYPT_ALGORITHM));
			byte[] encryptedPasswordBytes = cipher.doFinal(passwordBytes);
			return bytes2Hex(encryptedPasswordBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解密(使用密钥)
	 * @return
	 */
	private static String decrypt(String encryptedPassword, String secretKey){
		byte[] encryptedPasswordBytes = hex2Bytes(encryptedPassword);
		byte[] secretKeyBytes = hex2Bytes(secretKey);
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE,new SecretKeySpec(secretKeyBytes, ENCRYPT_ALGORITHM));
			byte[] passwordBytes = cipher.doFinal(encryptedPasswordBytes);
			return new String(passwordBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加密(随机密钥)
	 * @return
	 */
	public static String encrypt(String password){
		byte[] secretKeyBytes = generateSecretKey(ENCRYPT_ALGORITHM, ENCRYPT_KEY_LEN);
		String secretKey = bytes2Hex(secretKeyBytes);
		String encryptedPassword = encrypt(password, secretKey);
		//加密字符串拼接，注意，解密时获取密钥要参照这里
		return secretKey + encryptedPassword;
	}
	
	/**
	 * 解密(随机密钥)
	 * @return
	 */
	public static String decrypt(String encryptedPassword){
		int secretKeySize = ENCRYPT_KEY_LEN / 4;
		String secretKey = encryptedPassword.substring(0, secretKeySize);
		String encryptedPassword_ = encryptedPassword.substring(secretKeySize);
		return decrypt(encryptedPassword_, secretKey);
	}
	
	/**
	 * md5
	 * @param text
	 * @return
	 */
	public static String md5(String text){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte[] bytes = md.digest();
			return bytes2Hex(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 密码验证(md5)
	 * @param password 密码
	 * @param passwordStored 储存的密码
	 * @return
	 */
	public static boolean md5PasswordCheck(String password,String passwordStored){
		String salt = passwordStored.substring(0, 7);
		return passwordStored.equals(genPassword(salt,password));
	}
	
	/**
	 * 密码加密(md5)
	 * @param password 密码
	 * @return
	 */
	public static String md5Password(String password){
		String salt = UUID.randomUUID().toString().replace("-", "").substring(0,7);
		//加密密码拼接，注意，验证时要参照这里
		return genPassword(salt,password);
	}
	
	private static String genPassword(String salt,String password){
		return salt + md5(salt + password);
	}
	
	public static void main(String[] args){
		String password = "wang666mx5";
		String encodePassword = Encrypt.md5Password(password);
		System.out.println(encodePassword);
		System.out.println(Encrypt.md5PasswordCheck(password, encodePassword));
	}
}
