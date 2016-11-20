package pl.edu.pw.elka.proz.snake.model;

/**
 * Klasa reprezentuj鉍a wolny punkt na planszy
 * 
 * @author Marcin Wlaz造
 * @version 20110602
 */
class EmptyPoint
{
	/**Po這瞠nie punktu na planszy*/
	private final Coordinates coordinates;
	
	/**
	 * Tworzy nowy pusty punkt na planszy.
	 * 
	 * @param coordinates po這瞠nie punktu na planszy
	 */
	public EmptyPoint(Coordinates coordinates)
	{
		this.coordinates = coordinates;
	}
	
	/**
	 * Zwraca po這瞠nie punktu na planszy.
	 * 
	 * @return po這瞠nie punktu na planszy
	 */
	Coordinates getCoordinates()
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
		EmptyPoint other = (EmptyPoint) obj;
		if (coordinates == null)
		{
			if (other.coordinates != null)
				return false;
		} else if (!coordinates.equals(other.coordinates))
			return false;
		return true;
	}
}
