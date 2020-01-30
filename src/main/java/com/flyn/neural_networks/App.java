package com.flyn.neural_networks;

import java.util.Random;

import com.flyn.neural_networks.first_test.Matrix;
import com.flyn.neural_networks.first_test.NeuralNetworks;
import com.flyn.neural_networks.training_data.TrainingImage;
//import com.flyn.neural_networks.training_data.TrainingImageViewer;
import com.flyn.neural_networks.training_data.TrainingLabel;

public class App {
	
	private static TrainingImage image = new TrainingImage();
	private static TrainingLabel label = new TrainingLabel();
	//測試的樣本數 樣本的起始值
	private static int testAmount = 60000, testOffset = testAmount != image.itemAmount ? new Random().nextInt(image.itemAmount - testAmount) : 0;
	
    public static void main(String[] args) {
//    	TrainingImageViewer viewer = new TrainingImageViewer(image);
//    	viewer.show();
    	NeuralNetworks nn = new NeuralNetworks(image.rows * image.columns, 50, 10);
    	for(int L = 0; L < 1; L++) {
    		for(int i = testOffset; i < image.itemAmount && i < testOffset + testAmount; i++) {
    			//填入數據
    			Matrix data = new Matrix(nn.layerSize[0], 1);
    			for(int j = 0; j < nn.layerSize[0]; j++) {
    				double temp = (double) (image.rawData[i][j] & 0xFF) / 255.0;
    				data.setData(j, 0, temp);
    			}
    			int result = nn.predict(data, label.data[i]);
    			nn.BGD(1, label.data[i]);
    			System.out.printf("%d: %d, %d%n", i, result, label.data[i]);
    			System.out.printf("%f%%\n", nn.getCorrectRate() * 100);
    		}
    	}
    }
}
