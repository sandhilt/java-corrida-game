
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

//import java.rmi.*;
//import java.rmi.registry.*;
//import java.rmi.server.*;
public class JogoCorrida extends JFrame implements Runnable, KeyListener {

	private FrameRate fr;
	private BufferStrategy bs;
	private volatile boolean running;
	private Thread gameThread;

	private Player p1;
	private Player p2;

	public JogoCorrida() {
		fr = new FrameRate();

		p1 = new Player(0, 0);

	}

	public static void main(String[] args) {
		final JogoCorrida jogo = new JogoCorrida();
		jogo.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jogo.onWindowClosing();
			}
		});
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				jogo.createAndShowGui();
			}
		});
	}

	public void createAndShowGui() {
		Canvas canvas = new Canvas();
		canvas.setSize(800, 600);
		canvas.setBackground(Color.WHITE);
		canvas.setIgnoreRepaint(true);
		getContentPane().add(canvas);
		setTitle("The Need Velocity Run");
		setIgnoreRepaint(true);
		pack();
		setVisible(true);
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		running = true;
		fr.init();

		while (running) {
			gameLoop();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
		//
		//		}
		//		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
		//
		//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void gameLoop() {
		do {
			do {
				Graphics g = null;
				try {
					g = bs.getDrawGraphics();
					g.clearRect(0, 0, getWidth(), getHeight());
					render(g);
					p1.render(g);

					/* logica do jogo */
				} finally {
					if (g != null) {
						g.dispose();
					}
				}
			} while (bs.contentsRestored());

			bs.show();

		} while (bs.contentsLost());
	}

	private void render(Graphics g) {
		fr.calculate();
		g.setColor(Color.red);
		g.drawString(fr.getFrameRate(), 300, 20);
	}

	protected void onWindowClosing() {
		try {
			running = false;
			gameThread.join();
		} catch (InterruptedException e) {
		}
		System.exit(0);
	}

}
