package com.example.filmapp.ui.films_list;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapp.R;
import com.example.filmapp.data.models.Film;

import com.example.filmapp.databinding.ItemFilmBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {
    private List<Film> films = new ArrayList<>();
    public String filmIdKey = "filmKey";
    public int position;

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(films.get(position));
        this.position = position;
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
            Bundle bundle = new Bundle();
            bundle.putString(filmIdKey, film.getId());
            itemView.setOnClickListener(Navigation.createNavigateOnClickListener
                    (R.id.action_filmsFragment_to_FIlmInfoFragment, bundle));
            System.out.println(bundle.getString(filmIdKey));
            binding.titleFilm.setText(film.getTitle());
            binding.description.setText(film.getDescription());
        }
    }
}
