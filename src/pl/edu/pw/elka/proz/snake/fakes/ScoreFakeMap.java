package pl.edu.pw.elka.proz.snake.fakes;

import java.io.Serializable;
import java.util.Map;

import pl.edu.pw.elka.proz.snake.model.SnakeNumber;

/**
 * Zawiera mape wynik�w wszystkich graczy.
 * 
 * @author Marcin Wlaz�y
 * @version 20110604
 */
public class ScoreFakeMap implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Mapa wynik�w wszystkich graczy.*/
	private final Map<SnakeNumber, ScoreFake> scoreMap;
	
	/**
	 * Tworzy now� mape wynik�w wszystkich graczy.
	 * 
	 * @param scoreMap
	 */
	public ScoreFakeMap(final Map<SnakeNumber, ScoreFake> scoreMap)
	{
		this.scoreMap = scoreMap;
	}
	
	/**
	 * Zwraca mape wynik�w graczy.
	 * 
	 * @return
	 */
	public Map<SnakeNumber, ScoreFake> getScoreMap()
	{
		return scoreMap;
	}
}
