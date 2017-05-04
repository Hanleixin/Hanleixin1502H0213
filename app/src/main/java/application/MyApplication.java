package application;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Author ${韩磊鑫} on 2017/2/13 09:50
 * 邮箱：leixinhan@foxmail.com
 * 项目名称：
 * 类描述：
 * 修改人：${Oliver}
 * 修改备注：
 * 修改时间：
 */

public class MyApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            File file = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
            ImageLoader imageLoader = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
            ImageLoaderConfiguration builder = new ImageLoaderConfiguration.Builder(this).diskCache(new UnlimitedDiscCache(file)).defaultDisplayImageOptions(options).build();
            imageLoader.init(builder);
        }
}