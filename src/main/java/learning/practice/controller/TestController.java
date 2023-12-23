package learning.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    private final ApplicationContext applicationContext;
    private final Abcd abcd;

    @Autowired
    @Qualifier("testAbstractClassImpl1")
    private TestAbstractClass testAbstractClass;

    public TestController(ApplicationContext applicationContext, Abcd abcd) {
        this.applicationContext = applicationContext;
        this.abcd = abcd;
    }

    @GetMapping("/test/thread/1")
    public HttpStatus test() {
        try {
            Thread.sleep(50000);
            return HttpStatus.OK;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/test/thread/2")
    public HttpStatus thread2() {
        try {
            Thread.sleep(50000);
            return HttpStatus.OK;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/test/thread/3")
    public HttpStatus thread3() {
        try {
            //Thread.sleep(50000);
            return HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/application-beans")
    public String[] getBeanList() {
        abcd.notify(testAbstractClass);
        abcd.notify(testAbstractClass);
        return applicationContext.getBeanDefinitionNames();
    }
}


interface Abcd {
    void notify(DDD data);
}

@Service
class AbcdImpl implements Abcd {

    @Override
    public void notify(DDD data) {
        System.out.println(data);
        data.printClassName();
    }
}

interface DDD {
    void printClassName();
}


@Component
abstract class TestAbstractClass implements DDD {

}


@Component
class TestAbstractClassImpl1 extends TestAbstractClass {

    @Override
    public void printClassName() {
        System.out.println(this.getClass());
    }
}

@Component
class TestAbstractClassImpl2 extends TestAbstractClass {

    @Override
    public void printClassName() {
        System.out.println(this.getClass());
    }
}


