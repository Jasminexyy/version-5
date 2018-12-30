package cm.entity;

import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class MemberLimitStrategy {
    private Long id;
    private Byte minMember;
    private Byte maxMember;
}
