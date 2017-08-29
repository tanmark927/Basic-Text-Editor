package labNine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class gui extends JFrame
{
	private JCheckBox jchkCentered, jchkBold, jchkItalic;
	private JComboBox jcboFontName = new JComboBox();
	private JComboBox jcboFontSize = new JComboBox();
	private JRadioButton red, blue;
	private String fontName = "SansSerif";
	private int fontStyle = Font.PLAIN;
	private Color colorText = Color.BLACK;
	private int fontSize = 12;
	private MessagePanel messagePanel = new MessagePanel();

	public static void main(String[] args)
	{
		JFrame frame = new gui();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public gui() 
	{
		setTitle("Message Center");
		messagePanel.setBackground(Color.yellow);
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontnames = e.getAvailableFontFamilyNames();
		for (int i = 0; i < fontnames.length; i++)
			jcboFontName.addItem(fontnames[i]);
		jcboFontName.setSelectedItem("" + fontName);

		for (int i = 1; i <= 100; i++)
			jcboFontSize.addItem("" + i);
		jcboFontSize.setSelectedItem("" + fontSize);

		JPanel p1 = new JPanel(new BorderLayout(10, 10));
		p1.add(new JLabel("Font Name"), BorderLayout.WEST);
		p1.add(jcboFontName, BorderLayout.CENTER);

		JPanel p2 = new JPanel(new BorderLayout(10, 10));
		p2.add(new JLabel("Font Size"), BorderLayout.WEST);
		p2.add(jcboFontSize, BorderLayout.CENTER);

		JPanel p3 = new JPanel(new BorderLayout(10, 10));
		p3.setBorder(new EmptyBorder(10, 10, 10, 10));
		p3.add(p1, BorderLayout.CENTER);
		p3.add(p2, BorderLayout.EAST);

		JPanel bottom = new JPanel(new FlowLayout());
		
		JPanel p = new JPanel();
		p.add(jchkCentered = new JCheckBox("Centered"));
		p.add(jchkBold = new JCheckBox("Bold"));
		p.add(jchkItalic = new JCheckBox("Italic"));

		jchkCentered.setMnemonic('C');
		jchkBold.setMnemonic('B');
		jchkItalic.setMnemonic('I');
		
		JPanel p4 = new JPanel();
		red = new JRadioButton("Red");
		blue = new JRadioButton("Blue");
		p4.add(red);
		p4.add(blue);
		ButtonGroup b = new ButtonGroup();
		b.add(red);
		b.add(blue);
		
		bottom.add(p);
		bottom.add(p4);
		
		setLayout(new BorderLayout());
		add(messagePanel, BorderLayout.CENTER);
		add(p3, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);

		jcboFontName.addItemListener(new Listener());
		jcboFontSize.addItemListener(new Listener());

		jchkCentered.addItemListener(new Listener());
		jchkBold.addItemListener(new Listener());
		jchkItalic.addItemListener(new Listener());
		
		red.addItemListener(new Listener());
		blue.addItemListener(new Listener());
	}

	class Listener implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getSource() == jcboFontName)
			{
				fontName = (String)(jcboFontName.getSelectedItem());
				messagePanel.setFont(new Font(fontName, fontStyle, fontSize));
			}
			else if (e.getSource() == jcboFontSize)
			{
				fontSize = Integer.parseInt((String)jcboFontSize.getSelectedItem());
				messagePanel.setFont(new Font(fontName, fontStyle, fontSize));
			}
			else if ((e.getSource() == jchkBold) || (e.getSource() == jchkItalic))
			{
				fontStyle = Font.PLAIN;
				if (jchkBold.isSelected())
					fontStyle = fontStyle + Font.BOLD;
				if (jchkItalic.isSelected())
					fontStyle = fontStyle + Font.ITALIC;
				messagePanel.setFont(new Font(fontName, fontStyle, fontSize));
			}
			else if (e.getSource() == jchkCentered)
			{
				if (jchkCentered.isSelected())
					messagePanel.setCentered(true);
				else
					messagePanel.setCentered(false);
			}
			else if(e.getSource() == red)
				messagePanel.setForeground(Color.RED);
			else if(e.getSource() == blue)
				messagePanel.setForeground(Color.BLUE);
		}
	}
}
