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
import com.xcs.compare.mapparam.CompareCountMistreatgetByConParam;
import com.xcs.compare.model.CompareCountMistreat;

@Service
@Transactional
public class CompareCountMistreatDAOImpl  implements CompareCountMistreatDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareCountMistreatDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<CompareCountMistreat> getCompareCountMistreatgetByCon(
			CompareCountMistreatgetByConParam param) {
		

		StringBuilder sqlBuilder = new StringBuilder()
		.append(" select "
				+ "\"LawsuitID\","
				+ "\"IndictmentID\","
				+ "\"IsLawsuit\","
				+ "\"ReasonDontLawsuit\","
				+ "\"LawsuitNo\","
				+ "to_char(\"LawsuitDate\",'"+Pattern.FORMAT_DATETIME+"') as LawsuitDate,"
				+ "\"LawsuitTime\","
				+ "\"LawsuitStationCode\","
				+ "\"LawsuitStation\","
				+ "\"IsOutside\","
				+ "\"AccuserTestimony\","
				+ "\"LawsuitResult\","
				+ "\"DeliveryDocNo\","
				+ "to_char(\"DeliveryDate\",'"+Pattern.FORMAT_DATETIME+"') as DeliveryDate  "
				+ "from \"ops_arrest_indicment_detail\" ")
		.append(" inner join \"ops_lawsuit\" on \"ops_arrest_indicment_detail\".\"IndictmentID\" = \"ops_lawsuit\".\"IndictmentID\" ")
		.append(" inner join \"ops_arrest_indictment\" on \"ops_arrest_indicment_detail\".\"IndictmentID\" = \"ops_arrest_indictment\".\"IndictmentID\" ")
		.append(" inner join \"mas_law_guiltbase\" on \"ops_arrest_indictment\".\"GuiltBaseID\" = \"mas_law_guiltbase\".\"GuiltBaseID\" ")
		.append(" inner join \"mas_law_group_subsection_rule\" on \"mas_law_guiltbase\".\"SubSectionRuleID\" = \"mas_law_group_subsection_rule\".\"SubSectionRuleID\" ")
		.append(" where \"ops_arrest_indicment_detail\".\"LawbreakerID\" = '"+param.getLawbreakerID()+"' ")
		.append(" and \"mas_law_group_subsection_rule\".\"SectionNo\" = '"+param.getSectionNo()+"' ")
		.append(" and \"ops_arrest_indicment_detail\".\"IsActive\"  = 1 ")
		.append(" and \"mas_law_group_subsection_rule\".\"IsActive\"  = 1 ")
		.append(" and \"ops_arrest_indictment\".\"IsActive\"  = 1 ")
		.append(" and \"mas_law_guiltbase\".\"IsActive\"  = 1");
		
		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<CompareCountMistreat> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public CompareCountMistreat mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareCountMistreat item = new CompareCountMistreat();
			                	
			                	
			                	item.setLawsuitID(rs.getInt("LawsuitID"));
			                	item.setIndictmentID(rs.getInt("IndictmentID"));
			                	item.setIsLawsuit(rs.getInt("IsLawsuit"));
			                	item.setReasonDontLawsuit(rs.getString("ReasonDontLawsuit"));
			                	item.setLawsuitNo(rs.getString("LawsuitNo"));
			                	item.setLawsuitDate(rs.getString("LawsuitDate"));
			                	item.setLawsuitTime(rs.getString("LawsuitTime"));
			                	item.setLawsuitStationCode(rs.getString("LawsuitStationCode"));
			                	item.setLawsuitStation(rs.getString("LawsuitStation"));
			                	item.setIsOutside(rs.getInt("IsOutside"));
			                	item.setAccuserTestimony(rs.getString("AccuserTestimony"));
			                	item.setLawsuitResult(rs.getString("LawsuitResult"));
			                	item.setDeliveryDocNo(rs.getString("DeliveryDocNo"));
			                	item.setDeliveryDate(rs.getString("DeliveryDate"));
								return item;
			                }
			});      
		return dataList;
	}
	
	

	

	
	
	


	

}
