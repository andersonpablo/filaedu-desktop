package tela;

import eduFila.InteligenciaFila;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		setTitle("BiblioEdu - Monitoramento de Filas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/img/icon.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 385);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaInicial.class.getResource("/img/filaedu.png")));
		lblNewLabel.setBounds(97, 25, 315, 117);
		contentPane.add(lblNewLabel);

		JButton botaoMonitorar = criarBotao("Monitorar", new Color(20, 41, 10));
		botaoMonitorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboCamera.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "POR FAVOR SELECIONE UM TIPO DE CAMERA", "ALERTA",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				InteligenciaFila pc = new InteligenciaFila();
				pc.iniciarCamera(IdentificarCamera());
			}
		});
		botaoMonitorar.setBounds(97, 155, 297, 46);
		contentPane.add(botaoMonitorar);

		JButton botaoUpload = criarBotao("Enviar video", new Color(46, 20, 255));
		botaoUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarVideoParaSimulacao();
			}
		});
		botaoUpload.setBounds(97, 214, 297, 46);
		contentPane.add(botaoUpload);

		comboCamera = new JComboBox();
		comboCamera.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboCamera.setFocusable(false);
		comboCamera.setBorder(new MatteBorder(2, 2, 2, 2, new Color(20, 41, 10)));
		comboCamera.setForeground(new Color(20, 41, 10));
		comboCamera.setBackground(Color.WHITE);
		comboCamera.setFont(new Font("Eras Bold ITC", Font.PLAIN, 18));
		comboCamera.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione o tipo de camera", "Camera Nativa", "Camera Externa" }));
		comboCamera.setBounds(97, 273, 297, 46);
		contentPane.add(comboCamera);
	}

	private JButton criarBotao(String texto, Color corFundo) {
		JButton botao = new JButton(texto);
		botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botao.setFocusPainted(false);
		botao.setFont(new Font("Eras Bold ITC", Font.PLAIN, 24));
		botao.setForeground(new Color(255, 255, 255));
		botao.setBackground(corFundo);
		return botao;
	}

	private void selecionarVideoParaSimulacao() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Videos MP4", "mp4");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filtro);

		int resposta = fileChooser.showOpenDialog(this);
		if (resposta == JFileChooser.APPROVE_OPTION) {
			File arquivoSelecionado = fileChooser.getSelectedFile();
			InteligenciaFila pc = new InteligenciaFila();
			pc.iniciarVideoArquivo(arquivoSelecionado.getAbsolutePath());
		}
	}

	protected int IdentificarCamera() {
		int valorCamera = 0;

		if (comboCamera.getSelectedIndex() == 1) {
			valorCamera = 0;
		} else if (comboCamera.getSelectedIndex() == 2) {
			valorCamera = 2;
		}

		return valorCamera;
	}
}
