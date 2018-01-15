package io.branch.understand;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener{

    private RecyclerView myRecyclerView;
    private RecyclerViewAdapter myRecyclerViewAdapter;

    EditText nameField;
    Button btnAdd;

    RadioGroup optGroupLayout;
    RadioButton optLinearLayoutHorizontal;
    RadioButton optLinearLayoutVertical;
    RadioButton optGridLayoutHorizontal;
    RadioButton optGridLayoutVertical;

    private LinearLayoutManager linearLayoutManagerVertical;
    private LinearLayoutManager linearLayoutManagerHorizontal;
    private GridLayoutManager gridLayoutManagerVertical;
    private GridLayoutManager gridLayoutManagerHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = findViewById(R.id.myrecyclerview);

        linearLayoutManagerVertical =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManagerHorizontal =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        gridLayoutManagerVertical =
                new GridLayoutManager(this,
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL,
                        false);
        gridLayoutManagerHorizontal =
                new GridLayoutManager(this,
                        3, //The number of rows in the grid
                        LinearLayoutManager.HORIZONTAL,
                        false);

        //set SpanSizeLookup()
        // gridLayoutManagerVertical.setSpanSizeLookup(new MySpanSizeLookup(5, 1, 2));
        gridLayoutManagerVertical.setSpanSizeLookup(new MySpanSizeLookup(5, 1, 2));

        gridLayoutManagerHorizontal.setSpanSizeLookup(new MySpanSizeLookup(4, 1, 3));

        myRecyclerViewAdapter = new RecyclerViewAdapter(this);
        myRecyclerViewAdapter.setOnItemClickListener(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManagerVertical);

        //Add MyItemDecoration
        myRecyclerView.addItemDecoration(new MyItemDecoration());

        nameField = findViewById(R.id.namefield);
        btnAdd = findViewById(R.id.addbutton);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String newName = nameField.getText().toString();

                if(!newName.equals("")){
                    if (myRecyclerViewAdapter.getItemCount()>1) {
                        myRecyclerViewAdapter.add(1, newName);
                    }else {
                        myRecyclerViewAdapter.add(0, newName);
                    }
                }
            }
        });

        optGroupLayout = findViewById(R.id.optGroupLayout);
        optLinearLayoutHorizontal = findViewById(R.id.optLinearLayoutHorizontal);
        optLinearLayoutVertical = findViewById(R.id.optLinearLayoutVertical);
        optGridLayoutHorizontal = findViewById(R.id.optGridLayoutHorizontal);
        optGridLayoutVertical = findViewById(R.id.optGridLayoutVertical);
        optGroupLayout.setOnCheckedChangeListener(optLayoutCheckedChangeListener);

        //pre-load dummy items
        myRecyclerViewAdapter.add(0, "SpanSizeLookup");
        myRecyclerViewAdapter.add(0, "ItemDecoration");
        myRecyclerViewAdapter.add(0, "GridLayoutManager");
        myRecyclerViewAdapter.add(0, "LinearLayoutManager");
        myRecyclerViewAdapter.add(0, "RecyclerViewAdapter");
        myRecyclerViewAdapter.add(0, "RecyclerView example");
        myRecyclerViewAdapter.add(0, "RecyclerView");
        myRecyclerViewAdapter.add(0, "android-er.blogspot.com");
        myRecyclerViewAdapter.add(0, "android-er");
        myRecyclerViewAdapter.add(0, "android");

    }

    private RadioGroup.OnCheckedChangeListener optLayoutCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(optLinearLayoutVertical.isChecked()){
                        myRecyclerView.setLayoutManager(linearLayoutManagerVertical);
                    }else if(optLinearLayoutHorizontal.isChecked()){
                        myRecyclerView.setLayoutManager(linearLayoutManagerHorizontal);
                    }else if(optGridLayoutHorizontal.isChecked()){
                        myRecyclerView.setLayoutManager(gridLayoutManagerHorizontal);
                    }else if(optGridLayoutVertical.isChecked()){
                        myRecyclerView.setLayoutManager(gridLayoutManagerVertical);
                    }
                }
            };


    @Override
    public void onItemClick(RecyclerViewAdapter.ItemHolder item, int position) {
        Toast.makeText(this,
                "Remove " + position + " : " + item.getItemName(),
                Toast.LENGTH_SHORT).show();
        myRecyclerViewAdapter.remove(position);
    }
}