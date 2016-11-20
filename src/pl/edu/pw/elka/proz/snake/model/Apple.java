package pl.edu.pw.elka.proz.snake.model;

import java.util.ArrayList;

/**
 * Przechwuje informacj� o jab�ku znajduj�cym sie na planszy
 * 
 * @author Marcin Wlaz�y
 * @version 20110528
 */
class Apple
{
	/**Wsp�rzedne jab�ka*/
	private Coordinates coordinates;
	/** Informacja o tym czy jab�ko zostalo zjedzone przez we�a */
	private AppleStatus appleStatus = AppleStatus.EATEN;

	/**
	 * Tworzy nowe jab�ko.
	 */
	Apple()
	{
		coordinates = null;
	}

	/**
	 * Wybiera nowe miejsce dla jab�ka spo�r�d listy wolnych punkt�w.
	 * 
	 * @param emptyPoints lista wolnych punkt�w
	 */
	void chooseNewPoint(final ArrayList<EmptyPoint> emptyPoints)
	{
		appleStatus = appleStatus.newApple();
		int chooseIndexEmptyPoint = (int) (Math.random() * emptyPoints.size());
		EmptyPoint chooseEmptyPoint = emptyPoints.get(chooseIndexEmptyPoint);
		coordinates = chooseEmptyPoint.getCoordinates();
		emptyPoints.remove(new EmptyPoint(coordinates));
	}

	/**
	 * Zwraca obiekt opisuj�cy po�o�enie jab�ka.
	 * 
	 * @return obiekt zawierajacy wsp�rz�dne jab�ka.
	 */
	Coordinates getCoordinates()
	{
		return coordinates;
	}

	/**
	 * Zwraca informacje o tym czy dane miejsce na planszy jest zajmowane przez
	 * jab�ko. W przypadku gdy jest zaj�ta zmienia stan jab�ka na zjedzone.
	 * 
	 * @param snakeBody
	 * @return true je�li dane miejsce jest zajete w przeciwnym wypadku false
	 */
	boolean isApple(final SnakeBody snakeBody)
	{
		if (snakeBody.equals(new SnakeBody(coordinates, Direction.UNKNOW, Direction.UNKNOW)))
		{
			appleStatus = appleStatus.eatApple();
			return true;
		}
		return false;
	}

	/**
	 * Inforumje o stanie w jakim znajduje sie jab�ko.
	 * 
	 */
	boolean isEaten()
	{
		return appleStatus.isEaten();
	}
}
