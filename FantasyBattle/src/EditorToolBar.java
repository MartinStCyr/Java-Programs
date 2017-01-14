import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EditorToolBar {

	private EditorState editState;
	BufferedImage[] buttonImages;
	
	
	private int offsetX = 0;
	private int offsetY = 0;
	
	private int barWidth = 0;
	private int barHeight = 0;
	
	private int toolSelected = 0;
	
	public static final int NOTOOL = 0;
	
	public static final int NEWMAPTOOL = 1;
	public static final int SAVEMAPTOOL = 2;
	
	public static final int SELECTIONTOOL = 3;
	public static final int TRANSLATETOOL = 4;
	public static final int ROTATIONTOOL = 5;
	public static final int SCALETOOL = 6;
	
	public static final int DELETETOOL = 7;
	public static final int DUPLICATETOOL = 8;
	
	public EditorToolBar()
	{
		buttonImages = new BufferedImage[8];
		
		try {
			buttonImages[0] = ImageIO.read(getClass().getResourceAsStream("NewButton.png"));
			buttonImages[1] = ImageIO.read(getClass().getResourceAsStream("SaveButton.png"));
			buttonImages[2] = ImageIO.read(getClass().getResourceAsStream("SelectionButton.png"));
			buttonImages[3] = ImageIO.read(getClass().getResourceAsStream("TranslateButton.png"));
			buttonImages[4] = ImageIO.read(getClass().getResourceAsStream("RotationButton.png"));
			buttonImages[5] = ImageIO.read(getClass().getResourceAsStream("ScaleButton.png"));
			buttonImages[6] = ImageIO.read(getClass().getResourceAsStream("DeleteButton.png"));
			buttonImages[7] = ImageIO.read(getClass().getResourceAsStream("DuplicateButton.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public int getBarWidth() {
		return barWidth;
	}

	public void setBarWidth(int barWidth) {
		this.barWidth = barWidth;
	}

	public int getBarHeight() {
		return barHeight;
	}

	public void setBarHeight(int barHeight) {
		this.barHeight = barHeight;
	}

	public EditorToolBar(EditorState state)
	{
		editState = state;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(offsetX, offsetY, barWidth, barHeight);
		
		//Draw each button
		for(int i=0;i<8;i++)
		{
			g.drawImage(buttonImages[i], offsetX + i*barWidth/8, offsetY, barWidth/8, barHeight, null);
		}
		//Rectangle around buttons
		g.setColor(Color.BLACK);
		g.drawLine(offsetX, offsetY, offsetX + barWidth, offsetY);
		for(int i=0;i<9;i++)
		{
			g.drawLine(offsetX + i*barWidth/8, offsetY, offsetX + i*barWidth/8, offsetY + barHeight);
		}
		g.drawLine(offsetX, offsetY+barHeight, offsetX + barWidth, offsetY+barHeight);
	}
}
