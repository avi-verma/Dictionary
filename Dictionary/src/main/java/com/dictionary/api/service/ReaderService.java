package com.dictionary.api.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReaderService {
	SortedSet<String> dictionary = new TreeSet<String>();

	  public  SortedSet<String> load(MultipartFile file) throws IOException {
		    
		    InputStream is=file.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    String word;

		    while((word = reader.readLine()) != null) {
		    	StringTokenizer token=new StringTokenizer(word);
		    	while(token.hasMoreTokens()) {
		    		dictionary.add(token.nextToken());
		    }
		    }
		    
		    reader.close();
		    return dictionary;
		  }
	  public  void save(SortedSet<String> dictionary, String wordlist) throws IOException {
		    FileWriter file = new FileWriter(wordlist);
		    BufferedWriter writer = new BufferedWriter(file);
		    for(String word : dictionary) {
		      writer.append(word);
		      writer.newLine();
		    }

		    writer.close();
		    file.close();
		  }
	public boolean searchFile(String word) {
		
		return dictionary.contains(word);
	}
}
