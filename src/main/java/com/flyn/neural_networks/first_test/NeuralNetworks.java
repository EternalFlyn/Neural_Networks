package com.flyn.neural_networks.first_test;

import java.util.ArrayList;

public class NeuralNetworks {
	
	private int sampleSize = 0, matchSample = 0;
	private final int layerLength;
	private ArrayList<Matrix> weights = new ArrayList<>(), biases = new ArrayList<>(), net = new ArrayList<>();
	
	public NeuralNetworks(int[] layer_sizes) {
		layerLength = layer_sizes.length;
		for(int i = 1; i < layerLength; i++) {
			weights.add(new Matrix(layer_sizes[i], layer_sizes[i - 1]));
			biases.add(new Matrix(layer_sizes[i], 1));
		}
		for(int i = 0; i < layerLength; i++) {
			net.add(new Matrix(layer_sizes[i], 1));
		}
	}
	
	public int predict(Matrix data, int answer) {
		sampleSize++;
		net.set(0, data);
		Matrix input = data;
		for(int i = 0; i < layerLength - 1; i++) {
			input = sigmoid(Matrix.matrixPlus(Matrix.matrixProduct(weights.get(i), input), biases.get(i)));
			net.set(i + 1, input);
		}
		int num = 0;
		double max = Double.MIN_VALUE;
		for(int i = 0; i < input.getRow(); i++) {
			if(input.getData(i, 0) > max) {
				num = i;
				max = input.getData(i, 0);
			}
		}
		if(num == answer) matchSample++;
		return num;
	}
	
	public void BGD(double alpha, int answer) {
        //存每個節點gradient_b
        Matrix preBiasGradientMatrix = null;
        int answerData = 0;
        for(int i = layerLength - 2; i >= 0; i--) {
            //output -> invis2 : weight [10 x 256] bias [10 x 1]'
            //decalare bias maxtrix data storage
            double[][] biasGradientData = new double[biases.get(i).getRow()][1];
            double[][] preBiasGradientData = new double[weights.get(i).getColumn()][1];
            double[][] weightGradientData = new double[weights.get(i).getRow()][weights.get(i).getColumn()];
            for(int j = 0; j < weights.get(i).getRow(); j++) {
                double bias = net.get(i).getData(j, 0);
                double fx = 0;
                if(i == answer) answerData = 1;
                else answerData = 0;
                if(i == layerLength - 2) fx = 2 * (net.get(i + 1).getData(j, 0) - answerData);
                for(int k = 0; k < weights.get(i).getColumn(); k++) {
                    if(i != layerLength - 2) fx = weights.get(i).getData(j, k) * preBiasGradientMatrix.getData(j, 0);
                    double weight = weights.get(i).getData(j , k);
                    double input = net.get(i).getData(k, 0);
                    double pieceBiasGradient = countBiasGradient(weight, input, bias, fx);
                    double weightGradient = countWeightGradient(input, pieceBiasGradient);
                    //store and splice gradient_b
                    biasGradientData[j][0] += pieceBiasGradient;
                    preBiasGradientData[k][0] += pieceBiasGradient;
                    weightGradientData[j][k] = weightGradient;
                }
            }
            //update
            update(i, alpha, weightGradientData, biasGradientData);
            preBiasGradientMatrix = new Matrix(preBiasGradientData);
        }
    }
	
    private double countBiasGradient(double weight, double input, double bias, double fx){
        return sigmoidDerivative(weight * input + bias) * fx;
    }
    
    private double countWeightGradient(double input, double pieceBiasGradient){
        return input * pieceBiasGradient;
    }
    
    private void update(int layerNum, double alpha, double[][] weightGradient, double[][] biasGradient){
        double[][] weightData = weights.get(layerNum).getData();
        double[][] biasData = biases.get(layerNum).getData();
        for(int i = 0; i < biases.get(layerNum).getRow(); i++){
            biasData[i][0] -= alpha * biasGradient[i][0];
            for(int j = 0; j < weights.get(layerNum).getColumn(); j++){
                weightData[i][j] -= alpha * weightGradient[i][j];
            }
        }
        weights.set(layerNum, new Matrix(weightData));
        biases.set(layerNum, new Matrix(biasData));
    }
	
	private Matrix sigmoid(Matrix data) {
		double[][] resultData = data.getData();
		for(int i = 0; i < data.getRow(); i++) {
			for(int j = 0; j < data.getColumn(); j++) {
				resultData[i][j] = 1 / (1 + Math.exp(-1 * resultData[i][j]));
			}
		}
		return new Matrix(resultData);
	}
	
    private double sigmoidDerivative(double z) {
        double a = 1 / (1 + Math.exp(-1 * z));
        // (1/1+e^-x)的微分 = (1/1+e^-x) * (1 - 1/1+e^-x) = sigmoid(z) * ( 1 - sigmoid(z) )
        return a * (1 - a);
    }
    
    public float getCorrectRate() {
    	return (float) matchSample / (float) sampleSize;
    }
	
	public ArrayList<Matrix> getWeight() {
		return weights;
	}
	
	public ArrayList<Matrix> getBias() {
		return biases;
	}

	public ArrayList<Matrix> getNet() {
		return net;
	}
}
