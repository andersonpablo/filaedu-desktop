package tela;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eduFila.InteligenciaFila;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.MatteBorder;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboCamera;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaInicial() {
		setResizable(false);
		setTitle("FilaEdu - Monitoramento de Filas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/img/icon.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 343);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/filaedu.png")));
		lblNewLabel.setBounds(97, 25, 315, 117);
		contentPane.add(lblNewLabel);

		JButton botaoMonitorar = new JButton("Monitorar");
		botaoMonitorar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botaoMonitorar.setFocusPainted(false);
		botaoMonitorar.setFont(new Font("Eras Bold ITC", Font.PLAIN, 24));
		botaoMonitorar.setForeground(new Color(255, 255, 255));
		botaoMonitorar.setBackground(new Color(20, 41, 10));
		botaoMonitorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboCamera.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "POR FAVOR SELECIONE UM TIPO DE C�MERA", "ALERTA", JOptionPane.WARNING_MESSAGE);
				}
				
				InteligenciaFila pc = new InteligenciaFila();
				pc.processarVideo("files\\yolov7-tiny.weights", "files\\yolov7-tiny.cfg", "files\\teste.mp4");
				pc.cameraSelecionada = IdentificarCamera();
			}
		});
		botaoMonitorar.setBounds(97, 155, 297, 46);
		contentPane.add(botaoMonitorar);
		
		comboCamera = new JComboBox();
		comboCamera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboCamera.setFocusable(false);
		comboCamera.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(20, 41, 10)));
		comboCamera.setForeground(new Color(20, 41, 10));
		comboCamera.setBackground(Color.WHITE);
		comboCamera.setFont(new Font("Eras Bold ITC", Font.PLAIN, 18));
		comboCamera.setModel(new DefaultComboBoxModel(new String[] {"Selecione o tipo de câmera", "Câmera Nativa", "Câmera Externa"}));
		comboCamera.setBounds(97, 214, 297, 46);
		contentPane.add(comboCamera);
	}

	protected int IdentificarCamera() {
		
		int valorCamera = 0;
		
		if(comboCamera.getSelectedIndex() == 1) {
			valorCamera = 0;
		} else if(comboCamera.getSelectedIndex() == 2) {
			valorCamera = 2;
		}
		return valorCamera;
	}
}