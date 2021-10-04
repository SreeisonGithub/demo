package com.example.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String site_is_up = "Site_is_up";
    private final String site_is_down = "Site_is_down";
    private final String url_is_incorrect = "url_is_incorrect";

    @GetMapping("/check")
    public String getUrlMessage(@RequestParam String url) {
        String returnMsg = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == 200) {
                returnMsg = site_is_up;
            } else {
                returnMsg = site_is_down;
            }
        } catch (MalformedURLException e) {
            returnMsg = url_is_incorrect;
            e.printStackTrace();
        } catch (IOException e) {
            returnMsg = site_is_down;
            e.printStackTrace();
        }

        return returnMsg;

    }
}
