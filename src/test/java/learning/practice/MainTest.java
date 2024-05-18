package learning.practice;

import learning.practice.rest.StudentRestController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainTest {
    @Autowired
    StudentRestController studentRestController;

    @Test
    void contextLoads(){
        assertNotNull(studentRestController);
    }


}