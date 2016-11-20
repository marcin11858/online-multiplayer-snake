package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Naciœniêcie strza³ki w dó³ lub odpowiednika przez gracza.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public class PressDownKeyEvent extends KeyEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Tworzy nowy event zwi¹zany z naciœniêciem strza³ki z dó³.
	 *  
	 * @param whichSetKeys
	 */
	public PressDownKeyEvent(final KeySetID whichSetKeys)
	{
		super(whichSetKeys);
	}
}
