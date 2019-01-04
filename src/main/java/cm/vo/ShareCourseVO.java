package cm.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/22
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor()
public class ShareCourseVO {
    /**
     * 1为主 2为从
     */
    private Integer shareRelation;
    /**
     * 1为team 2为seminar
     */
    private Integer shareType;


    private Long shareCourseId;
    private String courseName;
    private String teacherName;
//    public void setShareCourse(Long shareCourseId,String courseName,String teacherName)
//    {
//
//        this.shareCourseId=shareCourseId;
//        this.courseName=courseName;
//        this.teacherName=teacherName;
//
//    }
}
