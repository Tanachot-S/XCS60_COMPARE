package com.xcs.compare.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcs.compare.constant.Pattern;
import com.xcs.compare.controller.CompareRestController;
import com.xcs.compare.mapparam.CompareParam;
import com.xcs.compare.mapparam.ComparegetByConAdvParam;
import com.xcs.compare.mapparam.ComparegetByConParam;
import com.xcs.compare.mapparam.CompareupdByConParam;
import com.xcs.compare.model.Compare;
import com.xcs.compare.model.CompareDetail;
import com.xcs.compare.model.CompareStaff;

@Service
@Transactional
public class CompareDAOImpl extends CompareMainDAO implements CompareDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public List<Compare> getComparegetByKeyword(String Textsearch) {
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("SELECT     DISTINCT  \"ops_arrest_indictment\".\"ArrestCode\","
	    		+ "\"ops_lawsuit\".\"LawsuitNo\","
	    		+ "\"ops_prove\".\"ProveReportNo\","
	    		+ "\"ops_compare\".\"CompareID\","
	    		+ "\"ops_compare\".\"CompareCode\","
	    		+ "to_char(\"ops_compare\".\"CompareDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as CompareDate,"
	    		+ "\"ops_compare\".\"CompareStationCode\","
	    		+ "\"ops_compare\".\"CompareStation\","
	    		+ "\"ops_compare\".\"CompareSubdistrictCode\","
	    		+ "\"ops_compare\".\"CompareSubdistrict\",    "
	    		+ "\"ops_compare\".\"CompareDistrictCode\",    "
	    		+ "\"ops_compare\".\"CompareDistrict\","
	    		+ "\"ops_compare\".\"CompareProvinceCode\","
	    		+ "\"ops_compare\".\"CompareProvince\","
	    		+ "\"ops_compare\".\"AccuserSubdistrictCode\","
	    		+ "\"ops_compare\".\"AccuserSubdistrict\","
	    		+ "\"ops_compare\".\"AccuserDistrictCode\","
	    		+ "\"ops_compare\".\"AccuserDistrict\","
	    		+ "\"ops_compare\".\"AccuserProvinceCode\","
	    		+ "\"ops_compare\".\"AccuserProvince\","
	    		+ "\"ops_compare\".\"IsOutside\","
	    		+ "\"ops_compare\".\"LawsuitID\","
	    		+ "\"ops_compare\".\"IsActive\"")
	    .append("FROM  ")
	    .append("\"ops_lawsuit\"  ")
	    .append("inner join \"ops_compare\" on \"ops_compare\".\"LawsuitID\" = \"ops_lawsuit\".\"LawsuitID\" ")
	    .append("left join \"ops_arrest_indictment\" on \"ops_arrest_indictment\".\"IndictmentID\" = \"ops_lawsuit\".\"IndictmentID\" ")
	    .append("left join \"ops_prove\" on \"ops_prove\".\"IndictmentID\" = \"ops_lawsuit\".\"IndictmentID\"")
	    .append("left join \"ops_compare_staff\" on \"ops_compare\".\"CompareID\" = \"ops_compare_staff\".\"CompareID\"")
	    .append(" where \"ops_arrest_indictment\".\"ArrestCode\" LIKE '%"+Textsearch+"%' ")
	    .append("AND \"ops_arrest_indictment\".\"IsActive\" = 1")
	    .append("OR \"ops_lawsuit\".\"LawsuitNo\" LIKE '%"+Textsearch+"%' ")
	    .append("AND \"ops_lawsuit\".\"IsActive\" = 1")
	    .append("OR \"ops_prove\".\"ProveReportNo\" LIKE '%"+Textsearch+"%' ")
	    .append("AND \"ops_prove\".\"IsActive\" = 1")
	    .append("OR \"ops_compare\".\"CompareCode\" LIKE '%"+Textsearch+"%' ")
	    .append("AND \"ops_compare\".\"IsActive\" = 1")
	    .append("OR \"ops_compare_staff\".\"TitleName\" LIKE '%"+Textsearch+"%' ")
	    .append("OR \"ops_compare_staff\".\"FirstName\" LIKE '%"+Textsearch+"%' ")
	    .append("OR \"ops_compare_staff\".\"LastName\" LIKE '%"+Textsearch+"%' ")
	    .append("OR \"ops_compare_staff\".\"DepartmentName\" LIKE '%"+Textsearch+"%' ");

		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<Compare> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public Compare mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	Compare item = new Compare();
			                	item.setArrestCode(rs.getString("ArrestCode"));
			                	item.setLawsuilt(rs.getString("LawsuitNo"));
			                	item.setProveReportNo(rs.getString("ProveReportNo"));
			                	item.setCompareID(rs.getInt("CompareID"));
			                	item.setCompareCode(rs.getString("CompareCode"));
			                	item.setCompareDate(rs.getString("CompareDate"));
			                	item.setCompareStationCode(rs.getString("CompareStationCode"));
			                	item.setCompareStation(rs.getString("CompareStation"));
			                	item.setCompareSubdistrictCode(rs.getString("CompareSubdistrictCode"));
			                	item.setCompareSubdistrict(rs.getString("CompareSubdistrict"));
			                	item.setCompareDistrictCode(rs.getString("CompareDistrictCode"));
			                	item.setCompareDistrict(rs.getString("CompareDistrict"));
			                	item.setCompareProvinceCode(rs.getString("CompareProvinceCode"));
			                	item.setCompareProvince(rs.getString("CompareProvince"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserSubdistrictCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserSubdistrict"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserDistrictCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserDistrict"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserProvinceCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserProvince"));
			                	item.setIsOutside(rs.getInt("IsOutside"));
			                	item.setLawsuitID(rs.getInt("LawsuitID"));
			                	item.setIsActive(rs.getInt("IsActive"));
			                	item.setCompareDetail(getCompareDetail("", rs.getInt("CompareID"),"","","",""));
			                	item.setCompareStaff(getCompareStaff(rs.getInt("CompareID"),"","",""));
								return item;
			                }
			});      
		return dataList;
	}
	
	@Override
	public List<Compare> getComparegetByConAdv(ComparegetByConAdvParam param) {
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("SELECT     DISTINCT  \"ops_arrest_indictment\".\"ArrestCode\","
	    		+ "\"ops_lawsuit\".\"LawsuitNo\","
	    		+ "\"ops_prove\".\"ProveReportNo\","
	    		+ "\"ops_compare\".\"CompareID\","
	    		+ "\"ops_compare\".\"CompareCode\","
	    		+ "to_char(\"ops_compare\".\"CompareDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as CompareDate,"
	    		+ "\"ops_compare\".\"CompareStationCode\","
	    		+ "\"ops_compare\".\"CompareStation\","
	    		+ "\"ops_compare\".\"CompareSubdistrictCode\","
	    		+ "\"ops_compare\".\"CompareSubdistrict\",    "
	    		+ "\"ops_compare\".\"CompareDistrictCode\",    "
	    		+ "\"ops_compare\".\"CompareDistrict\","
	    		+ "\"ops_compare\".\"CompareProvinceCode\","
	    		+ "\"ops_compare\".\"CompareProvince\","
	    		+ "\"ops_compare\".\"AccuserSubdistrictCode\","
	    		+ "\"ops_compare\".\"AccuserSubdistrict\","
	    		+ "\"ops_compare\".\"AccuserDistrictCode\","
	    		+ "\"ops_compare\".\"AccuserDistrict\","
	    		+ "\"ops_compare\".\"AccuserProvinceCode\","
	    		+ "\"ops_compare\".\"AccuserProvince\","
	    		+ "\"ops_compare\".\"IsOutside\","
	    		+ "\"ops_compare\".\"LawsuitID\","
	    		+ "\"ops_compare\".\"IsActive\"")
	    .append("FROM  ")
	    .append("\"ops_lawsuit\"  ")
	    .append("inner join \"ops_compare\" on \"ops_compare\".\"LawsuitID\" = \"ops_lawsuit\".\"LawsuitID\" ")
	    .append("left join \"ops_arrest_indictment\" on \"ops_arrest_indictment\".\"IndictmentID\" = \"ops_lawsuit\".\"IndictmentID\" ")
	    .append("left join \"ops_prove\" on \"ops_prove\".\"IndictmentID\" = \"ops_lawsuit\".\"IndictmentID\"")
	    .append("left join \"ops_compare_staff\" on \"ops_compare\".\"CompareID\" = \"ops_compare_staff\".\"CompareID\"")
	    .append(" where \"ops_arrest_indictment\".\"ArrestCode\" LIKE '%"+param.getArrestCode()+"%' ")
	    .append("AND \"ops_arrest_indictment\".\"IsActive\" = 1")
	    .append("OR \"ops_lawsuit\".\"LawsuitNo\" LIKE '%"+param.getLawsuitCode()+"%' ")
	    .append("AND \"ops_lawsuit\".\"IsActive\" = 1")
	    .append("OR \"ops_prove\".\"ProveReportNo\" LIKE '%"+param.getProveReportNo()+"%' ")
	    .append("AND \"ops_prove\".\"IsActive\" = 1")
	    .append("OR \"ops_compare\".\"CompareCode\" LIKE '%"+param.getCompareCode()+"%' ")
	    .append("OR \"ops_compare\".\"CompareDate\" BETWEEN TO_TIMESTAMP_TZ('"+param.getCompareDateFrom()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"') AND TO_TIMESTAMP_TZ('"+param.getCompareDateTo()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"')")
	    .append("AND \"ops_compare\".\"IsActive\" = 1")
	    .append("OR \"ops_compare_staff\".\"ProgramCode\" = '"+param.getProgramCode()+"'")
	    .append("OR \"ops_compare_staff\".\"ProcessCode\" = '"+param.getProcessCode()+"'")
	    .append("AND \"ops_compare_staff\".\"TitleName\" LIKE '%"+param.getStaff()+"%' ")
	    .append("AND \"ops_compare_staff\".\"FirstName\" LIKE '%"+param.getStaff()+"%' ")
	    .append("AND \"ops_compare_staff\".\"LastName\" LIKE '%"+param.getStaff()+"%' ")
	    .append("AND \"ops_compare_staff\".\"DepartmentName\" LIKE '%"+param.getDepartment()+"%' ");
		

		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<Compare> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public Compare mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	Compare item = new Compare();
			                	item.setArrestCode(rs.getString("ArrestCode"));
			                	item.setLawsuilt(rs.getString("LawsuitNo"));
			                	item.setProveReportNo(rs.getString("ProveReportNo"));
			                	item.setCompareID(rs.getInt("CompareID"));
			                	item.setCompareCode(rs.getString("CompareCode"));
			                	item.setCompareDate(rs.getString("CompareDate"));
			                	item.setCompareStationCode(rs.getString("CompareStationCode"));
			                	item.setCompareStation(rs.getString("CompareStation"));
			                	item.setCompareSubdistrictCode(rs.getString("CompareSubdistrictCode"));
			                	item.setCompareSubdistrict(rs.getString("CompareSubdistrict"));
			                	item.setCompareDistrictCode(rs.getString("CompareDistrictCode"));
			                	item.setCompareDistrict(rs.getString("CompareDistrict"));
			                	item.setCompareProvinceCode(rs.getString("CompareProvinceCode"));
			                	item.setCompareProvince(rs.getString("CompareProvince"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserSubdistrictCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserSubdistrict"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserDistrictCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserDistrict"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserProvinceCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserProvince"));
			                	item.setIsOutside(rs.getInt("IsOutside"));
			                	item.setLawsuitID(rs.getInt("LawsuitID"));
			                	item.setIsActive(rs.getInt("IsActive"));
			                	item.setCompareDetail(getCompareDetail("", rs.getInt("CompareID"),"","","",""));
			                	item.setCompareStaff(getCompareStaff(rs.getInt("CompareID"),"","",""));
								return item;
			                }
			});      
		return dataList;
	}
	
	@Override
	public List<Compare> getComparegetByCon(final ComparegetByConParam param) {
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("select "
	    		+ "\"CompareID\","
	    		+ "\"CompareCode\", "
	    		+ "to_char(\"CompareDate\",'"+Pattern.TO_CHAR_FORMAT_TIMESTAMP_TIMEZONE+"') as CompareDate,"
	    		+ "\"CompareStationCode\","
	    		+ "\"CompareStation\","
	    		+ "\"CompareSubdistrictCode\","
	    		+ "\"CompareSubdistrict\","
	    		+ "\"CompareDistrictCode\","
	    		+ "\"CompareDistrict\","
	    		+ "\"CompareProvinceCode\","
	    		+ "\"CompareProvince\","
	    		+ "\"AccuserSubdistrictCode\","
	    		+ "\"AccuserSubdistrict\","
	    		+ "\"AccuserDistrictCode\","
	    		+ "\"AccuserDistrict\","
	    		+ "\"AccuserProvinceCode\","
	    		+ "\"AccuserProvince\","
	    		+ "\"IsOutside\","
	    		+ "\"LawsuitID\","
	    		+ "\"IsActive\" "
	    		+ "from \"ops_compare\"  where \"IsActive\" = 1");
		 
		if(param.getCompareID() != null && !"".equals(param.getCompareID())){
			sqlBuilder.append("and \"CompareID\" = '"+param.getCompareID()+"'");
		}

		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<Compare> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public Compare mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	Compare item = new Compare();
			                	item.setCompareID(rs.getInt("CompareID"));
			                	item.setCompareCode(rs.getString("CompareCode"));
			                	item.setCompareDate(rs.getString("CompareDate"));
			                	item.setCompareStationCode(rs.getString("CompareStationCode"));
			                	item.setCompareStation(rs.getString("CompareStation"));
			                	item.setCompareSubdistrictCode(rs.getString("CompareSubdistrictCode"));
			                	item.setCompareSubdistrict(rs.getString("CompareSubdistrict"));
			                	item.setCompareDistrictCode(rs.getString("CompareDistrictCode"));
			                	item.setCompareDistrict(rs.getString("CompareDistrict"));
			                	item.setCompareProvinceCode(rs.getString("CompareProvinceCode"));
			                	item.setCompareProvince(rs.getString("CompareProvince"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserSubdistrictCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserSubdistrict"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserDistrictCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserDistrict"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserProvinceCode"));
			                	item.setAccuserDistrictCode(rs.getString("AccuserProvince"));
			                	item.setIsOutside(rs.getInt("IsOutside"));
			                	item.setLawsuitID(rs.getInt("LawsuitID"));	
			                	item.setIsActive(rs.getInt("IsActive"));
			                	item.setCompareDetail(getCompareDetail(param.getCompareDetailID(), rs.getInt("CompareID"),param.getCompareDetailReceiptID(),param.getCompareFineID(),param.getFineType(),param.getReceiptFineType()));
			                	item.setCompareStaff(getCompareStaff(rs.getInt("CompareID"),param.getStaffID(),param.getProgramCode(),param.getProcessCode()));
			                	
								return item;
			                }
			});      
		return dataList;
	}
	
	@Override
	public Boolean saveCompareinsAll(CompareParam param) {
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("INSERT INTO \"ILLEGAL60\".\"ops_compare\" ")
	    .append("(\"CompareID\","
	    		+ "\"CompareCode\","
	    		+ "\"CompareDate\","
	    		+ "\"CompareStationCode\","
	    		+ "\"CompareStation\","
	    		+ "\"CompareSubdistrictCode\","
	    		+ "\"CompareSubdistrict\",    "
	    		+ "\"CompareDistrictCode\",    "
	    		+ "\"CompareDistrict\","
	    		+ "\"CompareProvinceCode\","
	    		+ "\"CompareProvince\","
	    		+ "\"AccuserSubdistrictCode\","
	    		+ "\"AccuserSubdistrict\","
	    		+ "\"AccuserDistrictCode\","
	    		+ "\"AccuserDistrict\","
	    		+ "\"AccuserProvinceCode\","
	    		+ "\"AccuserProvince\","
	    		+ "\"IsOutside\","
	    		+ "\"LawsuitID\","
	    		+ "\"IsActive\")")
	    .append("VALUES ("
	    		//+ "'"+param.getCompareID()+"', "
	    		+ "\"ops_compare_SEQ\".NEXTVAL, "
	    		+ "'"+param.getCompareCode()+"', "
	    		+ "TO_TIMESTAMP_TZ('"+param.getCompareDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
	    		+ "'"+param.getCompareStationCode()+"', "
	    		+ "'"+param.getCompareStation()+"', "
	    		+ "'"+param.getCompareSubdistrictCode()+"', "
	    		+ "'"+param.getCompareSubdistrict()+"', "
	    		+ "'"+param.getCompareDistrictCode()+"', "
	    		+ "'"+param.getCompareDistrict()+"', "
	    		+ "'"+param.getCompareProvinceCode()+"', "
	    		+ "'"+param.getCompareProvince()+"', "
	    		+ "'"+param.getAccuserSubdistrictCode()+"', "
	    		+ "'"+param.getAccuserSubdistrict()+"', "
	    		+ "'"+param.getAccuserDistrictCode()+"', "
	    		+ "'"+param.getAccuserDistrict()+"', "
	    		+ "'"+param.getAccuserProvinceCode()+"', "
	    		+ "'"+param.getAccuserProvince()+"', "
	    		+ "'"+param.getIsOutside()+"',"
	    		+ "'"+param.getLawsuitID()+"', "
	    		+ "'1')");
		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}

	@Override
	public Boolean updateCompareupdByCon(CompareupdByConParam param) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE \"ILLEGAL60\".\"ops_compare\" "
				+ "SET \"CompareCode\" = '"+param.getCompareCode()+"', "
				+ "\"CompareDate\" = TO_TIMESTAMP_TZ('"+param.getCompareDate()+"', '"+Pattern.TO_TIMESTAMP_FORMAT_TIMESTAMP_TIMEZONE+"'), "
				+ "\"CompareStationCode\" = '"+param.getCompareStationCode()+"', "
				+ "\"CompareStation\" =  '"+param.getCompareStation()+"', "
				+ "\"CompareSubdistrictCode\" = '"+param.getCompareSubdistrictCode()+"', "
				+ "\"CompareSubdistrict\" = '"+param.getCompareSubdistrict()+"', "
				+ "\"CompareDistrictCode\" =  '"+param.getCompareDistrictCode()+"', "
				+ "\"CompareDistrict\" =  '"+param.getCompareDistrict()+"', "
				+ "\"CompareProvinceCode\" = '"+param.getCompareProvinceCode()+"', "
				+ "\"CompareProvince\" = '"+param.getCompareProvince()+"', "
				+ "\"AccuserSubdistrictCode\" = '"+param.getAccuserSubdistrictCode()+"', "
				+ "\"AccuserSubdistrict\" = '"+param.getAccuserSubdistrict()+"', "
				+ "\"AccuserDistrictCode\" =  '"+param.getAccuserDistrictCode()+"', "
				+ "\"AccuserDistrict\" = '"+param.getAccuserDistrict()+"', "
				+ "\"AccuserProvinceCode\" = '"+param.getAccuserProvinceCode()+"', "
				+ "\"AccuserProvince\" = '"+param.getAccuserProvince()+"', "
				+ "\"IsOutside\" = '"+param.getIsOutside()+"',"
				+ "\"LawsuitID\" = '"+param.getLawsuitID()+"',"
				+ "\"IsActive\" = 1 "
				+ "WHERE \"CompareID\" = '"+param.getCompareID()+"' ");

		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}

	@Override
	public Boolean deleteCompareupdDelete(String compareId) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE \"ILLEGAL60\".\"ops_compare\" SET \"IsActive\" = 0 WHERE \"CompareID\" = '"+compareId+"' ");

		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}

	
	//====================== Method Sub DataList Compare ===========================

	private List<CompareDetail> getCompareDetail(String compareDetailId,int compareId,final String receiptId,final String compareFineId,final String fineType,final String receiptFineType){
		
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
	    .append("from \"ops_compare_detail\" where \"CompareID\" = '"+compareId+"' and \"IsActive\" = 1 ");
	    	 			            		 
		if(compareDetailId != null && !"".equals(compareDetailId)){
			sqlBuilderDetail.append("and \"CompareDetailID\" = '"+compareDetailId+"'");
		}
		
		if(fineType != null && !"".equals(fineType)){
			sqlBuilderDetail.append("and \"FineType\" = '"+fineType+"'");
		}
    	
		log.info("[SQL] : "+sqlBuilderDetail.toString());
    	
    	@SuppressWarnings("unchecked")
		List<CompareDetail> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

		                public CompareDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
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
		                	item.setCompareDetailFine(getCompareDetailFine(rs.getInt("CompareDetailID"),compareFineId,getJdbcTemplate()));
		                	item.setCompareDetailReceipt(getCompareDetailReceipt(rs.getInt("CompareDetailID"),receiptFineType,receiptId,getJdbcTemplate()));
							return item;
		                }
		});
    	
    	
    	return dataList;
		
	}
	


	
	private List<CompareStaff> getCompareStaff(int compareId,String staffId,String programCode,String processCode){
		
		StringBuilder sqlBuilderDetail = new StringBuilder()
	    .append("select ")
	    .append("\"StaffID\", "
	    		+ "\"ProgramCode\", "
	    		+ "\"ProcessCode\", "
	    		+ "\"CompareID\", "
	    		+ "\"StaffCode\", "
	    		+ "\"TitleName\", "
	    		+ "\"FirstName\", "
	    		+ "\"LastName\", "
	    		+ "\"PositionCode\", "
	    		+ "\"PositionName\", "
	    		+ "\"PosLevel\", "
	    		+ "\"PosLevelName\", "
	    		+ "\"DepartmentCode\", "
	    		+ "\"DepartmentName\", "
	    		+ "\"DepartmentLevel\", "
	    		+ "\"OfficeCode\", "
	    		+ "\"OfficeName\", "
	    		+ "\"OfficeShortName\", "
	    		+ "\"ContributorID\", "
	    		+ "\"IsActive\"")
	    .append("from \"ops_compare_staff\" where \"CompareID\" = '"+compareId+"' and \"IsActive\" = 1 ");
		
		if(staffId != null && !"".equals(staffId)){
			sqlBuilderDetail.append("and \"StaffID\" = '"+staffId+"'");
		}
		if(programCode != null && !"".equals(programCode)){
			sqlBuilderDetail.append("and \"ProgramCode\" = '"+programCode+"'");
		}
		if(processCode != null && !"".equals(processCode)){
			sqlBuilderDetail.append("and \"ProcessCode\" = '"+processCode+"'");
		}
    	
		log.info("[SQL] : "+sqlBuilderDetail.toString());
		
    	@SuppressWarnings("unchecked")
		List<CompareStaff> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

		                public CompareStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
		                	CompareStaff item = new CompareStaff();
		                	item.setStaffID(rs.getInt("StaffID"));
		                	item.setProgramCode(rs.getString("ProgramCode"));
		                	item.setProcessCode(rs.getString("ProcessCode"));
		                	item.setCompareCode(rs.getString("CompareID"));
		                	item.setStaffCode(rs.getString("StaffCode"));
		                	item.setTitleName(rs.getString("TitleName"));
		                	item.setFirstName(rs.getString("FirstName"));
		                	item.setLastName(rs.getString("LastName"));
		                	item.setPositionCode(rs.getString("PositionCode"));
		                	item.setPositionName(rs.getString("PositionName"));
		                	item.setPosLevel(rs.getString("PosLevel"));
		                	item.setPosLevelName(rs.getString("PosLevelName"));
		                	item.setDepartmentCode(rs.getString("DepartmentCode"));
		                	item.setDepartmentName(rs.getString("DepartmentName"));
		                	item.setDepartmentLevel(rs.getString("DepartmentLevel"));
		                	item.setOfficeCode(rs.getString("OfficeCode"));
		                	item.setOfficeName(rs.getString("OfficeName"));
		                	item.setOfficeShortName(rs.getString("OfficeShortName"));
		                	item.setContributorID(rs.getInt("ContributorID"));
		                	item.setIsActive(rs.getInt("IsActive"));
							return item;
		                }
		});
    	
    	
    	return dataList;
		
	}

	
	
	
	
	

	
	
	


	

}
