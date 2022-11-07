package com.if5a.pab2.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5a.pab2.contact.databases.UserDatabase;
import com.if5a.pab2.contact.entities.User;

public class UpdateDataLoader extends AsyncTaskLoader {
    private User user;
    private UserDatabase db;

    public UpdateDataLoader(@NonNull Context context, User user) {
        super(context);
        this.user = user;
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        return db.userDao().updateUser(user);
    }
}
