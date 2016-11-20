package pl.edu.pw.elka.proz.snake.events;

import java.io.Serializable;

/**
 * Wydarzenie zwi�zane z konieczno�ci� wykonania ruchu przez w�a.
 * Wydarzenia te wrzucane s� do kolejki blokuj�cej co pewien kwan czasu trzez w�tek timera.
 * 
 * @author Marcin Wlaz�y
 * @version 20110601
 */
public class TimerEvent extends GameEvent implements Serializable
{
	private static final long serialVersionUID = 1L;

}
