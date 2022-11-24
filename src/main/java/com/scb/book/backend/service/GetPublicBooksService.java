package com.scb.book.backend.service;

import com.google.gson.Gson;
import com.scb.book.backend.response.GetPublicBooksResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPublicBooksService {

    public static String stream(URL url) throws IOException {
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        }
    }

    public List<GetPublicBooksResponse> getPublicBooks() throws IOException, InterruptedException {

        List<GetPublicBooksResponse>  mapOutput = new ArrayList<>();

        URL urlBooks = new URL("https://scb-test-book-publisher.herokuapp.com/books");
        String jsonBooks = stream(urlBooks);
        Gson gson = new Gson();
        List<GetPublicBooksResponse> outputList = gson.fromJson(jsonBooks, ArrayList.class);
        //Thread.sleep(2000);

        URL urlRecommendation = new URL("https://scb-test-book-publisher.herokuapp.com/books/recommendation");
        String jsonRecommendation = stream(urlRecommendation);
        gson = new Gson();
        List<GetPublicBooksResponse> outputList2 = gson.fromJson(jsonRecommendation, ArrayList.class);

        /*Thread.sleep(2000);
        outputList2.forEach(opList2 ->{
            System.out.println("1111111");
        });*/

        return outputList;
    }
}
