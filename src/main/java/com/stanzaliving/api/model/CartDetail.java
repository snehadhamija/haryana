/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "CART_DETAIL")
public class CartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_DETAIL_ID")
	private int cartDetailId;

	@Column(name = "TOKEN", nullable = false)
	private String token;

	@Column(name = "CART_DETAIL", nullable = false, columnDefinition = "LONGTEXT")
	private String cartDetail;

	public int getCartDetailId() {
		return cartDetailId;
	}

	public void setCartDetailId(int cartDetailId) {
		this.cartDetailId = cartDetailId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCartDetail() {
		return cartDetail;
	}

	public void setCartDetail(String cartDetail) {
		this.cartDetail = cartDetail;
	}

	@Override
	public String toString() {
		return "CartDetail "
				+ "[cartDetailId=" + cartDetailId + ", "
				+ "token=" + token + ", "
				+ "cartDetail=" + cartDetail + "]";
	}

}
