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
     * @param courseId1
     * @param courseId2
     * @return int
     */
    @Insert("insert into conflict_course_strategy(course_1_id,course_2_id) values(#{courseId1},#{courseId2})")
    int createConflictCourseStrategy(@Param("courseId1") Long courseId1,
                                     @Param("courseId2") Long courseId2);

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
}