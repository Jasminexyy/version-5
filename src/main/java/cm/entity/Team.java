package cm.entity;

import lombok.Data;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class Team {
    private Long id;
    private Long courseId;
    private Long klassId;
    private Long leaderId;
    private String teamName;
    private Byte teamSerial;
    private Byte status=0;
    private List<Student>students;
}
