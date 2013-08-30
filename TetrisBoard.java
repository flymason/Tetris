import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class TetrisBoard extends JPanel implements KeyListener, Runnable {

	private TetrisModel model;
	private Shape currentShape;
	private Shape nextShape;
	private int actualDelay;
	private int gravityDelay;
	private SidePanel sidePanel;
	private int normalDelay;
	private Block[] blocks;
	private Block[] nextBlocks;

	private boolean droppingShape = false;
	private boolean shapeMoved = false;
	private boolean downKeyPressed = false;
	private boolean paused = false;
	private final Semaphore mutex = new Semaphore(1);
	final Lock lock = new ReentrantLock();
	final Condition notPaused = lock.newCondition();

	public TetrisBoard() {
		setLayout(null);
		setPreferredSize(new Dimension(300, 360));
		model = new TetrisModel();
		setBackground(Color.LIGHT_GRAY);
		setFocusable(true);
		sidePanel = new SidePanel();
		sidePanel.setLocation(200, 0);
		add(sidePanel);

		normalDelay = calcDelay();
		actualDelay = normalDelay;
		gravityDelay = 25;

	}

	public void run() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
		}
		addKeyListener(this);
		nextShape = Dealer.draw();
		nextBlocks = nextShape.makeBlocks();

		while (true) // add new shapes to board
		{
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
			}
			currentShape = nextShape;
			blocks = nextBlocks;

			nextShape = Dealer.draw();
			nextBlocks = nextShape.makeBlocks();
			sidePanel.setShape(nextBlocks);

			if (currentShape.addToPlayingBoard(model, 3, 0) == -1) // add to
																	// board
			{
				System.out.println("Game Over"); // GAME OVER - if there is a
													// shape in the way at the
													// top
				removeKeyListener(this);
				break;
			}
			mutex.release();

			for (int i = 0; i < 4; i++) // add to view
			{
				add(blocks[i]);
			}

			repaint();

			dropShape(); // handles a shape falling

		}
	}

	private void dropShape() // handles a shape falling
	{
		while (true) {
			droppingShape = true;
			// model.toString(); //prints out model view
			try {
				Thread.sleep(actualDelay);
			} catch (InterruptedException e) {
			}
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
			}

			lock.lock();
			while (paused) {
				try {
					notPaused.await();
				} catch (InterruptedException e) {
				}
			}
			lock.unlock();

			if (currentShape.fall() != 0) // if shape is done falling
			{
				droppingShape = false;
				sidePanel.addToScore(tryToRemoveRow());
				normalDelay = calcDelay();
				actualDelay = normalDelay;
				shapeMoved = false;
				mutex.release();

				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
				}

				if (shapeMoved) {
					droppingShape = true;
				} else {
					break;
				}
			}
			mutex.release();
		}
	}

	public int tryToRemoveRow() // returns points earned
	{
		boolean completeRow = true;
		int tetrisCount = 0;
		for (int y = 0; y < 18; y++) {
			for (int x = 0; x < 10; x++) {
				if (model.getBlock(x, y) == null) {
					completeRow = false;
				}
			}
			if (completeRow) {
				tetrisCount++;
				for (int x = 0; x < 10; x++) // all the way across
				{
					remove(model.getBlock(x, y));
					model.removeBlock(x, y);
					repaint();

					for (int yAbove = y - 1; yAbove >= 0; yAbove--) {
						if (model.getBlock(x, yAbove) != null) {
							model.shift(model.getBlock(x, yAbove), 0, 1);
						}
					}
				}
			}
			completeRow = true;
		}
		if (tetrisCount > 3) {
			return 1000 + (tetrisCount - 4) * 500;
		} else {
			return tetrisCount * 100;
		}

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			try {
				mutex.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if (currentShape.moveRight() != -1) {
				shapeMoved = true;
			}
			mutex.release();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			try {
				mutex.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if (currentShape.moveLeft() != -1) {
				shapeMoved = true;
			}
			mutex.release();

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			actualDelay = gravityDelay;
			if (!downKeyPressed) {
				try {
					mutex.acquire();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				currentShape.fall();
				mutex.release();
			}
			downKeyPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			try {
				mutex.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			if (currentShape.rotate() != -1) {
				shapeMoved = true;
			}
			mutex.release();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			actualDelay = normalDelay;
			downKeyPressed = false;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	private int calcDelay() {
		return (int) (500 * (1 / Math
				.sqrt((((double) sidePanel.getScore() + 500) / 1200))));
	}

	public void pause() {
		paused = true;
		removeKeyListener(this);
	}

	public void unPause() {
		paused = false;
		addKeyListener(this);

		lock.lock();
		notPaused.signal();
		lock.unlock();
	}
}
