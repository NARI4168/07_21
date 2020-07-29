package board;

public class Reply {
	
	int No;
	int parent_No;
	String body;
	String witer;
	String date;
	
	public Reply(int No, int parent_No, String body, String witer, String date) {
	super();
	this.No=No;
	this.parent_No=parent_No;
	this.body=body;
	this.witer=witer;
	this.date=date;
	}

}
