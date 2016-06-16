package org.am.dba.ms.mystudents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.am.dba.ms.mystudents.R;
import org.am.dba.ms.mystudents.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D Beschi Antony on 9/5/2016.
 */
public class ProfileArrayAdapter extends ArrayAdapter<Student> {
    private List<Student> students = new ArrayList<>();
    private final Context context;

    public ProfileArrayAdapter(Context context, List<Student> students) {
        super(context, R.layout.activity_profile, students);
        this.context = context;
        this.students = students;
    }


    //View Holder design Pattern
    static class ViewHolder{
        public ImageView iv;
        public TextView fl;
        public TextView sl;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = li.inflate(R.layout.activity_profile_row, parent, false);
            ViewHolder vh = new ViewHolder();
            vh.iv = (ImageView)rowView.findViewById(R.id.icon);
            vh.fl = (TextView)rowView.findViewById(R.id.firstLine);
            vh.sl = (TextView)rowView.findViewById(R.id.secondLine);
            rowView.setTag(vh);
        }
        //Filling details
        ViewHolder vh = (ViewHolder)rowView.getTag();
        //Loading Image
        Picasso.with(context)
                .load(students.get(position).getProfileImg())
                .fit()
                .placeholder(R.drawable.logo)
                .into(vh.iv);
        vh.fl.setText(students.get(position).getName());
        vh.sl.setText(students.get(position).getStd());
        return  rowView;
    }
}
