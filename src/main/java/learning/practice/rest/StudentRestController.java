package learning.practice.rest;

import learning.practice.dao.StudentDao;
import learning.practice.dao.TeacherDao;
import learning.practice.dto.StudentCreateDto;
import learning.practice.dto.StudentDto;
import learning.practice.enums.Order;
import learning.practice.model.Hobby;
import learning.practice.model.Student;
import learning.practice.pojo.Sort;
import learning.practice.repository.HobbyRepository;
import learning.practice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@ComponentScan(basePackages = "learning.practice.dao")
@Log4j2
public class StudentRestController {

    private final StudentDao studentDao;

    public final TeacherDao teacherDao;

    private final StudentRepository studentRepository;

    private final HobbyRepository hobbyRepository;

    @Value("${this.app.name}")
    private String className;

    @PostMapping("/v1/students")
    public ResponseEntity<?> create(@RequestBody StudentCreateDto studentCreateDto) {
        Student student = studentCreateDto.mapToEntity(hobbyRepository);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @GetMapping("/v1/students")
    public ResponseEntity<List<StudentDto>> findAll(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false, defaultValue = "id") String orderBy,
            @RequestParam(required = false, defaultValue = "asc") Order order
    ) {
        log.info(className);
        log.info(teacherDao);
        Sort sort = new Sort(orderBy, order);
        List<StudentDto> studentDtoList;
        if (firstName != null) {
            studentDtoList = studentDao.findByFirstName(firstName, sort);
        } else {
            studentDtoList = studentDao.findAll(sort);
        }
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().header("accept", "").body(studentDtoList);
    }

    @GetMapping("/v1/students/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Integer id) {
        StudentDto studentDto = studentDao.getById(id);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().header("accept", "").body(studentDto);
    }

    @GetMapping("/v2/students/{id}")
    public ResponseEntity<Optional<Student>> findByIdEntity(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        Optional<Student> student2 = student.map(student1 -> {
            List<Hobby> hobbies = student1.getHobbies()
                    .stream()
                    .peek(hobby -> hobby.setStudent(null))
                    .collect(Collectors.toList());
            student1.setHobbies(hobbies);
            return student1;
        });
        return ResponseEntity.ok(student2);
    }

    @PutMapping("/v1/students/{id}")
    public ResponseEntity<Integer> update(
            @PathVariable Integer id,
            @RequestBody StudentDto studentDto) {
        int i = studentDao.updateStudent(id, studentDto);
        return ResponseEntity.ok(i);
    }

    @PutMapping("/v2/students/{id}")
    public ResponseEntity<StudentDto> updateV2(
            @PathVariable Integer id,
            @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentDao.updateStudentV2(id, studentDto));
    }

    @DeleteMapping("/v1/students/{id}")
    public ResponseEntity<Integer> delete(
            @PathVariable Integer id) {
        int i = studentDao.deleteV1(id);
        return ResponseEntity.ok(i);
    }

    @DeleteMapping("/v2/students/{id}")
    public ResponseEntity<Void> deleteV2(
            @PathVariable Integer id) {
        studentDao.deleteV2(id);
        return ResponseEntity.noContent().build();
    }
}
