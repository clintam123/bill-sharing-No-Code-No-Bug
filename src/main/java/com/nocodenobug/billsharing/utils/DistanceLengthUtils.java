package com.nocodenobug.billsharing.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocodenobug.billsharing.payload.response.DistanceResponse;
import com.squareup.okhttp.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistanceLengthUtils {
    public static DistanceResponse getDistanceLength(String origin, String distation) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/directions/json?origin="+origin+"&destination="+distation+"&language=vi&key=AIzaSyBCBfNm20V9CzumgUChdppVcpUK-vtD2bE&alternatives=true")
                .addHeader("Accept", "application/json")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response);
        int responseCode = 0;
        if ((responseCode = response.code()) == 200) {
            // Get response
            String jsonData = response.body().string();
            JSONObject json = new JSONObject(jsonData);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonData);
            List<Float> list=new ArrayList<>();
//            return  node.get("routes").get(0).get("legs").get(0).get("distance").get("text")+"";
            for (int i=0; i<node.get("routes").size();i++){
              String x=   node.get("routes").get(i).get("legs").get(0).get("distance").get("text")+"";
//                System.out.println(x.substring(1,x.length()-4).replace(",","."));

                list.add(Float.parseFloat(x.substring(1,x.length()-4).replace(",",".")));

            }
            float min=list.get(0);
            int index=0;
            for(int i=0;i<list.size();i++){
//                System.out.println(list.get(i));
                if (list.get(i)<min){
                    min=list.get(i);
                    index=i;
                }
            }
            DistanceResponse distanceResponse=new DistanceResponse();
            distanceResponse.setLength((node.get("routes").get(index).get("legs").get(0).get("distance").get("text")+"").replace("\"","").trim());
            distanceResponse.setSummary((node.get("routes").get(index).get("summary")+"").replace("\"","").trim());
            distanceResponse.setTime((node.get("routes").get(index).get("legs").get(0).get("duration").get("text")+"").replace("\"","").trim());
//
            mapper.writerWithDefaultPrettyPrinter().writeValue(System.out,node.get("routes").get(index));
            return distanceResponse;
        }
        return null;
    }
}