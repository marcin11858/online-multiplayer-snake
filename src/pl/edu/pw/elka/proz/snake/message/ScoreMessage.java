package pl.edu.pw.elka.proz.snake.message;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.fakes.ScoreFakeMap;

/**
 * Zawiera informacje o zdobytej liczbie punkt�w przez graczy.
 * 
 * @author Marcin Wlaz�y
 * @version 20110603
 */
public class ScoreMessage extends GameMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Mapa zawieraj�ca wyniki graczy.*/
	private final ScoreFakeMap scoreFakeMap;
	
	public ScoreMessage(ScoreFakeMap scoreFakeMap)
	{
		this.scoreFakeMap = scoreFakeMap;
	}
	
	/**
	 * Zwraca mape wynik�w graczy.
	 * 
	 * @return mapa wynik�w graczy.
	 */
	public ScoreFakeMap getScoreFakeMap()
	{
		return scoreFakeMap;
	}
}
