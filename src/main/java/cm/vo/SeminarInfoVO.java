package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class SeminarInfoVO {

    private String courseName;

    private Long seminarId;

    private Long klassId;

    private Byte isVisible;

    private Byte roundSerial;

    private String seminarName;

    private Byte seminarSerial;

    private String introduction;

    private Byte seminarStatus;

    private Byte teamNumLimit;

    private AttendanceListVO attendanceListVO;

    private String enrollStartTime;

    private String enrollEndTime;
}
