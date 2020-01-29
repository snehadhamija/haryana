/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.util.StringUtils;
import com.vcare.api.dto.FAQResponseDTO;
import com.vcare.api.model.FAQ;
import com.vcare.api.service.FAQService;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class FAQUtil {

	@Autowired
	private FAQService faqService;

	public Comparator<FAQResponseDTO> compareBySequenceId() {
		return (FAQResponseDTO dto1, FAQResponseDTO dto2) -> dto1.getSequenceId().compareTo(dto2.getSequenceId());
	}

	public FAQResponseDTO convertFAQEntityToFAQResponseDto(FAQ faq) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(faq, FAQResponseDTO.class);
	}

	public List<FAQResponseDTO> getSortedFAQResponseDtoList(List<FAQ> faqs) {
		List<FAQResponseDTO> faqResponseDTOs =
				faqs
						.stream()
						.map(faq -> convertFAQEntityToFAQResponseDto(faq))
						.collect(Collectors.toList());
		Collections.sort(faqResponseDTOs, compareBySequenceId());
		return faqResponseDTOs;
	}

	public FAQ getSubDisease(Integer faqId) {
		FAQ faq = faqService.findById(faqId);
		return Objects.isNull(faq) ? null : faq;
	}
	
	public FAQ saveFaq(String question) {
		FAQ faq = new FAQ(question);
		faqService.save(faq);
		return faq;
	}

	public FAQ getFaqForIdOrQuestion(Integer id, String question) {
		FAQ faq = null;
		if (Objects.isNull(id)) {
			if (!StringUtils.isNullOrEmpty(question)) {
				faq = saveFaq(question);
			}
		} else {
			faq = faqService.findById(id);
		}
		return faq;
	}
}
