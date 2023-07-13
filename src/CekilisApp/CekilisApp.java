package CekilisApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class CekilisApp extends JFrame {
	private String filepath ="";
	private JPanel contentPane;
	private JTextField SearchBar;
	private ArrayList<String> katilanlar = new ArrayList<>();
	private Set<String> winners = new TreeSet <String> ();
	private DefaultListModel model = new DefaultListModel<>();
	
	private JTextField winnum;

	/**
	 * Launch the application.
	 */
	public void cekilisyap() {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"ISO-8859-9"))){
			String kisi;
			while((kisi =reader.readLine()) != null) {
				katilanlar.add(kisi);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(winners.size() != Integer.parseInt(winnum.getText())) {
			Random random = new Random();
			int kazananindex= random.nextInt(katilanlar.size());
			winners.add(katilanlar.get(kazananindex));
		}
		
	}
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					CekilisApp frame = new CekilisApp();
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
	public CekilisApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    
		
		JButton Searchbtn = new JButton("Gözat");
		Searchbtn.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null); 

				if (result == JFileChooser.APPROVE_OPTION) {
				    filepath =fileChooser.getSelectedFile().getPath();
				    SearchBar.setText(filepath);
				} else if (result == JFileChooser.CANCEL_OPTION) {
				   
				}

				
				
				
				
				
			}
		});
		Searchbtn.setBackground(UIManager.getColor("Button.darkShadow"));
		Searchbtn.setBounds(319, 10, 105, 23);
		contentPane.add(Searchbtn);
		
		SearchBar = new JTextField();
		SearchBar.setBackground(UIManager.getColor("TextField.darkShadow"));
		SearchBar.setEditable(false);
		SearchBar.setBounds(10, 11, 283, 20);
		contentPane.add(SearchBar);
		SearchBar.setColumns(10);
		
		JList WinnerList = new JList();
		WinnerList.setBackground(new Color(70, 130, 180));
		WinnerList.setBounds(10, 94, 283, 156);
		contentPane.add(WinnerList);
		
		JButton CekilisBtn = new JButton("Çekiliş Yap");
		CekilisBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filepath.equals("")) {
					JOptionPane.showMessageDialog(null, "Lütfen bir çekiliş dosyası seçiniz...");
				}else {
					cekilisyap();
					for(String kazanan : winners) {
						model.addElement(kazanan);
					}
				}
				
				
				
			}
		});
		CekilisBtn.setBackground(UIManager.getColor("Button.darkShadow"));
		CekilisBtn.setBounds(319, 169, 105, 23);
		contentPane.add(CekilisBtn);
		
		JLabel lblNewLabel = new JLabel("Kazananlar");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 69, 109, 14);
		contentPane.add(lblNewLabel);
		WinnerList.setModel(model);
		
		JLabel lblNewLabel_1 = new JLabel("Kazanan Sayısı:");
		lblNewLabel_1.setBounds(319, 88, 105, 28);
		contentPane.add(lblNewLabel_1);
		
		winnum = new JTextField(10);
		winnum.setBackground(UIManager.getColor("TextField.background"));
		winnum.setBounds(319, 127, 105, 20);
		contentPane.add(winnum);
		winnum.setColumns(10);
		
		JButton resetBtn = new JButton("Sıfırla");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
			}
		});
		resetBtn.setBackground(UIManager.getColor("Button.darkShadow"));
		resetBtn.setBounds(319, 44, 105, 23);
		contentPane.add(resetBtn);
		
		JButton againbtn = new JButton("Tekrar yap");
		againbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				katilanlar.clear();
				winners.clear();
				model.clear();
				cekilisyap();
				for(String kazanan : winners) {
					model.addElement(kazanan);
				}
			}
		});
		againbtn.setBackground(UIManager.getColor("Button.darkShadow"));
		againbtn.setBounds(319, 212, 105, 23);
		contentPane.add(againbtn);
	}
}
