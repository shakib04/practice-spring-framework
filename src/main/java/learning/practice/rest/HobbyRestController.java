package learning.practice.rest;

import learning.practice.model.Hobby;
import learning.practice.model.Student;
import learning.practice.repository.HobbyRepository;
import learning.practice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HobbyRestController {

    private final HobbyRepository hobbyRepository;

    private final StudentRepository studentRepository;

    @PostMapping("/v1/hobbies")
    public ResponseEntity<?> create(@RequestBody Hobby hobby){
        Optional<Student> student = studentRepository.findById(hobby.getStudent().getId());
        hobby.setStudent(null);
        return ResponseEntity.ok(hobbyRepository.save(hobby));
    }

    @GetMapping("/v1/hobbies/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(hobbyRepository.findById(id));
    }
}
