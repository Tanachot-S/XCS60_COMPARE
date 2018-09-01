package com.xcs.compare.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xcs.compare.controller.CompareStaffRestController;
import com.xcs.compare.model.CompareStaff;

@Service
@Transactional
public class CompareStaffDAOImpl implements CompareStaffDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareStaffDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public Boolean saveCompareStaffInsAll(CompareStaff param) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("INSERT INTO \"ILLEGAL60\".\"ops_compare_staff\" ")
	    .append("(\"StaffID\", "
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
	    		+ "\"IsActive\")")
	    .append("VALUES ("
	    		//+ "'"+param.getStaffID()+"', "
	    		+ "\"ops_compare_staff_SEQ\".NEXTVAL, "
	    		+ "'"+param.getProgramCode()+"', "
	    		+ "'"+param.getProcessCode()+"', "
	    		+ ""+param.getCompareCode()+", "
	    		+ "'"+param.getStaffCode()+"', "
	    		+ "'"+param.getTitleName()+"', "
	    		+ "'"+param.getFirstName()+"', "
	    		+ "'"+param.getLastName()+"', "
	    		+ "'"+param.getPositionCode()+"', "
	    		+ "'"+param.getPositionName()+"', "
	    		+ "'"+param.getPosLevel()+"', "
	    		+ "'"+param.getPosLevelName()+"', "
	    		+ "'"+param.getDepartmentCode()+"', "
	    		+ "'"+param.getDepartmentName()+"', "
	    		+ "'"+param.getDepartmentLevel()+"', "
	    		+ "'"+param.getOfficeCode()+"', "
	    		+ "'"+param.getOfficeName()+"', "
	    		+ "'"+param.getOfficeShortName()+"', "
	    		+ "'"+param.getContributorID()+"', "
	    		+ "'1')");
		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}

	@Override
	public Boolean updateCompareStaffupdByCon(CompareStaff param) {
		
		
		StringBuilder sqlBuilder = new StringBuilder()
		.append("UPDATE \"ILLEGAL60\".\"ops_compare_staff\" "
				+ "SET \"ProgramCode\" = '"+param.getProgramCode()+"', "
				+ "\"ProcessCode\" = '"+param.getProcessCode()+"', "
				+ "\"CompareID\" = '"+param.getCompareCode()+"', "
				+ "\"StaffCode\" = '"+param.getStaffCode()+"', "
				+ "\"TitleName\" = '"+param.getTitleName()+"', "
				+ "\"FirstName\" = '"+param.getFirstName()+"', "
				+ "\"LastName\" = '"+param.getLastName()+"', "
				+ "\"PositionCode\" = '"+param.getPositionCode()+"', "
				+ "\"PositionName\" = '"+param.getPositionName()+"', "
				+ "\"PosLevel\" = '"+param.getPosLevel()+"', "
				+ "\"PosLevelName\" = '"+param.getPosLevelName()+"', "
				+ "\"DepartmentCode\" = '"+param.getDepartmentCode()+"', "
				+ "\"DepartmentName\" = '"+param.getDepartmentName()+"', "
				+ "\"DepartmentLevel\" = '"+param.getDepartmentLevel()+"', "
				+ "\"OfficeCode\" = '"+param.getOfficeCode()+"', "
				+ "\"OfficeName\" = '"+param.getOfficeName()+"', "
				+ "\"OfficeShortName\" = '"+param.getOfficeShortName()+"', "
				+ "\"ContributorID\" = '"+param.getContributorID()+"', "
				+ "\"IsActive\" = '1' "
				+ "WHERE \"StaffID\" = '"+param.getStaffID()+"' ");

		log.info("[SQL] : "+sqlBuilder.toString());
		
		int result = getJdbcTemplate().update(sqlBuilder.toString(), new Object[] {});
		return result != -1;
	}
	
	

	

	
	
	


	

}
