/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.restController;

import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stanzaliving.api.model.ProductCategory;
import com.stanzaliving.api.util.ProductCategoryUtil;

/**
 * @author nipunaggarwal
 *
 */
@RestController
@RequestMapping(value = {
		"/productCategory"
})
public class ProductCategoryRestController {

	@Autowired
	private ProductCategoryUtil productCategoryUtil;

	// ----- Get product categories -----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Object> findAllproductCategories(
			@RequestParam(value = "diseaseId", required = false) Integer diseaseId) {
		Set<ProductCategory> productCategories = productCategoryUtil.getProductCategorySet(diseaseId);
		return Objects.isNull(productCategories)
				? new ResponseEntity<Object>("Disease with id: " + diseaseId + " not found!", HttpStatus.NOT_FOUND)
				: productCategories.isEmpty()
						? new ResponseEntity<Object>("No product categories found!", HttpStatus.NO_CONTENT)
						: new ResponseEntity<Object>(productCategoryUtil.getSortedProductCategoryResponseDtoList(productCategories), HttpStatus.OK);
	}
}
