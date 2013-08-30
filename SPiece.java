import javax.swing.JPanel;


public class SPiece extends Shape{

	private enum dir {VERTICAL, HORIZONTAL};
	private dir state;
	
	public Block[] makeBlocks()
	{
		state = dir.HORIZONTAL;

		blocks[0] = new Block("green");
		blocks[0].setLocation(40, 100);
		
		blocks[1] = new Block("green");
		blocks[1].setLocation(60, 100);
		
		blocks[2] = new Block("green");
		blocks[2].setLocation(20, 120);
		
		blocks[3] = new Block("green");
		blocks[3].setLocation(40, 120);
		
		return blocks;
	}
	
	public int addToPlayingBoard(TetrisModel model, int x, int y)
	{
		this.model = model;
		xPos = x;
		yPos = y;
		
		blocks[0].setLocation(80, 0);
		if(this.model.addBlock(blocks[0], xPos+1, yPos) == -1)
		{
			return -1;
		}
		
		blocks[1].setLocation(100, 0);
		if(this.model.addBlock(blocks[1], xPos+2, yPos) == -1)
		{
			return -1;
		}
		
		blocks[2].setLocation(60, 20);
		if(this.model.addBlock(blocks[2], xPos, yPos+1) == -1)
		{
			return -1;
		}
		
		blocks[3].setLocation(80, 20);
		if(this.model.addBlock(blocks[3], xPos+1, yPos+1) == -1)
		{
			return -1;
		}
		
		return 0;
	}

	public int rotate() {
		
		if(state == dir.HORIZONTAL)
		{
			if(model.isOpen(xPos, yPos) && model.isOpen(xPos+1, yPos+2))
			{
				model.shift(blocks[0], -1, 0);
				model.shift(blocks[3], 0, 1);
				model.shift(blocks[2], 1, 0);
				model.shift(blocks[1], -2, 1);
				
				blocks[0].setLocation(0+xPos*20, 0+yPos*20);
				blocks[1].setLocation(0+xPos*20, 20+yPos*20);
				blocks[2].setLocation(20+xPos*20, 20+yPos*20);
				blocks[3].setLocation(20+xPos*20, 40+yPos*20);
				
				state = dir.VERTICAL;
			}
		}
		else
		{
			if(model.isOpen(xPos+1, yPos) && model.isOpen(xPos+2, yPos))
			{
				model.shift(blocks[1], 2, -1);
				model.shift(blocks[2], -1, 0);
				model.shift(blocks[3], 0, -1);
				model.shift(blocks[0], 1, 0);
				
				blocks[0].setLocation(20+xPos*20, 0+yPos*20);
				blocks[1].setLocation(40+xPos*20, 0+yPos*20);
				blocks[2].setLocation(0+xPos*20, 20+yPos*20);
				blocks[3].setLocation(20+xPos*20, 20+yPos*20);
				
				state = dir.HORIZONTAL;
			}
			
			
			
		}
		return 0;
	}
}



















