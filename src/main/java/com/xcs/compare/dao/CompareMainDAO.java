package com.xcs.compare.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.xcs.compare.constant.Pattern;
import com.xcs.compare.controller.CompareDetailRestController;
import com.xcs.compare.model.CompareDetailFine;
import com.xcs.compare.model.CompareDetailReceipt;

public class CompareMainDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMainDAO.class);
	
	//====================== Method Sub DataList CompareDetail ===========================

		protected List<CompareDetailFine> getCompareDetailFine(int compareDetailId,String compareFineId,JdbcTemplate jdbcTemplate){
			
			StringBuilder sqlBuilderDetail = new StringBuilder()
		    .append("select ")
		    .append("\"CompareFineID\", "
		    	    + "\"CompareDetailID\", "
		    	    + "\"ProductID\", "
		    	    + "\"ProductFine\", "
		    	    + "\"VatValue\", "
		    	    + "\"FineRate\", "
		    	    + "\"IsActive\" ")
		    .append("from \"ops_compare_detail_fine\" where \"CompareDetailID\" = '"+compareDetailId+"' and \"IsActive\" = 1 ");
			
			if(compareFineId != null && !"".equals(compareFineId)){
				sqlBuilderDetail.append("and \"CompareFineID\" = '"+compareFineId+"'");
			}
	    	
			log.info("[SQL]  : "+sqlBuilderDetail.toString());
			
	    	@SuppressWarnings("unchecked")
			List<CompareDetailFine> dataList = jdbcTemplate.query(sqlBuilderDetail.toString(), new RowMapper() {

			                public CompareDetailFine mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareDetailFine item = new CompareDetailFine();
			                	item.setCompareFineID(rs.getInt("CompareFineID"));
			                	item.setCompareDetailID(rs.getInt("CompareDetailID"));
			                	item.setProductID(rs.getInt("ProductID"));
			                	item.setProductFine(rs.getInt("ProductFine"));
			                	item.setVatValue(rs.getInt("VatValue"));
			                	item.setFineRate(rs.getInt("FineRate"));
			                	item.setIsActive(rs.getInt("IsActive"));
								return item;
			                }
			});
	    	
	    	
	    	return dataList;
			
		}
		
		protected List<CompareDetailReceipt> getCompareDetailReceipt(int compareDetailId ,String receiptId,String receiptFineType,JdbcTemplate jdbcTemplate){
			
			StringBuilder sqlBuilderDetail = new StringBuilder()
		    .append("select ")
		    .append("\"CompareReceiptID\", "
		    		+ "\"ReceiptType\", "
		    		+ "\"ReceiptBookNo\", "
		    		+ "\"ReceiptNo\", "
		    		+ "to_char(\"ReceiptDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as ReceiptDate,"
		    		+ "\"StationCode\", "
		    		+ "\"Station\", "
		    		+ "\"CompareDetailID\", "
		    		+ "to_char(\"PaymentDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as PaymentDate,"
		    		+ "\"TotalFine\", "
		    		+ "\"RevernueStatus\", "
		    		+ "to_char(\"RevernueDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as RevernueDate,"
		    		+ "\"ReceiptChanel\", "
		    		+ "\"ReferenceNo\", "
		    		+ "\"CompareAuthority\", "
		    		+ "\"FineType\", "
		    		+ "\"IsActive\" ")
		    .append("from \"ops_compare_detail_receipt\" where \"CompareDetailID\" = '"+compareDetailId+"' and \"IsActive\" = 1 ");
			
			if(receiptId != null && !"".equals(receiptId)){
				sqlBuilderDetail.append("and \"CompareReceiptID\" = '"+receiptId+"'");
			}
			
			if(receiptFineType != null && !"".equals(receiptFineType)){
				sqlBuilderDetail.append("and \"FineType\" = '"+receiptFineType+"'");
			}
			
			log.info("[SQL]  : "+sqlBuilderDetail.toString());
	    	
	    	@SuppressWarnings("unchecked")
			List<CompareDetailReceipt> dataList = jdbcTemplate.query(sqlBuilderDetail.toString(), new RowMapper() {

			                public CompareDetailReceipt mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareDetailReceipt item = new CompareDetailReceipt();
			                	item.setCompareReceiptID(rs.getInt("CompareReceiptID"));
			                	item.setReceiptType(rs.getString("ReceiptType"));
			                	item.setReceiptBookNo(rs.getString("ReceiptBookNo"));
			                	item.setReceiptNo(rs.getString("ReceiptNo"));
			                	item.setReceiptDate(rs.getString("ReceiptDate"));
			                	item.setStationCode(rs.getString("StationCode"));
			                	item.setStation(rs.getString("Station"));
			                	item.setCompareDetailID(rs.getInt("CompareDetailID"));
			                	item.setPaymentDate(rs.getString("PaymentDate"));
			                	item.setTotalFine(rs.getInt("TotalFine"));
			                	item.setRevernueStatus(rs.getInt("RevernueStatus"));
			                	item.setRevernueDate(rs.getString("RevernueDate"));
			                	item.setReceiptChanel(rs.getInt("ReceiptChanel"));
			                	item.setReferenceNo(rs.getString("ReferenceNo"));
			                	item.setCompareAuthority(rs.getInt("CompareAuthority"));
			                	item.setFineType(rs.getInt("FineType"));			                	
			                	item.setIsActive(rs.getInt("IsActive"));
								return item;
			                }
			});
	    	
	    	
	    	return dataList;
			
		}
		
		protected String getSequences(JdbcTemplate jdbcTemplate,String strSql) {
			log.info("[SQL]  : "+strSql);
			return jdbcTemplate.queryForObject(strSql, String.class);
		}


}
