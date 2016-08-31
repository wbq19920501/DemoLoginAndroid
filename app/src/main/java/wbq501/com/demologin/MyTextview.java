package wbq501.com.demologin;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2016/8/12.
 */
public class MyTextview extends TextView{
    public MyTextview(Context context) {
        super(context);
    }

    public MyTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
