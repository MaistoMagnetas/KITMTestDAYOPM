package com.example.moksleivis.kitmtestodienaopm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> users = new ArrayList<User>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList<User> users) {
        this.context = context;
        this.users = users;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.list_view_items, null);
        TextView vardasPavarde = (TextView) convertView.findViewById(R.id.listviewName);
        TextView gimimoData = (TextView) convertView.findViewById(R.id.listviewGimimoData);
        TextView telNR = (TextView) convertView.findViewById(R.id.listviewTelNR);
        TextView klientoTipas = (TextView) convertView.findViewById(R.id.listviewKlientoTipas);
        vardasPavarde.setText(users.get(position).getVardasPavarde());
        gimimoData.setText(users.get(position).getGimimoData());
        telNR.setText(users.get(position).getTelNr());
        klientoTipas.setText(users.get(position).getKlientoTipas());
        return convertView;

    }
}