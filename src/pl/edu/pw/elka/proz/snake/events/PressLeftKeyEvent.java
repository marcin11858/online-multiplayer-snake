package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Naci�niecie strza�ki w lewo lub odpowiednika przez gracza.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public class PressLeftKeyEvent extends KeyEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Tworzy nowy event zwi�zany z naci�ni�ciem strza�ki w lewo.
	 * @param whichSetKeys
	 */
	public PressLeftKeyEvent(final KeySetID whichSetKeys)
	{
		super(whichSetKeys);
	}
}
