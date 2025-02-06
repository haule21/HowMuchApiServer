package howmuch.com.controller.rest;

import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.util.Resource;

@RestController
public class TestController {
	
//	@GetMapping("/.well-known/pki-validation/644EDE70F553E652097133570ABC12B4.txt")
//	public ResponseEntity<ClassPathResource> Test() {
//		// 텍스트 파일의 경로 (예: src/main/resources에 위치한 myfile.txt)
//        ClassPathResource resource = new ClassPathResource("classpath:/.well-known/pki-validation/644EDE70F553E652097133570ABC12B4.txt");
//
//        // 파일을 반환하는 ResponseEntity 설정
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=myfile.txt")
//                .contentType(MediaType.TEXT_PLAIN)
//                .body(resource);
//	}
}
