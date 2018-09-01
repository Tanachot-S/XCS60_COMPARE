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
import com.xcs.compare.dao.CompareMasStaffDAO;
import com.xcs.compare.mapparam.CompareMasStaffgetByKeywordParam;
import com.xcs.compare.model.CompareMasStaff;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareMasStaffRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasStaffRestController.class);
	
	@Autowired
	protected CompareMasStaffDAO compareMasStaffDAO;
	
	
	@PostMapping(value = "/CompareMasStaffgetByKeyword")
	public ResponseEntity compareMasStaffgetByKeyword(@RequestBody CompareMasStaffgetByKeywordParam param) {
		

		log.info("============= Start API CompareMasStaffgetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<CompareMasStaff> res = null;
		Boolean checkType = true;
		try {
			
			res = compareMasStaffDAO.getCompareMasStaffgetByKeyword(param.getTextsearch() != null ? param.getTextsearch() : "");
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API CompareMasStaffgetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	

}