package cm.mapper;

import cm.entity.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/28
 */
@Mapper
@Repository
public interface CourseMemberLimitStrategyMapper {
    /**
     * 根据courseId获得CourseMemberLimitStrategy
     * @param courseId
     * @return cm.entity.CourseMemberLimitStrategy
     */
    @Select("select * from course_member_limit_strategy where course_id=#{courseId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "minMember",column = "min_member"),
            @Result(property = "maxMember",column = "max_member")
    })
    CourseMemberLimitStrategy getByCourseId(@Param("courseId") Long courseId);
    /**
     * 根据StrategyId得到CourseMemberLimitStrategy 关于某课程的人数控制
     * @param strategyId
     * @return cm.entity.CourseMemberLimitStrategy
     */
    @Select("select * from course_member_limit_strategy where id=#{strategyId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "courseId",column = "course_id"),
            @Result(property = "minMember",column = "min_member"),
            @Result(property = "maxMember",column = "max_member")
    })
    CourseMemberLimitStrategy getByStrategyId(@Param("strategyId") Long strategyId);

    /**
     * 创建CourseMemberLimitStrategy
     * @param courseMemberLimitStrategy
     * @return int
     */
    @Insert("insert into course_member_limit_strategy(course_id,min_member,max_member) " +
            "values(#{courseId},#{minMember},#{maxMember})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createCourseMemberLimitStrategy(@Param("courseMemberLimitStrategy") CourseMemberLimitStrategy courseMemberLimitStrategy);
}
