# Neural Networks
## **package neural_networks.first_test**
> ## **class NeuralNetworks**
>> public class NeuralNetworks
>> ### Field
>> * **layerLenth**
>> private final int layerLenth
>> 紀錄類神經網路層數
>> * **weights**
>> private ArrayList<Matrix> weights
>> 紀錄每個節點到下一個節點所需的權重
>> * **biases**
>> private ArrayList<Matrix> biases
>> 紀錄每個節點到下一個節點所需的偏移量
>> ### Constructor
>> * **NeuralNetworks**
>> public NeuralNetworks(int[] layer_sizes)
>> layer_sizes 輸入類神經網路需要的節點數和層數
>> ex: new int[] {3, 5, 7, 10} 輸入層節點數3 第一層隱藏層節點數5 第2層隱藏層節點數7 輸出層節點數10
>> ### Method
>> * **predict**
>> public Matrix predict(Marix data)
>> 輸入對應的資料，產生預測結果
>> * **getWeight**
>> public ArrayList<Matrix> getWeight()
>> 取得所有權重
>> * **getBias**
>> public ArrayList<Matrix> getBias()
>> 取得所有偏移量
>> * **activation**
>> private double activation(double x)
>> 活化函數：sigmoid function
> ## **class Matrix**
>> public class Matrix
>> ### Field
>> * **size**
>> public final int[] size
>> 矩陣大小
>> * **data**
>> private double[][] data
>> 矩陣資料
>> ### Constructor
>> * **Matrix**
>> public Matrix(int a, int b)
>> 輸入兩個數字設定矩陣大小，矩陣資料全為0
>> * **Matrix**
>> public Matrix(double[][] data)
>> 輸入資料設定矩陣
>> ### Mathod
>> * **fillOne**
>> public Matrix fillOne()
>> 將所有資料設定為1
>> * **fillRandomData**
>> public Matrix fillRandomData()
>> 將所有資料設定為隨機的倍精準浮點數
>> * **fillGaussianData**
>> public Matrix fillGaussianData()
>> 將所有資料設定為常態分布的隨機倍精準浮點數
>> * **plusNumber**
>> public Matrix plusNumber(double num)
>> 將所有資料加上num
>> * **minusNumber**
>> public Matrix minusNumber(double num)
>> 將所有資料減上num
>> * **multiplyNumber**
>> public Matrix multiplyNumber(double num)
>> 將所有資料乘上num
>> * **divideNumber**
>> public Matrix divideNumber(double num)
>> 將所有資料除上num
>> * **getData**
>> public double[][] getData()
>> 取得所有資料
>> * **printData**
>> public void printData()
>> 打印所有資料
>> * **printData**
>> pulbic void printData(int a, int b)
>> 打印位於[a][b]的資料
>> * **printSize**
>> public void printSize()
>> 打印矩陣大小
>> * **matrixPlus**
>> public static Matrix matrixPlus(Matrix m1, Matrix m2)
>> 矩陣相加 m1 + m2
>> * **matrixProduct**
>> public static Matrix matrixProduct(Matrix m1, Matrix m2)
>> 矩陣相乘 m1 x m2
