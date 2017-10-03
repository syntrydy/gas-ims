package cm.gasmyr.app.ims.util;

import java.security.Principal;

public class Utils {

	public static String getUserName(Principal principal) {
		return principal.getName().split("\\@")[0].toUpperCase();
	}

	public static  boolean isValid(String value) {
		return value != null && value != "";
	}
	
	public static  boolean isValid(Object value) {
		return value != null && value != "";
	}

}
