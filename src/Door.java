
public class Door extends Barrier
{	
	private boolean open;
	
	public Door(Room room, int x, int y, int width, int height)
	{
		super(room, x, y, width, height);
		
		open = true;
	}
	
	public boolean collide(int xPos, int yPos, int xSize, int ySize)
	{
		if (!open) return false;
		else
		{
			// Same code from Wall:
			if (xPos + xSize > x
				&& xPos < x + width
				&& yPos + ySize > y
				&& yPos < y + height)
				return true;
			
			else return false;
		}
	}
	
	public void setDoor(boolean state)
	{
		open = state;
	}
}
