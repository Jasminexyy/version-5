package cm.mapper;

import cm.entity.ConflictCourseStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/28
 */
@Mapper
@Repository
public interface ConflictCourseStrategyMapper {

    /**
     * 插入冲突
     * @param id
     * @param courseId
     * @return int
     */
    @Insert("insert into conflict_course_strategy(id,course_id) values(#{id},#{courseId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createConflictCourseStrategy(@Param("id") Long id,
                                     @Param("courseId") Long courseId);

    /**
     * 根据StrategyId获得所有的ConflictCourse
     * @param strategyId
     * @return java.util.List<cm.entity.ConflictCourseStrategy>
     */
    @Select("select * from conflict_course_strategy where id=#{strategyId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "courseId",column = "course_id")
    })
    List<ConflictCourseStrategy> listConflictCourseByStrategyId(@Param("strategyId") Long strategyId);

    /**
     * 更新ConflictCourseStrategy
     * @param strategyId
     * @param conflictCourseId
     * @param newConflictCourseId
     * @return int
     */
    @Update("update conflict_course_strategy set course_id=#{newConflictCourseId} where strategy_id=#{strategyId} and course_id=#{conflictCourseId}")
    int updateConflictCourseStrategy(@Param("strategyId") Long strategyId,
                                     @Param("conflictCourseId") Long conflictCourseId,
                                     @Param("newConflictCourseId") Long newConflictCourseId
    );
}