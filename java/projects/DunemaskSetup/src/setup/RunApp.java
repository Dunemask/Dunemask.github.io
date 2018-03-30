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
		//String filePath = new File(desktop+"tsfh.mp3").getAbsolutePath();
		String filePath = FileUtil.getResource("resources/tsfh.mp3").getAbsolutePath();
		String cmd = "java -jar \""+FileUtil.filePathFixReverse(desktop)+"PlayVideo.jar\" "+filePath+"";
		System.out.println("cmd:"+cmd);
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
