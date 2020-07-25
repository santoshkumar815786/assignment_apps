package com.smartjaguardevelopers.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smartjaguardevelopers.assignmentapp.home.HomeActivity;
import com.smartjaguardevelopers.assignmentapp.sign_in.SignInActivity;
import com.smartjaguardevelopers.assignmentapp.sign_up.SignUpActivity;
import com.smartjaguardevelopers.assignmentapp.user.UserLogInSharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserLogInSharedPreferences userLogInSharedPreferences = new UserLogInSharedPreferences(this);
        if(!userLogInSharedPreferences.getCurrentUserEmail().equals("null"))
        {
            // Starting HomeActivity if user is already logged in
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void signIn(View view)
    {
        // starting SignIn activity
        startActivity(new Intent(this, SignInActivity.class));
    }

    public void signUp(View view)
    {
        // starting SignUp activity
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
