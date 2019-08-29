package om.example.stayhydrated;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class HistoryListView extends ArrayAdapter {
    private final Activity context;
    private long LastClickTime;
    final long DOUBLE_CLICK_TIME_DELTA=300;
    ProgressBar progressBar;
    //to store the animal images
    private final ArrayList<WaterQuantity> quantityHistoryList;

    public HistoryListView(Activity context, ArrayList<WaterQuantity> quantityHistoryListParams){
        super(context, R.layout.data_view_row , quantityHistoryListParams);
        this.context = context;
        this.quantityHistoryList = quantityHistoryListParams;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.data_view_row, null,true);
        progressBar = (ProgressBar) context.findViewById(R.id.progressBar);


        rowView.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           int value = 0;
                                           long clicktime = System.currentTimeMillis();


                                           if(clicktime - LastClickTime < DOUBLE_CLICK_TIME_DELTA)
                                           {

                                               if (quantityHistoryList.get(position).toString().equalsIgnoreCase("50 mL"))
                                               {
                                                   value = 50;
                                               }else if (quantityHistoryList.get(position).toString().equalsIgnoreCase("200 mL"))
                                               {
                                                   value = 200;
                                               }
                                               else if (quantityHistoryList.get(position).toString().equalsIgnoreCase("350 mL"))
                                               {
                                                   value = 350;
                                               }


                                               progressBar.setProgress(progressBar.getProgress() - value);
                                               quantityHistoryList.remove(position);
                                               MainActivity.historyListViewAdapter.notifyDataSetChanged();
                                               //MainActivity.getprogressBar
                                               /*value = quantityHistoryList.get(i).getvalue();
                                               quantityHistoryList.remove(i);

                                               historyListViewAdapter.notifyDataSetChanged();*/
                                           }
                                           LastClickTime = clicktime;
                                       }
                                   });


                TextView historyDataText = rowView.findViewById(R.id.historyDataText);
        TextView historyDataTimestamp = rowView.findViewById(R.id.historyDataTimestamp);
        ImageView historyDataImage = rowView.findViewById(R.id.historyDataImage);


        historyDataText.setText(quantityHistoryList.get(position).toString());
        historyDataTimestamp.setText(quantityHistoryList.get(position).getTime());
        historyDataImage.setImageResource(quantityHistoryList.get(position).getImageId());

        return rowView;

    }
}
