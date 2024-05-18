package learning.practice.repository;

import jdk.jfr.Description;
import learning.practice.model.Student;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {


    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    void saveAndFetchStudent() {
        saveStudents();

        List<Student> studentList = studentRepository.findAll();
        assertEquals(2, studentList.size());
        assertEquals("ssss", studentList.get(0).getFirstName());
        assertEquals(1, studentList.get(0).getId());
    }

    private void saveStudents() {
        Student student = new Student();
        student.setFirstName("ssss");
        student.setLastName("dddd");

        Student student2 = new Student();
        student2.setFirstName("ssss");
        student2.setLastName("dddd");

        studentRepository.save(student);
        studentRepository.save(student2);
    }

    @Test
    @Order(2)
    void findAll(){
        assertEquals(0, studentRepository.findAll().size());
    }

    @Test
    @Order(3)
    void deleteAll(){
        saveStudents();
        studentRepository.deleteAll();
        assertEquals(0, studentRepository.findAll().size());
    }

    @AfterEach
    void deleteAllAfterEach(){
        //studentRepository.flush();
    }

}