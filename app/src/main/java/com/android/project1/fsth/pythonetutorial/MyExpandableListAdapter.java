package com.android.project1.fsth.pythonetutorial;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ParentRow> parentRowList;
    private ArrayList<ParentRow> originalList;

    public MyExpandableListAdapter(Context context
            , ArrayList<ParentRow> originalList) {
        this.context = context;
        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);
    }

    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentRowList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentRowList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentRowList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentRow parentRow = (ParentRow) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_row, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.parent_text);
        TextView parent_count=(TextView)convertView.findViewById(R.id.parent_count);
        heading.setText(parentRow.getName().trim());
        parent_count.setText(String.valueOf(groupPosition+1));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildRow childRow = (ChildRow) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row, null);
        }


        final TextView child_count=convertView.findViewById(R.id.child_count);
        int gp=groupPosition+1;
        int child_pos=childPosition+1;
        child_count.setText(String.valueOf(gp+"."+child_pos));
        final TextView childText = (TextView) convertView.findViewById(R.id.child_text);
        childText.setText(childRow.getText().trim());

        final View finalConvertView = convertView;
        childText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(finalConvertView.getContext()
                        , childText.getText()
                        , Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,Web_Test.class);
                intent.putExtra("get",childText.getText().toString());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        parentRowList.clear();

        if (query.isEmpty()) {
            parentRowList.addAll(originalList);
        }
        else {
            for (ParentRow parentRow : originalList) {
                ArrayList<ChildRow> childList = parentRow.getChildList();
                ArrayList<ChildRow> newList = new ArrayList<ChildRow>();

                for (ChildRow childRow: childList) {
                    if (childRow.getText().toLowerCase().contains(query)) {
                        newList.add(childRow);
                    }
                }
                if (newList.size() > 0) {
                    ParentRow nParentRow = new ParentRow(parentRow.getName(), newList);
                    parentRowList.add(nParentRow);
                }
            }

        } // end else

        notifyDataSetChanged();
    }
}
