package pl.edu.pw.elka.proz.snake.model;

import java.io.Serializable;

/**
 * Cz�� cia�a w�a.
 * 
 * @author Marcin Wlaz�y
 * @version 20110603
 */
public class SnakeBody implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Po�o�enie cze�ci cia�a na planszy.*/
	final private Coordinates coordinates;
	/** opis kierunku cz�ci cia�a, kierunek przy wej�ciu na dane pole */
	private Direction fromDirection;
	/** opis kierunku cz�ci cia�a, kierune przy wyj�ciu z danego pola */
	private Direction toDirection;
	/** Rozmiar cz�ci cia�a */
	private BodySize bodySize = BodySize.NORMAL;

	/**
	 * Tworzy now� cz�� cia��
	 * 
	 * @param coordinates po�o�enie cz�ci cia�a
	 * @param from kierunek sk�d w�� przyszed� na pole
	 * @param to kierunek dok�d w�� idzie z tego pola
	 */
	public SnakeBody(final Coordinates coordinates, final Direction from, final Direction to)
	{
		this.coordinates = coordinates;
		this.fromDirection = from;
		this.toDirection = to;
	}

	/**
	 * Informacja o po�o�eniu cz�ci cia�a.
	 * 
	 * @return
	 */
	Coordinates getCoordinates()
	{
		return coordinates;
	}

	/**
	 * informacja o rozmiarze cz�ci cia�a.
	 * 
	 * @return BIG je�li w tym miejscu znajdowa�o si� jab�ko zjedzone przez w�a
	 */
	BodySize getBodySize()
	{
		return bodySize;
	}

	/**
	 * Informacja o tym sk�d w�� przyszed� na to miejsce na planszy.
	 * 
	 * @return
	 */
	Direction getFrom()
	{
		return fromDirection;
	}

	/**
	 * Informacja o tym dok�d w�� idzie z tego miejsca na planszy.
	 * 
	 * @return
	 */
	Direction getWhere()
	{
		return toDirection;
	}

	/**
	 * Wzrost cz�ci cia�a po zjedzeniu jab�ka.
	 */
	void grow()
	{
		bodySize = bodySize.grow();
	}
	
	/**
	 * Ustawienie kierunku z kt�rego przyszed� w�� na dane miejsze na planszy.
	 * 
	 * @param from
	 */
	void setFrom(final Direction from)
	{
		this.fromDirection = from;
	}

	/**
	 * Ustawienie kierunku w kt�rym w�� idzie z tego miejsca na planszy.
	 * 
	 * @param to
	 */
	void setTo(final Direction to)
	{
		this.toDirection = to;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result
				+ ((fromDirection == null) ? 0 : fromDirection.hashCode());
		result = prime * result
				+ ((toDirection == null) ? 0 : toDirection.hashCode());
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
		SnakeBody other = (SnakeBody) obj;
		if (coordinates == null)
		{
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}
}
