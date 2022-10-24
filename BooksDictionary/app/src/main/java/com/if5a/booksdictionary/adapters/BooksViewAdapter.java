package com.if5a.booksdictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5a.booksdictionary.R;
import com.if5a.booksdictionary.models.BooksDictionary;

import java.util.ArrayList;

public class BooksViewAdapter extends RecyclerView.Adapter<BooksViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BooksDictionary> data = new ArrayList<>();

    public BooksViewAdapter(Context context){
        this.context = context;
    }

    public void setData(ArrayList<BooksDictionary> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BooksViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        holder.tvTitle.setText(data.get(pos).getBook_title());
        holder.tvBookAuthor.setText(data.get(pos).getBook_Author());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvBookAuthor;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvBookAuthor = itemView.findViewById(R.id.tv_book_author);
            ivImage = itemView.findViewById(R.id.iv_image);
        }
    }
}
