/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.util;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.stanzaliving.api.constants.Constants;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class CookieUtil {

	public String getCookieListFromFilter() {
		return !StringUtils.isEmpty(MDC.get(Constants.COOKIE_LIST))
				? MDC.get(Constants.COOKIE_LIST)
				: null;
	}

	public Cookie[] readCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		return Objects.nonNull(cookies) ? cookies : null;
	}

	public String convertConvertCookieListToString(Cookie[] cookies) {
		return Arrays
				.stream(cookies)
				.map(c -> c.getName() + "=" + c.getValue())
				.collect(Collectors.joining(", "));
	}

}
