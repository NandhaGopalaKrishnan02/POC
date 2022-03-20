package com.connectionHandlerService.JWTUtility;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JWTUtility  {
	
	private  static String SECRET_KEY = "SecretPOCkey";
	private  static Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
	private static String authToken="";
	
	public  static DecodedJWT verifyToken(String token) {
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	
	
	
}