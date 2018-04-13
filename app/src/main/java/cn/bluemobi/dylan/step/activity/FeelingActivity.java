package cn.bluemobi.dylan.step.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cn.bluemobi.dylan.step.R;

public class FeelingActivity extends AppCompatActivity {
    private String[] data = {"Apple", "Banana",
    "Orange", "Watermelon", "Pear"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_feeling);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                FeelingActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view_feeling);
        listView.setAdapter(adapter);
    }
}
