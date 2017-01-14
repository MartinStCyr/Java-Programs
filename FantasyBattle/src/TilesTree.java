
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
public class TilesTree {

	private static ArrayList<MapObject> objectList;
	private static TilesTree instance = null;
	//Game Panel offset
	private int treeOffsetX = 0;
	private int treeOffsetY = 0;
	//Game Panel width and height
	private int treeWidth = 100;
	private int treeHeight = 200;
	
	//Offset of the tiles in the tile Selection Zone
	private int wheelOffset = 0;
	
	public static TilesTree getInstance()
	{
		if(instance == null)
		{
			instance = new TilesTree();
		}
		return instance;
	}
	
	public void setTreeOffsetX(int offset)
	{
		treeOffsetX = offset;
	}
	
	public void setTreeWidth(int width)
	{
		treeWidth = width;
	}
	
	public void setTreeHeight(int height)
	{
		treeHeight = height;
	}
	
	public int getTreeWidth()
	{
		return treeWidth;
	}
	
	public int getTreeHeight()
	{
		return treeHeight;
	}
	
	public int getWheelOffset()
	{
		return wheelOffset;
	}
	
	public void setWheelOffset(int offset)
	{
		wheelOffset = offset;
	}
	
	public void setTreeOffsetY(int offset)
	{
		treeOffsetY = offset;
	}
	
	//Initialize the tree with every possible tiles
	private TilesTree()
	{
		objectList = new ArrayList<MapObject>();
		objectList.add(new MapRectangleObject("Grass.png",true));
		objectList.add(new MapRectangleObject("Tree1.png",false));
		objectList.add(new MapRectangleObject("LeftWater.png",false));
		objectList.add(new MapRectangleObject("Water1.png",false));
		objectList.add(new MapRectangleObject("Sun.png",false));
		
	}
	
	public MapObject getAt(int index)
	{
		if(index >= 0 && index < objectList.size())
		{
			return objectList.get(index);
		}
		return null;
	}
	
	public int getSize()
	{
		return objectList.size();
	}
	
	public int getOffsetX()
	{
		return treeOffsetX;
	}
	
	public int getOffsetY()
	{
		return treeOffsetY;
	}
	
	//Function allowing to click on a tile inside the tiles tree
	public MapObject getObjectClicked(int posX, int posY)
	{
		//Finds out which tile has been clicked considering there is 4 tiles per row and each take width/4 pixels of height and width
		int nbTiles = (int)(Math.floor(((double)posY -(double) treeOffsetY - (double) wheelOffset) / (double)(treeWidth/4))*4  + Math.floor(((double)posX - (double)treeOffsetX)/((double)treeWidth/4)));
		if(nbTiles >=0 && nbTiles<objectList.size())
		{
			objectList.get(nbTiles).setIsSelected(true);
			return objectList.get(nbTiles);
		}
		else
		{
			return null;
		}
	}
	
	public void draw(Graphics2D g)
	{
		//Draw background
		g.setColor(Color.WHITE);
		g.fillRect(treeOffsetX, treeOffsetY, treeWidth, treeHeight);
		
		for(int i=0;i<getSize();i++)
		{
			//Draw every object images
			//The position x is simply to assure every images are drawn 4 per rows
			//The position y is to show an image every 50 pixels
			int drawX = objectList.get(i).getPosX()+treeOffsetX+treeWidth/4*(i%4);
			int drawY = objectList.get(i).getPosY()+treeOffsetY+(int)((i/4))*treeWidth/4 + wheelOffset;
			int drawSize = treeWidth/4;
			g.drawImage(objectList.get(i).getImage(), objectList.get(i).getPosX()+treeOffsetX+treeWidth/4*(i%4),objectList.get(i).getPosY()+treeOffsetY+(int)((i/4))*treeWidth/4 + wheelOffset, drawSize,drawSize,null);
			//If the object is selected, we draw a rectangle around it
			if(objectList.get(i).getIsSelected())
			{
				g.setColor(Color.BLACK);
				g.setStroke(new BasicStroke(2));
				//Left Border
				g.drawLine(drawX, drawY, drawX, drawY + drawSize);
				//Top Border
				g.drawLine(drawX, drawY, drawX + drawSize, drawY);
				//Right Border
				g.drawLine(drawX + drawSize, drawY, drawX + drawSize, drawY + drawSize);
				//Bottom Border
				g.drawLine(drawX, drawY + drawSize, drawX + drawSize, drawY + drawSize);
			}
		}
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		//TopLine
		g.drawLine(treeOffsetX,treeOffsetY,treeOffsetX+treeWidth,treeOffsetY);
		//BottomLine
		g.drawLine(treeOffsetX,treeHeight+treeOffsetY,treeOffsetX+treeWidth,treeHeight+treeOffsetY);
		//LeftLine
		g.drawLine(treeOffsetX,treeOffsetY,treeOffsetX,treeOffsetY + treeHeight);
		//RightLine
		g.drawLine(treeOffsetX+treeWidth,treeOffsetY,treeOffsetX+treeWidth,treeHeight + treeOffsetY);
	}
}
