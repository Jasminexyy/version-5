package cm.dao;

import cm.entity.TeamValidApplication;
import cm.mapper.TeamValidApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Component
public class
TeamValidApplicationDAO {
    @Autowired
    private TeamValidApplicationMapper teamValidApplicationMapper;

    public int addApplication(TeamValidApplication teamValidApplication){
        return teamValidApplicationMapper.addApplication(teamValidApplication);
    }

    public int approveApplication(Long teacherId,Long teamId){
        return teamValidApplicationMapper.approveApplication(teacherId,teamId);
    }

    public List<TeamValidApplication> listByTeacherId(Long teacherId){
        return teamValidApplicationMapper.listByTeacherId(teacherId);
    }
}
