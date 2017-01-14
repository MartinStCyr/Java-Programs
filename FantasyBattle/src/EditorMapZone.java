import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class EditorMapZone {
	
	private Map map;
	//How far from the window border the zone is
	private int zoneOffsetX = 0;
	private int zoneOffsetY = 0;
	//How large the map zone is in the window
	private int zoneWidth = 100;
	private int zoneHeight = 100;
	//How much the map is offsetted inside the image
	private int mapOffsetX = 0;
	private int mapOffsetY = 0;
	
	public EditorMapZone()
	{
		map = new Map();
	}
	
	public void addTile(MapObject selectedTile, int mousePosX,int mousePosY)
	{
		//Switch on the tile type
		switch(selectedTile.getClass().getName())
		{
		case "MapRectangleObject" :
			//Create a new tile to avoid reference troubles
			MapObject newObject = new MapRectangleObject(selectedTile.getFileName(), selectedTile.getIsSolid());
			newObject.setPosition(mousePosX - selectedTile.getImage().getWidth()/2 - zoneOffsetX - mapOffsetX, mousePosY - selectedTile.getImage().getHeight()/2 - zoneOffsetY - mapOffsetY);
			map.addObject(newObject);
			break;
		default :
			break;
		}
	}
	
	public void removeObject(MapObject object)
	{
		map.removeObject(object);
	}
	
	//Function that allows tile selection inside the map editor
	public MapObject selectTile(int mousePosX,int mousePosY)
	{
		ArrayList<MapObject> objectList = map.getMap();
		//Goes through every tiles
		for(int i = objectList.size() - 1 ; i >= 0 ; i--)
		{
			MapObject element = objectList.get(i);
			/*Finds out the absolute element position
			Positions are considered absolute considering the map top left corner*/
			int posElemX = element.getPosX() + mapOffsetX;
			int posElemY = element.getPosY() + mapOffsetY;
			
			//Finds out the absolute click position
			int posClickX = mousePosX - zoneOffsetX;
			int posClickY = mousePosY - zoneOffsetY;
			
			//Check if click hits the object
			if(posClickX >= posElemX && posClickX <= posElemX + element.getImage().getWidth()
			&& posClickY >= posElemY && posClickY <= posElemY + element.getImage().getHeight())
			{
				element.setIsSelected(true);
				return element;
			}
		}
		return null;
	}
	
	//Change the actual map
	public void setMap(Map newMap)
	{
		map = newMap;
	}
	
	//Change the space between the frame left border and the map editor scene
	public void setZoneOffsetX(int offset)
	{
		zoneOffsetX = offset;
	}
	
	//Change the space between the frame top border and the map editor scene
	public void setZoneOffsetY(int offset)
	{
		zoneOffsetY = offset;
	}
	
	//Change the width of the map editor scene
	public void setZoneWidth(int width)
	{
		zoneWidth = width;
	}
	
	//Change the heigth of the map editor scene
	public void setZoneHeight(int height)
	{
		zoneHeight = height;
	}
	
	//Makes in sort that we can scroll the map from left to right
	public void setMapOffsetX(int offset)
	{
		mapOffsetX = offset;
	}
	
	//Makes in sort that we can scroll the map from top to bottom
	public void setMapOffsetY(int offset)
	{
		mapOffsetY = offset;
	}

	public int getZoneOffsetX() {
		return zoneOffsetX;
	}

	public int getZoneOffsetY() {
		return zoneOffsetY;
	}

	public int getZoneWidth() {
		return zoneWidth;
	}

	public int getZoneHeight() {
		return zoneHeight;
	}

	public int getMapOffsetX() {
		return mapOffsetX;
	}

	public int getMapOffsetY() {
		return mapOffsetY;
	}
	
	//Draw the image at a specific location without considering the map Offset
	public void drawImageAt(Graphics2D g, MapObject objectToDraw, int objectX, int objectY)
	{
		//Determines the image dimensions
		int objectWidth = objectToDraw.getImage().getWidth();
		int objectHeight = objectToDraw.getImage().getHeight();

		//Set the base drawing position to the top left corner
		int imageStartX=0;
		int imageStartY=0;
		int imageWidth = objectWidth;
		int imageHeight = objectHeight;
		//If the image is outside of the zone on left side, we reduce the width and chop the picture
		if(objectX < 0)
		{
			imageStartX = -(objectX);
			imageWidth = imageWidth - imageStartX;
		}
		//If the image is outside of the zone on right side, we reduce the width and chop the picture
		if(objectX + imageWidth > zoneWidth)
		{
			imageWidth = imageWidth - (objectX+imageWidth-zoneWidth);
		}
		//Same thing as for x but for y (top side)
		if(objectY < 0)
		{
			imageStartY = -(objectY);
			imageHeight = imageHeight - imageStartY;
		}
		//Same thing as for x but for y (bottom side)
		if(objectY + imageHeight > zoneHeight)
		{
			imageHeight = imageHeight - (objectY + imageHeight - zoneHeight);
		}
		//If the image that has to be drawn exists (is part of the map editor)
		if(imageHeight > 0 && imageWidth >0 && imageStartX < objectWidth && imageStartY < objectHeight && imageStartX >= 0 && imageStartY >=0)
		{
			BufferedImage subImage = objectToDraw.getImage().getSubimage(imageStartX,imageStartY,imageWidth,imageHeight);
			
			//If the item is inside the borders of the map zone
			g.drawImage(subImage, zoneOffsetX + (objectX + imageStartX), zoneOffsetY + (objectY + imageStartY), null);
		}
	}
	
	//Function that allows to save the actual map inside a text file
	/*
	 * The save format is TYPE;FILENAME;POSX;POSY
	 */
	public void saveMap(String fileName)
	{
		try {
			FileWriter fw = new FileWriter(fileName);
			//For each objects in the map
			for(int i=0;i<map.getSize();i++)
			{
				//Write it to the file
				MapObject elem = map.getMap().get(i);
				fw.write(elem.getClass().getName()+";"+elem.getFileName()+";"+elem.getPosX()+";"+elem.getPosY()+"\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Function allowing to read a map from a text file
	public void loadMap(String fileName)
	{
		System.out.print("Load Map\n");
		//Empty the map in case some items were already on it
		map.clear();
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = new String();
			int nextSeparator = 0;
			int lastSeparator = 0;
			//For each objects that are in the map, we create a new object
			while((line = br.readLine()) != null)
			{
				nextSeparator = line.indexOf(';');
				lastSeparator = nextSeparator+1;
				switch(line.substring(0, nextSeparator))
				{
				case "MapRectangleObject" :
					nextSeparator = line.indexOf(';',lastSeparator);
					String imageName = line.substring(lastSeparator, nextSeparator);
					lastSeparator = nextSeparator+1;
					nextSeparator = line.indexOf(';',lastSeparator);
					int positionX = Integer.parseInt(line.substring(lastSeparator, nextSeparator));
					lastSeparator = nextSeparator+1;
					nextSeparator = line.length();
					int positionY = Integer.parseInt(line.substring(lastSeparator, nextSeparator));
					MapObject newObject = new MapRectangleObject(imageName,true);
					newObject.setPosition(positionX, positionY);
					map.addObject(newObject);
					break;
				default :
					break;
				}
			}
			br.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//Function that draws the whole map editor zone
	public void draw(Graphics2D g)
	{
		//Draw the background of the map
		g.setColor(Color.BLACK);
		g.fillRect(zoneOffsetX, zoneOffsetY, zoneWidth, zoneHeight);
		
		ArrayList<MapObject> objectList = map.getMap();
		//Draws each elements of the map
		for(int i=0;i<objectList.size();i++)
		{
			MapObject objectToDraw = objectList.get(i);
			int objectX = objectToDraw.getPosX();
			int objectY = objectToDraw.getPosY();
			int objectWidth = objectToDraw.getImage().getWidth();
			int objectHeight = objectToDraw.getImage().getHeight();

			int imageStartX=0;
			int imageStartY=0;
			int imageWidth = objectWidth;
			int imageHeight = objectHeight;
			if(objectX + mapOffsetX < 0)
			{
				imageStartX = -(objectX + mapOffsetX);
				imageWidth = imageWidth - imageStartX;
			}
			if(objectX + imageWidth + mapOffsetX > zoneWidth)
			{
				imageWidth = imageWidth - (objectX+imageWidth+mapOffsetX-zoneWidth);
			}
			if(objectY + mapOffsetY < 0)
			{
				imageStartY = -(objectY + mapOffsetY);
				imageHeight = imageHeight - imageStartY;
			}
			if(objectY + imageHeight + mapOffsetY > zoneHeight)
			{
				imageHeight = imageHeight - (objectY + imageHeight + mapOffsetY - zoneHeight);
			}
			if(imageHeight > 0 && imageWidth >0 && imageStartX < objectWidth && imageStartY < objectHeight && imageStartX >= 0 && imageStartY >=0)
			{
				BufferedImage subImage = objectToDraw.getImage().getSubimage(imageStartX,imageStartY,imageWidth,imageHeight);
				
				int drawPosX = zoneOffsetX + mapOffsetX + (objectX + imageStartX);
				int drawPosY = zoneOffsetY + mapOffsetY + (objectY + imageStartY);
				//If the item is inside the borders of the map zone
				g.drawImage(subImage, drawPosX, drawPosY, null);
				//If the object is selected, we put a border around it
				if(objectToDraw.getIsSelected())
				{
					//Draw a rectangle around it
					g.setColor(Color.WHITE);
					g.setStroke(new BasicStroke(2));
					//Left Border
					g.drawLine(drawPosX, drawPosY, drawPosX, drawPosY + subImage.getHeight());
					//Top Border
					g.drawLine(drawPosX, drawPosY, drawPosX + subImage.getWidth(), drawPosY );
					//Right Border
					g.drawLine(drawPosX + subImage.getWidth(), drawPosY, drawPosX + subImage.getWidth(), drawPosY + subImage.getHeight() );
					//Bottom Border
					g.drawLine(drawPosX, drawPosY + subImage.getHeight(), drawPosX + subImage.getWidth(), drawPosY + subImage.getHeight() );
				}
			}
		}
	}	
}
