package com.cnews.guji.smart.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.cnews.guji.smart.R;

/**
 * @package: HeaderView
 * @author： JSYL-DCL
 * @date: 2019/5/7
 * @describe： TODO
 * @email： 11442865
 */
public class HeaderView {
    private Activity context;
    public HeaderView(Activity context) {
        this.context = context;
    }
    public View getView() {
        return context.getLayoutInflater().inflate(R.layout.layout_refresh_header, null);
    }


}
