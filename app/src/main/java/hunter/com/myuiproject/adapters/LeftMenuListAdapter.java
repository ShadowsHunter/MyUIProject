package hunter.com.myuiproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hunter.com.myuiproject.R;

/**
 * 左侧的滑动菜单列表适配器
 * Created by hcgan on 2017/7/25.
 */

public class LeftMenuListAdapter extends BaseExpandableListAdapter {
    List<String> parentList = null;
    Map<String,List<String>> childList = null;
    LayoutInflater mLayoutInfater;
    public LeftMenuListAdapter(Context context) {
        super();
        mLayoutInfater = LayoutInflater.from(context);
        //从strings.xml配置文件中获取父级列表
        parentList = Arrays.asList(context.getResources().getStringArray(R.array.menu_list));
        childList = new HashMap<String,List<String>>();
        childList.put(parentList.get(0),Arrays.asList(context.getResources().getStringArray(R.array.menu_child_ui)));
        childList.put(parentList.get(1),Arrays.asList(context.getResources().getStringArray(R.array.menu_child_anim)));
        childList.put(parentList.get(2),Arrays.asList(context.getResources().getStringArray(R.array.menu_child_http)));
        childList.put(parentList.get(3),Arrays.asList(context.getResources().getStringArray(R.array.menu_child_navigation)));
        childList.put(parentList.get(4),Arrays.asList(context.getResources().getStringArray(R.array.menu_child_function)));
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        String key = parentList.get(i);
        return childList.get(key).size();
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        String key = parentList.get(i);
        return childList.get(key).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i+i1;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ParentViewHolder holder ;
        if(view == null){
            view = mLayoutInfater.inflate(R.layout.item_left_menu_parent,null);
            holder = new ParentViewHolder();
            view.setTag(holder);
        }else {
            holder = (ParentViewHolder) view.getTag();
        }
        holder.tv = (TextView)view.findViewById(R.id.tv_leftMenu_parent);
        holder.tv.setText(parentList.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder holder ;
        if(view == null){
            view = mLayoutInfater.inflate(R.layout.item_left_menu_child,null);
            holder = new ChildViewHolder();
            view.setTag(holder);
        }else {
            holder = (ChildViewHolder) view.getTag();
        }
        holder.tv = (TextView)view.findViewById(R.id.tv_leftMenu_child);
        holder.tv.setText(childList.get(parentList.get(i)).get(i1));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private class ParentViewHolder{
        TextView tv;
    }
    private class ChildViewHolder{
        TextView tv;
    }
}
