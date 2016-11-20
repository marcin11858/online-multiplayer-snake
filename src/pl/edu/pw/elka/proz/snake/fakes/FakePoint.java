package pl.edu.pw.elka.proz.snake.fakes;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.model.Coordinates;

/**
 * Obiekt fake reprezentuj¹cy punkt na planszy.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class FakePoint implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Opisuje po³o¿enie punktu na planszy*/
	private final Coordinates coordinates;
	
	/**
	 * Tworzy nowy fake punkt.
	 * 
	 * @param coordinates
	 */
	public FakePoint(final Coordinates coordinates)
	{
		this.coordinates = coordinates;
	}
	
	/**
	 * Zwraca koordynate opisuj¹c¹ dany punkt.
	 * 
	 * @return
	 */
	public Coordinates getCoordinates()
	{
		return coordinates;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
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
		FakePoint other = (FakePoint) obj;
		if (coordinates == null)
		{
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}
}
