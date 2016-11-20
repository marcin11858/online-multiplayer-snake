package pl.edu.pw.elka.proz.snake.message;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.fakes.ScoreFakeMap;

/**
 * Zawiera informacje o zdobytej liczbie punktów przez graczy.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class ScoreMessage extends GameMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Mapa zawieraj¹ca wyniki graczy.*/
	private final ScoreFakeMap scoreFakeMap;
	
	public ScoreMessage(ScoreFakeMap scoreFakeMap)
	{
		this.scoreFakeMap = scoreFakeMap;
	}
	
	/**
	 * Zwraca mape wyników graczy.
	 * 
	 * @return mapa wyników graczy.
	 */
	public ScoreFakeMap getScoreFakeMap()
	{
		return scoreFakeMap;
	}
}
