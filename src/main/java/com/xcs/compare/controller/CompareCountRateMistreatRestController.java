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
import com.xcs.compare.dao.CompareCountRateMistreatDAO;
import com.xcs.compare.mapparam.CompareCountRateMistreatgetByConParam;
import com.xcs.compare.model.CompareCountRateMistreat;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareCountRateMistreatRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareCountRateMistreatRestController.class);
	
	@Autowired
	protected CompareCountRateMistreatDAO compareCountRateMistreatDAO;
	
	
	@PostMapping(value = "/CompareCountRateMistreatgetByCon")
	public ResponseEntity compareCountRateMistreatgetByCon(@RequestBody CompareCountRateMistreatgetByConParam param) {
		

		log.info("============= Start API CompareCountRateMistreatgetByCon ================");
		MessageResponse msg = new MessageResponse();
		List<CompareCountRateMistreat> res = null;
		Boolean checkType = true;
		try {
			
			res = compareCountRateMistreatDAO.getCompareCountRateMistreatgetByCon(param);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API CompareCountRateMistreatgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}

}