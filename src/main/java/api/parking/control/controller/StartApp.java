package api.parking.control.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parking")		
public class StartApp {
	
	public String hello() {
		return "DIO Parking Control";
	}
}
