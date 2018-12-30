package cm.vo;

import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class ShareCourseVO {
    /**
     * 分享类型：
     * 1表示分享team
     * 2表示分享seminar
     */
    private Integer shareType;
    /**
     * 主从类型：
     * 1表示此课程为主
     * 2表示此课程为从
     */
    private Integer shareRelation;
    private Long shareCourseId;
    private String courseMame;
    private String teacherName;
}
