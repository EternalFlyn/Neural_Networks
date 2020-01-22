package com.flyn.neural_networks.training_data;

public class TrainingLabel {
	
	public final byte[] data;
	public final int magic_number, itemAmount;
	
	public TrainingLabel() {
		data = Utils.read(getClass().getResourceAsStream("./train-labels.idx1-ubyte"));
		magic_number = Utils.byteToInt(data, 0);
		itemAmount = Utils.byteToInt(data, 4);
		for(int i = 8; i < data.length; i++) {
			this.data[i - 8] = data[i];
		}
	}

}
