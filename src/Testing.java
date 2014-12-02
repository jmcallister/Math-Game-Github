import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Testing extends JPanel
{	
	private static int xPosition = 50;
	private static int yPosition = 50;
		
	// Testing elements:
	static Testing content;
	
	// Horizontal is y coordinate of left and right doors,
	// vertical is x coordinate of up and down doors
	private static int horizontalDoorPosition;
	private static int verticalDoorPosition;
	
	static Wall TOP_WALL;
	
	public void createWalls(Graphics g)
	{
		TOP_WALL = new Wall(g, 50, 200, 100, 10);
		TOP_WALL.createVerticalOpening(10, 10);
	}
	
	public static void main(String[] args)
	{			
		Dungeon.create(10, 10);
		
		content = new Testing();
		
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
		window.setJMenuBar(menuBar);
	}
	
	private static class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			TOP_WALL.createVerticalOpening(10, 10);
		}
	}
	
	public Testing()
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
	    		
	    		horizontalDoorPosition = (content.getHeight() / 2) - 15;
				verticalDoorPosition = (content.getWidth() / 2)  - 15;
	    		
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
				
				if (Wall.collideAny(xFuturePosition, yFuturePosition, 20, 20)) return;
				else
				{
					xPosition = xFuturePosition;
					yPosition = yFuturePosition;
				}
				
	            repaint();
	        }
		});

	} // end Testing
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		renderRoom(g);
		
		g.setColor(Color.GREEN);
		g.fillOval(xPosition, yPosition, 20, 20);
	}
	
	public void setCenter()
	{
		xPosition = content.getWidth() / 2;
		yPosition = content.getHeight() / 2;
		repaint();
	}
	
	public void renderRoom(Graphics g)
	{	
	}
}
