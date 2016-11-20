package pl.edu.pw.elka.proz.snake.model;

/**
 * Opisuje kierunek w kt�rym porusza si� w��
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public enum Direction
{
	/** W�� porusza si� na p�noc */
	NORTH(0, -1)
	{
		@Override
		Direction goEast()
		{
			return EAST;
		}

		@Override
		Direction goNorth()
		{
			return NORTH;
		}

		@Override
		Direction goSouth()
		{
			return NORTH;
		}

		@Override
		Direction goWest()
		{
			return WEST;
		}

		@Override
		Direction turnBack()
		{
			return SOUTH;
		}
	},

	/** W�� porusza si� na po�udnie */
	SOUTH(0, 1)
	{

		@Override
		Direction goEast()
		{
			return EAST;
		}

		@Override
		Direction goNorth()
		{
			return SOUTH;
		}

		@Override
		Direction goSouth()
		{
			return SOUTH;
		}

		@Override
		Direction goWest()
		{
			return WEST;
		}

		@Override
		Direction turnBack()
		{
			return NORTH;
		}
		
	},

	/** W�� porusza si� na wsch�d */
	EAST(1, 0)
	{

		@Override
		Direction goEast()
		{
			return EAST;
		}

		@Override
		Direction goNorth()
		{
			return NORTH;
		}

		@Override
		Direction goSouth()
		{
			return SOUTH;
		}

		@Override
		Direction goWest()
		{
			return EAST;
		}

		@Override
		Direction turnBack()
		{
			return WEST;
		}
	},

	/** W�� porusza si� na zach�d */
	WEST(-1, 0)
	{

		@Override
		Direction goEast()
		{
			return WEST;
		}

		@Override
		Direction goNorth()
		{
			return NORTH;
		}

		@Override
		Direction goSouth()
		{
			return SOUTH;
		}

		@Override
		Direction goWest()
		{
			return WEST;
		}

		@Override
		Direction turnBack()
		{
			return EAST;
		}
	},

	/** W�� porusza si� w kierunku nieznanym */
	UNKNOW(0, 0)
	{

		@Override
		Direction goEast()
		{
			return UNKNOW;
		}

		@Override
		Direction goNorth()
		{
			return UNKNOW;
		}

		@Override
		Direction goSouth()
		{
			return UNKNOW;
		}

		@Override
		Direction goWest()
		{
			return UNKNOW;
		}

		@Override
		Direction turnBack()
		{
			return UNKNOW;
		}
	};

	/** Informacje o wektorze przemieszczenia */
	private int vectorAlfa, vectorBeta;

	private Direction(final int vectorAlfa, final int vectorBeta)
	{
		this.vectorAlfa = vectorAlfa;
		this.vectorBeta = vectorBeta;
	}

	/** 
	 * Zwraca informacje o wektorze alfa kierunku.
	 */
	int getVectorAlfa()
	{
		return vectorAlfa;
	}

	/** 
	 * Zwraca informacje o wektorze beta kierunku.
	 */
	int getVectorBeta()
	{
		return vectorBeta;
	}

	/** 
	 * Zmienia kierunek na wschodni w przypadku gdy jest to mo�liwe.
	 */
	abstract Direction goEast();

	/** 
	 * Zmienia kierunek na p�nocny w przypadku gdy jest to mo�liwe.
	 */
	abstract Direction goNorth();

	/**
	 * Zmienia kierunek na po�udniowy w przypadku gdy jest to mo�liwe.
	 */
	abstract Direction goSouth();

	/** 
	 * Zmienia kierunek na zachodni w przypadku gdy jest to mo�liwe.
	 */
	abstract Direction goWest();
	
	/**
	 * Zmienia kierunek na przeciwny.
	 */
	abstract Direction turnBack();

}