import javax.swing.JPanel;

public class JPiece extends Shape{
	
	private enum dir {UP, DOWN, LEFT, RIGHT};
	private dir state;
	
	public Block[] makeBlocks()
	{
		state = dir.DOWN;
		
		for(int x = 0; x < 3; x++)
		{
			blocks[x] = new Block("blue");
			blocks[x].setLocation((x * 20)+ 20, 100);
		}
		
		blocks[3] = new Block("blue");
		blocks[3].setLocation(60, 120);
		
		return blocks;
	}
	
	public int addToPlayingBoard(TetrisModel model, int x, int y)
	{
		this.model = model;
		xPos = x;
		yPos = y;
		
		for(int i = 0; i < 3; i++)
		{
			blocks[i].setLocation((i * 20)+ 60, 0);
			if(this.model.addBlock(blocks[i], i+xPos, yPos) == -1)
			{
				return -1;
			}
		}
		blocks[3].setLocation(100, 20);
		if(this.model.addBlock(blocks[3], 2+xPos, 1+yPos) == -1)
		{
			return -1;
		}
		
		return 0;
	}

	public int rotate() {
		if(state == dir.DOWN)
		{
			if(model.isOpen(xPos+1, yPos-1) && model.isOpen(xPos+1, yPos+1) && model.isOpen(xPos, yPos+1))
			{
				model.shift(blocks[0], 1, -1);
				model.shift(blocks[2], -2, 1);
				model.shift(blocks[3], -1, 0);
				
				state = dir.LEFT;
			}
		}
		else if(state == dir.LEFT)
		{
			if(model.isOpen(xPos, yPos-1) && model.isOpen(xPos, yPos) && model.isOpen(xPos+2, yPos))
			{
				model.shift(blocks[0], -1, 0);
				model.shift(blocks[1], -1, 0);
				model.shift(blocks[2], 1, -1);
				model.shift(blocks[3], 1, -1);
				state = dir.UP;
			}
		}
		else if(state == dir.UP)
		{
			if(model.isOpen(xPos+1, yPos-1) && model.isOpen(xPos+2, yPos-1) && model.isOpen(xPos+1, yPos+1))
			{
				model.shift(blocks[0], 1, 0);
				model.shift(blocks[1], 2, -1);
				model.shift(blocks[3], -1, 1);
				state = dir.RIGHT;
			}
		}
		else if(state == dir.RIGHT)
		{
			if(model.isOpen(xPos, yPos) && model.isOpen(xPos+2, yPos) && model.isOpen(xPos+2, yPos+1))
			{
				model.shift(blocks[0], -1, 1);
				model.shift(blocks[2], 1, 0);
				model.shift(blocks[1], -1, 1);
				model.shift(blocks[3], 1, 0);
				state = dir.DOWN;
			}
		}
		return 0;
	}
}

