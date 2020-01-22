package com.flyn.neural_networks.first_test;

import java.util.Random;

public class Matrix {
	
	public final int[] size;
	
	private double[][] data;
	
	public Matrix(int a, int b) {
		this.data = new double[a][b];
		size = new int[] {a, b};
	}
	
	public Matrix(double[][] data) {
		this.data = data;
		size = new int[] {data.length, data[0].length};
	}
	
	public double[][] getData() {
		return data;
	}
	
	public double getData(int a, int b) {
		return data[a][b];
	}
	
	public Matrix fillOne() {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				data[i][j] = 1;
			}
		}
		return this;
	}
	
	public Matrix fillRandomData() {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				data[i][j] = new Random().nextDouble();
			}
		}
		return this;
	}
	
	public Matrix fillGaussianData() {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				data[i][j] = new Random().nextGaussian();
			}
		}
		return this;
	}
	
	public void printData() {
		System.out.print("[");
		for(int i = 0; i < size[0];) {
			System.out.print("[");
			for(int j = 0; j < size[1];) {
				System.out.print(data[i][j]);
				j++;
				if(j < size[1]) System.out.print(", ");
				else System.out.print("]");
			}
			i++;
			if(i < size[0]) System.out.println();
		}
		System.out.println("]");
	}
	
	public void printData(int a, int b) {
		System.out.println(data[a][b]);
	}
	
	public void printSize() {
		System.out.println(size[0] + "x" + size[1]);
	}
	
	public Matrix plusNumber(double num) {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				data[i][j] += num;
			}
		}
		return this;
	}
	
	
	public Matrix minusNumber(double num) {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				data[i][j] -= num;
			}
		}
		return this;
	}
	
	
	public Matrix multiplyNumber(double num) {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				data[i][j] *= num;
			}
		}
		return this;
	}
	
	
	public Matrix divideNumber(double num) {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				data[i][j] /= num;
			}
		}
		return this;
	}
	
	public static Matrix matrixPlus(Matrix m1, Matrix m2) {
		if(m1.size[0] != m2.size[0] || m1.size[1] != m2.size[1]) {
			System.err.println("Matrix size not match!");
			System.err.println("A : " + m1.size[0] + "x" + m1.size[1]);
			System.err.println("B : " + m2.size[0] + "x" + m2.size[1]);
			return null;
		}
		int a = m1.size[0], b = m1.size[1];
		double[][] result = new double[a][b];
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				result[i][j] = m1.data[i][j] + m2.data[i][j];
			}
		}
		return new Matrix(result);
	}
	
	public static Matrix matrixProduct(Matrix m1, Matrix m2) {
		if(m1.size[1] != m2.size[0]) {
			System.err.println("Matrix size not match!");
			System.err.println("A : " + m1.size[0] + "x" + m1.size[1]);
			System.err.println("B : " + m2.size[0] + "x" + m2.size[1]);
			return null;
		}
		int a = m1.size[0], b = m2.size[1];
		double[][] result = new double[a][b];
		for(int i = 0; i < a; i++) {
			for(int j = 0; j < b; j++) {
				result[i][j] = 0;
				for(int r = 0; r < m2.data.length; r++) {
					result[i][j] += m1.data[i][r] * m2.data[r][j];
				}
			}
		}
		return new Matrix(result);
	}
	
}
