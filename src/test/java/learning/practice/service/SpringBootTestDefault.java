package learning.practice.service;

import learning.practice.Main;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = Main.class)
@ActiveProfiles({
        "default"
})
public interface SpringBootTestDefault {
}
