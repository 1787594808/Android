package com.example.bookstore.yuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.bookstore.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClassifyAdapter extends ArrayAdapter<Classify> {

    private int resourseId;

    public ClassifyAdapter(@NonNull Context context, int resource, List<Classify> objects) {
        super(context, resource,objects);
        resourseId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Classify classify=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourseId,parent,false);
        TextView classifyName=(TextView)view.findViewById(R.id.classify_name);
        classifyName.setText(classify.getclassifyName());
        return view;
    }
}
