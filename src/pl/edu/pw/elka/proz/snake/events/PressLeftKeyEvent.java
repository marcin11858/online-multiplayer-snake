package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Naciœniecie strza³ki w lewo lub odpowiednika przez gracza.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public class PressLeftKeyEvent extends KeyEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Tworzy nowy event zwi¹zany z naciœniêciem strza³ki w lewo.
	 * @param whichSetKeys
	 */
	public PressLeftKeyEvent(final KeySetID whichSetKeys)
	{
		super(whichSetKeys);
	}
}
