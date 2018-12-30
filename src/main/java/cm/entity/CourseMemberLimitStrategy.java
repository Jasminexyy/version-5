package cm.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class CourseMemberLimitStrategy {
    private Long id;
    private Long courseId;
    private Byte minMember;
    private Byte maxMember;
}
