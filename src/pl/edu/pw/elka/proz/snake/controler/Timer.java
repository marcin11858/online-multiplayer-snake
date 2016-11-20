package pl.edu.pw.elka.proz.snake.controler;

import java.util.concurrent.BlockingQueue;

import pl.edu.pw.elka.proz.snake.events.GameEvent;
import pl.edu.pw.elka.proz.snake.events.TimerEvent;

/**
 * Timer odlicza czas pomi�dzy kolejnymi ruchami w�a.
 * 
 * @author Marcin Wlaz�y
 * @version 20110602
 */
class Timer implements Runnable
{
	/**Kolejka blokuj�ca w kt�rej timer umieszcza eventy. */
	private final BlockingQueue<GameEvent> blockingQueue;

	/**
	 * Tworzy Timer.
	 * 
	 * @param blockingQueue klejka blokuj�ca zawieraj�ca eventy od gracza
	 */
	Timer(final BlockingQueue<GameEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	/**
	 * Usypia w�tek po czym umieszcza TimerEvent w kolejce blokuj�cej.
	 */
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				Thread.sleep(100);
				blockingQueue.add(new TimerEvent());
			}
		} catch (Exception e)
		{
			System.out.println(Messages.getString("Timer.0")); //$NON-NLS-1$
			e.printStackTrace();
		}
	}
}
