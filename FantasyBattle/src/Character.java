
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Character {

	//This is the sprite on which all animations are represented
	protected BufferedImage characterSprite;
	
	//The animation that is being casted
	protected Animation animation;
	//The position on the map where the character is Left-Right, Bottom-Top
	protected int posX, posY;
	
	protected boolean facingRight = true;
	
	public abstract void update(long elapsedTime);
	
	public void setFacingRight(boolean facingRight)
	{
		this.facingRight = facingRight;
	}
	public void setPosition(int x, int y)
	{
		posX = x;
		posY = y;
	}
	public void draw(Graphics2D g)
	{
		if(facingRight)
		{
			g.drawImage(animation.getImage(),posX,posY, null);
		}
		else
		{
			g.drawImage(animation.getImage(),posX + animation.getImage().getWidth(), posY, -animation.getImage().getWidth(),animation.getImage().getHeight(),null);
		}
	}
}
