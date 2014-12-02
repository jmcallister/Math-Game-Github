
public abstract class Barrier
{	
	protected String id;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected Room room;
	
	public Barrier(Room room, int x, int y, int width, int height)
	{	
		this.room = room;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		room.barriers.add(this);
	}
	
	/********************************/
	// "Get" methods:
	
	public int getXPos()
	{
		return x;
	}
	
	public int getYPos()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	/********************************/

	public abstract boolean collide(int xPos, int yPos,
									int xSize, int ySize);
	
	
}
