package jdbc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBMngScheduler implements ServletContextListener {

	private static ScheduledExecutorService scheduler;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		int schedulerThreadsAmount = 1;
		scheduler = Executors.newScheduledThreadPool(schedulerThreadsAmount);
	}
	
	public static ScheduledExecutorService getScheduler() {
		return scheduler;
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		scheduler.shutdownNow();
	}
}
