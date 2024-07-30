package wmcommon.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
	
	
	public static Double roundDouble(Double d) {
		if(d==null)
			return 0.0;
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

}
