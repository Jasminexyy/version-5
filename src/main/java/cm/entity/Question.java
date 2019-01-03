package cm.entity;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class Question {
    private Long id;
    private Byte isSelected=0;
    private Long teamId;
    private Long studentId;
    private Long klassSeminarId;
    private Long attendanceId;
    private BigDecimal score;
}
