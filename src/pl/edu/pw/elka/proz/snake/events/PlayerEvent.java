package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.PlayerID;

/**
 * Wydarzenia zwi¹zane z graczem.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public abstract class PlayerEvent extends GameEvent implements Serializable
{

	private static final long serialVersionUID = 1L;
	/** ID gracza */
	private PlayerID ID;

	/**
	 * Tworzy nowe zdarzenie dotycz¹ce gracza.
	 */
	public PlayerEvent()
	{
		ID = new PlayerID(0);
	}

	/**
	 * Zwraca numer ID gracza.
	 * 
	 * @return numer ID gracza
	 */
	public PlayerID getID()
	{
		return ID;
	}

	/**
	 * Nadanie graczowi numeru ID.
	 * 
	 * @param ID numer ID gracza
	 */
	public void setID(final PlayerID ID)
	{
		this.ID = ID;
	}
}
