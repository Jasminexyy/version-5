package cm.dao;

import cm.entity.SeminarScore;
import cm.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/27
 */
@Component
public class SeminarScoreDAO {
    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

    public SeminarScore getByKlassSeminarIdAndTeamId(Long klassSeminarId, Long teamId){
        return seminarScoreMapper.getByKlassSeminarIdAndTeamId(klassSeminarId, teamId);
    }

    public SeminarScore getBySeminarIdAndKlassIdAndTeamId(Long seminarId,Long klassId,Long teamId){
        return seminarScoreMapper.getBySeminarIdAndKlassIdAndTeamId(seminarId, klassId, teamId);
    }

    public int deleteByKlassSeminarId(Long klassSeminarId){
        return seminarScoreMapper.deleteByKlassSeminarId(klassSeminarId);
    }

    public void updateSeminarScore(SeminarScore seminarScore,Long klassSeminarId,Long teamId){
        seminarScoreMapper.updateSeminarScore(seminarScore,klassSeminarId,teamId);
    }
}
