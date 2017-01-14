
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args)
	{
		GamePanel myPanel = new GamePanel();
		//Create the window
		JFrame window = new JFrame("Fantasy Battle");
		window.setResizable(false);
		//Set the GamePanel as the window's content
		window.setContentPane(myPanel);
		//Set the window size to the gamePanel preferred Size
		window.pack();
		//Set the window visible
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
}
