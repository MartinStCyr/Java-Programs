

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

	//Menu Options
	private String[] options = {"Play", "Editor", "Options", "Credit"};
	//Current menu option
	private int currentChoice = 0;
	
	//Menu index
	private int interfaceIndex = 0;
	
	//Menu Fonts
	private Font selectedMenuFont;
	private Font normalFont;
	
	//BackGround Image
	private BackGround backGroundImage;
	
	//Constructor for the menu State
	public MenuState()
	{
		normalFont = new Font("Century Gothic", Font.PLAIN, 18);
		selectedMenuFont = new Font("Arial", Font.BOLD, 20);
		backGroundImage = new BackGround("Background.png");
	}
	
	//Initialization method
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	//Update the menu
	@Override
	public void update(long elapsedTime) {
		keyPressed();
		keyReleased();
	}

	//Draw the menu
	@Override
	public void draw(Graphics2D g) {
		backGroundImage.draw(g);
		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, GamePanel.width, GamePanel.height);
		g.setColor(Color.BLACK);
		for(int i=0; i < options.length; i++)
		{
			if(i==currentChoice)
			{
				g.setFont(selectedMenuFont);
			}
			else
			{
				g.setFont(normalFont);
			}
			g.drawString(options[i], GamePanel.width/2 - 50, GamePanel.height/3 + i*30);
		}
	}

	//Check if some keys are pressed
	@Override
	public void keyPressed() {
		GameStateManager gsm = GameStateManager.getInstance();
		//If the UP Arrow is pressed
		if(gsm.isKeyPressed(KeyEvent.VK_UP))
		{
			//Change the actual choice
			currentChoice--;
			//Make sure that the actual choice go back to the last choice if you go above the first one
			if(currentChoice <0)
			{
				currentChoice = options.length-1;
			}
		}
		//If the down arrow is pressed
		if(gsm.isKeyPressed(KeyEvent.VK_DOWN))
		{
			currentChoice = (currentChoice+1)%(options.length);
		}
		
		//If ENTER is pressed, we select the new state to affect
		if(gsm.isKeyPressed(KeyEvent.VK_ENTER))
		{
			if(currentChoice == 0)
			{
				gsm.setState(GameStateManager.CHARACTERSELECTSTATE);
			}
			if(currentChoice == 1)
			{
				gsm.setState(GameStateManager.EDITORSTATE);
			}
		}
		
	}

	//If a key is released
	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub
	}

}
