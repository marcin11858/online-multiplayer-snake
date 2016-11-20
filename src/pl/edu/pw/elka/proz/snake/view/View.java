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
 * @author Marcin Wlaz�y 
 * @version 20110602
 */
public class View
{
	/**Ramka w kt�rej s� umieszczane elementy. */
	private MainFrame mainFrame;
	/**G��wna plansza na kt�rej pe�zaj� w�e. */
	private MainBoard mainBoard;
	/**Panel z wynikami graczy. */
	private ScoreFrame scoreFrame;
	/**Modu� sieciowy kt�ry obs�uguje po��czenie z serwerem. */
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
	 * Nazwi�zuje po��czenie z serwerem oraz wy�wietla ramke GUI.
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
	 * Wy�wietla informacje na ekranie.
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
	 * Uaktualnia widok planszy na kt�rej poruszaj� si� w�e
	 * 
	 * @param message informacje o fakeMapie oraz id w�a
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