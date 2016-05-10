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

	public static double[][] multMatrix(double a[][], double b[][])
	{
		//a[m][n], b[n][p]
		if (a.length == 0)
		{
			return new double[0][0];
		}
		if (a[0].length != b.length)
		{
			throw new RuntimeException();
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
}
