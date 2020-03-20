package crawlerui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
 
public class GUIDesign {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {    
        // Creating instance of JFrame
    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Simple Crawler UI");
        // Setting the width and height of frame
        frame.setSize(600, 370);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container pane = frame.getContentPane();
        frame.setContentPane(pane);
        
        FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
        pane.setLayout(flow);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(600, 400);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel);
        
        //panel holds required fields - seed url and storage location
        JPanel reqFieldsPanel = new JPanel();
        reqFieldsPanel.setPreferredSize(new Dimension(580,100));
        mainPanel.add(reqFieldsPanel);
        JTextField urlText = new JTextField();
        final JTextField fileLocationText = new JTextField();
        placeComponents1(reqFieldsPanel, urlText, fileLocationText);

        //holds the panels for optional fields and results
        JPanel holdingPanel = new JPanel();
        holdingPanel.setLayout(new BoxLayout(holdingPanel, BoxLayout.X_AXIS));
        mainPanel.add(holdingPanel);
        
        //panel holds optional fields - threads, politeness, pages, depth
        JPanel optFieldsPanel = new JPanel();
        optFieldsPanel.setPreferredSize(new Dimension(200, 200));
        holdingPanel.add(optFieldsPanel);
        JTextField threadsText = new JTextField("1");
        JTextField depthText = new JTextField("2");
        JTextField maxPageText = new JTextField("1000");
        JTextField politenessText = new JTextField("200");
        JButton startCrawlBtn = new JButton("Start crawl");
        placeComponents2(optFieldsPanel, threadsText, depthText, maxPageText, politenessText, startCrawlBtn);
        
        //panel holds results
        JPanel resultPanel = new JPanel();
        resultPanel.setPreferredSize(new Dimension(200, 200));
        holdingPanel.add(resultPanel);
        JTextArea resultArea = new JTextArea("Result will appear here...");
        placeComponents3(resultPanel, resultArea);
        
        
        // Setting the frame visibility to true
        frame.setVisible(true);
        
        startCrawlBtn.addActionListener(
        		new ButtonActionListener(urlText, fileLocationText, threadsText, depthText, maxPageText, politenessText, resultArea)
        		);
        
    }

    private static void placeComponents1(JPanel panel, JTextField urlText, final JTextField fileLocationText) {

    	Border border = BorderFactory.createTitledBorder("Required fields");
    	panel.setBorder(border);
    	
        panel.setLayout(null);

        // url label and field
        JLabel urlLabel = new JLabel("Seed URL");
        
        urlLabel.setBounds(10,20,100,25);
        panel.add(urlLabel);

        //urlText = new JTextField(30);
        urlText.setBounds(150,20,300,25);
        panel.add(urlText);

        // storage label and field.
        JLabel storageLabel = new JLabel("Storage Location");
        storageLabel.setBounds(10,50,100,25);
        panel.add(storageLabel);

        JButton fileChooserBtn = new JButton("Browse");
        fileChooserBtn.setBounds(350,50,100,25);
        //fileLocationText = new JTextField();
        fileLocationText.setBounds(150, 50, 200, 25);
        fileChooserBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(new JFrame());
				if(option == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					fileLocationText.setText(file.getAbsolutePath());
		        }   
			}
		});
        
        panel.add(fileLocationText);
        panel.add(fileChooserBtn);
        
        
    }

    private static void placeComponents2(JPanel panel, JTextField threadsText, JTextField depthText, JTextField maxPageText, JTextField politenessText, JButton startCrawlBtn){
    	//Border blackline = BorderFactory.createLineBorder(Color.black);
    	panel.setLayout(null);
    	
    	Border border = BorderFactory.createTitledBorder("Optional fields");
    	panel.setBorder(border);
    	
    	//panel.setBorder(blackline);
    	
    	
    	JLabel threadsLabel = new JLabel("Number of threads");
    	threadsLabel.setBounds(10, 20, 150, 25);
    	//JTextField threadsText = new JTextField("1");
    	threadsText.setBounds(170, 20, 50, 25);
    	panel.add(threadsLabel);
    	panel.add(threadsText);
    	
    	JLabel depthLabel = new JLabel("Maximum depth");
    	depthLabel.setBounds(10, 50, 150, 25);
    	//JTextField depthText = new JTextField("2");
    	depthText.setBounds(170, 50, 50, 25);
    	panel.add(depthLabel);
    	panel.add(depthText);
    	
    	JLabel maxPageLabel = new JLabel("Maximum pages to fetch");
    	maxPageLabel.setBounds(10, 80, 150, 25);
    	//JTextField maxPageText = new JTextField("1000");
    	maxPageText.setBounds(170, 80, 50, 25);
    	panel.add(maxPageLabel);
    	panel.add(maxPageText);
    	
    	JLabel politenessLabel = new JLabel("Politeness delay");
    	politenessLabel.setBounds(10, 110, 150, 25);
    	//JTextField politenessText = new JTextField("200");
    	politenessText.setBounds(170, 110, 50, 25);
    	panel.add(politenessLabel);
    	panel.add(politenessText);
    	
    	//Creating login button
        //JButton startCrawlBtn = new JButton("Start crawler");
        startCrawlBtn.setBounds(10, 150, 150, 25);
        panel.add(startCrawlBtn);
        
    }

    private static void placeComponents3(JPanel panel, JTextArea resultArea){
    	panel.setLayout(null);
    	
    	Border border = BorderFactory.createTitledBorder("Result");
    	panel.setBorder(border);
//    	JTextArea resultArea = new JTextArea("Result will appear here...");
    	//resultArea.setBounds(10, 20, 270, 170);
    	JScrollPane scroll = new JScrollPane(resultArea);
    	scroll.setBounds(10, 20, 270, 170);
    	panel.add(scroll);
    	resultArea.setEditable(false);
    	//panel.add(resultArea);
    }

}

