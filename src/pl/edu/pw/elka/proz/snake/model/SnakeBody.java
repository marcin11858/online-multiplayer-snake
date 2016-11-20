package pl.edu.pw.elka.proz.snake.model;

import java.io.Serializable;

/**
 * Czêœæ cia³a wê¿a.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class SnakeBody implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Po³o¿enie czeœci cia³a na planszy.*/
	final private Coordinates coordinates;
	/** opis kierunku czêœci cia³a, kierunek przy wejœciu na dane pole */
	private Direction fromDirection;
	/** opis kierunku czêœci cia³a, kierune przy wyjœciu z danego pola */
	private Direction toDirection;
	/** Rozmiar czêœci cia³a */
	private BodySize bodySize = BodySize.NORMAL;

	/**
	 * Tworzy now¹ czêœæ cia³¹
	 * 
	 * @param coordinates po³o¿enie czêœci cia³a
	 * @param from kierunek sk¹d w¹¿ przyszed³ na pole
	 * @param to kierunek dok¹d w¹¿ idzie z tego pola
	 */
	public SnakeBody(final Coordinates coordinates, final Direction from, final Direction to)
	{
		this.coordinates = coordinates;
		this.fromDirection = from;
		this.toDirection = to;
	}

	/**
	 * Informacja o po³o¿eniu czêœci cia³a.
	 * 
	 * @return
	 */
	Coordinates getCoordinates()
	{
		return coordinates;
	}

	/**
	 * informacja o rozmiarze czêœci cia³a.
	 * 
	 * @return BIG jeœli w tym miejscu znajdowa³o siê jab³ko zjedzone przez wê¿a
	 */
	BodySize getBodySize()
	{
		return bodySize;
	}

	/**
	 * Informacja o tym sk¹d w¹¿ przyszed³ na to miejsce na planszy.
	 * 
	 * @return
	 */
	Direction getFrom()
	{
		return fromDirection;
	}

	/**
	 * Informacja o tym dok¹d w¹¿ idzie z tego miejsca na planszy.
	 * 
	 * @return
	 */
	Direction getWhere()
	{
		return toDirection;
	}

	/**
	 * Wzrost czêœci cia³a po zjedzeniu jab³ka.
	 */
	void grow()
	{
		bodySize = bodySize.grow();
	}
	
	/**
	 * Ustawienie kierunku z którego przyszed³ w¹¿ na dane miejsze na planszy.
	 * 
	 * @param from
	 */
	void setFrom(final Direction from)
	{
		this.fromDirection = from;
	}

	/**
	 * Ustawienie kierunku w którym w¹¿ idzie z tego miejsca na planszy.
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
