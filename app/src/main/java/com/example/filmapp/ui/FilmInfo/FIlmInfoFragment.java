package com.example.filmapp.ui.FilmInfo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.filmapp.App;
import com.example.filmapp.R;
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
    private List<Film> Films =  new ArrayList<>();
    private FilmInfoAdapter infoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         infoAdapter = new FilmInfoAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
                    Films.add(response.body());
                    binding.TVTitle.setText(Films.get(0).getTitle());
                    binding.diretor.setText("Director: " + Films.get(0).getDirector());
                    binding.releaseDate.setText("Release Date: " + Films.get(0).getRelease_date());
                    binding.runningTime.setText("Movie length: " + Films.get(0).getRunning_time() + "min");
                    Glide.with(binding.Pic).load(Films.get(0).getMovie_banner()).into(binding.Pic);
                    binding.FilmDescription.setText(Films.get(0).getDescription());
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
}