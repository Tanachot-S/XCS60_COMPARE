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
import com.xcs.compare.controller.CompareMasOfficeRestController;
import com.xcs.compare.model.CompareMasDivisionRate;

@Service
@Transactional
public class CompareMasDivisionRateDAOImpl implements CompareMasDivisionRateDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasDivisionRateDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	

	@Override
	public List<CompareMasDivisionRate> getCompareMasDivisionRategetByCon() {
		
		StringBuilder sqlBuilder = new StringBuilder()
		.append("select "
				+ "\"RateID\","
				+ "\"TreasuryRate\","
				+ "\"BribeRate\","
				+ "\"BribeMaxMoney\","
				+ "\"RewardRate\","
				+ "\"RewardMaxMoney\","
				+ "to_char(\"EffectiveDate\",'"+Pattern.FORMAT_DATETIME+"') as EffectiveDate,"
				+ "to_char(\"ExpirationDate\",'"+Pattern.FORMAT_DATETIME+"') as ExpirationDate, "
				+ "\"IsActive\""
				+ "from \"mas_divisionrate\" where \"IsActive\" = 1");
		log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<CompareMasDivisionRate> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public CompareMasDivisionRate mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareMasDivisionRate item = new CompareMasDivisionRate();
			                	item.setRateID(rs.getInt("RateID"));
			                	item.setTreasuryRate(rs.getInt("TreasuryRate"));
			                	item.setBribeRate(rs.getInt("BribeRate"));
			                	item.setBribeMaxMoney(rs.getInt("BribeMaxMoney"));
			                	item.setRewardRate(rs.getInt("RewardRate"));
			                	item.setRewardMaxMoney(rs.getInt("RewardMaxMoney"));
								item.setEffectiveDate(rs.getString("EffectiveDate"));
								item.setExpirationDate(rs.getString("ExpirationDate"));
								item.setIsActive(rs.getInt("IsActive"));
								return item;
			                }
			});      
		return dataList;
	}



	

	

}
