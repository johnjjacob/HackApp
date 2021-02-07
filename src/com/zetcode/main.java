package com.zetcode;

import java.awt.BorderLayout;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.ObjectMapper;


public class main {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	    

		
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.print("Which state would you like?");
		String state = scanner.nextLine();
		
		
		String url = "https://api.covidtracking.com/v1/states/"+state+"/current.csv";
		
		
		HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      //  System.out.println(response.body());
        
        String rawData = response.body();
        
        Data dataset = new Data();
        
        read(rawData, dataset);
        
        for(int i = 0; i < dataset.header.size();i++)
        {
        	
        	if(dataset.dataVal.get(i) != "" && i !=39 && i != 48 && i != 54 && i != 49 && i != 50 && i != 51 && i != 52 && i != 53)
        	{
                System.out.println(dataset.header.get(i)+" "+dataset.dataVal.get(i));
        	}

        }
        
        
  
        
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