package vn.mog.topup.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil {

	public static String createSign(final String data, final String privateKeyPath) throws IOException,
			NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
		String signOfData = null;
		final byte[] privateKeyBytes = FileUtils.readFileToByteArray(new File(privateKeyPath));
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		final PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		final Signature sg = Signature.getInstance("SHA1withRSA");
		sg.initSign(privateKey);
		sg.update(data.getBytes());
		final byte[] signBytes = sg.sign();
		signOfData = new String(Base64.encodeBase64(signBytes));
		return signOfData;

	}

	public static boolean verifySign(final String sign, final String data, final String publicKeyPath)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			InvalidKeyException {
		final byte[] publicKeyBytes = FileUtils.readFileToByteArray(new File(publicKeyPath));
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
		final PublicKey publicKey = keyFactory.generatePublic(keySpec);
		final Signature sg = Signature.getInstance("SHA1withRSA");
		sg.initVerify(publicKey);
		sg.update(data.getBytes());
		return sg.verify(Base64.decodeBase64(sign.getBytes()));
	}

	public static String encrypt(String key, String data) throws Exception {
		if (StringUtils.isEmpty(key))
			throw new NullPointerException();
		Cipher cipher = Cipher.getInstance("TripleDES");
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(key.getBytes(), 0, key.length());
		String keymd5 = new BigInteger(1, md5.digest()).toString(16).substring(0, 24);
		SecretKeySpec keyspec = new SecretKeySpec(keymd5.getBytes(), "TripleDES");
		cipher.init(Cipher.ENCRYPT_MODE, keyspec);
		byte[] stringBytes = data.getBytes();
		byte[] raw = cipher.doFinal(stringBytes);
		BASE64Encoder encoder = new BASE64Encoder();
		String base64 = encoder.encode(raw);
		return base64;
	}

	public static String decrypt(String key, String data) throws Exception {
		if (StringUtils.isEmpty(key))
			throw new NullPointerException();
		Cipher cipher = Cipher.getInstance("TripleDES");
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(key.getBytes(), 0, key.length());
		String keymd5 = new BigInteger(1, md5.digest()).toString(16).substring(0, 24);
		SecretKeySpec keyspec = new SecretKeySpec(keymd5.getBytes(), "TripleDES");
		cipher.init(Cipher.DECRYPT_MODE, keyspec);
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] raw = decoder.decodeBuffer(data);
		byte[] stringBytes = cipher.doFinal(raw);
		String result = new String(stringBytes);
		return result;
	}
}
