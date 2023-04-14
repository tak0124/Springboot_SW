package com.tukorea.faq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tukorea.faq.dao.FaqDao;
import com.tukorea.faq.domain.Faq;
import com.tukorea.faq.dto.FaqListDto;
@Service
public class FaqService {
	
	private FaqDao dao;
	@Autowired
	public FaqService(FaqDao dao) {
		this.dao = dao;
	}
	
	
	
	
	
	public Map<String,Object> getFaqList(FaqListDto faqListDto){
		Map<String,Object> resultMap = new HashMap<>();
		try {
			resultMap.put("pageNum", faqListDto.getPageNum());
			resultMap.put("searchCategory", faqListDto.getSearchCategory());
			resultMap.put("searchType", faqListDto.getSearchType());
			resultMap.put("searchKeyword", faqListDto.getSearchKeyword());
			//[1] FAQ 카테고리 목록 조회
			String[] categoryList = dao.selectFaqCategory();
			resultMap.put("categoryList", categoryList);
			
			System.out.println("[1] FAQ 카테고리 목록 조회");
			
			Map<String, Object> paramMap = new HashMap<>();
			
			paramMap.put("searchCategory", faqListDto.getSearchCategory());
			paramMap.put("searchType", faqListDto.getSearchType());
			paramMap.put("searchKeyword", faqListDto.getSearchKeyword());
			
			int listNum = 10;
			int pageNum = faqListDto.getPageNum();
			
			paramMap.put("listNum", listNum);
			paramMap.put("startNum",(pageNum - 1) * listNum);
			//[2] FAQ 목록 총 건수 조회
			int totalCount = dao.selectFaqListTotalCount(paramMap);
			resultMap.put("totalCount", totalCount);
			
			System.out.println("[2] FAQ 목록 총 건수 조회");
			//[3] FAQ 목록 조회
			List<Faq> faqList = dao.selectFaqList(paramMap);
			resultMap.put("faqList", faqList);
			
			System.out.println("[3] FAQ 목록 조회");
			//[4] FAQ 페이징 처리
			//[4-1] 페이징 계산용 변수 설정
			int pageUnitNum=5;
			//[4-2] 총 페이징 계산
			int totalPagingNum = (totalCount / listNum) + (totalCount % listNum == 0 ? 0 : 1);
			//[4-3] 배열 값 비교하여 페이징 시작 번호 return
			if (totalPagingNum==0) {
				resultMap.put("startUnitNum", 1);
				resultMap.put("endUnitNum", 1);
				resultMap.put("totalPagingNum", 1);
				
			}else {
				int totalPagingUnitNum=(totalPagingNum/ pageUnitNum) + (totalPagingNum % pageUnitNum == 0 ? 0 : 1);
				for (int i=0; i<totalPagingUnitNum; i++) {
					int startUnitNum = (i* pageUnitNum) +1;
					int endUnitNum = (i+1) * pageUnitNum;
					
					if (endUnitNum>totalPagingNum) {
						endUnitNum = totalPagingNum;
					}
					if(pageNum>= startUnitNum && pageNum<=endUnitNum) {
						resultMap.put("startUnitNum", startUnitNum);
						resultMap.put("endUnitNum", endUnitNum);
						resultMap.put("totalPagingNum", totalPagingNum);
						
						break;		
					}
				}
			}
			System.out.println("[4] FAQ 페이징 처리");
			
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultMap;
	}

}
