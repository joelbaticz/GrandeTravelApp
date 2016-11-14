package com.example.joel.grandetravelapp;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentMain extends Fragment {


    public ItemAdapter ia=null;
    public ListView lv;

    LayoutInflater inflater;
    ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.inflater=inflater;
        this.container=container;


        View mainView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView textViewAddress=(TextView)mainView.findViewById(R.id.textViewAddress);
        TextView textViewPhone=(TextView)mainView.findViewById(R.id.textViewPhone);
        TextView textViewEmail=(TextView)mainView.findViewById(R.id.textViewEmail);
        TextView textViewHours=(TextView)mainView.findViewById(R.id.textViewHours);


        Typeface typeface= Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"fonts/fontawesome-webfont.ttf");


        textViewAddress.setTypeface(typeface);
        textViewPhone.setTypeface(typeface);
        textViewEmail.setTypeface(typeface);
        textViewHours.setTypeface(typeface);


        //Create the list

        //Get resources
        lv = (ListView) mainView.findViewById(R.id.lvPackageList);

        //Populate list view with items
        PackageItemDataSource itemDataSource = new PackageItemDataSource(this.getActivity().getApplicationContext());
        itemDataSource.open();
        List<PackageItem> actualItems = itemDataSource.getAll();
        itemDataSource.close();

        ia = new ItemAdapter(this.getActivity().getApplicationContext(), (ArrayList) actualItems);
        lv.setAdapter(ia);



        return mainView;
    }



    public void refreshView()
    {

        View mainView = inflater.inflate(R.layout.fragment_main, container, false);

        lv = (ListView) mainView.findViewById(R.id.lvPackageList);

        //Populate list view with items
        PackageItemDataSource itemDataSource = new PackageItemDataSource(this.getActivity().getApplicationContext());
        itemDataSource.open();
        List<PackageItem> actualItems = itemDataSource.getAll();
        itemDataSource.close();

        ia = new ItemAdapter(this.getActivity().getApplicationContext(), (ArrayList) actualItems);
        lv.setAdapter(ia);


        ia.notifyDataSetChanged();
    }
}
