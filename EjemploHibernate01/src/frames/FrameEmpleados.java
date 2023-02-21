package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Empleados;
import tools.Funciones;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FrameEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField campoId;
	private JTextField campoApellido;
	private JTextField campoOficio;
	private JTextField campoDir;
	private JTextField campoAlta;
	private JTextField campoSalario;
	private JTextField campoDepartamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameEmpleados frame = new FrameEmpleados();
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
	public FrameEmpleados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etId = new JLabel("ID");
		etId.setBounds(23, 40, 130, 14);
		contentPane.add(etId);
		
		JLabel etApellido = new JLabel("Apellido");
		etApellido.setBounds(23, 65, 130, 14);
		contentPane.add(etApellido);
		
		JLabel etOficio = new JLabel("Oficio");
		etOficio.setBounds(23, 90, 130, 14);
		contentPane.add(etOficio);
		
		JLabel etDir = new JLabel("Dir");
		etDir.setBounds(23, 115, 130, 14);
		contentPane.add(etDir);
		
		JLabel etAlta = new JLabel("Alta (YYYY-MM-DD)");
		etAlta.setBounds(23, 140, 130, 14);
		contentPane.add(etAlta);
		
		JLabel etSalario = new JLabel("Salario");
		etSalario.setBounds(23, 165, 130, 14);
		contentPane.add(etSalario);
		
		JLabel etDepartamento = new JLabel("Departamento");
		etDepartamento.setBounds(23, 190, 130, 14);
		contentPane.add(etDepartamento);
		
		campoId = new JTextField();
		campoId.setBounds(163, 34, 86, 20);
		contentPane.add(campoId);
		campoId.setColumns(10);
		
		campoApellido = new JTextField();
		campoApellido.setColumns(10);
		campoApellido.setBounds(163, 59, 86, 20);
		contentPane.add(campoApellido);
		
		campoOficio = new JTextField();
		campoOficio.setColumns(10);
		campoOficio.setBounds(163, 84, 86, 20);
		contentPane.add(campoOficio);
		
		campoDir = new JTextField();
		campoDir.setColumns(10);
		campoDir.setBounds(163, 109, 86, 20);
		contentPane.add(campoDir);
		
		campoAlta = new JTextField();
		campoAlta.setColumns(10);
		campoAlta.setBounds(163, 134, 86, 20);
		contentPane.add(campoAlta);
		
		campoSalario = new JTextField();
		campoSalario.setColumns(10);
		campoSalario.setBounds(163, 159, 86, 20);
		contentPane.add(campoSalario);
		
		campoDepartamento = new JTextField();
		campoDepartamento.setColumns(10);
		campoDepartamento.setBounds(163, 184, 86, 20);
		contentPane.add(campoDepartamento);
		
		JLabel etEmpleados = new JLabel("EMPLEADOS");
		etEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		etEmpleados.setBounds(153, 11, 108, 14);
		contentPane.add(etEmpleados);
		
		
		JLabel etResultado = new JLabel("Resultado");
		etResultado.setHorizontalAlignment(SwingConstants.CENTER);
		etResultado.setBounds(23, 236, 401, 14);
		contentPane.add(etResultado);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dptoNo = Integer.valueOf(campoDepartamento.getText());
				int empNo=Integer.valueOf(campoId.getText());
				String apellido = campoApellido.getText();
				String oficio = campoOficio.getText();
				float salario = Float.valueOf(campoSalario.getText());
				int dir = Integer.valueOf(campoDir.getText());
				String fecha = campoAlta.getText();
				
				
			etResultado.setText(tools.Funciones.insertarEmpleadoHibernate(dptoNo, empNo, apellido,oficio,salario,dir,fecha));
			}
		});
		btnInsertar.setBounds(282, 181, 89, 23);
		contentPane.add(btnInsertar);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int empNo = Integer.valueOf(campoId.getText());
				Empleados emp = tools.Funciones.mostrarEmpleadoHibernate(empNo);

				if (emp != null) {
					campoApellido.setText(emp.getApellido());
					campoOficio.setText(emp.getOficio());
					campoSalario.setText(String.valueOf(emp.getSalario()));
					campoDir.setText(String.valueOf(emp.getDir()));
					campoAlta.setText(String.valueOf(emp.getFechaAlta()));
					campoDepartamento.setText(emp.getDepartamentos().getDnombre());
					etResultado.setText("Mostrado correctamente");
				} else {
					etResultado.setText("Id no existente");
				}
			}
		});
		btnMostrar.setBounds(282, 36, 89, 23);
		contentPane.add(btnMostrar);
		
		
	}
}
