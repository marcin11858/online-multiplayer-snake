package pl.edu.pw.elka.proz.snake.model;

import java.io.Serializable;

/**
 * Opisuje po³o¿enie obiektów na planszy.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class Coordinates implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**K¹t alfa obiektu na planszy*/
	private final int alfa;
	/**K¹t beta obiektu na planszy*/
	private final int beta;
	
	
	public Coordinates(final int alfa, final int beta)
	{
		this.alfa = alfa;
		this.beta = beta;
	}
	
	/**
	 * Zwraca k¹t alfa obiektu.
	 * 
	 * @return
	 */
	public int getAlfa()
	{
		return alfa;
	}
	
	/**
	 * Zwraca k¹t beta obiektu.
	 * @return
	 */
	public int getBeta()
	{
		return beta;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + alfa;
		result = prime * result + beta;
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
		Coordinates other = (Coordinates) obj;
		if (alfa != other.alfa)
			return false;
		if (beta != other.beta)
			return false;
		return true;
	}
}
