package com.example.samuel.booklisting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Samuel on 30/05/2017.
 */

public class FetchBookData {

    public static ArrayList<Books> FetchDataFromJson(String x) {
        ArrayList<Books> mybook = new ArrayList<>();
        String title = "";
        String publisher = "No available publisher";
        String description = "No available description";
        String authorNames = "No available author";
        String image_link = "";
        String book_link ="";




            try {
                JSONObject rootObject = new JSONObject(x);
                int number_of_books = rootObject.getInt("totalItems");
                if (number_of_books == 0) {
                    mybook.clear();

                    return mybook;
                } else {
                    JSONArray rootArray = rootObject.getJSONArray("items");

                    for (int i = 0; i < rootArray.length(); i++) {

                        JSONObject currentObject = rootArray.getJSONObject(i);
                        JSONObject innerObject = currentObject.getJSONObject("volumeInfo");
                        title = innerObject.getString("title");

                        if (innerObject.has("authors")) {

                            JSONArray authorArray = innerObject.getJSONArray("authors");


                            authorNames = authorArray.toString().replace("[","").replace("\"","").replace("]","")+".";}
//
                        if (innerObject.has("publisher")) {
                            publisher = innerObject.getString("publisher");
                        }
                        if (innerObject.has("description")) {
                            description = innerObject.getString("description");
                        }
                        if(innerObject.has("imageLinks")){
                            JSONObject imageObject = innerObject.getJSONObject("imageLinks");
                            image_link = imageObject.getString("thumbnail");

                        }
                        if(innerObject.has("infoLink")){
                            book_link = innerObject.getString("infoLink");
                        }

                        Books currentBook = new Books(title, publisher, description, authorNames,image_link,book_link);
                        mybook.add(currentBook);
                         publisher = "No available publisher";
                         description = "No available description";
                         authorNames = "No available author";


                    }
                    return mybook;

                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
                return null;
            }

        }



    public static String FetchJson() {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        InputStream stream = null;
        String data = MainActivity.search.replace(" ", "+");
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=" + data + "&maxResults=10");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int response = connection.getResponseCode();

            Log.v("Response code is : ",String.valueOf(response));


            stream = connection.getInputStream();

            String line = "";
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                }


                String cow = buffer.toString();

                Log.v("String returned is : ",cow);
                return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("MalformedURLException ", "MalformedURLException : " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOException ", "IOException : " + e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }
}
