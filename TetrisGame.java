import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TetrisGame extends JFrame implements ActionListener
{
	private TetrisBoard board;
	private Thread gameThread;
	
	private JMenuItem newGame = new JMenuItem("New Game");
	private JMenuItem pause = new JMenuItem("Pause");
	private JMenuItem exit = new JMenuItem("Exit");
	private boolean paused = false;

	
	public TetrisGame() throws InterruptedException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tetris");
		
		board = new TetrisBoard();
		gameThread = new Thread(board);
		
		mkTopMenu();
		add(board);
		
		pack();	
		Dimension		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2 );
		setVisible(true);
		
		gameThread.start();
		
	}
	
	public void mkTopMenu()
	{
		JMenuBar topMenu = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		
		newGame.addActionListener(this);
		pause.addActionListener(this);
		exit.addActionListener(this);
		
		newGame.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		pause.setAccelerator(KeyStroke.getKeyStroke('p'));
		exit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		
		gameMenu.add(newGame);
		gameMenu.add(pause);
		gameMenu.add(exit);
		
		topMenu.add(gameMenu);
		add(topMenu, BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == newGame)
		{
			board.pause();
			remove(board);
			board = new TetrisBoard();
			gameThread = new Thread(board);
			
			add(board);
			revalidate();
			gameThread.start();
			
		}
		else if (source == pause)
		{
			if(paused)
			{
				board.unPause();
				//gameThread = new Thread(board);
//				gameThread.notifyAll();
				paused = false;
			}
			else
			{
				board.pause();
				paused = true;
			}
		}
		else if(source == exit)
		{
			System.exit(0);
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException
	{
		new TetrisGame();
	}
}














