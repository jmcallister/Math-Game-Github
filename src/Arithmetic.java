import java.util.Scanner;

public class Arithmetic
{	
	static Scanner keyb = new Scanner(System.in);
	static int numberOne;
	static int numberTwo;
	static char operation;
	static boolean correct;
	
	
	/******************************************************************************/

	/*
	 * Utilities:
	 */

	static int convertOperator(int arg1, int arg2, char operator)
	{
		int output;
		switch(operator)
		{
		case '+':
			output = arg1 + arg2;
			break;
		case '-':
			output = arg1 - arg2;
			break;
		case '*':
			output = arg1 * arg2;
			break;
		case '/':
			output = arg1 / arg2;
			break;
		default:
			output = 0;
		}
		return output;
	}
	
	/******************************************************************************/

	/*
	 * Solving Equations:
	 */
	
	static int getAnswer(int numberOne, int numberTwo, char operationChar)
	{
		return convertOperator(numberOne, numberTwo, operationChar);
	}
	
	/******************************************************************************/
	
	static char randomOperation()
	{
		char[] operations = {'+', '-', '/', '*'};
		int random = (int) (Math.random() * 4);
		char result = operations[random];
		return result;
	}
	
	static void generateEquation()
	{
		numberOne = (int) (Math.random() * 100);
		numberTwo = (int) (Math.random() * 100);
		operation = randomOperation();
	}
	
	/******************************************************************************/
	
	static void equation()
	{
		generateEquation();
		System.out.println(numberOne + " " + operation + " " + numberTwo + " = ?");
		int userAnswer = Integer.parseInt(keyb.nextLine());
		if(userAnswer == getAnswer(numberOne, numberTwo, operation)) correct = true;
		else correct = false;
	}
}

