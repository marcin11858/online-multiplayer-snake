package pl.edu.pw.elka.proz.snake.model;

/**
 * Opisuje rozmiar czêœci cia³a wê¿a.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
public enum BodySize
{
	NORMAL, BIG;

	/** zmienia rozmiar czêœci cia³a na du¿y */
	BodySize grow()
	{
		return BIG;
	}
}
