package board;

import java.util.HashMap;

class Article {
	String title;
	String body;
	String witer;
	int No;
	String date;
	int hit;
	HashMap<String, Integer> LikesAndHates;

	Article() {

	}

	Article(int No, String title, String body, String witer, String date, int hit,HashMap<String, Integer> LikesAndHates) {
		this.No = No;
		this.title = title;
		this.body = body;
		this.witer = witer;
		this.date = date;
		this.hit = hit;
		this.LikesAndHates=LikesAndHates;

	}
	
	void set_likes_and_hates(String userID, int LikeOrHate) {
		if(LikesAndHates == null) {
			LikesAndHates = new HashMap<String, Integer>();
		} 
		if(LikesAndHates.containsKey(userID)) {
			if(LikesAndHates.get(userID)==LikeOrHate) {
				LikesAndHates.remove(userID);
			}else {
				LikesAndHates.put(userID,LikeOrHate);
			}
		}else {
			LikesAndHates.put(userID, LikeOrHate);
		}
	}
	
	HashMap<String, Integer> get_likes_and_hates(){
		int likeCnt= 0;
		int hateCnt= 0;
		
		for(int value : LikesAndHates.values()) {
			if(value ==1) {
				likeCnt++;
			}
		}
		hateCnt = LikesAndHates.size()-likeCnt;
		
		HashMap<String,Integer> resultMap = new HashMap<>();
		resultMap.put("like", likeCnt);
		resultMap.put("hate", hateCnt);
		
		return resultMap;
		}
	
	String getPropertyByType (int type) {
		String rst = "";
		if(type==1) {
			rst=this.title;
		}else if(type==2) {
			rst=this.body;
		}
		return rst;
	}

	
	

}
