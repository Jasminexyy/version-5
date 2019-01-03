package cm.dao;

import cm.entity.ShareTeamApplication;
import cm.mapper.ShareTeamApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/26
 */
@Component
public class ShareTeamDAO {


    @Autowired
    private ShareTeamApplicationMapper shareTeamApplicationMapper;

    public List<ShareTeamApplication> listByMainCourseId(Long mainCourseId){
        return shareTeamApplicationMapper.listByMainCourseId(mainCourseId);
    }

    public List<ShareTeamApplication> listBySubCourseTeacherId(Long subCourseTeacherId){
        return shareTeamApplicationMapper.listBySubCourseTeacherId(subCourseTeacherId);
    }
    public ShareTeamApplication getByShareTeamIdAndTeacherId(Long id,
                                                             Long subCourseTeacherId){
        return shareTeamApplicationMapper.getByIdAndSubCourseTeacherId(id,subCourseTeacherId);
    }

    public int updateStatusByShareTeamId(Byte status,Long id){
        return shareTeamApplicationMapper.updateStatusById(status, id);
    }
    public int createShareTeamApplication(Long mainCourseId,Long subCourseId,Long subCourseTeacherId,Byte status){
        return shareTeamApplicationMapper.createShareTeamApplication(mainCourseId, subCourseId, subCourseTeacherId, status);
    }
}
