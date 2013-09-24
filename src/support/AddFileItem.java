package support;

import java.io.File;
import java.io.IOException;

import com.example.vitocrypt.R;

import activities.AddFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddFileItem extends LinearLayout{
	
	File file;
	ImageView fileThumb;
	TextView fileName;
	Context context;
	AddFragment caller;

	public AddFileItem(Context context, AddFragment caller) {
		super(context);
		this.caller = caller;
		this.context = context;
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setBackgroundResource(R.drawable.abs__ab_transparent_dark_holo);
    	fileThumb = new ImageView(context);
    	fileName = new TextView(context);
    	fileThumb.setMaxWidth(50);
    	fileThumb.setMaxHeight(50);
    	LinearLayout.LayoutParams imageMargins = new LinearLayout.LayoutParams(50, 50);
    	imageMargins.setMargins(8,8,8,8);
    	fileThumb.setLayoutParams(imageMargins);
    	fileName.setTextSize(20);
    	fileName.setTextColor(Color.parseColor("#ffffff"));
    	LinearLayout.LayoutParams textMargins = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	textMargins.setMargins(5,22,0,0);
    	fileName.setLayoutParams(textMargins);
    	this.addView(fileThumb);
    	this.addView(fileName);
	}

	public AddFileItem(Context context, File file, AddFragment caller){
		super(context);
		this.caller = caller;
		this.context = context;
		this.file = file;
		String[] parts = file.getName().split("\\.");
		System.out.println(file.getName() + " - " + parts.length);
		String extension = parts.length > 0 ? parts[parts.length - 1] : "";
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setBackgroundResource(R.drawable.abs__ab_transparent_dark_holo);
    	fileThumb = new ImageView(context);
    	fileName = new TextView(context);
    	fileThumb.setMaxWidth(50);
    	fileThumb.setMaxHeight(50);
    	LinearLayout.LayoutParams imageMargins = new LinearLayout.LayoutParams(50, 50);
    	imageMargins.setMargins(8,8,8,8);
    	fileThumb.setLayoutParams(imageMargins);
    	fileName.setTextSize(20);
    	fileName.setTextColor(Color.parseColor("#ffffff"));
    	LinearLayout.LayoutParams textMargins = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	textMargins.setMargins(5,22,0,0);
    	fileName.setLayoutParams(textMargins);
    	if(extension.equalsIgnoreCase("gif") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("bpm")){
    		fileThumb.setImageResource(R.drawable.image);
    	}else if(extension.equalsIgnoreCase("mp3") || extension.equalsIgnoreCase("wav")){
    		fileThumb.setImageResource(R.drawable.audio);
    	}else if(extension.equalsIgnoreCase("avi") || extension.equalsIgnoreCase("mp4")){
    		fileThumb.setImageResource(R.drawable.video);
    	}
    	fileName.setText(file.getName());
    	this.addView(fileThumb);
    	this.addView(fileName);
    	this.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				try {
					protectFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    	});
	}
	
	public void setFile(File file){
		this.file = file;
    	fileThumb.setImageURI(Uri.parse(file.getAbsolutePath()));
    	fileName.setText(file.getName());
	}
	
	public File getFile(){
		return this.file;
	}
	
	public void protectFile() throws IOException{
    	caller.protectFile(file,this);
	}
}