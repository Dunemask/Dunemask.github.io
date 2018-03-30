/**
 * 
 */
package player;

import java.io.File;

import dunemask.util.MediaUtil;
import dunemask.util.StringUtil;

/**
 * @author Elijah
 *
 */
public class Player {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
			try {
				boolean repeat=false;
				String file = args[0]= args[0].replace("%20", " ");
				long sleep = 0;
					Thread t = new Thread( ()->{
						MediaUtil.play(new File(file));
					});
					t.start();
					//sleep =  Long.valueOf(String.valueOf(new Media(new File(file).toURI().toString()).getDuration().toMillis()));
				if(args.length>=2) {
					if(StringUtil.containsIgnoreCase(args[1], "t")) {
						repeat=true;
					}
				}
				if(args.length>=3) {
					sleep=Long.valueOf(args[2]);
				}
				
				if(repeat==true) {
					MediaUtil.setOnRepeat();
				}else {
					System.out.println("Gonna Sleep:"+sleep);
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.exit(0);
				}
				
			}catch(IndexOutOfBoundsException e) {
				
			}
	}

}
