package com.khahani.app.quakeeventlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dev on 7/29/2018.
 */

public class QuakeAdapter extends ArrayAdapter<Event> {
    public QuakeAdapter(@NonNull Context context, @NonNull List<Event> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null){
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.quake_item, parent, false);
        }

        Event event = getItem(position);

        TextView magTextView = view.findViewById(R.id.tv_mag);
        TextView feltTextView = view.findViewById(R.id.tv_felt);

        magTextView.setText(Double.toString(event.getMag()));
        feltTextView.setText(Integer.toString(event.getFelt()));

        return view;
    }
}
