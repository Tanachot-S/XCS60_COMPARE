package com.xcs.compare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xcs.compare.constant.Message;
import com.xcs.compare.dao.CompareMasDivisionRateDAO;
import com.xcs.compare.model.CompareMasDivisionRate;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareMasOfficeRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasOfficeRestController.class);
	
	@Autowired
	protected CompareMasDivisionRateDAO compareMasDivisionRateDAO;
	
	
	@PostMapping(value = "/CompareMasDivisionRategetByCon")
	public ResponseEntity compareMasDivisionRategetByCon() {
		

		log.info("============= Start API compareMasDivisionRategetByCon ================");
		MessageResponse msg = new MessageResponse();
		List<CompareMasDivisionRate> res = null;
		Boolean checkType = true;
		try {
			
			res = compareMasDivisionRateDAO.getCompareMasDivisionRategetByCon();
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API compareMasDivisionRategetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	

}