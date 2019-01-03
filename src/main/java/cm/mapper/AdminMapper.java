package cm.mapper;

import cm.entity.Admin;
import cm.entity.LoginUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Mapper
@Repository
public interface AdminMapper {
    /**
     * 根据AdminId获得admin
     * @param adminId
     * @return cm.entity.admin
     */
    @Select("select * from admin where id=#{adminId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password")
    })
    Admin getByAdminId(@Param("adminId") String adminId);

    /**
     * 根据AdminAccount获得admin 判断管理员是否登录
     * @param adminAccount
     * @return com.common.entity.LoginUser
     */
    @Select("select id,account,password from admin where account=#{adminAccount}")
    @Results({
            @Result(property = "userId",column = "id"),
            @Result(property = "userAccount",column = "account"),
            @Result(property = "passWord",column = "password")
    })
    LoginUser getAdminByAdminAccount(@Param("adminAccount") String adminAccount);
}
