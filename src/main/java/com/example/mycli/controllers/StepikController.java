//package com.example.mycli.controllers;
//
//import com.google.gson.Gson;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.List;
//import com.google.gson.annotations.SerializedName;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
//public class StepikController {
//    String client_id = "wDBYsxclQcO0QM31i7klQCXpoDVs15MgGss8ZtqI";
//    String client_secret =
//            "1CcY0GCzqWJJHAAfLKBw9IdPv3u6Z0OOuzHhg84wQQlmbddXvupaUSNPK00CKA0faGmVF4l11lgaKJdfzQtaCQIhW3UZwiy4RFZ5cMwfJeh1uhw2mHQB2VSEAIV55C7m"
//
//    @GetMapping("/stepik")
//    public void getInfo(HttpServletRequest httpServletRequest) {
//        # 2. Get a token
//                auth = httpServletRequest..HTTPBasicAuth(client_id, client_secret)
//        response = requests.post('https://stepik.org/oauth2/token/',
//                data={'grant_type': 'client_credentials'},
//        auth=auth)
//        token = response.json().get('access_token', None)
//        if not token:
//        print('Unable to authorize with provided credentials')
//        exit(1)
//
//# 3. Call API (https://stepik.org/api/docs/) using this token.
//        api_url = 'https://stepik.org/api/courses/67'
//        course = requests.get(api_url,
//                headers={'Authorization': 'Bearer ' + token}).json()
//
//        print(course)
//    }
//
//
//
//}
