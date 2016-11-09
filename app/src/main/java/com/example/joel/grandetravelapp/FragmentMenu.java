package com.example.joel.grandetravelapp;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentMenu extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View menuView = inflater.inflate(R.layout.fragment_menu, container, false);

        TextView syncIcon=(TextView) menuView.findViewById(R.id.syncIcon);

        Typeface typeface= Typeface.createFromAsset(getActivity().getAssets(),"fonts/fontawesome-webfont.ttf");
        syncIcon.setTypeface(typeface);

        return menuView;
    }
}
