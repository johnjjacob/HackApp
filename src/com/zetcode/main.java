package com.zetcode;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;

import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.fasterxml.jackson.databind.ObjectMapper;


public class main {
	
		public static String state;
		public static  HttpResponse<String> response;
		/**
		 * Launch the application.
		 * 
		 * @param args
		 */
		
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		try {
			Window window = new Window();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		JFrame frame = new JFrame("COVID-19 Area Data Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);

        JPanel panel = new JPanel(); 
        JLabel label = new JLabel("Enter State");
        JTextField input = new JTextField(15); 
        JButton search = new JButton("Search");
        JButton abbrev = new JButton("Abbreviations");
        
        panel.add(label); 
        panel.add(input);
        panel.add(search);
        panel.add(abbrev);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
        
        String[] statelist = {"ak", "al", "ar", "as", "az", "ca", "co", "ct", "dc", "de", "fl", "ga", "gu", "hi", "ia", "id", "il", "in", "ks", "ky", "la", "ma", "md", "me", "mi", "mn", "mo", "mp", "ms", "mt", "nc", "nd", "ne", "nh", "nj", "nm", "nv", "ny", "oh", "ok", "or", "pa", "pr", "ri", "sc", "sd", "tn", "tx", "um", "ut", "va", "vi", "vt", "wa", "wi", "wv", "wy" };
        
        
        abbrev.addActionListener((e) -> {
        	JOptionPane.showMessageDialog(frame,
    			    "ak, al, ar, as, az, ca, co, ct, dc, de, fl, ga, gu, hi, ia, id, il, in, ks, ky, la, ma, md, me, mi,\nmn, mo, mp, ms, mt, nc, nd, ne, nh, nj, nm, nv, ny, oh, ok, or, pa, pr, ri, sc, sd, tn, tx, um, ut, va, vi, vt, wa, wi, wv, wy");
        });
        
        search.addActionListener((e) -> {
        	state = input.getText();
        	
        	boolean isState = false;
        	for(int s = 0; s < 50; ++s) {
        		if(state.equals(statelist[s])) {
        			isState = true;
        		}
        		
        	}
        	
        	if(isState) {
        	String url = "https://api.covidtracking.com/v1/states/"+state+"/current.csv";
    		
    		
    		HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

           
			try {
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

          //  System.out.println(response.body());
            
            String rawData = response.body();
            
            Data dataset = new Data();
            
            read(rawData, dataset);
            
            for(int i = 0; i < dataset.header.size();i++)
            {
            	
            	if(dataset.dataVal.get(i) != "" && i !=39 && i != 48 && i != 54 &&i != 40)
            	{
            		if(!dataset.dataVal.get(i).equals("0")) {
            			 System.out.println(dataset.header.get(i)+" "+dataset.dataVal.get(i));
            		}
                   
            	}

            }
            
            
            
            
           
            
            List<String> data = new ArrayList<>();
            List<String> column = new ArrayList<>();        
            int j = 0;
            
            for(int i = 0; i < dataset.header.size();i++)
            {
            	if(dataset.dataVal.get(i) != "" && i !=39 && i != 48 && i != 54 &&i != 40)
            	{
            		if(!dataset.dataVal.get(i).equals("0")) {

                    column.add(dataset.header.get(i));
                    j++;
            		}
            	}
            }
            
            String[] fcolumn = new String[j];
            
            for(int m = 0; m < j; ++m) {
            	
            	fcolumn[m] = column.get(m);
            	
            }
            
            j = 0;
            
             for(int i = 0; i < dataset.header.size();i++)
             {
             	if(dataset.dataVal.get(i) != "" && i !=39 && i != 48 && i != 54 &&i != 40)
                 {
            		if(!dataset.dataVal.get(i).equals("0")) {

                     data.add(dataset.dataVal.get(i));
                      j++;
            		}
                }
             }
             
             String[][] fdata = new String[1][j];
             
             for(int p = 0; p < j; ++p) {
            	 fdata[0][p] = data.get(p);
           
             }
            	
            

                     
            JTable jt=new JTable(fdata,fcolumn);
            jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jt.setPreferredScrollableViewportSize(new Dimension(500, 70));
            jt.setFillsViewportHeight(true);
            //jt.setRowHeight(0, jt.getRowHeight() + 300);
            jt.setRowHeight(jt.getRowHeight() + 300);
            jt.setRowMargin(jt.getRowMargin() + 100);
            //jt.setFont(new Font("Serif", Font.CENTER_BASELINE, 5));
            JScrollPane sp=new JScrollPane(jt); 
            frame.add(sp);
        	}
        	
        	else {
        		JOptionPane.showMessageDialog(frame,
        			    "Please use a valid state abbreviation");
        	}
            frame.setVisible(true);
			
		});
		
		
		
        
        frame.setVisible(true);
	}
	
	
	
	
	public static void read(String data, Data d)
	{
		
		
		String[] values = data.split("\n");
		String[] headers = values[0].split(",");
		String[] vals = values[1].split(",");


		
		for(int i = 0; i < headers.length; i++)
		{
			d.header.put(i, headers[i]);
		}
		
		for(int i = 0; i < vals.length; i++)
		{
			d.dataVal.put(i, vals[i]);
		}
		
		
		
	}
}

public class Window implements MouseListener{

	private String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL",
			"IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
			"NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV",
			"WY" };

	

	/**
	 * Open the window.
	 */
	public void open() {

		Display display = Display.getDefault();

		int displayHeight = display.getPrimaryMonitor().getBounds().height;
		int displayWidth = display.getPrimaryMonitor().getBounds().width;

		Shell shell = new Shell();
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label lblChooseAState = new Label(shell, SWT.NONE);
		lblChooseAState.setLayoutData(new RowData(155, 33));
		lblChooseAState.setText("Choose A State");
		
		
		Combo combo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		for(int i = 0; i < states.length; i++)
		{
			combo.add(states[i]);
		}
		
		/*
		 * predefined selection
		 */
//		combo.select(0);
		
		combo.addMouseListener(this);
		
		
		shell.setSize(252, 56);

		shell.setText("COVID Application");

		shell.pack();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}
	
	
	/**
	 * 
	 */
	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
	}

	/**
	 * Mouse down event based action
	 */
	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
		
	}

	/**
	 * Mouse up event based action
	 */
	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getSource());
	}
	
	
	
	
}
