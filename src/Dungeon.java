import java.util.ArrayList;

public class Dungeon
{	
	static ArrayList<ArrayList<Room>> dungeon;

	public static void create(int xsize, int ysize)
	{
		dungeon = new ArrayList<ArrayList<Room>>(0);
				
		for(int i = 0; i < xsize; i++)
		{
			dungeon.add(new ArrayList<Room>(0));
			
			for(int j = 0; j < ysize; j++)
				dungeon.get(i).add(new Room(i, j));
			
		}
	}
	
	/**************************/
	
	public static boolean isLocationConnected(int direction)
	{
		if (GUI.thisRoom().isConnected(direction)) return true;
		else return false;
	}
}
