package until;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;



import entity.ValidateCode;


public class RandomNumber {
	//先随机响应4位数，然后把这4个数生成图片传给客户端
public ValidateCode generateImage() throws IOException{
String sRand="";
Random random=new Random();
for(int i=0;i<4;i++) {
	
	
	String rand =String.valueOf(random.nextInt(10));
     sRand+=rand;
    
}
//创建图像
int width=80,height=30;
BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);	
//获取图形上下文
Graphics g=image.getGraphics();
//设定背景色
g.setColor(getRandColor(100,250));
//画一个矩形
g.fillRect(0,0,width,height);
//设置字体
g.setFont(new Font("Timess New Roman",Font.PLAIN,28));
//随机产生155条干扰线，使图像中数字不易被其程序探测到
g.setColor(getRandColor(160,200));
for(int i=0;i<155;i++) {
	int x=random.nextInt(width);
	int y=random.nextInt(height);
	int x1=random.nextInt(12);
	int y1=random.nextInt(12);
	g.drawLine(x, y, x+x1, y+y1);
}
//设置颜色
g.setColor(new Color(20+random.nextInt(110)));
//验证码显示到图像中去
g.drawString(sRand,10,26);
//绘图工具释放
g.dispose();
ValidateCode vc=new ValidateCode();
vc.setImage(image);
vc.setRand(sRand);
return vc;
}
Color getRandColor(int fc,int bc) {
	Random random=new Random();
	if(fc>255)
		fc=255;
	if(bc>255)
		bc=255;
	int r=fc+random.nextInt(bc-fc);
	int g=fc+random.nextInt(bc-fc);
	int b=fc+random.nextInt(bc-fc);
	return new Color(r,g,b);
	
	
}
}
