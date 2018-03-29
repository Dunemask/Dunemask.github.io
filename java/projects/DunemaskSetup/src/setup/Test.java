package setup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dunemask.dm.CMD;
import dunemask.dunemasking.Setup;
import dunemask.dunemasking.StreamGobbler;
import dunemask.util.FileUtil;
import dunemask.util.JarUtil;
import dunemask.util.RW;

public class Test {
	public static final String jarName="Run.jar";
	public static void main(String[] args) {
		String top = dunemask.dunemasking.Setup.init(Setup.autoHandleSetup, jarName, "cookie");
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
		//cmds.add("pause");
		//System.out.println("com:"+cmds.get(1));
		CMD.openElevatedCmd(cmds);
		
		
	}


}
