package net.endpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import net.endpoint.dto.account.EmailDto;
import net.endpoint.service.email.EmailService;


@RestController
public class EmailController extends MainController {

	
	@Autowired
	EmailService emailService;
	
	public List<EmailDto> getAll(){

		return null;
	}
	
}
