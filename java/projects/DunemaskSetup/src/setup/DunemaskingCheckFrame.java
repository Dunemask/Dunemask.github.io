/**
 * 
 */
package setup;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * @author Java Test
 *
 */
public class DunemaskingCheckFrame extends JFrame {
	public DunemaskingCheckFrame() {
		setFont(new Font("Times New Roman", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dunemask Libraries\r\n");
		setForeground(Color.LIGHT_GRAY);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		
	}
}
