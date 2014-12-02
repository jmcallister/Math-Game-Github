import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JPanel
{	
	private static int xPosition = 50;
	private static int yPosition = 50;
		
	// GUI elements:
	static GUI content;
	
	// Horizontal is y coordinate of left and right doors,
	// vertical is x coordinate of up and down doors
	private static int horizontalDoorPosition;
	private static int verticalDoorPosition;
	
	// Starting point:
	private static Coordinate<Integer> location = new Coordinate<Integer>(3, 3);
	
	public static void main(String[] args)
	{			
		Dungeon.create(10, 10);
		
		content = new GUI();
		
		MenuListener menuListener = new MenuListener();
		
		JMenu optionsMenu = new JMenu("Options");
			
			JMenuItem reset = new JMenuItem("Reset");
			reset.addActionListener(menuListener);
			optionsMenu.add(reset);
		
		JMenuBar menuBar = new JMenuBar();
			menuBar.add(optionsMenu);
			
		JFrame window = new JFrame("Dungeon");
		window.add(content);
		window.setSize(500, 500);
		window.setLocation(15, 15);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
		window.setJMenuBar(menuBar);
		
		thisRoom().createWalls(content.getWidth(), content.getHeight());
	}
	
	private static class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			content.repaint();
		}
	}
	
	public GUI()
	{
		addMouseListener(new MouseAdapter()
		{
	        public void mousePressed(MouseEvent evt)
	        {
	            requestFocus();
	        }
		});

		addKeyListener(new KeyAdapter()
		{
	    	public void keyPressed(KeyEvent evt)
	    	{
	            int code = evt.getKeyCode();  // Which key was pressed?
	    		
				// starts at current position until changed:
				int xFuturePosition = xPosition;
				int yFuturePosition = yPosition;
							
				/*
				 *  MOVEMENT
				 */
				
				if (code == KeyEvent.VK_LEFT) xFuturePosition = xPosition - 5;
				else if (code == KeyEvent.VK_RIGHT) xFuturePosition = xPosition + 5;
				else if (code == KeyEvent.VK_UP) yFuturePosition = yPosition - 5;
				else if (code == KeyEvent.VK_DOWN) yFuturePosition = yPosition + 5;
				
				if (thisRoom().collideAny(xFuturePosition, yFuturePosition, 20, 20)) return;
				else
				{
					xPosition = xFuturePosition;
					yPosition = yFuturePosition;
				}
				
				
				/*
				 *  GOING THROUGH DOORS
				 */
	    		
	    		horizontalDoorPosition = (content.getHeight() / 2) - 15;
				verticalDoorPosition = (content.getWidth() / 2)  - 15;
				
	            // Case 1: left door
	            if (xPosition < 0
	            	&& yPosition > horizontalDoorPosition
	            	&& yPosition < horizontalDoorPosition + 30)
            	{	moveHorizontally(-1);	}
	            
	            
	            // Case 2: right door
	            if (xPosition > content.getWidth()
	            	&& yPosition > horizontalDoorPosition
	            	&& yPosition < horizontalDoorPosition + 30)
	            {	moveHorizontally(1);	}
	            
	            // Case 3: top door
	            if (yPosition < 0
	            	&& xPosition > verticalDoorPosition
	            	&& xPosition < verticalDoorPosition + 30)
	            {	moveVertically(1);		}
	            
	            // Case 4: bottom door
	            if (yPosition > content.getHeight()
	            	&& xPosition > verticalDoorPosition
	            	&& xPosition < verticalDoorPosition)
	            {	moveVertically(-1);		}
	            
	            repaint();
	        }
		});

	} // end GUI
		
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		drawWalls(g);
		
		g.setColor(Color.GREEN);
		g.fillOval(xPosition, yPosition, 20, 20);
	}
	
	// Movement methods:
	
	public static void moveVertically(int distance)
	{
		location.setY(location.getY() + distance);
		GUI.setCenter();
		thisRoom().createWalls(content.getWidth(), content.getHeight());
	}
	
	public static void moveHorizontally(int distance)
	{
		location.setX(location.getX() + distance);
		GUI.setCenter();
		thisRoom().createWalls(content.getWidth(), content.getHeight());
	}
	
	public void drawWalls(Graphics g)
	{
		// Clumsy, but you can't say it doesn't work
		// Iterates through walls, drawing each of them:
		
		for (int i = 0; i < thisRoom().barriers.size(); i++)
		{	
			g.fillRect(walls().get(i).getXPos(), walls().get(i).getYPos(),
					walls().get(i).getWidth(), walls().get(i).getHeight());
		}
	}
	
	/*******************************/
	// Utilities:
	
	public static void setCenter()
	{
		xPosition = (content.getWidth() / 2) - 10;
		yPosition = (content.getHeight() / 2) - 10;
	}
	
	public static ArrayList<Wall> walls()
	{
		return thisRoom().getWalls();
	}
	
	public static Room thisRoom()
	{
		return Dungeon.dungeon.get(location.getX()).get(location.getY());
	}
}
