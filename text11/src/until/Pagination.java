package until;

public class Pagination {
	private int ye;
	private int maxYe;
	private int endYe;
	private int beginYe;
	private int begin;

	//numInPage一页显示多少数据   numOfPage 一页显示多少分页按钮数  conut 数据库中的数据个数   ye当前页数
	public  Pagination(int ye,int count,int numInPage,int numOfPage) {
	  this.ye=ye;
	   maxYe=count%numInPage==0?count/numInPage:count/numInPage+1;
	   if(maxYe==0) {
		   maxYe=1;
	   }
	  if(this.ye<=1) {
		  this.ye=1;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	  }
	  if(this.ye>=maxYe) {
		this.ye=maxYe; 
	  }
	  beginYe=ye-numOfPage/2;
	  if(beginYe<=1) {
		  beginYe=1;
		 
	  }
	  endYe=beginYe+numOfPage-1;
	  
	  
	  
	  if(endYe>=maxYe) {
		 endYe=maxYe; 
		 beginYe=endYe-numOfPage+1;
	  } if(beginYe<=1) {
		  beginYe=1;
		 
	  }
		
	   begin = (this.ye - 1) * numInPage;
	}
	
	
	public void setBegin(int begin) {
		this.begin = begin;
	}


	public int getBegin() {
		return begin;
	}
	public int getYe() {
		return ye;
	}
	public int getMaxYe() {
		return maxYe;
	}
	public int getEndYe() {
		return endYe;
	}
	public int getBeginYe() {
		return beginYe;
	}


}
