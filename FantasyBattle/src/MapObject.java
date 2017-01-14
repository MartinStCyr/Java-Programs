
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class MapObject {

	//The position of the block
	protected int posX=0, posY=0;
	//The angle of the block
	protected double rotateAngle = 0;
	//The scale of the block
	protected double scaleX = 1;
	protected double scaleY = 1;
	//Determines if the block is selected (in case of an editor mode)
	protected boolean isSelected = false;
	protected String fileName = "";
	protected boolean isSolid = false;
	
	//Update the block if it's a gif
	public abstract void update(long elapsed);
	//Draw the block on the image
	public abstract void draw(Graphics2D g);
	
	public abstract BufferedImage getImage();
	
	//Move the block on the image (the position is relative to the virtual world (Negative Y is up)
	public void setPosition(int x, int y)
	{
		posX = x;
		posY = y;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public boolean getIsSolid()
	{
		return isSolid;
	}
	
	public boolean getIsSelected()
	{
		return isSelected;
	}
	
	public void setIsSelected(boolean selected)
	{
		this.isSelected = selected;
	}
	
	public int getPosX()
	{
		return posX;
	}
	
	public int getPosY()
	{
		return posY;
	}
	
}
