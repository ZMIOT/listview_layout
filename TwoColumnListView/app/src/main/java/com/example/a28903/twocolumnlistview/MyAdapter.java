package com.example.a28903.twocolumnlistview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
/**
 *
 * @author 周明
 *
 */
public class MyAdapter extends BaseAdapter{
	protected Context context;
	protected LayoutInflater inflater;
	protected int resource;
	protected ArrayList<String> list;

	public MyAdapter(Context context, int resource, ArrayList<String> list){
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.resource = resource;
		if(list==null){
			this.list=new ArrayList<>();
		}else{
			this.list = list;
		}
	}

	@Override
	public int getCount() {
		if(list.size()%2>0) {
			return list.size()/2+1;
		} else {
			return list.size()/2;
		}
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null ) {
			convertView = inflater.inflate(resource, null);
			vh = new ViewHolder();
			vh.tv1=(TextView)convertView.findViewById(R.id.tv1);
			vh.tv2=(TextView)convertView.findViewById(R.id.tv2);
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder)convertView.getTag();
		}
		int distance =  list.size() - position*2;
		int cellCount = distance >= 2? 2:distance;
		final List<String> itemList = list.subList(position*2,position*2+cellCount);
		if (itemList.size() >0) {
			vh.tv1.setText(itemList.get(0));
			vh.tv1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(context, itemList.get(0), Toast.LENGTH_SHORT).show();
				}
			});
            if (itemList.size() >1){
            	vh.tv2.setVisibility(View.VISIBLE);
            	vh.tv2.setText(itemList.get(1));
            	vh.tv2.setOnClickListener(new OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					Toast.makeText(context, itemList.get(1), Toast.LENGTH_SHORT).show();
    				}
    			});
            }else{
            	vh.tv2.setVisibility(View.INVISIBLE);
            }
        }
		return convertView;
	}

	public static class ViewHolder{
		TextView tv1;
		TextView tv2;
	}
}
