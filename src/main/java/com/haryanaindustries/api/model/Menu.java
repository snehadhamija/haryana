/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.model;

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
@Table(name = "menu")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "menu", nullable = false)
	private String menu;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	@Column(name = "sequence_id", nullable = false)
	private int sequenceId = 0;

	@Column(name = "img_url", nullable = true)
	private String imgurl;
}
