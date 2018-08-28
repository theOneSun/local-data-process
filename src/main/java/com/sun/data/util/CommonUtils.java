package com.sun.data.util;

import java.util.UUID;

/**
 * @author sunjian.
 */
public class CommonUtils
{
    /**
     * 生成uuid
     *
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 计算排名变化
     *
     * @param oldRank
     * @param newRank
     * @return
     */
    public static String getChange(Integer oldRank, Integer newRank)
    {
        if (oldRank == null || newRank == null)
        {
            return "-";
        } else
        {
            if (oldRank < newRank)
            {
                // 说明排名下降,应该使用加-
                return String.valueOf(oldRank - newRank);
            } else if (oldRank > newRank)
            {
                // 说明排名上升,应该使用+
                return "+" + (oldRank - newRank);
            } else
            {
                return "-";
            }
        }
    }

}
