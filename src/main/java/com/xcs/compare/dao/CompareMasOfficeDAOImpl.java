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
import com.xcs.compare.controller.CompareFineNoticeRestController;
import com.xcs.compare.model.CompareMasOffice;

@Service
@Transactional
public class CompareMasOfficeDAOImpl implements CompareMasOfficeDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasOfficeDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public List<CompareMasOffice> getCompareMasOfficegetByKeyword(String Textsearch) {
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("SELECT ")
	    	.append("\"OfficeCode\", ")
	    	.append("\"OfficeName\", ")
	    	.append("\"OfficeShortName\", ")
	    	.append("\"INDCOffice\", ")
	    	.append("\"SubDistrictCode\", ")
	    	.append("\"SubOfficeCode\", ")
	    	.append("to_char(\"EffectiveDate\",'"+Pattern.FORMAT_DATETIME+"') as EffectiveDate, ")
	    	.append("to_char(\"ExpirationDate\",'"+Pattern.FORMAT_DATETIME+"') as ExpirationDate, ")
	    	.append("\"IsActive\", ")
	    	.append("to_char(\"EventDatetime\",'"+Pattern.FORMAT_TIMESTAMP+"') as EventDatetime ")
	    .append("FROM \"mas_office\" ")
	    .append("where \"OfficeCode\" like '%"+Textsearch+"%' OR \"OfficeName\" like '%"+Textsearch+"%' OR  \"OfficeShortName\" like '%"+Textsearch+"%' ")
	    .append("OR \"EffectiveDate\" BETWEEN SYSDATE AND SYSDATE OR \"ExpirationDate\" BETWEEN SYSDATE AND SYSDATE")
	    .append(" AND \"IsActive\" = 1");
		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<CompareMasOffice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public CompareMasOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareMasOffice item = new CompareMasOffice();
								item.setOfficeCode(rs.getString("OfficeCode"));
								item.setOfficeName(rs.getString("OfficeName"));
								item.setOfficeShortName(rs.getString("OfficeShortName"));
								item.setINDCOffice(rs.getString("INDCOffice"));
								item.setSubDistrictCode(rs.getString("SubDistrictCode"));
								item.setEffectiveDate(rs.getString("EffectiveDate"));
								item.setExpirationDate(rs.getString("ExpirationDate"));
								item.setIsActive(rs.getInt("IsActive"));
								item.setEventDatetime(rs.getString("EventDatetime"));
								return item;
			                }
			});      
		return dataList;
	}



	

	

}
