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

import com.xcs.compare.controller.CompareCountMistreatRestController;
import com.xcs.compare.mapparam.CompareCountRateMistreatgetByConParam;
import com.xcs.compare.model.CompareCountRateMistreat;

@Service
@Transactional
public class CompareCountRateMistreatDAOImpl implements CompareCountRateMistreatDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareCountRateMistreatDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<CompareCountRateMistreat> getCompareCountRateMistreatgetByCon(
			CompareCountRateMistreatgetByConParam param) {
		
		

		StringBuilder sqlBuilder = new StringBuilder()
	    .append("SELECT ")
	    	.append("\"FineID\", ")
	    	.append("\"SubSectionRuleID\", ")
	    	.append("\"GroupCode\", ")
	    	.append("\"MistreatStartNo\", ")
	    	.append("\"MistreatToNo\", ")
	    	.append("\"IsFine\", ")
	    	.append("\"FineRate\", ")
	    	.append("\"MistreatDesc\", ")
	    	.append("\"MistreatStartVolume\", ")
	    	.append("\"MistreatToVolume\", ")
	    	.append("\"FineAmount\", ")
	    	.append("\"FineTax\", ")
	    	.append("\"IsActive\" ")
	        .append("FROM \"mas_law_guiltbase_fine\" ")
	        .append("where 1=1 ");
	    
	    if(!"".endsWith(param.getSubSectionRuleID())){
	    	sqlBuilder.append("and \"SubSectionRuleID\" = '"+param.getSubSectionRuleID()+"'");
	    }
	    
		if(!"".endsWith(param.getGroupCode())){
	    	sqlBuilder.append("or \"GroupCode\" = '"+param.getGroupCode()+"'");
	    }
		
		if(param.getMistreatNo() != 0){
	    	sqlBuilder.append("or \"MistreatStartNo\" between "+param.getMistreatNo()+" and "+param.getMistreatNo()+"");
	    	sqlBuilder.append("or \"MistreatToNo\" between "+param.getMistreatNo()+" and "+param.getMistreatNo()+"");
	    }
		
		if(param.getMistreatVolume() != 0){
			sqlBuilder.append("or \"MistreatStartVolume\" = "+param.getMistreatVolume()+"");
			sqlBuilder.append("or \"MistreatToVolume\" = "+param.getMistreatVolume()+"");
	    }
		sqlBuilder.append("and \"IsActive\" = 1");
	
	    
		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<CompareCountRateMistreat> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public CompareCountRateMistreat mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareCountRateMistreat item = new CompareCountRateMistreat();
								item.setFineID(rs.getInt("FineID"));
								item.setSubSectionRuleID(rs.getInt("SubSectionRuleID"));
								item.setGroupCode(rs.getString("GroupCode"));
								item.setMistreatStartNo(rs.getInt("MistreatStartNo"));
								item.setMistreatToNo(rs.getInt("MistreatToNo"));
								item.setIsFine(rs.getInt("IsFine"));
								item.setFineRate(rs.getInt("FineRate"));
								item.setMistreatDesc(rs.getString("MistreatDesc"));
								item.setMistreatStartVolume(rs.getInt("MistreatStartVolume"));
								item.setMistreatToVolume(rs.getInt("MistreatToVolume"));
								item.setFineAmount(rs.getInt("FineAmount"));
								item.setFineTax(rs.getInt("FineTax"));
								item.setIsActive(rs.getInt("IsActive"));
								return item;
								
			                }
			});      
		return dataList;
	}
	
	

	

	
	
	


	

}
