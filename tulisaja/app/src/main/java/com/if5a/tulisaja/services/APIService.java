package com.if5a.tulisaja.services;

import com.if5a.tulisaja.models.Post;
import com.if5a.tulisaja.models.ValueData;
import com.if5a.tulisaja.models.ValueNoData;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("loginUser")
    Call<ValueNoData> loginUser(@Field("key") String key,
                                @Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("registerUser")
    Call<ValueNoData> registerUser(@Field("key") String key,
                                   @Field("username") String username,
                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("getAllPost")
    Call<ValueData<Post>> getPost(@Field("key") String key);

    @FormUrlEncoded
    @POST("insertPost")
    Call<ValueNoData> addPost(@Field("key") String key,
                              @Field("username") String username,
                              @Field("content") String content);

    @FormUrlEncoded
    @POST("updatePost")
    Call<ValueNoData> updatePost(@Field("key") String key,
                                 @Field("id") String id,
                                 @Field("content") String content);
    @FormUrlEncoded
    @POST("updatePost")
    Call<ValueNoData> deletePost(@Field("key") String key,
                                 @Field("id") String id);
}
