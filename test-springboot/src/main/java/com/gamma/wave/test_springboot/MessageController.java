package com.gamma.wave.test_springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	
	@RequestMapping(value="api/messages",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<List<Message>> getMessages(){
		List<Message> messages=new ArrayList<Message>(){
			{
				add(new Message(1,"Ciao"));
				add(new Message(2,"Ciao again"));
			}
		};
		return new ResponseEntity<List<Message>>(messages,HttpStatus.OK);
	}
	@RequestMapping(value="api/messages/{id}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> getMessage(@PathVariable("id") Integer id){
		if(id!=1){
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
		else{
			return new ResponseEntity<Message>(new Message(1,"I'm Java Boss"),HttpStatus.OK);
		}
	}
	
}
