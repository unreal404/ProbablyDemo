package com.animationdemo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.animationdemo.R;

import com.animationdemo.base.BaseActivity;
import com.animationdemo.databinding.ActivityDisplayfileBinding;
import com.animationdemo.listener.DownloadUtils;
import com.animationdemo.listener.JsDownloadListener;
import com.animationdemo.util.ConstantUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.ValueCallback;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by HaiyuKing
 * Used 调用腾讯浏览服务预览文件
 */

public class DisplayFileActivity extends BaseActivity implements JsDownloadListener {

	private ActivityDisplayfileBinding binding;
	private TbsReaderView mTbsReaderView;//用于预览文件5-1

	private String filePath = "";
	private String fileName = "";
	private String url = "http://xtzx01-1255000116.cos-zwy.xuetangx.com/zwy/img/tmp/省委办省府办经济责任审计办法97d2dd116ee222055d31c589db6919ce.docx";
	private String title = "";
	private int type = 1;
	private RxPermissions mRxPermissions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_displayfile);
		initStatusBar(getResources().getColor(R.color.color_F));
		setStatusBarMode(this, 1);
		mRxPermissions = new RxPermissions(this);

		initView();
		initData();
		initListener();
	}

	@Override
	public void initView() {
		initTbsReaderView();//用于预览文件5-2
	}

	@Override
	public void initData() {
		Intent intent = getIntent();
		if (intent != null) {
			url = intent.getStringExtra(ConstantUtil.RESOURCE_URL);
			title = intent.getStringExtra(ConstantUtil.RESOURCE_TITLE);
			type = intent.getIntExtra(ConstantUtil.RESOURCE_TYPE, 1);
		}

		binding.includeTitle.tvTitle.setText(title);
		addPermissions();
	}

	@Override
	public void initListener() {
		binding.includeTitle.llTitleLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mTbsReaderView.onStop();//用于预览文件5-5
	}

	//初始化TbsReaderView 5-3
	private void initTbsReaderView(){
		mTbsReaderView = new TbsReaderView(DisplayFileActivity.this, new TbsReaderView.ReaderCallback(){
			@Override
			public void onCallBackAction(Integer integer, Object o, Object o1) {
				//ReaderCallback 接口提供的方法可以不予处理（目前不知道有什么用途，但是一定要实现这个接口类）
			}
		});
		binding.rootLayout.addView(mTbsReaderView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
	}
	//预览文件5-4
	/**
	 * filePath :文件路径。格式为 android 本地存储路径格式，例如：/sdcard/Download/xxx.doc. 不支持 file:///格式。暂不支持在线文件。
	 * fileName : 文件的文件名（含后缀）*/
	private void displayFile(String filePath,String fileName) {
		Bundle bundle = new Bundle();
		bundle.putString("filePath", filePath);
		bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
		boolean result = mTbsReaderView.preOpen(parseFormat(fileName), false);
		if (result) {
			mTbsReaderView.openFile(bundle);
		}

//		openOtherFile(filePath);
	}

	private String parseFormat(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	private void downLoadFile(String url) {
		int i = url.lastIndexOf("/");
		String tmp_url = url.substring(0, i + 1);
		fileName = url.substring(i + 1, url.length());
		filePath = createWordFile(this, String.format("%s", fileName)).getPath();
		if (!DownloadUtils.fileIsExists(filePath)) {
			DownloadUtils downloadUtils = new DownloadUtils(tmp_url, this);
			downloadUtils.download(fileName, filePath, new Observer() {
				@Override
				public void onSubscribe(Disposable d) {

				}

				@Override
				public void onNext(Object o) {

				}

				@Override
				public void onError(Throwable e) {

				}

				@Override
				public void onComplete() {

				}
			});
		} else {
			displayFile(filePath, fileName);
		}

	}

	@Override
	public void onStartDownload() {
		showMsg(this, "开始下载");
	}

	@Override
	public void onProgress(final int progress) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (progress == 100) {
					displayFile(filePath, fileName);
				}
			}
		});
	}

	@Override
	public void onFinishDownload() {
	}

	@Override
	public void onFail(String errorInfo) {
		Toast.makeText(DisplayFileActivity.this, errorInfo, Toast.LENGTH_LONG).show();
	}

	public File createWordFile(Context context, String id) {

		File file = null;
		String state = Environment.getExternalStorageState();

		if (state.equals(Environment.MEDIA_MOUNTED)) {

			file = new File(String.format("%s/animation/word/%s", Environment.getExternalStorageDirectory().getAbsolutePath(), id));
		} else {
//            file = new File(String.format("%s/%s.txt", context.getCacheDir().getAbsolutePath(), id));
		}

//        L.d("vivi","file "+file.getAbsolutePath());

		return file;

	}

	private void addPermissions() {
		if (mRxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
			if (type == 1) {
				downLoadFile(url);
			} else {
				displayFile(url, title);
			}
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
					@Override
					public void accept(Boolean aBoolean) throws Exception {
						if (aBoolean) {
							if (type == 1) {
								downLoadFile(url);
							} else {
								displayFile(url, title);
							}
						} else {
							showMsg(DisplayFileActivity.this, "未授权权限，功能不能使用");
							finish();
						}
					}
				});
			}

		}
	}

	private void openOtherFile(String path) {
        /*QbSdk.canOpenFile(activity, path, new ValueCallback<Boolean>() {
            @Override
            public void onReceiveValue(Boolean aBoolean) {
                Log.e(TAG,"文件是否能够打开:"+aBoolean);
            }
        });*/
		HashMap<String,String> params = new HashMap<>();
		//“0”表示文件查看器使用默认的UI 样式。“1”表示文件查看器使用微信的UI 样式。不设置此key或设置错误值，则为默认UI 样式。
		params.put("style","0");
		//“true”表示是进入文件查看器，如果不设置或设置为“false”，则进入miniqb 浏览器模式。不是必须设置项
		params.put("local","false");
		//定制文件查看器的顶部栏背景色。格式为“#xxxxxx”，例“#2CFC47”;不设置此key 或设置错误值，则为默认UI 样式。
		params.put("topBarBgColor","#ff8b3d");
		QbSdk.openFileReader(this, path, params, new ValueCallback<String>() {
			@Override
			public void onReceiveValue(String s) {

			}
		});
	}


}
