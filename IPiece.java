import javax.swing.JPanel;


public class IPiece extends Shape{
	
	private enum dir {VERTICAL, HORIZONTAL};
	private dir state;
	
	public Block[] makeBlocks()
	{
		state = dir.HORIZONTAL;
		
		for(int x = 0; x < 4; x++)
		{
			blocks[x] = new Block("cyan");
			blocks[x].setLocation((x * 20)+ 15, 100);
		}
		return blocks;
	}
	
	public int addToPlayingBoard(TetrisModel model, int x, int y)
	{
		this.model = model;
		xPos = x;
		yPos = y;
		
		for(int i = 0; i < 4; i++)
		{
			blocks[i].setLocation((i * 20)+60, 0);
			if(this.model.addBlock(blocks[i], i+xPos, yPos) == -1)
			{
				return -1;
			}
		}	
		return 0;
	}

	public int rotate()
	{
		if(state == dir.HORIZONTAL)
		{
			if(model.isOpen(xPos+1, yPos-1) && model.isOpen(xPos+1, yPos+1)&& model.isOpen(xPos+1, yPos+2))
			{
				model.shift(blocks[0], 1, -1);
				model.shift(blocks[2], -1, 1);
				model.shift(blocks[3], -2, 2);
				
				state = dir.VERTICAL;
			}
		}
		else
		{
			if(model.isOpen(xPos, yPos+1) && model.isOpen(xPos+2, yPos+1) && model.isOpen(xPos+3, yPos+1))
			{
				model.shift(blocks[3], 2, -2);
				model.shift(blocks[2], 1, -1);
				model.shift(blocks[0], -1, 1);
				
				state = dir.HORIZONTAL;
			}
		}
		return 0;
	}
}
	
	
	
	