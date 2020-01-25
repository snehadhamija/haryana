/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.stanzaliving.api.constants.Constants;

/**
 * @author nipunaggarwal
 *
 */
public class LogsFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LogsFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String guid = UUID.randomUUID().toString().replace("-", ""); // globally unique identifier
		String luid = UUID.randomUUID().toString().replace("-", ""); // locally unique identifier

		Cookie customCookie = null;
		String customCookieValue = null;

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			guid = (null != httpRequest.getHeader(Constants.GUID)) ? httpRequest.getHeader(Constants.GUID) : guid;

			Cookie[] cookies = httpRequest.getCookies();
			Stream<Cookie> stream =
					Objects.nonNull(cookies)
							? Arrays.stream(cookies)
							: Stream.empty();
			customCookie =
					stream
							.filter(cookie -> "vCareToken".equals(cookie.getName()))
							.findFirst()
							.orElse(new Cookie("vCareToken", guid));

			customCookieValue = customCookie.getValue();
			System.out.println("cookiesList: " + customCookieValue);
		}

		MDC.put(Constants.GUID, guid);
		MDC.put(Constants.LUID, luid);
		if (Objects.nonNull(customCookieValue)) {
			MDC.put(Constants.vCareToken, customCookieValue);
		}

		if (request instanceof HttpServletRequest) {
			logger.info("RequestReceived URI " + ((HttpServletRequest) request).getRequestURI() +
					" QueryString " + ((HttpServletRequest) request).getQueryString() +
					" App Version " + ((HttpServletRequest) request).getHeader("appversion"));
		}

		request.setAttribute(Constants.GUID, guid);
		if (Objects.nonNull(customCookieValue)) {
			request.setAttribute(Constants.vCareToken, customCookieValue);
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.addCookie(customCookie);
		}
		chain.doFilter(request, response);

		if (response instanceof HttpServletResponse) {
			logger.info("ResponseSent Code " + ((HttpServletResponse) response).getStatus());
		}
		MDC.remove(Constants.GUID);
		MDC.remove(Constants.LUID);
		MDC.remove(Constants.vCareToken);
	}

	@Override
	public void destroy() {

	}
}
