package com.erjmessage.erjmessage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView messageList;
    private Context context;
    private MessageAdapter messageAdapter;
    private List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        bindViews();
        setMessageRecyclerView();
        getMessages();
    }


    private void initializeVariables(){
        context = this;
        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(messages, context);
    }

    private void bindViews(){
        messageList = findViewById(R.id.message_list);
    }

    private void setMessageRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        messageList.setLayoutManager(layoutManager);
        messageList.setAdapter(messageAdapter);
    }

    private void getMessages(){
        StringRequest stringRequest = new StringRequest(Constants.MESSAGE_ENDPOINT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("VOLLEY", response);

                        try {

                            JSONObject data = (JSONObject) new JSONTokener(response).nextValue();
                            JSONArray result = data.getJSONArray("result");

                            for(int i = 0; i < result.length(); i++){
                                messages.add(new Message(
                                        result.getJSONObject(i).getString("message_id"),
                                        result.getJSONObject(i).getString("sender"),
                                        result.getJSONObject(i).getString("receiver"),
                                        result.getJSONObject(i).getString("message_subject"),
                                        result.getJSONObject(i).getString("message_body"),
                                        result.getJSONObject(i).getString("message_timestamp")
                                ));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        messageAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY", "ERROR");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
