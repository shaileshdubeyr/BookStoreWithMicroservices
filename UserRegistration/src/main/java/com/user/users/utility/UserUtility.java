package com.user.users.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Service;

@Service
public class UserUtility {

    public static final String TOKEN_SECRET = "shailesh";

    public String createToken(int id) {

        // We create a token using the HMAC256 algorithm and store the id as claim.
        Algorithm algo = Algorithm.HMAC256(TOKEN_SECRET);

        String token = JWT.create().withClaim("id_key", id).sign(algo);
        return token;
    }

    public int decodeToken(String token) throws SignatureVerificationException {

        // We specify the algorithm for the verifier here and then build the verifier in the next step
        Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        JWTVerifier jwtVerifier = verification.build();
        System.out.println(jwtVerifier.toString());
        // We verify and decode the token using the verifier. If the token is incorrect it will throw an exception.
        DecodedJWT decodedJWT =  jwtVerifier.verify(token);
        // We extract the claim fr
        // We extract the claim from the decoded token and the convert the claim to int type. We then return this id.
        Claim idClaim = decodedJWT.getClaim("id_key");
        int id = idClaim.asInt();
        return id;
    }

}
