package pl.edu.pw.elka.proz.snake.fakes;

import java.io.Serializable;

/**
 * Zawiera informacje o wyniku gracza.
 * 
 * @author Marcin Wlaz�y
 * @version 20110605
 */
public class ScoreFake implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Wynik gracza.*/
	private final int score;
	
	/**
	 * Tworzy nowy obiekt Score Fake
	 * 
	 * @param score
	 */
	
	public ScoreFake(final int score)
	{
		this.score = score;
	}
	
	/**
	 * Zwraca wynik gracza.
	 * 
	 * @return
	 */
	public int getScore()
	{
		return score;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreFake other = (ScoreFake) obj;
		if (score != other.score)
			return false;
		return true;
	}
}
