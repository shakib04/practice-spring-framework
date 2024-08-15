package learning.practice.event.listeners;

import learning.practice.model.User;
import learning.practice.model.UserDetails;
import learning.practice.repository.UserDetailsRepository;
import learning.practice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Objects;

@Component
public class ApplicationStartupEventListener {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartupEventListener.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void saveUserListener() {
        saveUser();
        log.info("saved user");
    }

    private void saveUser(){
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setUsername("joy");
        User savedUser = userRepository.save(user);


        UserDetails userDetails = new UserDetails();
        //userDetails.setId(System.currentTimeMillis());
        userDetails.setFullName("joy miah");
        userDetails.setUser(savedUser);
        userDetailsRepository.save(userDetails);
    }
}
