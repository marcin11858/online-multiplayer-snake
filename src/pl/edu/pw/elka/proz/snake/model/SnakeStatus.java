package pl.edu.pw.elka.proz.snake.model;

/**
 * Zawiera informacje o stanie w kt�rym znajduje si� w��.
 * 
 * @author Marcin Wlaz�y
 * @version 20110601
 */
enum SnakeStatus
{
	/** w�� �yje */
	ALIVE(1)
	{
		@Override
		boolean isDead()
		{
			return false;
		}
	},
	/** w�� nie �yje */
	DEAD(0)
	{
		@Override
		boolean isDead()
		{
			return true;
		}
	};

	/**Okre�la stan w kt�rym znajduje si� w��.*/
	private final int stan;

	private SnakeStatus(final int stan)
	{
		this.stan = stan;
	}

	/** 
	 * Zwraca stan w�a.
	 */
	int getStan()
	{
		return stan;
	}

	/**
	 * Zwraca informacje o tym czy w�� nie �yje.
	 * 
	 * @return jesli w�� nie �yje true w przeciwnym przypadku false
	 */
	abstract boolean isDead();

	/**
	 * Zmienia status w�a na DEAD
	 */
	SnakeStatus uderzyl()
	{
		return DEAD;
	}
}
