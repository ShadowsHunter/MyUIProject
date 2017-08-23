package hunter.com.myuiproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import hunter.com.myuiproject.MainActivity;
import hunter.com.myuiproject.R;
import hunter.com.myuiproject.adapters.LeftMenuListAdapter;

/**
 * 左侧滑动菜单
 * Created by hcgan on 2017/7/24.
 */
public class SlideMenuFragment extends Fragment implements ExpandableListView.OnChildClickListener,
        ExpandableListView.OnGroupExpandListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ExpandableListView listView;
    LeftMenuListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_slide_menu, null);
        listView = (ExpandableListView)view.findViewById(R.id.listview_slideMenu);
        adapter = new LeftMenuListAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnGroupExpandListener(this);
        listView.setOnChildClickListener(this);
        return view;
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        String title = adapter.getChild(i,i1).toString();
        if(getActivity() instanceof MainActivity){
            MainActivity activity = (MainActivity)getActivity();
            activity.changeFragment(i,i1,title);
        }
        return false;
    }

    @Override
    public void onGroupExpand(int pos) {
        for(int i =0;i<adapter.getGroupCount();i++){
            if(pos!=i&&listView.isGroupExpanded(pos)){
                listView.collapseGroup(i);
            }
        }
    }
}
