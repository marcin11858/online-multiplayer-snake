package pl.edu.pw.elka.proz.snake.model;

/**
 * Informacja o stanie w którym znajduje siê jab³ko.
 * 
 * @author Marcin Wlaz³y
 * @version 20110528
 */
enum AppleStatus
{
	/** Jab³ko zosta³o zjedzone przez wê¿a */
	EATEN
	{
		@Override
		boolean isEaten()
		{
			return true;
		}
	},

	/** Jab³ko nie zosta³o zjedzone przez wê¿a */
	NOEATEN
	{
		@Override
		boolean isEaten()
		{
			return false;
		}
	};

	/** 
	 * Zmienia stan jab³ka na zjedzony.
	 */
	AppleStatus eatApple()
	{
		return EATEN;
	}

	/** 
	 * Inforumje o tym czy jab³ko zosta³o zjedzone.
	 */
	abstract boolean isEaten();

	/**
	 * Zmienia stan jab³ka na nie zjedzone
	 */
	AppleStatus newApple()
	{
		return NOEATEN;
	}
}
