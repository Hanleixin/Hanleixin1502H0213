package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import adapter.MyAdapter;
import com.bwei.hanleixin1502h0213.R;
import com.bwei.hanleixin1502h0213.WebViewActiity;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import bean.Bean;

/**
 * Author ${韩磊鑫} on 2017/2/13 09:41
 * 邮箱：leixinhan@foxmail.com
 * 项目名称：
 * 类描述：
 * 修改人：${Oliver}
 * 修改备注：
 * 修改时间：
 */

public class MyFragment extends Fragment {
    private static final String TAG=MyFragment.class.getSimpleName();

    private List<Bean.ResultBean.DataBean> data;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bean bean= (Bean) msg.obj;
            data = bean.getResult().getData();
            listView.setAdapter(new MyAdapter(getActivity(), data));
        }
    };

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.item_myfrsgment,null);
        listView = (ListView) view.findViewById(R.id.listview);
        //点击条目跳转到webview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getActivity(),WebViewActiity.class);

                //跳转传值
                intent.putExtra("url",data.get(position).getUrl());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int num = getArguments().getInt("num");//接受值
        switch (num){
            case 0:
                getData("top");
                break;
            case 1:
                getData("shehui");
                break;
            case 2:
                getData("guoji");
                break;
            case 3:
                getData("guonei");
                break;
            case 4:
                getData("yule");
                break;
            case 5:
                getData("tiyu");
                break;
            case 6:
                getData("junshi");
                break;
            case 7:
                getData("keji");
                break;
            case 8:
                getData("caijing");
                break;
            case 9:
                getData("shishang");
                break;
        }
    }
    private void getData(final String name){
        //线程进行联网耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //地址
                String path="http://v.juhe.cn/toutiao/index?type="+name+"&key=1d0359e69946f7edbbc3b74a3c6d7e57";

                try {
                    URL url=new URL(path);
                    HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();

                    openConnection.setRequestMethod("GET");//网络请求方式

                    openConnection.setReadTimeout(5000);//读取超时

                    openConnection.setConnectTimeout(5000);//连接超时

                    openConnection.connect();//连接

                    if(200==openConnection.getResponseCode()){//判断请求码是否为200

                        InputStream inputStream = openConnection.getInputStream();

                        Gson gson=new Gson();//解析

                        Bean bean = gson.fromJson(new InputStreamReader(inputStream), Bean.class);
                        Message message=new Message();//发送Message对象
                        message.obj=bean;

                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
