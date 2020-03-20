package crawlerui;


import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.net.URL;
import java.util.Locale;

public class ButtonActionListener implements ActionListener{
	
	private JTextField urlText;
	private JTextField fileLocationText;
	private JTextField threadsText;
	private JTextField depthText;
	private JTextField maxPageText;
	private JTextField politenessText;
	private JTextArea resultArea;
	
	public ButtonActionListener(JTextField urlText, JTextField fileLocationText, JTextField threadsText, JTextField depthText, JTextField maxPageText, JTextField politenessText, JTextArea resultArea){
		this.urlText = urlText;
		this.fileLocationText = fileLocationText;
		this.threadsText = threadsText;
		this.depthText = depthText;
		this.maxPageText = maxPageText;
		this.politenessText = politenessText;
		this.resultArea = resultArea;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Text fields input: \n=================");
		
		String urlVal = urlText.getText();
		System.out.println("URL: "+urlVal);
		
		String locationVal = fileLocationText.getText();
		String osName = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);
		System.out.println(osName);
		if(osName.contains("win")){
			locationVal = locationVal.replace("\\", "\\\\");
			
		}
		System.out.println("Location: "+locationVal);
		
		String threadsVal = threadsText.getText();
		System.out.println("Threads: "+threadsVal);
		
		String depthVal = depthText.getText();
		System.out.println("Depth: "+depthVal);
		
		String maxPageVal = maxPageText.getText();
		System.out.println("Max Page: "+maxPageVal);
		
		String politenessVal = politenessText.getText();
		System.out.println("Politeness: "+politenessVal);
		
		boolean isValidUrl = isValidURL(urlVal);
		boolean isValidThread = isValidThread(threadsVal);
		boolean isValidDepth = isValidDepth(depthVal);
		boolean isValidMaxPage = isValidMaxPage(maxPageVal);
		boolean isValidPoliteVal = isValidPoliteness(politenessVal);
		
		if(!isValidUrl){
			showErrorMessage(new JFrame(), "Please check URL", "Invalid URL");
		}
		else if(!isValidThread){
			showErrorMessage(new JFrame(), "Please check threads value. Note that minimum value is 1", "Invalid threads value");
		}
		else if(!isValidDepth){
			showErrorMessage(new JFrame(), "Please check depth value. Note that minimum value is 1", "Invalid depth value");
		}
		else if(!isValidMaxPage){
			showErrorMessage(new JFrame(), "Please check number of pages value. Note that minimum value is 1", "Invalid Number of Pages");
		}
		else if(!isValidPoliteVal){
			showErrorMessage(new JFrame(), "Please check politeness value. Note that minimum value is 200", "Invalid politeness value");
		}
		else{
			//all ok -> call CralwerController
			JButton source = (JButton)e.getSource();
			source.setEnabled(false);
			System.out.println("+++++++button source: "+source.getText());
			resultArea.setText("Please wait for crawling to complete...");
			CrawlerController crawlerController = new CrawlerController();
			try{
				System.out.println("before calling control try block+++++++++++++++++++++++++++++++++++");
				crawlerController.crawlControl(urlVal, locationVal, Integer.parseInt(threadsVal), 
						Integer.parseInt(depthVal), Integer.parseInt(maxPageVal), Integer.parseInt(politenessVal), resultArea);
				System.out.println("in try block+++++++++++++++++++++++++++++++++++");
				
			}
			catch(Exception exception){
				//String exceptionText = exception.getStackTrace().toString();
				resultArea.setText("Exception: \nCrawler job ended with an exception.");
				resultArea.setForeground(Color.RED);
			}
			source.setEnabled(true);
		}
		
		
	}
	
	private boolean isValidURL(String urlVal){
		try{
			new URL(urlVal).toURI();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	private boolean isValidThread(String threadsVal){
		try{
			int a = Integer.parseInt(threadsVal);
			if(a <= 0)
				return false;
			else
				return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	private boolean isValidDepth(String depthVal){
		try{
			int a = Integer.parseInt(depthVal);
			if(a <= 0)
				return false;
			else
				return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	private boolean isValidMaxPage(String maxPageVal){
		try{
			int a = Integer.parseInt(maxPageVal);
			if(a <= 0)
				return false;
			else
				return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	private boolean isValidPoliteness(String politenessVal){
		try{
			int a = Integer.parseInt(politenessVal);
			if(a < 200)
				return false;
			else
				return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	private void showErrorMessage(JFrame frame, String message, String title){
		JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
	}
}
