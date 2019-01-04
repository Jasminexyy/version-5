package cm.mapper;

import cm.entity.LoginUser;
import cm.entity.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/18
 */
@Mapper
@Repository
public interface StudentMapper {

    /**
     * 根据studentId获取student
     * @param studentId
     * @return cm.entity.Student
     */
    @Select("select * from student where id=#{studentId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    Student getByStudentId(Long studentId);

    /**
     * 根据UserAccount获得loginUser
     * @param userAccount
     * @return cm.entity.LoginUser
     */
    @Select("select id,account,password,is_active from student where account=#{userAccount}")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "userAccount",column = "account"),
            @Result(property = "passWord",column = "password"),
            @Result(property = "isActive",column = "is_active")
    })
    LoginUser getUserByStudentAccount(@Param("userAccount") String userAccount);

    /**
     * 根据studnetAccount获得student
     * @param userAccount
     * @return cm.entity.Student
     */
    @Select("select * from student where account=#{userAccount}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    Student getByStudentAccount(@Param("userAccount") String userAccount);

    /**
     * 根据Account获得Student
     * @param account
     * @return cm.entity.Student
     */
    @Select("select * from student where account=#{account}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    Student getByAccount(@Param("account") String account);

    /**
     * 获得全部student
     * @param
     * @return java.util.List<cm.entity.Student>
     */
    @Select("select * from student")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    List<Student> listAllStudent();

    /**
     * 根据name或account获得student
     * @param nameOrAccount
     * @return cm.entity.Student
     */
    @Select("select * from student where account=#{nameOrAccount} or student_name=#{nameOrAccount}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    Student getByNameOrAccount(String nameOrAccount);

    /**
     * 根据studentId激活学生账户
     * @param password
     * @param email
     * @param studentId
     * @return java.lang.Integer
     */
    @Update("update student set password=#{password},email=#{email},is_active=0 " +
            "where id=#{studentId}")
    Integer activateByStudentId(@Param("password") String password,
                                @Param("email") String email,
                                @Param("studentId") Long studentId);

    /**
     * 修改信息
     * @param student
     * @return int
     */
    @Update("update student set student_name=#{studentName},account=#{account},email=#{email} where id=#{id}")
    int modifyStudent(Student student);

    /**
     * 重置密码
     * @param studentId
     * @return int
     */
    @Update("update student set password=123456 where id=#{studentId}")
    int revertPassword(Long studentId);

    /**
     * 删除学生
     * @param studentId
     * @return int
     */
    @Delete("delete from student where id=#{studentId}")
    int deleteByStudentId(Long studentId);

    /**
     * 得到小组中的全部成员
     * @param teamId
     * @return java.util.List<cm.entity.Student>
     */
    @Select("select * from student where id " +
            "in(select student_id from team_student where team_id=#{teamId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    List<Student>listByTeamId(@Param("teamId") Long teamId);

    /**
     * 根据klassId获得student
     * @param klassId
     * @return java.util.List<cm.entity.Student>
     */
    @Select("select * from student where id " +
            "in(select student_id from klass_student where klass_id=#{klassId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    List<Student>listByKlassId(@Param("klassId") Long klassId);

    /**
     * 修改学生密码
     * @param password
     * @param studentId
     * @return int
     */
    @Update("update student set password=#{password} where id=#{studentId}")
    int modifyPasswordByStudentId(@Param("password") String password,
                                  @Param("studentId") Long studentId);
    /**
     * 修改学生邮箱
     * @param email
     * @param studentId
     * @return int
     */
    @Update("update student set email=#{email} where id=#{studentId}")
    int modifyEmailByStudentId(@Param("email") String email,
                               @Param("studentId") Long studentId);

    /**
     * 创建学生
     * @param student
     * @return int
     */
    @Insert("insert into student(account,password,student_name,is_active) " +
            "values(#{account},#{password},#{studentName},#{isActive})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createStudent(Student student);


    /**
     * 根据courseId获得课程下未组队学生
     * @param courseId
     * @return java.util.List<cm.entity.Student>
     */
    @Select("select * from student where id " +
            "in(select student_id from klass_student where klass_id" +
            "in(select id from klass where course_id=#{courseId})) and id" +
            "not in (select student_id from team_student where team_id" +
            "in(select id from team where course_id=#{courseId}))")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    List<Student> listNoTeamStudentsByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据klassId获得班级下未组队学生
     * @param klassId
     * @return java.util.List<cm.entity.Student>
     */
    @Select("select * from student where id in (select student_id from klass_student where klass_id=#{klassId})" +
            "and id not in (select student_id from team_student where team_id in(select id from team where klass_id=#{klassId}))")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "email",column = "email"),
            @Result(property = "courseIdList", column = "id", many=@Many(select="cm.mapper.CourseMapper.listCourseIdByStudentId",fetchType = FetchType.LAZY))
    })
    List<Student> listNoTeamStudentsByKlassId(@Param("klassId") Long klassId);
}
