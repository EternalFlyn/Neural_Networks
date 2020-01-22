package com.flyn.neural_networks.training_data;

import java.awt.image.BufferedImage;

public class TrainingImage {
	
	public final byte[][] rawData;
	public final BufferedImage[] imageData;
	public final int magic_number, itemAmount, rows, columns;

	public TrainingImage() {
		byte[] data = Utils.read(getClass().getResourceAsStream("./train-images.idx3-ubyte"));
		magic_number = Utils.byteToInt(data, 0);
		itemAmount = Utils.byteToInt(data, 4);
		rows = Utils.byteToInt(data, 8);
		columns = Utils.byteToInt(data, 12);
		rawData = sortData(data);
		imageData = new BufferedImage[itemAmount];
		for(int i = 0; i < itemAmount; i++) imageData[i] = getImage(rawData[i]);
	}
	
	private byte[][] sortData(byte[] data) {
		byte[][] result = new byte[itemAmount][rows * columns];
		for(int i = 0; i < itemAmount; i++) {
			for(int j = 0; j < rows * columns; j++) {
				result[i][j] = data[16 + i * rows * columns + j];
			}
		}
		return result;
	}
	
	private BufferedImage getImage(byte[] data) {
		BufferedImage result = new BufferedImage(rows, columns, BufferedImage.TYPE_INT_ARGB);
		int[] rgb = new int[rows * columns];
		for(int i = 0; i < rows * columns; i++) {
			rgb[i] = getRGB(data[i]);
		}
		result.setRGB(0, 0, rows, columns, rgb, 0, rows);
		return result;
	}
	
	private int getRGB(byte data) {
		int result = 0;
		for(int i = 2; i >= 0; i--) result |= (data & 0xFF) << i * 8;
		result |= 255 << 24;
		return result;
	}

}
