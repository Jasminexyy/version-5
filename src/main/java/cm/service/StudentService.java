package cm.service;

import cm.dao.KlassDAO;
import cm.dao.StudentDAO;
import cm.entity.LoginUser;
import cm.entity.Student;
import cm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {


    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private KlassDAO klassDAO;

    public UserVO getUserVOByAccount(String account) {
        Student s=studentDAO.getByNameOrAccount(account);
        UserVO student=new UserVO();

        student.setId(s.getId());
        student.setAccount(s.getAccount());
        student.setEmail(s.getEmail());
        student.setName(s.getStudentName());
        student.setRole("student");
        student.setIsActive(s.getIsActive());
        student.setPassword(s.getPassword());
        return student;
    }

    public UserVO transferStudentToUserVO(Student student){
        UserVO tmp=new UserVO();
        tmp.setId(student.getId());
        tmp.setName(student.getStudentName());
        tmp.setAccount(student.getAccount());
        tmp.setRole("student");
        tmp.setIsActive(student.getIsActive());
        tmp.setPassword(student.getPassword());
        tmp.setEmail(student.getEmail());
        return tmp;
    }

    public Student getStudentByAccount(String account){
        return studentDAO.getByStudentAccount(account);
    }

    public boolean vertify(String account, String password){
        LoginUser student=new LoginUser();
        if(studentDAO.getUserByStudentAccount(account)!=null) {
            student = studentDAO.getUserByStudentAccount(account);
            if (student.getPassword().equals(password))
                return true;
            else return false;
        }
        else
            return false;
    }

    public boolean modifyStudentEmail(String email,UserVO student){
        studentDAO.modifyEmailByStudentId(email,student.getId());
        return true;
    }

    public boolean modifyStudentPwd(String password,UserVO student){
        studentDAO.modifyPasswordByStudentId(password,student.getId());
        return true;
    }

    public List<Student> findAllStudents(){
        return studentDAO.listAllStudent();
    }

    public void modifyStudent(long id,String account,String studentName,String email){
        Student student=new Student();
        student.setEmail(email);
        student.setStudentName(studentName);
        student.setAccount(account);
        student.setId(id);
        studentDAO.modifyStudent(student);
    }

    public boolean active(String password,String password1,String email,UserVO student)
    {
        if(password.equals(password1)) {
            student.setEmail(email);
            student.setIsActive(Byte.valueOf((byte)1));
            student.setPassword(password);
            studentDAO.activateByStudentId(password,email,student.getId());
            return true;
        }
        else{
            return false;
        }
    }

    public Byte getIs_active(UserVO s)
    {
        return s.getIsActive();
    }

    public Student getByStudentId(Long studentId) {
        return studentDAO.getByStudentId(studentId);
    }

    public List<Student> getStudentNotInTeam(Long courseId,Long studentId) {
        Long klassId=klassDAO.getKlassIdByStudentIdAndCourseId(studentId,courseId);
        return studentDAO.listNoTeamStudentByKlassId(klassId);//班级id
    }

    public List<Student> getStudentNotInTeamByCourseId(Long courseId)
    {
        return studentDAO.listNoTeamStudentByCourseId(courseId);
    }

    public List<UserVO> listStudentByKlassId(Long klassId){
        List<Student> students=studentDAO.listStudentByKlassId(klassId);
        List<UserVO> list=new ArrayList<UserVO>();
        for(int i=0;i<students.size();i++){
            UserVO tmp=transferStudentToUserVO(students.get(i));
            list.add(tmp);
        }
        return list;
    }
}
