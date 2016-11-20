package pl.edu.pw.elka.proz.snake.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import pl.edu.pw.elka.proz.snake.events.NewGameEvent;
import pl.edu.pw.elka.proz.snake.events.PressDownKeyEvent;
import pl.edu.pw.elka.proz.snake.events.PressLeftKeyEvent;
import pl.edu.pw.elka.proz.snake.events.PressRightKeyEvent;
import pl.edu.pw.elka.proz.snake.events.PressUpKeyEvent;
import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Ramka w kt�rej s� wy�wietlane poszczeg�lne elementy sk�adowe GUI.
 * 
 * @author Marcin Wlaz�y.
 * @version 20110527
 * 
 */
public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	/**Menu rozwijalne. */
	private final JMenuBar menu;
	/**Modu� sieciowy obs�uguj�cy po��czenie z serwerem. */
	private final ClientNetwork clientNetwork;

	MainFrame(final ClientNetwork clientNetwork)
	{
		super("Snake");
		this.clientNetwork = clientNetwork;
		setSize(440, 410); // 440 410
		setResizable(false);
		menu = new JMenuBar();
		JMenu gameMenu = new JMenu("Gra");
		JMenuItem newGame = new JMenuItem("Nowa gra");
		newGame.addActionListener(new NewGameAction());
		gameMenu.add(newGame);
		menu.add(gameMenu);
		setJMenuBar(menu);
		addKeyListener(new BoardKeyListener());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * Nas�uchiwacz klawiszy. Wysy�a do serewera informacje o naci�ni�ciu klawiszy przez gracza.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private class BoardKeyListener implements KeyListener
	{
		@Override
		public void keyPressed(final KeyEvent arg0)
		{
			if (arg0.getKeyCode() == KeyEvent.VK_LEFT)
			{
				clientNetwork.sendEvent(new PressLeftKeyEvent(new KeySetID(1)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				clientNetwork.sendEvent(new PressRightKeyEvent(new KeySetID(1)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_UP)
			{
				clientNetwork.sendEvent(new PressUpKeyEvent(new KeySetID(1)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_DOWN)
			{
				clientNetwork.sendEvent(new PressDownKeyEvent(new KeySetID(1)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_W)
			{
				clientNetwork.sendEvent(new PressUpKeyEvent(new KeySetID(2)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_S)
			{
				clientNetwork.sendEvent(new PressDownKeyEvent(new KeySetID(2)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_D)
			{
				clientNetwork.sendEvent(new PressRightKeyEvent(new KeySetID(2)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_A)
			{
				clientNetwork.sendEvent(new PressLeftKeyEvent(new KeySetID(2)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_H)
			{
				clientNetwork.sendEvent(new PressUpKeyEvent(new KeySetID(3)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_N)
			{
				clientNetwork.sendEvent(new PressDownKeyEvent(new KeySetID(3)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_M)
			{
				clientNetwork.sendEvent(new PressRightKeyEvent(new KeySetID(3)));
			}
			if (arg0.getKeyCode() == KeyEvent.VK_B)
			{
				clientNetwork.sendEvent(new PressLeftKeyEvent(new KeySetID(3)));
			}
		}

		@Override
		public void keyReleased(final KeyEvent arg0)
		{
		}

		@Override
		public void keyTyped(final KeyEvent arg0)
		{
		}

	}

	/**
	 * Listener przycisku nowa gra. Wysy�a informacje do serwera o tym �e gracz nacisn� przycisk.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private class NewGameAction implements ActionListener
	{
		@Override
		public void actionPerformed(final ActionEvent arg0)
		{
			clientNetwork.sendEvent(new NewGameEvent());
		}
	}
}
