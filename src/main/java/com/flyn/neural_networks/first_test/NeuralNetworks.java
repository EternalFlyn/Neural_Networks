package com.flyn.neural_networks.first_test;

import java.util.HashMap;

public class NeuralNetworks {
	
	private final int layerLength;
	private HashMap<Integer, Matrix> weights = new HashMap<Integer, Matrix>(), biases = new HashMap<Integer, Matrix>();
	
	public NeuralNetworks(int[] layer_sizes) {
		layerLength = layer_sizes.length;
		for(int i = 1; i < layerLength; i++) {
			weights.put(i, new Matrix(layer_sizes[i], layer_sizes[i - 1]));
			biases.put(i, new Matrix(layer_sizes[i], 1));
		}
	}
	
	public Matrix predict(Matrix data) {
		Matrix result = data;
		for(int i = 1; i < layerLength; i++) {
			result = Matrix.matrixPlus(Matrix.matrixProduct(weights.get(i), result), biases.get(i));
			double[][] resultData = result.getData();
			for(int a = 0; a < result.size[0]; a++) {
				for(int b = 0; b < result.size[1]; b++) {
					resultData[a][b] = activation(resultData[a][b]);
				}
			}
		}
		return result;
	}
	
	private double activation(double x) {
		return 1 / (1 + Math.exp(-x));
	}
	
	public HashMap<Integer, Matrix> getWeight() {
		return weights;
	}
	
	public HashMap<Integer, Matrix> getBias() {
		return biases;
	}
}
