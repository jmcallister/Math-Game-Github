
public class GiveEquation
{
	static boolean correct;
	
	static void give()
	{
		while (true)
		{
			try
			{
			Quadratic.equation();
			correct = Quadratic.correct;
			if (correct) System.out.println("Correct.");
			else System.out.println("Incorrect.");
			}
			catch (Exception e)
			{
				System.out.println("Please enter a valid number.");
			}
		}
	}
	
	public static void main(String[] args)
	{
		give();
	}
}
