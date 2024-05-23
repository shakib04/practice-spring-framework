package learning.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static learning.practice.utility.Printer.println;


@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Main implements AsyncConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    int i = 0;

    //@Scheduled(fixedRate = 1000)
    @Async
    public void print(){
       printHelloWorld();
       printHelloBangladesh();
    }


    @Scheduled(fixedRate = 1000)
    @Async
    public void printHelloBangladesh() {
        //Thread.sleep(5000);
        println(++i);
        println("hello Bangladesh from thread - " + Thread.currentThread().getName());
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void printHelloWorld() {
        //Thread.sleep(2000);
        println(++i);
        println("hello world from thread - " + Thread.currentThread().getName());
    }


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        return executor;
    }


}