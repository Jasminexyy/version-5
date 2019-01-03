package cm.dao;

import cm.entity.KlassSeminar;
import cm.mapper.KlassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/27
 */
@Component
public class KlassSeminarDAO {
    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    public int createByKlassIdAndSeminarId(Long klassId,Long seminarId){
        return  klassSeminarMapper.createByKlassIdAndSeminarId(klassId,seminarId);
    }

    public KlassSeminar getBySeminarIdAndCourseIdAndStudentId(Long seminarId, Long courseId, Long studentId){
        return klassSeminarMapper.getBySeminarIdAndCourseIdAndStudentId(seminarId, courseId, studentId);
    }

    public KlassSeminar getBySeminarIdAndKlassId(Long seminarId, Long klassId){
        return klassSeminarMapper.getBySeminarIdAndKlassId(seminarId, klassId);
    }

    public List<KlassSeminar>listBySeminarId(Long seminarId){
        return klassSeminarMapper.listBySeminarId(seminarId);
    }

    public List<KlassSeminar>listByKlassId(Long klassId){
        return klassSeminarMapper.listByKlassId(klassId);
    }

    public KlassSeminar getByKlassSeminarId(Long klassSeminarId){
        return klassSeminarMapper.getByKlassSeminarId(klassSeminarId);
    }

    public int deleteByKlassSeminarId(Long klassSeminarId){
        return klassSeminarMapper.deleteByKlassSeminarId(klassSeminarId);
    }

    public int updateReportDdlBySeminarId(Timestamp reportDdl, Long seminarId){
            return klassSeminarMapper.updateReportDdlBySeminarId(reportDdl,seminarId);
    }

    public int updateReportDdlByKlassSeminarid(Timestamp reportDdl, Long klassSeminarId){
        return klassSeminarMapper.updateReportDdlByKlassSeminarId(reportDdl,klassSeminarId);
    }

    public int setKlassSeminarStatus(Long klassSeminarId,Byte status){
        return klassSeminarMapper.setStatusById(klassSeminarId,status);
    }


}
