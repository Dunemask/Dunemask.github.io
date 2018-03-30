/**
 * 
 */
package setup;

import java.io.IOException;

import dunemask.util.FileUtil;

/**
 * @author Elijah
 *
 */
public class RunApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String desktop = System.getProperty("user.home")+"/Desktop/";
		String cmd = "java -jar \""+FileUtil.filePathFixReverse(desktop)+"PlayVideo.jar\"";
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
