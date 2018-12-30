package cm.form;

import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Data
public class CourseMemberLimitStrategyForm {
    Long courseId;
    Byte minMember;
    Byte maxMember;
}
