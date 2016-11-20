package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Naci�ni�cie strza�ki w d� lub odpowiednika przez gracza.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public class PressDownKeyEvent extends KeyEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Tworzy nowy event zwi�zany z naci�ni�ciem strza�ki z d�.
	 *  
	 * @param whichSetKeys
	 */
	public PressDownKeyEvent(final KeySetID whichSetKeys)
	{
		super(whichSetKeys);
	}
}
