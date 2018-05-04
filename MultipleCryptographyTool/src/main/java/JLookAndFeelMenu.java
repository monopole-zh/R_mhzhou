
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * һ����װ��LookAndFeel�Ӳ˵� �����ڸı������LookAndFeel ��ЩLookAndFeel��JRE �Դ��� LookAndFeel
 * ��Щ���������һ����������� �������ſɸ���LookAndFeel
 * 
 * @author QIU_BaiChao
 */
public class JLookAndFeelMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String defaultMenuName = "LookAndFeel";

	UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();

	ButtonGroup buttonGroup = new ButtonGroup();

	Component parent;

	public JLookAndFeelMenu(String menuName, final Component parent) {
		setParentComponent(parent);
		this.setText(menuName);
	}

	public JLookAndFeelMenu(Component parent) {
		this(defaultMenuName, parent);
	}

	public JLookAndFeelMenu() {
		super(defaultMenuName);
	}

	/**
	 * ����LookAndFeel�ĵ�ѡ�Ӳ˵�
	 * 
	 * @param parent
	 */
	private void generateLookAndFeelSubMenu(final Component parent) {
		for (int i = 0; i < info.length; i++) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(info[i].getName(), i == 0);
			final String className = info[i].getClassName();
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evn) {
					try {
						UIManager.setLookAndFeel(className);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Don't support the style!");
					}
					SwingUtilities.updateComponentTreeUI(parent);
				}
			});
			buttonGroup.add(item);
			add(item);
		}
	}

	public Component getParentComponent() {
		return parent;
	}

	public void setParentComponent(Component parent) {
		this.parent = parent;
		generateLookAndFeelSubMenu(parent);
	}

}