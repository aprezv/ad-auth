package com.cloudcatalogs.adauth.config;

import com.cloudcatalogs.userauthentication.entities.User;
import com.cloudcatalogs.userauthentication.repositories.RoleRepository;
import com.cloudcatalogs.userauthentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

/**
 * Created on 2019-02-21.
 */
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private final AuthorizationServerTokenServices defaultTokenServices;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${security.oauth2.client.registered-redirect-uri}")
    private String redirectUri;

    @Value("${authentication.client.id}")
    private String CLIENT_ID;

    @Value("${authentication.message.user-disabled}")
    private String USER_DISABLED_MESSAGE;

    @Autowired
    public OAuth2AuthenticationSuccessHandler(AuthorizationServerTokenServices defaultTokenServices,
                                              UserRepository userRepository, RoleRepository roleRepository) {
        this.defaultTokenServices = defaultTokenServices;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        System.out.println(authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        User user = loadOrCreateUser((User) authentication.getPrincipal());

        if (!user.getActive()) {
            final String redirectErrorUri = createRedirectErrorUri(
                    USER_DISABLED_MESSAGE,
                    OAuth2Exception.INVALID_GRANT,
                    HttpStatus.FORBIDDEN.getReasonPhrase());

            getRedirectStrategy().sendRedirect(request, response, redirectErrorUri);
            return;
        }

        OAuth2Request original = ((OAuth2Authentication) authentication).getOAuth2Request();
        OAuth2Request oAuth2Request = new OAuth2Request(null, this.CLIENT_ID, null, true, null, null, original.getRedirectUri(), null, null);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "N/A", user.getAuthorities());

        String targetUrl = determineTargetUrl(new OAuth2Authentication(oAuth2Request, auth), request.getSession().getId());

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String createRedirectErrorUri(final String errorDescription, final String errorType, final String error) {

        return UriComponentsBuilder.fromUriString(redirectUri)
                .queryParam("error_description", errorDescription)
                .queryParam("error_type", errorType)
                .queryParam("error", error)
                .build().toUriString();
    }

    protected String determineTargetUrl(Authentication authentication, String sessionId) {


        OAuth2AccessToken token = defaultTokenServices.createAccessToken((OAuth2Authentication) authentication);


        return UriComponentsBuilder.fromUriString(redirectUri)
                .queryParam("token", token.getValue())
                //.queryParam("refresh_token", token.getRefreshToken().getValue())
                .queryParam("session_id", sessionId)
                .build().toUriString();
    }

    @Transactional
    User loadOrCreateUser(User user) {

        Optional<User> currentUser = userRepository.findByUsername(user.getUsername());

        if (currentUser.isPresent()) {

            return currentUser.get();
        }

        if(!CollectionUtils.isEmpty(user.getRoles())) {
            roleRepository.saveAll(user.getRoles());
        }

        user.setVerified(true);
        user.setActive(true);
        user.setChangePasswordAtNextLogon(false);
        user = userRepository.save(user);

        userRepository.save(user);

        return user;
    }

}
