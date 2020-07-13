package com.zwh.firstsc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);
    /**
     * per：路由之前，如实现认证、记录调试信息等
     * routing：路由时
     * post：路由后，比如添加HTTP Header
     * error：发生错误时调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 定义filter的顺序，数字越小表示顺序越高，越先执行
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 表示是否需要执行该filter，true表示执行，false表示不执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info(" ------------ token filter");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String token = request.getHeader("token");

        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        //登陆校验逻辑
        if (StringUtils.isBlank(token)) {
//            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
