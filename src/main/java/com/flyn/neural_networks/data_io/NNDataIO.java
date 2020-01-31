package com.flyn.neural_networks.data_io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.flyn.neural_networks.first_test.Matrix;
import com.flyn.neural_networks.first_test.NeuralNetworks;

public class NNDataIO {
	
	/*
	 * float accuracy(%)
	 * int layerLength
	 * int first layer node amount
	 * int second layer node amount
	 * ...
	 * double first layer first weight
	 * double first layer second weight
	 * ...
	 * double last layer last weight
	 * double first layer first bias
	 * double first layer second bias
	 * ...
	 * double last layer last bias
	 */
	
	private NNDataIO() {}
	
	public static NeuralNetworks read(String fileName) {
		NeuralNetworks nn = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("./" + fileName + ".nn"));
			System.out.println(input.readFloat());
			int layerLength = input.readInt();
			int[] layerSize = new int[layerLength];
			nn = new NeuralNetworks(layerSize);
			ArrayList<Matrix> weights = nn.getWeight(), biases = nn.getBias();
			for(int i = 0; i < layerLength; i++) layerSize[i] = input.readInt();
			for(int i = 1; i < layerLength; i++) {
				Matrix weight = new Matrix(layerSize[i], layerSize[i - 1]);
				for(int j = 0; j < weight.getRow(); j++) {
					for(int k = 0; k < weight.getColumn(); k++) {
						weight.setData(j, k, input.readDouble());
					}
				}
				weights.set(i - 1, weight);
			}
			for(int i = 1; i < layerLength; i++) {
				Matrix bias = new Matrix(layerSize[i], 1);
				for(int j = 0; j < bias.getRow(); j++) {
					bias.setData(j, 0, input.readDouble());
				}
				biases.set(i - 1, bias);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nn;
	}
	
	public static void wirte(String fileName, NeuralNetworks nn) {
		ArrayList<Matrix> weights = nn.getWeight(), biases = nn.getBias();
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./" + fileName + ".nn"));
			output.writeFloat(nn.getCorrectRate() * 100);
			output.writeInt(nn.layerLength);
			for(int i : nn.layerSize) output.writeInt(i);
			for(int i = 0; i < weights.size(); i++) {
				for(double[] dArray : weights.get(i).getData()) {
					for(double data : dArray) {
						output.writeDouble(data);
					}
				}
			}
			for(int i = 0; i < biases.size(); i++) {
				for(double[] dArray : biases.get(i).getData()) {
					for(double data : dArray) {
						output.writeDouble(data);
					}
				}
			}
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
