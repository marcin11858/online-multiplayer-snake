package pl.edu.pw.elka.proz.snake.controler;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.edu.pw.elka.proz.snake.events.EndGameEvent;
import pl.edu.pw.elka.proz.snake.events.GameEvent;
import pl.edu.pw.elka.proz.snake.events.NewGameEvent;
import pl.edu.pw.elka.proz.snake.events.PlayerEvent;
import pl.edu.pw.elka.proz.snake.events.PressDownKeyEvent;
import pl.edu.pw.elka.proz.snake.events.PressLeftKeyEvent;
import pl.edu.pw.elka.proz.snake.events.PressRightKeyEvent;
import pl.edu.pw.elka.proz.snake.events.PressUpKeyEvent;
import pl.edu.pw.elka.proz.snake.events.TimerEvent;
import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.message.InfoMessage;
import pl.edu.pw.elka.proz.snake.message.ScoreMessage;
import pl.edu.pw.elka.proz.snake.model.Model;
import pl.edu.pw.elka.proz.snake.model.SnakeNumber;
import pl.edu.pw.elka.proz.snake.snake.KeySetID;
import pl.edu.pw.elka.proz.snake.snake.PlayerID;

/**
 * Kontroler gry.
 * 
 * @author Marcin Wlaz�y
 * @version 20110604
 */
public class Controler
{
	/** Model kt�reg metody wywo�uje kontroler. */
	private final Model model;
	/** Kolejka blokuj�ca do kt�rej wrzucane s� eventy. */
	private final BlockingQueue<GameEvent> blockingQueue;
	/** Maksymalna liczba klient�w. */
	private final int howManyClients;
	/** Liczba w�y w grze. */
	private final int snakes;
	/** Modu� sieciowy. */
	private final NetworkModule networkModule;
	/** Mapa akcji zwi�zanych z eventami wrzucanymi do kolejki blokuj�cej. */
	private final Map<Class<? extends GameEvent>, GameAction> actions;
	/** Mapa akcji dotycz�cych zmiany kierunku w�a */
	private final Map<Class<? extends PlayerEvent>, TurningAction> turningAction;
	/** Mapa zawieraj�ca listy akcji dotycz�cych konkretnego w�a*/
	private final Map<SnakeNumber, LinkedList<GameEvent>> playerEvent;
	/** Mapa zawieraj�ca odwzorowania set�w klawiszy na w�e kt�rymi steruj�*/
	private final Map<KeySetID, SnakeNumber> keySetIDMap;
	/** Mapa zawieraj�ca odwzorowania klient�w na w�e kt�rymi steruj�*/
	private final Map<PlayerID, SnakeNumber> playerIDMap;
	
	/**
	 * Tworzy nowy obiekt kontrolera.
	 * 
	 * @param model
	 * @param blockingQueue
	 * @param clients liczba klient�w bior�cych udzia� w rozgrywce
	 * @param snakes liczba w�y na planszy
	 */
	public Controler(final Model model, final BlockingQueue<GameEvent> blockingQueue, final int clients, final int snakes)
	{
		this.model = model;
		this.blockingQueue = blockingQueue;
		this.howManyClients = clients;
		this.snakes = snakes;
		this.networkModule = new NetworkModule(blockingQueue);
		actions = fillActions();
		turningAction = fillTurning();
		playerEvent = fillPlayerEvent();
		keySetIDMap = fillKeySetIDMap();
		playerIDMap = fillPlayerIDMap();
		networkModule.begin(howManyClients);
	}
	
	/**
	 * Rozpoczyna dzia�anie kontrolera.
	 */
	public void begin()
	{
		while(true)
		{
			try
			{
				GameEvent gameEvent = blockingQueue.take();
				actions.get(gameEvent.getClass()).perform(gameEvent);
			} 
			catch (Exception e)
			{
				System.out.println(Messages.getString("Controler.3")); 
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Wype�nia mape odwzorowa� set�w klawiszy na numery w�y.
	 * 
	 * @return keySetIDMap
	 */
	private Map<KeySetID, SnakeNumber> fillKeySetIDMap()
	{
		Map<KeySetID, SnakeNumber> keySetIDMap = new HashMap<KeySetID, SnakeNumber>();
		keySetIDMap.put(new KeySetID(1), SnakeNumber.FIRST);
		keySetIDMap.put(new KeySetID(2), SnakeNumber.SECOND);
		keySetIDMap.put(new KeySetID(3), SnakeNumber.THIRD);
		keySetIDMap.put(new KeySetID(4), SnakeNumber.FOURTH);
		return Collections.unmodifiableMap(keySetIDMap);
	}
	
	/**
	 * Wype�nia mape odwzorowa� ID klient�w na numery w�y.
	 * 
	 * @return playerIDMap
	 */
	private Map<PlayerID, SnakeNumber> fillPlayerIDMap()
	{
		Map<PlayerID, SnakeNumber> playerIDMap = new HashMap<PlayerID, SnakeNumber>();
		playerIDMap.put(new PlayerID(1), SnakeNumber.FIRST);
		playerIDMap.put(new PlayerID(2), SnakeNumber.SECOND);
		playerIDMap.put(new PlayerID(3), SnakeNumber.THIRD);
		playerIDMap.put(new PlayerID(4), SnakeNumber.FOURTH);
		return Collections.unmodifiableMap(playerIDMap);
	}
	
	/**
	 * Tworzy listy event�w dotycz�cych w�y.
	 * Dla ka�dego w�a tworzona jest osobna kolejka w kt�rej s� przechowywane 
	 * informacje o naci�ni�ciach klawiszy pomi�dzy przerwaniami zegara.
	 * 
	 * @return playerEvent
	 */
	private Map<SnakeNumber, LinkedList<GameEvent>> fillPlayerEvent()
	{
		Map<SnakeNumber, LinkedList<GameEvent>> playerEvent = new HashMap<SnakeNumber, LinkedList<GameEvent>>();
		playerEvent.put(SnakeNumber.FIRST, new LinkedList<GameEvent>());
		playerEvent.put(SnakeNumber.SECOND, new LinkedList<GameEvent>());
		playerEvent.put(SnakeNumber.THIRD, new LinkedList<GameEvent>());
		playerEvent.put(SnakeNumber.FOURTH, new LinkedList<GameEvent>());
		return Collections.unmodifiableMap(playerEvent);
	}
	
	/**
	 * Wype�nia mape akcji zwi�zanych z nadchodz�cymi eventami.
	 * 
	 * @return actions
	 */
	private Map<Class<? extends GameEvent>, GameAction> fillActions()
	{
		Map<Class<? extends GameEvent>, GameAction> actions = new HashMap<Class<? extends GameEvent>, GameAction>();
		actions.put(PressDownKeyEvent.class, new PressDownAction());
		actions.put(PressLeftKeyEvent.class, new PressLeftAction());
		actions.put(PressRightKeyEvent.class, new PressRightAction());
		actions.put(PressUpKeyEvent.class, new PressUpAction());
		actions.put(TimerEvent.class, new TimerAction());
		actions.put(NewGameEvent.class, new NewGameAction());
		actions.put(EndGameEvent.class, new EndGameAction());
		return Collections.unmodifiableMap(actions);
	}

	/**
	 * Wype�nia mape akcji zwi�zanych ze zmian� kierunku w�a.
	 * 
	 * @return turningAction
	 */
	private Map<Class<? extends PlayerEvent>, TurningAction> fillTurning()
	{
		Map<Class<? extends PlayerEvent>, TurningAction> turningAction = new HashMap<Class<? extends PlayerEvent>, TurningAction>();
		turningAction.put(PressUpKeyEvent.class, new GoNorth());
		turningAction.put(PressDownKeyEvent.class, new GoSouth());
		turningAction.put(PressLeftKeyEvent.class, new GoWest());
		turningAction.put(PressRightKeyEvent.class, new GoEast());
		return Collections.unmodifiableMap(turningAction);
	}

	/**
	 * Wysy�a graczom informacje o zako�czeniu gry.
	 */
	private void sendEndInformation()
	{
		if (!model.inGame())
		{
			networkModule.sendAllPlayersMessage(new InfoMessage(Messages.getString("Controler.2")));
		}
	}

	/**
	 * Wysy�a graczom mape fake'�w.
	 */
	private void sendFakeBoardAllClients()
	{
			FakeMap fakeMap = model.getFake();
			networkModule.sendAllPlayersFakeMap(fakeMap);
	}

	/**
	 * Wysy�a graczom informacje o wynikach.
	 */
	private void sendScoreInformation()
	{
		networkModule.sendAllPlayersScore(new ScoreMessage(model.getScoreFakeMap()));
	}
	
	/**
	 * Klasa odpowiedzialna za wykonanie akcji zwi�zanych z eventami wrzucanymi do kolejki blokuj�cej.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private abstract class GameAction
	{
		abstract void perform(final GameEvent gameEvent);
	}
	
	/**
	 * Zako�czenie gry.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private class EndGameAction extends GameAction
	{
		@Override
		void perform(final GameEvent gameEvent)
		{
			model.endGame();
		}
	}
	
	/**
	 * Rozpocz�cie nowej gry.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110602
	 */
	private class NewGameAction extends GameAction
	{
		@Override
		void perform(final GameEvent gameEvent)
		{
			if (networkModule.isMoreThanOnePlayer()&& (!networkModule.allPlayersAreConected()))
			{
				return;
			}
			for(LinkedList<GameEvent> liknedList : playerEvent.values())
			{
				liknedList.clear();
			}
			model.startGame(snakes);
		}
	}

	/**
	 * Naci�niecie strza�ki w d�.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class PressDownAction extends GameAction
	{
		@Override
		void perform(final GameEvent gameEvent)
		{
			if(networkModule.isMoreThanOnePlayer())
			{
				PressDownKeyEvent pressDownKeyEvent = (PressDownKeyEvent)gameEvent;
				if(pressDownKeyEvent.isBasicSet())
				{
					playerEvent.get(playerIDMap.get(pressDownKeyEvent.getID())).addLast(pressDownKeyEvent);
				}
			}
			else
			{
				PressDownKeyEvent pressDownKeyEvent = (PressDownKeyEvent) gameEvent;
				playerEvent.get(keySetIDMap.get(pressDownKeyEvent.getWhichSetKeys())).addLast(pressDownKeyEvent);
			}
		}
	}

	/**
	 * Naci�ni�cie strza�ki w lewo.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class PressLeftAction extends GameAction
	{
		@Override
		void perform(final GameEvent gameEvent)
		{
			if(networkModule.isMoreThanOnePlayer())
			{
				PressLeftKeyEvent pressLeftKeyEvent = (PressLeftKeyEvent)gameEvent;
				if(pressLeftKeyEvent.isBasicSet())
				{
					playerEvent.get(playerIDMap.get(pressLeftKeyEvent.getID())).addLast(pressLeftKeyEvent);
				}
			}
			else
			{
				PressLeftKeyEvent pressLeftKeyEvent = (PressLeftKeyEvent) gameEvent;
				playerEvent.get(keySetIDMap.get(pressLeftKeyEvent.getWhichSetKeys())).addLast(pressLeftKeyEvent);
			}
		}
	}

	/**
	 * Naci�ni�cie strza�ki w prawo.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class PressRightAction extends GameAction
	{
		@Override
		void perform(final GameEvent gameEvent)
		{
			if(networkModule.isMoreThanOnePlayer())
			{
				PressRightKeyEvent pressRightKeyEvent = (PressRightKeyEvent)gameEvent;
				if(pressRightKeyEvent.isBasicSet())
				{
					playerEvent.get(playerIDMap.get(pressRightKeyEvent.getID())).addLast(pressRightKeyEvent);
				}
			}
			else
			{
				PressRightKeyEvent pressRightKeyEvent = (PressRightKeyEvent) gameEvent;
				playerEvent.get(keySetIDMap.get(pressRightKeyEvent.getWhichSetKeys())).addLast(pressRightKeyEvent);
			}
		}
	}

	/**
	 * Naci�ni�cie strza�ki w g�re.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class PressUpAction extends GameAction
	{
		@Override
		void perform(final GameEvent gameEvent)
		{
			if(networkModule.isMoreThanOnePlayer())
			{
				PressUpKeyEvent pressUpKeyEvent = (PressUpKeyEvent)gameEvent;
				if(pressUpKeyEvent.isBasicSet())
				{
					playerEvent.get(playerIDMap.get(pressUpKeyEvent.getID())).addLast(pressUpKeyEvent);
				}
			}
			else
			{
				PressUpKeyEvent pressUpKeyEvent = (PressUpKeyEvent) gameEvent;
				playerEvent.get(keySetIDMap.get(pressUpKeyEvent.getWhichSetKeys())).addLast(pressUpKeyEvent);
			}
		}
	}

	/**
	 * Przerwanie zegarowe inforumj�ce o konieczno�ci wykonania ruchu przez w�e.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class TimerAction extends GameAction
	{
		@Override
		void perform(final GameEvent gameEvent)
		{
			if (!model.inGame())
			{
				return;
			}
			for(SnakeNumber snakeNumber : playerEvent.keySet())
			{
				if(!(playerEvent.get(snakeNumber).isEmpty()))
				{
					turningAction.get(playerEvent.get(snakeNumber).getFirst().getClass()).perform(snakeNumber);
					playerEvent.get(snakeNumber).clear();
				}
			}
			model.moveSnakes();
			sendFakeBoardAllClients();
			sendEndInformation();
			sendScoreInformation();
		}
	}

	/**
	 * Abstrakcyjna klasa odpowiedzialna za zmiane kierunku.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private abstract class TurningAction
	{
		abstract void perform(SnakeNumber snakeNumber);
	}
	
	/**
	 * Zmiana kierunku na wschodni.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class GoEast extends TurningAction
	{
		@Override
		void perform(final SnakeNumber snakeNumber)
		{
			model.goEast(snakeNumber);
		}
	}

	/**
	 * Zmiana kierunku na p�nocny.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class GoNorth extends TurningAction
	{
		@Override
		void perform(final SnakeNumber snakeNumber)
		{
			model.goNorth(snakeNumber);
		}
	}

	/**
	 * Zmiana kierunku na po�udniowy.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class GoSouth extends TurningAction
	{
		@Override
		void perform(final SnakeNumber snakeNumber)
		{
			model.goSouth(snakeNumber);

		}
	}

	/**
	 * Zmiana kierunku na zachodni.
	 * 
	 * @author Marcin Wlaz�y
	 * @version 20110603
	 */
	private class GoWest extends TurningAction
	{
		@Override
		void perform(final SnakeNumber snakeNumber)
		{
			model.goWest(snakeNumber);
		}
	}
}
