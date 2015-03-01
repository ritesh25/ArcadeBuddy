package com.dquid.baytektestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by shobhitg on 2/28/2015.
 */
public class UserAdapter extends BaseAdapter {

    Context context;
    UserModel[] users;
    private static LayoutInflater inflater = null;

    public UserAdapter(Context context, UserModel[] users) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.users = users;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return users.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return users[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.user_item, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        ImageView imageView = (ImageView) vi.findViewById(R.id.imageView1);

        text.setText(users[position].getName());
        Picasso.with(context).load(users[position].getAvatarUrl()).into(imageView);

        return vi;
    }
}