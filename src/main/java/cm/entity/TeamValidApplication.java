package cm.entity;

import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class TeamValidApplication {
    private Long id;
    private Long teamId;
    private Long teacherId;
    private String reason;
    private Byte status=0;
}
