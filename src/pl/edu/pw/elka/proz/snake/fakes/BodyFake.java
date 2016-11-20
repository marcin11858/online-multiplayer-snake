package pl.edu.pw.elka.proz.snake.fakes;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.model.BodySize;
import pl.edu.pw.elka.proz.snake.model.Direction;
import pl.edu.pw.elka.proz.snake.model.SnakeNumber;

/**
 * Obiekt fake reprezentuj�cy cz�� cia�a w�a na planszy.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public class BodyFake extends GameFake implements Serializable
{

	private static final long serialVersionUID = 1L;
	/**Id w�a.*/
	final private SnakeNumber snakeNumber;
	/**Kierunek sk�d przyszed� w�� na dane pole.*/
	final private Direction from;
	/**Kierunek w kt�rym idzie w�� z danego pola.*/
	final private Direction to;
	/**Rozmiar w�a na danym polu.*/
	final private BodySize bodySize;

	/**
	 * Tworzy nowy obiekt cz�ci cia�a w�a.
	 * 
	 * @param snakeNumber id w�a
	 * @param from kierunek sk�d przyszed� w�� na dane pole.
	 * @param to kierunek dok�d idzie w�� z danego pola
	 * @param bodySize rozmiar cz�ci cia�a na danym polu
	 */
	public BodyFake(final SnakeNumber snakeNumber, final Direction from, final Direction to, final BodySize bodySize)
	{
		this.snakeNumber = snakeNumber;
		this.from = from;
		this.to = to;
		this.bodySize = bodySize;
	}

	/**
	 * Zwraca rozmiar w�a.
	 * 
	 * @return rozmiar w�a
	 */
	public BodySize getBodySize()
	{
		return bodySize;
	}

	/**
	 * Zwraca kierunek sk�d przyszed� w��.
	 * 
	 * @return kierunek sk�d w�� przyszed� na dane pole
	 */
	public Direction getFrom()
	{
		return from;
	}

	/**
	 * Zwraca kierunek do kt�rego idzie w�� w wybranego pola.
	 * 
	 * @return kierunek dok�d w�� idzie z wybranego pola
	 */
	public Direction getTo()
	{
		return to;
	}
	
	/**
	 * Zwraca ID w�a.
	 * @return numer ID w�a
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
