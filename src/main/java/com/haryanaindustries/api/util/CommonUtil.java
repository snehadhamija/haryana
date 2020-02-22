/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.amazonaws.util.CollectionUtils;

/**
 * @author nipunaggarwal
 *
 */
@PropertySource({
		"classpath:system.properties"
})
@Component
public class CommonUtil {

	@Autowired
	private Environment environment;

	public boolean isProdEnv() {
		String isProdActivityEligibile = getEnvironmentType();
		return (Objects.nonNull(isProdActivityEligibile) && isProdActivityEligibile.equalsIgnoreCase("prod"));
	}

	private String getEnvironmentType() {
		return environment.getProperty("system.type");
	}

	public StringBuilder convertListToDelimiterSeperatedString(List<?> objects, String delimiter) {
		StringBuilder objectIdsdDelimiterSeperated = new StringBuilder();
		if (!CollectionUtils.isNullOrEmpty(objects)) {
			objects
					.stream()
					.forEach(id -> {
						objectIdsdDelimiterSeperated.append(id).append(delimiter);
					});
			objectIdsdDelimiterSeperated.deleteCharAt(objectIdsdDelimiterSeperated.lastIndexOf(delimiter));
		}
		return objectIdsdDelimiterSeperated;
	}

	public List<String> convertDelimiterSeperatedStringToList(Object object, String delimiter) {
		return Stream
				.of(object.toString().split(delimiter))
				.collect(Collectors.toList());
	}

	public boolean isStringPresentInList(String string, List<String> stringList) {
		for (String s : stringList) {
			if (s.toLowerCase().equals(string.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
