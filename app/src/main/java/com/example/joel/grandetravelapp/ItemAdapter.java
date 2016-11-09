package com.example.joel.grandetravelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<PackageItem> {

    public ItemAdapter(Context context, ArrayList<PackageItem> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Get item from list view
        PackageItem item = getItem(position);

        //Create view from xml
        View v = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);

        //Get resources
        TextView tvPackageIndex = (TextView) v.findViewById(R.id.textViewPackageIndex);
        TextView tvPackageName = (TextView) v.findViewById(R.id.textViewPackageName);
        TextView tvPackageLocation = (TextView) v.findViewById(R.id.textViewPackageLocation);
        TextView tvPackageDescription = (TextView) v.findViewById(R.id.textViewPackageDescription);
        TextView tvPackageRating = (TextView) v.findViewById(R.id.textViewPackageRating);
        TextView tvPackagePrice = (TextView) v.findViewById(R.id.textViewPackagePrice);
        Button btnPackageBook = (Button) v.findViewById(R.id.buttonPackageBook);

        //Fill in values in view
        tvPackageIndex.setText(Integer.toString(position));
        tvPackageName.setText(item.getName());
        tvPackageLocation.setText(item.getLocation());
        tvPackageDescription.setText(item.getDescription());
        tvPackageRating.setText(Double.toString(item.getRating()));
        tvPackagePrice.setText(Double.toString(item.getPrice()));
        //btnPackageBook.setText(item.getName());
        btnPackageBook.setTag(item.getId());

        /*
        //Set onClickListener for checkbox
        cbItem.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                //Get resources
                CheckBox cbItem = (CheckBox) v;

                //Set checkbox animation
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.checkboxanim);
                v.startAnimation(animation);

                //Update status in database
                ShoppingItemDataSource itemDataSource = new ShoppingItemDataSource(getContext());
                itemDataSource.open();
                //ShoppingItem currentShoppingItem = itemDataSource.getById( ((ShoppingItem)cbItem.getTag()).getId() );
                ShoppingItem currentItem = (ShoppingItem)cbItem.getTag();
                itemDataSource.setChecked( currentItem.getId() , cbItem.isChecked());
                itemDataSource.close();

                //Prepare toast message
                String itemName=((ShoppingItem)cbItem.getTag()).getName().toString();
                String itemStatusMsg;

                if (cbItem.isChecked())
                    itemStatusMsg=" acquired.";
                else
                    itemStatusMsg=" removed.";

                Toast.makeText(getContext(),itemName + itemStatusMsg,Toast.LENGTH_SHORT).show();

            }
        });
        */

        return v;
    }


}
