/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.util.CollectionUtils;
import com.stanzaliving.api.model.ProductVariant;
import com.stanzaliving.api.service.ProductVariantService;
import com.stanzaliving.api.util.ProductVariantUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/productVariant"
})
public class ProductVariantRestController {

	@Autowired
	private ProductVariantUtil productVariantUtil;

	@Autowired
	private ProductVariantService productVariantService;

	// ----- Get product variants -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllproductVariants(
			@RequestParam(value = "productId", required = false) Integer productId) {
		List<ProductVariant> productVariants = productVariantService.findProductVariantsForProduct(productId);
		return !CollectionUtils.isNullOrEmpty(productVariants)
				? new ResponseEntity<Object>(productVariantUtil.getSortedProductVariantResponseDtoList(productVariants), HttpStatus.OK)
				: new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
