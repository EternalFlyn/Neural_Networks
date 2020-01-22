package com.flyn.neural_networks.training_data;

import java.awt.image.BufferedImage;

public class TrainingImage {
	
	public final BufferedImage[] data;
	public final int magic_number, itemAmount, rows, columns;

	public TrainingImage() {
		byte[] tempData = Utils.read(getClass().getResourceAsStream("./train-images.idx3-ubyte"));
		magic_number = Utils.byteToInt(tempData, 0);
		itemAmount = Utils.byteToInt(tempData, 4);
		rows = Utils.byteToInt(tempData, 8);
		columns = Utils.byteToInt(tempData, 12);
		data = new BufferedImage[itemAmount];
		for(int i = 0; i < itemAmount; i++) data[i] = getImage(tempData, i);
	}
	
	private BufferedImage getImage(byte[] data, int imgNum) {
		int offset = 16 + rows * columns * imgNum;
		BufferedImage result = new BufferedImage(rows, columns, BufferedImage.TYPE_INT_ARGB);
		int[] rgb = new int[rows * columns];
		for(int i = 0; i < rows * columns; i++) {
			rgb[i] = getRGB(data[offset + i]);
		}
		result.setRGB(0, 0, rows, columns, rgb, 0, rows);
		System.out.println("Image " + imgNum + " complete.");
		return result;
	}
	
	private int getRGB(byte data) {
		int result = 0;
		for(int i = 2; i >= 0; i--) result |= (data & 0xFF) << i * 8;
		result |= 255 << 24;
		return result;
	}

}
