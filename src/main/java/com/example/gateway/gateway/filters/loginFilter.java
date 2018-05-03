package com.example.gateway.gateway.filters;

import com.example.gateway.gateway.AuthorizationService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class loginFilter extends ZuulFilter {

    private AuthorizationService authorizationService;

    @Autowired
    public loginFilter(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = authorizationService.getAuthorizationToken();
        if (token.isEmpty()) {
            ctx.setSendZuulResponse( false );
            ctx.setResponseStatusCode( 401 );

        } else {
            ctx.addZuulRequestHeader("Authorization", token);
        }
//        if (ctx.getRequest().getContextPath().equalsIgnoreCase( "sse" )) {
//            ctx.addZuulRequestHeader( "OpCode", "sse" );
//        }
        return null;
    }
}
