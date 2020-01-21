package com.flyn.neural_networks;

import com.flyn.neural_networks.first_test.Matrix;
import com.flyn.neural_networks.first_test.NeuralNetworks;

public class App {
	
	private static int[] layer_sizes = new int[] {3, 5, 10};
	
    public static void main(String[] args) {
    	NeuralNetworks nn = new NeuralNetworks(layer_sizes);
    	for(Matrix m : nn.getWeight()) {
    		m.fillGaussianData().divideNumber(Math.pow(layer_sizes[0], 0.5));
    		m.printSize();
    		m.printData();
    	}
    	for(Matrix m : nn.getBias()) {
    		m.printSize();
    		m.printData();
    	}
    	Matrix data = new Matrix(layer_sizes[0], 1).fillOne();
    	Matrix result = nn.predict(data);
    	result.printSize();
    	result.printData(0, 0);
    }
}
