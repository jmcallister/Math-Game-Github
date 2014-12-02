import java.util.ArrayList;


public class Room
{
	private int xIndex;
	private int yIndex;
	
	private boolean[] doors = new boolean[4];
	public static final boolean OPEN = true;
	public static final boolean CLOSED = false;
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	private Wall topWall, bottomWall, rightWall, leftWall;
	public ArrayList<Barrier> barriers = new ArrayList<Barrier>();
	
	public Room(int x, int y)
	{
		xIndex = x;
		yIndex = y;
		
		for(int i = 0; i < 4; i++)
		{
			setConnection(i);
		}
	}
	
	public void setConnection(int direction)
	{
		 if(Math.random() > 0.3)
			this.doors[direction] = OPEN;
	}
	
	/*****************************/
	// get methods:
	
	public int getXIndex()
	{
		return xIndex;
	}
	
	public int getYIndex()
	{
		return yIndex;
	}
	
	public ArrayList<Wall> getWalls()
	{
		ArrayList<Wall> walls = new ArrayList<Wall>();
		
		for (int i = 0; i < barriers.size(); i++)
		{
			if (barriers.get(i).id.equals("Wall"))
				walls.add((Wall) (barriers.get(i)));
		}
		
		return walls;
	}
	
	/**************************/
	
	public boolean isConnected(int direction)
	{
		try
		{
			if (this.doors[direction] == OPEN			// if this room is open in direction
					&& this.getAdjacent(direction).		// if the adjacent room in direction
					doors[opposite(direction)] == OPEN)	// is open toward this room
				return true;
			else return false;
		}
		catch (IndexOutOfBoundsException e)
		{
			return false;
		}
	}
	
	// xIndex and yIndex = this.xIndex, this.yIndex
	public Room getAdjacent(int direction)
	{
		if (direction == UP)
			return Dungeon.dungeon.get(xIndex).get(yIndex + 1);
		
		else if (direction == DOWN)
			return Dungeon.dungeon.get(xIndex).get(yIndex - 1);
		
		else if (direction == RIGHT)
			return Dungeon.dungeon.get(xIndex + 1).get(yIndex);
		
		else if (direction == LEFT)
			return Dungeon.dungeon.get(xIndex - 1).get(yIndex);
		
		else return null;
	}
	
	/**************************/

	private int opposite(int direction)
	{
		switch (direction)
		{
		case UP: return DOWN;
		case DOWN: return UP;
		case LEFT: return RIGHT;
		case RIGHT: return LEFT;
		default: return -1;
		}
	}
	
	/***************************/
	
	public boolean collideAny(int xPos, int yPos, int xSize, int ySize)
	{
		for (int i = 0; i < this.barriers.size(); i++)
		{
			// iterates through each wall
			if (this.barriers.get(i).collide
				(xPos, yPos, xSize, ySize))
					return true;
		}
		
		return false;
	}
	
	/***************************/
	
	// Use content.getHeight() etc. to get width and height
	
	public void createWalls(int width, int height)
	
	{
		// Define four walls:
		
		topWall = new Wall(this, 0, 0, width, 10);
		leftWall = new Wall(this, 0, 0, 10, height);
		rightWall = new Wall(this, width - 10, 0, 10, height);
		bottomWall = new Wall(this, 0, height - 10, width, 10);
		
		// Create necessary doors:
			
		int horizontalDoorPosition = (height / 2) - 15;
		int verticalDoorPosition = (width / 2)  - 15;
				
		if (Dungeon.isLocationConnected(Room.UP))
			topWall.createHorizontalOpening(verticalDoorPosition, 30);
		
		if (Dungeon.isLocationConnected(Room.DOWN))
			bottomWall.createHorizontalOpening(verticalDoorPosition, 30);	
		
		if (Dungeon.isLocationConnected(Room.RIGHT))
			rightWall.createVerticalOpening(horizontalDoorPosition, 30);

		if (Dungeon.isLocationConnected(Room.LEFT))
			leftWall.createVerticalOpening(horizontalDoorPosition, 30);
		
	}
}