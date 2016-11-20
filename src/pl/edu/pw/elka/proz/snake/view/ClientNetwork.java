package pl.edu.pw.elka.proz.snake.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import pl.edu.pw.elka.proz.snake.events.PlayerEvent;
import pl.edu.pw.elka.proz.snake.message.BoardMessage;
import pl.edu.pw.elka.proz.snake.message.GameMessage;
import pl.edu.pw.elka.proz.snake.message.InfoMessage;
import pl.edu.pw.elka.proz.snake.message.ScoreMessage;

/**
 * Klasa odpowiedzialna za komunikacj� z serwerem.
 * 
 * @author Marcin Wlaz�y
 * @version 20110603
 */

class ClientNetwork
{
	/** Widok klienta.*/
	final private View view;
	/** Socket z kt�rym komunikuje si� klient.*/
	private Socket clientSocket;
	/** Strume� obiekt�w wyj�ciowych do serwera.*/
	private ObjectOutputStream objectOutputStream;

	ClientNetwork(final View view)
	{
		this.view = view;
	}
	
	/**
	 * Nawi�zanie po��czenia z serwerem.
	 * 
	 * @param IpNumber numer IP serwera
	 */
	void conectToServer(final String IpNumber)
	{
		try
		{
			clientSocket = new Socket(IpNumber, 5555);
			objectOutputStream = new ObjectOutputStream(
					clientSocket.getOutputStream());
			Thread t = new Thread(new InputReader(clientSocket));
			t.start();

		} catch (Exception e)
		{
			view.showInfoMessage(new InfoMessage("Blad serwera"));
		}
	}

	/**
	 * Wysy�a informacje o zdarzeniu do serwera.
	 * 
	 * @param playerEvent
	 */
	void sendEvent(final PlayerEvent playerEvent)
	{
		if (objectOutputStream == null)
		{
			view.showInfoMessage(new InfoMessage("Brak po��czenia z serwerem"));
			return;
		}
		try
		{
			objectOutputStream.writeObject(playerEvent);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Nas�uchiwacz obiekt�w z serwera.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private class InputReader implements Runnable
	{
		/** Strumie� danych wej�ciowych z serwera. */
		private ObjectInputStream objectInputStream;
		/** Mapa akcji podejmowanych zgodnie z nadchodz�cymi obiektami message. */
		private final HashMap<Class<? extends GameMessage>, GameAction> actions;

		public InputReader(final Socket socket)
		{
			actions = new HashMap<Class<? extends GameMessage>, ClientNetwork.InputReader.GameAction>();
			try
			{
				objectInputStream = new ObjectInputStream(socket.getInputStream());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			actions.put(BoardMessage.class, new FakeAction());
			actions.put(InfoMessage.class, new InformationAction());
			actions.put(ScoreMessage.class, new ActScore());
		}

		/**
		 * Obsluga nadchodz�cych informacji z serwera.
		 */
		@Override
		public void run()
		{
			while (true)
			{
				try
				{
					GameMessage gameMessage = (GameMessage) objectInputStream.readObject();
					actions.get(gameMessage.getClass()).perform(gameMessage);
				} catch (IOException e)
				{
					view.showInfoMessage(new InfoMessage("Utracono po��czenie z serwerem"));
					break;
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				objectOutputStream.close();
				objectInputStream.close();
				objectInputStream = null;
				objectOutputStream = null;
				clientSocket.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
		
		/**
		 * Abstrakcyjna klasa odpowiedzialna za wykonywanie akcji.
		 * 
		 * @author Marcin Wlaz�y
		 * @version 20110602
		 */
		private abstract class GameAction
		{
			abstract void perform(GameMessage gameMessage);
		}

		/**
		 * Aktualizacja wynik�w graczy.
		 * 
		 * @author Marcin Wlaz�y
		 * @version 20110603
		 */
		private class ActScore extends GameAction
		{
			@Override
			void perform(final GameMessage gameMessage)
			{
				view.actScores((ScoreMessage) gameMessage);
			}
		}

		/**
		 * Uaktualnienie widoku g��wnej planszy.
		 * 
		 * @author Marcin Wlaz�y
		 * @version 20110603
		 */
		private class FakeAction extends GameAction
		{
			@Override
			void perform(final GameMessage gameMessage)
			{
				view.updateBoard(gameMessage);
			}
		}

		/**
		 * Wy�wietlenie okna z informacj�.
		 * 
		 * @author Marcin Wlaz�y
		 * @version 20110602
		 */
		private class InformationAction extends GameAction
		{
			@Override
			void perform(final GameMessage gameMessage)
			{
				view.showInfoMessage((InfoMessage) gameMessage);
			}
		}
	}
}
