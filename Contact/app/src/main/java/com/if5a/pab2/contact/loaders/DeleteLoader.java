package com.if5a.pab2.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5a.pab2.contact.databases.UserDatabase;
import com.if5a.pab2.contact.entities.User;

public class DeleteLoader extends AsyncTaskLoader {
    private int userId;
    private UserDatabase db;

    public DeleteLoader(@NonNull Context context) {
        super(context);
        this.userId = userId;
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        return db.userDao().deleteUser(userId);
    }
}
