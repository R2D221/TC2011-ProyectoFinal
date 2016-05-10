/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tc2011.proyectofinal;

import java.util.*;
import static tc2011.proyectofinal.Helpers.*;

/**
 *
 * @author r2d2a
 */
public class NeuralNetwork
{
	private final double[] a1;
	private final double[] a2;
	private final double[] a3;
	private double[] z2;
	private double[] z3;
	public final double[][] Theta1;
	public final double[][] Theta2;

	public NeuralNetwork(int input_size, int hidden_size, int output_size)
	{
		a1 = new double[input_size + 1];
		a2 = new double[hidden_size + 1];
		a3 = new double[output_size];
		z2 = new double[hidden_size + 1];
		z3 = new double[output_size];
		Theta1 = new double[hidden_size][input_size + 1];
		Theta2 = new double[output_size][hidden_size + 1];
		randInitializeWeights();
	}
	
	private NeuralNetwork(double[][] Theta1, double[][] Theta2)
	{
		this.Theta1 = Theta1;
		this.Theta2 = Theta2;
		
		a1 = new double[Theta1[0].length];
		a2 = new double[Theta2[0].length];
		a3 = new double[Theta2.length];
		z2 = new double[Theta2[0].length];
		z3 = new double[Theta2.length];
	}

	public double[] feedForward(double[] x)
	{
		//Primera capa
		a1[0] = 1; // bias
		for (int i = 0; i < x.length; i++)
		{
			a1[i + 1] = x[i];
		}

		//Capa Intermedia
		z2 = multMatrix(Theta1, a1);
		a2[0] = 1; // bias
		for (int i = 0; i < z2.length; i++)
		{
			a2[i + 1] = g(z2[i]);
		}

		//Capa de salida
		z3 = multMatrix(Theta2, a2);
		for (int i = 0; i < z3.length; i++)
		{
			a3[i] = g(z3[i]);
		}
		
		return Arrays.copyOf(a3, a3.length);
	}

	public double rnFuncionCosto(/*double[][] Theta1, double[][] Theta2, */int input_layer_size, int hidden_layer_size, int num_labels, double[][] x, double[][] y, double lambda)
	{
		int m = x.length;
		int K = num_labels;

		double[][] Δ1 = null;
		double[][] Δ2 = null;
		
		double J_Θ = 0;
		
		for (int i = 0; i < x.length; i++)
		{
			feedForward(x[i]);
			
			double[] δ3 = new double[a3.length];
			for (int k = 0; k < a3.length; k++)
			{
				δ3[k] = (a3[k] - y[i][k]);
				J_Θ += -y[i][k] * Math.log(a3[k]) - (1 - y[i][k]) * Math.log(1 - a3[k]);
			}

			double[] δ2 = pointMult(removeFirst(multMatrix(transposeMatrix(Theta2), δ3)), sigmoidGradient(z2));
			if (Δ2 == null)
			{
				Δ2 = multMatrix(δ3, transposeMatrix(a2));
			}
			else
			{
				Δ2 = addMatrix(Δ2, multMatrix(δ3, transposeMatrix(a2)));
			}
			if (Δ1 == null)
			{
				Δ1 = multMatrix(δ2, transposeMatrix(a1));
			}
			else
			{
				Δ1 = addMatrix(Δ1, multMatrix(δ2, transposeMatrix(a1)));
			}
		}

		J_Θ = (J_Θ / m)
//			(1.0 / m)
//			*
//			sum(0, m, i ->
//				sum(0, K, k ->
//					-y[i][k] * Math.log(a3[k]) - (1 - y[i][k]) * Math.log(1 - a3[k]) // h_theta(x[i])[k] = a3[k]
//				)
//			)
			+
			(lambda / (2 * m))
			*
			(
				sum(0, Theta1.length, j ->
					sum(0, Theta1[0].length, k ->
						Math.pow(Theta1[j][k], 2)
					)
				)
				+
				sum(0, Theta2.length, j ->
					sum(0, Theta2[0].length, k ->
						Math.pow(Theta2[j][k], 2)
					)
				)
			);
		
		double[][] D1 = new double[Δ1.length][Δ1[0].length];
		for (int i = 0; i < D1.length; i++)
		{
			int j = 0;
			D1[i][j] = (1.0 / m) * Δ1[i][j];
			for (j = 1; j < D1[0].length; j++)
			{
				D1[i][j] = (1.0 / m) * Δ1[i][j] + (lambda / m) * Theta1[i][j];
			}
		}

		double[][] D2 = new double[Δ2.length][Δ2[0].length];
		for (int i = 0; i < D2.length; i++)
		{
			int j = 0;
			D2[i][j] = (1.0 / m) * Δ2[i][j];
			for (j = 1; j < D2[0].length; j++)
			{
				D2[i][j] = (1.0 / m) * Δ2[i][j] + (lambda / m) * Theta1[i][j];
			}
		}
		
		for (int i = 0; i < Theta1.length; i++)
		{
			for (int j = 0; j < Theta1[0].length; j++)
			{
				Theta1[i][j] = Theta1[i][j] - (D1[i][j]);
			}
		}
		
		for (int i = 0; i < Theta2.length; i++)
		{
			for (int j = 0; j < Theta2[0].length; j++)
			{
				Theta2[i][j] = Theta2[i][j] - (D2[i][j]);
			}
		}

		return J_Θ;
	}

	public static double g(double z)
	{
		return (1 / (1 + Math.exp(-z)));
	}

	public static double sigmoidGradient(double z)
	{
		return g(z) * (1 - g(z));
	}

	public static double[] sigmoidGradient(double[] z)
	{
		double[] result = new double[z.length];
		for (int i = 0; i < z.length; i++)
		{
			result[i] = sigmoidGradient(z[i]);
		}
		return result;
	}

	private void randInitializeWeights()
	{
		double parametro_e = 0.12;
		
		for (int i = 0; i < Theta1.length; i++)
		{
			for (int j = 0; j < Theta1[0].length; j++)
			{
				Theta1[i][j] = (Math.random() * 2 * parametro_e) - parametro_e;
			}
		}
		
		for (int i = 0; i < Theta2.length; i++)
		{
			for (int j = 0; j < Theta2[0].length; j++)
			{
				Theta2[i][j] = (Math.random() * 2 * parametro_e) - parametro_e;
			}
		}

	}
	
	public static int[] prediceRNYaEntrenada(double[][] x, double[][] Theta1, double[][] Theta2)
	{
		NeuralNetwork network = new NeuralNetwork(Theta1, Theta2);
		
		int[] y = new int[x.length];
		
		for (int i = 0; i < x.length; i++)
		{
			double[] result = network.feedForward(x[i]);
			
			double maxNumber = Double.NEGATIVE_INFINITY;
			int maxIndex = 0;
			for (int j = 0; j < result.length; j++)
			{
				if (result[j] > maxNumber)
				{
					maxNumber = result[j];
					maxIndex = j;
				}
			}
			y[i] = maxIndex + 1;
		}
		
		return y;
	}

}
