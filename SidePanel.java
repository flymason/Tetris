import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel
{
	private int score;
	private Block[] blocks;
	
	public SidePanel()
	{
		setSize(100, 360);
		score = 0;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 10, 360);
		
		g.drawString("Next Shape", 20, 90);
		Font	f = g.getFont();
		f = f.deriveFont(f.getSize2D() * 1.5F);
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString("SCORE", 25, 25);
		g.drawString(score + "", 40, 50);
		
	}
	
	public void addToScore(int s)
	{
		score += s;
	}
	public int getScore()
	{
		return score;
	}
	public void setShape(Block[] newBlocks)
	{
		for(int i = 0; i < 4; i++)
		{
			if(blocks != null)
			{
				remove(blocks[i]);
			}
			
			add(newBlocks[i]);
		}
		blocks = newBlocks;	
	}
}
