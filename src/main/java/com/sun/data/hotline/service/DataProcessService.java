package com.sun.data.hotline.service;

import com.sun.data.hotline.domain.AreaDictionaries;
import com.sun.data.hotline.domain.CommunicationSkill;
import com.sun.data.hotline.domain.GeneralInformation;
import com.sun.data.hotline.domain.HalfYear;
import com.sun.data.hotline.domain.HotlineScore;
import com.sun.data.hotline.domain.QuestionResponse;
import com.sun.data.hotline.domain.ReceptionPolite;
import com.sun.data.hotline.mapper.CityMapper;
import com.sun.data.hotline.mapper.HotlineMapper;
import com.sun.data.util.CommonUtils;
import javafx.stage.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunjian.
 */
@Service
public class DataProcessService
{
    private final HotlineMapper hotlineMapper;
    private final CityMapper cityMapper;
    private Map<String, ReceptionPolite> politeMap;
    private Map<String, CommunicationSkill> skillMap;
    private Map<String, QuestionResponse> responseMap;

    @Autowired
    public DataProcessService(HotlineMapper hotlineMapper, CityMapper cityMapper)
    {
        this.hotlineMapper = hotlineMapper;
        this.cityMapper = cityMapper;
    }

    /**
     * 插入hotlineScore
     *
     * @return
     */
    public int addHotlineScore()
    {
        /*
        1.查询2017上半年的数据
        2.对三个set进行重新封装为map(key为城市名)
        3.创建hotlineScore
         */
        List<GeneralInformation> generalList = hotlineMapper.getAllGeneral();

        List<ReceptionPolite> politeList = hotlineMapper.getAllPolite();
        List<QuestionResponse> responseList = hotlineMapper.getAllResponse();
        List<CommunicationSkill> skillList = hotlineMapper.getAllSkill();

        //将详细信息的数据放到map中
        this.politeMap = getReceptionPoliteMap(politeList);
        this.responseMap = getResponseMap(responseList);
        this.skillMap = getSkillMap(skillList);

        //转换后的hotlineScoreList
        List<HotlineScore> hotlineScoreList = transferHotline(generalList);

        //新增hotlineScore
        return hotlineMapper.batchInsertHotlineScore2(hotlineScoreList);
    }

    //匹配城市的id
    public int matchCityId()
    {
        /*
        1.查询所有数据
        2.查询城市id数据
        3.遍历设置
         */
        // 更新hotline_score城市名称
        cityMapper.updateCityFirstHalf();
        List<HotlineScore> hotlineScoreList = hotlineMapper.listAll();
        List<AreaDictionaries> areaList = hotlineMapper.listByCity(hotlineScoreList);
        Map<String, String> cityMap = new HashMap<>();
        areaList.forEach(area -> cityMap.put(area.getAreaName(), area.getId()));
        cityMap.put("吉林", "644");
        cityMap.put("石河子", "2656");

        hotlineScoreList.forEach(hotlineScore ->
                                 {
                                     String city = hotlineScore.getCity();
                                     hotlineScore.setCityId(cityMap.get(city));
                                 });

        return hotlineMapper.updateCityId(hotlineScoreList);
    }

    //匹配没有城市id的数据的城市的id(针对的是新增的数据)
    public int matchNewCityId()
    {
        /*
        1.查询hotlineScore中没有cityId的数据
        2.查询城市id数据
        3.遍历设置
         */

        List<HotlineScore> hotlineScoreList = hotlineMapper.listNoCityId();
        List<AreaDictionaries> areaList = hotlineMapper.listByCity(hotlineScoreList);
        Map<String, String> cityMap = new HashMap<>();
        areaList.forEach(area -> cityMap.put(area.getAreaName(), area.getId()));
        cityMap.put("吉林", "644");
        cityMap.put("石河子", "2656");

        hotlineScoreList.forEach(hotlineScore ->
        {
            String city = hotlineScore.getCity();
            hotlineScore.setCityId(cityMap.get(city));
        });

        return hotlineMapper.updateCityId(hotlineScoreList);
    }

    //计算排名变化
    public int wrapperRankChange()
    {
        /*
        1.查询所有下半年的
        2.查询上半年的数据封装成map
         */
//        List<HotlineScore> upList = hotlineMapper.listByYearAndHalf(2017, "上半年");
//        List<HotlineScore> downList = hotlineMapper.listByYearAndHalf(2018, "上半年");
        List<HotlineScore> upList = hotlineMapper.listByYearAndHalf(2018, "上半年");
        List<HotlineScore> downList = hotlineMapper.listByYearAndHalf(2018, "下半年");
        //把上半年的城市和排名封装成map
        Map<String, Integer> serviceLevelMap = new HashMap<>();
        Map<String, Integer> comprehensiveMap = new HashMap<>();
        Map<String, Integer> noRateMap = new HashMap<>();
        Map<String, Integer> connectionRateMap = new HashMap<>();

        upList.forEach(score ->
                       {
                           serviceLevelMap.put(score.getCityId(), score.getServiceLevelRank());
                           comprehensiveMap.put(score.getCityId(), score.getComprehensiveRank());
                           noRateMap.put(score.getCityId(),score.getNoRateRank());
                           connectionRateMap.put(score.getCityId(),score.getConnectionRateRank());
                       });

        //遍历下半年计算排名变化
        downList.forEach(score ->
                         {
                             String cityId = score.getCityId();
                             Integer oldServiceLevelRank = serviceLevelMap.get(cityId);
                             Integer newServiceLevelRank = score.getServiceLevelRank();
                             score.setServiceLevelChange(CommonUtils.getChange(oldServiceLevelRank,newServiceLevelRank));
                             // ------------------------------------------------------------------------------------------------
                             Integer oldComprehensiveRank = comprehensiveMap.get(cityId);
                             Integer newComprehensiveRank = score.getComprehensiveRank();
                             score.setComprehensiveChange(CommonUtils.getChange(oldComprehensiveRank,newComprehensiveRank));
                             // ------------------------------------------------------------------------------------------------
                             Integer oldNoRateRank = noRateMap.get(cityId);
                             Integer newNoRateRank = score.getNoRateRank();
                             score.setNoRateChange(CommonUtils.getChange(oldNoRateRank,newNoRateRank));
                             // ------------------------------------------------------------------------------------------------
                             Integer oldConnectionRateRank = connectionRateMap.get(cityId);
                             Integer newConnectionRateRank = score.getConnectionRateRank();
                             score.setConnectionRateChange(CommonUtils.getChange(oldConnectionRateRank,newConnectionRateRank));
                         });
        //todo 更新排名变化
        return hotlineMapper.updateRankChange(downList);
    }


    /**
     * 封装hotlineScore
     *
     * @param generalList
     * @return
     */
    private List<HotlineScore> transferHotline(List<GeneralInformation> generalList)
    {

        List<HotlineScore> hotlineScoreList = new ArrayList<>();
        HotlineScore hotlineScore;
        ReceptionPolite polite;
        QuestionResponse questionResponse;
        CommunicationSkill skill;
        String city;
        for (GeneralInformation general : generalList)
        {
            city = general.getCity();
            polite = politeMap.get(city);
            skill = skillMap.get(city);
            questionResponse = responseMap.get(city);
            hotlineScore = HotlineScore.builder()
                                       .id(CommonUtils.uuid())
                                       .cityId("")
                                       .city(city)
                                       .year(2017)
                                       .halfYear(HalfYear.first_half.getValue())
                                       .hundredIndexCount(general.getHundredIndexCount())
                                       .hundredIndexCountRank(general.getHundredIndexCountRank())
                                       .throughConnectionScore(general.getThroughConnectionScore())
                                       .serviceLevelScore(general.getScoreExcludeConnectRate())
                                       .serviceLevelRank(general.getServiceRanking())
                                       .serviceLevelChange("-")
                                       .noRateScore(general.getScoreExcludeConnectRate())
                                       .noRateRank(general.getServiceRanking())
                                       .noRateChange("-")
                                       .receptionEtiquette(general.getReceptionEtiquette())
                                       .communicationSkill(general.getCommunicationSkill())
                                       .solveStatus(general.getSolveStatus())
                                       .comprehensiveScore(general.getScoreIncludeConnectRate())
                                       .comprehensiveRank(general.getRankIncludeConnectRate())
                                       .comprehensiveChange("-")
                                       .connectionRate(general.getRate())
                                       .connectionRateRank(general.getRateRanking())
                                       .connectionRateChange("-")
                                       // 封装服务礼仪指标得分
                                       .greeting(polite.getGreeting())
                                       .selfIntroduction(polite.getSelfIntroduction())
                                       .askDemand(polite.getAskDemand())
                                       .mandarin(polite.getMandarin())
                                       .speedDistinct(polite.getSpeedDistinct())
                                       .querySorry(polite.getQuerySorry())
                                       .askDemandAgain(polite.getAskDemandAgain())
                                       .farewell(polite.getFarewell())
                                       .thanks(polite.getThanks())
                                       // 封装沟通技巧
                                       .patience(skill.getPatience())
                                       .askDetail(skill.getAskDetail())
                                       .expressionClear(skill.getExpressionClear())
                                       // 封装问题回应
                                       .attitude(questionResponse.getAttitude())
                                       .noShuffle(questionResponse.getNoShuffle())
                                       .fastFindProblem(questionResponse.getFastFindProblem())
                                       .explainClearProcess(questionResponse.getExplainClearProcess())
                                       .explainClearPolicy(questionResponse.getExplainClearPolicy())
                                       .build();

            hotlineScoreList.add(hotlineScore);
        }
        return hotlineScoreList;
    }

    /**
     * 封装接待礼仪的数据
     *
     * @param receptionPoliteList
     * @return
     */
    private Map<String, ReceptionPolite> getReceptionPoliteMap(List<ReceptionPolite> receptionPoliteList)
    {
        Map<String, ReceptionPolite> map = new HashMap<>();
        if (ObjectUtils.isEmpty(receptionPoliteList))
        {
            throw new RuntimeException("数据错误");
        }
        receptionPoliteList.forEach(receptionPolite -> map.put(receptionPolite.getCity(), receptionPolite));
        return map;
    }

    /**
     * 封装沟通技巧的数据
     *
     * @param skillList
     * @return
     */
    private Map<String, CommunicationSkill> getSkillMap(List<CommunicationSkill> skillList)
    {
        Map<String, CommunicationSkill> map = new HashMap<>();
        if (ObjectUtils.isEmpty(skillList))
        {
            throw new RuntimeException("数据错误");
        }
        skillList.forEach(skill -> map.put(skill.getCity(), skill));
        return map;
    }

    /**
     * 封装问题回应的数据
     *
     * @param questionResponseList
     * @return
     */
    private Map<String, QuestionResponse> getResponseMap(List<QuestionResponse> questionResponseList)
    {
        Map<String, QuestionResponse> map = new HashMap<>();
        if (ObjectUtils.isEmpty(questionResponseList))
        {
            throw new RuntimeException("数据错误");
        }
        questionResponseList.forEach(questionResponse -> map.put(questionResponse.getCity(), questionResponse));
        return map;
    }

}
