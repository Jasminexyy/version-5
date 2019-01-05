package cm.mapper;

import cm.entity.LoginUser;
import cm.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Mapper
@Repository
public interface TeacherMapper {

    /**
     * 根据teacherId获取teacher
     * @param teacherId
     * @return cm.entity.Teacher
     */
    @Select("select * from teacher where id=#{teacherId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "email",column = "email")
    })
    Teacher getByTeacherId(Long teacherId);

    /**
     * 根据course得到teacher
     * @param courseId
     * @return cm.entity.Teacher
     */
    @Select("select * from teacher where id " +
            "in(select teacher_id from course where id=#{courseId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "email",column = "email")
    })
    Teacher getByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据account得到教师
     * @param userAccount
     * @return cm.entity.LoginUser
     */
    @Select("select id,account,password,is_active from teacher where account=#{userAccount}")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "userAccount",column = "account"),
            @Result(property = "passWord",column = "password"),
            @Result(property = "isActive",column = "is_active")
    })
    LoginUser getUserByTeacherAccount(@Param("userAccount") String userAccount);

    /**
     *
     * @param userAccount
     * @return cm.entity.Teacher
     */
    @Select("select * from teacher where account=#{userAccount}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "email",column = "email")
    })
    Teacher getByTeacherAccount(@Param("userAccount") String userAccount);

    /**
     * 得到全部教师
     * @param
     * @return java.util.List<cm.entity.Teacher>
     */
    @Select("select * from teacher")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "email",column = "email")
    })
    List<Teacher> listAllTeacher();

    /**
     * 根据教工号或姓名得到教师
     * @param nameOrAccount
     * @return cm.entity.Teacher
     */
    @Select("select * from teacher where account=#{nameOrAccount} or teacher_name=#{nameOrAccount}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "teacherName",column = "teacher_name"),
            @Result(property = "email",column = "email")
    })
    Teacher getByNameOrAccount(String nameOrAccount);

    /**
     * 创建教师
     * @param teacher
     * @return int
     */
    @Insert("insert into teacher(account,password,teacher_name,email,is_active) " +
            "values(#{account},#{password},#{teacherName},#{email},#{isActive})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createTeacher(Teacher teacher);

    /**
     * 激活教师账户
     * @param password
     * @param teacherId
     * @return java.lang.Integer
     */
    @Update("update teacher set password=#{password},is_active=1 " +
            "where id=#{teacherId}")
    Integer activateByTeacherId(@Param("password") String password,
                                @Param("teacherId") Long teacherId);

    /**
     * 修改教师信息
     * @param teacher
     * @return int
     */
    @Update("update teacher set teacher_name=#{teacherName},account=#{account},email=#{email} where id=#{id}")
    int modifyTeacher(Teacher teacher);

    /**
     * 重置教师密码
     * @param teacherId
     * @return int
     */
    @Update("update teacher set password=123456 where id=#{teacherId}")
    int revertPassword(Long teacherId);


    /**
     * 删除教师
     * @param teacherId
     * @return int
     */
    @Delete("delete from teacher where id=#{teacherId}")
    int deleteByTeacherId(Long teacherId);

    /**
     * 修改教师密码
     * @param password
     * @param teacherId
     * @return int
     */
    @Update("update teacher set password=#{password} where id=#{teacherId}")
    int modifyPasswordByTeacherId(@Param("password") String password,
                                  @Param("teacherId") Long teacherId);

    /**
     * 修改教师邮箱
     * @param email
     * @param teacherId
     * @return int
     */
    @Update("update teacher set email=#{email} where id=#{teacherId}")
    int modifyEmailByTeacherId(@Param("email") String email,
                               @Param("teacherId") Long teacherId);
}
