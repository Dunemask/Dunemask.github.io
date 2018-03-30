/**
 * 
 */
package player;

import java.io.File;

import dunemask.objects.movieplayer.MovieLauncher;

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
			MovieLauncher.startPlayer(new File(args[0]), true);
		}catch(IndexOutOfBoundsException e) {
			MovieLauncher.startPlayer(null, true);
		}

	}

}
