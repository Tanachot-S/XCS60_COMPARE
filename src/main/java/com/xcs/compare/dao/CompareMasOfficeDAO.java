package com.xcs.compare.dao;

import java.util.List;

import com.xcs.compare.model.CompareMasOffice;




public interface CompareMasOfficeDAO {
	
		
	public List<CompareMasOffice> getCompareMasOfficegetByKeyword(String Textsearch);
	
	
}
