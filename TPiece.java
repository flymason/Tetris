import javax.swing.JPanel;

public class TPiece extends Shape{

	private enum dir {UP, DOWN, LEFT, RIGHT};
	private dir state;
	
	public Block[] makeBlocks()
	{
		state = dir.DOWN;
		
		for(int x = 0; x < 3; x++)
		{
				blocks[x] = new Block("purple");
				blocks[x].setLocation((x * 20)+20, 100);
		}
		blocks[3] = new Block("purple");
		blocks[3].setLocation((1 * 20)+20, 120);
		
		return blocks;
	}
	
	public int addToPlayingBoard(TetrisModel model, int x, int y)
	{
		this.model = model;
		xPos = x;
		yPos = y;
		
		blocks[3].setLocation((1 * 20)+60, 1 * 20);
		if(this.model.addBlock(blocks[3], xPos+1, 1+yPos) == -1)
		{
			return -1;
		}
		
		for(int i = 0; i < 3; i++)
		{
			blocks[i].setLocation((i * 20)+60, 0);
			
			if(this.model.addBlock(blocks[i], xPos+i, yPos) == -1)
			{
				return -1;
			}
		}
		
		
		return 0;
	}

	public int rotate() {
		if(state == dir.DOWN)
		{
			if(model.isOpen(xPos+1, yPos-1))
			{
				model.shift(blocks[0], 1, -1);
				model.shift(blocks[1], -1, 0);
				model.shift(blocks[2], -1, 0);
				state = dir.LEFT;
			}
		}
		else if(state == dir.LEFT)
		{
			if(model.isOpen(xPos+2, yPos))
			{
				model.shift(blocks[3], 1, -1);
				state = dir.UP;
			}
		}
		else if(state == dir.UP)
		{
			if(model.isOpen(xPos+1, yPos+1))
			{
				model.shift(blocks[3], -1, 1);
				model.shift(blocks[2], 1, 0);
				model.shift(blocks[1], 1, 0);
				state = dir.RIGHT;
			}
		}
		else if(state == dir.RIGHT)
		{
			if(model.isOpen(xPos, yPos))
			{
				model.shift(blocks[0], -1, 1);
				state = dir.DOWN;
			}
		}
		return 0;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
