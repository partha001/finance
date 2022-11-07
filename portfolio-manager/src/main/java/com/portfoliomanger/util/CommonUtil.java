package com.portfoliomanger.util;

import com.google.common.base.Strings;

public class CommonUtil {
	
	public static Double parseDouble(String input) {
		Double result;
		if(Strings.isNullOrEmpty(input)) {
			result = null;
		}else {
			result = new Double(input);
		}
		return result;
	}

}
