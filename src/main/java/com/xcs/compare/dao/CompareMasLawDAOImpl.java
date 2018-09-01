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

import com.xcs.compare.controller.CompareMasDivisionRateRestController;
import com.xcs.compare.model.CompareMasLaw;
import com.xcs.compare.model.CompareMasLawGuiltBase;
import com.xcs.compare.model.CompareMasLawPenalty;
import com.xcs.compare.model.CompareMasLawSection;
import com.xcs.compare.model.CompareMasLawSubSection;
import com.xcs.compare.model.CompareMasLawSubSectionRule;

@Service
@Transactional
public class CompareMasLawDAOImpl implements CompareMasLawDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasLawDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public CompareMasLaw getCompareMasLawgetByCon(String guiltBaseId) {
		
		StringBuilder sqlBuilder = new StringBuilder()
	    .append("SELECT ")
	    	.append("\"mas_law_group\".* ,")
	    	.append("\"mas_law_group_section\".*,")
	    	.append("\"mas_law_group_subsection\".*,")
	    	.append("\"mas_law_group_subsection_rule\".*,")
	    	.append("\"mas_law_guiltbase\".*,")
	    	.append("\"mas_law_penalty\".*")
	    .append(" FROM \"mas_law_guiltbase\" ")
	    .append(" inner join \"mas_law_group_subsection_rule\" on \"mas_law_guiltbase\".\"SubSectionRuleID\" = \"mas_law_group_subsection_rule\".\"SubSectionRuleID\"")
	    .append(" inner join \"mas_law_group_subsection\" on \"mas_law_group_subsection_rule\".\"SubSectionID\" = \"mas_law_group_subsection\".\"SubSectionID\"")
	    .append(" inner join \"mas_law_penalty\" on \"mas_law_group_subsection_rule\".\"SectionNo\" = \"mas_law_penalty\".\"SectionNo\"")
	    .append(" inner join \"mas_law_group_section\" on \"mas_law_group_subsection\".\"SectionNo\" = \"mas_law_group_section\".\"SectionNo\"")
	    .append(" inner join \"mas_law_group\" on \"mas_law_group_section\".\"LawGroupID\" = \"mas_law_group\".\"LawGroupID\"")
	    .append(" where \"mas_law_guiltbase\".\"GuiltBaseID\" = "+guiltBaseId+"")
	    .append(" and \"mas_law_group\".\"IsActive\" = 1")
	    .append(" and \"mas_law_group_section\".\"IsActive\" = 1")
	    .append(" and \"mas_law_group_subsection\".\"IsActive\" = 1")
	    .append(" and \"mas_law_group_subsection_rule\".\"IsActive\" = 1")
	    .append(" and \"mas_law_guiltbase\".\"IsActive\" = 1")
	    .append(" and \"mas_law_penalty\".\"IsActive\" = 1");
		log.info("[SQL] : "+sqlBuilder.toString());
		
		return getJdbcTemplate().query(sqlBuilder.toString(), new ResultSetExtractor<CompareMasLaw>() {

			public CompareMasLaw extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					
					CompareMasLaw item = new CompareMasLaw();
					item.setLawGroupID(rs.getInt("LawGroupID"));
					item.setLawGroupNo(rs.getString("LawGroupNo"));
					item.setLawGroupName(rs.getString("LawGroupName"));
					item.setPartNo(rs.getString("PartNo"));
					item.setPartName(rs.getString("PartName"));
					item.setIsActive(rs.getInt("IsActive"));
					
					CompareMasLawSection obj1  = new CompareMasLawSection();
					obj1.setSectionNo(rs.getInt("SectionNo"));
					obj1.setSectionName(rs.getString("SectionName"));
					obj1.setSectionDesc1(rs.getString("SectionDesc1"));
					obj1.setSectionDesc2(rs.getString("SectionDesc2"));
					obj1.setSectionDesc3(rs.getString("SectionDesc3"));
					obj1.setLawGroupID(rs.getInt("LawGroupID"));
					obj1.setIsActive(rs.getInt("IsActive"));
					
					CompareMasLawSubSection obj2 = new CompareMasLawSubSection();
					obj2.setSubSectionID(rs.getInt("SubSectionID"));
					obj2.setSubSectionNo(rs.getInt("SubSectionNo"));
					obj2.setSubSectionType(rs.getString("SubSectionType"));
					obj2.setSubSectionDesc(rs.getString("SubSectionDesc"));
					obj2.setSectionNo(rs.getInt("SectionNo"));
					obj2.setIsActive(rs.getInt("IsActive"));
					
					CompareMasLawSubSectionRule obj3 = new CompareMasLawSubSectionRule();
					obj3.setSubSectionRuleID(rs.getInt("SubSectionRuleID"));
					obj3.setSubSectionID(rs.getInt("SubSectionID"));
					obj3.setSectionNo(rs.getInt("SectionNo"));
					obj3.setIsActive(rs.getInt("IsActive"));
					
					CompareMasLawGuiltBase obj4 = new CompareMasLawGuiltBase();
					obj4.setGuiltBaseID(rs.getInt("GuiltBaseID"));
					obj4.setGuiltBaseName(rs.getString("GuiltBaseName"));
					obj4.setFine(rs.getString("Fine"));
					obj4.setIsProve(rs.getInt("IsProve"));
					obj4.setRemark(rs.getString("Remark"));
					obj4.setIsActive(rs.getInt("IsActive"));
					
					CompareMasLawPenalty obj5 = new CompareMasLawPenalty();
					obj5.setPenaltyID(rs.getInt("PenaltyID"));
					obj5.setSectionNo(rs.getInt("SectionNo"));
					obj5.setPenaltyDesc(rs.getString("PenaltyDesc"));
					obj5.setIsFinePrison(rs.getInt("IsFinePrison"));
					obj5.setIsFine(rs.getInt("IsFine"));
					obj5.setFineRateMin(rs.getInt("FineRateMin"));
					obj5.setFineRateMax(rs.getInt("FineRateMax"));
					obj5.setFineMin(rs.getInt("FineMin"));
					obj5.setFineMax(rs.getInt("FineMax"));
					obj5.setIsImprison(rs.getInt("IsImprison"));
					obj5.setPrisonRateMin(rs.getString("PrisonRateMin"));
					obj5.setPrisonRateMax(rs.getString("PrisonRateMax"));
					obj5.setIsTaxPaid(rs.getInt("IsTaxPaid"));
					obj5.setIsActive(rs.getInt("IsActive"));
					
					item.setCompareMasLawSection(obj1);
					item.setCompareMasLawSubSection(obj2);
					item.setCompareMasLawSubSectionRule(obj3);
					item.setCompareMasLawGuiltBase(obj4);
					item.setCompareMasLawPenalty(obj5);
					
					return item;
				}
				
				return null;
			}

			
			
		});
	}
	
	



	

	

}
