package fr.iutblagnac.devmobtp1p2.model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.iutblagnac.devmobtp1p2.R;

public class TodoListAdapter extends BaseAdapter {
    Context c;
    LayoutInflater li;
    TodoListData tld;

    public TodoListAdapter(Context _c, TodoListData _tld) {
        this.c = _c;
        this.li = LayoutInflater.from(this.c);
        this.tld = _tld;
    }

    @Override
    public int getCount() {
        return this.tld.size();
    }

    @Override
    public Todo getItem(int i) {
        return this.tld.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.tld.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LinearLayout ll;
        if (convertView == null) {
            ll = (LinearLayout) li.inflate(R.layout.todo_view, null);
        } else {
            ll = (LinearLayout) convertView;
        }
        TextView tv = ll.findViewById(R.id.tv_todoviewer_titre_id);

        String todo = this.tld.get(i).getTodo();
        if(todo.length() > 40){
            todo = this.tld.get(i).getTodo().substring(0,40) + "(...)";
        }

        String comment = this.tld.get(i).getComment();
        if(comment.length() > 40){
            comment =  this.tld.get(i).getComment().substring(0,40) + "(...)";
        }
        String with = "";
        if(!this.tld.get(i).getWithWho().equals("")){
            with = " with " + this.tld.get(i).getWithWho() + " " + comment;
        }

        tv.setText(this.tld.get(i).getDayDue() + "/" + this.tld.get(i).getMonthDue() + "/" + this.tld.get(i).getYearDue() + ", " +
                this.tld.get(i).getId() + " " + this.tld.get(i).getTitle() + ", " + todo + with
        );

        if(i % 2 == 0){
            tv.setTextColor(Color.BLACK);
        }else{
            tv.setTextColor(Color.GRAY);
        }
        return tv;
    }
}
