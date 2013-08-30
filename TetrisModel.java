
public class TetrisModel {

	private Block[][] model;
	
	public TetrisModel()
	{
		model = new Block[10][18];
	}
	
	public int move(int oldX, int oldY, int newX, int newY)
	{
		if(isOpen(newX, newY))
		{
			model[newX][newY] = model[oldX][oldY];
			model[newX][newY].setLocation(newX*20, newY*20);
			model[oldX][oldY] = null;
			return 0;
		}
		else
		{
			return -1;
		}
	}
	public int shift(Block b, int xOffset, int yOffset)
	{
		int oldX = b.getXPos();
		int oldY = b.getYPos();
		b.setPosition(oldX+xOffset, oldY + yOffset);
		return move(oldX, oldY, oldX + xOffset, oldY + yOffset);
	}
	
	public int addBlock(Block b, int x, int y)
	{
		if(isOpen(x, y))
		{
			model[x][y] = b;
			b.setPosition(x, y);
			b.setModel(this);
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	
	public boolean isOpen(int x, int y)
	{
		try{
			return (model[x][y] == null);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
	}
	public Block getBlock(int x, int y)
	{
		return model[x][y];
	}
	public void removeBlock(int x, int y)
	{
		model[x][y] = null;
	}

	public String toString()
	{
		for(int i = 0; i < 18; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(model[j][i] != null)
					System.out.print("X");
				else
					System.out.print("-");
			}
			System.out.println("");
		}
		System.out.println("");
		return "";
	}
}
     








