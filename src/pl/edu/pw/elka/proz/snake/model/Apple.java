package pl.edu.pw.elka.proz.snake.model;

import java.util.ArrayList;

/**
 * Przechwuje informacjê o jab³ku znajduj¹cym sie na planszy
 * 
 * @author Marcin Wlaz³y
 * @version 20110528
 */
class Apple
{
	/**Wspó³rzedne jab³ka*/
	private Coordinates coordinates;
	/** Informacja o tym czy jab³ko zostalo zjedzone przez we¿a */
	private AppleStatus appleStatus = AppleStatus.EATEN;

	/**
	 * Tworzy nowe jab³ko.
	 */
	Apple()
	{
		coordinates = null;
	}

	/**
	 * Wybiera nowe miejsce dla jab³ka spoœród listy wolnych punktów.
	 * 
	 * @param emptyPoints lista wolnych punktów
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
	 * Zwraca obiekt opisuj¹cy po³o¿enie jab³ka.
	 * 
	 * @return obiekt zawierajacy wspó³rzêdne jab³ka.
	 */
	Coordinates getCoordinates()
	{
		return coordinates;
	}

	/**
	 * Zwraca informacje o tym czy dane miejsce na planszy jest zajmowane przez
	 * jab³ko. W przypadku gdy jest zajêta zmienia stan jab³ka na zjedzone.
	 * 
	 * @param snakeBody
	 * @return true jeœli dane miejsce jest zajete w przeciwnym wypadku false
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
	 * Inforumje o stanie w jakim znajduje sie jab³ko.
	 * 
	 */
	boolean isEaten()
	{
		return appleStatus.isEaten();
	}
}
