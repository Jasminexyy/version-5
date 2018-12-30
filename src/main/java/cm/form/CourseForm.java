package cm.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Data
public class CourseForm {
    @JsonProperty("name")
    private String courseName;
    @JsonProperty("intro")
    private String introduction;
    @JsonProperty("presentationWeight")
    private Byte presentationPercentage;
    @JsonProperty("questionWeight")
    private Byte questionPercentage;
    @JsonProperty("reportWeight")
    private Byte reportPercentage;
    @JsonProperty("startTeamTime")
    private String teamStartTime;
    @JsonProperty("endTeamTime")
    private String teamEndTime;
    @JsonProperty("minMemberNumber")
    private Byte minMember;
    @JsonProperty("maxMemberNumber")
    private Byte maxMember;
    @JsonProperty("courseList")
    private List<CourseMemberLimitStrategyForm>courseMemberLimitStrategyFormList;
    /**
     * 与或非
     * 0表示都满足
     * 1表示其中有1满足
     */
    private Byte andOrOr;
    @JsonProperty("conflictCourses")
    private List<IdFormList> conflictCourseIds;

}
