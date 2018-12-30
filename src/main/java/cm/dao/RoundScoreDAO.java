package cm.dao;

import cm.entity.RoundScore;
import cm.mapper.RoundScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/27
 */
@Component
public class RoundScoreDAO {
    @Autowired
    private RoundScoreMapper roundScoreMapper;

    public RoundScore getByRoundIdAndTeamId(Long roundId,Long teamId){
        return roundScoreMapper.getByRoundIdAndTeamId(roundId, teamId);
    }
}
