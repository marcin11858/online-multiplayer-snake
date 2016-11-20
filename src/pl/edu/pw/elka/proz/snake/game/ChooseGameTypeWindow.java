package pl.edu.pw.elka.proz.snake.game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import pl.edu.pw.elka.proz.snake.controler.Controler;
import pl.edu.pw.elka.proz.snake.events.GameEvent;
import pl.edu.pw.elka.proz.snake.model.Model;
import pl.edu.pw.elka.proz.snake.view.View;

/**
 * Wyœwietla okno z mo¿liwoœci¹ wyboru rodzaju gry.
 * 
 * @author Marcin Wlaz³y
 * @version 20110530
 */
public class ChooseGameTypeWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	/** Kolejka blokuj¹ca eventy */
	private final LinkedBlockingQueue<GameEvent> blockingQueue;

	/**
	 * Tworzy nowe okno wyboru gry.
	 */
	public ChooseGameTypeWindow()
	{
		super(Messages.getString("ChooseGameTypeWindow.0")); //$NON-NLS-1$
		blockingQueue = new LinkedBlockingQueue<GameEvent>();
		setSize(300, 200);
		setResizable(false);
		setLayout(new GridLayout(3, 2));
		JButton newGamePlayer1 = new JButton(Messages.getString("ChooseGameTypeWindow.1")); //$NON-NLS-1$
		newGamePlayer1.addActionListener(new NewGamePlayer1Action());
		JButton newGamePlayer2 = new JButton(Messages.getString("ChooseGameTypeWindow.2")); //$NON-NLS-1$
		newGamePlayer2.addActionListener(new NewGamePlayer2Action());
		JButton newGamePlayer3 = new JButton(Messages.getString("ChooseGameTypeWindow.3"));  //$NON-NLS-1$
		newGamePlayer3.addActionListener(new NewGamePlayer3Action());
		JButton joinGame = new JButton(Messages.getString("ChooseGameTypeWindow.4")); //$NON-NLS-1$
		joinGame.addActionListener(new JoinGame());
		JButton makeServer = new JButton(Messages.getString("ChooseGameTypeWindow.5")); //$NON-NLS-1$
		makeServer.addActionListener(new MakeServer());
		add(newGamePlayer1);
		add(newGamePlayer2);
		add(newGamePlayer3);
		add(joinGame);
		add(makeServer);
		add(new JLabel(Messages.getString("ChooseGameTypeWindow.6"), SwingConstants.CENTER )); //$NON-NLS-1$
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	/**
	 * Wyœwietla okno.
	 */
	public void display()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				setVisible(true);
			}
		});
	}

	/**
	 * Ukrywa okno po wyborze odpowiedniego rodzaju gry.
	 */
	private void hideWindow()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				setVisible(false);
			}
		});
	}
	
	/**
	 * Utworzenie okna z mo¿liwoœci¹ wpisania numeru IP serwera.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110602
	 */
	private class JoinGame implements ActionListener
	{
		@Override
		public void actionPerformed(final ActionEvent e)
		{
			GetIPNumberWindow getIPNumberWindow = new GetIPNumberWindow();
			hideWindow();
			getIPNumberWindow.display();
		}
	}

	/**
	 * Utworzenia okienka do wyboru liczby klientów na serwerze.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110602
	 */
	private class MakeServer implements ActionListener
	{
		@Override
		public void actionPerformed(final ActionEvent e)
		{
			ChooseNumberOfClientsWindow chooseNumberOfClientsWindow = new ChooseNumberOfClientsWindow(blockingQueue);
			chooseNumberOfClientsWindow.display();
			hideWindow();
		}
	}

	/**
	 * Rozpoczêcie nowej rozgrywki 1 w¹¿ na 1 oknie.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110602
	 */
	private class NewGamePlayer1Action implements ActionListener
	{
		@Override
		public void actionPerformed(final ActionEvent e)
		{
			hideWindow();
			new NewGameOneClient(1).start();
		}
	}

	/**
	 * Rozpoczêcie nowej rozgrywki 2 wê¿e na 1 okienku
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110602
	 */
	private class NewGamePlayer2Action implements ActionListener
	{

		@Override
		public void actionPerformed(final ActionEvent e)
		{
			hideWindow();
			new NewGameOneClient(2).start();
		}

	}
	
	/**
	 * Rzpoczêcie nowej rozgrywki 3 wê¿e na 1 oknie.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110604
	 */
	private class NewGamePlayer3Action implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			hideWindow();
			new NewGameOneClient(3).start();
		}
		
	}

	/**
	 * Rozpoczêcie nowej gry z jednym klientem.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110604
	 */
	private class NewGameOneClient extends Thread
	{
		/**Lczba wê¿y bior¹cych udzia³ w grze.*/
		private final int howManySnakes;
		
		public NewGameOneClient(final int howManySnakes)
		{
			this.howManySnakes = howManySnakes;
		}
		
		@Override
		public void run()
		{
			Model model = new Model();
			Controler controler = new Controler(model, blockingQueue, 1, howManySnakes);
			View view = new View();
			view.display(Messages.getString("ChooseGameTypeWindow.7")); //$NON-NLS-1$
			controler.begin();
		}
	}
}
