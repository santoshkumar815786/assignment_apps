package com.smartjaguardevelopers.assignmentapp.sign_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.smartjaguardevelopers.assignmentapp.R;
import com.smartjaguardevelopers.assignmentapp.home.HomeActivity;
import com.smartjaguardevelopers.assignmentapp.room_database.MyAppDatabase;
import com.smartjaguardevelopers.assignmentapp.room_database.User;
import com.smartjaguardevelopers.assignmentapp.user.UserLogInSharedPreferences;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtEmailId, edtName, edtPassword, edtConfirmPassword;

    public MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtEmailId = (EditText)findViewById(R.id.edtEmailId);
        edtName = (EditText)findViewById(R.id.edtName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText)findViewById(R.id.edtConfirmPassword);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"usersDb").allowMainThreadQueries().build();
    }

    public void signUp(View view)
    {
        if(isAllInputsValid())
        {
            // this method will execute only if all the inputs are valid
            String emailId, name, password;
            emailId = edtEmailId.getText().toString();
            name = edtName.getText().toString();
            password = edtPassword.getText().toString();

            User user = new User();
            user.setEmailId(emailId);
            user.setName(name);
            user.setPassword(password);
            try {
                Snackbar.make(findViewById(R.id.clRoot),"SignUp successfull...",Snackbar.LENGTH_LONG).show();
                myAppDatabase.myDao().addUser(user);
                UserLogInSharedPreferences userLogInSharedPreferences = new UserLogInSharedPreferences(this);
                userLogInSharedPreferences.saveCurrentUserLoginDetails(user.getEmailId());
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            catch (SQLiteConstraintException e) {
                Snackbar.make(findViewById(R.id.clRoot),""+e.getMessage(),Snackbar.LENGTH_LONG).show();
            }

        }
    }

    private boolean isAllInputsValid()
    {

        if(edtEmailId.getText().toString().equals(""))
        {
            edtEmailId.requestFocus();
           // edtEmailId.setError("Empty email id !!!");
            Snackbar.make(findViewById(R.id.clRoot),"Empty email id !!!",Snackbar.LENGTH_LONG).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(edtEmailId.getText().toString()).matches())
        {
            edtEmailId.requestFocus();
           // edtEmailId.setError("Invalid email format !!!");
            Snackbar.make(findViewById(R.id.clRoot),"Invalid email format !!!",Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(edtName.getText().toString().equals(""))
        {
            edtName.requestFocus();
           // edtName.setError("Empty name !!!");
            Snackbar.make(findViewById(R.id.clRoot),"Empty name !!!",Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(edtPassword.getText().toString().length() < 6 || edtPassword.getText().toString().equals(""))
        {
            edtPassword.requestFocus();
           // edtPassword.setError("length must be greater than 6");
            Snackbar.make(findViewById(R.id.clRoot),"length must be greater than 6",Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(edtConfirmPassword.getText().toString().equals(""))
        {
            edtConfirmPassword.requestFocus();
           // edtConfirmPassword.setError("length must be greater than 6");
            Snackbar.make(findViewById(R.id.clRoot),"length must be greater than 6",Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(!edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString()))
        {
            edtConfirmPassword.requestFocus();
           // edtConfirmPassword.setError("Password don't match !!!");
            Snackbar.make(findViewById(R.id.clRoot),"Password don't match !!!",Snackbar.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
