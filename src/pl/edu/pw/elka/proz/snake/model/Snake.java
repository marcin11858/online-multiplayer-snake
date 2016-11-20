package pl.edu.pw.elka.proz.snake.model;

import java.util.ArrayList;
import java.util.LinkedList;

import pl.edu.pw.elka.proz.snake.fakes.BodyFake;
import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.fakes.FakePoint;

/**
 * Zawiera informacje o wê¿u.
 * 
 * @author Marcin Wlaz³y
 * @version 20110528
 */
class Snake
{
	/** Zawiera liste czêœci cia³a z których sk³ada siê w¹¿.*/
	private final LinkedList<SnakeBody> snake = new LinkedList<SnakeBody>();
	/** Kierunek w którym porusza siê w¹¿.*/
	private Direction direction;
	/** Stan w którym znajduje siê w¹¿.*/
	private SnakeStatus stanSnake = SnakeStatus.ALIVE;
	/** Liczba zdobytych punktów przez wê¿¹.*/
	private int score = 0;
	/** K¹t o jaki porusza siê w¹¿ */
	private static int angle = 20;
	/** Numer identyfikacyjny wê¿¹ */
	private final SnakeNumber snakeNumber;
	
	/**
	 * Tworzy nowego wê¿a.
	 * 
	 * @param alfa k¹t alfa ogonu
	 * @param beta k¹t beta ogonu
	 * @param snakeNumber id Wê¿a
	 * @param emptyPoints lista pustych punktów na planszy
	 */
	Snake(final int alfa, final int beta, final SnakeNumber snakeNumber, final ArrayList<EmptyPoint> emptyPoints)
	{
		direction = Direction.EAST;
		makeSnake(alfa, beta, emptyPoints);
		this.snakeNumber = snakeNumber;

	}
	
	/**
	 * Zwraca k¹t o jaki porusza siê w¹¿.
	 * 
	 * @return k¹t ruchu
	 */
	static int getAngle()
	{
		return angle;
	}

	/**
	 * Sprawdza czy dane pole jest zajête przez wê¿a.
	 * 
	 * @param snakeBody referencja na nowe pole
	 * @return
	 */
	boolean isBusy(final SnakeBody snakeBody)
	{
		if (inGame() == 0)
		{
			return false;
		}
		return snake.contains(snakeBody);
	}
	
	/**
	 * Wpisuje do mapy fake'ów wszystkie punkty które zajmuje w¹¿.
	 * 
	 * @param mapFake fake mapa do której wpisujemy czêœci cia³a wê¿a
	 */
	void fillFake(final FakeMap mapFake)
	{
		if (inGame() == 0)
		{
			return;
		}
		for (SnakeBody p : snake)
		{
			mapFake.setFake( new FakePoint(p.getCoordinates()), new BodyFake(snakeNumber, p.getFrom(), p.getWhere(), p.getBodySize()));
		}
	}
	
	/**
	 * Zwraca kierunek w którym porusza siê w¹¿.
	 */
	Direction getDirection()
	{
		return direction;
	}
	
	/**
	 * Zwraca g³owê wê¿a.
	 * 
	 * @return referencje na g³owê wê¿a
	 */
	SnakeBody getFirst()
	{
		return snake.getFirst();
	}

	/**
	 * Zwraca liste czêœci cia³a z których sk³ada siê w¹¿.
	 */
	LinkedList<SnakeBody> getList()
	{
		return snake;
	}
	
	/**
	 * Aktualna liczba punktów posiadanych przez wê¿a.
	 * 
	 * @return ilczba punktów posiadanych przez wê¿a
	 */
	int getScore()
	{
		return score;
	}
	
	/**
	 * Zmienia kierunek wê¿a na wschodni.
	 */
	void goEast()
	{
		direction = direction.goEast();
	}

	/**
	 * Zmienia kierunek wê¿a na pó³nocny.
	 */
	void goNorth()
	{
		direction = direction.goNorth();
	}

	/**
	 * Zmienia kierunek wê¿a na po³udniowy.
	 */
	void goSouth()
	{
		direction = direction.goSouth();
	}

	/**
	 * Zmienia kierunek wê¿a na zachodni.
	 */
	void goWest()
	{
		direction = direction.goWest();
	}
	
	/**
	 * Informuje o tym czy w¹¿ bierze udzia³ w rozgrywce.
	 * 
	 * @return true jeœli w¹¿ ¿yje false w przeciwnym wypadku
	 */
	int inGame()
	{
		return stanSnake.getStan();
	}

	/**
	 * Zwraca informacje czy w¹¿ nie ¿yje.
	 * 
	 * @return
	 */
	boolean isNotAlive()
	{
		if (stanSnake.isDead())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Sprawdza czy w¹¿ nie uderzy³ w przeciwnika.
	 * 
	 * @param anotherSnake referencja na przeciwnika
	 * @param emptyPoints lista pustych punktów na planszy.
	 */
	void isCollisionBetweenSnakes(final Snake anotherSnake, final ArrayList<EmptyPoint> emptyPoints)
	{
		if (stanSnake.isDead())
		{
			return;
		}
		LinkedList<SnakeBody> anotherSnakeList = anotherSnake.getList();
		for (SnakeBody p : anotherSnakeList)
		{
			if ((!(p.equals(anotherSnake.getFirst()))) && (snake.contains(p)))
			{
				stanSnake = stanSnake.uderzyl();
				for (SnakeBody snakeBody : snake)
				{
					if (!(snakeBody == snake.getFirst()))
					{
						emptyPoints.add(new EmptyPoint(snakeBody.getCoordinates()));
					}
				}
				break;
			}
		}
	}
	
	/**
	 * Zmienia stan wê¿a na nie ¿ywy.
	 */
	void killSnake()
	{
		stanSnake = stanSnake.uderzyl();
	}
	
	/**
	 * Przemieszcza wê¿a wraz ze zjedzeniem jab³ka.
	 * 
	 * @param emptyPoints referencja na liste wolnych punktów na planszy
	 */
	void moveSnakeWithEatApple(final ArrayList<EmptyPoint> emptyPoints)
	{
		score += 10;
		moveSnakeHead(emptyPoints);
	}

	/**
	 * Przemieszcza wê¿a bez zjadania jab³ka.
	 * 
	 * @param emptyPoints lista pustych punktów na planszy
	 */
	void moveSnakeWithoutEatApple(final ArrayList<EmptyPoint> emptyPoints)
	{
		if (stanSnake.isDead())
		{
			return;
		}
		emptyPoints.add(new EmptyPoint(snake.getLast().getCoordinates()));
		
		snake.removeLast();
		(snake.getLast()).setFrom(Direction.UNKNOW);

		
		moveSnakeHead(emptyPoints);
	}
	
	/**
	 * Zwraca informacje o tym czy w¹¿ ¿yje.
	 * 
	 * @return true jeœli ¿yje false w przeciwnym wypadku
	 */
	boolean snakeIsAlive()
	{
		return stanSnake.getStan() == 1;
	}
	
	/**
	 * Oblicza wspó³rzêdne nowego po³o¿enia czêœci cia³a wê¿a.
	 * 
	 * @return nowa czêœæ cia³a
	 */
	SnakeBody newBody()
	{
		SnakeBody tmp = snake.getFirst();
		int alfa = tmp.getCoordinates().getAlfa();
		int beta = tmp.getCoordinates().getBeta();
		alfa = getNewAngle(alfa, direction.getVectorAlfa());
		beta = getNewAngle(beta, direction.getVectorBeta());
		return new SnakeBody(new Coordinates(alfa, beta), direction, Direction.UNKNOW);
	}
	
	/**
	 * Oblicza nowy k¹t.
	 * 
	 * @param oldAngle 
	 * @param isMove okreœla czy w¹¿ przemieszcza sie w tym kierunku
	 * @return
	 */
	private static int getNewAngle(final int oldAngle, final int isMove)
	{
		if (isMove == 0)
		{
			return oldAngle;
		}
		int newAngle = oldAngle + isMove * angle;
		double x, y;
		x = Math.sin(Math.toRadians(newAngle));
		y = Math.cos(Math.toRadians(newAngle));
		if ((x + 2e-10) >= 0)
		{
			if (y < 0)
			{
				newAngle = 180 - (int) Math
						.round((Math.toDegrees(Math.asin(x))));
				return newAngle;
			} else
			{
				return (int) Math.round((Math.toDegrees(Math.asin(x))));
			}
		} 
		else
		{
			if (y < 0)
			{
				newAngle = 360 - (int) Math.round(Math.toDegrees(Math.acos(y)));
				return newAngle;
			} else
			{
				newAngle = 360 + (int) Math.round(Math.toDegrees(Math.asin(x)));
				return newAngle;
			}
		}
	}

	/**
	 * Przemieszcza g³owê wê¿a
	 * 
	 * @param emptyPoints lista pustych punktów na planszy
	 */
	private void moveSnakeHead(final ArrayList<EmptyPoint> emptyPoints)
	{
		if (stanSnake.isDead())
		{
			return;
		}
		SnakeBody tmp = newBody();
		if (isBusy(tmp))
		{
			killSnake();
			for (SnakeBody snakeBody : snake)
			{
				emptyPoints.add(new EmptyPoint(snakeBody.getCoordinates()));
			}
			return;
		}
		(snake.getFirst()).setTo(direction);
		
		snake.addFirst(tmp);
		emptyPoints.remove(new EmptyPoint(tmp.getCoordinates()));
	}
	
	/**
	 * Tworzy wê¿a.
	 * 
	 * @param alfa wartoœc k¹ta alfa ogonu wê¿a
	 * @param beta wartoœæ k¹ta beta ogonu wê¿a
	 * @param emptyPoints lista pustych punktów na planszy
	 */
	private void makeSnake(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		setTail(alfa, beta, emptyPoints);
		setBody(alfa + angle, beta, emptyPoints);
		setHead(alfa + 2 * angle, beta, emptyPoints);
	}

	/**
	 * Dodaje czêœæ cia³a do listy czêœci cia³a wê¿a.
	 * 
	 * @param alfa k¹t alfa czêœci cia³a
	 * @param beta k¹t beta czêœci cia³a
	 * @param emptyPoints lista pustych punktów na planszy
	 */
	private void setBody(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		emptyPoints.remove(new EmptyPoint(new Coordinates(alfa, beta)));
		snake.addFirst(new SnakeBody(new Coordinates(alfa, beta), direction, direction));
	}

	/**
	 * Dodaje g³owe do listy czêœci cia³a wê¿a
	 * 
	 * @param alfa k¹t alfa g³owy
	 * @param beta k¹t beta g³owy
	 * @param emptyPoints lista pustych punktów na planszy
	 */
	private void setHead(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		emptyPoints.remove(new EmptyPoint(new Coordinates(alfa, beta)));
		snake.addFirst(new SnakeBody(new Coordinates(alfa, beta), direction, Direction.UNKNOW));
	}

	/**
	 * Dodaje ogon do listy czêœci cia³a wê¿a
	 * 
	 * @param alfa k¹t alfa ogonu wê¿a
	 * @param beta k¹t beta ogonu wê¿a
	 * @param emptyPoints lista pustych punktów na planszy
	 */
	private void setTail(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		emptyPoints.remove(new EmptyPoint(new Coordinates(alfa, beta)));
		snake.addFirst(new SnakeBody(new Coordinates(alfa, beta), Direction.UNKNOW, direction));
	}
}
