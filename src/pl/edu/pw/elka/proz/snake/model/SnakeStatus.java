package pl.edu.pw.elka.proz.snake.model;

/**
 * Zawiera informacje o stanie w którym znajduje siê w¹¿.
 * 
 * @author Marcin Wlaz³y
 * @version 20110601
 */
enum SnakeStatus
{
	/** w¹¿ ¿yje */
	ALIVE(1)
	{
		@Override
		boolean isDead()
		{
			return false;
		}
	},
	/** w¹¿ nie ¿yje */
	DEAD(0)
	{
		@Override
		boolean isDead()
		{
			return true;
		}
	};

	/**Okreœla stan w którym znajduje siê w¹¿.*/
	private final int stan;

	private SnakeStatus(final int stan)
	{
		this.stan = stan;
	}

	/** 
	 * Zwraca stan wê¿a.
	 */
	int getStan()
	{
		return stan;
	}

	/**
	 * Zwraca informacje o tym czy w¹¿ nie ¿yje.
	 * 
	 * @return jesli w¹¿ nie ¿yje true w przeciwnym przypadku false
	 */
	abstract boolean isDead();

	/**
	 * Zmienia status wê¿a na DEAD
	 */
	SnakeStatus uderzyl()
	{
		return DEAD;
	}
}
