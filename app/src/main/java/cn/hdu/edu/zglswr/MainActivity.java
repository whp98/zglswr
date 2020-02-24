package cn.hdu.edu.zglswr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    //用于绑定相应的内容
    private ListView listview1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Resources res =getResources();
        //设置专用主题
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview1 = (ListView) findViewById(R.id.Lv);
        //输入名称列表
        final String[] name = res.getStringArray(R.array.name);
        //输入介绍列表
        final String[] message = res.getStringArray(R.array.details);
        //图片列表读取
        final TypedArray ta = getResources().obtainTypedArray(R.array.photo);
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        String name1;
        int pic;
        String content;
        for (int i=0;i<name.length;i++){

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("photo",ta.getResourceId(i,0));
            map1.put("name", name[i]);
            data.add(map1);
        }
        listview1.setAdapter(new SimpleAdapter(this, data, R.layout.item,new String[] { "photo", "name" }, new int[] { R.id.iv,R.id.tv_name }));
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                Bundle bundle = new Bundle();
                bundle.putInt("photo", ta.getResourceId(arg2,0));
                bundle.putString("message", message[arg2]);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(MainActivity.this, ShowActivity.class);
                Log.i("message", message[arg2]);
                startActivity(intent);
            }
        });

    }
}
