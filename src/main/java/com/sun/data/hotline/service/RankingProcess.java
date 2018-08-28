package com.sun.data.hotline.service;

import com.sun.data.hotline.domain.HotlineScore;
import com.sun.data.hotline.domain.Ranking;
import com.sun.data.hotline.mapper.RankingMapper;
import com.sun.data.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;

/**
 * 榜单数据处理
 *
 * @author sunjian.
 */
@Service
public class RankingProcess {
    private final RankingMapper rankMapper;

    @Autowired
    public RankingProcess(RankingMapper rankMapper) {
        this.rankMapper = rankMapper;
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
        List<Ranking> oldList = rankMapper.listPolite(2017, "上半年");
        Map<String, Integer> oldMap = new HashMap<>();
        oldList.forEach(rank -> oldMap.put(rank.getCityId(), rank.getRank()));

        List<Ranking> newList = rankMapper.listPolite(2018, "上半年");
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
        List<Ranking> oldList = rankMapper.listSkill(2017, "上半年");
        Map<String, Integer> oldMap = new HashMap<>();
        oldList.forEach(rank -> oldMap.put(rank.getCityId(), rank.getRank()));

        List<Ranking> newList = rankMapper.listSkill(2018, "上半年");
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
        List<Ranking> oldList = rankMapper.listResponse(2017, "上半年");
        Map<String, Integer> oldMap = new HashMap<>();
        oldList.forEach(rank -> oldMap.put(rank.getCityId(), rank.getRank()));

        List<Ranking> newList = rankMapper.listResponse(2018, "上半年");
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
    public void insertConnectionRateRanking() {
        List<Ranking> insertList = new ArrayList<>();
        List<HotlineScore> hotlineScoreList = rankMapper.listConnectionRateList();
        hotlineScoreList.forEach(hotlineScore -> {

            insertList.add(Ranking.builder()
                    .id(CommonUtils.uuid())
                    .city(hotlineScore.getCity())
                    .cityId(hotlineScore.getCityId())
                    .categoryId("b3f046f8235946e0ba7c80fdffd201de")
                    .year(2018)
                    .halfYear("上半年")
                    .rank(hotlineScore.getConnectionRateRank())
                    .rankChange(hotlineScore.getConnectionRateChange())
                    .build());
        });

        int batchInsert = rankMapper.batchInsert(insertList);
        System.out.println("插入了" + batchInsert + "条");
    }

    //插入飙升榜
    public void setIncrease(){
        /*
        1.查询上升的
        2.处理变化的数据
         */
//        List<HotlineScore> hotlineScoreList = rankMapper.listComprehensiveIncrease(2018, "上半年");
//
//        List<Ranking> insertList = new ArrayList<>();
//        hotlineScoreList.forEach(hotlineScore -> {
//
//            Ranking.builder()
//                    .id(CommonUtils.uuid())
//                    .cityId(hotlineScore.getCityId())
//                    .city(hotlineScore.getCity())
//                    .year(2018)
//                    .rank()
//                    .halfYear("上半年")
//                    .build();
//        });
    }

}
