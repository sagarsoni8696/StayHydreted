package om.example.stayhydrated;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WaterQuantity{


    private enum QtyEnum {QTY_50, QTY_200, QTY_350};
    private final String timestamp;
    private QtyEnum qty;

    private WaterQuantity(QtyEnum qty){
        this.timestamp = getTime();
        this.qty = qty;
    }

    static WaterQuantity getQty50(){
        return new WaterQuantity(QtyEnum.QTY_50);
    }

    static WaterQuantity getQty200(){
        return new WaterQuantity(QtyEnum.QTY_200);
    }

    static WaterQuantity getQty350(){
        return new WaterQuantity(QtyEnum.QTY_350);
    }

    int getImageId(){
        switch (this.qty) {
            case QTY_50:
                return R.drawable.icons8_water_droplet;
            case QTY_200:
                return R.drawable.icons8_water_glass;
            case QTY_350:
                return R.drawable.icons8_water_bottle;
            default:
                throw new IllegalArgumentException();
        }
    }

    public String toString(){
        switch (this.qty) {
            case QTY_50:
                return "50 mL";
            case QTY_200:
                return "200 mL";
            case QTY_350:
                return "350 mL";
            default:
                throw new IllegalArgumentException();
        }
    }

    String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm - dd/MM");
        String date = df.format(new Date());
        return date;
    }
}
