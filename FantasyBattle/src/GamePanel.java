
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseMotionListener, MouseListener, MouseWheelListener{

	//The game panel window size
	public static int width = 800;
	public static int height = 600;
	
	//Game status
	private boolean isRunning = false;
	
	//Game thread
	private Thread thread;
	//Set the number of frames per second
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	//The image on the panel
	private BufferedImage image;
	private Graphics2D g;
	
	//The game state manager
	private GameStateManager gsm;
	
	//Constructor for GamePanel
	public GamePanel()
	{
		super();
		
		//Tells the window size
		setPreferredSize(new Dimension(800,600));
		setFocusable(true);
		requestFocus();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		setDoubleBuffered(true);
	}
	
	@Override
	public void run() {
		
		//Initialize the GamePanel
		init();
		
		//Timers used to determine how long since last frame has passed
		long start;
		long elapsed = 0;
		long wait;
		
		//Game Loop
		while(isRunning)
		{
			start = System.nanoTime();
			
			//Update the game logic and inputs
			update(elapsed);
			//Time between 2 frames
			//System.out.print("Elapsed : " + elapsed/1000000 + "\n");
			//Draw the components into an image
			draw();
			//Draw the image into the JPanel
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			if(targetTime - elapsed/1000000 > 0)
			{
				try {
					Thread.sleep(targetTime - elapsed/1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				};
			}
			elapsed = System.nanoTime() - start;
		}
	}
	
	//Allow the thread to start
	public void addNotify() 
	{
		super.addNotify();
		if(thread == null)
		{
			thread = new Thread(this);
			thread.start();
		}
	}
	
	//Function used to initialize the gamePanel stuff
	private void init()
	{
		
		isRunning = true;
		gsm = GameStateManager.getInstance();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}
	
	//Function used to update the Game Panel
	private void update(long elapsedTime)
	{
		gsm.update(elapsedTime);
	}
	
	//Function used to Draw the Logic to an image
	private void draw()
	{
		gsm.draw(g);
	}
	
	//Function used to Draw the Image to the Screen
	private void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image,  0, 0, width, height, null);
		g2.dispose();
	}
	
	public Graphics getPanelGraphics()
	{
		return getGraphics();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		gsm.mousePressed(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		gsm.mouseReleased(arg0);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		gsm.mouseMoved(arg0);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		gsm.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		gsm.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		gsm.keyTyped(arg0);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		gsm.mouseWheelMoved(arg0);
		//System.out.print(arg0.getWheelRotation()+"\n");
	}

	
}
