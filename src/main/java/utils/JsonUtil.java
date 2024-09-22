package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtil(){}

    public static AddressDTO deserialize (String json) {
       return gson.fromJson(json, AddressDTO.class);

    }

    public static <T> void serializeListForJson(List<T> list, OutputStreamWriter outputStreamWriter) throws IOException {
        outputStreamWriter.write(gson.toJson(list));
        outputStreamWriter.flush();
    }

}
