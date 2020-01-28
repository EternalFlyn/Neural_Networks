package com.flyn.neural_networks;

import java.util.Random;

import com.flyn.neural_networks.first_test.Matrix;
import com.flyn.neural_networks.first_test.NeuralNetworks;
import com.flyn.neural_networks.training_data.TrainingImage;
import com.flyn.neural_networks.training_data.TrainingImageViewer;
import com.flyn.neural_networks.training_data.TrainingLabel;

public class App {
	
	//測試的樣本數 樣本的起始值
	private static int testAmount = 1000, testOffset = 0;
	private static int[] layer_sizes;
	private static TrainingImage image = new TrainingImage();
	private static TrainingLabel label = new TrainingLabel();
	
    public static void main(String[] args) {
    	TrainingImageViewer viewer = new TrainingImageViewer(image);
    	viewer.show();
    	layer_sizes = new int[] {image.rows * image.columns, 512, 256, 10};
    	NeuralNetworks nn = new NeuralNetworks(layer_sizes);
    	//設定w, b
    	for(int i = 0; i < layer_sizes.length - 1; i++) {
    		nn.getWeight().get(i).fillGaussianData().divideNumber(Math.pow(layer_sizes[i], 0.5));
    	}
    	testOffset = new Random().nextInt(image.itemAmount - testAmount);
    	for(int i = testOffset; i < image.itemAmount && i < testOffset + testAmount; i++) {
        	//填入數據
        	double[][] dataArray = new double[layer_sizes[0]][1];
        	for(int j = 0; j < layer_sizes[0]; j++) {
        		double temp = (double) (image.rawData[i][j] & 0xFF) / 255.0;
        		dataArray[j][0] = temp;
        	}
        	Matrix data = new Matrix(dataArray);
        	nn.predict(data, label.data[i]);
        	nn.BGD(0.3, label.data[i]);
        	System.out.println(nn.getCorrectRate());
    	}
    }
}
