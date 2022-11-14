package com.if5a.pab2.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5a.pab2.contact.databases.UserDatabase;
import com.if5a.pab2.contact.entities.User;

public class InsertLoader extends AsyncTaskLoader<Boolean> {
    private User user;
    private UserDatabase db;

    public InsertLoader(@NonNull Context context, User user) {
        super(context);
        this.user = user;
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Boolean loadInBackground() {
        db.userDao().insertUser(user);
        return true;
    }
}
