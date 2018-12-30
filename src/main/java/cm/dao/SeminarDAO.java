package cm.dao;

import cm.entity.Seminar;
import cm.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/23
 */
@Component
public class SeminarDAO {
    @Autowired
    private SeminarMapper seminarMapper;

    public Seminar getBySeminarId(Long seminarId){
        return seminarMapper.getBySeminarId(seminarId);
    }

    public Seminar getBySeminarIdAndTeacherId(Long seminarId,Long teacherId){
        return seminarMapper.getBySeminarIdAndTeacherId(seminarId, teacherId);
    }

    public int deleteBySeminarId(Long seminarId){
        return seminarMapper.deleteBySeminarId(seminarId);
    }

    public int createSeminar(Seminar seminar){
        return seminarMapper.createSeminar(seminar);
    }

    public int modifySeminar(Seminar seminar){
        return seminarMapper.modifySeminar(seminar);
    }

    public List<Seminar> listByRoundId(Long roundId){
        return  seminarMapper.listByRoundId(roundId);
    }


    public int modifyRoundId(Long roundId,Long semianrId){
        return seminarMapper.modifyRoundIdBySeminarId(roundId,semianrId);
    }

    public Long getRoundIdBySeminarId(Long semianrId){
        return  seminarMapper.getRoundIdBySeminarId(semianrId);
    }
}
