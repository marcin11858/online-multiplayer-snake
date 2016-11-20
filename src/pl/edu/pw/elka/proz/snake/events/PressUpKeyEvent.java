package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Naci�niecie strza�ki w g�re lub odpowiednika przez gracza.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public class PressUpKeyEvent extends KeyEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Tworzy nowy obiekt zwi�zany z naci�ni�ciem klawisza w g�re.
	 * 
	 * @param whichSetKeys
	 */
	public PressUpKeyEvent(final KeySetID whichSetKeys)
	{
		super(whichSetKeys);
	}
}
