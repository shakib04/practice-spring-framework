package learning.practice.web.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }


    @Service
    public static class AsyncService {

        private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

        @Async(value = "taskExecutor")
        public void test() {
            log.info("task executed");
        }
    }

//  @Bean(name = "taskScheduler")
//  public ThreadPoolTaskScheduler taskScheduler()
//  {
//    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//    scheduler.setPoolSize(10);
//    scheduler.setThreadNamePrefix("Scheduled-");
//    scheduler.initialize();
//    return scheduler;
//  }
}
