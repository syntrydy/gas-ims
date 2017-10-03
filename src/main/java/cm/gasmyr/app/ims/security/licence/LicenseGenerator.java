package cm.gasmyr.app.ims.security.licence;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LicenseGenerator {

	public static String FILE_SEPARATOR = System.getProperty("file.separator");
	public static String LICENSE_FILE_NAME = "app.dl";

	public static boolean createLicenseFile() {
		File file = new File(LICENSE_FILE_NAME);
		try {
			if (file.exists()) {
				file.delete();
			}

			boolean created = file.createNewFile();
			return created;
		} catch (IOException e) {
			return false;
		}
	}
	
	public static void readApplicationLicense(){
		try {
            FileReader reader = new FileReader(LICENSE_FILE_NAME);
            int character;
 
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
