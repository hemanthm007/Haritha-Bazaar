package security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter {
    @Override
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Collection<GrantedAuthority> defaultAuthorities = super.extractAuthorities(jwt);
        List<String> roles = jwt.getClaimAsStringList("role");

        if (roles == null) return defaultAuthorities;

        return roles.stream()
            .map(role -> (GrantedAuthority) () -> "ROLE_" + role.toUpperCase())
            .collect(Collectors.toSet());
    }
}
