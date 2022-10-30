package com.if5a.booksdictionary.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BooksDictionary implements Parcelable {
    private int id;

    public BooksDictionary(){

    }

    protected BooksDictionary(Parcel in) {
        id = in.readInt();
        ISBN = in.readString();
        Book_title = in.readString();
        Book_Author = in.readString();
        Year_of_Publish = in.readString();
        Publisher = in.readString();
        image_url_s = in.readString();
        image_url_m = in.readString();
        image_url_l = in.readString();
    }

    public static final Creator<BooksDictionary> CREATOR = new Creator<BooksDictionary>() {
        @Override
        public BooksDictionary createFromParcel(Parcel in) {
            return new BooksDictionary(in);
        }

        @Override
        public BooksDictionary[] newArray(int size) {
            return new BooksDictionary[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String ISBN;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBook_title() {
        return Book_title;
    }

    public void setBook_title(String book_title) {
        Book_title = book_title;
    }

    public String getBook_Author() {
        return Book_Author;
    }

    public void setBook_Author(String book_Author) {
        Book_Author = book_Author;
    }

    public String getYear_of_Publish() {
        return Year_of_Publish;
    }

    public void setYear_of_Publish(String year_of_Publish) {
        Year_of_Publish = year_of_Publish;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getImage_url_s() {
        return image_url_s;
    }

    public void setImage_url_s(String image_url_s) {
        this.image_url_s = image_url_s;
    }

    public String getImage_url_m() {
        return image_url_m;
    }

    public void setImage_url_m(String image_url_m) {
        this.image_url_m = image_url_m;
    }

    public String getImage_url_l() {
        return image_url_l;
    }

    public void setImage_url_l(String image_url_l) {
        this.image_url_l = image_url_l;
    }

    private String Book_title;
    private String Book_Author;
    private String Year_of_Publish;
    private String Publisher;
    private String image_url_s;
    private String image_url_m;
    private String image_url_l;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(ISBN);
        parcel.writeString(Book_title);
        parcel.writeString(Book_Author);
        parcel.writeString(Year_of_Publish);
        parcel.writeString(Publisher);
        parcel.writeString(image_url_s);
        parcel.writeString(image_url_m);
        parcel.writeString(image_url_l);
    }
}
