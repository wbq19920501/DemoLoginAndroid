package wbq501.com.demologin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by admin on 2016/7/20.
 */
public class HelloActivity extends Activity{
    private Integer userId;
    private String username;
    private TextView tvWelcome;

    public static void actionStart(Context context, Integer userId,
                                   String username) {
        Intent intent = new Intent(context, HelloActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("username", username);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        this.userId = getIntent().getIntExtra("userId", 0);
        this.username = getIntent().getStringExtra("username");
        this.tvWelcome = (TextView) this.findViewById(R.id.tvWelcome);
        if (userId == 0 || username == null || "".equals(username)) {
            return;
        } else {
            this.tvWelcome.setText("欢迎您：" + userId + "号会员：" + username + "！");
        }
    }
}
