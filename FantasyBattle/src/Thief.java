
import java.awt.Graphics2D;

public class Thief extends Character{

	//animations index
	private static final int IDLE = 0;
	
	//Animation number of frames to prepare the animations
	private final int[] numFrames = {
		1	
	};
	
	public Thief()
	{
		
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
