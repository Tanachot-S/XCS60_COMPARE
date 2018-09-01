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
import com.xcs.compare.dao.CompareDetailDAO;
import com.xcs.compare.mapparam.CompareDetailParam;
import com.xcs.compare.mapparam.CompareDetailReceiptParam;
import com.xcs.compare.mapparam.CompareDetailReceiptupdDeleteParam;
import com.xcs.compare.model.CompareDetailinsAllResponse;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareDetailRestController {
	
	private static final Logger log = LoggerFactory.getLogger(CompareDetailRestController.class);
	
	@Autowired
	protected CompareDetailDAO compareDetailDAO;
	
	@PostMapping(value = "/CompareDetailinsAll")
	public ResponseEntity compareDetailinsAll(@RequestBody CompareDetailParam param) {
		
	
		log.info("============= Start API CompareDetailinsAll ================");
		MessageResponse msg = new MessageResponse();
		CompareDetailinsAllResponse res = null;
		Boolean checkType = true;
		try {
			
			res = compareDetailDAO.saveCompareStaffInsAll(param);
			
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
		}
		log.info("============= End API CompareDetailinsAll =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/CompareDetailupdByCon")
	public ResponseEntity compareDetailupdByCon(@RequestBody CompareDetailParam param) {
		
		log.info("============= Start API CompareDetailupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(compareDetailDAO.updateCompareDetailupdByCon(param)){
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
		log.info("============= End API CompareDetailupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/CompareDetailReceiptinsAll")
	public ResponseEntity compareDetailReceiptinsAll(@RequestBody CompareDetailReceiptParam param) {
		
		log.info("============= Start API CompareDetailReceiptinsAll ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(compareDetailDAO.saveCompareDetailReceiptinsAll(param)){
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
		log.info("============= End API CompareDetailReceiptinsAll =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/CompareDetailReceiptupdByCon")
	public ResponseEntity compareDetailReceiptupdByCon(@RequestBody CompareDetailReceiptParam param) {
		
		log.info("============= Start API CompareDetailReceiptupdByCon ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(compareDetailDAO.updateCompareDetailReceiptupdByCon(param)){
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
		log.info("============= End API CompareDetailReceiptupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}

	@PostMapping(value = "/CompareDetailReceiptupdDelete")
	public ResponseEntity compareDetailReceiptupdDelete(@RequestBody CompareDetailReceiptupdDeleteParam param) {
		
		log.info("============= Start API CompareDetailReceiptupdDelete ================");
		
		MessageResponse msg = new MessageResponse();
		try {
			if(compareDetailDAO.deleteCompareDetailReceiptupdDelete(param.getCompareReceiptID())){
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
		log.info("============= End API CompareDetailReceiptupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	

}