package com.thestore.eam.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密者
 * 
 * @author: weip
 * @time: 2007-8-29 下午05:45:36
 */
public final class Encrypter {
	private static Cipher ecipher;

	private static Cipher dcipher;

	// 必须24个字符
	private static final String key = "*:@1$7!a*:@1$7!a*:@1$7!a";

	private static final String alg = "DESede";

	static {
		try {
			SecretKey skey = new SecretKeySpec(key.getBytes(), alg);

			ecipher = Cipher.getInstance(alg);
			dcipher = Cipher.getInstance(alg);
			ecipher.init(Cipher.ENCRYPT_MODE, skey);
			dcipher.init(Cipher.DECRYPT_MODE, skey);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密字符串
	 * 
	 * @author: weip
	 * @time: 2007-8-29 下午09:41:11
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		if (str == null) {
			return "";
		}
		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string
			return Base64Support.toUrlStr(enc);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 解密字符串
	 * 
	 * @author: weip
	 * @time: 2007-8-29 下午10:00:00
	 * @param str
	 * @return
	 */
	public static String decrypt(String str) {
		if (str == null) {
			return "";
		}
		try {
			// Decode base64 to get bytes
			byte[] dec = Base64Support.fromUrlStr(str);

			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf8, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 对字符串进行md5加密
	 * 
	 * @Title: MD5Encrypt
	 * @Description:返回加密后的字符串
	 * @param String
	 * @return String
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date 2013-4-2
	 */
	public static String MD5Encrypt(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("MD5"); // 可以选中其他的算法如SHA
			byte[] digest = md.digest(inStr.getBytes());
			// 返回的是byet[]，要转化为String存储比较方便
			outStr = bytetoString(digest);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}

	/**
	 * 将字符数字转换成字符串
	 * 
	 * @Title: bytetoString
	 * @Description:
	 * @param byte[]
	 * @return String
	 * @author Wu Wenqi(wuwenqi@yihaodian.com)
	 * @date 2013-4-2
	 */
	public static String bytetoString(byte[] digest) {
		String tempStr = "";
		StringBuffer sb = new StringBuffer("");
		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				sb.append("0").append(tempStr);
			} else {
				sb.append(tempStr);
			}
		}
		return sb.toString().toLowerCase();
	}

	public static void main(String args[]) {
		System.out.println(decrypt("RDdQqSW2VJ1XlOw16BP8lw.."));
		System.out.println(encrypt("jingpin"));
		System.out.println("aaa");
		System.out.println(decrypt("9ixXGbTDb7iApW5w8t-gNw.."));
		System.out.println(decrypt("axl6_7umh37iNiiqIWQPKg.."));
		System.out.println(decrypt("MCqIBp8ILiw."));

		System.out.println("aaa");
		System.out.println(encrypt("adoutside"));
		String encode = MD5Encrypt("yhdadm");
		System.out.println(encode);
		System.out.println(encrypt("ams1q2wASD"));
		System.out.println("aaa");
		System.out.println(encrypt("xieyalin"));
		System.out.println("aaa");
		System.out.println(encrypt("yhdasm123"));
		System.out.println("bbb");
		System.out.println(decrypt("RDdQqSW2VJ1XlOw16BP8lw.."));

		// mrc线上数据库密码
		System.out.println(decrypt("13sbpXNQhWq00FDYdpX76Q.."));

		// bi测试数据库密码
		System.out.println("biTest_" + decrypt("yzV-yrN3R3hXlOw16BP8lw.."));

		// try {
		// URL url = new
		// URL("https://api.cn.miaozhen.com/cms/v1/campaigns/show_spot?campaign_id=1012678&spot_id_str=4%2bbCF0&access_token=2.De8WY1UVfx2kVBoHYXBpLXloZCIGMTAwMDE5KAM.MCwCFAc8EmQFPIxpSoxH-wi4JMsfLJT8AhQpQuRGaVACYP_hrLTxl7_awcO4kA");
		// InputStreamReader isr= new InputStreamReader(url.openStream());
		// BufferedReader br=new BufferedReader(isr);
		// String str;
		// while((str = br.readLine()) != null){
		// System.out.println(str);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
}
