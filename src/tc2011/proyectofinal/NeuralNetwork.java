/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tc2011.proyectofinal;

import static tc2011.proyectofinal.Helpers.sum;

/**
 *
 * @author r2d2a
 */
public class NeuralNetwork
{
	private final double[] a1;
	private final double[] a2;
	private final double[] a3;
	private final double[][] Theta1;
    private final double[][] Theta2;
	
	public NeuralNetwork(int input_size, int hidden_size, int output_size)
	{
		a1 = new double[input_size + 1];
		a2 = new double[hidden_size + 1];
		a3 = new double[output_size];
		Theta1 = new double[hidden_size][input_size + 1];
		for (int i = 0; i < hidden_size; i++)
		{
			Theta1[i][input_size] = 1;
		}
        Theta2 = new double[output_size][hidden_size + 1];
		for (int i = 0; i < output_size; i++)
		{
			Theta2[i][hidden_size] = 1;
		}
	}
	
	//public static 
	
	public double rnFuncionCosto(double[][] Theta1, double[][] Theta2, int input_layer_size, int hidden_layer_size, int num_labels, double[][] x, double[][] y, double lambda)
	{
		int m = x.length;//input_layer_size;
		int K = num_labels;
		
		double J_Θ =
			(1.0 / m) *
			sum(0, m, i ->
				sum(0, K, k ->
					-y[i][k] * Math.log(a3[k]) - (1 - y[i][k]) * Math.log(1 - a3[k]) // h_theta(x[i])[k] = a3[k]
				)
			)
			+
			(lambda / (2*m)) *
			(
				sum(0, hidden_layer_size, j ->
					sum(0, input_layer_size, k ->
						Math.pow(Theta1[j][k], 2)
					)
				)
				+
				sum(0, num_labels, j ->
					sum(0, hidden_layer_size, k ->
						Math.pow(Theta2[j][k], 2)
					)
				)
			);
		
		public void feedForward(double[] a1,double[] a2, double[] a3, double[][] x){

        	//Primera capa
        	for(int i =0; i < x.length; i++){
            	for(int j =0; j < a1.length; j++){
                	a1[j] = x[i][j];
            	}
        	}
        
        	//Capa Intermedia
        	double z2[] = multMatrix(Theta1,a1);
        	for(int j=0; j < z2.length; j++){
                a2[j] = g(z2[j]);
        	}
        
        	//Capa de salida
        	double z3[] = multMatrix(Theta2,a2);
        	for(int j=0; j < z3.length; j++){
            	a3[j] = g(z3[j]);
        	}
        
    	}
		
		for (int i = 0; i < x.length; i++)
		{
			double[] δ3 = new double[a3.length];
			
			for (int k = 0; k < a3.length; k++)
			{
				δ3[k] = (a3[k] - y[i][k]);
			}
		}
		
		return 0;
	}

	public static double g(double z){
            return (1 / (1 + Math.exp(-z)));
    }

	public static double sigmoidGradient(double z){
        return g(z) * (1 - g(z));
    }
    
    public static void randInitializeWeights(int input_size, double[] L_int, int output_size, double[] L_out){       
 
        double parametro_e = 0.12;
 
        for(int i = 0; i < input_size; i++){
            L_int[i] = (Math.random() * 2 * parametro_e) - parametro_e;
        }
        
        for(int j = 0; j < output_size; j++){
            L_int[j] = (Math.random() * 2 * parametro_e)- parametro_e;
        }
 
    } 
	
}
