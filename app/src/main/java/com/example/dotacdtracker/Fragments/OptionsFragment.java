package com.example.dotacdtracker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dotacdtracker.MainActivity;
import com.example.dotacdtracker.R;
import com.example.dotacdtracker.TrackerActivity;

import java.util.Objects;

public class OptionsFragment extends DialogFragment {

    Button lvl;
    CheckBox aghs;
    CheckBox talent;
    CheckBox octarine;
    CheckBox quickening;
    CheckBox prism;

    public static OptionsFragment newInstance() {
        return new OptionsFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.options_fragment, container);
        lvl = view.findViewById(R.id.lvl);
        aghs = view.findViewById(R.id.aghs);
        talent = view.findViewById(R.id.talent);
        octarine = view.findViewById(R.id.octarine);
        quickening = view.findViewById(R.id.quickening);
        prism = view.findViewById(R.id.prism);

        quickening.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((TrackerActivity) Objects.requireNonNull(getActivity())).onQuickeningClicked(quickening, prism);
            }
        });
        prism.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((TrackerActivity) Objects.requireNonNull(getActivity())).onPrismClicked(prism, quickening);
            }
        });

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        boolean hasLvl = getArguments().getBoolean("lvl");
        boolean hasAghs = getArguments().getBoolean("aghs");
        boolean hasTalent = getArguments().getBoolean("talent");
        int curr_lvl = getArguments().getInt("curr_lvl");
        boolean curr_aghs = getArguments().getBoolean("curr_aghs");
        boolean curr_talent = getArguments().getBoolean("curr_talent");
        boolean has_octarine = getArguments().getBoolean("octarine");
        boolean has_quickening = getArguments().getBoolean("quickening");
        boolean has_prism = getArguments().getBoolean("prism");

        if(!hasLvl){
            lvl.setEnabled(false);
        }else{
            switch (curr_lvl){
                case 0:
                    lvl.setText(getString(R.string.ult_lvl1));
                    break;
                case 1:
                    lvl.setText(getString(R.string.ult_lvl2));
                    break;
                case 2:
                    lvl.setText(getString(R.string.ult_lvl3));
                    break;
            }
        }
        if(!hasAghs){
            aghs.setEnabled(false);
        }else{
            aghs.setChecked(curr_aghs);
        }
        if(!hasTalent){
            talent.setEnabled(false);
        }else{
            talent.setChecked(curr_talent);
        }
        octarine.setChecked(has_octarine);
        quickening.setChecked(has_quickening);
        prism.setChecked(has_prism);

    }
}
