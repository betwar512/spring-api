package net.endpoint.config.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component(value="CORSFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

	private String getUrl(ServletRequest request) {
	String result = ((HttpServletRequest)request).getHeader("Referer");
		return result != null && !result.isEmpty() ? result.substring(0, result.length() -1) : "https://www.betwarendpoint.net";	 
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtering on...........................................................");
		
		String string = getUrl(req);
		System.out.println(string);
		HttpServletResponse response = (HttpServletResponse) res;
		  HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", string );
        response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Credentials,X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
	}
	


	 @Override
	public void init(FilterConfig filterConfig) {}
	 @Override
	public void destroy() {}

}
