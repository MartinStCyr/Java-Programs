

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EditorState extends GameState{

	private MapObject selectedTile;
	private ArrayList<MapObject> editingTile;
	private EditorMapZone mapZone;
	private EditorToolBar toolBar;
	
	public EditorState()
	{
			TilesTree renderTree = TilesTree.getInstance();
			renderTree.setTreeOffsetX(GamePanel.width/3*2);
			renderTree.setTreeOffsetY(80);
			renderTree.setTreeWidth(200);
			renderTree.setTreeHeight(400);
			
			mapZone = new EditorMapZone();
			//mapZone.setZoneOffsetX(GamePanel.width/20);
			//mapZone.setZoneOffsetY(GamePanel.height/20);
			mapZone.setZoneOffsetX(80);
			mapZone.setZoneOffsetY(80);
			mapZone.setZoneWidth(GamePanel.width/2);
			mapZone.setZoneHeight(GamePanel.height/2);
			
			editingTile = new ArrayList<MapObject>();
			toolBar = new EditorToolBar();
			toolBar.setOffsetX(GamePanel.width/10);
			toolBar.setOffsetY(GamePanel.height/30);
			toolBar.setBarWidth(GamePanel.width/2);
			toolBar.setBarHeight(GamePanel.height/20);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(long elapsedTime) {
		keyPressed();
		keyReleased();
	}

	@Override
	public void draw(Graphics2D g) {
		TilesTree renderTree = TilesTree.getInstance();
		Color overlayColor = new Color(125,125,125);
		
		//Draw the overlay
		g.setColor(overlayColor);
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);
		
		//Draw the tilesSelector
		renderTree.draw(g);
		
		//Overwrite the tileSelector
		g.setColor(overlayColor);
		g.fillRect(renderTree.getOffsetX()-5, 0, renderTree.getTreeWidth()+10, renderTree.getOffsetY());
		g.fillRect(renderTree.getOffsetX()-5, renderTree.getOffsetY()+renderTree.getTreeHeight(), renderTree.getTreeWidth()+10, GamePanel.height-(renderTree.getTreeHeight()+renderTree.getOffsetY()));
		
		//Draw the map		
		mapZone.draw(g);
		//Draw the selectedTile
		if(selectedTile != null)
		{
			GameStateManager gsm = GameStateManager.getInstance();
			mapZone.drawImageAt(g, selectedTile, -selectedTile.getImage().getWidth()/2 + gsm.getMouseX() - mapZone.getZoneOffsetX(), -selectedTile.getImage().getHeight()/2 + gsm.getMouseY() - mapZone.getZoneOffsetY());
		}
		
		//Draw the Toolbar
		toolBar.draw(g);
		
	}

	@Override
	public void keyPressed() {
		GameStateManager gsm = GameStateManager.getInstance();
		
		//Ctrl + S
		if(gsm.isKey(KeyEvent.VK_CONTROL) && gsm.isKeyPressed(KeyEvent.VK_S))
		{
			mapZone.saveMap("Map1.txt");
		}
		//Ctrl + O
		if(gsm.isKey(KeyEvent.VK_CONTROL) && gsm.isKeyPressed(KeyEvent.VK_O))
		{
			mapZone.loadMap("Map1.txt");
		}
		
		//LEFT
		if(gsm.isKey(KeyEvent.VK_LEFT))
		{
			mapZone.setMapOffsetX(mapZone.getMapOffsetX() -5);
		}
		//RIGHT
		if(gsm.isKey(KeyEvent.VK_RIGHT))
		{
			mapZone.setMapOffsetX(mapZone.getMapOffsetX() +5);
		}
		//UP
		if(gsm.isKey(KeyEvent.VK_UP))
		{
			mapZone.setMapOffsetY(mapZone.getMapOffsetY() -5);
		}
		//DOWN
		if(gsm.isKey(KeyEvent.VK_DOWN))
		{
			mapZone.setMapOffsetY(mapZone.getMapOffsetY() +5);
		}
		if(gsm.isKeyPressed(KeyEvent.VK_DELETE))
		{
			for(int i=0; i < editingTile.size(); i++)
			{
				mapZone.removeObject(editingTile.get(i));
			}
			
		}
		
		//Mouse Wheel
		int mouseWheelRotation = gsm.getMouseWheelRotation();
		//MOUSE WHEEL
		if(mouseWheelRotation !=0)
		{
			TilesTree tree = TilesTree.getInstance();
			int mousePosX = gsm.getMouseX();
			int mousePosY = gsm.getMouseY();
			if(mousePosX >= tree.getOffsetX() && mousePosX <= tree.getOffsetX()+tree.getTreeWidth() && mousePosY >= tree.getOffsetY() && mousePosY <= tree.getOffsetY() + tree.getTreeHeight())
			{
				tree.setWheelOffset(tree.getWheelOffset() - mouseWheelRotation*5);
			}
		}
		
		//CLICK
		if(gsm.isButtonPressed(MouseEvent.BUTTON1))
		{
			TilesTree tree = TilesTree.getInstance();
			int mousePosX = gsm.getMouseX();
			int mousePosY = gsm.getMouseY();
			
			//Click on the map
			if(mousePosX >= mapZone.getZoneOffsetX() && mousePosX <= mapZone.getZoneOffsetX() + mapZone.getZoneWidth() && mousePosY >= mapZone.getZoneOffsetY() && mousePosY <= mapZone.getZoneOffsetY() + mapZone.getZoneHeight())
			{
				//If a tile has been selected
				if(selectedTile != null)
				{
					mapZone.addTile(selectedTile, mousePosX, mousePosY);
					selectedTile.setIsSelected(false);
					selectedTile = null;
				}
				else
				{
					MapObject clickObject= mapZone.selectTile(mousePosX, mousePosY);
					if(clickObject == null)
					{
						for(int i=0;i<editingTile.size();i++)
							editingTile.get(i).setIsSelected(false);
						editingTile.clear();
					}
					//IF CTRL + CLICK
					else if(gsm.isKey(KeyEvent.VK_CONTROL))
					{
							clickObject.setIsSelected(true);
							editingTile.add(clickObject);
					}
					else
					{
						for(int i=0;i<editingTile.size();i++)
							editingTile.get(i).setIsSelected(false);
						editingTile.clear();
						clickObject.setIsSelected(true);
						editingTile.add(clickObject);
					}
				}
			}
			
			//Click on the Tile Selector
			if(mousePosX >= tree.getOffsetX() && mousePosX <= tree.getOffsetX()+tree.getTreeWidth() && mousePosY >= tree.getOffsetY() && mousePosY <= tree.getOffsetY() + tree.getTreeHeight())
			{
				if(selectedTile != null)
				{
					selectedTile.setIsSelected(false);
				}
				selectedTile = tree.getObjectClicked(mousePosX, mousePosY);
				for(int i=0;i<editingTile.size();i++)
					editingTile.get(i).setIsSelected(false);
				editingTile.clear();
			}
		}
	}
	
	@Override
	public void keyReleased() {
		// TODO Auto-generated method stub
		
	}

}
