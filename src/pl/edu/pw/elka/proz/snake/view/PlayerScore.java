package pl.edu.pw.elka.proz.snake.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Informacjie o liczbie zdobytych punktów przez gracza.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
class PlayerScore extends JPanel
{

	private static final long serialVersionUID = 1L;
	/** Label na której wyœwietlana s¹ informacje o liczbie zdobytych punktów przez graczy*/
	private final JLabel score;

	PlayerScore(final Color color)
	{
		setSize(40, 50);
		setBackground(color);
		setLayout(new GridLayout(1, 1));
		score = new JLabel();
		score.setText("0");
		add(score);
	}
	
	/**
	 * Aktualizacja liczby zdobytych punktów.
	 * 
	 * @param newScore nowa liczba zdobytych punktów
	 */
	void actScore(final Integer newScore)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				score.setText(newScore.toString());
			}
		});
	}

}
