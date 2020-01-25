/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.util.CookieUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/cartDetail"
})
public class CartDetailRestController {

	@Autowired
	CookieUtil cookieUtil;

	// ----- get cart detail -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findCartDetails() {
		String cookies = cookieUtil.getCookieListFromFilter();
		if (Objects.nonNull(cookies)) {
			List<String> cookieList =
					Stream.of(
							cookies.split(","))
							.collect(Collectors.toList());
			if(cookieList.contains("JSESSIONID")){
				String sessionId;
			}
		}
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

}
