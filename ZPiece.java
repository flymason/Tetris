import javax.swing.JPanel;




public class ZPiece extends Shape{

	private enum dir {VERTICAL, HORIZONTAL};
	private dir state;
	
	public Block[] makeBlocks()
	{

		state = dir.HORIZONTAL;

		blocks[0] = new Block("red");
		blocks[0].setLocation(20, 100);
		
		blocks[1] = new Block("red");
		blocks[1].setLocation(40, 100);
		
		blocks[2] = new Block("red");
		blocks[2].setLocation(40, 120);
		
		blocks[3] = new Block("red");
		blocks[3].setLocation(60, 120);
		
		return blocks;
		
	}
	
	public int addToPlayingBoard(TetrisModel m, int x, int y)
	{
		this.model = m;
		xPos = x;
		yPos = y;
		
		blocks[0].setLocation(60, 0);
		if(model.addBlock(blocks[0], xPos, yPos) == -1)
		{
			return -1;
		}
		blocks[1].setLocation(80, 0);
		if(model.addBlock(blocks[1], xPos+1, yPos) == -1)
		{
			return -1;
		}
		blocks[2].setLocation(80, 20);
		if(model.addBlock(blocks[2], xPos+1, yPos+1) == -1)
		{
			return -1;
		}
		blocks[3].setLocation(100, 20);
		if(model.addBlock(blocks[3], xPos+2, yPos+1) == -1)
		{
			return -1;
		}
		
		return 0;
	}
	
	public int rotate()
	{
		if(state == dir.HORIZONTAL)
		{
			if(model.isOpen(xPos, yPos+1) && model.isOpen(xPos, yPos+2))
			{
				model.shift(blocks[1], -1, 1);
				model.shift(blocks[0], 1, 0);
				model.shift(blocks[3], -2, 1);
				
				state = dir.VERTICAL;
			}
		}
		else
		{
			if(model.isOpen(xPos, yPos) && model.isOpen(xPos+2, yPos+1))
			{
				model.shift(blocks[3], 2, -1);
				model.shift(blocks[0], -1, 0);
				model.shift(blocks[1], 1, -1);
				
				state = dir.HORIZONTAL;
			}
			
			
			
		}
		return 0;
	}
}














