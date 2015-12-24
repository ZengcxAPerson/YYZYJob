package com.ane.framework.Base.util;

import android.graphics.drawable.Animatable;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;


/**
 *需要一些特别设置的类
 */
public class FrescoSetDraweUtil {

	/**
	 * 加载失败可以点击重新加载
	 * 需要在xml中设置相关的图片
	 * @param sDraweeView 
	 * @param imgPath 网络路径
	 */
	public static void setImg_RetryImageForNetWork(SimpleDraweeView sDraweeView,
			String imgPath) {
		Uri uri = Uri.parse(imgPath);
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setUri(uri).setTapToRetryEnabled(true)
				.setOldController(sDraweeView.getController()).build();
		sDraweeView.setController(controller);
	}
	/**
	 * 加载失败可以点击重新加载
	 * 需要在xml中设置相关的图片
	 * @param sDraweeView 
	 * @param resId 
	 */
	public static void setImg_RetryImageForRes(SimpleDraweeView sDraweeView,
			int resId) {
		Uri uri = Uri.parse("res:///" + resId);
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setUri(uri).setTapToRetryEnabled(true)
				.setOldController(sDraweeView.getController()).build();
		sDraweeView.setController(controller);
	}
	/**
	 * 加载失败可以点击重新加载
	 * 需要在xml中设置相关的图片
	 * @param sDraweeView 
	 * @param filePath 本地图片路径
	 */
	public static void setImg_RetryImageForLocalFile(SimpleDraweeView sDraweeView,
			String filePath) {
		File file = new File(filePath);
		Uri uri = Uri.fromFile(file);
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setUri(uri).setTapToRetryEnabled(true)
				.setOldController(sDraweeView.getController()).build();
		sDraweeView.setController(controller);
	}
	/**
	 * 加载失败可以点击重新加载GIF
	 * @param sDraweeView
	 * @param imgPath 网络路径
	 * @param playing 是否自动播放
	 */
	public static void setGIF_RetryImageForNetWork(SimpleDraweeView sDraweeView,
			String imgPath,boolean playing){
		Uri uri = Uri.parse(imgPath); 
		sDraweeView.setController(setGIF(sDraweeView, uri, playing));
	}
	/**
	 * 加载失败可以点击重新加载GIF
	 * @param sDraweeView
	 * @param resId 
	 * @param playing 是否自动播放
	 */
	public static void setGIF_RetryImageForRes(SimpleDraweeView sDraweeView,
			int resId,boolean playing){
		Uri uri = Uri.parse("res:///" + resId); 
		sDraweeView.setController(setGIF(sDraweeView, uri, playing));
	}
	/**
	 * 修改GIF播放状态
	 * @param sDraweeView
	 */
	public static void updataAnimatableRun(SimpleDraweeView sDraweeView){
		Animatable animation = sDraweeView.getController().getAnimatable();
		if (animation != null) {
			if(animation.isRunning()){ 
				  animation.stop();
			}else{ 
				  animation.start();
			}
		 } 
	}
	/**
	 * GIF核心设置代码
	 * @param uri
	 * @return
	 */
	private static DraweeController setGIF(SimpleDraweeView sDraweeView,Uri uri,boolean playing) {
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
				.build();
		DraweeController controller = Fresco.newDraweeControllerBuilder() 
				.setAutoPlayAnimations(playing)
				.setImageRequest(request)
				.setOldController(sDraweeView.getController())
				.setTapToRetryEnabled(true)
				.build();
		return controller;
	}
	
	/**
	 * 设置同时请求的两种图片，首先显示小的低分辨率的图片，在显示大的高分辨率的图片
	 * @param sDraweeView
	 * @param lowResImgPath 小的低分辨率的图片路径
	 * @param highResImgPath 大的高分辨率的图片路径
	 */
	public static void setImg_ForLowRes_HighRes(SimpleDraweeView sDraweeView,String lowResImgPath,String highResImgPath){
		Uri lowResUri = Uri.parse(lowResImgPath);  
		Uri highResUri = Uri.parse(highResImgPath); 
		DraweeController controller = Fresco.newDraweeControllerBuilder()
		    .setLowResImageRequest(ImageRequest.fromUri(lowResUri))
		    .setImageRequest(ImageRequest.fromUri(highResUri))
		    .setOldController(sDraweeView.getController())
		    .build();
		sDraweeView.setController(controller);
	}
}
