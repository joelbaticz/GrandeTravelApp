package com.example.joel.grandetravelapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FragmentMain extends Fragment {


    private ItemAdapter ia=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View mainView = inflater.inflate(R.layout.fragment_main, container, false);


        //Create the list

        //Get resources
        ListView lv = (ListView) mainView.findViewById(R.id.lvPackageList);

        //Populate list view with items
        PackageItemDataSource itemDataSource = new PackageItemDataSource(this.getActivity().getApplicationContext());
        itemDataSource.open();
        List<PackageItem> actualItems = itemDataSource.getAll();
        itemDataSource.close();

        ia = new ItemAdapter(this.getActivity().getApplicationContext(), (ArrayList) actualItems);
        lv.setAdapter(ia);



        return mainView;


    }
}
