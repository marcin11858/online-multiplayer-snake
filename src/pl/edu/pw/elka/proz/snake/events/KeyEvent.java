package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Abstrakcyjna klasa reprezentuj¹za wydarzenie zwi¹zane z naciœnieniem klawisza przez gracza.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public abstract class KeyEvent extends PlayerEvent implements Serializable
{

	private static final long serialVersionUID = 1L;
	/** Set klawiszy z którego pochodzi event*/
	private final KeySetID whichSetKeys;
	
	/**
	 * Tworzy key Event.
	 * 
	 * @param whichSetKeys okreœla set klawiszy z którego pochodzi zdarzenie.
	 */
	public KeyEvent(final KeySetID whichSetKeys)
	{
		this.whichSetKeys = whichSetKeys;
	}
	
	/**
	 * Zwraca ID setu klawiszy z którego pochodzi zdarzenie.
	 * 
	 * @return ID setu klawiszy
	 */
	public KeySetID getWhichSetKeys()
	{
		return whichSetKeys;
	}
	
	/**
	 * Informuje czy zdareznie pochodzi z podstawowego zestawu klawiszy.
	 * 
	 * @return true jeœli zdarzenie pochodzi z podstawowego zestawu klawiszy.
	 */
	public boolean isBasicSet()
	{
		return whichSetKeys.equals(new KeySetID(1));
	}
}
