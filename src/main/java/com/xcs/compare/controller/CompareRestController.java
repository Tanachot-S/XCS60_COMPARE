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
import com.xcs.compare.dao.CompareDAO;
import com.xcs.compare.mapparam.CompareParam;
import com.xcs.compare.mapparam.ComparegetByConAdvParam;
import com.xcs.compare.mapparam.ComparegetByConParam;
import com.xcs.compare.mapparam.ComparegetByKeywordParam;
import com.xcs.compare.mapparam.CompareupdByConParam;
import com.xcs.compare.mapparam.CompareupdDeleteParam;
import com.xcs.compare.model.Compare;
import com.xcs.compare.model.MessageResponse;



@RestController
public class CompareRestController  {
	
	private static final Logger log = LoggerFactory.getLogger(CompareRestController.class);
	
	@Autowired
	protected CompareDAO compareDAO;
	
	
	@PostMapping(value = "/ComparegetByKeyword")
	public ResponseEntity comparegetByKeyword(@RequestBody ComparegetByKeywordParam param) {
		

		log.info("============= Start API ComparegetByKeyword ================");
		MessageResponse msg = new MessageResponse();
		List<Compare> res = null;
		Boolean checkType = true;
		try {
			
			res = compareDAO.getComparegetByKeyword(param.getTextsearch() != null ? param.getTextsearch() : "");
		
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
			
		}
		log.info("============= End API ComparegetByKeyword =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ComparegetByConAdv")
	public ResponseEntity comparegetByConAdv(@RequestBody ComparegetByConAdvParam param) {
		
		log.info("============= Start API ComparegetByConAdv ================");
		MessageResponse msg = new MessageResponse();
		List<Compare> res = null;
		Boolean checkType = true;
		try {
			res = compareDAO.getComparegetByConAdv(param);
		} catch (Exception e) {
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
		}
		log.info("============= End API ComparegetByConAdv =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/ComparegetByCon")
	public ResponseEntity comparegetByCon(@RequestBody ComparegetByConParam param) {
		

		log.info("============= Start API ComparegetByCon ================");
		MessageResponse msg = new MessageResponse();
		List<Compare> res = null;
		Boolean checkType = true;
		try {
			res = compareDAO.getComparegetByCon(param);
		} catch (Exception e) {
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
		}
		log.info("============= End API ComparegetByCon =================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/CompareinsAll")
	public ResponseEntity compareinsAll(@RequestBody CompareParam param) {
		
		log.info("============= Start API CompareinsAll ================");
		MessageResponse msg = new MessageResponse();
		try {
			if(compareDAO.saveCompareinsAll(param)){
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
		log.info("============= End API CompareinsAll =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/CompareupdByCon")
	public ResponseEntity compareupdByCon(@RequestBody CompareupdByConParam param) {
		
		log.info("============= Start API CompareupdByCon ================");
		MessageResponse msg = new MessageResponse();
		try {
			if(compareDAO.updateCompareupdByCon(param)){
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
		log.info("============= End API CompareupdByCon =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/CompareupdDelete")
	public ResponseEntity compareupdDelete(@RequestBody CompareupdDeleteParam param) {
		
		log.info("============= Start API CompareupdDelete ================");
		MessageResponse msg = new MessageResponse();
		try {
			if(compareDAO.deleteCompareupdDelete(param.getCompareID())){
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
		log.info("============= End API CompareupdDelete =================");
		return new ResponseEntity(msg, HttpStatus.OK);
	}
	
	
	

}