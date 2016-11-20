package pl.edu.pw.elka.proz.snake.model;

/**
 * Opisuje rozmiar cz�ci cia�a w�a.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
public enum BodySize
{
	NORMAL, BIG;

	/** zmienia rozmiar cz�ci cia�a na du�y */
	BodySize grow()
	{
		return BIG;
	}
}
