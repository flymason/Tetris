import javax.swing.JFrame;


public class PieceTester {

	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Block");

		Shape b;
		b = new TPiece();
		b.setLocation(0, 0);
		frame.add(b);
		
		b = new SPiece();
		b.setLocation(40, 0);
		frame.add(b);
		
		b = new SqPiece();
		b.setLocation(100, 0);
		frame.add(b);
		
		b = new IPiece();
		b.setLocation(140, 0);
		frame.add(b);
		
		b = new ZPiece();
		b.setLocation(220, 0);
		frame.add(b);
		
		b = new LPiece();
		b.setLocation(140, 20);
		frame.add(b);
		
		b = new JPiece();
		b.setLocation(160, 40);
		frame.add(b);
		

		frame.setSize(20*20, 20*20);
		frame.setVisible(true);
	}
}
