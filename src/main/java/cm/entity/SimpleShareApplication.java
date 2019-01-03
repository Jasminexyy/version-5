package cm.entity;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Data
public class SimpleShareApplication  {
    private Long id;
    private Long mainCourseId;
    private Long subCourseId;
    private Long subCourseTeacherId;
    private Byte status;
}
