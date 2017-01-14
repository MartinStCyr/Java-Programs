
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Orc extends Character{

	//animations index
	private static final int IDLE = 0;
	
	//Animation number of frames to prepare the animations
	private final int[] numFrames = {
		3	
	};
	
	public Orc()
	{
		BufferedImage tempImage;
		BufferedImage[] frameImage = new BufferedImage[numFrames[0]];
		
		try {
			tempImage = ImageIO.read(getClass().getResourceAsStream("Orc.gif"));
			frameImage[0] = tempImage.getSubimage(0, 0, 90, tempImage.getHeight());
			frameImage[1] = tempImage.getSubimage(90, 0, 90, tempImage.getHeight());
			frameImage[2] = tempImage.getSubimage(180, 0, 90, tempImage.getHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		animation = new Animation();
		animation.setFrames(frameImage);
		animation.setDelay(400);
	}
	
	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		
		
		//Update the animation
		animation.update();
	}

	@Override
	public void draw(Graphics2D g) {
		
		//Draw the character using the Character class
		super.draw(g);
		
	}

}
