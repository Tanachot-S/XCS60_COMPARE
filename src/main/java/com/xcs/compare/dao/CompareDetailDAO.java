package com.xcs.compare.dao;

import com.xcs.compare.mapparam.CompareDetailParam;
import com.xcs.compare.mapparam.CompareDetailReceiptParam;
import com.xcs.compare.model.CompareDetailinsAllResponse;





public interface CompareDetailDAO {
	
	
	public CompareDetailinsAllResponse saveCompareStaffInsAll(CompareDetailParam param);
	public Boolean updateCompareDetailupdByCon(CompareDetailParam param);
	public Boolean saveCompareDetailReceiptinsAll(CompareDetailReceiptParam param);
	public Boolean updateCompareDetailReceiptupdByCon(CompareDetailReceiptParam param);
	public Boolean deleteCompareDetailReceiptupdDelete(String compareReceiptId);
	
	
}
