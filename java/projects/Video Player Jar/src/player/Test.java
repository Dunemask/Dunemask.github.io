/**
 * 
 */
package player;

import java.io.File;
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
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		String desktop = System.getProperty("user.home")+"/Desktop/";
		File song = FileUtil.getResource("resources/tsfh.mp3");
		String cmd = "java -jar \""+FileUtil.filePathFixReverse(desktop)+"PlaySong.jar\" "+FileUtil.fixSpaces(song.getAbsolutePath())+" true 0";
		//String cmd = "java -jar \"C:\\Users\\dunemask\\Desktop\\PlaySong.jar\" C:\\Users\\dunemask\\Documents\\GitHub\\dunemask.github.io\\java\\projects\\Video%20Player%20Jar\\bin\\resources\\tsfh.mp3 true 5000";
		// Run a java app in a separate system process
        Process ps=Runtime.getRuntime().exec(cmd);
        ps.waitFor();
        java.io.InputStream is=ps.getInputStream();
        byte b[]=new byte[is.available()];
        is.read(b,0,b.length);
        System.out.println(new String(b));
    
	}

}
