package learning.practice.dao;

import learning.practice.dto.StudentDto;
import learning.practice.mapper.StudentMapper;
import learning.practice.model.Student;
import learning.practice.pojo.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
//@RequiredArgsConstructor
public class StudentDao {

    private EntityManager entityManager;


    public StudentDao() {
    }

    @Autowired
    public StudentDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(StudentDto studentDto) {
        Student entity = StudentMapper.toEntity(studentDto);
        //Student entity  = new Student(null,"shakib", "rahman");
        entityManager.merge(entity);
    }

    public StudentDto getById(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            throw new RuntimeException("No Student Found with Id = " + id);
        }
        return StudentMapper.toDto(student);
    }

    public List<StudentDto> findAll(Sort sort) {
        String orderBy = String.format("%s %s ", sort.fieldName, sort.order);
        TypedQuery<Student> entityManagerQuery = entityManager.createQuery("from Student order by " + orderBy, Student.class);
        return entityManagerQuery.getResultStream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentDto> findByFirstName(String firstName, Sort sort) {
        String orderBy = "";
        if (sort != null && sort.fieldName != null) {
            orderBy = String.format(" order by %s %s", sort.fieldName, sort.order);
        }
        TypedQuery<Student> entityManagerQuery = entityManager.createQuery(
                "from Student where firstName like concat('%',:firstName,'%') " + orderBy, Student.class);
        entityManagerQuery.setParameter("firstName", firstName);
        return entityManagerQuery.getResultStream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public int updateStudent(Integer id, StudentDto student) {
        Query query = entityManager.createQuery(
                "update Student set " +
                        "firstName=:firstName, " +
                        "lastName=:lastName " +
                        "where id = :id");
        query.setParameter("id", id);
        query.setParameter("firstName", student.getFirstName());
        query.setParameter("lastName", student.getLastName());

        return query.executeUpdate();
    }

    @Transactional
    public StudentDto updateStudentV2(Integer id, StudentDto studentDto) {
        Student studentEntity = entityManager.find(Student.class, id);
        studentEntity.setFirstName(studentDto.getFirstName());
        studentEntity.setLastName(studentDto.getLastName());
        return StudentMapper.toDto(entityManager.merge(studentEntity));
    }

    @Transactional
    public int deleteV1(Integer id){
        return entityManager.createQuery("delete from Student where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void deleteV2(Integer id){
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
