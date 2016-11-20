package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Naciœniecie strza³ki w góre lub odpowiednika przez gracza.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public class PressUpKeyEvent extends KeyEvent implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Tworzy nowy obiekt zwi¹zany z naciœniêciem klawisza w góre.
	 * 
	 * @param whichSetKeys
	 */
	public PressUpKeyEvent(final KeySetID whichSetKeys)
	{
		super(whichSetKeys);
	}
}
