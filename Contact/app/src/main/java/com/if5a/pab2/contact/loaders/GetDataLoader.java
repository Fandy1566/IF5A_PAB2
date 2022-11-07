package com.if5a.pab2.contact.loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.if5a.pab2.contact.databases.UserDatabase;
import com.if5a.pab2.contact.entities.User;

import java.util.List;

public class GetDataLoader extends AsyncTaskLoader<List<User>> {
    private UserDatabase db;

    public GetDataLoader(@NonNull Context context) {
        super(context);
        db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public List<User> loadInBackground() {
        return db.userDao().getAllUsers();
    }
}
