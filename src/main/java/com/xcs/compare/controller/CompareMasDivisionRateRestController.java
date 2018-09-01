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
import com.xcs.compare.dao.CompareMasOfficeDAO;
import com.xcs.compare.mapparam.CompareMasOfficegetByKeywordParam;
import com.xcs.compare.model.CompareMasOffice;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareMasDivisionRateRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasDivisionRateRestController.class);
	
	@Autowired
	protected CompareMasOfficeDAO compareMasOfficeDAO;
	
	
	@PostMapping(value = "/CompareMasOfficegetByKeyword")
	public ResponseEntity compareMasStaffgetByKeyword(@RequestBody CompareMasOfficegetByKeywordParam param) {
		

		log.info("============= Start API CompareMasOfficegetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<CompareMasOffice> res = null;
		Boolean checkType = true;
		try {
			
			res = compareMasOfficeDAO.getCompareMasOfficegetByKeyword(param.getTextsearch() != null ? param.getTextsearch() : "");
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API CompareMasStaffgetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	

}