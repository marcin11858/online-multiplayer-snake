package pl.edu.pw.elka.proz.snake.model;

import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.fakes.ScoreFakeMap;

/**
 * Model gry.
 * 
 * @author Marcin Wlaz³y
 * @version 20110528
 */
public class Model
{
	/** Plansza na której trwa rozgrywka */
	private final Board board;

	/**
	 * Tworzy model.
	 */
	public Model()
	{
		board = new Board();
	}

	/** 
	 * Koñczy gre. 
	 */
	public void endGame()
	{
		board.endGame();
	}

	/** 
	 * Zwraca informace czy gra zosta³a zakoñczona.
	 */
	public boolean gameIsEnd()
	{
		return board.gameIsEnd();
	}

	/** 
	 * Zwraca ca³¹ plansze fake'ów.
	 */
	public FakeMap getFake()
	{
		return board.getFake();
	}

	/** 
	 * Zmienia kierunek wê¿a na wschodni.
	 * 
	 * @param id wê¿a
	 */
	public void goEast(SnakeNumber snakeNumber)
	{
		board.goEast(snakeNumber);
	}

	/** 
	 * Zmienia kierunek wê¿a na pó³nocny.
	 * 
	 * @param id wê¿a
	 */
	public void goNorth(SnakeNumber snakeNumber)
	{
		board.goNorth(snakeNumber);
	}

	/** 
	 * Zmienia kierunek wê¿a na po³udniowy.
	 * 
	 * @param id wê¿a
	 */
	public void goSouth(SnakeNumber snakeNumber)
	{
		board.goSouth(snakeNumber);
	}

	/** 
	 * Zmienia kierunek wê¿a na zachodni.
	 * 
	 * @param id wê¿a
	 */
	public void goWest(SnakeNumber snakeNumber)
	{
		board.goWest(snakeNumber);
	}

	/** 
	 * Zwraca informacje o tym czy gra siê toczy.
	 */
	public boolean inGame()
	{
		return board.inGame();
	}

	/** 
	 * Rozpoczyna gre z okreœlona liczb¹ wê¿y.
	 */
	public void startGame(final int howManySnakes)
	{
		board.startGame(howManySnakes);
	}

	/** 
	 * Wykonuje ruch wê¿y, przemieszcza wê¿e 
	 */
	public void moveSnakes()
	{
		board.moveSnakes();
	}

	/**
	 * Zwraca informacje o wynikach wê¿y
	 * 
	 * @return mapa wyników
	 */
	public ScoreFakeMap getScoreFakeMap()
	{
		return board.getScoreFakeMap();
	}

}
