package pl.edu.pw.elka.proz.snake.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.edu.pw.elka.proz.snake.fakes.AppleFake;
import pl.edu.pw.elka.proz.snake.fakes.EmptyFake;
import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.fakes.FakePoint;
import pl.edu.pw.elka.proz.snake.fakes.ScoreFake;
import pl.edu.pw.elka.proz.snake.fakes.ScoreFakeMap;

/**
 * Przechowuje informacje o planszy gry
 * 
 * @author Marcin Wlaz�y
 * 
 */
class Board
{


	/** Lista w�y na planszy.*/
	private final Map<SnakeNumber, Snake> snake;
	/** Jab�ko na planszy.*/
	private final Apple apple = new Apple();
	/** Status gry.*/
	private GameStatus gameStatus = GameStatus.ENDED;
	/** Lista wolnych punkt�w na planszy.*/
	private final ArrayList<EmptyPoint> emptyPoints = new ArrayList<EmptyPoint>();

	/**
	 * Tworzy now� plansze.
	 */
	Board()
	{
		snake = new HashMap<SnakeNumber, Snake>();
		fillEmpty();
	}

	/**
	 * Sprawdza czy nie wyst�puje kolizja polegaj�ca na uderzeniu w bok przeciwnika.
	 * 
	 */
	void checkCollision()
	{
		for (SnakeNumber snakeNumber : SnakeNumber.values())
		{
			for (SnakeNumber secondSnakeNumber : SnakeNumber.values())
			{
				if (snake.containsKey(snakeNumber)&&snake.containsKey(secondSnakeNumber)&&secondSnakeNumber.getNumber() != snakeNumber.getNumber()&& snake.get(snakeNumber).snakeIsAlive() && snake.get(secondSnakeNumber).snakeIsAlive())
				{
					snake.get(secondSnakeNumber).isCollisionBetweenSnakes((snake.get(snakeNumber)),emptyPoints);
				}
			}
		}
	}

	/**
	 * Sprawdza czy nie wyst�puje szczeg�lny przypadek kolizji w kt�rej gin� oba w��.
	 * Kolizja czo�owa.
	 * 
	 */
	void checkHeadCollision()
	{
		
		for (SnakeNumber snakeNumber : SnakeNumber.values())
		{
			for (SnakeNumber secondSnakeNumber : SnakeNumber.values())
			{
				if (snake.containsKey(snakeNumber)&&snake.containsKey(secondSnakeNumber)&&secondSnakeNumber != snakeNumber && snake.get(snakeNumber).snakeIsAlive()&& snake.get(secondSnakeNumber).snakeIsAlive()&& snake.get(snakeNumber).getFirst().equals(snake.get(secondSnakeNumber).getFirst())&&snake.get(snakeNumber).getDirection() == snake.get(secondSnakeNumber).getDirection().turnBack())
				{
					snake.get(snakeNumber).killSnake();
					snake.get(secondSnakeNumber).killSnake();
				}
			}
		}
	}
	
	/**
	 * Sprawdza czy g�owy dw�ch w�y nie znajduj� si� w tym samym miejscu na planszy.
	 */
	void checkPlaceCollision()
	{
		
		for (SnakeNumber snakeNumber : SnakeNumber.values())
		{
			for (SnakeNumber secondSnakeNumber : SnakeNumber.values())
			{
				if (snake.containsKey(snakeNumber)&&snake.containsKey(secondSnakeNumber)&&secondSnakeNumber != snakeNumber && snake.get(snakeNumber).snakeIsAlive()&& snake.get(secondSnakeNumber).snakeIsAlive()&& snake.get(snakeNumber).getFirst().equals(snake.get(secondSnakeNumber).getFirst()))
				{
					snake.get(snakeNumber).killSnake();
					snake.get(secondSnakeNumber).killSnake();
				}
			}
		}
	}

	/**
	 * Ko�czy gre.
	 */
	void endGame()
	{
		gameStatus = gameStatus.endGame();
		snake.clear();
	}

	/**
	 * Wype�nia liste wolnych punkt�w.
	 * 
	 */
	private void fillEmpty()
	{
		for (int alfa = 0; alfa < 360; alfa += Snake.getAngle())
		{
			for (int beta = 0; beta < 360; beta += Snake.getAngle())
			{
				emptyPoints.add(new EmptyPoint(new Coordinates(alfa, beta)));

			}
		}
	}

	/**
	 * Informuje o tym czy gra zosta�a zako�czona w przypadku gdy gracz jeszcze
	 * o tym nie wie
	 * 
	 */
	boolean gameIsEnd()
	{
		if (!gameStatus.inGame())
		{
			return true;
		}
		return false;

	}


	/**
	 * Zwraca mape ca�ej planszy
	 * 
	 */
	FakeMap getFake()
	{

		FakeMap tab = new FakeMap();
		if (!inGame())
		{
			return tab;
		}
		for (int i = 0; i < (360); i += Snake.getAngle())
		{
			for (int j = 0; j < (360); j += Snake.getAngle())
			{
				tab.setFake(new FakePoint(new Coordinates(i, j)), new EmptyFake());
			}
		}

		for (Snake s : snake.values())
		{
			if (s.inGame() == 1)
			{
				s.fillFake(tab);
			}
		}

		if (gameStatus.inGame())
		{
			tab.setFake(new FakePoint(apple.getCoordinates()),
					new AppleFake());
		}
		return tab;
	}

	/**
	 * Zwraca informacje o akualnym wyniku w꿹
	 * 
	 * @param id
	 */
	int getScore(final int id)
	{
		return snake.get(id).getScore();
	}

	/** Zmienia kierunek w�a na East */
	void goEast(SnakeNumber snakeNumber)
	{
		if(snake.containsKey(snakeNumber))
		{
			(snake.get(snakeNumber)).goEast();
		}
	}

	/** Zmienia kierunek w�na na North */
	void goNorth(SnakeNumber snakeNumber)
	{
		if(snake.containsKey(snakeNumber))
		{
			(snake.get(snakeNumber)).goNorth();
		}
	}

	/** Zmienia kierunek w�a na South */
	void goSouth(SnakeNumber snakeNumber)
	{
		if(snake.containsKey(snakeNumber))
		{
			(snake.get(snakeNumber)).goSouth();
		}
	}

	/** Zmienia kierunek w�a na West. */
	void goWest(SnakeNumber snakeNumber)
	{
		if(snake.containsKey(snakeNumber))
		{
			(snake.get(snakeNumber)).goWest();
		}
	}

	/**
	 * Zwraca ilo�� �yj�cych w�y.
	 * 
	 * @return
	 */
	int howManyInGames()
	{
		int iluWGrze = 0;
		for (Snake s : snake.values())
		{
			iluWGrze += s.inGame();
		}
		return iluWGrze;
	}

	/**
	 * Zwraca informacje czy gra si� toczy
	 * 
	 * @return
	 */
	boolean inGame()
	{
		return gameStatus.inGame();
	}

	/**
	 * Inforuj� czy wynik w�a si� zmieni�
	 * 
	 * @param id
	 *            numer wybranego w꿹
	 */


	/**
	 * Wykonuje ruch w�y
	 * 
	 */
	void moveSnakes()
	{
		if (!gameStatus.inGame())
		{
			return;
		}
		for (Snake s : snake.values())
		{
			if(!(s.isNotAlive()))
			{
				if (apple.isApple(s.newBody()))
				{
					s.moveSnakeWithEatApple(emptyPoints);
				} else
				{
					s.moveSnakeWithoutEatApple(emptyPoints);
				}
				checkHeadCollision();
			}
		}
		if (apple.isEaten())
		{
			
			apple.chooseNewPoint(emptyPoints);
			
		}
		checkPlaceCollision();
		checkCollision();
		if (noSnakesInGame())
		{
			gameStatus = gameStatus.endGame();
		}

	}

	/**
	 * Inforumej o tym �e wszystkie w�e zgine�y
	 * 
	 * @return
	 */
	boolean noSnakesInGame()
	{
		return (howManyInGames() == 0);
	}

	/**
	 * Rozpoczyna gre o okre�lon� liczb� w�y
	 * 
	 * @param howManySnakes
	 */
	void startGame(final int howManySnakes)
	{
		snake.clear();
		gameStatus = GameStatus.INGAME;
		for(SnakeNumber snakeNumber : SnakeNumber.values())
		{
			snake.put(snakeNumber, new Snake (5 * Snake.getAngle(), snakeNumber.getNumber() * 3 * Snake.getAngle(), snakeNumber, emptyPoints ));
			if(snakeNumber.getNumber() == howManySnakes)
			{
				break;
			}
		}
		apple.chooseNewPoint(emptyPoints);
	}



	ScoreFakeMap getScoreFakeMap()
	{
		Map<SnakeNumber, ScoreFake> scoreFakeMap = new HashMap<SnakeNumber, ScoreFake>();
		for(SnakeNumber snakeNumber : snake.keySet())
		{
			scoreFakeMap.put(snakeNumber, new ScoreFake(snake.get(snakeNumber).getScore()));
		}
		return new ScoreFakeMap(scoreFakeMap);
	}
}
