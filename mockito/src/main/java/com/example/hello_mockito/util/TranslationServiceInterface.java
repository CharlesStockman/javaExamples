package com.example.hello_mockito.util;

public interface TranslationServiceInterface {
         	    default String translate(String text,
 	                             String sourceLang,
 	                             String targetLang) {
 	        return text;
 	    }
 	}
