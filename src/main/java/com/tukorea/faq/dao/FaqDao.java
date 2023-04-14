package com.tukorea.faq.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tukorea.faq.domain.Faq;

@Mapper

public interface FaqDao {
	public String[] selectFaqCategory();
	
	public int selectFaqListTotalCount(Map<String, Object> paramMap);
	
	public List<Faq> selectFaqList(Map<String, Object> paramMap);

}
