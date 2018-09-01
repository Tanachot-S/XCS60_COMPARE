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
import com.xcs.compare.dao.CompareStaffDAO;
import com.xcs.compare.model.CompareStaff;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareStaffRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareStaffRestController.class);
	
	@Autowired
	protected CompareStaffDAO compareStaffDAO;
	
	
	@PostMapping(value = "/CompareStaffInsAll")
	public ResponseEntity compareStaffInsAll(@RequestBody CompareStaff param) {
		
		log.info("============= Start API CompareStaffInsAll ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(compareStaffDAO.saveCompareStaffInsAll(param)){
				msg.setIsSuccess(Message.TRUE);
				msg.setMsg(Message.COMPLETE);
			}else{
				msg.setIsSuccess(Message.FALSE);
				msg.setMsg(Message.NOT_COMPLETE);
			}
			
			
		} catch (Exception e) {
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
		}
		log.info("============= End API CompareStaffInsAll =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/CompareStaffupdByCon")
	public ResponseEntity compareStaffupdByCon(@RequestBody CompareStaff param) {
		
		log.info("============= Start API CompareStaffupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(compareStaffDAO.updateCompareStaffupdByCon(param)){
				msg.setIsSuccess(Message.TRUE);
				msg.setMsg(Message.COMPLETE);
			}else{
				msg.setIsSuccess(Message.FALSE);
				msg.setMsg(Message.NOT_COMPLETE);
			}
			
		} catch (Exception e) {
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
		}
		log.info("============= End API CompareStaffupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	

}