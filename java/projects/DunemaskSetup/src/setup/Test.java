package setup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dunemask.dm.CMD;
import dunemask.dunemasking.GitHub;
import dunemask.dunemasking.Setup;
import dunemask.util.FileUtil;
import dunemask.util.JarUtil;
import dunemask.util.RW;

public class Test {
	public static final String jarName="Run";
	static ArrayList<File> versions = new ArrayList<File>();
	public static void main(String[] args) {
		String vers = JOptionPane.showInputDialog("Please Specify what Dunemasking Version You wish to download (double version only)");
		versions.add(GitHub.gitFile("dunemask.github.io", "dunemasking/libraries/Dunemasking"+vers+".jar"));
	
	}
	private static void install() {
		String top = FileUtil.fixSpaces(dunemask.dunemasking.Setup.init(Setup.autoHandleSetup, jarName, "cookie")).replaceAll("%20", " ");
		int c = JOptionPane.showConfirmDialog(null, "The Installation Of Dunemasking Will now Begin, Admin Rights/Interweb Required, Proceed?");
		switch (c) {
		case 1:System.exit(0);
			break;
		case 2: System.exit(0);
			break;
		
		}
		File l = GitHub.gitFile("dunemask.github.io", "java/dunemask_libraries/Dunemasking3.86.jar");
		System.out.println("Jar:"+JarUtil.isJar(jarName));
		File[] subs = FileUtil.getAllSubFiles(new File(top+"export\\"));
		System.out.println("top:"+top);
		String programFilesDir = System.getenv("ProgramFiles")+"\\";
		String dunemaskingPath = programFilesDir+"Dunemasking\\";
		String mkdirCommand = "mkdir ";
		String command =mkdirCommand+"\""+dunemaskingPath+"\"";
		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add(command);
		for(File f: subs) {
			if(f.isDirectory()) {
				cmds.add(CMD.createDirCmd(dunemaskingPath+f.getName()));
			}else {
				cmds.add(CMD.copyFileViaCmd(f, new File(dunemaskingPath+f.getAbsolutePath().replace(top+"export\\", ""))));
			}
		}
		cmds.add(CMD.copyFileViaCmd(l,new File(dunemaskingPath+l.getName())));
		//cmds.add("pause");
		try {
			File helloWorld = File.createTempFile("HelloWorld", ".java");
			RW.write(helloWorld,new String[] 
					{"public class HelloWorld {",
					 "	public static void main(String[] args){",
					 "  System.out.println(\"HelloWorld!\");}}"
					},0);
			//cmds.add(CMD.copyFileViaCmd(helloWorld, new File(dunemaskingPath+"HelloWorld.java")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		copyAndClean(cmds, top);
		JOptionPane.showMessageDialog(null, "Thanks for Installing Dunemasking!");
	
	}
	/**
	 * 
	 */
	private static void copyAndClean(ArrayList<String> cmds,String top) {
		CMD.openElevatedCmd(cmds);
		if(JarUtil.isJar(jarName)) {
			System.out.println("Cookie!");
			FileUtil.deleteAllSubFolders(new File(top));
			System.out.println("top "+top);
		}
		
	}
	


}
