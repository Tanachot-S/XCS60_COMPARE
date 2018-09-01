package com.xcs.compare.dao;

import java.util.List;

import com.xcs.compare.model.CompareMasStaff;




public interface CompareMasStaffDAO {
	
	
	public List<CompareMasStaff> getCompareMasStaffgetByKeyword(String Textsearch);
	
	
}
