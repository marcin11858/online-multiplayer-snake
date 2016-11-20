package pl.edu.pw.elka.proz.snake.view;

import pl.edu.pw.elka.proz.snake.model.Direction;

/**
 * Informacje o kierunku sk�d i dok�d przemieszcza si� cz�� cia�a.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public class BodyDirection
{
	/**Opisuje kierunek z kt�rego w�� przyszed� na pole.*/
	Direction from;
	/**Opisuje kierunek w kt�rym porusza si� w�� z pola.*/
	Direction where;

	public BodyDirection(final Direction from, final Direction where)
	{
		this.from = from;
		this.where = where;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		BodyDirection other = (BodyDirection) obj;
		if (from == null)
		{
			if (other.from != null)
			{
				return false;
			}
		} else if (!from.equals(other.from))
		{
			return false;
		}
		if (where == null)
		{
			if (other.where != null)
			{
				return false;
			}
		} else if (!where.equals(other.where))
		{
			return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((where == null) ? 0 : where.hashCode());
		return result;
	}
}
