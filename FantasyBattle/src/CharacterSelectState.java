

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class CharacterSelectState extends GameState{
	private BackGround background;
	private Character selectedCharacter;
		
	public CharacterSelectState()
	{
		background = new BackGround("CharacterSelectBackground.png");
		selectedCharacter = new Orc();
		selectedCharacter.setPosition(GamePanel.width/2 , GamePanel.height/2);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		selectedCharacter.update(elapsedTime);
		keyPressed();
		keyReleased();
	}

	@Override
	public void draw(Graphics2D g) {
		//Draw background
		background.draw(g);
		
		selectedCharacter.draw(g);
	}

	@Override
	public void keyPressed() {
		// TODO Auto-generated method stub
		GameStateManager gsm = GameStateManager.getInstance();
		if(gsm.isKeyPressed(KeyEvent.VK_LEFT))
		{
			selectedCharacter.setFacingRight(false);
		}
		if(gsm.isKeyPressed(KeyEvent.VK_RIGHT))
		{
			selectedCharacter.setFacingRight(true);
		}
	}

	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub
		
	}

}
