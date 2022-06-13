package com.example.simplechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ChatActivity extends AppCompatActivity {

    ImageButton ibSendButton;
    EditText etContent;
    RecyclerView rvChat;

    Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rvChat = findViewById(R.id.rvChat);
        etContent = findViewById(R.id.etContent);
        ibSendButton = findViewById(R.id.ibSendButton);


        if (ParseUser.getCurrentUser() != null) { // Start with existing user
            startWithCurrentUser(); //TODO: We will build out this method in the next step
        } else { // If not logged in, login as a new anonymous user
            login();
        }
    }

    private void setupMessagePosting() {
        ibSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String body = etContent.getText().toString();
                Message message = new Message();
                message.setBody(body);
                message.setUserId(ParseUser.getCurrentUser().getObjectId());


                message.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(ChatActivity.this, "Successfully created message on Parse",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("SETUPMESSAGEPOSTING", "Failed to save message", e);
                        }
                    }
                });
            }
        });
    }

    private void login() {
        ParseAnonymousUtils.logIn(new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e("CHATACTIVITY", "Anonymous login failed: ", e);
                } else {
                    startWithCurrentUser();
                }
            }
        });
    }

    private void startWithCurrentUser() {
        setupMessagePosting();
    }

}