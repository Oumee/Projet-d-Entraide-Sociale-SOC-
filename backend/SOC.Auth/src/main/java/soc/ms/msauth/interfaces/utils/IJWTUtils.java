package soc.ms.msauth.interfaces.utils;

public interface IJWTUtils {
    String createToken(JWTUser user);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
    void invalidateToken(String token);
}
