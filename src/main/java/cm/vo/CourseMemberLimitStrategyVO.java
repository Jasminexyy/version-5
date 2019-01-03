package cm.vo;

import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/30
 */
@Data
public class CourseMemberLimitStrategyVO {
    private Long courseId;
    private Byte minMember;
    private Byte maxMember;
}