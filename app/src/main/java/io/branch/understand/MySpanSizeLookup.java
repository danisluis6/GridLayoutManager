package io.branch.understand;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

/**
 * Created by lorence on 15/01/2018.
 *
 */

public class MySpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    int spanPos, spanCnt1, spanCnt2;

    public MySpanSizeLookup(int spanPos, int spanCnt1, int spanCnt2) {
        super();
        this.spanPos = spanPos;
        this.spanCnt1 = spanCnt1;
        this.spanCnt2 = spanCnt2;
    }

    @Override
    public int getSpanSize(int position) {
        Log.i("TAG", String.valueOf(position));
        Log.i("TAG", String.valueOf(spanPos));
        Log.i("TAG", String.valueOf(spanCnt1));
        Log.i("TAG", String.valueOf(spanCnt2));
        return (position % spanPos == 0 ? spanCnt2 : spanCnt1);
    }
}

