
public class Wall extends Barrier
{	
	public Wall(Room room, int x, int y, int width, int height)
	{	
		super(room, x, y, width, height);
		
		id = "Wall";
	}
	
	// Parameters are coordinates of object that might be
	// colliding with the wall
	public boolean collide(int xPos, int yPos, int xSize, int ySize)
	{
		// if object in question is inside wall
		if (xPos + xSize > x	// must account for size of object
			&& xPos < x + width
			&& yPos + ySize > y	// accounts for size of object
			&& yPos < y + height)
			return true;
		
		else return false;
	}
	
	// xPos is how far down the opening should be,
	// length is how tall
	public void createVerticalOpening(int xPos, int length)
	{	
		// creates a new wall from the opening down
		new Wall(room, x, y + xPos + length,
				this.width, this.height - xPos - length);
		
		// shrinks this wall to the upper half
		this.height = xPos;
	}
	
	public void createHorizontalOpening(int yPos, int length)
	{
		// same as with HorizontalOpening:
			
		new Wall(room, x + yPos + length, y,
				this.width - yPos - length, this.height);
		
		this.width = yPos;
	}
}
