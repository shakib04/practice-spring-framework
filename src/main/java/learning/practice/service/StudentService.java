package learning.practice.service;

import learning.practice.dto.StudentDto;
import learning.practice.mapper.StudentMapper;
import learning.practice.model.Student;
import learning.practice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentDto getById(Integer id) {
        Student student = studentRepository.findById(id).get();
        return StudentMapper.toDto(student);
    }

    public Student save(StudentDto studentDto) {
        Student studentEntity = StudentMapper.toEntity(studentDto);
        return studentRepository.save(studentEntity);
    }
}
