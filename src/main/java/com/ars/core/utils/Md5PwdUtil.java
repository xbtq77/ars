package com.ars.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * Md5加密
 * @author lx
 *
 */
public class Md5PwdUtil {

	//加密
	public static String  encode(String password){
		String algorithm = "MD5";
		//加盐  fghjkgddfgh
		//password = "erqwefghjkgddfghyuiwww";
		MessageDigest instance = null;
		try {
			instance = MessageDigest.getInstance(algorithm);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//加密
		byte[] digest = instance.digest(password.getBytes());
		//十六进制加密
		char[] encodeHex = Hex.encodeHex(digest);
		
		return new String(encodeHex);
		
	}
	public static void main(String[] args) {
		System.out.println(encode("admin"));
	}
}
