/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.restController;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.StringUtils;
import com.vcare.api.model.CartDetail;
import com.vcare.api.service.CartDetailService;
import com.vcare.api.util.CookieUtil;

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

	@Autowired
	CartDetailService cartDetailService;

	// ----- get cart detail -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findCartDetails() {
		String cookie = cookieUtil.getCookieListFromFilter();
		if (!StringUtils.isNullOrEmpty(cookie)) {
			CartDetail cartDetail = cartDetailService.findCartDetailsForToken(cookie);
			return Objects.nonNull(cartDetail)
					? new ResponseEntity<Object>(cartDetail, HttpStatus.OK)
					: new ResponseEntity<Object>("No cart-details found!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Object>("No cookie found for current user!", HttpStatus.NOT_FOUND);
	}

}
