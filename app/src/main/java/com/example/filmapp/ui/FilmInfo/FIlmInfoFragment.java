package com.example.filmapp.ui.FilmInfo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.filmapp.App;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.FragmentFIlmInfoBinding;
import com.example.filmapp.ui.films_list.FilmsAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FIlmInfoFragment extends Fragment {
    private FragmentFIlmInfoBinding binding;
    private Film film = new Film();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFIlmInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle;
        bundle = getArguments();
        App.api.getFilmDetail(bundle.getString("filmKey")).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    film = (response.body());
                    SetInfo();
                } else {
                    Snackbar.make(
                            binding.getRoot(),
                            response.message(),
                            BaseTransientBottomBar.LENGTH_LONG
                    ).setBackgroundTint(Color.RED).show();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Snackbar.make(
                        binding.getRoot(),
                        t.getLocalizedMessage(),
                        BaseTransientBottomBar.LENGTH_LONG
                ).setBackgroundTint(Color.RED).show();
            }
        });
    }

    private void SetInfo() {
        binding.TVTitle.setText(film.getTitle());
        binding.diretor.setText("Director: " + film.getDirector());
        binding.releaseDate.setText("Release Date: " + film.getRelease_date());
        binding.runningTime.setText("Movie length: " + film.getRunning_time() + "min");
        Glide.with(binding.Pic).load(film.getMovie_banner()).into(binding.Pic);
        binding.FilmDescription.setText(film.getDescription());
        binding.TVTitle.setVisibility(View.VISIBLE);
        binding.diretor.setVisibility(View.VISIBLE);
        binding.releaseDate.setVisibility(View.VISIBLE);
        binding.runningTime.setVisibility(View.VISIBLE);
        binding.FilmDescription.setVisibility(View.VISIBLE);
        binding.Pic.setVisibility(View.VISIBLE);
    }
}