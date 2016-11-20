package pl.edu.pw.elka.proz.snake.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.edu.pw.elka.proz.snake.fakes.ScoreFake;
import pl.edu.pw.elka.proz.snake.message.ScoreMessage;
import pl.edu.pw.elka.proz.snake.model.SnakeNumber;

/**
 * Zawiera panele z informacjami o liczbie zdobytych punktów przez graczy.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */

class ScoreFrame extends JPanel
{

	private static final long serialVersionUID = 1L;
	/**Lista paneli z wynikami graczy */
	private final Map<SnakeNumber, PlayerScore> players;

	public ScoreFrame()
	{
		setSize(40, 100);
		setPreferredSize(new Dimension(70, 360));
		setLayout(new GridLayout(5, 1));
		JLabel lab = new JLabel("Wyniki:");
		add(lab);
		players = new HashMap<SnakeNumber, PlayerScore>();
		players.put( SnakeNumber.FIRST, new PlayerScore(Color.RED));
		players.put(SnakeNumber.SECOND, new PlayerScore(Color.GREEN));
		players.put(SnakeNumber.THIRD, new PlayerScore(Color.YELLOW));
		players.put(SnakeNumber.FOURTH, new PlayerScore(Color.MAGENTA));
		add(players.get(SnakeNumber.FIRST));
		add(players.get(SnakeNumber.SECOND));
		add(players.get(SnakeNumber.THIRD));
		add(players.get(SnakeNumber.FOURTH));
	}

	/**
	 * Aktualizacja wyników graczy.
	 * 
	 * @param scoreMessage informacja o nomym wyniku oraz id we¿a
	 */
	void actScore(final ScoreMessage scoreMessage)
	{
		Map<SnakeNumber, ScoreFake> scoreFakeMap = scoreMessage.getScoreFakeMap().getScoreMap();
		for(SnakeNumber snakeNumber : scoreFakeMap.keySet())
		{
			players.get(snakeNumber).actScore(scoreFakeMap.get(snakeNumber).getScore());
		}
	}
}
