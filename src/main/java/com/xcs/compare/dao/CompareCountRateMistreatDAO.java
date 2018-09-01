package com.xcs.compare.dao;

import java.util.List;

import com.xcs.compare.mapparam.CompareCountRateMistreatgetByConParam;
import com.xcs.compare.model.CompareCountRateMistreat;





public interface CompareCountRateMistreatDAO {
	
	public List<CompareCountRateMistreat> getCompareCountRateMistreatgetByCon(CompareCountRateMistreatgetByConParam param);
	
	
	
}
