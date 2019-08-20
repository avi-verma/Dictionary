package com.dictionary.api.controller;

import java.io.IOException;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dictionary.api.service.ReaderService;

@RestController
public class Controller {
	@Autowired
	ReaderService service;
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE,path="upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
		SortedSet<String> set=service.load(file);
		service.save(set, "C:\\Users\\Avinash\\dictionary.txt");
		return "uploaded";
	}
	@RequestMapping(method=RequestMethod.GET,path="search/{searchString}")
	public String searchFile(@PathVariable("searchString") String word) throws IOException{
		if(service.searchFile(word))
		return word+" Search Found";
		else
			return "Word is not present in dictionary";
			
	}

}

