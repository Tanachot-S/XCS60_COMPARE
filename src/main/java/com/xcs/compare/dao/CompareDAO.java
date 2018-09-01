package com.xcs.compare.dao;

import java.util.List;

import com.xcs.compare.mapparam.CompareParam;
import com.xcs.compare.mapparam.ComparegetByConAdvParam;
import com.xcs.compare.mapparam.ComparegetByConParam;
import com.xcs.compare.mapparam.CompareupdByConParam;
import com.xcs.compare.model.Compare;





public interface CompareDAO {
	
	
	public Boolean saveCompareinsAll(CompareParam param);
	public Boolean updateCompareupdByCon(CompareupdByConParam param);
	public Boolean deleteCompareupdDelete(String compareId);
	public List<Compare> getComparegetByKeyword(String Textsearch);
	public List<Compare> getComparegetByConAdv(ComparegetByConAdvParam param);
	public List<Compare> getComparegetByCon(ComparegetByConParam param);
	
	
	
}
