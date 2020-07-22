package board;

import java.util.ArrayList;
import java.util.Scanner;
import test.Test;

public class board1 {

	static ArrayList<Article> articles = new ArrayList<Article>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String cmd = " ";

		int targetNo;
		String all;
		int No = 4;
		
		Article article1 = new Article();
		article1.No =1;
		article1.title = "제목1";
		article1.body = "내용1";
		article1.witer = "작성자1";
		article1.date = Test.getCurrentDate();
		article1.hit =20;
		
		Article article2 = new Article(2, "제목2", "내용2","작성자2",Test.getCurrentDate(),5);
		Article article3 = new Article(3, "제목3", "내용3","작성자3",Test.getCurrentDate(),30);
		
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
		
		
		while (true) {
			System.out.println("====명령어를 입력하세요 : ");
			cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				System.out.println("==시스템을 종료합니다.==");
				break;
			}

			if (cmd.equals("help")) {
				System.out.println("add : 저장");
				System.out.println("list : 목록 조회");
				System.out.println("detail : 상세 조회");
				System.out.println("update : 수정");
				System.out.println("delete : 삭제");
				System.out.println("search : 검색");
			}

			if (cmd.equals("add")) {
				Article article = new Article();
				article.No = No;

				System.out.println("제목을 입력하세요 : ");
				article.title = sc.nextLine();
				System.out.println("내용을 입력하세요 : ");
				article.body = sc.nextLine();
				System.out.println("작성자를 입력하세요 : ");
				article.witer = sc.nextLine();

				article.date = Test.getCurrentDate();

				articles.add(article);
				System.out.println(No + "번 게시물이 등록되었습니다.");
				No++;
			}

			if (cmd.equals("list")) {
				System.out.println("어떤 게시물을 조회하겠 ? :");

				targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_No(targetNo);
				
				
				if (targetArticle == null) {
					System.out.println("존재하지 않는 게시물입니다. 전체를 불러옵니다.");
//					for (int i = 0; i < articles.size(); i++) {
//						System.out.println("번호 : " + articles.get(i).No);
//						System.out.println("제목 : " + articles.get(i).title);
//						System.out.println("내용 : " + articles.get(i).body);
//						System.out.println("작성자 : " + articles.get(i).witer);
//
//						String str = articles.get(i).date;
//						String[] arr = str.split(" ");
//						System.out.println("작성일 : " + arr[0]);
//						
//						System.out.println("조회수 : " + articles.get(i).hit);
						
					 print_articles(articles);
						
					}
				//} else {

					System.out.println("번호 : " + targetArticle.No);
					System.out.println("제목 : " + targetArticle.title);
					System.out.println("내용 : " + targetArticle.body);
					System.out.println("작성자 : " + targetArticle.witer);
					String str = targetArticle.date;
					String[] arr = str.split(" ");
					System.out.println("작성일 : " + arr[0]);
					
					System.out.println("조회수 : " + targetArticle.hit);
				//}
			}

			if (cmd.equals("update")) {
				System.out.println("몇번 게시물을 수정할까요 ? : ");
				targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_No(targetNo);

				if (targetArticle != null) {
					System.out.println("게시물의 무엇을 수정할까요? (전부_all) : ");
					cmd = sc.nextLine();
					if (cmd.equals("title")) {
						System.out.println("새로운 제목을 입력하세요 : ");
						String update_title = sc.nextLine();
						targetArticle.title = update_title;

					}
					if (cmd.equals("body")) {
						System.out.println("새로운 내용을 입력하세요 : ");
						String update_body = sc.nextLine();
						targetArticle.body = update_body;

					}
					if (cmd.equals("witer")) {
						System.out.println("새로운 작성자를 입력하세요 : ");
						String update_body = sc.nextLine();
						targetArticle.body = update_body;

					}
					if (cmd.equals("all")) {
						System.out.println("수정할 제목을 입력하세요 : ");
						String update_title = sc.nextLine();
						targetArticle.title = update_title;
						System.out.println("수정할 내용을 입력하세요 : ");
						String update_body = sc.nextLine();
						targetArticle.body = update_body;
						System.out.println("수정할 작성자를 입력하세요 : ");
						String update_witer = sc.nextLine();
						targetArticle.witer = update_witer;
					}

					System.out.println("==수정이 완료되었습니다==");
				} else {
					System.out.println("==존재하지 않는 게시물번호입니다==");
				}

			}

			if (cmd.equals("delete")) {
				System.out.println("어떤 게시물을 삭제하겠습니까? : ");
				targetNo = Integer.parseInt(sc.nextLine());
				Article targetArticle = get_article_by_No(targetNo);

				if (targetArticle != null) {
					articles.remove(targetArticle);
					System.out.println("게시물이 삭제되었씀");
				} else {
					System.out.println("==존재하지 않는 게시물번호입니다==");
				}
			}
			
			if (cmd.equals("search")) {
				System.out.println("어떤 게시물을 검색하겠습니까? : 1.제목, 2.내용");
				int searchFlag = Integer.parseInt(sc.nextLine());
				System.out.println("검색어를 입력해주세요. ");
				String keyword = sc.nextLine();
				ArrayList <Article> searchedArticles = new ArrayList<>();
				
				if(searchFlag == 1) {
					for(int i=0; i<articles.size(); i++) {
						if(articles.get(i).title.contains(keyword)) {
							searchedArticles.add(articles.get(i));
						}
					}
				}else if (searchFlag == 2) {
					for(int i=0; i<articles.size(); i++) {
						if(articles.get(i).body.contains(keyword)) {
							searchedArticles.add(articles.get(i));
						}
				}
			

		} 
				print_articles(searchedArticles);
		
			}
			
		 if(cmd.equals("detail")) {
			 System.out.println("게시물 번호를 입력해주세요.");
			 int articleNo = Integer.parseInt(sc.nextLine());
			 Article article = get_article_by_No(articleNo);
			 
			 if(article == null) {
				 System.out.println("없는 게시물입니다.");
			 }else {
				 article.hit++;
				 print_article(article);
			 }
			 
			 
		 }
			
		}
		
		

	}

	public static void print_article(Article article) {
		System.out.println("===게시물 상세====");
		System.out.println("번호 : "+ article.No);
		System.out.println("제목 : "+ article.title);
		System.out.println("내용 : "+ article.body);
		System.out.println("조회수 : "+ article.hit);
		
	}
	
	public static void print_articles(ArrayList<Article> articles) {
		System.out.println("===게시물 목록====");
	for(int i=0; i<articles.size(); i++) {
		System.out.println("번호 : "+ articles.get(i).No);
		System.out.println("제목 : "+ articles.get(i).title);
		
		String str = articles.get(i).date;
		String[] arr = str.split(" ");
		System.out.println("작성일 : "+ arr[0]);
		
		System.out.println("조회수 : "+ articles.get(i).hit);
		
	 }
	}
		
		
		
	public static Article get_article_by_No(int No) {
		Article article = null;
		for (int i = 0; i < articles.size(); i++) {

			Article target = articles.get(i);

			if (target.No == No) {
				article = target;
				break;
			}
		}
		return article;

	}

}

class Article {
	String title;
	String body;
	String witer;
	int No;
	String date;
	int hit;

	Article() {

	}

	Article(int No, String title, String body, String witer, String date, int hit) {
		this.No = No;
		this.title = title;
		this.body = body;
		this.witer = witer;
		this.date = date;
		this.hit = hit;

	}

}
