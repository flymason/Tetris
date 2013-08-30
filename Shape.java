import java.util.Stack;

import javax.swing.JPanel;



abstract public class Shape{

	protected int xPos;
	protected int yPos;
	protected TetrisModel model;
	protected Block[] blocks = new Block[4];
	abstract public int addToPlayingBoard(TetrisModel model, int x, int y);
	abstract public int rotate();
	abstract public Block[] makeBlocks();
	
	public Shape()
	{
		
	}

	public void setPosition(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	
	
	public int fall()
	{
		

		//move each block in model
		Stack<Block> stack = new Stack<Block>();
		for(int i = 3; i >= 0; i--)
		{
			if(blocks[i].fall() == -1)
			{
				while(!stack.isEmpty())
				{
					stack.pop().moveUp();
				}
				return -1;
			}
			stack.push(blocks[i]);
		}
		yPos++;
		return 0;
	}
	
	public int moveRight()
	{
		

		//move each block in model
		Stack<Block> stack = new Stack<Block>();
		for(int i = 3; i >= 0; i--)
		{
			if(blocks[i].moveRight() == -1)
			{
				while(!stack.isEmpty())
				{
					stack.pop().moveLeft();
				}
				return -1;
			}
			stack.push(blocks[i]);
		}
		xPos++;
		return 0;
	}
	
	public int moveLeft()
	{
		//move each block in model
		Stack<Block> stack = new Stack<Block>();
		for(int i = 0; i < 4; i++)
		{
			if(blocks[i].moveLeft() == -1)
			{
				while(!stack.isEmpty())
				{
					stack.pop().moveRight();
				}
				return -1;
			}
			stack.push(blocks[i]);
		}
		xPos--;	//success condition
		return 0;
	}

}

