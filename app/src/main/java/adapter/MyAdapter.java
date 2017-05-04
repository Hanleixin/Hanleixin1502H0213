package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.hanleixin1502h0213.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.Bean;

/**
 * Author ${韩磊鑫} on 2017/2/13 09:55
 * 邮箱：leixinhan@foxmail.com
 * 项目名称：
 * 类描述：
 * 修改人：${Oliver}
 * 修改备注：
 * 修改时间：
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Bean.ResultBean.DataBean> list=new ArrayList<>();

    public MyAdapter(Context context, List<Bean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldr v=null;//ViewHoldr优化
        if(convertView==null){//判空
            convertView=View.inflate(context, R.layout.item,null);
            v=new ViewHoldr();

            //获取id
            v.imageView= (ImageView) convertView.findViewById(R.id.item_imageView);
            v.textView1= (TextView) convertView.findViewById(R.id.item_title);
            v.textView2= (TextView) convertView.findViewById(R.id.item_from);

            convertView.setTag(v);
        }else{
            v= (ViewHoldr) convertView.getTag();
        }

        //赋值
        Bean.ResultBean.DataBean dataBean = list.get(position);

        v.textView1.setText(dataBean.getTitle());
        v.textView2.setText(dataBean.getAuthor_name());

        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(dataBean.getThumbnail_pic_s(),v.imageView);

        return convertView;
    }
    class ViewHoldr{
        ImageView imageView;
        TextView textView1,textView2;
    }
}
