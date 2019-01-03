package cm.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/30
 */
@Data
public class TeamNeedVO {
    private CourseMemberLimitStrategyVO teamMemberLimitStrategy;
    private List<CourseMemberLimitStrategyVO> CourseMemberLimitStrategyList;
    private List<ConflictCourseStrategyVO> ConflictCourseStrategyList;
}
