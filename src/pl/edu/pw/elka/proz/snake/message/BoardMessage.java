package pl.edu.pw.elka.proz.snake.message;

import java.io.Serializable;

import pl.edu.pw.elka.proz.snake.fakes.FakeMap;

/**
 * Klasa która opakowuje fake mape w celu wys³ania przez sieæ.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class BoardMessage extends GameMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Obiekt Fake zawieraj¹cy plansze gry.*/
	private final FakeMap fakeMap;

	public BoardMessage(final FakeMap fakeMap)
	{
		this.fakeMap = fakeMap;
	}

	/**
	 * Zwraca obiekt fake ca³ej planszy.
	 * 
	 * @return plansza gry
	 */
	public FakeMap getFakeMap()
	{
		return fakeMap;
	}
}
