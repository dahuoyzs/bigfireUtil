package com.bigfire.imgdown.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.event.IIOReadWarningListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import com.bigfire.imgdown.util.HttpUtils;
import com.bigfire.imgdown.util.StrUtil;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Author ：dahuo.
 * @ Date   ：2019/6/22  22:14
 * @ Addr   ：China ShangHai
 * @ Email  ：835476090@qq.com
 * @ Desc   :
 */
public class CrawerController {
    @FXML
    TextArea listTA;
    @FXML
    TextField netUrlTF;
    @FXML
    TextField pathTF;
    @FXML
    Label process;

    List<String> imgs;
    public void getImg(){
        listTA.setText("");
        process.setText("");

        String url = netUrlTF.getText().trim();
        if (url==null||url.equals("")){
            process.setText("网址不能为空");
            return;
        }else {
            imgs = getImgs(url);
            if (imgs.size()<1){
                process.setText("没有可下载图片");
                return;
            }else {
                StringBuilder sb = new StringBuilder();
                imgs.forEach(imgUrl->{
                    sb.append(imgUrl+"\r\n");
                });
                listTA.setText(sb.toString());
            }
        }
    }
    public void selectPath() {
    	FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
    	JFileChooser fileChooser = new JFileChooser(fsv.getHomeDirectory());
    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	int returnVal = fileChooser.showOpenDialog(fileChooser);
    	if(returnVal == JFileChooser.APPROVE_OPTION){ 
    		String	filePath= fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的路径
    		pathTF.setText(filePath);
    	} 
    }
    public void downLoad(){
        String path = pathTF.getText().trim().trim();

        if (imgs==null||imgs.size()<1){
            process.setText("没有可下载图片");
            return;
        }else if(path.equals("")){
            process.setText("存储路径不能为空");
        }else {
            if (!path.endsWith("/"))path=path+"/";
            dowanLoadImg(path,imgs);
        }
    }
    private  void dowanLoadImg(String path,List<String> imgs){
        try{

            File file = new File(path);
            System.out.println("inputPath:"+path);
            if (!file.exists()){
                System.out.println("目录不存在");
                boolean b = file.mkdirs();
                if (!b){
                    process.setText("目录不存在");
                    return;
                }
            }
            imgs.forEach(imgUrl->{
                String  imgName= imgUrl.substring(imgUrl.lastIndexOf('/')+1);
                String fileName = path+imgName;
                HttpUtils.download(imgUrl, fileName);
                process.setText(imgName);
            });
            process.setText("下载完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    

    //获取网页中的图片连接
    public   List<String> getImgs(String url) {
        String html = HttpUtils.utf8Get(url);
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
            if (src.startsWith("//")) {
                src = "http:"+src;
            }
            if (!src.startsWith("http")) {
            	src = getFinalImg(url, src);
	        }
            imageSrcList.add(src);
        }
        return  imageSrcList;
    }
    //不是http开头的图片地址,一般不会走到这里。正则匹配,就值匹配是链接的图片,而相对链接的图片不会被匹配到
    public String getFinalImg(String url,String img) {
  		String finalStr = img;
  		int num = StrUtil.count(img, "../");
		String mainUrl = url;
		if (url.contains("?")) {
			mainUrl = url.substring(0,url.indexOf("?"));
		}
		if (num==0) {
			int index = mainUrl.lastIndexOf("/");
			if (index>10) {
				mainUrl = mainUrl.substring(0,+1);
			}
			if (!mainUrl.endsWith("/")) {
				mainUrl+="/";
			}
			finalStr = mainUrl+img;
		}else {
			StringBuffer remove = new StringBuffer();
			for(int i=0;i<num;i++) {
				mainUrl = mainUrl.substring(0,mainUrl.lastIndexOf("/"));
				remove.append("../");
			}
			img = img.replace(remove, "");
			finalStr = mainUrl+"/"+img;
		}
		return finalStr;
	}
}
