/**
 * 
 */
package player;

import java.io.IOException;
import java.io.InputStream;

import dunemask.util.FileUtil;

/**
 * @author Elijah
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String desktop = System.getProperty("user.home")+"/Desktop/";
		String cmd = "java -jar \""+FileUtil.filePathFixReverse(desktop)+"PlayVideo.jar\"";
		System.out.println(cmd);
		Runtime.getRuntime().exec(cmd);

	}

}
