package com.xcs.compare.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcs.compare.constant.Message;
import com.xcs.compare.constant.Pattern;
import com.xcs.compare.controller.CompareDetailRestController;
import com.xcs.compare.mapparam.CompareDetailParam;
import com.xcs.compare.mapparam.CompareDetailReceiptParam;
import com.xcs.compare.model.CompareDetail;
import com.xcs.compare.model.CompareDetailFine;
import com.xcs.compare.model.CompareDetailinsAllResponse;

@Service
@Transactional
public class CompareDetailDAOImpl extends CompareMainDAO implements CompareDetailDAO {

	
	private static final Logger log = LoggerFactory.getLogger(CompareDetailDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public CompareDetailinsAllResponse saveCompareStaffInsAll(CompareDetailParam param) {
		
		CompareDetailinsAllResponse res = new CompareDetailinsAllResponse();
		
		
		String compareDetailIdSql = getSequences(jdbcTemplate, "SELECT \"ops_compare_detail_SEQ\".NEXTVAL FROM DUAL");
		
		
		
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("INSERT INTO \"ILLEGAL60\".\"ops_compare_detail\" ")
	    .append("(\"CompareDetailID\", "
	    		+ "\"CompareID\", "
	    		+ "\"IndictmentDetailID\","
	    		+ "\"CompareAction\", "
	    		+ "\"LawbrakerTestimony\", "
	    		+ "\"Fact\", "
	    		+ "\"IsRequest\", "
	    		+ "\"RequestForAction\","
	    		+ "\"CompareReason\", "
	    		+ "\"IsProvisionalAcquittal\", "
	    		+ "\"Bail\", "
	    		+ "\"Guaruntee\", "
	    		+ "\"CompareFine\", "
	    		+ "\"PaymentFineDate\", "
	    		+ "\"PaymentFineAppointDate\", "
	    		+ "\"PaymentVatDate\", "
	    		+ "\"TreasuryMoney\", "
	    		+ "\"BribeMoney\", "
	    		+ "\"RewardMoney\", "
	    		+ "\"IsActive\", "
	    		+ "\"ApproveStationCode\", "
	    		+ "\"ApproveStation\", "
	    		+ "\"ApproveReportDate\", "
	    		+ "\"CommandNo\", "
	    		+ "\"CommandDate\", "
	    		+ "\"CompareAuthority\", "
	    		+ "\"ApproveReportType\", "
	    		+ "\"MistreatNo\", "
	    		+ "\"FineType\", "
	    		+ "\"AdjustReason\") ")
	    .append("VALUES ("
	    		//+ "'"+param.getCompareDetailID()+"', "
	    		+ "'"+compareDetailIdSql+"', "
	    		+ "'"+param.getCompareID()+"', "
	    		+ "'"+param.getIndictmentDetailID()+"', "
	    		+ "'"+param.getCompareAction()+"', "
	    		+ "'"+param.getLawbrakerTestimony()+"', "
	    		+ "'"+param.getFact()+"', "
	    		+ "'"+param.getIsRequest()+"', "
	    		+ "'"+param.getRequestForAction()+"', "
	    		+ "'"+param.getCompareReason()+"', "
	    		+ "'"+param.getIsProvisionalAcquittal()+"', "
	    		+ "'"+param.getBail()+"', "
	    		+ "'"+param.getGuaruntee()+"', "
	    		+ "'"+param.getCompareFine()+"', "
	    		+ "TO_TIMESTAMP_TZ('"+param.getPaymentFineDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "TO_TIMESTAMP_TZ('"+param.getPaymentFineAppointDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "TO_TIMESTAMP_TZ('"+param.getPaymentVatDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "'"+param.getTreasuryMoney()+"', "
	    		+ "'"+param.getBribeMoney()+"', "
	    		+ "'"+param.getRewardMoney()+"', "
	    		+ "'1', "
	    		+ "'"+param.getApproveStationCode()+"', "
	    		+ "'"+param.getApproveStation()+"', "
	    		+ "TO_TIMESTAMP_TZ('"+param.getApproveReportDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "'"+param.getCommandNo()+"', "
	    		+ "TO_TIMESTAMP_TZ('"+param.getCommandDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "'"+param.getCompareAuthority()+"', "
	    		+ "'"+param.getApproveReportType()+"', "
	    		+ "'"+param.getMistreatNo()+"', "
	    		+ "'"+param.getFineType()+"', "
	    		+ "'"+param.getAdjustReason()+"')");
		log.info("[SQL] Main : "+sqlBuilder.toString());
		log.info("[Sub] Size : "+param.getCompareDetailFine().size());
	    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
	    
	    if(param.getCompareDetailFine().size() > 0){
	    	for(CompareDetailFine item : param.getCompareDetailFine()){
	    		
	    		StringBuilder sqlBuilderSub = new StringBuilder()
	    	    .append("INSERT INTO \"ILLEGAL60\".\"ops_compare_detail_fine\" ")
	    	    .append("("
	    	    		+ "\"CompareFineID\", "
	    	    		+ "\"CompareDetailID\", "
	    	    		+ "\"ProductID\", "
	    	    		+ "\"ProductFine\", "
	    	    		+ "\"VatValue\", "
	    	    		+ "\"FineRate\", "
	    	    		+ "\"IsActive\") ")
	    	    .append("VALUES ("
	    	    		//+ "'"+item.getCompareFineID()+"', "
	    	    		//+ "'"+item.getCompareDetailID()+"', "
	    	    		+ "\"ops_compare_fine_SEQ\".NEXTVAL, "
	    	    		+ "'"+compareDetailIdSql+"', "
	    	    		+ "'"+item.getProductID()+"', "
	    	    		+ "'"+item.getProductFine()+"', "
	    	    		+ "'"+item.getVatValue()+"', "
	    	    		+ "'"+item.getFineRate()+"', "
	    	    		+ "'1')");
	    		log.info("[SQL] Sub : "+sqlBuilderSub.toString());
	    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
	    	}
	    }
	    
	    
	    res.setIsSuccess(Message.TRUE);
	    res.setMsg(Message.COMPLETE);
	    res.setResponseData(getCompareDetail(compareDetailIdSql));
		
		return res;
	}

	@Override
	public Boolean updateCompareDetailupdByCon(CompareDetailParam param) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE \"ILLEGAL60\".\"ops_compare_detail\" "
				+ "SET \"CompareID\" = '"+param.getCompareID()+"' , "
				+ "\"IndictmentDetailID\" = '"+param.getIndictmentDetailID()+"', "
				+ "\"CompareAction\" = '"+param.getCompareAction()+"', "
				+ "\"LawbrakerTestimony\" = '"+param.getLawbrakerTestimony()+"', "
				+ "\"Fact\" = '"+param.getFact()+"' , "
				+ "\"IsRequest\" = '"+param.getIsRequest()+"', "
				+ "\"RequestForAction\" = '"+param.getRequestForAction()+"', "
				+ "\"CompareReason\" = '"+param.getCompareReason()+"', "
				+ "\"IsProvisionalAcquittal\" = '"+param.getIsProvisionalAcquittal()+"', "
				+ "\"Bail\" = '"+param.getBail()+"', "
				+ "\"Guaruntee\" = '"+param.getGuaruntee()+"', "
				+ "\"CompareFine\" = '"+param.getCompareFine()+"', "
				+ "\"PaymentFineDate\" = TO_TIMESTAMP_TZ('"+param.getPaymentFineDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"PaymentFineAppointDate\" = TO_TIMESTAMP_TZ('"+param.getPaymentFineAppointDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"PaymentVatDate\" = TO_TIMESTAMP_TZ('"+param.getPaymentVatDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"TreasuryMoney\" = '"+param.getTreasuryMoney()+"', "
				+ "\"BribeMoney\" = '"+param.getBribeMoney()+"', "
				+ "\"RewardMoney\" = '"+param.getRewardMoney()+"', "
				+ "\"IsActive\" = '1', "
				+ "\"ApproveStationCode\" = '"+param.getApproveStationCode()+"', "
				+ "\"ApproveStation\" = '"+param.getApproveStation()+"', "
				+ "\"ApproveReportDate\" = TO_TIMESTAMP_TZ('"+param.getApproveReportDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"CommandNo\" = '"+param.getCommandNo()+"', "
				+ "\"CommandDate\" = TO_TIMESTAMP_TZ('"+param.getCommandDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"CompareAuthority\" = '"+param.getCompareAuthority()+"', "
				+ "\"ApproveReportType\" = '"+param.getApproveReportType()+"', "				
				+ "\"MistreatNo\" = '"+param.getMistreatNo()+"', "				
				+ "\"FineType\" = '"+param.getFineType()+"', "				
				+ "\"AdjustReason\" = '"+param.getAdjustReason()+"' "
				+ "WHERE \"CompareDetailID\" = '"+param.getCompareDetailID()+"' ");
		

		log.info("[SQL] Main: "+sqlBuilder.toString());
	    getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		
		 if(param.getCompareDetailFine().size() > 0){
		    	for(CompareDetailFine item : param.getCompareDetailFine()){
		    		
		    		StringBuilder sqlBuilderSub = new StringBuilder()
		    		.append("UPDATE \"ILLEGAL60\".\"ops_compare_detail_fine\" "
		    				+ "SET \"CompareDetailID\" = '"+item.getCompareDetailID()+"', "
		    				+ "\"ProductID\"  = '"+item.getProductID()+"', "
		    				+ "\"ProductFine\"  = '"+item.getProductFine()+"', "
		    				+ "\"VatValue\"  = '"+item.getVatValue()+"', "
		    				+ "\"FineRate\"  = '"+item.getFineRate()+"', "
		    				+ "\"IsActive\"  = '1' "
		    				+ "WHERE \"CompareFineID\" = '"+item.getCompareFineID()+"' ");
		    		log.info("[SQL] Sub : "+sqlBuilderSub.toString());
		    		getJdbcTemplate().update(sqlBuilderSub.toString(), new Object[] {});
		    	}
		    }
		
			return true;
	}

	@Override
	public Boolean saveCompareDetailReceiptinsAll(CompareDetailReceiptParam param) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("INSERT INTO \"ILLEGAL60\".\"ops_compare_detail_receipt\" ")
	    .append("(\"CompareReceiptID\", "
	    		+ "\"ReceiptType\", "
	    		+ "\"ReceiptBookNo\", "
	    		+ "\"ReceiptNo\", "
	    		+ "\"ReceiptDate\", "
	    		+ "\"StationCode\", "
	    		+ "\"Station\", "
	    		+ "\"CompareDetailID\", "
	    		+ "\"PaymentDate\", "
	    		+ "\"TotalFine\", "
	    		+ "\"RevernueStatus\", "
	    		+ "\"RevernueDate\", "
	    		+ "\"ReceiptChanel\", "
	    		+ "\"ReferenceNo\", "
	    		+ "\"CompareAuthority\", "
	    		+ "\"FineType\", "
	    		+ "\"IsActive\") ")
	    .append("VALUES ("
	    		//+ "'"+param.getCompareReceiptID()+"', "
	    		+ "\"ops_compare_detail_receipt_SEQ\".NEXTVAL, "
	    		+ "'"+param.getReceiptType()+"', "
	    		+ "'"+param.getReceiptBookNo()+"', "
	    		+ "'"+param.getReceiptNo()+"', "
	    		+ "TO_TIMESTAMP_TZ('"+param.getReceiptDate()+"','"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "'"+param.getStationCode()+"', "
	    		+ "'"+param.getStation()+"', "
	    		+ "'"+param.getCompareDetailID()+"', "
	    		+ "TO_TIMESTAMP_TZ('"+param.getPaymentDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "'"+param.getTotalFine()+"', "
	    		+ "'"+param.getRevernueStatus()+"', "
	    		+ "TO_TIMESTAMP_TZ('"+param.getRevernueDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "'"+param.getReceiptChanel()+"', "
	    		+ "'"+param.getReferenceNo()+"', "
	    		+ "'"+param.getCompareAuthority()+"', "
	    		+ "'"+param.getFineType()+"', "
	    		+ "'1')");
		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}

	@Override
	public Boolean updateCompareDetailReceiptupdByCon(CompareDetailReceiptParam param) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE \"ILLEGAL60\".\"ops_compare_detail_receipt\" "
				+ "SET \"ReceiptType\" = '"+param.getReceiptType()+"', "
				+ "\"ReceiptBookNo\" = '"+param.getReceiptBookNo()+"', "
				+ "\"ReceiptNo\" = '"+param.getReceiptNo()+"', "
				+ "\"ReceiptDate\" = TO_TIMESTAMP_TZ('"+param.getReceiptDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"StationCode\" = '"+param.getStationCode()+"', "
				+ "\"Station\" = '"+param.getStation()+"', "
				+ "\"CompareDetailID\" = '"+param.getCompareDetailID()+"', "
				+ "\"PaymentDate\" = TO_TIMESTAMP_TZ('"+param.getPaymentDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"TotalFine\" = '"+param.getTotalFine()+"', "
				+ "\"RevernueStatus\" = '"+param.getRevernueStatus()+"', "
				+ "\"RevernueDate\" = TO_TIMESTAMP_TZ('"+param.getRevernueDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"ReceiptChanel\" = '"+param.getReceiptChanel()+"', "
				+ "\"ReferenceNo\" = '"+param.getReferenceNo()+"', "
				+ "\"CompareAuthority\" = '"+param.getCompareAuthority()+"', "
				+ "\"FineType\" = '"+param.getFineType()+"', "
				+ "\"IsActive\" = '1' "
				+ "WHERE \"CompareReceiptID\" = '"+param.getCompareReceiptID()+"'");

		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}
	
	@Override
	public Boolean deleteCompareDetailReceiptupdDelete(String compareReceiptId) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE \"ILLEGAL60\".\"ops_compare_detail_receipt\" SET \"IsActive\" = 0 WHERE \"CompareReceiptID\" = '"+compareReceiptId+"' ");

		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}

	//====================
	
	private CompareDetail getCompareDetail(String compareDetailId){
		
		StringBuilder sqlBuilderDetail = new StringBuilder()
	    .append("select ")
	    .append("\"CompareDetailID\", "
	    	    + "\"CompareID\", "
	    	    + "\"IndictmentDetailID\","
	    	    + "\"CompareAction\", "
	    	    + "\"LawbrakerTestimony\", "
	    	    + "\"Fact\", "
	    	  	+ "\"IsRequest\", "
	    	    + "\"RequestForAction\","
	    	    + "\"CompareReason\", "
	    	    + "\"IsProvisionalAcquittal\", "
	    	    + "\"Bail\", "
	    	    + "\"Guaruntee\", "
	    	    + "\"CompareFine\", "
	    	    + "to_char(\"PaymentFineDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as PaymentFineDate, "
	    	    + "to_char(\"PaymentFineAppointDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as PaymentFineAppointDate, "
	    	    + "to_char(\"PaymentVatDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as PaymentVatDate, "
	    	    + "\"TreasuryMoney\", "
	    	    + "\"BribeMoney\", "
	    	    + "\"RewardMoney\", "
	    	    + "\"IsActive\", "
	    	    + "\"ApproveStationCode\", "
	    	    + "\"ApproveStation\", "
	    	    + "to_char(\"ApproveReportDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as ApproveReportDate, "
	    	    + "\"CommandNo\", "
	    	    + "to_char(\"CommandDate\",'"+Pattern.FORMAT_DATETIME+"') as CommandDate, "
	    	    + "\"CompareAuthority\", "
	    	    + "\"ApproveReportType\", "
	    	    + "\"MistreatNo\", "
	    	    + "\"FineType\", "
	    	    + "\"AdjustReason\" ")
	    .append("from \"ops_compare_detail\" where \"CompareDetailID\" = '"+compareDetailId+"' and \"IsActive\" = 1 ");
		
		
		log.info("[SQL] : "+sqlBuilderDetail.toString());
		
		return getJdbcTemplate().query(sqlBuilderDetail.toString(), new ResultSetExtractor<CompareDetail>() {
			
			public CompareDetail extractData(ResultSet rs) throws SQLException,DataAccessException {
				if (rs.next()) {
                	CompareDetail item = new CompareDetail();
                	item.setCompareDetailID(rs.getInt("CompareDetailID"));
                	item.setCompareID(rs.getInt("CompareID"));
                	item.setIndictmentDetailID(rs.getInt("IndictmentDetailID"));
                	item.setCompareAction(rs.getString("CompareAction"));
                	item.setLawbrakerTestimony(rs.getString("LawbrakerTestimony"));
                	item.setFact(rs.getString("Fact"));
                	item.setIsRequest(rs.getInt("IsRequest"));
                	item.setRequestForAction(rs.getString("RequestForAction"));
                	item.setCompareReason(rs.getString("CompareReason"));
                	item.setIsProvisionalAcquittal(rs.getInt("IsProvisionalAcquittal"));
                	item.setBail(rs.getString("Bail"));
                	item.setGuaruntee(rs.getString("Guaruntee"));
                	item.setCompareFine(rs.getInt("CompareFine"));
                	item.setPaymentFineDate(rs.getString("PaymentFineDate"));
                	item.setPaymentFineAppointDate(rs.getString("PaymentFineAppointDate"));
                	item.setPaymentVatDate(rs.getString("PaymentVatDate"));
                	item.setTreasuryMoney(rs.getInt("TreasuryMoney"));
                	item.setBribeMoney(rs.getInt("BribeMoney"));
                	item.setRewardMoney(rs.getInt("RewardMoney"));
                	item.setIsActive(rs.getInt("IsActive"));
                	item.setApproveStationCode(rs.getString("ApproveStationCode"));
                	item.setApproveStation(rs.getString("ApproveStation"));
                	item.setApproveReportDate(rs.getString("ApproveReportDate"));
                	item.setCommandNo(rs.getString("CommandNo"));
                	item.setCommandDate(rs.getString("CommandDate"));
                	item.setCompareAuthority(rs.getInt("CompareAuthority"));
                	item.setApproveReportType(rs.getInt("ApproveReportType"));
                	item.setMistreatNo(rs.getInt("MistreatNo"));
                	item.setFineType(rs.getInt("FineType"));
                	item.setAdjustReason(rs.getString("AdjustReason"));
                	item.setCompareDetailFine(getCompareDetailFine(rs.getInt("CompareDetailID"),"",getJdbcTemplate()));
                	item.setCompareDetailReceipt(getCompareDetailReceipt(rs.getInt("CompareDetailID"),"","",getJdbcTemplate()));
					return item;
                }
				
				return null;
			}
			
		});
	
		
	}

	
	

	
	
	


	

}
