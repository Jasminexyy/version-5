package cm.entity;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class TeamStrategy {
    private Long courseId;
    private Byte strategySerial;
    private String strategyName;
    private Long strategyId;
}
