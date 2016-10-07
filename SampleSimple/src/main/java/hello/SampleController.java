package hello;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class SampleController {
	
	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "Hello World";
	}
	
	public static void main(String[] args) throws Exception {
//        SpringApplication.run(SampleController.class, args);
		SpringApplication app = new SpringApplication(SampleController.class);
	    app.setBannerMode(Mode.OFF);
	    app.run(args);
    }
}
