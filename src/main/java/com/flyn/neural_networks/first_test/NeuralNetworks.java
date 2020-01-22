package com.flyn.neural_networks.first_test;

import java.util.ArrayList;

public class NeuralNetworks {
	
	private final int layerLength;
	private ArrayList<Matrix> weights = new ArrayList<Matrix>(), biases = new ArrayList<Matrix>();
	
	public NeuralNetworks(int[] layer_sizes) {
		layerLength = layer_sizes.length;
		for(int i = 1; i < layerLength; i++) {
			weights.add(new Matrix(layer_sizes[i], layer_sizes[i - 1]));
			biases.add(new Matrix(layer_sizes[i], 1));
		}
	}
	
	public Matrix predict(Matrix data) {
		Matrix result = data;
		for(int i = 0; i < layerLength - 1; i++) result = activation(Matrix.matrixPlus(Matrix.matrixProduct(weights.get(i), result), biases.get(i)));
		return result;
	}
	
	private Matrix activation(Matrix data) {
		double[][] resultData = data.getData();
		for(int i = 0; i < data.size[0]; i++) {
			for(int j = 0; j < data.size[1]; j++) {
				resultData[i][j] = 1 / (1 + Math.exp(-1 * resultData[i][j]));
			}
		}
		return new Matrix(resultData);
	}
	
	public ArrayList<Matrix> getWeight() {
		return weights;
	}
	
	public ArrayList<Matrix> getBias() {
		return biases;
	}
}
