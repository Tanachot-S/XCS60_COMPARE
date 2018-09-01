package com.xcs.compare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xcs.compare.constant.Message;
import com.xcs.compare.dao.CompareMasLawDAO;
import com.xcs.compare.mapparam.CompareMasLawgetByCon;
import com.xcs.compare.model.CompareMasLaw;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareMasLawRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareMasLawRestController.class);
	
	@Autowired
	protected CompareMasLawDAO compareMasLawDAO;
	
	
	@PostMapping(value = "/CompareMasLawgetByCon")
	public ResponseEntity compareMasStaffgetByKeyword(@RequestBody CompareMasLawgetByCon param) {
		

		log.info("============= Start API CompareMasLawgetByCon ================");
		MessageResponse msg = new MessageResponse();
		CompareMasLaw res = null;
		Boolean checkType = true;
		try {
			if(!"".equals(param.getGuiltBaseID())){
				res = compareMasLawDAO.getCompareMasLawgetByCon(param.getGuiltBaseID());
			}
			
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API CompareMasLawgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	

}