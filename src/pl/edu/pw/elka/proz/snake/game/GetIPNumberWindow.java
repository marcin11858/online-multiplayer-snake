package pl.edu.pw.elka.proz.snake.game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import pl.edu.pw.elka.proz.snake.view.View;

/**
 * Okienko które umo¿liwia podanie IP serwera.
 * 
 * @author Marcin Wlaz³y
 * @version 20110603
 */
public class GetIPNumberWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	/** Numer IP serwera.*/
	private String ipNumber;
	/**Pole w którym mo¿na podaæ numer IP serwera.*/
	private final JTextField ipNumberField;

	/**
	 * Tworzy nowe okno do wpisania numeru IP serwera.
	 */
	public GetIPNumberWindow()
	{
		super(Messages.getString("GetIPNumberWindow.0")); //$NON-NLS-1$
		setSize(250, 150);
		setLayout(new GridLayout(3, 1));
		JLabel information = new JLabel(Messages.getString("GetIPNumberWindow.1")); //$NON-NLS-1$
		add(information);
		ipNumberField = new JTextField(15);
		ipNumberField.setText(Messages.getString("GetIPNumberWindow.2")); //$NON-NLS-1$
		add(ipNumberField);
		JButton okKey = new JButton(Messages.getString("GetIPNumberWindow.3")); //$NON-NLS-1$
		okKey.addActionListener(new OkKeyLinstener());
		add(okKey);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	/**
	 * Wyœwietla okno.
	 */
	void display()
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				setVisible(true);
			}
		});
	}

	/**
	 * Ukrywa okno.
	 */
	private void hideWindow()
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{

				setVisible(false);
			}
		});
	}
	
	/**
	 * Listener dla przycisku OK w oknie.
	 * 
	 * @author Marcin Wlaz³y
	 * @version 20110603
	 */
	private class OkKeyLinstener implements ActionListener
	{
		@Override
		/**
		 * Utworzenie nowego widoku dla gracza i podanie mu numeru IP
		 */
		public void actionPerformed(final ActionEvent e)
		{
			ipNumber = ipNumberField.getText();
			hideWindow();
			NewView newView = new NewView(ipNumber);
			newView.start();
		}
	}
	private class NewView extends Thread
	{
		private final String ipNumber;
		NewView(String ipNumber)
		{
			this.ipNumber = ipNumber;
		}
		@Override
		public void run()
		{
			View view = new View();
			view.display(ipNumber);
		}
	}
}
