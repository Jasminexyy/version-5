package cm.vo;

import cm.entity.TeamAndStrategy;
import lombok.Data;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/30
 */
@Data
public class TeamNeedVO {
    private List<TeamAndStrategyVO> teamAndStrategyVOList;
    private List<ConflictCourseStrategyVO> conflictCourseStrategyVOList;
}
