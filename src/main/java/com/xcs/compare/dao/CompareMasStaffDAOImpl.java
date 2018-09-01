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

import com.xcs.compare.controller.CompareMasOfficeRestController;
import com.xcs.compare.model.CompareMasStaff;

@Service
@Transactional
public class CompareMasStaffDAOImpl implements CompareMasStaffDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasStaffDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	@Override
	public List<CompareMasStaff> getCompareMasStaffgetByKeyword(String Textsearch) {

		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("SELECT ")
	    	.append("\"StaffCode\", ")
	    	.append("\"PerType\", ")
	    	.append("\"TitleName\", ")
	    	.append("\"FirstName\", ")
	    	.append("\"LastName\", ")
	    	.append("\"OperationPosCode\", ")
	    	.append("\"OperationPosName\", ")
	    	.append("\"ManagementPosCode\", ")
	    	.append("\"ManagementPosName\", ")
	    	.append("\"PosLevel\", ")
	    	.append("\"PosLevelName\", ")
	    	.append("\"RepresentationPosCode\", ")
	    	.append("\"RepresentationPosName\", ")
	    	.append("\"OperationDeptCode\", ")
	    	.append("\"OperationDeptName\", ")
	    	.append("\"UnderDeptCode\", ")
	    	.append("\"UnderDeptName\", ")
	    	.append("\"DeptLevel\", ")
	    	.append("\"OfficeCode\", ")
	    	.append("\"OfficeName\", ")
	    	.append("\"OfficeShortName\", ")
	    	.append("\"StatusCode\", ")
	    	.append("\"IsActive\" ")
	    .append("FROM \"mas_staff\" ")
	    .append("where \"StaffCode\" like '%"+Textsearch+"%' OR \"TitleName\" like '%"+Textsearch+"%' OR  \"FirstName\" like '%"+Textsearch+"%' OR  \"LastName\" like '%"+Textsearch+"%' ")
	    .append(" AND \"IsActive\" = 1");
		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<CompareMasStaff> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public CompareMasStaff mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareMasStaff item = new CompareMasStaff();
								item.setStaffCode(rs.getString("StaffCode"));
								item.setPerType(rs.getInt("PerType"));
								item.setTitleName(rs.getString("TitleName"));
								item.setFirstName(rs.getString("FirstName"));
								item.setLastName(rs.getString("LastName"));
								item.setOperationPosCode(rs.getString("OperationPosCode"));
								item.setOperationPosName(rs.getString("OperationPosName"));
								item.setManagementPosCode(rs.getString("ManagementPosCode"));
								item.setManagementPosName(rs.getString("ManagementPosName"));
								item.setPosLevel(rs.getString("PosLevel"));
								item.setPosLevelName(rs.getString("PosLevelName"));
								item.setRepresentationPosCode(rs.getString("RepresentationPosCode"));
								item.setRepresentationPosName(rs.getString("RepresentationPosName"));
								item.setOperationDeptCode(rs.getString("OperationDeptCode"));
								item.setOperationDeptName(rs.getString("OperationDeptName"));
								item.setUnderDeptCode(rs.getString("UnderDeptCode"));
								item.setUnderDeptName(rs.getString("UnderDeptName"));
								item.setDeptLevel(rs.getString("DeptLevel"));
								item.setOfficeCode(rs.getString("OfficeCode"));
								item.setOfficeName(rs.getString("OfficeName"));
								item.setOfficeShortName(rs.getString("OfficeShortName"));
								item.setStatusCode(rs.getInt("StatusCode"));
								item.setIsActive(rs.getInt("IsActive"));
								return item;
			                }
			});      
		return dataList;
	}



	

}
