package com.example.filmapp.ui.films_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.ItemFilmBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {
    private List<Film> films = new ArrayList<>();
    public String filmIdKey = "filmKey";

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new FilmViewHolder(binding);
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(films.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filmId;
                filmId = films.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString(filmIdKey, filmId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    protected class FilmViewHolder extends RecyclerView.ViewHolder {
        private ItemFilmBinding binding;
        public FilmViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {

            binding.title.setText(film.getTitle());
            binding.description.setText(film.getDescription());
        }
    }
}
