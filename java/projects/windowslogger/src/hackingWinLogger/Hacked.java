/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * File: hackingWinLogger.Hacked.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 * <p>Belongs to Package {@link hackingWinLogger }</p>
 */
package hackingWinLogger;

import java.io.File;
import java.util.ArrayList;

import dunemask.objects.Expression;
import dunemask.other.StringBasedEncryption;
import dunemask.util.FileUtil;
import dunemask.util.ObjectUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

/**
 * @author Elijah
 *  <p>Belongs to Package {@link hackingWinLogger }</p>
 */
public class Hacked {
	static File input = FileUtil.getResource("winlogger/WindowsKeys.txt");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File desktop = new File(System.getProperty("user.home") + "/Desktop/");
		desktop.mkdir();
		
		File[] files = FileUtil.getAllSubFiles(new File("bin/resources/"));
		Expression neededKey = null;
		
		for(int i=0;i<files.length;i++) {
			if(files[i].getName().length()<9&&files[i].isDirectory()) {
				System.out.println(files[i]);
				File keyFile = new File(files[i].toString()+"\\key.DMEK");
				Expression key = (Expression) ObjectUtil.readObject(keyFile);
				
				String[] lines = RW.readAll(input);
				for(int c=0;c<lines.length;c++) {
					String text = StringBasedEncryption.decrypt(key, lines[c], key.getVari());
					if(StringUtil.containsIgnoreCase(text, "windows")) {
						neededKey = (Expression) ObjectUtil.readObject(keyFile);
						System.out.println("Found in:"+keyFile);
						i=files.length;
						c = lines.length;
					}
				}
					System.out.println("Not FOund in:"+keyFile);
				
	
			}	
		
		}
		
		
		File az = new File(System.getProperty("user.home") + "/Desktop/Found.txt");
		String[] lines = RW.readAll(input);
		for(int i=0;i<lines.length;i++) {
			String text = StringBasedEncryption.decrypt(neededKey, lines[i], neededKey.getVari());
			lines[i] = text;
		}
		RW.write(az, lines, 1);

	}

}
