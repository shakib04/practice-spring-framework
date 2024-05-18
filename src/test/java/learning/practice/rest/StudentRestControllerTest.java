package learning.practice.rest;

import learning.practice.dao.StudentDao;
import learning.practice.dto.StudentDto;
import learning.practice.model.Student;
import learning.practice.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private StudentDao studentDao;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {

        Mockito.when(studentDao.findAll(Mockito.any()))
                .thenReturn(new ArrayList<>());

        int size = testRestTemplate.getForEntity("http://localhost:" + port + "/v1/students",
                List.class).getBody().size();
        assertEquals(0, size);
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void updateV2() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteV2() {
    }
}