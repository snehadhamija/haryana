/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_CATEGORY_ID")
	private int productCategoryId;

	@Column(name = "PRODUCT_CATEGORY_NAME", nullable = false)
	private String productCategoryName;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = true;

	@Column(name = "SEQUENCE_ID", nullable = false)
	private int sequenceId = 0;

	@Column(name = "IMG_URL", nullable = true)
	private String imgurl;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PRODUCT_CATEGORY_PRODUCT", joinColumns = { @JoinColumn(name = "PRODUCT_CATEGORY_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PRODUCT_ID") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Product> products = new HashSet<Product>();

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ProductCategory "
				+ "[productCategoryId=" + productCategoryId + ", "
				+ "productCategoryName=" + productCategoryName + ", "
				+ "isActive=" + isActive + ", "
				+ "sequenceId=" + sequenceId + ", "
				+ "imgurl=" + imgurl + ", "
				+ "products=" + products + "]";
	}

}
