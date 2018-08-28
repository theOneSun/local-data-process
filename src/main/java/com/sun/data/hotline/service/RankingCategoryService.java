package com.sun.data.hotline.service;

import com.sun.data.hotline.mapper.DateTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunjian.
 */
@Service
public class RankingCategoryService {

    private final DateTestMapper mapper;

    @Autowired
    public RankingCategoryService(DateTestMapper mapper) {this.mapper = mapper;}


}
