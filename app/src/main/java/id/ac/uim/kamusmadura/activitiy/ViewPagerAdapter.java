package id.ac.uim.kamusmadura.activitiy;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import id.ac.uim.kamusmadura.R;

public class ViewPagerAdapter extends PagerAdapter {

    //generate > constructor
    Context context;
    int[] gambarbilal;

    private ImageView ivAdab;
    String kondisi;
    private ImageView ivText;

    public ViewPagerAdapter(Context context, int[] gambarbilal) {
        this.context = context;
        this.gambarbilal = gambarbilal;
    }

    //jumlah gambarnya
    @Override
    public int getCount() {
        return gambarbilal.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //generate > override method
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.item_belajar, null, true);

        ivAdab = rowview.findViewById(R.id.ivAdab);

        Glide.with(context).load(gambarbilal[position]).diskCacheStrategy(DiskCacheStrategy.RESULT).priority(Priority.NORMAL).into(ivAdab);

        //tambahkan view
        container.addView(rowview);
        return rowview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
