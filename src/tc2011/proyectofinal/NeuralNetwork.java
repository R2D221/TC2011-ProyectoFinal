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
	public static double rnFuncionCosto(double[][] Theta1, double[][] Theta2, int input_layer_size, int hidden_layer_size, int num_labels, double[][] x, double[][] y, double lambda)
	{
		int m = input_layer_size;
		int K = num_labels;
		
		return
			(1.0 / m) *
			sum(0, m, i ->
				sum(0, K, k ->
					-y[i][k] * Math.log(h_theta(x[i])[k]) - (1 - y[i][k]) * Math.log(1 - h_theta(x[i])[k])
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
			)
		;
	}
	
}
