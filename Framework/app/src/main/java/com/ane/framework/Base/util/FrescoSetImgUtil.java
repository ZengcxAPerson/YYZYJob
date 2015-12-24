package com.ane.framework.Base.util;

import android.graphics.drawable.Animatable;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

/**
 * 设置图片
 * 
 */
public class FrescoSetImgUtil {

	/**
	 *设置图片，网络图片
	 * 
	 * @param sDraweeView
	 * @param imgPath 网络路径
	 */
	public static void setImg_ForNetwork(SimpleDraweeView sDraweeView,
			String imgPath) {
		Uri uri = Uri.parse(imgPath);
		sDraweeView.setImageURI(uri);
	}

	/**
	 *设置图片，res图片
	 * 
	 * @param sDraweeView
	 * @param resId 图片id
	 */
	public static void setImg_ForRes(SimpleDraweeView sDraweeView, int resId) {
		Uri uri = Uri.parse("res:///" + resId);
		sDraweeView.setImageURI(uri);
	}

	/**
	 *设置图片，本地图片
	 * 
	 * @param sDraweeView
	 * @param filePath 图片路径
	 */
	public static void setImg_ForLocalFile(SimpleDraweeView sDraweeView,
			String filePath) {
		File file = new File(filePath);
		Uri uri = Uri.fromFile(file);
		sDraweeView.setImageURI(uri);
	}
	/**
	 * 设置本地图片，自动旋转角度
	 * @param sDraweeView
	 * @param filePath 图片路径
	 */
	public static void setImg_ForLocalFile_AutoRotate(SimpleDraweeView sDraweeView,
			String filePath) { 
		File file = new File(filePath);
		Uri uri = Uri.fromFile(file);
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
			    .setAutoRotateEnabled(true) 
			    .build();
		PipelineDraweeController controller=(PipelineDraweeController) Fresco.newDraweeControllerBuilder()
				.setOldController(sDraweeView.getController())
				.setImageRequest(request)
				.build();
		sDraweeView.setController(controller);
	}
	/**
	 * 设置GIF图片，网络文件
	 * 
	 * @param sDraweeView
	 * @param imgPath
	 * @param Playing  是否自动播放
	 */
	public static void setGIF_ForNetWork(SimpleDraweeView sDraweeView,
			String imgPath,boolean Playing) {
		Uri uri = Uri.parse(imgPath);
		sDraweeView.setController(setGIF(sDraweeView,uri,Playing));
	}
	/**
	 * 设置GIF图片，res文件
	 * 
	 * @param sDraweeView
	 * @param resId
	 * @param Playing 是否自动播放
	 */
	public static void setGif_ForResId(SimpleDraweeView sDraweeView, int resId,boolean Playing) {
		Uri uri = Uri.parse("res:///" + resId);
		sDraweeView.setController(setGIF(sDraweeView,uri,Playing));
	}
	/**
	 * 设置GIF图片核心代码
	 * @param uri
	 * @return
	 */
	private static DraweeController setGIF(SimpleDraweeView sDraweeView,Uri uri,boolean playing) {
		ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
				.build();
		DraweeController controller = Fresco.newDraweeControllerBuilder()
				.setImageRequest(request)
				.setAutoPlayAnimations(playing) 
				.setOldController(sDraweeView.getController())  
				.build();
		return controller;
	}
	/**
	 *修改GIF播放状态̬
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
}
