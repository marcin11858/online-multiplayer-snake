package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Naciœniecie strza³ki w prawo lub odpowiednika przez gracza.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public class PressRightKeyEvent extends KeyEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Tworzy nowy obiek zwi¹zany z naciœniêciem strza³ki w prawo.
	 * 
	 * @param whichSetKeys
	 */
	public PressRightKeyEvent(final KeySetID whichSetKeys)
	{
		super(whichSetKeys);
	}
}
