

/*This abstract class is the general GameState
 * Every folowwing states need to implements these functions*/
public abstract class GameState {

	public abstract void init();
	public abstract void update(long elapsedTime);
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed();
	public abstract void keyReleased();
}
