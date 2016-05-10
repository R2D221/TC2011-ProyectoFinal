/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tc2011.proyectofinal;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author r2d2a
 */
public class TC2011ProyectoFinal
{

	static double[][] x;
	static List<double[]> lectura_ejemplos = new LinkedList<>();
	static double[][] y;
	static int[] y_labels;
	static List<double[]> lectura_pruebas = new LinkedList<>();
	static List<Integer> lectura_labels = new LinkedList<>();

	public static void main(String[] args) throws IOException
	{
		leerArchivoEjemplos();
		x = ListToArray(lectura_ejemplos);
		leerArchivoPruebas();
		y = ListToArray(lectura_pruebas);
		y_labels = ListToIntArray(lectura_labels);


		NeuralNetwork network = new NeuralNetwork(400, 25, 10);
		
		double cost = Double.POSITIVE_INFINITY;
		int iteraciones = 1;
		
		while (cost > 0.1)
		{
			cost = network.rnFuncionCosto(400, 25, 10, x, y, 0.01);
			if (iteraciones % 10 == 0)
			{
				System.out.println(iteraciones + " iteraciones");
				System.out.println(cost + " cost");
				System.out.println("----");
			}
			iteraciones++;
		}
		
		int[] resultado = NeuralNetwork.prediceRNYaEntrenada(x, network.Theta1, network.Theta2);
		
		double promedio = 0;
		
		for (int i = 0; i < y_labels.length; i++)
		{
			if (y_labels[i] == resultado[i])
			{
				promedio++;
			}
		}
		
		promedio = promedio / y_labels.length;
		
		System.out.format("Pasó el %s%% de pruebas", new DecimalFormat("#.##").format(promedio * 100));
	}

	public static void leerArchivoEjemplos() throws FileNotFoundException, IOException
	{
		try
		{
			FileInputStream fstream = new FileInputStream("X.txt");
			DataInputStream entrada = new DataInputStream(fstream);

			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String strLinea;
			String ejemplo[];
			while ((strLinea = buffer.readLine()) != null && !(strLinea.equals("")))
			{
				ejemplo = strLinea.substring(1, strLinea.length()).split(" ");
				double[] copia_ejemplo = new double[ejemplo.length];
				for (int i = 0; i < ejemplo.length; i++)
				{
					double a = Double.parseDouble(ejemplo[i]);
					copia_ejemplo[i] = Double.parseDouble(ejemplo[i]);
				}
				lectura_ejemplos.add(copia_ejemplo);
			}
			entrada.close();
		}
		catch (Exception e)
		{
			System.err.println("Ocurrio un error: " + e.getMessage());
		}
	}

	public static void leerArchivoPruebas() throws FileNotFoundException, IOException
	{
		try
		{
			FileInputStream fstream = new FileInputStream("y.txt");
			DataInputStream entrada = new DataInputStream(fstream);

			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String strLinea;
			while ((strLinea = buffer.readLine()) != null && !(strLinea.equals("")))
			{

				int ejemplo = Integer.parseInt(strLinea.substring(1, strLinea.length()));
				double[] digito = new double[10];

				digito[ejemplo - 1] = 1;

				lectura_pruebas.add(digito);
				lectura_labels.add(ejemplo);
			}
			entrada.close();
		}
		catch (Exception e)
		{
			System.err.println("Ocurrio un error: " + e.getMessage());
		}
	}

	public static double[][] ListToArray(List<double[]> lectura_ejemplos)
	{
		double[][] x = new double[lectura_ejemplos.size()][lectura_ejemplos.get(0).length];
		int i = 0;
		for (double[] a : lectura_ejemplos)
		{
			x[i] = a;
			i++;
		}
		return x;
	}

	public static int[] ListToIntArray(List<Integer> lectura_ejemplos)
	{
		int[] x = new int[lectura_ejemplos.size()];
		int i = 0;
		for (int a : lectura_ejemplos)
		{
			x[i] = a;
			i++;
		}
		return x;
	}

}
