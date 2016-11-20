package pl.edu.pw.elka.proz.snake.model;

/**
 * Informacja o stanie w kt�rym znajduje si� jab�ko.
 * 
 * @author Marcin Wlaz�y
 * @version 20110528
 */
enum AppleStatus
{
	/** Jab�ko zosta�o zjedzone przez w�a */
	EATEN
	{
		@Override
		boolean isEaten()
		{
			return true;
		}
	},

	/** Jab�ko nie zosta�o zjedzone przez w�a */
	NOEATEN
	{
		@Override
		boolean isEaten()
		{
			return false;
		}
	};

	/** 
	 * Zmienia stan jab�ka na zjedzony.
	 */
	AppleStatus eatApple()
	{
		return EATEN;
	}

	/** 
	 * Inforumje o tym czy jab�ko zosta�o zjedzone.
	 */
	abstract boolean isEaten();

	/**
	 * Zmienia stan jab�ka na nie zjedzone
	 */
	AppleStatus newApple()
	{
		return NOEATEN;
	}
}
