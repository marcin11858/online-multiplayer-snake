package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

/**
 * Wydarzenie zwi¹zane z koniecznoœci¹ wykonania ruchu przez wê¿a.
 * Wydarzenia te wrzucane s¹ do kolejki blokuj¹cej co pewien kwan czasu trzez w¹tek timera.
 * 
 * @author Marcin Wlaz³y
 * @version 20110601
 */
public class TimerEvent extends GameEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

}
