package frames;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FramePruebas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePruebas frame = new FramePruebas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePruebas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1er num");
		lblNewLabel.setBounds(41, 36, 57, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Suma");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int n1=Integer.parseInt(textField.getText());
				int n2=Integer.parseInt(textField_1.getText());
				int suma = n1+n2;
				lblNewLabel_1.setText(String.valueOf(suma));
			}
		});
		btnNewButton.setBounds(172, 181, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(108, 36, 162, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(108, 64, 162, 23);
		contentPane.add(textField_1);
		
		JLabel lbldoNum = new JLabel("2do num");
		lbldoNum.setBounds(41, 68, 57, 19);
		contentPane.add(lbldoNum);
		
		lblNewLabel_1 = new JLabel("Resultado");
		lblNewLabel_1.setBounds(273, 185, 70, 19);
		contentPane.add(lblNewLabel_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Azul");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setBackground(Color.blue);
			}
		});
		mntmNewMenuItem.setBounds(38, 0, 98, 25);
		contentPane.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Rojo");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setBackground(Color.red);
			}
		});
		mntmNewMenuItem_1.setBounds(146, 0, 115, 25);
		contentPane.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Verde");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setBackground(Color.green);
			}
		});
		mntmNewMenuItem_2.setBounds(273, 0, 134, 25);
		contentPane.add(mntmNewMenuItem_2);
	}
}
