package pl.edu.pw.elka.proz.snake.message;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.fakes.FakeMap;

/**
 * Klasa kt�ra opakowuje fake mape w celu wys�ania przez sie�.
 * 
 * @author Marcin Wlaz�y
 * @version 20110603
 */
public class BoardMessage extends GameMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Obiekt Fake zawieraj�cy plansze gry.*/
	private final FakeMap fakeMap;

	public BoardMessage(final FakeMap fakeMap)
	{
		this.fakeMap = fakeMap;
	}

	/**
	 * Zwraca obiekt fake ca�ej planszy.
	 * 
	 * @return plansza gry
	 */
	public FakeMap getFakeMap()
	{
		return fakeMap;
	}
}
