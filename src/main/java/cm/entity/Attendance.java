package cm.entity;

import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class Attendance {
    private Long id;
    private Long klassSeminarId;
    private Long teamId;
    private Integer teamOrder;
    private Byte isPresent=0;
    private String reportName;
    private String reportUrl;
    private String pptName;
    private String pptUrl;
}
