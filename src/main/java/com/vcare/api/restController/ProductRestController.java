/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.restController;

import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcare.api.model.Product;
import com.vcare.api.util.ProductUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/product"
})
public class ProductRestController {

	@Autowired
	private ProductUtil productUtil;

	// ----- Get products -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllProducts(
			@RequestParam(value = "categoryId", required = false) Integer categoryId) {
		Set<Product> products = productUtil.getProductSet(categoryId);
		return Objects.isNull(products)
				? new ResponseEntity<Object>("Category with id: " + categoryId + " not found!", HttpStatus.NOT_FOUND)
				: products.isEmpty()
						? new ResponseEntity<Object>("No products found!", HttpStatus.NO_CONTENT)
						: new ResponseEntity<Object>(productUtil.getSortedProductResponseDtoList(products), HttpStatus.OK);
	}

}
