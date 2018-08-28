package com.sun.data.hotline.mapper;

import com.sun.data.hotline.domain.HotlineScore;
import com.sun.data.hotline.domain.Ranking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface RankingMapper
{
    //礼仪榜的数据查询
    @Select("SELECT * FROM hotline_ranking WHERE year = #{year} AND half_year = #{halfYear} AND category_id = 'd0c5f9902a23444bb7ea7230b995b242'")
    List<Ranking> listPolite(@Param("year") int year, @Param("halfYear") String halfYear);

    //技巧榜
    @Select("SELECT * FROM hotline_ranking WHERE year = #{year} AND half_year = #{halfYear} AND category_id = '1b23bdf9a01c446aa63fa3458e8fe1a6'")
    List<Ranking> listSkill(@Param("year") int year, @Param("halfYear") String halfYear);

    //回应榜
    @Select("SELECT * FROM hotline_ranking WHERE year = #{year} AND half_year = #{halfYear} AND category_id = '06641e28a71248c0a40ce3183dc89f38'")
    List<Ranking> listResponse(@Param("year") int year, @Param("halfYear") String halfYear);

    //批量更新
    int batchUpdateChange(@Param("rankingList")List<Ranking> rankingList);

    //接通率榜单查询
    List<HotlineScore> listConnectionRateList(@Param("year") int year,@Param("halfYear") String halfYear);

    //批量新增
    int batchInsert(@Param("insertList")List<Ranking> insertList);

    //查询综合排名上升的数据(排好序的飙升榜)
    List<HotlineScore> listComprehensiveIncrease(@Param("year") int year, @Param("halfYear") String halfYear);
}
