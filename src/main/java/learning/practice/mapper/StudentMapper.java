package learning.practice.mapper;

import learning.practice.dto.StudentDto;
import learning.practice.model.Student;

public class StudentMapper {

    static {
        System.out.println("from student mapper, static block");
    }

    public static Student toEntity(StudentDto dto) {
        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setVersion(dto.getVersion());
        entity.setAddedBy(dto.getAddedBy());
        entity.setLastModifiedBy(dto.getLastModifiedBy());
        return entity;
    }

    public static StudentDto toDto(Student entity) {
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setVersion(entity.getVersion());
        dto.setAddedBy(entity.getAddedBy());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        return dto;
    }
}
