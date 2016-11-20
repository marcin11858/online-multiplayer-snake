package pl.edu.pw.elka.proz.snake.model;

enum GameStatus
{
	/** Gra trwa.*/
	INGAME
	{
		@Override
		GameStatus endGame()
		{
			return ENDED;
		}

		@Override
		boolean inGame()
		{
			return true;
		}

		@Override
		GameStatus newGame()
		{
			return INGAME;
		}
	},
	/** Gra zosta�a zako�czona.*/
	ENDED
	{
		@Override
		GameStatus endGame()
		{
			return ENDED;
		}

		@Override
		boolean inGame()
		{
			return false;
		}

		@Override
		GameStatus newGame()
		{
			return INGAME;
		}
	};

	/** 
	 * Zmienia status gry na zako�czona. 
	 */
	abstract GameStatus endGame();

	/** 
	 * Zwraca informace o tym czy gra si� toczy. 
	 */
	abstract boolean inGame();

	/** 
	 * Zmienia status gry na INGAME. 
	 */
	abstract GameStatus newGame();
}
