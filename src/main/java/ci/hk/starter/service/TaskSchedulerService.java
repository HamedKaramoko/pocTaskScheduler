package ci.hk.starter.service;

@FunctionalInterface
public interface TaskSchedulerService {
	
	void changeScheduledTask(String cronValue, Runnable task);
}