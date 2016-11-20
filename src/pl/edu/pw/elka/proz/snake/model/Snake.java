package pl.edu.pw.elka.proz.snake.model;

import java.util.ArrayList;
import java.util.LinkedList;

import pl.edu.pw.elka.proz.snake.fakes.BodyFake;
import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.fakes.FakePoint;

/**
 * Zawiera informacje o w�u.
 * 
 * @author Marcin Wlaz�y
 * @version 20110528
 */
class Snake
{
	/** Zawiera liste cz�ci cia�a z kt�rych sk�ada si� w��.*/
	private final LinkedList<SnakeBody> snake = new LinkedList<SnakeBody>();
	/** Kierunek w kt�rym porusza si� w��.*/
	private Direction direction;
	/** Stan w kt�rym znajduje si� w��.*/
	private SnakeStatus stanSnake = SnakeStatus.ALIVE;
	/** Liczba zdobytych punkt�w przez w꿹.*/
	private int score = 0;
	/** K�t o jaki porusza si� w�� */
	private static int angle = 20;
	/** Numer identyfikacyjny w꿹 */
	private final SnakeNumber snakeNumber;
	
	/**
	 * Tworzy nowego w�a.
	 * 
	 * @param alfa k�t alfa ogonu
	 * @param beta k�t beta ogonu
	 * @param snakeNumber id W�a
	 * @param emptyPoints lista pustych punkt�w na planszy
	 */
	Snake(final int alfa, final int beta, final SnakeNumber snakeNumber, final ArrayList<EmptyPoint> emptyPoints)
	{
		direction = Direction.EAST;
		makeSnake(alfa, beta, emptyPoints);
		this.snakeNumber = snakeNumber;

	}
	
	/**
	 * Zwraca k�t o jaki porusza si� w��.
	 * 
	 * @return k�t ruchu
	 */
	static int getAngle()
	{
		return angle;
	}

	/**
	 * Sprawdza czy dane pole jest zaj�te przez w�a.
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
	 * Wpisuje do mapy fake'�w wszystkie punkty kt�re zajmuje w��.
	 * 
	 * @param mapFake fake mapa do kt�rej wpisujemy cz�ci cia�a w�a
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
	 * Zwraca kierunek w kt�rym porusza si� w��.
	 */
	Direction getDirection()
	{
		return direction;
	}
	
	/**
	 * Zwraca g�ow� w�a.
	 * 
	 * @return referencje na g�ow� w�a
	 */
	SnakeBody getFirst()
	{
		return snake.getFirst();
	}

	/**
	 * Zwraca liste cz�ci cia�a z kt�rych sk�ada si� w��.
	 */
	LinkedList<SnakeBody> getList()
	{
		return snake;
	}
	
	/**
	 * Aktualna liczba punkt�w posiadanych przez w�a.
	 * 
	 * @return ilczba punkt�w posiadanych przez w�a
	 */
	int getScore()
	{
		return score;
	}
	
	/**
	 * Zmienia kierunek w�a na wschodni.
	 */
	void goEast()
	{
		direction = direction.goEast();
	}

	/**
	 * Zmienia kierunek w�a na p�nocny.
	 */
	void goNorth()
	{
		direction = direction.goNorth();
	}

	/**
	 * Zmienia kierunek w�a na po�udniowy.
	 */
	void goSouth()
	{
		direction = direction.goSouth();
	}

	/**
	 * Zmienia kierunek w�a na zachodni.
	 */
	void goWest()
	{
		direction = direction.goWest();
	}
	
	/**
	 * Informuje o tym czy w�� bierze udzia� w rozgrywce.
	 * 
	 * @return true je�li w�� �yje false w przeciwnym wypadku
	 */
	int inGame()
	{
		return stanSnake.getStan();
	}

	/**
	 * Zwraca informacje czy w�� nie �yje.
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
	 * Sprawdza czy w�� nie uderzy� w przeciwnika.
	 * 
	 * @param anotherSnake referencja na przeciwnika
	 * @param emptyPoints lista pustych punkt�w na planszy.
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
	 * Zmienia stan w�a na nie �ywy.
	 */
	void killSnake()
	{
		stanSnake = stanSnake.uderzyl();
	}
	
	/**
	 * Przemieszcza w�a wraz ze zjedzeniem jab�ka.
	 * 
	 * @param emptyPoints referencja na liste wolnych punkt�w na planszy
	 */
	void moveSnakeWithEatApple(final ArrayList<EmptyPoint> emptyPoints)
	{
		score += 10;
		moveSnakeHead(emptyPoints);
	}

	/**
	 * Przemieszcza w�a bez zjadania jab�ka.
	 * 
	 * @param emptyPoints lista pustych punkt�w na planszy
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
	 * Zwraca informacje o tym czy w�� �yje.
	 * 
	 * @return true je�li �yje false w przeciwnym wypadku
	 */
	boolean snakeIsAlive()
	{
		return stanSnake.getStan() == 1;
	}
	
	/**
	 * Oblicza wsp�rz�dne nowego po�o�enia cz�ci cia�a w�a.
	 * 
	 * @return nowa cz�� cia�a
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
	 * Oblicza nowy k�t.
	 * 
	 * @param oldAngle 
	 * @param isMove okre�la czy w�� przemieszcza sie w tym kierunku
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
	 * Przemieszcza g�ow� w�a
	 * 
	 * @param emptyPoints lista pustych punkt�w na planszy
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
	 * Tworzy w�a.
	 * 
	 * @param alfa warto�c k�ta alfa ogonu w�a
	 * @param beta warto�� k�ta beta ogonu w�a
	 * @param emptyPoints lista pustych punkt�w na planszy
	 */
	private void makeSnake(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		setTail(alfa, beta, emptyPoints);
		setBody(alfa + angle, beta, emptyPoints);
		setHead(alfa + 2 * angle, beta, emptyPoints);
	}

	/**
	 * Dodaje cz�� cia�a do listy cz�ci cia�a w�a.
	 * 
	 * @param alfa k�t alfa cz�ci cia�a
	 * @param beta k�t beta cz�ci cia�a
	 * @param emptyPoints lista pustych punkt�w na planszy
	 */
	private void setBody(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		emptyPoints.remove(new EmptyPoint(new Coordinates(alfa, beta)));
		snake.addFirst(new SnakeBody(new Coordinates(alfa, beta), direction, direction));
	}

	/**
	 * Dodaje g�owe do listy cz�ci cia�a w�a
	 * 
	 * @param alfa k�t alfa g�owy
	 * @param beta k�t beta g�owy
	 * @param emptyPoints lista pustych punkt�w na planszy
	 */
	private void setHead(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		emptyPoints.remove(new EmptyPoint(new Coordinates(alfa, beta)));
		snake.addFirst(new SnakeBody(new Coordinates(alfa, beta), direction, Direction.UNKNOW));
	}

	/**
	 * Dodaje ogon do listy cz�ci cia�a w�a
	 * 
	 * @param alfa k�t alfa ogonu w�a
	 * @param beta k�t beta ogonu w�a
	 * @param emptyPoints lista pustych punkt�w na planszy
	 */
	private void setTail(final int alfa, final int beta, final ArrayList<EmptyPoint> emptyPoints)
	{
		emptyPoints.remove(new EmptyPoint(new Coordinates(alfa, beta)));
		snake.addFirst(new SnakeBody(new Coordinates(alfa, beta), Direction.UNKNOW, direction));
	}
}
