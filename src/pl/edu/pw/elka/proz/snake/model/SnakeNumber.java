package pl.edu.pw.elka.proz.snake.model;

/**
 * Identyfikuje numer w�a.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public enum SnakeNumber
{
	FIRST(1), SECOND(2), THIRD(3), FOURTH(4) ;

	/** numer w�a */
	private final int number;

	private SnakeNumber(final int number)
	{
		this.number = number;
	}

	/** 
	 * Zwraca id w�a. 
	 */
	public int getNumber()
	{
		return number;
	}
}
