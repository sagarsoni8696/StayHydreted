package om.example.stayhydrated;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import static om.example.stayhydrated.WaterQuantity.getQty350;


public class MainActivity extends AppCompatActivity {

    ListView historyListView;
    ArrayList<WaterQuantity> quantityHistoryList = new ArrayList<WaterQuantity>();
    static HistoryListView historyListViewAdapter;
    ProgressBar progressBar;
    static  int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyListViewAdapter = new HistoryListView(this, quantityHistoryList);

        // Set up history list adapter
        historyListView = findViewById(R.id.historyListView);
        historyListView.setAdapter(historyListViewAdapter);

        // Set up the progress bar and give it a default progress
        progressBar = findViewById(R.id.progressBar);

        // The three quantity buttons
        final ImageView dropletImage = findViewById(R.id.dropletImage);


        ImageView glassImage = findViewById(R.id.glassImage);
        ImageView bottleImage = findViewById(R.id.bottleImage);

        // Example: adding quantity to history list
        //quantityHistoryList.add(WaterQuantity.getQty50());
        //quantityHistoryList.add(WaterQuantity.getQty200());
       // quantityHistoryList.add(getQty350());
        //historyListViewAdapter.notifyDataSetChanged();

        //progressBar.setProgress(600);

        // On click listeners
        dropletImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                quantityHistoryList.add(WaterQuantity.getQty50());
                historyListViewAdapter.notifyDataSetChanged();
                progressBar.setProgress(count+=50);


            }
        });

        glassImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                quantityHistoryList.add(WaterQuantity.getQty200());
                historyListViewAdapter.notifyDataSetChanged();
                progressBar.setProgress(count+=200);
            }
        });

        bottleImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                quantityHistoryList.add(getQty350());
                historyListViewAdapter.notifyDataSetChanged();
                progressBar.setProgress(count+=350);

            }
        });





    }


}
