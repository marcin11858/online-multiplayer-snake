package pl.edu.pw.elka.proz.snake.fakes;

import java.io.Serializable;
import java.util.Map;

import pl.edu.pw.elka.proz.snake.model.SnakeNumber;

/**
 * Zawiera mape wyników wszystkich graczy.
 * 
 * @author Marcin Wlaz³y
 * @version 20110604
 */
public class ScoreFakeMap implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Mapa wyników wszystkich graczy.*/
	private final Map<SnakeNumber, ScoreFake> scoreMap;
	
	/**
	 * Tworzy now¹ mape wyników wszystkich graczy.
	 * 
	 * @param scoreMap
	 */
	public ScoreFakeMap(final Map<SnakeNumber, ScoreFake> scoreMap)
	{
		this.scoreMap = scoreMap;
	}
	
	/**
	 * Zwraca mape wyników graczy.
	 * 
	 * @return
	 */
	public Map<SnakeNumber, ScoreFake> getScoreMap()
	{
		return scoreMap;
	}
}
