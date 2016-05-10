/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tc2011.proyectofinal;

import java.util.function.*;
import java.util.stream.*;

/**
 *
 * @author r2d2a
 */
public class Helpers
{
	public static double sum(int startInclusive, int endExclusive, IntToDoubleFunction function)
	{
		return IntStream.range(startInclusive, endExclusive).mapToDouble(function).sum();
	}
	
	public static double[][] transposeMatrix(double[] m)
	{
		double[][] temp = new double[1][m.length];
		System.arraycopy(m, 0, temp[0], 0, m.length);
		return temp;
	}

	public static double[][] transposeMatrix(double[][] m)
	{
		double[][] temp = new double[m[0].length][m.length];
		for (int i = 0; i < m.length; i++)
		{
			for (int j = 0; j < m[0].length; j++)
			{
				temp[j][i] = m[i][j];
			}
		}
		return temp;
	}

	public static double[] multMatrix(double a[][], double b[])
	{
		if (a.length == 0)
		{
			return new double[0];
		}
		if (a[0].length != b.length)
		{
			throw new RuntimeException();
		}
		int n = a[0].length;
		int m = a.length;

		double ans[] = new double[m];

		for (int i = 0; i < m; i++)
		{
			ans[i] = 0;
			for (int k = 0; k < n; k++)
			{
				ans[i] += a[i][k] * b[k];
			}
		}
		return ans;
	}

	public static double[][] multMatrix(double a[][], double b[][])
	{
		//a[m][n], b[n][p]
		if (a.length == 0)
		{
			return new double[0][0];
		}
		if (a[0].length != b.length)
		{
			throw new RuntimeException("Matrix size mismatch");
		}
		int n = a[0].length;
		int m = a.length;
		int p = b[0].length;

		double ans[][] = new double[m][p];

		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < p; j++)
			{
				ans[i][j] = 0;
				for (int k = 0; k < n; k++)
				{
					ans[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return ans;
	}

	public static double[][] multMatrix(double a[], double b[][])
	{
		if (a.length == 0)
		{
			return new double[0][0];
		}
		if (1 != b.length)
		{
			throw new RuntimeException("Matrix size mismatch");
		}
		int n = 1;
		int m = a.length;
		int p = b[0].length;

		double ans[][] = new double[m][p];

		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < p; j++)
			{
				ans[i][j] = a[i] * b[0][j];
			}
		}
		return ans;
	}
	
	public static double[][] addMatrix(double[][] a, double[][] b)
	{
		if (a.length != b.length || a[0].length != b[0].length)
		{
			throw new RuntimeException("Matrix size mismatch");
		}

		double ans[][] = new double[a.length][a[0].length];

		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[0].length; j++)
			{
				ans[i][j] = a[i][j] + b[i][j];
			}
		}
		return ans;
	}
	
	public static double[] pointMult(double[] a, double[] b)
	{
		if (a.length != b.length)
		{
			throw new RuntimeException("Matrix size mismatch");
		}
		
		double[] result = new double[a.length];
		
		for (int i = 0; i < a.length; i++)
		{
			result[i] = a[i] * b[i];
		}
		
		return result;
	}
}
