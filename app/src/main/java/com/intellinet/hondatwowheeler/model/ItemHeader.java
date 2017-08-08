package com.intellinet.hondatwowheeler.model;

import com.intellinet.hondatwowheeler.R;

import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

/**
 * Created by Administrator on 8/2/2017.
 */

public class ItemHeader extends DataBean {
    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    private String headerText;
    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.steaky_header;
    }
    @Override
    public boolean shouldSticky() {
        return true;
    }
}
