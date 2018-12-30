package cm.service;

import cm.dao.StudentDao;
import cm.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentDao studentDao;

    public Student findStudentByAccount(String account){
        return studentDao.selectStudentByAccount(account);
    }
}
