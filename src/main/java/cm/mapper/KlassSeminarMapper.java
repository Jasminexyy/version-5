package cm.mapper;

import cm.entity.KlassSeminar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Mapper
@Repository
public interface KlassSeminarMapper {
    /**
     * 根据seminarId，courseId与studentId得到klassSeminar
     * @param seminarId
     * @param courseId
     * @param studentId
     * @return cm.entity.KlassSeminar
     */
    @Select("select * from klass_seminar where seminar_id=#{seminarId} and klass_id " +
            "in (select klass_id from klass_student where course_id=#{courseId} and student_id=#{studentId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "reportDdl",column = "report_ddl"),
            @Result(property = "status",column = "status")
    })
    KlassSeminar getBySeminarIdAndCourseIdAndStudentId(@Param("seminarId") Long seminarId,
                                                        @Param("courseId") Long courseId,
                                                        @Param("studentId") Long studentId);
    /**
     * 根据seminarId得到所有KlassSeminar
     * @param seminarId
     * @return java.util.List<cm.entity.KlassSeminar>
     */
    @Select("select * from klass_seminar where seminar_id=#{seminarId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "reportDdl",column = "report_ddl"),
            @Result(property = "status",column = "status")
    })
    List<KlassSeminar> listBySeminarId(@Param("seminarId") Long seminarId);

    /**
     * 根据klassId获得所有KlassSeminar
     * @param klassId
     * @return java.util.List<cm.entity.KlassSeminar>
     */
    @Select("select * from klass_seminar where klass_id=#{klassId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "reportDdl",column = "report_ddl"),
            @Result(property = "status",column = "status")
    })
    List<KlassSeminar> listByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据KlassSeminarId获得klassSeminar
     * @param klassSeminarId
     * @return cm.entity.KlassSeminar
     */
    @Select("select * from klass_seminar where id=#{klassSeminarId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "reportDdl",column = "report_ddl"),
            @Result(property = "status",column = "status")
    })
    KlassSeminar getByKlassSeminarId(@Param("klassSeminarId") Long klassSeminarId);

    /**
     * 根据seminarId和klassI获得klassSeminar
     * @param seminarId
     * @param klassId
     * @return cm.entity.KlassSeminar
     */
    @Select("select * from klass_seminar where seminar_id=#{seminarId} and klass_id=#{klassId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "reportDdl",column = "report_ddl"),
            @Result(property = "status",column = "status")
    })
    KlassSeminar getBySeminarIdAndKlassId(@Param("seminarId") Long seminarId,
                                          @Param("klassId") Long klassId);

    /**
     * 根据KlassSeminarId删除此KlassSeminar
     * @param klassSeminarId
     * @return int
     */
    @Delete("delete from klass_seminar where id = #{klassSeminarId}")
    int deleteByKlassSeminarId(@Param("klassSeminarId") Long klassSeminarId);

    /**
     * 根据seminarId删除所有klassSeminar
     * @param seminarId
     * @return int
     */
    @Delete("delete from klass_seminar where seminar_id = #{seminarId}")
    int deleteBySeminarId(@Param("seminarId") Long seminarId);

    /**
     * 根据klassId删除所有klassSeminar
     * @param klassId
     * @return int
     */
    @Delete("delete from klass_seminar where klass_id = #{klassId}")
    int deleteByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据klassId和seminarId新建KlassSeminar
     * @param klassId
     * @param seminarId
     * @return int
     */
    @Insert("insert into klass_seminar(klass_id,seminar_id,status) " +
            "values(#{klassId},#{seminarId},0)")
    int createByKlassIdAndSeminarId(@Param("klassId") Long klassId,
                                    @Param("seminarId") Long seminarId);


    /**
     * 根据seminarId修改reportDDl
     * @param reportDdl
     * @param seminarId
     * @return int
     */
    @Update("update klass_seminar set report_ddl=#{reportDdl} where seminar_id=#{seminarId}")
    int updateReportDdlBySeminarId(@Param("reportDdl") Timestamp reportDdl,
                                   @Param("seminarId") Long seminarId);

    /**
     * 根据klassSeminarId修改具体讨论课DDL
     * @param reportDdl
     * @param seminarId
     * @return int
     */
    @Update("update klass_seminar set report_ddl=#{reportDdl} where id=#{klassSeminarId}")
    int updateReportDdlByKlassSeminarId(@Param("reportDdl") Timestamp reportDdl,
                                        @Param("klassSeminarId") Long seminarId);


    /**
     * 根据Id修改讨论课状态
     * @param klassSeminarId
     * @param status
     * @return int
     */
    @Update("update klass_seminar set status=#{status} where id=#{klassSeminarId}")
    int setStatusById(@Param("klassSeminarId") Long klassSeminarId,
                      @Param("status") Byte status);
}
