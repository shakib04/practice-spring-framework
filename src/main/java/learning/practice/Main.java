package learning.practice;

import learning.practice.dao.StudentDao;
import learning.practice.dao.TeacherDao;
import learning.practice.daoV2.TeacherDaoImplV2;
import learning.practice.dto.StudentDto;
import learning.practice.model.Student;
import learning.practice.model.Teacher;
import learning.practice.repository.StudentRepository;
import learning.practice.repository.TeacherRepository;
import learning.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.util.Arrays;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("before SpringApplication.run()");
        SpringApplication.run(Main.class, args);
        System.out.println("after SpringApplication.run()");
    }

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherRepository teacherRepository;



    @Bean
    public CommandLineRunner commandLineRunner(String[] args){
        System.out.println("from command line runner, before run method");
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("from command line runner");
                System.out.println(Arrays.toString(args));
                System.out.println("Hello World");

                //Student student_1 = studentRepository.save(new Student(null, "shakib"));
                //System.out.println(student_1);
//                StudentDto student = studentService.getById(13);
//                student.setFirstName("Kiron");
//
//                teacherRepository.save(new Teacher(null, "Mushi"));
//                studentDao.save(student);
            }
        };
    }
}