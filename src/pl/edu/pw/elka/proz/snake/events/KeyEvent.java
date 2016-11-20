package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.snake.KeySetID;

/**
 * Abstrakcyjna klasa reprezentuj�za wydarzenie zwi�zane z naci�nieniem klawisza przez gracza.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public abstract class KeyEvent extends PlayerEvent implements Serializable
{

	private static final long serialVersionUID = 1L;
	/** Set klawiszy z kt�rego pochodzi event*/
	private final KeySetID whichSetKeys;
	
	/**
	 * Tworzy key Event.
	 * 
	 * @param whichSetKeys okre�la set klawiszy z kt�rego pochodzi zdarzenie.
	 */
	public KeyEvent(final KeySetID whichSetKeys)
	{
		this.whichSetKeys = whichSetKeys;
	}
	
	/**
	 * Zwraca ID setu klawiszy z kt�rego pochodzi zdarzenie.
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
	 * @return true je�li zdarzenie pochodzi z podstawowego zestawu klawiszy.
	 */
	public boolean isBasicSet()
	{
		return whichSetKeys.equals(new KeySetID(1));
	}
}
