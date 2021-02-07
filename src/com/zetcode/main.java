package com.zetcode;

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


import com.fasterxml.jackson.databind.ObjectMapper;


public class main {
	

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	
		
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.print("Which state would you like?");
		String state = scanner.nextLine();
		
		
		String url = "https://api.covidtracking.com/v1/states/"+state+"/current.json";
		
		
		HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        
        String rawData = response.body();
        
        System.out.println(rawData);


        
//        Data one = new Data();
//        
//        HashMap<String,Object> result = new ObjectMapper().readValue(response.body(), HashMap.class);        
//        
        
        
        
	}

}