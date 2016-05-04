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
}
