package com.smartjaguardevelopers.assignmentapp.sign_in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.smartjaguardevelopers.assignmentapp.R;
import com.smartjaguardevelopers.assignmentapp.home.HomeActivity;
import com.smartjaguardevelopers.assignmentapp.room_database.MyAppDatabase;
import com.smartjaguardevelopers.assignmentapp.user.UserLogInSharedPreferences;

public class SignInActivity extends AppCompatActivity {

    private EditText edtEmailId, edtPassword;

    public MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtEmailId = (EditText)findViewById(R.id.edtEmailId);
        edtPassword = (EditText)findViewById(R.id.edtPassword);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class,"usersDb").allowMainThreadQueries().build();

    }

    public void signIn(View view)
    {
        if(isAllInputsValid())
        {
            // checking for credentials
            String password=null;

            try {
                password = myAppDatabase.myDao().getUserPassword(edtEmailId.getText().toString());

                if(password==null || password.equals(""))
                {
                    Snackbar.make(findViewById(R.id.clRoot),"User not found !!!",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if(edtPassword.getText().toString().equals(password))
                {
                    Snackbar.make(findViewById(R.id.clRoot),"Login Successfull....",Snackbar.LENGTH_LONG).show();
                    UserLogInSharedPreferences userLogInSharedPreferences = new UserLogInSharedPreferences(this);
                    userLogInSharedPreferences.saveCurrentUserLoginDetails(edtEmailId.getText().toString());
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else
                {
                    Snackbar.make(findViewById(R.id.clRoot),"Invalid login credentials !!!",Snackbar.LENGTH_LONG).show();
                }
            } catch (SQLiteConstraintException e) {
                Log.i("Santosh","Exception : "+e.getMessage());
            }
            catch (Exception e)
            {
                Log.i("Santosh","Exception : "+e.getMessage());
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


        if(edtPassword.getText().toString().equals(""))
        {
            edtPassword.requestFocus();
            // edtPassword.setError("Empty password !!!");
            Snackbar.make(findViewById(R.id.clRoot),"Empty password !!!",Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
