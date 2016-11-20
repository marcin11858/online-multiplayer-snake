package pl.edu.pw.elka.proz.snake.message;

import java.io.Serializable;

/**
 * Komunikaty wysy³ane prze sieæ do gracza.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class InfoMessage extends GameMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	/** Tekst zawierajacy wiadomoœæ */
	private final String message;

	public InfoMessage(final String message)
	{
		this.message = message;
	}

	/**
	 * 
	 * @return wiadomoœæ dla gracza.
	 */
	public String getMessage()
	{
		return message;
	}
}
