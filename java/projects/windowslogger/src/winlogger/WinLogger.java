/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * File: winlogger.WinLogger.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 * <p>Belongs to Package {@link winlogger }</p>
 */
package winlogger;

import java.io.File;
import java.util.Random;

import javax.swing.JOptionPane;

import dunemask.objects.Expression;
import dunemask.other.StringBasedEncryption;
import dunemask.util.FileUtil;
import dunemask.util.ObjectUtil;
import dunemask.util.RW;

/**
 * @author Elijah
 *  <p>Belongs to Package {@link winlogger }</p>
 */
public class WinLogger {
	static File input = FileUtil.getResource("winlogger/WindowsKeys.txt");
	//static File output = new File("bin/winlogger/output.txt");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//createFolders();
		//writeActualKey();
		//reset();
		String keyCode = JOptionPane.showInputDialog("GIVE MEH PASSCODE!\r\n (Result Saved To Desktop)");
		Expression key = (Expression) ObjectUtil.readObject(FileUtil.getResource("resources/"+keyCode+"/key.DMEK"));
		String[] lines = RW.readAll(input);
		String[] newLines = new String[lines.length];
		for(int i=0;i<lines.length;i++) {
			newLines[i] = StringBasedEncryption.decrypt(key, lines[i], key.getVari());
			
		}
		File winLogger = new File(System.getProperty("user.home") + "/Desktop/WinLogger.txt");
		RW.write(winLogger, newLines, 1);
		
	}
	
	



	public static void createFolders() {
	 int passLength = 8;
	 Random r = new Random();
		String[] orig = new String[12];
		String alphab = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String character;
	 //	File tmp = new File("src/resources/"+character+"/");
			for( int a=0; a< alphab.length()*58;a++) {
				character = "";
				for(int i=0;i<passLength;i++) {
					int charatindex = r.nextInt(alphab.length());
					character+= alphab.charAt(charatindex);
					
				}
				File folder = new File("bin/resources/"+character+"/");
				File file = new File("bin/resources/"+character+"/key.DMEK");
				ObjectUtil.writeObject(file,StringBasedEncryption.RandomEncryptionKeyCode(3) );
				
				
			}
		
		
		
		
	}
	
	

}
