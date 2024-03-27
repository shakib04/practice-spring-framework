package learning.practice.rest;

import learning.practice.dao.StudentDao;
import learning.practice.dao.TeacherDao;
import learning.practice.dto.StudentDto;
import learning.practice.enums.Order;
import learning.practice.pojo.Sort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@ComponentScan(basePackages = "learning.practice.dao")
@Log4j2
public class StudentRestController {

    private StudentDao studentDao;

    public TeacherDao teacherDao;

    @GetMapping("/v1/students")
    public ResponseEntity<List<StudentDto>> findAll(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false, defaultValue = "id") String orderBy,
            @RequestParam(required = false, defaultValue = "asc") Order order
    ) {
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
