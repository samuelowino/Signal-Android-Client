package org.aplusstudios.com.signalclient.utils;

public class BitmapDecodingException extends Exception {

  public BitmapDecodingException(String s) {
    super(s);
  }

  public BitmapDecodingException(Exception nested) {
    super(nested);
  }
}
