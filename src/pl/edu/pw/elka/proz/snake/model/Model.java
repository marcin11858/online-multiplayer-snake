package pl.edu.pw.elka.proz.snake.model;

import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.fakes.ScoreFakeMap;

/**
 * Model gry.
 * 
 * @author Marcin Wlaz�y
 * @version 20110528
 */
public class Model
{
	/** Plansza na kt�rej trwa rozgrywka */
	private final Board board;

	/**
	 * Tworzy model.
	 */
	public Model()
	{
		board = new Board();
	}

	/** 
	 * Ko�czy gre. 
	 */
	public void endGame()
	{
		board.endGame();
	}

	/** 
	 * Zwraca informace czy gra zosta�a zako�czona.
	 */
	public boolean gameIsEnd()
	{
		return board.gameIsEnd();
	}

	/** 
	 * Zwraca ca�� plansze fake'�w.
	 */
	public FakeMap getFake()
	{
		return board.getFake();
	}

	/** 
	 * Zmienia kierunek w�a na wschodni.
	 * 
	 * @param id w�a
	 */
	public void goEast(SnakeNumber snakeNumber)
	{
		board.goEast(snakeNumber);
	}

	/** 
	 * Zmienia kierunek w�a na p�nocny.
	 * 
	 * @param id w�a
	 */
	public void goNorth(SnakeNumber snakeNumber)
	{
		board.goNorth(snakeNumber);
	}

	/** 
	 * Zmienia kierunek w�a na po�udniowy.
	 * 
	 * @param id w�a
	 */
	public void goSouth(SnakeNumber snakeNumber)
	{
		board.goSouth(snakeNumber);
	}

	/** 
	 * Zmienia kierunek w�a na zachodni.
	 * 
	 * @param id w�a
	 */
	public void goWest(SnakeNumber snakeNumber)
	{
		board.goWest(snakeNumber);
	}

	/** 
	 * Zwraca informacje o tym czy gra si� toczy.
	 */
	public boolean inGame()
	{
		return board.inGame();
	}

	/** 
	 * Rozpoczyna gre z okre�lona liczb� w�y.
	 */
	public void startGame(final int howManySnakes)
	{
		board.startGame(howManySnakes);
	}

	/** 
	 * Wykonuje ruch w�y, przemieszcza w�e 
	 */
	public void moveSnakes()
	{
		board.moveSnakes();
	}

	/**
	 * Zwraca informacje o wynikach w�y
	 * 
	 * @return mapa wynik�w
	 */
	public ScoreFakeMap getScoreFakeMap()
	{
		return board.getScoreFakeMap();
	}

}
