package ci.hk.starter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.hk.starter.service.TaskSchedulerService;

@RestController
@RequestMapping("/testController")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TaskSchedulerService testService;
	
	@PostMapping(value = "/changeScheduledTask")
    public void changeScheduledTask(String cronValue) {
		LOGGER.info("Starting scheduling");
	
		testService.changeScheduledTask(cronValue, () -> LOGGER.info("Setting scheduler : {}", cronValue));
	
		LOGGER.info("Ending scheduling");
    }
}
