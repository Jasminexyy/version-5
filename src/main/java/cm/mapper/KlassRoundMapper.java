package cm.mapper;

import cm.entity.KlassRound;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/22
 */
@Mapper
@Repository
public interface KlassRoundMapper {
    /**
     * 根据KlassId删除全部
     * @param klassId
     * @return int
     */
    @Delete("delete from klass_round where klass_id = #{klassId}")
    int deleteByKlassId(@Param("klassId") Long klassId);

    /**
     * 根据RoundId获得KlassRound
     * @param roundId
     * @return java.util.List<cm.entity.KlassRound>
     */
    @Select("select * from klass_round where round_id=#{roundId}")
    @Results({
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "enrollNumber",column = "enroll_number")
    })
    List<KlassRound> listByRoundId(@Param("roundId") Long roundId);

    /**
     * 根据RoundId与KlassId获得EnrollNumber
     * @param klassId
     * @param roundId
     * @return java.lang.Byte
     */
    @Select("select enroll_number from klass_round where klass_id=#{klassId} and round_id=#{roundId}")
    Byte getEnrollNumberByRoundIdAndKlassId(@Param("klassId") Long klassId,
                                            @Param("roundId") Long roundId);
    /**
     * 根据RoundId与KlassId更新EnrollNumber
     * @param enrollNumber
     * @param klassId
     * @param roundId
     * @return int
     */
    @Update("update klass_round set enroll_number=#{enrollNumber} where klass_id=#{klassId} and round_id=#{roundId}")
    int updateEnrollNumberByRoundIdAndKlassId(@Param("enrollNumber") Byte enrollNumber,
                                              @Param("klassId") Long klassId,
                                              @Param("roundId") Long roundId);
    /**
     * 根据RoundId获得KlassRound
     * @param roundId
     * @return java.util.List<cm.entity.KlassRound>
     */
    @Select("select * from klass_round where round_id=#{roundId}")
    @Results({
            @Result(property = "klassId",column = "klass_id"),
            @Result(property = "roundId",column = "round_id"),
            @Result(property = "enrollNumber",column = "enroll_number")
    })
    List<KlassRound> listKlassRoundsByRoundId(@Param("roundId") Long roundId);
}
