package cm.dao;

import cm.entity.KlassRound;
import cm.entity.Round;
import cm.mapper.KlassRoundMapper;
import cm.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/23
 */
@Component
public class RoundDAO {
    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private KlassRoundMapper klassRoundMapper;

    public Round getByRoundId(Long roundId){
        return roundMapper.getByRoundId(roundId);
    }

    public List<Round> listByCourseId(Long courseId){
        return roundMapper.listByCourseId(courseId);
    }

    public int deleteByRoundId(Long roundId){
        return roundMapper.deleteByRoundId(roundId);
    }


    public List<KlassRound> listKlassRoundsByRoundId(Long roundId){
        return klassRoundMapper.listKlassRoundsByRoundId(roundId);
    }
}
