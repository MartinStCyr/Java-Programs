
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapRectangleObject extends MapObject{

	private BufferedImage objectImage;
	
	//Initialize a rectangle object which can be part of the map
	public MapRectangleObject(String fileName, boolean isSolid)
	{
		this.fileName = fileName;
		this.isSolid = isSolid;
		
		try {
			objectImage = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MapRectangleObject()
	{
		this.isSolid = true;
		this.fileName = "";
	}
	
	//Update the tile (in case we have an animated rectangle tile
	@Override
	public void update(long elapsed) {
		//Only used in derived class
	}

	//Draw the image of the tile
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawImage(objectImage, posX, posY, null);
	}
	
	public BufferedImage getImage()
	{
		return objectImage;
	}

}
