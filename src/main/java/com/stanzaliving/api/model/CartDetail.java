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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "CART_DETAIL")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_DETAIL_ID")
	private int cartDetailId;

	@Column(name = "TOKEN", nullable = false)
	private String token;

	@Column(name = "CART_DETAIL", nullable = false, columnDefinition = "LONGTEXT")
	private String cartDetail;

}
