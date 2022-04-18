package com.example.filmapp.ui.FilmInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.FragmentFIlmInfoBinding;
import com.example.filmapp.databinding.FragmentFIlmInfoBinding;
import com.example.filmapp.databinding.ItemFilmBinding;
import com.example.filmapp.ui.films_list.FilmsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FilmInfoAdapter extends RecyclerView.Adapter<FilmInfoAdapter.FilmInfoViewHolder>{
    private List<Film> Films = new ArrayList<>();


    public List<Film> getFilms() {
        return Films;
    }

    public void setFilms(Film films) {
        this.Films.add(films);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmInfoAdapter.FilmInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentFIlmInfoBinding binding =
        FragmentFIlmInfoBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new FilmInfoAdapter.FilmInfoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmInfoAdapter.FilmInfoViewHolder holder, int position) {
        holder.onBind(Films.get(position));
    }

    @Override
    public int getItemCount() {
        return Films.size();
    }

    public class FilmInfoViewHolder extends RecyclerView.ViewHolder {
        private FragmentFIlmInfoBinding binding;
        public FilmInfoViewHolder(@NonNull FragmentFIlmInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {
        }
    }
}