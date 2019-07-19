package util;

import java.util.Random;

public class RandomStringGenerator {
	private static final char[] URLSafeASCII = new char[] {'0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g',
			'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o',
			'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'V', 'v', 'W', 'w',
			'X', 'x', 'Y', 'y', 'Z', 'z', '-', '_'};
	
	public static String getRandomURLSafeASCIIString(int length) {
		String res = "";
		for (int i = 0; i < length; i++)
			res += URLSafeASCII[new Random().nextInt(URLSafeASCII.length)];
		return res;
	}
	
}
