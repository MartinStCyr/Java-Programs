
import java.awt.image.BufferedImage;

public class Animation {

	//Image list
	private BufferedImage[] frames;
	//The actual animation image
	private int currentFrame;
	
	//The time at which the animation started
	private long startTime;
	//Time between each frame
	private long delay;
	//Boolean telling if the animation is over
	private boolean hasPlayed;
	
	//Initialize the animation
	public Animation()
	{
		hasPlayed = false;
		delay = -1;
	}
	
	//Set every frames of the animation and start the animation
	public void setFrames(BufferedImage[] animationFrames)
	{
		this.frames = animationFrames;
		currentFrame = 0;
		hasPlayed = false;
		startTime = System.nanoTime();
	}
	
	//Set the delay between each frames
	public void setDelay(long newDelay)
	{
		this.delay = newDelay;
	}
	
	//Set the actual frame
	public void setFrame(int index)
	{
		this.currentFrame = index;
	}
	
	//Update the current image of the animation
	public void update()
	{
		if(delay == -1)
		{
			return;
		}
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay)
		{
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length)
		{
			currentFrame = 0;
			hasPlayed = true;
		}
	}
	
	//Return the actual image of the animation (To draw it)
	public BufferedImage getImage()
	{
		return frames[currentFrame];
	}
	
	//Return the index of the actual image
	public int getFrame()
	{
		return currentFrame;
	}
	
	//Return if the animation has completed
	public boolean hasPlayedOnce()
	{
		return hasPlayed;
	}
}
