package learning.practice.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

public interface TeacherDao {
}

@Service
@Primary
class TeacherDaoImplV1 implements TeacherDao{}
