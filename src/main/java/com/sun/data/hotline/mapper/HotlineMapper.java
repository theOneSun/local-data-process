package com.sun.data.hotline.mapper;

import com.sun.data.hotline.domain.AreaDictionaries;
import com.sun.data.hotline.domain.CommunicationSkill;
import com.sun.data.hotline.domain.GeneralInformation;
import com.sun.data.hotline.domain.HotlineScore;
import com.sun.data.hotline.domain.ProductAttribute;
import com.sun.data.hotline.domain.QuestionResponse;
import com.sun.data.hotline.domain.ReceptionPolite;
import com.sun.xml.internal.xsom.impl.ListSimpleTypeImpl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface HotlineMapper
{

    @Select("SELECT * FROM hotline_general_information WHERE year = '2017' AND half = '上';")
    List<GeneralInformation> getAllGeneral();

    @Select("SELECT * FROM hotline_detail_communication_skill WHERE year = '2017' AND half = '上';")
    List<CommunicationSkill> getAllSkill();

    @Select("SELECT * FROM hotline_detail_question_response WHERE year = '2017' AND half = '上';")
    List<QuestionResponse> getAllResponse();

    @Select("SELECT * FROM hotline_detail_reception_polite WHERE year = '2017' AND half = '上';")
    List<ReceptionPolite> getAllPolite();


    // 批量新增新增hotlineScore
    int batchInsertHotlineScore(@Param("insertList") List<HotlineScore> insertList);

    //
    int batchInsertHotlineScore2(@Param("insertList") List<HotlineScore> insertList);

    //找出城市名一样的和id
    @Select("SELECT a.id,a.name,b.id 'value' FROM base_product_attribute a,base_area_dictionaries b WHERE a.name = b.areaname AND a.product_type_id = 'cffaf02f4079477ba1ef22e2891ccd0b'\n")
    List<ProductAttribute> getAllAttributeByProductTypeId();

    //批量更新
    int batchUpdate(@Param("attributeList") List<ProductAttribute> attributeList);

    //查询全部hotlineScore
    @Select("SELECT * FROM hotline_score")
    List<HotlineScore> listAll();

    //查询全部hotlineScore
    @Select("SELECT * FROM `hotline_score` WHERE city_id IS NULL")
    List<HotlineScore> listNoCityId();

    // 查询全部城市和id(根据已有城市)
    List<AreaDictionaries> listByCity(@Param("scoreList") List<HotlineScore> scoreList);

    // 更新城市的id
    int updateCityId(@Param("hotlineScoreList") List<HotlineScore> hotlineScoreList);

    //根据时间参数查询数据
    List<HotlineScore> listByYearAndHalf(@Param("year") int year ,@Param("halfYear") String halfYear);

    // 修改排名变化
    int updateRankChange(@Param("list") List<HotlineScore> list);
}
