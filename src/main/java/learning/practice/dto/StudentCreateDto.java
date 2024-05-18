package learning.practice.dto;

import learning.practice.model.Hobby;
import learning.practice.model.Student;
import learning.practice.repository.HobbyRepository;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudentCreateDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private List<Long> hobbies;

    public Student mapToEntity(HobbyRepository hobbyRepository){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);

        List<Hobby> hobbies2 = getHobbies(hobbyRepository);
        student.setHobbies(hobbies2);
        return student;
    }

    private List<Hobby> getHobbies(HobbyRepository hobbyRepository) {
        if (hobbyRepository != null){
            return hobbyRepository.findAllById(hobbies);
        }
        return hobbies.stream().map(id -> {
            Hobby hobby = new Hobby();
            hobby.setId(id);
            return hobby;
        }).collect(Collectors.toList());
    }
}
