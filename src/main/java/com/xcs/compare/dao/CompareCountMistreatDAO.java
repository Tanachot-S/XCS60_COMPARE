package com.xcs.compare.dao;

import java.util.List;

import com.xcs.compare.mapparam.CompareCountMistreatgetByConParam;
import com.xcs.compare.model.CompareCountMistreat;





public interface CompareCountMistreatDAO {
	
	
	public List<CompareCountMistreat> getCompareCountMistreatgetByCon(CompareCountMistreatgetByConParam param);
	
	
}
