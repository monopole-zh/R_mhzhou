
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * 一个封装了LookAndFeel子菜单 可用于改变组件的LookAndFeel 这些LookAndFeel是JRE 自带的 LookAndFeel
 * 这些组件必须与一个父组件的相 关联，才可更新LookAndFeel
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
	 * 生成LookAndFeel的单选子菜单
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