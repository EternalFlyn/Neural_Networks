package com.flyn.neural_networks.training_data;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
	
	private Utils() {}
	
	public static byte[] read(InputStream input) {
		byte[] result = null;
		InputStream ins = input;
		try {
			result = new byte[ins.available()];
			while(ins.available() > 0) {
				ins.read(result); 
			}
			ins.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int byteToInt(byte[] data, int offset) {
		int result = 0;
		for(int i = offset; i < offset + 4; i++) result |= (data[i] & 0xFF) << (3 - i) * 8;
		return result;
	}

}
