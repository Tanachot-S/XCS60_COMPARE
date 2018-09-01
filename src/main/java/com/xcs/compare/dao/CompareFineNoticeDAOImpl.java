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
import com.xcs.compare.model.CompareFineNotice;

@Service
@Transactional
public class CompareFineNoticeDAOImpl implements CompareFineNoticeDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CompareFineNoticeDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public List<CompareFineNotice> getCompareFineNoticegetByCon(String ArrestCode) {

		StringBuilder sqlBuilder = new StringBuilder()
	    .append("SELECT ")
	    	.append("\"NoticeCode\", ")
	    	.append("\"NoticeStationCode\", ")
	    	.append("\"NoticeStation\", ")
	    	.append("to_char(\"NoticeDate\",'"+Pattern.FORMAT_DATE+"') as NoticeDate , ")
	    	.append("\"NoticeTime\", ")
	    	.append("\"NoticeDue\", ")
	    	.append("to_char(\"NoticeDueDate\",'"+Pattern.FORMAT_DATETIME+"') as NoticeDueDate , ")
	    	.append("\"CommunicationChanelID\", ")
	    	.append("\"ArrestCode\", ")
	    	.append("\"IsArrest\", ")
	    	.append("\"IsActive\" ")
	        .append("FROM \"ops_notice\" ");
	    
	    if(!"".endsWith(ArrestCode)){
	    	sqlBuilder.append("where \"ArrestCode\" = '"+ArrestCode+"'");
	    }
	    
	    log.info("[SQL] : "+sqlBuilder.toString());
	    
			@SuppressWarnings("unchecked")
			List<CompareFineNotice> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
	
			                public CompareFineNotice mapRow(ResultSet rs, int rowNum) throws SQLException {
			                	CompareFineNotice item = new CompareFineNotice();
								item.setNoticeCode(rs.getString("NoticeCode"));
								item.setNoticeStationCode(rs.getString("NoticeStationCode"));
								item.setNoticeStation(rs.getString("NoticeStation"));
								item.setNoticeDate(rs.getString("NoticeDate"));
								item.setNoticeTime(rs.getString("NoticeTime"));
								item.setNoticeDue(rs.getInt("NoticeDue"));
								item.setNoticeDueDate(rs.getString("NoticeDueDate"));
								item.setCommunicationChanelID(rs.getInt("CommunicationChanelID"));
								item.setArrestCode(rs.getString("ArrestCode"));
								item.setIsArrest(rs.getInt("IsArrest"));
								item.setIsActive(rs.getInt("IsActive"));
								return item;
			                }
			});      
		return dataList;
	}

	
	
	


	

}
