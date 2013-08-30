import javax.swing.JPanel;


public class SqPiece extends Shape{
	
	public Block[] makeBlocks()
	{

		
		for(int x = 0; x < 2; x++)
		{
			for(int y = 0; y < 2; y++)
			{
				blocks[(x*2)+y] = new Block("yellow");
				blocks[(x*2)+y].setLocation((x * 20)+30, (y * 20)+ 100);
			}
		}	
		return blocks;
	}
	
	public int addToPlayingBoard(TetrisModel model, int x, int y)
	{
		this.model = model;
		xPos = x+1;
		yPos = y;
		
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				blocks[(i*2)+j].setLocation((i * 20)+80, (j * 20));
				if(this.model.addBlock(blocks[(i*2)+j], i+xPos, j+yPos) == -1)
				{
					return -1;
				}
			}
		}	
		return 0;
	}

	public int rotate()
	{
		return 0;
	}
}