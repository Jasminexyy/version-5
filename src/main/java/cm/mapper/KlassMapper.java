package cm.mapper;

import cm.entity.Klass;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/22
 */
@Mapper
@Repository
public interface KlassMapper {
    /**
     * 根据courseId获得所有klass
     * @param courseId
     * @return java.util.List<cm.entity.Klass>
     */
    @Select("select * from klass where course_id =#{courseId} order by klass_serial asc")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "grade",column = "grade"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "klassTime",column = "klass_time"),
            @Result(property = "klassLocation",column = "klass_location"),
            @Result(property = "teams", column = "id", many=@Many(select="cm.mapper.TeamMapper.listByKlassId",fetchType = FetchType.LAZY)),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByKlassId",fetchType = FetchType.LAZY))
    })
    List<Klass>listByCourseId(@Param("courseId") Long courseId);

    /**
     * 根据StudentId和CourseId获得klass
     * @param courseId
     * @param studentId
     * @return cm.entity.Klass
     */
    @Select("select * from klass where id" +
            " in(select klass_id from klass_student where course_id=#{courseId} and student_id =#{studentId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "grade",column = "grade"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "klassTime",column = "klass_time"),
            @Result(property = "klassLocation",column = "klass_location"),
            @Result(property = "teams", column = "id", many=@Many(select="cm.mapper.TeamMapper.listByKlassId",fetchType = FetchType.LAZY)),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByKlassId",fetchType = FetchType.LAZY))
    })
    Klass getByCourseIdAndStudentId(@Param("courseId") Long courseId,
                                    @Param("studentId") Long studentId);

    /**
     * 根据courseId新建klass
     * @param klass
     * @param courseId
     * @return int
     */
    @Insert("insert into klass(course_id,grade,klass_serial,klass_time,klass_location) " +
            "values(#{courseId},#{klass.grade},#{klass.klassSerial},#{klass.klassTime},#{klass.klassLocation})")
    @Options(useGeneratedKeys = true, keyProperty = "klass.id")
    int createByCourseId(@Param("klass") Klass klass,
                         @Param("courseId") Long courseId);


    /**
     * 根据klassId获得klass
     * @param klassId
     * @return cm.entity.Klass
     */
    @Select("select * from klass where id=#{klassId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "grade",column = "grade"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "klassTime",column = "klass_time"),
            @Result(property = "klassLocation",column = "klass_location"),
            @Result(property = "teams", column = "id", many=@Many(select="cm.mapper.TeamMapper.listByKlassId",fetchType = FetchType.LAZY)),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByKlassId",fetchType = FetchType.LAZY))
    })
    Klass getByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据KlassId与TeacherId获得klass 验证权限操作
     * @param klassId
     * @param teacherId
     * @return cm.entity.Klass
     */
    @Select("select * from klass where id=#{klassId} and course_id " +
            "in(select id from course where teacher_id=#{teacherId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "grade",column = "grade"),
            @Result(property = "klassSerial",column = "klass_serial"),
            @Result(property = "klassTime",column = "klass_time"),
            @Result(property = "klassLocation",column = "klass_location"),
            @Result(property = "teams", column = "id", many=@Many(select="cm.mapper.TeamMapper.listByKlassId",fetchType = FetchType.LAZY)),
            @Result(property = "students", column = "id", many=@Many(select="cm.mapper.StudentMapper.listByKlassId",fetchType = FetchType.LAZY))
    })
    Klass getByKlassIdAndTeacherId(@Param("klassId") Long klassId,
                                   @Param("teacherId") Long teacherId);
    /**
     * 根据KlassId删除班级
     * @param klassId
     * @return int
     */
    @Delete("delete from klass where id = #{klassId}")
    int deleteByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据studentId和courseId得到klassId
     * @param courseId
     * @param studentId
     * @return java.lang.Long
     */
    @Select("select klass_id from klass_student where course_id=#{courseId} and student_id =#{studentId}")
    Long getKlassIdByCourseIdAndStudentId(@Param("courseId") Long courseId,
                                          @Param("studentId") Long studentId);

    /**
     * 根据klassId得到courseId
     * @param klassId
     * @return java.lang.Long
     */
    @Select("select course_id from klass where id=#{klassId}")
    Long getCourseIdByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据klassSerial和courseId得到klassId
     * @param courseId
     * @param klassSerial
     * @return
     */
    @Select("select id from klass where course_id=#{courseId} and klass_serial=#{klassSerial}")
    Long getKlassIdByCourseIdAndKlassSerial(@Param("courseId") Long courseId,
                                            @Param("klassSerial") Long klassSerial);
}
