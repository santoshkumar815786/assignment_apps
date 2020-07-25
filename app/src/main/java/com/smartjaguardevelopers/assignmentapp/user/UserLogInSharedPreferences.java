package com.smartjaguardevelopers.assignmentapp.user;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLogInSharedPreferences
{
    private static final String TAG_USER_DETAILS = "CurrentUserDetails";
    private static final String TAG_USER_EMAIL = "UserEmail";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserLogInSharedPreferences(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(TAG_USER_DETAILS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveCurrentUserLoginDetails(String emailId)
    {
        editor.putString(TAG_USER_EMAIL, emailId);
        editor.commit();
    }

    public String getCurrentUserEmail()
    {
        return sharedPreferences.getString(TAG_USER_EMAIL,"xyz@gmail.com");
    }
}
