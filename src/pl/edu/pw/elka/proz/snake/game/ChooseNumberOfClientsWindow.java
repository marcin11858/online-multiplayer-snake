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
 * Okno umo¿liwiaj¹ce wybór iloœci graczy na serwerze.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class ChooseNumberOfClientsWindow extends JFrame
{

	/**
	 * Tworzy okno do wyboru iloœci graczy.
	 */
	private static final long serialVersionUID = 1L;
	private final LinkedBlockingQueue<GameEvent> blockingQueue;
	ChooseNumberOfClientsWindow(final LinkedBlockingQueue<GameEvent> blockingQueue)
	{
		super(Messages.getString("ChooseNumberOfClientsWindow.0")); //$NON-NLS-1$
		setSize(200, 200);
		this.blockingQueue = blockingQueue;
		setLayout(new GridLayout(4, 1));
		add(new JLabel(Messages.getString("ChooseNumberOfClientsWindow.1"), SwingConstants.CENTER)); //$NON-NLS-1$
		JButton twoClients = new JButton(Messages.getString("ChooseNumberOfClientsWindow.2")); //$NON-NLS-1$
		twoClients.addActionListener(new TwoClientsAction());
		JButton threeClients = new JButton(Messages.getString("ChooseNumberOfClientsWindow.3")); //$NON-NLS-1$
		threeClients.addActionListener(new ThreeClientsAction());
		JButton fourClients = new JButton(Messages.getString("ChooseNumberOfClientsWindow.4")); //$NON-NLS-1$
		fourClients.addActionListener(new FourClientsAction());
		add(twoClients);
		add(threeClients);
		add(fourClients);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
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
	 * Ukrywa okno.
	 */
	public void hideWindow()
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
	 * Listener przycisku dla dwóch graczy.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110603
	 */
	private class TwoClientsAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			hideWindow();
			new MakeServer(2).start();
		}
	}
	
	/**
	 * Listener przycisku dla trzech graczy.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110603
	 */
	private class ThreeClientsAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			hideWindow();
			new MakeServer(3).start();
		}
		
	}
	
	/**
	 * Listener pzycisku dla cztere
	 * 
	 * @author Marcin
	 *
	 */
	private class FourClientsAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			hideWindow();
			new MakeServer(4).start();
		}
	}
	
	/**
	 * Klasa odpowiedzialna za utworzenie serwera z wybrana liczb¹ klientów.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110603
	 */
	private class MakeServer extends Thread
	{
		/**Liczba klientów na serwerze.*/
		private final int howManyClients;
		
		MakeServer(final int howManyClients)
		{
			this.howManyClients = howManyClients;
		}
		
		/**
		 * Uruchamia w¹tek serwera oraz tworzy jednego klienta.
		 * 
		 */
		@Override
		public void run()
		{
			Model model = new Model();
			Controler controler = new Controler(model, blockingQueue, howManyClients, howManyClients);
			View view = new View();
			view.display(Messages.getString("ChooseNumberOfClientsWindow.5")); //$NON-NLS-1$
			controler.begin();
		}
	}
}
