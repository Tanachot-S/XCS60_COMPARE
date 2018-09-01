package com.xcs.compare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xcs.compare.constant.Message;
import com.xcs.compare.dao.CompareFineNoticeDAO;
import com.xcs.compare.mapparam.CompareFineNoticegetByConParam;
import com.xcs.compare.model.CompareFineNotice;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareFineNoticeRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareFineNoticeRestController.class);
	
	@Autowired
	protected CompareFineNoticeDAO compareFineNoticeDAO;
	
	
	@PostMapping(value = "/CompareFineNoticegetByCon")
	public ResponseEntity compareFineNoticegetByCon(@RequestBody CompareFineNoticegetByConParam param) {
		

		log.info("============= Start API CompareFineNoticegetByCon ================");
		MessageResponse msg = new MessageResponse();
		List<CompareFineNotice> res = null;
		Boolean checkType = true;
		try {
			
			res = compareFineNoticeDAO.getCompareFineNoticegetByCon(param.getArrestCode() != null ? param.getArrestCode() : "");
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API CompareFineNoticegetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}

}