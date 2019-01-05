package cm.service;

import cm.dao.TeacherDAO;
import cm.entity.LoginUser;
import cm.entity.Teacher;
import cm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherDAO teacherDAO;

    public Teacher findTeacherByAccount(String account){

        return teacherDAO.getByNameOrAccount(account);
    }

    public boolean vertify(String account, String password){
        Teacher tmp=new Teacher();

        if(teacherDAO.getUserByTeacherAccount(account)!=null) {
            tmp=teacherDAO.getByNameOrAccount(account);
            if (tmp.getPassword().equals(password))
                return true;
            else return false;
        }
        else
            return false;
    }

    public boolean modifyEmail(String email,UserVO teacher){
        teacherDAO.modifyEmailByTeacherId(email,teacher.getId());
        teacher.setEmail(email);
        return true;
    }

    public boolean modifyPwd(String password,UserVO teacher){
        teacherDAO.modifyPasswordByTeacherId(password,teacher.getId());
        return true;
    }

    /**
     * 显示所有教师信息
     * @return
     */
    public List<UserVO> listAllTeacher(){

        List<UserVO> userVOS= new LinkedList<UserVO>();
        List<Teacher> teachers=teacherDAO.listAllTeacher();
        for(int i=0;i<teachers.size();i++)
        {
            UserVO userVO=new UserVO();
            Teacher teacher=teachers.get(i);
            //userVO.setRole("teacher");
            userVO.setName(teacher.getTeacherName());
            userVO.setEmail(teacher.getEmail());
            userVO.setAccount(teacher.getAccount());
            userVOS.add(userVO);
        }
        return userVOS;
    }

    public void modifyTeacher(Long id,String account,String teacherName,String email)
    {
        Teacher teacher=new Teacher();
        teacher.setAccount(account);
        teacher.setId(id);
        teacher.setTeacherName(teacherName);
        teacher.setEmail(email);
        teacherDAO.modifyTeacher(teacher);
    }

    public void deleteTeacher(long teacherId){
        teacherDAO.deleteByTeacherId(teacherId);
    }

    public Teacher getTeacherByAccount(String teacherAccount) {
        Teacher teacher=teacherDAO.getByTeacherAccount(teacherAccount);
        return teacher;
    }

    public boolean activate(String password, String password1,UserVO teacher) {
        if(password.equals(password1)) {
            teacherDAO.activateByTeacherId(password,teacher.getId());
            teacher.setIsActive(Byte.valueOf((byte)1));
            return true;
        }
        else return false;
    }

    public UserVO getUserVOByAccount(String account) {
        Teacher t=teacherDAO.getByTeacherAccount(account);
        UserVO teacher=new UserVO();
        teacher.setIsActive(t.getIsActive());
        teacher.setId(t.getId());
        teacher.setAccount(t.getAccount());
        teacher.setEmail(t.getEmail());
        teacher.setName(t.getTeacherName());
        teacher.setRole("teacher");
        teacher.setPassword(t.getPassword());
        return teacher;
    }

    public int getIs_active(UserVO t) {
        if(t.getIsActive()==1)
            return 1;
        else return 0;
    }
}
