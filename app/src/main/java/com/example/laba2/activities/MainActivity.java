package com.example.laba2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.laba2.Network;
import com.example.laba2.R;
import com.example.laba2.model.Civilization;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private final String JSON_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json";
    private final String IMAGE_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
    private final static String TAG ="HomeActivity";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;

    private List<Civilization> civilizations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        civilizations = new ArrayList<>();
        jsonrequest();

    }

//    Парсим наш JSON файл и добавляем элементы в List
    private void jsonrequest() {

        // private final String JSON_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json";
        request = new JsonArrayRequest(JSON_URL, response -> {

            JSONObject jsonObject = null;

            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = response.getJSONObject(i);
                    Civilization civilization = new Civilization();
                    civilization.setName(jsonObject.getString("name"));
                    if (jsonObject.has("helptext")) {
                        civilization.setHelptext(jsonObject.getString("helptext"));
                    } else {
                        civilization.setHelptext("This card does't have help text!");
                    }

                    String image = IMAGE_URL + jsonObject.getString("graphic");

                    civilization.setGraphic(image);
                    civilizations.add(civilization);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            checkImage(civilizations);
        }, error -> {

        });

        // Делаем сетевой запрос
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);



    }
    //Добавляем Adapter для RecyclerView
    public void checkImage(List<Civilization> lstCivilization) {
        Network network = new Network();

            Network.executorService.submit(() ->{
                try {
                    Singleton.getInstance().setItems( network.getList(lstCivilization));

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

}
