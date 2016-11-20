package pl.edu.pw.elka.proz.snake.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pl.edu.pw.elka.proz.snake.fakes.AppleFake;
import pl.edu.pw.elka.proz.snake.fakes.BodyFake;
import pl.edu.pw.elka.proz.snake.fakes.EmptyFake;
import pl.edu.pw.elka.proz.snake.fakes.FakeMap;
import pl.edu.pw.elka.proz.snake.fakes.FakePoint;
import pl.edu.pw.elka.proz.snake.fakes.GameFake;
import pl.edu.pw.elka.proz.snake.model.Direction;
import pl.edu.pw.elka.proz.snake.model.SnakeNumber;

/**
 * Plansza gry
 * 
 * @author Marcin
 * 
 */
public class MainBoard extends JPanel
{

	private static final long serialVersionUID = 1L;
	/**Plansza gry. */
	private FakeMap orginFakeMap = null;
	/**Akcje zwiazane z rysoaniem obiektów */
	final private HashMap<Class<? extends GameFake>, PaintAction> paintAction;
	/**Mapa zawieraj¹ca obrazki dla ka¿dego wê¿a*/
	final private Map<SnakeNumber, Map<BodyDirection, Image>> pictures;
	/**Obrazek jab³ka. */
	final private Image apple;
	/**Obrazek t³a. */
	final private Image empty;
	/**Obrazek startowy. */
	final private Image startImage;
	
	/**
	 * Tworzy nowy Main Board.
	 */
	MainBoard()
	{
		setPreferredSize(new Dimension(360, 360));
		paintAction = new HashMap<Class<? extends GameFake>, PaintAction>();
		apple = new ImageIcon("images/Apple.png").getImage();
		empty = new ImageIcon("images/Empty.png").getImage();
		startImage = new ImageIcon("images/StartWindow.png").getImage();
		fillPaintAction();
		pictures = new HashMap<SnakeNumber, Map<BodyDirection,Image>>();
		pictures.put(SnakeNumber.FIRST, fillBodyImagePlayer1());
		pictures.put(SnakeNumber.SECOND, fillBodyImagePlayer2());
		pictures.put(SnakeNumber.THIRD, fillBodyImagePlayer3());
		pictures.put(SnakeNumber.FOURTH, fillBodyImagePlayer4());
	}
	
	/**
	 * Rysuje plansze na ekranie.
	 */
	@Override
	protected void paintComponent(final Graphics g)
	{

		if (orginFakeMap != null)
		{
			HashMap<FakePoint, GameFake> mapa = orginFakeMap.getFakeMap();
			for (FakePoint point : mapa.keySet())
			{

				g.drawImage((paintAction.get((mapa.get(point)).getClass()))
						.getRightImage(mapa.get(point)), point.getCoordinates().getAlfa(), point.getCoordinates()
						.getBeta(), null);
			}
		} else
		{
			g.drawImage(startImage, 0, 0, this);
		}
	}
	
	/**
	 * Wczytanie nowej mapy fake'ów. 
	 */
	void setFake(final FakeMap fakeMap)
	{
		if (orginFakeMap == null)
		{
			this.orginFakeMap = fakeMap;
		} 
		else
		{
			HashMap<FakePoint, GameFake> mapa = orginFakeMap.getFakeMap();
			HashMap<FakePoint, GameFake> newMap = fakeMap.getFakeMap();
			mapa.putAll(newMap);
			orginFakeMap.setMap(mapa);
		}
	}
	
	/** 
	 * Wype³nienie mapy akcji ryswoania.
	 */
	private void fillPaintAction()
	{
		paintAction.put(BodyFake.class, new PaintBodyAction());
		paintAction.put(EmptyFake.class, new PaintEmpty());
		paintAction.put(AppleFake.class, new PaintApple());
	}
	
	/** 
	 * Wype³nienie mapy obrazków pierwszego wê¿a
	 */
	private Map<BodyDirection, Image> fillBodyImagePlayer1()
	{
		final Map<BodyDirection, Image> bodyImagePlayer1 = new HashMap<BodyDirection, Image>();
		bodyImagePlayer1.put(new BodyDirection(Direction.UNKNOW, Direction.EAST),new ImageIcon("images/TailEastPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.UNKNOW, Direction.WEST),new ImageIcon("images/TailWestPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.UNKNOW,Direction.NORTH), new ImageIcon("images/TailNorthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.UNKNOW,Direction.SOUTH), new ImageIcon("images/TailSouthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.WEST, Direction.WEST),new ImageIcon("images/BodyWestEastPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.EAST, Direction.EAST),new ImageIcon("images/BodyWestEastPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.NORTH, Direction.NORTH),new ImageIcon("images/BodyNorthSouthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.SOUTH, Direction.SOUTH),new ImageIcon("images/BodyNorthSouthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.SOUTH, Direction.EAST),new ImageIcon("images/BodyNorthEastPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.EAST, Direction.SOUTH),new ImageIcon("images/BodyWestSouthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.EAST, Direction.NORTH),new ImageIcon("images/BodyWestNorthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.NORTH, Direction.EAST),new ImageIcon("images/BodySouthEastPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.NORTH, Direction.WEST),new ImageIcon("images/BodyWestSouthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.WEST, Direction.NORTH),new ImageIcon("images/BodyNorthEastPlayer1.png").getImage()); 
		bodyImagePlayer1.put(new BodyDirection(Direction.WEST, Direction.SOUTH),new ImageIcon("images/BodySouthEastPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.SOUTH, Direction.WEST),new ImageIcon("images/BodyWestNorthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.WEST, Direction.UNKNOW),new ImageIcon("images/HeadWestPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.EAST, Direction.UNKNOW),new ImageIcon("images/HeadEastPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.SOUTH, Direction.UNKNOW), new ImageIcon("images/HeadSouthPlayer1.png").getImage());
		bodyImagePlayer1.put(new BodyDirection(Direction.NORTH,	Direction.UNKNOW), new ImageIcon("images/HeadNorthPlayer1.png").getImage());
		return Collections.unmodifiableMap(bodyImagePlayer1);
	}
	
	/** 
	 * Wype³nienie mapy obrazków drugiego gracza.
	 */
	private Map<BodyDirection, Image> fillBodyImagePlayer2()
	{
		final Map<BodyDirection, Image> bodyImagePlayer2 = new HashMap<BodyDirection, Image>();
		bodyImagePlayer2.put(new BodyDirection(Direction.UNKNOW, Direction.WEST),new ImageIcon("images/TailWestPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.UNKNOW, Direction.EAST),new ImageIcon("images/TailEastPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.UNKNOW,Direction.SOUTH), new ImageIcon("images/TailSouthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.UNKNOW,Direction.NORTH), new ImageIcon("images/TailNorthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.WEST, Direction.WEST),new ImageIcon("images/BodyWestEastPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.EAST, Direction.EAST),new ImageIcon("images/BodyWestEastPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.NORTH, Direction.NORTH),new ImageIcon("images/BodyNorthSouthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.SOUTH, Direction.SOUTH),new ImageIcon("images/BodyNorthSouthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.SOUTH, Direction.EAST),new ImageIcon("images/BodyNorthEastPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.EAST, Direction.SOUTH),new ImageIcon("images/BodyWestSouthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.EAST, Direction.NORTH),new ImageIcon("images/BodyWestNorthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.NORTH, Direction.EAST),new ImageIcon("images/BodySouthEastPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.NORTH, Direction.WEST),new ImageIcon("images/BodyWestSouthPlayer2.png").getImage()); 
		bodyImagePlayer2.put(new BodyDirection(Direction.WEST, Direction.NORTH),new ImageIcon("images/BodyNorthEastPlayer2.png").getImage()); 		
		bodyImagePlayer2.put(new BodyDirection(Direction.WEST, Direction.SOUTH), new ImageIcon("images/BodySouthEastPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.SOUTH, Direction.WEST),new ImageIcon("images/BodyWestNorthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.EAST, Direction.UNKNOW), new ImageIcon("images/HeadEastPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.WEST, Direction.UNKNOW), new ImageIcon("images/HeadWestPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.NORTH,	Direction.UNKNOW), new ImageIcon("images/HeadNorthPlayer2.png").getImage());
		bodyImagePlayer2.put(new BodyDirection(Direction.SOUTH,	Direction.UNKNOW), new ImageIcon("images/HeadSouthPlayer2.png").getImage());
		return Collections.unmodifiableMap(bodyImagePlayer2);
	}
	
	/**
	 * Wype³nia mape obrazków trzeciego wê¿a.
	 * @return
	 */
	private Map<BodyDirection, Image> fillBodyImagePlayer3()
	{
		final Map<BodyDirection, Image> bodyImagePlayer3 = new HashMap<BodyDirection, Image>();
		bodyImagePlayer3.put(new BodyDirection(Direction.UNKNOW, Direction.EAST),new ImageIcon("images/TailEastPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.UNKNOW, Direction.WEST),new ImageIcon("images/TailWestPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.UNKNOW,Direction.NORTH), new ImageIcon("images/TailNorthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.UNKNOW,Direction.SOUTH), new ImageIcon("images/TailSouthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.WEST, Direction.WEST),new ImageIcon("images/BodyWestEastPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.EAST, Direction.EAST),new ImageIcon("images/BodyWestEastPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.NORTH, Direction.NORTH),new ImageIcon("images/BodyNorthSouthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.SOUTH, Direction.SOUTH),new ImageIcon("images/BodyNorthSouthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.SOUTH, Direction.EAST),new ImageIcon("images/BodyNorthEastPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.EAST, Direction.SOUTH),new ImageIcon("images/BodyWestSouthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.EAST, Direction.NORTH), new ImageIcon("images/BodyWestNorthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.NORTH, Direction.EAST),new ImageIcon("images/BodySouthEastPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.NORTH, Direction.WEST),new ImageIcon("images/BodyWestSouthPlayer3.png").getImage()); 
		bodyImagePlayer3.put(new BodyDirection(Direction.WEST, Direction.NORTH),new ImageIcon("images/BodyNorthEastPlayer3.png").getImage()); 
		bodyImagePlayer3.put(new BodyDirection(Direction.WEST, Direction.SOUTH),new ImageIcon("images/BodySouthEastPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.SOUTH, Direction.WEST),new ImageIcon("images/BodyWestNorthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.WEST, Direction.UNKNOW),new ImageIcon("images/HeadWestPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.EAST, Direction.UNKNOW),new ImageIcon("images/HeadEastPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.SOUTH,Direction.UNKNOW), new ImageIcon("images/HeadSouthPlayer3.png").getImage());
		bodyImagePlayer3.put(new BodyDirection(Direction.NORTH,Direction.UNKNOW), new ImageIcon("images/HeadNorthPlayer3.png").getImage());
		return Collections.unmodifiableMap(bodyImagePlayer3);
	}
	
	/**
	 * Wype³nia mape obrazków czwartego wê¿a.
	 * @return
	 */
	private Map<BodyDirection, Image> fillBodyImagePlayer4()
	{
		final Map<BodyDirection, Image> bodyImagePlayer4 = new HashMap<BodyDirection, Image>();
		bodyImagePlayer4.put(new BodyDirection(Direction.UNKNOW, Direction.EAST),new ImageIcon("images/TailEastPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.UNKNOW, Direction.WEST),new ImageIcon("images/TailWestPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.UNKNOW,Direction.NORTH), new ImageIcon("images/TailNorthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.UNKNOW,Direction.SOUTH), new ImageIcon("images/TailSouthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.WEST, Direction.WEST),new ImageIcon("images/BodyWestEastPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.EAST, Direction.EAST),new ImageIcon("images/BodyWestEastPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.NORTH, Direction.NORTH),new ImageIcon("images/BodyNorthSouthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.SOUTH, Direction.SOUTH),new ImageIcon("images/BodyNorthSouthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.SOUTH, Direction.EAST),new ImageIcon("images/BodyNorthEastPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.EAST, Direction.SOUTH),new ImageIcon("images/BodyWestSouthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.EAST, Direction.NORTH),new ImageIcon("images/BodyWestNorthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.NORTH, Direction.EAST),new ImageIcon("images/BodySouthEastPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.NORTH, Direction.WEST), new ImageIcon("images/BodyWestSouthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.WEST, Direction.NORTH),new ImageIcon("images/BodyNorthEastPlayer4.png").getImage()); 
		bodyImagePlayer4.put(new BodyDirection(Direction.WEST, Direction.SOUTH),new ImageIcon("images/BodySouthEastPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.SOUTH, Direction.WEST),new ImageIcon("images/BodyWestNorthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.WEST, Direction.UNKNOW),new ImageIcon("images/HeadWestPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.EAST, Direction.UNKNOW), new ImageIcon("images/HeadEastPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.SOUTH,Direction.UNKNOW), new ImageIcon("images/HeadSouthPlayer4.png").getImage());
		bodyImagePlayer4.put(new BodyDirection(Direction.NORTH,	Direction.UNKNOW), new ImageIcon("images/HeadNorthPlayer4.png").getImage());
		return Collections.unmodifiableMap(bodyImagePlayer4);
	}
	
	/** 
	 * Interfejs odpowiedzialny za wykonanie akcji wysowania obrazka na ekranie.
	 */
	private interface PaintAction
	{
		public Image getRightImage(GameFake gameFake);
	}

	/** 
	 * Narysowanie jab³ka.
	 */
	private class PaintApple implements PaintAction
	{
		@Override
		public Image getRightImage(final GameFake gameFake)
		{
			return apple;
		}
	}

	/** 
	 * Narysowanie czesci ciala.
	 */
	private class PaintBodyAction implements PaintAction
	{
		@Override
		public Image getRightImage(final GameFake gameFake)
		{
			BodyFake bodyFake = (BodyFake) gameFake;
			return pictures.get(bodyFake.getWhichPlayer()).get(new BodyDirection(bodyFake.getFrom(), bodyFake.getTo()));
		}
	}

	/** 
	 * Narysowanie pustego pola.
	 */
	private class PaintEmpty implements PaintAction
	{
		@Override
		public Image getRightImage(final GameFake gameFake)
		{
			return empty;
		}
	}
}
