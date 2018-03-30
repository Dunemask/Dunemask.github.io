package setup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dunemask.dm.CMD;
import dunemask.dunemasking.GitHub;
import dunemask.dunemasking.Setup;
import dunemask.util.FileUtil;
import dunemask.util.JarUtil;
import dunemask.util.RW;
import dunemask.util.StringUtil;

public class Test {
	public static final String jarName="Run";
	static ArrayList<File> versions = new ArrayList<File>();
	static String[] verList = RW.readAll(GitHub.gitFile("dunemask.github.io", "dunemasking/Versions.txt"));
	public static void main(String[] args) {
		getVersions();
		install();
	}
	
	
	/** Gets all the versions from Dunemasking
	 * 
	 * */
	private static void getVersions() {
		JFrame f= new JFrame("Versions");
		JPanel p = new JPanel(null);
		JTextArea tb = new JTextArea();
		for(String v:verList) {
			tb.setText(tb.getText()+v+"\r\n");
		}
		p.add(tb);
		f.add(p);
		boolean success = false;
		while(!success) {
			String vers = JOptionPane.showInputDialog("Please Specify what Dunemasking Version You wish to download (double of version only)");
			if(vers!=""&&vers!=null&&checkAvailable(vers)) {
				versions.add(GitHub.gitFile("dunemask.github.io", "dunemasking/libraries/Dunemasking"+vers+".jar"));
					String tmp = JOptionPane.showInputDialog("More? Y/N");
					if(StringUtil.containsIgnoreCase(tmp, "N")) {
						success = true;
						f.setVisible(false);
					}
			}else if(vers!=""&&vers!=null) {
				JOptionPane.showMessageDialog(null,"Please Select a Valid Version from one of these");
				f.setSize(verList.length*30, verList.length*30);
				f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				tb.setSize(f.getSize());
				f.setVisible(true);
				f.setLocationRelativeTo(null);;
				f.repaint();
				f.revalidate();
				
			}else {
				System.exit(0);
			}
		}
		
	}
	/**Checks if it's a valid version of Dunemasking
	 * */
	public static boolean checkAvailable(String vers) {
		boolean avail = false;
		for(int i=0;i<verList.length;i++) {
			if(verList[i].contains(vers)) {
				avail=true;
				i=verList.length;
			}
		}
		return avail;
	}
	
	/**Installs this loaders components
	 * */
	private static void install() {
		String top = FileUtil.fixSpaces(dunemask.dunemasking.Setup.init(Setup.autoHandleSetup, jarName, "cookie")).replaceAll("%20", " ");
		int c = JOptionPane.showConfirmDialog(null, "The Installation Of Dunemasking Will now Begin, Admin Rights/Interweb Required, Proceed?");
		switch (c) {
		case 1:System.exit(0);
			break;
		case 2: System.exit(0);
			break;
		
		}
		//File l = GitHub.gitFile("dunemask.github.io", "java/dunemask_libraries/Dunemasking3.86.jar");
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
		//cmds.add(CMD.copyFileViaCmd(l,new File(dunemaskingPath+l.getName())));
		//cmds.add("pause");
		try {
			File installedVersions = File.createTempFile("HelloWorld", ".txt");
			ArrayList<String> names = new ArrayList<String>();
			for(int i=0;i<versions.size();i++) {
				names.add(versions.get(i).getName());
			}
			RW.write(installedVersions,names.toArray(new String[names.size()]),0);
			cmds.add(CMD.copyFileViaCmd(installedVersions, new File(dunemaskingPath+"Installed Versions.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cmds.addAll(installAndCopyDunemasking(dunemaskingPath));
		//cmds.add("pause");
		copyAndClean(cmds, top);
		JOptionPane.showMessageDialog(null, "Thanks for Installing Dunemasking!");
		System.exit(0);
	}
	/** @param dunemaskingPath Path to dunemasking install folder.
	 * */
	private static ArrayList<String> installAndCopyDunemasking(String dunemaskingPath) {
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("mkdir \""+dunemaskingPath+"dunemask_libraries\"");
		for(int i=0;i<versions.size();i++) {
			commands.add(CMD.copyFileViaCmd(versions.get(i),new File(dunemaskingPath+"dunemask_libraries\\"+versions.get(i).getName())));
		}
		return commands;
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
