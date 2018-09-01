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
import com.xcs.compare.dao.CompareCountMistreatDAO;
import com.xcs.compare.mapparam.CompareCountMistreatgetByConParam;
import com.xcs.compare.model.CompareCountMistreat;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareCountMistreatRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareCountMistreatRestController.class);
	
	@Autowired
	protected CompareCountMistreatDAO compareCountMistreatDAO;
	
	
	@PostMapping(value = "/CompareCountMistreatgetByCon")
	public ResponseEntity compareCountMistreatgetByCon(@RequestBody CompareCountMistreatgetByConParam param) {
		

		log.info("============= Start API CompareCountMistreatgetByCon ================");
		MessageResponse msg = new MessageResponse();
		List<CompareCountMistreat> res = null;
		Boolean checkType = true;
		try {
			
			res = compareCountMistreatDAO.getCompareCountMistreatgetByCon(param);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API CompareCountMistreatgetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	

}