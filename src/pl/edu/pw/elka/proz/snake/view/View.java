package pl.edu.pw.elka.proz.snake.view;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import pl.edu.pw.elka.proz.snake.message.BoardMessage;
import pl.edu.pw.elka.proz.snake.message.GameMessage;
import pl.edu.pw.elka.proz.snake.message.InfoMessage;
import pl.edu.pw.elka.proz.snake.message.ScoreMessage;

/**
 * Buduje GUI gry.
 * 
 * @author Marcin Wlaz³y 
 * @version 20110602
 */
public class View
{
	/**Ramka w której s¹ umieszczane elementy. */
	private MainFrame mainFrame;
	/**G³ówna plansza na której pe³zaj¹ wê¿e. */
	private MainBoard mainBoard;
	/**Panel z wynikami graczy. */
	private ScoreFrame scoreFrame;
	/**Modu³ sieciowy który obs³uguje po³¹czenie z serwerem. */
	private final ClientNetwork clientNetwork;

	public View()
	{
		clientNetwork = new ClientNetwork(this);
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				scoreFrame = new ScoreFrame();
				mainFrame = new MainFrame(clientNetwork);
				mainBoard = new MainBoard();

				mainFrame.setLayout(new BorderLayout());
				mainFrame.add(mainBoard, BorderLayout.CENTER);
				mainFrame.add(scoreFrame, BorderLayout.EAST);
				mainFrame.setLocationRelativeTo(null);
			}
		});
	}

	/**
	 * Aktualizuje wyniki graczy.
	 * 
	 * @param scoreMessage
	 */
	public void actScores(final ScoreMessage scoreMessage)
	{
	
		scoreFrame.actScore(scoreMessage);
	}

	/**
	 * Nazwi¹zuje po³¹czenie z serwerem oraz wyœwietla ramke GUI.
	 * 
	 * @param IPNumber numer IP serwera
	 */
	public void display(final String IPNumber)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{

				mainFrame.setVisible(true);
			}
		});
		clientNetwork.conectToServer(IPNumber);
	}

	/**
	 * Wyœwietla informacje na ekranie.
	 * 
	 * @param infoMessage
	 */
	public void showInfoMessage(final InfoMessage infoMessage)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				JOptionPane.showMessageDialog(mainFrame,
						infoMessage.getMessage());
			}
		});
	}

	/**
	 * Uaktualnia widok planszy na której poruszaj¹ siê wê¿e
	 * 
	 * @param message informacje o fakeMapie oraz id wê¿a
	 */
	public void updateBoard(final GameMessage message)
	{
		final BoardMessage boradMessage = (BoardMessage) message;
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{

				mainBoard.setFake(boradMessage.getFakeMap());
				mainBoard.repaint();
			}
		});
	}
}