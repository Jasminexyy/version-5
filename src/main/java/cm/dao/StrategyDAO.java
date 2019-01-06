package cm.dao;

import cm.entity.*;
import cm.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Component
public class StrategyDAO {
    private static final String TEAM_AND_STRATEGY = "TeamAndStrategy";
    private static final String TEAM_OR_STRATEGY = "TeamOrStrategy";
    private static final String CONFLICT_COURSE_STRATEGY = "ConflictCourseStrategy";
    private static final String MEMBER_LIMIT_STRATEGY = "MemberLimitStrategy";
    private static final String COURSE_MEMBER_LIMIT_STRATEGY= "CourseMemberLimitStrategy";
    private static final Integer MAX_MEMBER=6;


    @Autowired
    private ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    private MemberLimitStrategyMapper memberLimitStrategyMapper;

    @Autowired
    private CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    @Autowired
    private TeamStrategyMapper teamStrategyMapper;

    @Autowired
    private TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    private TeamOrStrategyMapper teamOrStrategyMapper;
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 总判断入口
     */
    public void judgeTeamValid(Team team){
        if (team.getStudents().size()>=MAX_MEMBER){
            team.setStatus((byte)0);
            return;
        }
        List<TeamStrategy> teamStrategyList=teamStrategyMapper.listByCourseId(team.getCourseId());
        for (TeamStrategy teamStrategy:teamStrategyList) {
            if (false== judgeByTotalStrategy(teamStrategy.getStrategyName(),teamStrategy.getStrategyId(),team)) {
                team.setStatus((byte)0);
                return;
            }
        }
        team.setStatus((byte)1);
    }
    /**
     * 根据名字判断此记录符不符合实际
     */
    private Boolean judgeByTotalStrategy(String strategyName, Long strategyId, Team team){
        switch (strategyName){
            case TEAM_AND_STRATEGY :
                if (!judgeByTeamAndStrategy(strategyId,team)) {
                    return false;
                }
                break;
            case TEAM_OR_STRATEGY :
                if (!judgeByTeamOrStrategy(strategyId,team)) {
                    return false;
                }
                break;
            case CONFLICT_COURSE_STRATEGY:
                if (!judgeByConflictCourseStrategy(strategyId,team)) {
                    return false;
                }
                break;
            case MEMBER_LIMIT_STRATEGY :
                if (!judgeByMemberLimitStrategy(strategyId,team)) {
                    return false;
                }
                break;
            case COURSE_MEMBER_LIMIT_STRATEGY :
                if (!judgeByCourseMemberLimitStrategy(strategyId,team)) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private Boolean judgeByTeamAndStrategy(Long strategyId, Team team){
        List<TeamAndStrategy>teamAndStrategyList=teamAndStrategyMapper.listByStrategyId(strategyId);
        for (TeamAndStrategy teamAndStrategy:teamAndStrategyList) {
            if (false== judgeByTotalStrategy(teamAndStrategy.getStrategyName(),teamAndStrategy.getStrategyId(),team)) {
                return false;
            }
        }
        return true;
    }

    private Boolean judgeByTeamOrStrategy(Long strategyId, Team team){
        List<TeamOrStrategy>teamOrStrategyList=teamOrStrategyMapper.listByStrategyId(strategyId);
        for (TeamOrStrategy teamOrStrategy:teamOrStrategyList) {
            if (true== judgeByTotalStrategy(teamOrStrategy.getStrategyName(),teamOrStrategy.getStrategyId(),team)) {
                return true;
            }
        }
        return false;
    }

    private Boolean judgeByConflictCourseStrategy(Long strategyId, Team team){
        List<ConflictCourseStrategy>conflictCourseStrategyList=conflictCourseStrategyMapper.listConflictCourseByStrategyId(strategyId);
        Integer klassNum=0,klassTypeNum=0;
        for (ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList) {
            for (Student student:team.getStudents()) {
                List<Course> tmp=courseMapper.listByStudentId(student.getId());
                for (Course course:tmp) {
                    if (course.getId().equals(conflictCourseStrategy.getCourseId())) {
                        klassNum++;
                    }
                }
            }
            if (klassNum>0){
                klassTypeNum++;
                klassNum=0;
            }
            if (klassTypeNum >=2) {
                return false;
            }
        }
        return true;
    }

    private Boolean judgeByMemberLimitStrategy(Long strategyId, Team team){
        MemberLimitStrategy memberLimitStrategy=memberLimitStrategyMapper.getByMemberLimitStrategyId(strategyId);
        Integer teamSize=team.getStudents().size();
        if (null != memberLimitStrategy.getMinMember()&&teamSize<memberLimitStrategy.getMinMember()) {
            return false;
        }
        if (null != memberLimitStrategy.getMaxMember()&&teamSize>memberLimitStrategy.getMaxMember()) {
            return false;
        }
        return true;
    }

    private Boolean judgeByCourseMemberLimitStrategy(Long strategyId, Team team){
        CourseMemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitStrategyMapper.getByStrategyId(strategyId);
        System.out.println("StrategyDAO:judgeByCourseMemberLimitStrategy");
        Integer count=0;
        for (Student student:team.getStudents()) {
                List<Course> tmp=courseMapper.listByStudentId(student.getId());
            for (Course course:tmp) {
                //System.out.println(courseMemberLimitStrategy.getCourseId());
                if (course.getId().equals(courseMemberLimitStrategy.getCourseId())) {
                    count++;
                }
            }
        }
        if (null != courseMemberLimitStrategy.getMinMember()&&count<courseMemberLimitStrategy.getMinMember()) {
            return false;
        }
        if (null != courseMemberLimitStrategy.getMaxMember()&&count>courseMemberLimitStrategy.getMaxMember()) {
            return false;
        }
        return true;
    }

    public List<TeamStrategy> listTeamStrategyByCourseId(Long courseId){
        return teamStrategyMapper.listByCourseId(courseId);
    }

    public List<TeamStrategy> listAllTeamStrategy(){
        return teamStrategyMapper.listAllTeamStrategy();
    }

    public List<TeamAndStrategy> listTeamAndStrategyByStrategyId(Long strategyId){
        return teamAndStrategyMapper.listByStrategyId(strategyId);
    }

    public List<TeamAndStrategy> listAllTeamAndStrategy(){
        return teamAndStrategyMapper.listAllTeamAndStrategy();
    }

    public MemberLimitStrategy getMemberLimitStrategyById(Long strategyId){
        return memberLimitStrategyMapper.getByMemberLimitStrategyId(strategyId);
    }

    public List<TeamOrStrategy> listTeamOrStrategyByStrategyId(Long strategyId){
        return teamOrStrategyMapper.listByStrategyId(strategyId);
    }

    public List<TeamOrStrategy> listAllTeamOrStrategy(){
        return teamOrStrategyMapper.listAllTeamOrStrategy();
    }

    public CourseMemberLimitStrategy getCourseMemberLimitStrategyById(Long strategyId){
        return courseMemberLimitStrategyMapper.getByStrategyId(strategyId);
    }

    public List<ConflictCourseStrategy> listConflictCourseStrategyByStrategyId(Long strategyId){
        return conflictCourseStrategyMapper.listConflictCourseByStrategyId(strategyId);
    }

    public int createTeamAndStrategy(Long id,String strategyName, Long strategyId){
        return  teamAndStrategyMapper.createTeamAndStrategy(id,strategyName, strategyId);
    }

    public int createCourseMemberLimitStrategy(Long courseId, Byte minMember, Byte maxMember){
        return  courseMemberLimitStrategyMapper.createCourseMemberLimitStrategy(courseId, minMember, maxMember);
    }

    public int createMemberLimitStrategy(Byte minMember, Byte maxMember){
        return  memberLimitStrategyMapper.createMemberLimitStrategy(minMember, maxMember);
    }

    public int createTeamStrategy(Long courseId, Byte strategySerial, String strategyName, Long strategyId){
        return  teamStrategyMapper.createTeamStrategy(courseId, strategySerial, strategyName, strategyId);
    }

    public int createTeamOrStrategy(Long id, String strategyName,Long strategyId){
        return  teamOrStrategyMapper.createTeamOrStrategy(id,strategyName,strategyId);
    }

    public int createConflictCourseStrategy(Long id,Long courseId){
        return  conflictCourseStrategyMapper.createConflictCourseStrategy(id,courseId);
    }

    public int updateConflictCourseStrategy(Long strategyId, Long conflictCourseId, Long newConflictCourseId){
        return  conflictCourseStrategyMapper.updateConflictCourseStrategy(strategyId, conflictCourseId, newConflictCourseId);
    }
    public int updateMemberLimitStrategy(Long strategyId, Byte minMember, Byte maxMember){
        return  memberLimitStrategyMapper.updateMemberLimitStrategy(strategyId, minMember, maxMember);
    }
    public int updateCourseMemberLimitStrategy(Long courseId, Byte minMember, Byte maxMember){
        return  courseMemberLimitStrategyMapper.updateCourseMemberLimitStrategy(courseId, minMember, maxMember);
    }
}