
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GameStateManager {

	//Array keeping every states loaded
	private GameState[] gameStates;
	//Integer showing what is the current State
	private int currentState;
	
	//Singleton
	public static GameStateManager instance = null;
	
	//The number of state in the game that can be loaded
	public static final int NUMGAMESTATES = 3;
	//The index of each states
	public static final int MENUSTATE = 0;
	public static final int CHARACTERSELECTSTATE = 1;
	public static final int EDITORSTATE = 2;
	
	//Inputs
	//Arrays that allows us to keep track of keyboard inputs
	private boolean[] keys = new boolean[1024];
	private boolean[] keysLast = new boolean[1024];
	
	//Arrays that allows us to keep track of mouse inputs
	private boolean[] buttons = new boolean[8];
	private boolean[] buttonsLast = new boolean[8];
	
	//Mouse Wheel rotations -1 = UP and 1 = DOWN
	private int mouseWheelRotation;
	
	//Mouse position
	private int mouseX, mouseY;
	
	//Constructor for gameStateManager class
	private GameStateManager()
	{
		//Allocate the space for the states
		gameStates = new GameState[NUMGAMESTATES];
		
		//Set the first state to the menu state
		currentState = MENUSTATE;
		//Load the first state
		loadState(currentState);
	}
	
	//Singleton function used to get the gsm
	public static GameStateManager getInstance()
	{
		if(instance == null)
		{
			instance = new GameStateManager();
		}
		return instance;
	}
	
	//Function used to load a state
	private void loadState(int state)
	{
		//Depending which state is being launched, different actions to be performed
		switch(state)
		{
		case 0 :
			//If the state to be loaded is 0, we load the MenuState
			gameStates[state] = new MenuState();
			break;
		case 1 :
			gameStates[state] = new CharacterSelectState();
			break;
		case 2:
			gameStates[state] = new EditorState();
			break;
		default :
			//If the state to be loaded isn't part of the values, it doesn't exist
			System.out.print("The state to be loaded doesn't exist");
		}
	}
	
	//Unload a state to keep more spaces on the computer
	private void unloadState(int state)
	{
		gameStates[state] = null;
	}
	
	//Function used to change the actual state to another one
	public void setState(int state)
	{
		//Start by unloading the actual state
		unloadState(currentState);
		//Then set the state to the new one
		currentState = state;
		loadState(currentState);
	}
	
	//Function used to update the game
	public void update(long elapsedTime)
	{
		if(gameStates[currentState] != null)
		{
			//update the state
			gameStates[currentState].update(elapsedTime);
			
			//Update the key binding
			keysLast = keys.clone();
			buttonsLast = buttons.clone();
			//After having handled the gameState Update, we don't want it to keep track of last frame wheel
			mouseWheelRotation = 0;
		}
	}
	
	//Function used to draw the game state to an image
	public void draw(java.awt.Graphics2D g)
	{
		if(gameStates[currentState] != null)
		{
			gameStates[currentState].draw(g);
		}
	}
	
	public void mouseMoved(MouseEvent arg0)
	{
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}
	
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}
	
	public void keyPressed(KeyEvent key) {
		keys[key.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent key) {
		keys[key.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent key) {
		keys[key.getKeyCode()] = true;
	}
	
	public int getMouseX()
	{
		return mouseX;
	}

	public void setMouseX(int mouseX)
	{
		this.mouseX = mouseX;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	public void setMouseY(int mouseY)
	{
		this.mouseY = mouseY;
	}
	
	public boolean isKey(int keyCode)
	{
		return keys[keyCode];
	}
	
	public boolean isKeyPressed(int keyCode)
	{
		return keys[keyCode] && !keysLast[keyCode];
	}
	
	public boolean isKeyReleased(int keyCode)
	{
		return !keys[keyCode] && keysLast[keyCode];
	}
	
	public boolean isButton(int button)
	{
		return buttons[button];
	}
	
	public boolean isButtonPressed(int button)
	{
		return buttons[button] && !buttonsLast[button];
	}
	
	public boolean isButtonReleased(int button)
	{
		return !buttons[button] && buttonsLast[button];
	}
	
	public void mouseWheelMoved(MouseWheelEvent arg0)
	{
		mouseWheelRotation = arg0.getWheelRotation();
	}
	
	public int getMouseWheelRotation()
	{
		return mouseWheelRotation;
	}
	
	
	
}
