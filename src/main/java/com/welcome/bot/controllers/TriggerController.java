package com.welcome.bot.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.welcome.bot.domain.Trigger;
import com.welcome.bot.models.TriggerContentDTO;
import com.welcome.bot.models.TriggerDTO;
import com.welcome.bot.services.TriggerService;


@RestController
public class TriggerController {
	
	@Autowired
	TriggerService triggerService;
	
	//get trigger
	@GetMapping("/api/triggers/{triggerId}")
	public TriggerContentDTO getTrigger(@PathVariable Integer triggerId) {
		return triggerService.getTrigger(triggerId);
	}
	
	//get all triggers
	@GetMapping("/api/triggers")
	public Page<TriggerContentDTO> getAllTriggers(Pageable pageable){
		return triggerService.getAllTriggers(pageable);
	}
	
	//get trigger by Message
	@GetMapping("/triggersByMessage/{message_id}")
	public List<Trigger> getTriggerByMessage(@PathVariable Integer messageId){
		return triggerService.getTriggerByMessage(messageId);
	}
	
	//create trigger
	@PostMapping("/api/triggers")
	public TriggerContentDTO createTrigger(@RequestBody TriggerDTO triggerModel) {
		return triggerService.createTrigger(triggerModel);		
	}
	
	@PutMapping("/api/triggers/{triggerId}")
	public TriggerContentDTO updateTrigger(@PathVariable Integer triggerId, @RequestBody TriggerDTO triggerModel) {
		return triggerService.updateTrigger(triggerId, triggerModel);
	}
	
	@DeleteMapping("/api/triggers/{triggerId}")
	public ResponseEntity<Trigger> deleteTrigger(@PathVariable Integer triggerId) {
		return triggerService.deleteTrigger(triggerId);
	}
}
	