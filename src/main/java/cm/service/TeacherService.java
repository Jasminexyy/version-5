package cm.service;

import cm.dao.TeacherDao;
import cm.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private TeacherDao teacherDao;

    public Teacher findTeacherByAccount(String account){
        return teacherDao.selectTeacherByAccount(account);
    }
}
