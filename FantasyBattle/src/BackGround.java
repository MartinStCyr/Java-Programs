

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround {
	private BufferedImage backGroundImage;
	
	private BackGround()
	{
		
	}
	
	public BackGround(String fileName)
	{
		try {
			backGroundImage = ImageIO.read(getClass().getResource(fileName));
		} catch (IOException e) {
			System.out.print("ERROR");
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(backGroundImage, 0, 0, GamePanel.width, GamePanel.height, null);
	}
}
