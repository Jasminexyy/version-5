package cm.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class CourseDetailVO {
    private Long id;
    private String courseName;
    private String introduction;
    private Byte presentationPercentage;
    private Byte questionPercentage;
    private Byte reportPercentage;
    private String teamStartTime;
    private String teamEndTime;
}
