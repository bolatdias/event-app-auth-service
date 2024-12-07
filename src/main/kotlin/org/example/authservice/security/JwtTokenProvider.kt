package org.example.authservice.security


import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtTokenProvider {
    @Value("\${app.jwtSecret}")
    private val jwtSecret: String? = null

    @Value("\${app.jwtExpirationInMs}")
    private val jwtExpirationInMs = 0

    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal

        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)

        return Jwts.builder()
                .subject(userPrincipal.id.toString())
                .issuedAt(Date()).expiration(expiryDate)
                .signWith(getSecretKey(jwtSecret), Jwts.SIG.HS512).compact()
    }

    fun getUserIdFromJWT(token: String?): Long {
        val claims = Jwts.parser().verifyWith(getSecretKey(jwtSecret)).build().parseSignedClaims(token).payload

        return claims.subject.toLong()
    }

    fun validateToken(authToken: String?): Boolean {
        try {
            Jwts.parser().verifyWith(getSecretKey(jwtSecret)).build().parseSignedClaims(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }
        return false
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }

}

fun getSecretKey(jwtSecret: String?): SecretKey {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))
}