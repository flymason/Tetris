import java.util.Random;


public class Dealer {

	private static Random rand = new Random();
	
	public static Shape draw()
	{
		int r = rand.nextInt() % 7;
		if(r < 0)
		{
			r = r * (-1);
		}
		//r = 0;
		switch(r)
		{
			case 0:
				return (Shape)new IPiece();
			case 1:
				return (Shape)new JPiece();
			case 2:
				return (Shape)new LPiece();
			case 3:
				return (Shape)new SPiece();
			case 4:
				return (Shape)new SqPiece();
			case 5:
				return (Shape)new TPiece();
			case 6:
				return (Shape)new ZPiece();
		}
		return (Shape)new ZPiece();	//to satisfy return statement
	}
}
