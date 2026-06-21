package com.Ecommercee.utiles.dataReader;

import com.Ecommercee.utiles.logs.logsManager;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonReader {

      private static final String TEST_DATA_PATH = "src/test/resources/test-data/";

      private String jsonReader;
      private String jsonFileName;

      public JsonReader(String jsonFileName) {
            this.jsonFileName = jsonFileName;
            try {
                  JSONParser parser = new JSONParser();
                  JSONObject data = (JSONObject) parser.parse(new FileReader(TEST_DATA_PATH + jsonFileName + ".json"));
                  jsonReader = data.toJSONString();

            } catch (Exception e) {
                  logsManager.error("Error loading json file:" , jsonFileName, e.getMessage());
                  jsonReader="{}";
            }
      }

      public String getJsonData(String jsonPath) {

            try {
                  return JsonPath.read(jsonReader, jsonPath);

            } catch (Exception e) {
                 logsManager.error("Error reading json file for path:" ,jsonPath ,e.getMessage());
                 return "";
            }
      }
}
