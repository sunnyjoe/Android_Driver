package com.example.jiaoqing.myapplication3;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

/**
 * Created by jiaoqing on 16/8/16.
 */
public class PopupMenuEventHandler implements PopupMenu.OnMenuItemClickListener {
    Context context;
    private PopupMenuCallBack delegate;

    public  PopupMenuEventHandler(Context context, PopupMenuCallBack delegate){
        this.context = context;
        this.delegate = delegate;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_delete:
                delegate.popupMenuDidClickDelete();
                break;
            case R.id.item_update:
                delegate.popupMenuDidClickUpdate();
                break;
        }
        return false;
    }
}
