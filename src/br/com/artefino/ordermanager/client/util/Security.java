/**
 * (C) Copyright 2010, 2011 upTick Pty Ltd
 *
 * Licensed under the terms of the GNU General Public License version 3
 * as published by the Free Software Foundation. You may obtain a copy of the
 * License at: http://www.gnu.org/copyleft/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package br.com.artefino.ordermanager.client.util;

import com.google.gwt.user.client.Random;

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

  public static native String md5(String pText)
  /*-{
    return $wnd.hex_md5(pText);
  }-*/;

  public static native String sha256(String pText)
  /*-{
    return $wnd.hex_sha256(pText);
  }-*/;

  public static native String sha512(String pText)
  /*-{
    return $wnd.hex_sha512(pText);
  }-*/;

  public static String randomCharString() {
    String r = "";
    for (int i = 0; i < 32; i++) {
      r += (char) ('A' + Random.nextInt(26));
    }
    return r;
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
