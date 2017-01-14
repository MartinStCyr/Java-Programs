import java.awt.Graphics2D;
import java.util.ArrayList;

public class Map {

	private ArrayList<MapObject> mapObjects;
	
	public Map()
	{
		mapObjects = new ArrayList<MapObject>();
	}
	
	public Map(ArrayList<MapObject> map)
	{
		mapObjects = map;
	}
	
	public void addObject(MapObject object)
	{
		mapObjects.add(object);
	}
	
	public void removeObject(MapObject object)
	{
		mapObjects.remove(object);
	}
	
	public void update(long elapsedTime)
	{
		for(int i = 0; i< mapObjects.size();i++)
		{
			mapObjects.get(i).update(elapsedTime);
		}
	}
	
	public ArrayList<MapObject> getMap()
	{
		return mapObjects;
	}
	
	public int getSize()
	{
		return mapObjects.size();
	}
	
	public boolean removeObject(int pos)
	{
		if(pos>=0 && pos < mapObjects.size())
		{
			mapObjects.remove(pos);
			return true;
		}
		return false;
	}
	
	public void clear()
	{
		mapObjects.clear();;
	}
	
	public void draw(Graphics2D g)
	{
		for(int i = 0; i< mapObjects.size();i++)
		{
			g.drawImage(mapObjects.get(i).getImage(), mapObjects.get(i).getPosX(), mapObjects.get(i).getPosY(),null);
		}
	}
}
