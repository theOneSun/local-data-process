package com.sun.data.hotline.service;

import com.sun.data.hotline.domain.ProductAttribute;
import com.sun.data.hotline.mapper.HotlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 改变本地数据
 *
 * @author sunjian.
 */
@Service
public class LocalChangeData
{
    @Autowired
    private HotlineMapper hotlineMapper;

    //改变attribute中的value为cityId
    public int updateAttributeValue()
    {
        // 查询全部attribute
        List<ProductAttribute> attributeList = hotlineMapper.getAllAttributeByProductTypeId();
        int i = hotlineMapper.batchUpdate(attributeList);
        return i;
    }
}
