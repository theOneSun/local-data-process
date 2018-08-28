package com.sun.data.hotline.service;

import com.sun.data.hotline.domain.HalfYear;
import com.sun.data.hotline.domain.HotlineScore;
import com.sun.data.hotline.domain.Ranking;
import com.sun.data.hotline.mapper.HotlineMapper;
import com.sun.data.hotline.mapper.RankingMapper;
import com.sun.data.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 榜单数据处理
 *
 * @author sunjian.
 */
@Service
public class RankingProcess {
    private final RankingMapper rankMapper;
    private final HotlineMapper hotlineMapper;

    //礼仪帮id
    private final String PILOT_CATEGORY_ID = "d0c5f9902a23444bb7ea7230b995b242";
    //技巧榜id
    private final String SKILL_CATEGORY_ID = "1b23bdf9a01c446aa63fa3458e8fe1a6";
    //响应榜id
    private final String SOLVE_CATEGORY_ID = "06641e28a71248c0a40ce3183dc89f38";

    @Autowired
    public RankingProcess(RankingMapper rankMapper, HotlineMapper hotlineMapper) {
        this.rankMapper = rankMapper;
        this.hotlineMapper = hotlineMapper;
    }

    //todo 查询新增的数据,排名榜单中新增数据,新增后在进行变化计算
    public void insertData(int year, HalfYear halfYear) {
        /*
        1.排序查询礼仪数据
        2.排序查询技巧数据
        3.排序查询回应数据
         */
        List<Ranking> insertList = new ArrayList<>();
        // 礼仪榜
        final List<HotlineScore> pilotList = hotlineMapper.listPilotByYearAndHalf(year, halfYear.getValue());
        addRankingToList(insertList, pilotList, year, halfYear, PILOT_CATEGORY_ID);

        //技巧榜
        final List<HotlineScore> skillList = hotlineMapper.listSkillYearAndHalf(year, halfYear.getValue());
        addRankingToList(insertList, skillList, year, halfYear, SKILL_CATEGORY_ID);

        //回应榜
        final List<HotlineScore> responseList = hotlineMapper.listSolveResponseByYearAndHalf(year, halfYear.getValue());
        addRankingToList(insertList, responseList, year, halfYear, SOLVE_CATEGORY_ID);

        //插入数据
        if (insertList.size() > 0) {
            final int batchInsert = rankMapper.batchInsert(insertList);
            System.out.println("共插入数据" + batchInsert + "条");
        }
        else {
            System.out.println("无可插入数据");
        }


    }

    private void addRankingToList(List<Ranking> insertList, List<HotlineScore> dataList, int year, HalfYear halfYear, String categoryId) {
        if (dataList != null && dataList.size() > 0) {
            for (int i = 1; i <= dataList.size(); i++) {
                final HotlineScore hotlineScore = dataList.get(i - 1);
                final Ranking ranking = Ranking.builder()
                        .id(CommonUtils.uuid())
                        .categoryId(categoryId)
                        .city(hotlineScore.getCity())
                        .cityId(hotlineScore.getCityId())
                        .year(year)
                        .halfYear(halfYear.getValue())
                        .rank(i)
                        .rankChange("")
                        .build();
                insertList.add(ranking);
            }
        }
        else {
            System.out.println("NO DATA");
        }
    }

    // 计算封装礼仪榜的排名变化
    public void setPoliteChange() {
        /*
        1.查询2017上半年的数据
        2.封装map
        3.查询2018上半年的数据
        4.对比数据进行封装
        5.更新数据库
         */
//        List<Ranking> oldList = rankMapper.listPolite(2017, "上半年");
        List<Ranking> oldList = rankMapper.listPolite(2018, "上半年");
        Map<String, Integer> oldMap = new HashMap<>();
        oldList.forEach(rank -> oldMap.put(rank.getCityId(), rank.getRank()));

//        List<Ranking> newList = rankMapper.listPolite(2018, "上半年");
        List<Ranking> newList = rankMapper.listPolite(2018, "下半年");
        newList.forEach(rank ->
        {
            // 之前的排名
            Integer oldRank = oldMap.get(rank.getCityId());
            rank.setRankChange(CommonUtils.getChange(oldRank, rank.getRank()));
        });

        int i = rankMapper.batchUpdateChange(newList);
        System.out.println("更新了礼仪榜数据" + i + "条记录");
    }

    // 计算封装技巧榜的排名变化
    public void setSkillChange() {
        /*
        1.查询2017上半年的数据
        2.封装map
        3.查询2018上半年的数据
        4.对比数据进行封装
        5.更新数据库
         */
//        List<Ranking> oldList = rankMapper.listSkill(2017, "上半年");
        List<Ranking> oldList = rankMapper.listSkill(2018, "上半年");
        Map<String, Integer> oldMap = new HashMap<>();
        oldList.forEach(rank -> oldMap.put(rank.getCityId(), rank.getRank()));

//        List<Ranking> newList = rankMapper.listSkill(2018, "上半年");
        List<Ranking> newList = rankMapper.listSkill(2018, "下半年");
        newList.forEach(rank ->
        {
            // 之前的排名
            Integer oldRank = oldMap.get(rank.getCityId());
            rank.setRankChange(CommonUtils.getChange(oldRank, rank.getRank()));
        });

        int i = rankMapper.batchUpdateChange(newList);
        System.out.println("更新了技巧榜" + i + "条记录");
    }

    // 计算封装回应榜的排名变化
    public void setResponseChange() {
        /*
        1.查询2017上半年的数据
        2.封装map
        3.查询2018上半年的数据
        4.对比数据进行封装
        5.更新数据库
         */
//        List<Ranking> oldList = rankMapper.listResponse(2017, "上半年");
        List<Ranking> oldList = rankMapper.listResponse(2018, "上半年");
        Map<String, Integer> oldMap = new HashMap<>();
        oldList.forEach(rank -> oldMap.put(rank.getCityId(), rank.getRank()));

//        List<Ranking> newList = rankMapper.listResponse(2018, "上半年");
        List<Ranking> newList = rankMapper.listResponse(2018, "下半年");
        newList.forEach(rank ->
        {
            // 之前的排名
            Integer oldRank = oldMap.get(rank.getCityId());
            rank.setRankChange(CommonUtils.getChange(oldRank, rank.getRank()));
        });

        int i = rankMapper.batchUpdateChange(newList);
        System.out.println("更新了回应榜" + i + "条记录");
    }

    // 插入接通率榜单数据
    public void insertConnectionRateRanking(Integer year, HalfYear halfYear) {
        List<Ranking> insertList = new ArrayList<>();
        List<HotlineScore> hotlineScoreList = rankMapper.listConnectionRateList(year, halfYear.getValue());
        hotlineScoreList.forEach(hotlineScore -> {

            insertList.add(Ranking.builder()
                    .id(CommonUtils.uuid())
                    .city(hotlineScore.getCity())
                    .cityId(hotlineScore.getCityId())
                    .categoryId("b3f046f8235946e0ba7c80fdffd201de")
                    .year(year)
                    .halfYear(halfYear.getValue())
                    .rank(hotlineScore.getConnectionRateRank())
                    .rankChange(hotlineScore.getConnectionRateChange())
                    .build());
        });

        int batchInsert = rankMapper.batchInsert(insertList);
        System.out.println("插入了" + batchInsert + "条");
    }

    //插入飙升榜
    public void setIncrease(Integer year, HalfYear halfYear) {
        /*
        1.查询上升的
        2.处理变化的数据
         */
        List<HotlineScore> hotlineScoreList = rankMapper.listComprehensiveIncrease(year, halfYear.getValue());

        List<Ranking> insertList = new ArrayList<>();
        if (hotlineScoreList != null && hotlineScoreList.size() > 0) {
            for (int i = 1; i <= hotlineScoreList.size(); i++) {
                final HotlineScore hotlineScore = hotlineScoreList.get(i - 1);
                final Ranking ranking = Ranking.builder()
                        .id(CommonUtils.uuid())
                        .cityId(hotlineScore.getCityId())
                        .city(hotlineScore.getCity())
                        .year(year)
                        .rank(i)
                        .rankChange("+" + hotlineScore.getComprehensiveRank())
                        .halfYear(halfYear.getValue())
                        .categoryId("c2941f663f014c4781c6ab6355e7448d")
                        .build();
                insertList.add(ranking);
            }
            final int batchInsert = rankMapper.batchInsert(insertList);
            System.out.println("插入了" + batchInsert + "条");
        }
        else {
            System.out.println("查无数据");
        }
    }

}
