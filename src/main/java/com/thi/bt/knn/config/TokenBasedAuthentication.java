package com.thi.bt.knn.config;

import com.thi.bt.knn.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;


/**
 * TODO: write you class description here
 *
 * @author
 */


public class TokenBasedAuthentication extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 1L;
    private Object credentials;
    private final User principal;
    private boolean authenticated = false;

    public TokenBasedAuthentication(User principal ) {
        super(principal.getAuthorities());
        this.principal = principal;
    }

    public TokenBasedAuthentication(User principal, Object credentials) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.credentials = credentials;
        this.authenticated = false;
    }

    public TokenBasedAuthentication(User principal, boolean authenticated) {
        super( principal.getAuthorities() );
        this.principal = principal;
        this.authenticated = authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public User getPrincipal() {
        return this.principal;
    }

}
