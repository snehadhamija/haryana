/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.dto;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotBlank;

import com.haryanaindustries.api.Enum.ContactMode;
import com.haryanaindustries.api.Enum.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nipunaggarwal
 *
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AskAQueryRequestDTO {

	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@NotBlank(message = "Mobile number is mandatory")
	private String mobileNumber;

	@NotBlank(message = "Email is mandatory")
	private String email;

	@Column(name = "question", columnDefinition = "LONGTEXT")
	private String question;

	private String lastName;
	private Gender gender;
	private Integer faqId;
	private ContactMode contactMode;

}
