import java.util.Scanner;

public class Quadratic
{
	static Scanner keyb = new Scanner(System.in);
	static boolean correct;
	static double a, b, c;
	
	
	/******************************************************************************/
	
	/*
	 * Utilities:
	 */
	
	/**
	 * @truncate
	 * removes all decimal places but two. 3.435 --> 3.43
	 */
	
	static double truncate(double input)
	{
		return ((int) (input * 100)) / 100.0;
	}
	
	/******************************************************************************/

	/*
	 * Solving Quadratics:
	 */
	
	static double getDiscriminant(double a, double b, double c)
	{
		return (b * b) - (4 * a * c);
	}
	
	static double getSol1(double a, double b, double c)
	{
		return ((b * -1) + Math.sqrt(getDiscriminant(a, b, c))) / (2 * a);
	}
	
	static double getSol2(double a, double b, double c)
	{
		return ((b * -1) - Math.sqrt(getDiscriminant(a, b, c))) / (2 * a);
	}
	
	/******************************************************************************/
	
	/*
	 * Types of equations. These methods define the a, b, and c coefficients.
	 */
	
	static void generateRandomEquation()
	{
		a = ((int) (Math.random() * 20)) * 1.0;
		b = ((int) (Math.random() * 20)) * 1.0;
		c = ((int) (Math.random() * 20)) * 1.0;
		if (a == 0) generateRandomEquation();
	}
	
	/**
	 * @rootEquation
	 * format is (xCoef1x + root1)(xCoef2x + root2)
	 */
	
	static void generateRootEquation()
	{
		int root1 = (int) (Math.random() * 10 * Math.pow(-1, (int) (Math.random() * 2)));
		int root2 = (int) (Math.random() * 10 * Math.pow(-1, (int) (Math.random() * 2)));
		// calculates the coefficients with an exponential distribution (more 1s, less 5s, etc.)
		int xCoef1 = (int) (Math.pow(2, Math.random() * 4));
		int xCoef2 = (int) (Math.pow(2, Math.random() * 4));
		// foil that shit:
		a = xCoef1 * xCoef2;
		b = (xCoef1 * root2) + (xCoef2 * root1);
		c = root1 * root2;
	}
	
	/******************************************************************************/
	
	static void equation()
	{
		//if (Math.random() > 0.5) generateRandomEquation();
		//else
		generateRootEquation();
		
		
		System.out.println(getSol1(a, b, c));
		System.out.println(getSol2(a, b, c));
		System.out.println("Solve. If no real solutions, "
				+ "write 'no real solutions.' "
				+ "Give answer to nearest hundredths, using truncation.");
		System.out.println(a + "x^2 " + b + "x " + c);
		
			correct = false;
			
			if (getDiscriminant(a, b, c) < 0)
			{
				if (keyb.nextLine().equals("no real solutions")) correct = true;
			}
			if (getDiscriminant(a, b, c) == 0)
			{
				if (Double.parseDouble(keyb.nextLine()) == truncate(getSol1(a, b, c))) correct = true;
			}
			if (getDiscriminant(a, b, c) > 0)
			{
				double input1 = Double.parseDouble(keyb.nextLine());
				double input2 = Double.parseDouble(keyb.nextLine());
				if (input1 == truncate(getSol1(a, b, c))
					&&	input2 == truncate(getSol2(a, b, c)))
					correct = true;
				if (input2 == truncate(getSol1(a, b, c))
					&&	input1 == truncate(getSol2(a, b, c)))
					correct = true;
			}
	}
}
