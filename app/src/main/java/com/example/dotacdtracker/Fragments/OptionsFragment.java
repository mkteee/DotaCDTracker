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

import com.example.dotacdtracker.R;

public class OptionsFragment extends DialogFragment {

    Button lvl;
    CheckBox aghs;
    CheckBox talent;

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
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        boolean hasLvl = getArguments().getBoolean("lvl");
        boolean hasAghs = getArguments().getBoolean("aghs");
        boolean hasTalent = getArguments().getBoolean("talent");
        if(!hasLvl){
            lvl.setEnabled(false);
        }
        if(!hasAghs){
            aghs.setEnabled(false);
        }
        if(!hasTalent){
            talent.setEnabled(false);
        }
    }
}
