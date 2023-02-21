package frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Departamentos;
import modelo.Empleados;

public class FrameDepartamentos extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;
	private JLabel etResultado;
	private JTextField campoLocalidad;
	private JTextField campoId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDepartamentos frame = new FrameDepartamentos();
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
	public FrameDepartamentos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etId = new JLabel("ID");
		etId.setBounds(41, 36, 57, 20);
		contentPane.add(etId);
		
		JButton Inserción = new JButton("Insertar");
		Inserción.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id=Integer.valueOf(campoId.getText());
				String nombre=campoNombre.getText();
				String localidad=campoLocalidad.getText();
				
				etResultado.setText(tools.Funciones.insertarDepartamentoHibernate(id, nombre, localidad));
			}
		});
		Inserción.setBounds(73, 183, 89, 23);
		contentPane.add(Inserción);
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(108, 66, 162, 23);
		contentPane.add(campoNombre);
		
		JLabel etNombre = new JLabel("Nombre");
		etNombre.setBounds(41, 68, 57, 19);
		contentPane.add(etNombre);
		
		etResultado = new JLabel("Resultado");
		etResultado.setHorizontalAlignment(SwingConstants.CENTER);
		etResultado.setBounds(10, 219, 266, 34);
		contentPane.add(etResultado);
		
		JLabel etLocalidad = new JLabel("Localidad");
		etLocalidad.setBounds(41, 98, 57, 19);
		contentPane.add(etLocalidad);
		
		campoLocalidad = new JTextField();
		campoLocalidad.setColumns(10);
		campoLocalidad.setBounds(108, 98, 162, 23);
		contentPane.add(campoLocalidad);
		
		JTextArea textArea = new JTextArea();
		
		JButton btnSelect = new JButton("Mostrado");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resul = "";
				int id=Integer.valueOf(campoId.getText());
				Departamentos dep = tools.Funciones.selectDepartamentoHibernate(id);
				Set<Empleados>empleados= dep.getEmpleadoses();
		         Iterator<Empleados>it=empleados.iterator();
				
				 while(it.hasNext()) {
		             Empleados emp = it.next();
		             resul=resul+"\n"+"Apellido: "+emp.getApellido()+" "+"Salario: "+emp.getSalario();
		             
		         }
				campoNombre.setText(dep.getDnombre());
				campoLocalidad.setText(dep.getLoc());
				textArea.setText(resul);
				etResultado.setText("Departamento mostrado");
			}
		});
		btnSelect.setBounds(181, 183, 89, 23);
		contentPane.add(btnSelect);
		
		JLabel Departamento = new JLabel("Departamentos");
		Departamento.setHorizontalAlignment(SwingConstants.CENTER);
		Departamento.setBounds(10, 0, 424, 25);
		contentPane.add(Departamento);
		
		campoId = new JTextField();
		campoId.setColumns(10);
		campoId.setBounds(108, 36, 162, 23);
		contentPane.add(campoId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(280, 11, 258, 307);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textArea);
		
	}
}
