import javax.swing.*;
import java.awt.*;

public class Block extends JPanel
{
	private Color centerColor;
	private int size;
	private int xPos;
	private int yPos;
	private TetrisModel model;
	
	public Block(String color)
	{
		size = 20;
		setSize(new Dimension(size, size));
		//setPreferredSize(new Dimension(size, size));
		
		switch (color)
		{
			case "purple":
				centerColor = Color.decode("#D138BD");
				break;
			case "green":
				centerColor = Color.decode("#4FFA11");
				break;
			case "red":
				centerColor = Color.decode("#ED0E0E");
				break;
			case "cyan":
				centerColor = Color.decode("#05FFFF");
				break;
			case "orange":
				centerColor = Color.decode("#FFB20D");
				break;
			case "blue":
				centerColor = Color.decode("#293BFF");
				break;
			case "yellow":
				centerColor = Color.decode("#FFFF00");
				break;
			default:
				centerColor = Color.BLACK;				
		}		
	}
	public void setPosition(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	public int getXPos()
	{
		return xPos;
	}
	public int getYPos()
	{
		return yPos;
	}
	public void setModel(TetrisModel model)
	{
		this.model = model;
	}
	
	public int fall()
	{
		try
		{
			if(model.move(xPos, yPos, xPos, yPos+1) == -1)
		
			{
			return -1;
			}
		} catch (NullPointerException e)
		{
			return -1;
		}
		yPos++;
		return 0;
	}
	public int moveRight()
	{
		if(model.move(xPos, yPos, xPos+1, yPos) == -1)
		{
			return -1;
		}
		xPos++;
		return 0;
	}
	public int moveLeft()
	{
		if(model.move(xPos, yPos, xPos-1, yPos) == -1)
		{
			return -1;
		}
		xPos--;
		return 0;
	}
	public int moveUp()
	{
		if(model.move(xPos, yPos, xPos, yPos-1) == -1)
		{
			return -1;
		}
		yPos--;
		return 0;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(centerColor);
		g.fillRoundRect(0, 0, size-1, size - 1, 4, 4);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, size-1, size-1, 4, 4);
		
		g.setColor(Color.BLACK);
		Graphics2D		g2 = (Graphics2D)g;
		
		Composite	cOld = g2.getComposite();
		Composite	cNew = ((AlphaComposite)cOld).derive(0.25F);
		g2.setComposite(cNew);
		
		Polygon shadow;
		{int[] x = {0, 0, 3, 3};
		int[] y = {0, 20, 17, 3};
		shadow = new Polygon(x, y, 4);
		g2.fillPolygon(shadow);}
		
		int [] x = {20, 17, 3, 0};
		int [] y = {20, 17, 17, 20};
		shadow = new Polygon(x, y, 4);
		g2.fillPolygon(shadow);
		
	}
}
