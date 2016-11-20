package pl.edu.pw.elka.proz.snake.fakes;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.model.BodySize;
import pl.edu.pw.elka.proz.snake.model.Direction;
import pl.edu.pw.elka.proz.snake.model.SnakeNumber;

/**
 * Obiekt fake reprezentuj¹cy czêœæ cia³a wê¿a na planszy.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public class BodyFake extends GameFake implements Serializable
{

	private static final long serialVersionUID = 1L;
	/**Id wê¿a.*/
	final private SnakeNumber snakeNumber;
	/**Kierunek sk¹d przyszed³ w¹¿ na dane pole.*/
	final private Direction from;
	/**Kierunek w którym idzie w¹¿ z danego pola.*/
	final private Direction to;
	/**Rozmiar wê¿a na danym polu.*/
	final private BodySize bodySize;

	/**
	 * Tworzy nowy obiekt czêœci cia³a wê¿a.
	 * 
	 * @param snakeNumber id wê¿a
	 * @param from kierunek sk¹d przyszed³ w¹¿ na dane pole.
	 * @param to kierunek dok¹d idzie w¹¿ z danego pola
	 * @param bodySize rozmiar czêœci cia³a na danym polu
	 */
	public BodyFake(final SnakeNumber snakeNumber, final Direction from, final Direction to, final BodySize bodySize)
	{
		this.snakeNumber = snakeNumber;
		this.from = from;
		this.to = to;
		this.bodySize = bodySize;
	}

	/**
	 * Zwraca rozmiar wê¿a.
	 * 
	 * @return rozmiar wê¿a
	 */
	public BodySize getBodySize()
	{
		return bodySize;
	}

	/**
	 * Zwraca kierunek sk¹d przyszed³ w¹¿.
	 * 
	 * @return kierunek sk¹d w¹¿ przyszed³ na dane pole
	 */
	public Direction getFrom()
	{
		return from;
	}

	/**
	 * Zwraca kierunek do którego idzie w¹¿ w wybranego pola.
	 * 
	 * @return kierunek dok¹d w¹¿ idzie z wybranego pola
	 */
	public Direction getTo()
	{
		return to;
	}
	
	/**
	 * Zwraca ID wê¿a.
	 * @return numer ID wê¿a
	 */
	public SnakeNumber getWhichPlayer()
	{
		return snakeNumber;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bodySize == null) ? 0 : bodySize.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		BodyFake other = (BodyFake) obj;
		if (bodySize != other.bodySize)
			return false;
		if (from != other.from)
			return false;
		if (to != other.to)
			return false;
		if (snakeNumber != other.snakeNumber)
			return false;
		return true;
	}
}
