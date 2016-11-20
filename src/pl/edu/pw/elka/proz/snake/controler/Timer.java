package pl.edu.pw.elka.proz.snake.controler;

import java.util.concurrent.BlockingQueue;

import pl.edu.pw.elka.proz.snake.events.GameEvent;
import pl.edu.pw.elka.proz.snake.events.TimerEvent;

/**
 * Timer odlicza czas pomiêdzy kolejnymi ruchami wê¿a.
 * 
 * @author Marcin Wlaz³y
 * @version 20110602
 */
class Timer implements Runnable
{
	/**Kolejka blokuj¹ca w której timer umieszcza eventy. */
	private final BlockingQueue<GameEvent> blockingQueue;

	/**
	 * Tworzy Timer.
	 * 
	 * @param blockingQueue klejka blokuj¹ca zawieraj¹ca eventy od gracza
	 */
	Timer(final BlockingQueue<GameEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	/**
	 * Usypia w¹tek po czym umieszcza TimerEvent w kolejce blokuj¹cej.
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
