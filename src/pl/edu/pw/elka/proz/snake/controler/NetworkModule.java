package pl.edu.pw.elka.proz.snake.controler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import pl.edu.pw.elka.proz.snake.events.EndGameEvent;
import pl.edu.pw.elka.proz.snake.events.GameEvent;
import pl.edu.pw.elka.proz.snake.events.NewGameEvent;
import pl.edu.pw.elka.proz.snake.events.PlayerEvent;
import pl.edu.pw.elka.proz.snake.fakes.BodyFake;
import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.fakes.FakePoint;
import pl.edu.pw.elka.proz.snake.fakes.GameFake;
import pl.edu.pw.elka.proz.snake.fakes.ScoreFake;
import pl.edu.pw.elka.proz.snake.fakes.ScoreFakeMap;
import pl.edu.pw.elka.proz.snake.message.BoardMessage;
import pl.edu.pw.elka.proz.snake.message.GameMessage;
import pl.edu.pw.elka.proz.snake.message.InfoMessage;
import pl.edu.pw.elka.proz.snake.message.ScoreMessage;
import pl.edu.pw.elka.proz.snake.model.SnakeNumber;
import pl.edu.pw.elka.proz.snake.snake.PlayerID;

/**
 * Modu� sieciowy umo�liwia komunikacj� z klientami
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
class NetworkModule
{
	/** Kolejka blokuj�ca do kt�rej modu� sieciowy wrzuca zdarzenia. */
	private final BlockingQueue<GameEvent> blockingQueue;
	/** Mapa zawieraj�ca sockety pod��czonych klient�w. */
	private final ConcurrentMap<PlayerID, Socket> sockets = new ConcurrentHashMap<PlayerID, Socket>();
	/** Mapa zawieraj�ca objectOutputStreamy pod��czonych klient�w.*/
	private final ConcurrentMap<PlayerID, ObjectOutputStream> objectOutputStreams = new ConcurrentHashMap<PlayerID, ObjectOutputStream>();
	/** Maksymalna liczba klient�w pod��czonych do serwera. */
	private int numberOfClients;
	/** Ostatnio wys�ana Fake Mapa.*/
	private HashMap<FakePoint, GameFake> prevFakeMap = null;
	/** Ostatnio wys�ana mapa wynik�w.*/
	private Map<SnakeNumber, ScoreFake> prevScoreMap = null;
	
	/**
	 * Tworzy modu� sieciowy.
	 * 
	 * @param blockingQueue kolejka blokuj�ca do kt�rej s� wrzucane eventy przez modu� sieciowy.
	 */
	NetworkModule(final BlockingQueue<GameEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}
	
	/**
	 * Uruchamia w�tek serwera.
	 * 
	 * @param howManyClients liczba klient�w pod��czonych do serwera.
	 */
	void begin(final int howManyClients)
	{
		Server server = new Server(howManyClients);
		Thread t = new Thread(server);
		t.start();
	}
	
	/**
	 * Wysy�a wszystkim graczom zmiany jakie zasz�y na planszy.
	 * 
	 * @param fakeMap nowa fake mapa planszy por�wnywana jest z map� wys�an� wcze�niej.
	 */
	void sendAllPlayersFakeMap(final FakeMap fakeMap)
	{
		HashMap<FakePoint, GameFake> newFakeMap = fakeMap.getFakeMap();
		if(newFakeMap.isEmpty())
		{
			return;
		}
		if(prevFakeMap == null)
		{
			prevFakeMap = newFakeMap;
			sendAllPlayersMessage(new BoardMessage(fakeMap) );
			return;
		}
		HashMap<FakePoint, GameFake> changes = new HashMap<FakePoint, GameFake>();
		for(FakePoint fakePoint : prevFakeMap.keySet())
		{
			if((!(prevFakeMap.get(fakePoint).getClass().equals(newFakeMap.get(fakePoint).getClass()))))
			{
				changes.put(fakePoint, newFakeMap.get(fakePoint));
			}
			if(newFakeMap.get(fakePoint).getClass().equals(BodyFake.class) && (!prevFakeMap.get(fakePoint).equals(newFakeMap.get(fakePoint))))
			{
				changes.put(fakePoint, newFakeMap.get(fakePoint));
			}
		}
		prevFakeMap = newFakeMap;
		sendAllPlayersMessage(new BoardMessage(new FakeMap(changes)));
	}
	
	/**
	 * Wysy�a informacje do wszystkich graczy.
	 * 
	 * @param gameMessage
	 */
	void sendAllPlayersMessage(final GameMessage gameMessage)
	{
		for (ObjectOutputStream objectOutputStream : objectOutputStreams.values())
		{
			try
			{
				objectOutputStream.writeObject(gameMessage);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Wysy�a informacje wybranemu klientowi.
	 * 
	 * @param gameMessage informacja do gracza
	 * @param whichClient id klienta
	 */
	void sendPlayerMessage(final GameMessage gameMessage, final PlayerID whichClient)
	{
		try
		{
			if (objectOutputStreams.containsKey(whichClient))
			{
				objectOutputStreams.get(whichClient).writeObject(gameMessage);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Wysy�a wszystkim graczom informacje o wynikach.
	 * Nowa mapa wynik�w pr�wnywana jest z map� wys�an� wcze�niej.
	 * 
	 * @param scoreMessage
	 */
	void sendAllPlayersScore(ScoreMessage scoreMessage)
	{
		if(prevScoreMap == null)
		{
			prevScoreMap = scoreMessage.getScoreFakeMap().getScoreMap();
			sendAllPlayersMessage(scoreMessage);
			return;
		}
		Map<SnakeNumber, ScoreFake> newScore = scoreMessage.getScoreFakeMap().getScoreMap();
		Map<SnakeNumber, ScoreFake> changesScore = new HashMap<SnakeNumber, ScoreFake>();
		for(SnakeNumber snakeNumber : newScore.keySet())
		{
			if(!(prevScoreMap.get(snakeNumber).equals(newScore.get(snakeNumber))))
			{
				changesScore.put(snakeNumber, newScore.get(snakeNumber));
			}
		}
		prevScoreMap = newScore;
		if(!changesScore.isEmpty())
		{
			sendAllPlayersMessage(new ScoreMessage(new ScoreFakeMap(changesScore)));
		}
	}
	
	/**
	 * Zwraca liczbe aktualnie pod��czonych klient�w.
	 * 
	 * @return liczba aktualnie pod��czonych klient�w do serwera.
	 */
	int howManyPlayerAct()
	{
		return objectOutputStreams.size();
	}

	/**
	 * Informuje o tym czy pod��czony jest wi�cej ni� jeden klient 
	 */
	boolean isMoreThanOnePlayer()
	{
		return (getNumberOfClients() > 1);
	}
	
	/**
	 * Informuje o tym czy wszyscy klienci s� pod��czeni do serwera.
	 */
	boolean allPlayersAreConected()
	{
		return getNumberOfClients() == objectOutputStreams.size();
	}
	
	/**
	 * Zwraca maksymaln� liczb� klient�w pod��czonych do serwera.
	 * 
	 * @return
	 */
	private synchronized int getNumberOfClients()
	{
		return numberOfClients;
	}

	/**
	 * Ustawia maksymaln� liczb� klient�w pod��czonych do serwera. 
	 * 
	 * @param numberOfClients
	 */
	private synchronized void setNumberOfClients(final int numberOfClients)
	{
		this.numberOfClients = numberOfClients;
	}
	
	/**
	 * Odpowiada za komunikacj� serwera z klientami.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private class PlayerConected implements Runnable
	{
		/**ID klienta*/
		private final PlayerID playerID;
		/**Socket klienta*/
		private final Socket socket;

		PlayerConected(final PlayerID playerID, final Socket socket)
		{
			this.playerID = playerID;
			this.socket = socket;
		}

		/**
		 * Oczekuje na informacje od klient�w
		 */
		@Override
		public void run()
		{
			ObjectInputStream objectInputStream;
			try
			{
				objectInputStream = new ObjectInputStream(socket.getInputStream());
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
			while (sockets.containsValue(socket))
			{
				PlayerEvent playerEvent;
				try
				{
					playerEvent = (PlayerEvent) objectInputStream.readObject();
					playerEvent.setID(playerID);
					blockingQueue.add(playerEvent);
				} 
				catch (IOException e)
				{
					objectOutputStreams.remove(playerID);
					sockets.remove(playerID);
					sendAllPlayersMessage(new InfoMessage("Gracz " + playerID.getPlayerID() + " zako�czy� gre "));
					break;
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				blockingQueue.put(new EndGameEvent());
			} catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Serwer gry Snake
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private class Server implements Runnable
	{
		/** Socket serwera */
		private ServerSocket serverSocket;

		Server(final int howManyPlayers)
		{
			setNumberOfClients(howManyPlayers);

		}

		/**
		 * Nasluchuje na porcie 5555 po czym gdy wszyscy gracze si� pod��cz� uruchamia gre.
		 */
		@Override
		public void run()
		{
			try
			{
				serverSocket = new ServerSocket(5555);
			} 
			catch (Exception e)
			{

				e.printStackTrace();
			}
			for (int i = 1; i <= getNumberOfClients(); ++i)
			{
				Socket tmp;
				try
				{
					tmp = serverSocket.accept();
					sockets.put(new PlayerID(i), tmp);
					objectOutputStreams.put(new PlayerID(i), new ObjectOutputStream(tmp.getOutputStream()));
					PlayerConected newPlayer = new PlayerConected(new PlayerID(i), tmp);
					Thread t = new Thread(newPlayer);
					t.start();

				} catch (IOException e)
				{
					System.out.println("Nie mo�na utworzy� serwera");
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			Thread timer = new Thread(new Timer(blockingQueue));
			timer.start();
			try
			{
				blockingQueue.put(new NewGameEvent());
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
