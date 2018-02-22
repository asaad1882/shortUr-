package com.leverton.shortURL.utilities;

public class UrlGenerator {
	 public static final String ALPHNUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    public static final int URLGENEARTOR = ALPHNUMERIC.length();
	    
	    public static String generateShortUrl(long value) {
	        final StringBuilder sb = new StringBuilder(1);
	        do {
	            sb.insert(0, ALPHNUMERIC.charAt((int) (value % URLGENEARTOR)));
	            value /= URLGENEARTOR;
	        } while (value > 0);
	        return sb.toString();
	    }
	 
	    
	    
}
