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
 * @author Marcin Wlaz³y
 * @version 20110604
 */
public class Controler
{
	/** Model któreg metody wywo³uje kontroler. */
	private final Model model;
	/** Kolejka blokuj¹ca do której wrzucane s¹ eventy. */
	private final BlockingQueue<GameEvent> blockingQueue;
	/** Maksymalna liczba klientów. */
	private final int howManyClients;
	/** Liczba wê¿y w grze. */
	private final int snakes;
	/** Modu³ sieciowy. */
	private final NetworkModule networkModule;
	/** Mapa akcji zwi¹zanych z eventami wrzucanymi do kolejki blokuj¹cej. */
	private final Map<Class<? extends GameEvent>, GameAction> actions;
	/** Mapa akcji dotycz¹cych zmiany kierunku wê¿a */
	private final Map<Class<? extends PlayerEvent>, TurningAction> turningAction;
	/** Mapa zawieraj¹ca listy akcji dotycz¹cych konkretnego wê¿a*/
	private final Map<SnakeNumber, LinkedList<GameEvent>> playerEvent;
	/** Mapa zawieraj¹ca odwzorowania setów klawiszy na wê¿e którymi steruj¹*/
	private final Map<KeySetID, SnakeNumber> keySetIDMap;
	/** Mapa zawieraj¹ca odwzorowania klientów na wê¿e którymi steruj¹*/
	private final Map<PlayerID, SnakeNumber> playerIDMap;
	
	/**
	 * Tworzy nowy obiekt kontrolera.
	 * 
	 * @param model
	 * @param blockingQueue
	 * @param clients liczba klientów bior¹cych udzia³ w rozgrywce
	 * @param snakes liczba wê¿y na planszy
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
	 * Rozpoczyna dzia³anie kontrolera.
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
	 * Wype³nia mape odwzorowañ setów klawiszy na numery wê¿y.
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
	 * Wype³nia mape odwzorowañ ID klientów na numery wê¿y.
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
	 * Tworzy listy eventów dotycz¹cych wê¿y.
	 * Dla ka¿dego wê¿a tworzona jest osobna kolejka w której s¹ przechowywane 
	 * informacje o naciœniêciach klawiszy pomiêdzy przerwaniami zegara.
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
	 * Wype³nia mape akcji zwi¹zanych z nadchodz¹cymi eventami.
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
	 * Wype³nia mape akcji zwi¹zanych ze zmian¹ kierunku wê¿a.
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
	 * Wysy³a graczom informacje o zakoñczeniu gry.
	 */
	private void sendEndInformation()
	{
		if (!model.inGame())
		{
			networkModule.sendAllPlayersMessage(new InfoMessage(Messages.getString("Controler.2")));
		}
	}

	/**
	 * Wysy³a graczom mape fake'ów.
	 */
	private void sendFakeBoardAllClients()
	{
			FakeMap fakeMap = model.getFake();
			networkModule.sendAllPlayersFakeMap(fakeMap);
	}

	/**
	 * Wysy³a graczom informacje o wynikach.
	 */
	private void sendScoreInformation()
	{
		networkModule.sendAllPlayersScore(new ScoreMessage(model.getScoreFakeMap()));
	}
	
	/**
	 * Klasa odpowiedzialna za wykonanie akcji zwi¹zanych z eventami wrzucanymi do kolejki blokuj¹cej.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110602
	 */
	private abstract class GameAction
	{
		abstract void perform(final GameEvent gameEvent);
	}
	
	/**
	 * Zakoñczenie gry.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Rozpoczêcie nowej gry.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Naciœniecie strza³ki w dó³.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Naciœniêcie strza³ki w lewo.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Naciœniêcie strza³ki w prawo.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Naciœniêcie strza³ki w góre.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Przerwanie zegarowe inforumj¹ce o koniecznoœci wykonania ruchu przez wê¿e.
	 * 
	 * @author Marcin Wlaz³y
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
	 * @author Marcin Wlaz³y
	 * @version 20110603
	 */
	private abstract class TurningAction
	{
		abstract void perform(SnakeNumber snakeNumber);
	}
	
	/**
	 * Zmiana kierunku na wschodni.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Zmiana kierunku na pó³nocny.
	 * 
	 * @author Marcin Wlaz³y
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
	 * Zmiana kierunku na po³udniowy.
	 * 
	 * @author Marcin Wlaz³y
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
	 * @author Marcin Wlaz³y
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
