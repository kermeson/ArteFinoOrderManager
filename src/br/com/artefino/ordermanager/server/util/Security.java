package br.com.artefino.ordermanager.server.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.allen_sauer.gwt.log.client.Log;

public class Security {

  public static String byteStringToHexString(final String s) {
    String r = "";
    for (int i = 0; i < s.length(); i++) {
      r += byteToHexChars(s.charAt(i));
    }
    return r;
  }

  public static String byteToHexChars(final int i) {
    final String s = "0" + Integer.toHexString(i);
    return s.substring(s.length() - 2);
  }

  public static String hexStringToByteString(final String s) {
    String r = "";
    for (int i = 0; i < s.length(); i += 2) {
      r += (char) Integer.parseInt(s.substring(i, i + 2), 16);
    }
    return r;
  }

  public static String md5(final String text) {
    String hashword = null;

    try {
      final MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(text.getBytes());
      final BigInteger hash = new BigInteger(1, md5.digest());
      hashword = hash.toString(16);
    }
    catch (final NoSuchAlgorithmException nsae) {
    }

    while (hashword.length() < 32) {
      hashword = "0" + hashword;
    }

    return hashword;
  }

  public static String sha256(final String text) {
    String hashword = null;

    try {
      final MessageDigest sha = MessageDigest.getInstance("SHA-256");
      sha.update(text.getBytes());
      final BigInteger hash = new BigInteger(1, sha.digest());
      hashword = hash.toString(16);
    }
    catch (final NoSuchAlgorithmException nsae) {
    }

    while (hashword.length() < 32) {
      hashword = "0" + hashword;
    }

    return hashword;
  }

  public static String sha512(final String text) {
    String hashword = null;

    try {
      final MessageDigest sha = MessageDigest.getInstance("SHA-512");
      sha.update(text.getBytes());
      final BigInteger hash = new BigInteger(1, sha.digest());
      hashword = hash.toString(16);
    }
    catch (final NoSuchAlgorithmException nsae) {
    }

    while (hashword.length() < 32) {
      hashword = "0" + hashword;
    }

    return hashword;
  }

  public static String randomCharString() {
    String buffer = null;

    try {
      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

      byte[] salt = new byte[32];
      secureRandom.nextBytes(salt);

      Log.debug("salt: " + salt);

      buffer = new String(salt);
    }
    catch (NoSuchAlgorithmException e) {
    }

    Log.debug("buffer: " + buffer);

    return buffer;
  }

  private final byte sbox[] = new byte[256];
  private int i;
  private int j;

  public String codeDecode(final String plaintext) {
    byte x;
    String r = "";
    final int pl = plaintext.length();
    for (int k = 0; k < pl; k++) {
      i = i + 1 & 0xff;
      j = j + sbox[i] & 0xff;

      x = sbox[i];
      sbox[i] = sbox[j];
      sbox[j] = x;

      r += (char) (plaintext.charAt(k) ^ sbox[sbox[i] + sbox[j] & 0xff] & 0xff);
    }

    return r;
  }

  public String codeDecode(final String key, final String plaintext) {
    setUp(key);
    return codeDecode(plaintext);
  }

  public void setUp(final String key) {
    int k;
    byte x;

    for (i = 0; i < 256; i++) {
      sbox[i] = (byte) i;
    }

    final int kl = key.length();
    for (i = 0, j = 0, k = 0; i < 256; i++) {
      j = j + sbox[i] + key.charAt(k) & 0xff;
      k = (k + 1) % kl;

      x = sbox[i];
      sbox[i] = sbox[j];
      sbox[j] = x;
    }

    // Set things up to start coding/decoding

    i = 0;
    j = 0;
  }
}
