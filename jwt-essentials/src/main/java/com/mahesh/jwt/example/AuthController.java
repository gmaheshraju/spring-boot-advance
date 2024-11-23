package com.mahesh.jwt.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.auth.AuthRequest;
import com.mahesh.auth.AuthResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	JWTUtils jwtUtil;

	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authReqest) {

		if ("mahesh".equals(authReqest.getUserName()) && "password".equals(authReqest.getPassword())) {

			String token = jwtUtil.generateToken(authReqest.getUserName());
			return ResponseEntity.ok(new AuthResponse(token));
		} else {
			return ResponseEntity.status(401).body(null);
		}

	}

	
	@GetMapping("/validate")
	public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String header) {

		if (header == null && !header.startsWith("Bearer ")) {
			return ResponseEntity.status(400).body("Invalid Authorization Header");
		}

		String token = header.substring(7);
		if (jwtUtil.validateToken(token)) {

			String userId = jwtUtil.extractUserId(token);
			return ResponseEntity.ok().body("Token is Vlaid UserId:" + userId);

		} else {
			return ResponseEntity.status(401).body("Invalid Token");
		}

	}

}
