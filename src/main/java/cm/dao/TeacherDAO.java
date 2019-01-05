package cm.dao;

import cm.entity.LoginUser;
import cm.entity.Teacher;
import cm.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Component
public class TeacherDAO {
    @Autowired
    private TeacherMapper teacherMapper;

    public LoginUser getUserByTeacherAccount(String userAccount){
        LoginUser result=teacherMapper.getUserByTeacherAccount(userAccount);
        if(result==null) {
            return null;
        }
        result.setUserRole("teacher");
        return result;
    }


    public Teacher getByTeacherAccount(String account){
        return teacherMapper.getByTeacherAccount(account);
    }

    public List<Teacher> listAllTeacher()
    {
        return teacherMapper.listAllTeacher();
    }


    public Teacher getByNameOrAccount(String nameOrAccount){
        return teacherMapper.getByNameOrAccount(nameOrAccount);
    }


    public Teacher createTeacher(Teacher teacher)
    {
        try {
            teacherMapper.createTeacher(teacher);
        }catch (Exception e){
            return null;
        }
        return teacher;
    }

    public Integer activateByTeacherId(String passWord,Long teacherId) {
        return teacherMapper.activateByTeacherId(passWord,teacherId);
    }

    public Teacher modifyTeacher(Teacher teacher){
        int flag;
        try{
            flag=teacherMapper.modifyTeacher(teacher);
        }catch (Exception e){
            return null;
        }

        if(flag==0){
            return null;
        }

        return teacher;
    }


    public Teacher revertPassword(Long teacherId){
        if(teacherMapper.revertPassword(teacherId)==1){
            return teacherMapper.getByTeacherId(teacherId);
        }

        return null;
    }

    public boolean deleteByTeacherId(Long teacherId){
        if(teacherMapper.deleteByTeacherId(teacherId)==1){
            return true;
        }
        return false;
    }

    public Teacher getByTeacherId(Long teacherId){
        return teacherMapper.getByTeacherId(teacherId);
    }

    public Teacher getByCourseId(Long courseId){
        return teacherMapper.getByCourseId(courseId);
    }

    public Integer modifyPasswordByTeacherId(String password,Long teacherId){
        try {
            return teacherMapper.modifyPasswordByTeacherId(password, teacherId);
        } catch (Exception e) {
            return 0;
        }
    }

    public Integer modifyEmailByTeacherId(String email,Long teacherId){
        try {
            return teacherMapper.modifyEmailByTeacherId(email, teacherId);
        } catch (Exception e) {
            return 0;
        }
    }
}
