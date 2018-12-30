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

    @JsonProperty("classId")
    private Long klassId;

    private Byte roundSerial;

    private String seminarName;

    private Byte seminarSerial;

    private String introduction;

    private Byte seminarStatus;

    private Byte teamNumLimit;

    /**
     * AttendanceVO表示报名情况
     */
    @JsonProperty("attendance")
    private AttendanceVO attendanceVO;

    @JsonProperty("registerStartTime")
    private String enrollStartTime;

    @JsonProperty("registerEndTime")
    private String enrollEndTime;
}
