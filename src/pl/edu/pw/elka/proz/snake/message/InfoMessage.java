package pl.edu.pw.elka.proz.snake.message;

import java.io.Serializable;

/**
 * Komunikaty wysy�ane prze sie� do gracza.
 * 
 * @author Marcin Wlaz�y
 * @version 20110603
 */
public class InfoMessage extends GameMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	/** Tekst zawierajacy wiadomo�� */
	private final String message;

	public InfoMessage(final String message)
	{
		this.message = message;
	}

	/**
	 * 
	 * @return wiadomo�� dla gracza.
	 */
	public String getMessage()
	{
		return message;
	}
}
