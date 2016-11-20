package pl.edu.pw.elka.proz.snake.fakes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Obiekt fake reprezentuj¹cy plansze.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public class FakeMap implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**Lista punktów oraz fake'ów reprezentuj¹cych obiekty na planszy.*/
	private HashMap<FakePoint, GameFake> fakeMap = new HashMap<FakePoint, GameFake>();

	/**
	 * Tworzy now¹ fake mape.
	 * 
	 * @param fakeMap
	 */
	public FakeMap(HashMap<FakePoint, GameFake> fakeMap)
	{
		this.fakeMap = fakeMap;
	}
	
	public FakeMap(){ }
	
	/**
	 * 
	 * @return mapa fake punktów oraz obiektów znajduj¹cych siê na danym polu.
	 */
	public HashMap<FakePoint, GameFake> getFakeMap()
	{
		return fakeMap;
	}
	
	/**
	 * Dodanie do mapy nowej pozycji.
	 * 
	 * @param fakePoint reprezentuje punkt na planszy
	 * @param gameFake reprezentuje obiekt na danym polu planszy
	 */
	public void setFake(final FakePoint fakePoint, final GameFake gameFake)
	{
		fakeMap.put(fakePoint, gameFake);
	}

	/**
	 * Przypisanie mapy fakeów.
	 * 
	 * @param map mapa fakeów
	 */
	public void setMap(final HashMap<FakePoint, GameFake> map)
	{
		fakeMap = map;
	}
}
