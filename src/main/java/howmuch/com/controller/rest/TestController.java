package howmuch.com.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {
	
	@GetMapping("/hello")
	public String Test() {
		return "TEST API";
	}
}
