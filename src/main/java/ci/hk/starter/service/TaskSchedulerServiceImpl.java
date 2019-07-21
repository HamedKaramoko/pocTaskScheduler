package ci.hk.starter.service;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class TaskSchedulerServiceImpl implements TaskSchedulerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedulerServiceImpl.class);
	
	private TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
	
	private ScheduledFuture<?> taskSaved;

	@Override
	public void changeScheduledTask(String cronValue, Runnable task) {
		LOGGER.info("Start : Changing taskScheduler");
		removeScheduledTask();
		addScheduledTask(cronValue, task);
		LOGGER.info("Start : Changing taskScheduler");
	}
	
    private void addScheduledTask(String cronValue, Runnable task) {
		LOGGER.info("Start : Adding taskScheduler");
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(task, new CronTrigger(cronValue));
        taskSaved = scheduledTask;
        LOGGER.info("End : Adding taskScheduler");
    }
    
    private void removeScheduledTask() {
        if(taskSaved == null) {
        	LOGGER.info("No task to remove");
        	return;
        }
        
        LOGGER.info("Start : Removing taskScheduler");
    	taskSaved.cancel(true);
    	taskSaved = null;
        LOGGER.info("End : Removing taskScheduler");
    }

}
