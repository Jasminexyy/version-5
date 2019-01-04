package cm.dao;

import cm.entity.Team;
import cm.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/20
 */
@Component
public class TeamDAO {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private TeamStudentMapper teamStudentMapper;
    @Autowired
    private SeminarScoreMapper seminarScoreMapper;
    @Autowired
    private RoundScoreMapper roundScoreMapper;
    @Autowired
    private KlassTeamMapper klassTeamMapper;
    @Autowired
    private TeamValidApplicationMapper teamValidApplicationMapper;


    public Team getByKlassIdAndStudentId(Long klassId, Long studentID){
        return teamMapper.getByKlassIdAndStudentId(klassId, studentID);
    }

    public List<Team> listByKlassId(Long classId){
        return teamMapper.listByKlassId(classId);
    }

    public List<Team> listByCourseId(Long courseId){
        return teamMapper.listByCourseId(courseId);
    }

    public int createTeam(Team team){
        teamMapper.createTeam(team);
        klassTeamMapper.createByKlassIdAndTeamId(team.getKlassId(),team.getId());
        return 1;
    }

    public Team getByTeamId(Long teamId){
        return teamMapper.getByTeamId(teamId);
    }

    public Team getByIdAndLeaderId(Long teamId, Long leaderId){
        return teamMapper.getByIdAndLeaderId(teamId, leaderId);
    }
    public int deleteTeamById(Long teamId){
        questionMapper.deleteByTeamId(teamId);
        seminarScoreMapper.deleteByTeamId(teamId);
        roundScoreMapper.deleteByTeamId(teamId);
        teamValidApplicationMapper.deleteByTeamId(teamId);
        teamStudentMapper.deleteTeamByTeamId(teamId);
        klassTeamMapper.deleteByKlassId(teamId);
        return teamMapper.deleteById(teamId);
    }

    public Team getByCourseIdAndStudentId(Long courseId, Long studentId){
        return  teamMapper.getByCourseIdAndStudentId(courseId,studentId);
    }

    public int addTeamMemberByTeamIdAndStudentId(Long teamId,Long studentId){
        return teamStudentMapper.createByTeamIdAndStudentId(teamId,studentId);
    }


    public int deleteMemberByTeamIdAndStudentId(Long teamId,Long studentId){
        return teamStudentMapper.deleteMemberByTeamIdAndStudentId(teamId, studentId);
    }

    public Long getTeamIdByStudentIdAndKlassId(Long studentId,Long klassId){
        return teamMapper.getTeamIdByStudentIdAndKlassId(studentId,klassId);
    }

    public Long getKlassIdByTeamId(Long teamId){
        return  teamMapper.getKlassIdByTeamId(teamId);
    }

    public Team getByAttendanceId(Long attendanceId){
        return teamMapper.getByAttendanceId(attendanceId);
    }

    //public List<KlassRound> listKlassRoundByTeamIdAnd
}
