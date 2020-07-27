package com.dm.recyclerview2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> moviesData;

    private OnItemClickListener listener;

    public MovieAdapter(List<Movie> movies) {
        this.moviesData = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movie currentMovie = moviesData.get(position);
        holder.bind(currentMovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentMovie);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClickListener(currentMovie);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView duration;
        private TextView year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.coverImage);
            title = itemView.findViewById(R.id.titleTextView);
            duration = itemView.findViewById(R.id.durationTextView);
            year = itemView.findViewById(R.id.yearTextView);
        }

        public void bind(Movie movie) {
            image.setImageBitmap(movie.getCoverImage());
            title.setText(movie.getTitle());
            duration.setText(duration.getContext().getString(R.string.minute, movie.getDuration()));
            year.setText(movie.getYear() +"");
        }
    }

    interface OnItemClickListener {
        void onItemClick(Movie movie);

        void onItemLongClickListener(Movie movie);
    }
}
