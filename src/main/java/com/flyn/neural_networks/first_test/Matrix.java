package com.flyn.neural_networks.first_test;

import java.util.Random;

public class Matrix {
	
	private final int row, column;
	private double[][] data;
	
	public Matrix(int row, int column) {
		this.data = new double[row][column];
		this.row = row;
		this.column = column;
	}
	
	public Matrix(double[][] data) {
		this.data = data;
		this.row = data.length;
		this.column = data[0].length;
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public double[][] getData() {
		return data;
	}
	
	public void setData(double[][] data) {
		if(data.length != row && data[0].length != column) {
			printNotMatchError(this, new Matrix(data));
		}
		this.data = data;
	}
	
	public double getData(int a, int b) {
		return data[a][b];
	}
	
	public void setData(int a, int b, double data) {
		this.data[a][b] = data;
	}
	
	public void addData(int a, int b, double data) {
		this.data[a][b] += data;
	}
	
	public Matrix fillOne() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				data[i][j] = 1;
			}
		}
		return this;
	}
	
	public Matrix fillRandomData() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				data[i][j] = new Random().nextDouble();
			}
		}
		return this;
	}
	
	public Matrix fillGaussianData() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				data[i][j] = new Random().nextGaussian();
			}
		}
		return this;
	}
	
	public void printData() {
		System.out.print("[");
		for(int i = 0; i < row;) {
			System.out.print("[");
			for(int j = 0; j < column;) {
				System.out.print(data[i][j]);
				j++;
				if(j < column) System.out.print(", ");
				else System.out.print("]");
			}
			i++;
			if(i < row) System.out.println();
		}
		System.out.println("]");
	}
	
	public void printData(int a, int b) {
		System.out.println(data[a][b]);
	}
	
	public void printSize() {
		System.out.println(row + "x" + column);
	}
	
	@Override
	public String toString() {
		return new String("Matrix [" + row + "x" + column + "]");
	}
	
	public Matrix plusNumber(double num) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				data[i][j] += num;
			}
		}
		return this;
	}
	
	
	public Matrix minusNumber(double num) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				data[i][j] -= num;
			}
		}
		return this;
	}
	
	
	public Matrix multiplyNumber(double num) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				data[i][j] *= num;
			}
		}
		return this;
	}
	
	
	public Matrix divideNumber(double num) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				data[i][j] /= num;
			}
		}
		return this;
	}
	
	public static Matrix matrixPlus(Matrix m1, Matrix m2) {
		if(m1.row != m2.row || m1.column != m2.column) {
			printNotMatchError(m1, m2);
			return null;
		}
		double[][] result = new double[m1.row][m1.column];
		for(int i = 0; i < m1.row; i++) {
			for(int j = 0; j < m1.column; j++) {
				result[i][j] = m1.data[i][j] + m2.data[i][j];
			}
		}
		return new Matrix(result);
	}
	
	public static Matrix matrixMinus(Matrix m1, Matrix m2) {
		if(m1.row != m2.row || m1.column != m2.column) {
			printNotMatchError(m1, m2);
			return null;
		}
		double[][] result = new double[m1.row][m1.column];
		for(int i = 0; i < m1.row; i++) {
			for(int j = 0; j < m1.column; j++) {
				result[i][j] = m1.data[i][j] - m2.data[i][j];
			}
		}
		return new Matrix(result);
	}
	
	public static Matrix matrixProduct(Matrix m1, Matrix m2) {
		if(m1.column != m2.row) {
			printNotMatchError(m1, m2);
			return null;
		}
		double[][] result = new double[m1.row][m2.column];
		for(int i = 0; i < m1.row; i++) {
			for(int j = 0; j < m2.column; j++) {
				result[i][j] = 0;
				for(int r = 0; r < m2.data.length; r++) {
					result[i][j] += m1.data[i][r] * m2.data[r][j];
				}
			}
		}
		return new Matrix(result);
	}
	
	public static Matrix reverseMatrix(Matrix m) {
		double[][] result = new double[m.column][m.row];
	    for(int i = 0; i < m.column; i++) {
	        for(int j = 0; j < m.row; j++) {
	            result[i][j] = m.getData(j, i);
	        }
	    }
	    return new Matrix(result);
	}
	
	private static void printNotMatchError(Matrix m1, Matrix m2) {
		System.err.println("Matrix size not match!");
		System.err.println("A : " + m1.row + "x" + m1.column);
		System.err.println("B : " + m2.row + "x" + m2.column);
	}
	
}
