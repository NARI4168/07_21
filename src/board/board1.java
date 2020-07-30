package board;

import java.util.ArrayList;
import java.util.Scanner;
import test.Test;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

public class board1 {

	ArrayList<Article> articles = new ArrayList<Article>();
	ArrayList<Reply> replies = new ArrayList<Reply>();
	int lastArticleNo = 0; // 게시물 번호 관리용
	int lastReplyNo = 0; // 댓글 번호 관리용

	void start() {

		Scanner sc = new Scanner(System.in);
		String cmd = " ";

		int targetNo;
		String all;
		int No = 4;

		make_test_data();
		print_articles(articles);

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
				System.out.println("sort : 게시물 정렬 조회");
			}

			if (cmd.equals("add")) {
				Article article = new Article();
				article.No = lastArticleNo;
				lastArticleNo++;

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

				} else {

					System.out.println("번호 : " + targetArticle.No);
					System.out.println("제목 : " + targetArticle.title);
					System.out.println("내용 : " + targetArticle.body);
					System.out.println("작성자 : " + targetArticle.witer);
					String str = targetArticle.date;
					String[] arr = str.split(" ");
					System.out.println("작성일 : " + arr[0]);

					System.out.println("조회수 : " + targetArticle.hit);
					// }
				}
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
				ArrayList<Article> searchedArticles = new ArrayList<>();

//				if (searchFlag == 1) {
//					for (int i = 0; i < articles.size(); i++) {
//						if (articles.get(i).title.contains(keyword)) {
//							searchedArticles.add(articles.get(i));
//						}
//					}
//				} else if (searchFlag == 2) {
//					for (int i = 0; i < articles.size(); i++) {
//						if (articles.get(i).body.contains(keyword)) {
//							searchedArticles.add(articles.get(i));
//						}
//					}
//
//				}
				for (int i = 0; i < articles.size(); i++) {
					if (articles.get(i).getPropertyByType(searchFlag).contains(keyword)) {
						searchedArticles.add(articles.get(i));
					}
				}
				print_articles(searchedArticles);

			}

			if (cmd.equals("detail")) {
				System.out.println("게시물 번호를 입력해주세요.");
				int articleNo = Integer.parseInt(sc.nextLine());
				Article article = get_article_by_No(articleNo);

				if (article == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					article.hit++;
					print_article(article);
					ArrayList<Reply> replies = get_replies_by_parent_No(articleNo);
					print_replies(replies);

					while (true) {
						System.out.println("무엇을 수행하시겠습니까? 1.댓글  2.좋아요  3.수정  4.삭제  5.뒤로가기");
						int detailCmd = Integer.parseInt(sc.nextLine());

						if (detailCmd == 1) {
							int replyNo = lastReplyNo;
							lastReplyNo++;

							System.out.println("댓글 내용을 입력해주세요.");
							String replyBody = sc.nextLine();
							String witer = "익명";
							String date = Test.getCurrentDate();

							Reply new_reply = new Reply(replyNo, articleNo, replyBody, witer, date);
							this.replies.add(new_reply);
							System.out.println("댓글이 성공적으로 등록되었습니다.");

							print_article(article);
							ArrayList<Reply> replies2 = get_replies_by_parent_No(articleNo);
							print_replies(replies2);

						} else if (detailCmd == 2) {
							System.out.println("1. 좋아요   2.싫어요");
							int LikeOrHate = Integer.parseInt(sc.nextLine());
							article.set_likes_and_hates("cha1", LikeOrHate);
							print_article(article);
						}

						else if (detailCmd == 5) {
							break;
						}
					}
				}

			} else if (cmd.equals("sort")) {
				System.out.println("정렬 대상을 선택해 주세요: 1.좋아요  2.작성일");
				int fieldFlag = Integer.parseInt(sc.nextLine());
				System.out.println("정렬 방법을 선택해 주세요 : 1.오름차순  2.내림차순");
				int sortFlag = Integer.parseInt(sc.nextLine());
				sortArticles(articles, fieldFlag, sortFlag);
				print_articles(articles);
			}

		}

	}

	public void sortArticles(ArrayList<Article> articles, int type, int sort) {
		Collections.sort(articles, new Comparator<Article>() {

			@Override
			public int compare(Article a1, Article a2) {
				int rst = -1;
				if (type == 1) {
					int c1 = a1.get_likes_and_hates().get("like");
					int c2 = a2.get_likes_and_hates().get("like");

					rst = c1 > c2 ? 1 : -1;
				} else if (type == 2) {
					String c1 = a1.date;
					String c2 = a2.date;
					rst = c1.compareTo(c2) > 0 ? 1 : -1;

				}
				if (sort == 2) {
					rst *= -1;

				}
				return rst;
			}
		});
	}

	public ArrayList<Reply> get_replies_by_parent_No(int parent_No) {
		ArrayList<Reply> result = new ArrayList<Reply>();
		for (int i = 0; i < replies.size(); i++) {
			if (this.replies.get(i).parent_No == parent_No) {
				result.add(this.replies.get(i));
			}
		}
		return result;
	}

	public void print_replies(ArrayList<Reply> replies) {
		System.out.println("=======댓글======");
		for (int i = 0; i < replies.size(); i++) {
			System.out.println(">>내용 : " + replies.get(i).body);
			System.out.println("작성자 : " + replies.get(i).witer);
			System.out.println("등록일 : " + replies.get(i).date);

		}

	}

	public void print_article(Article article) {
		System.out.println("===게시물 상세====");
		System.out.println("번호 : " + article.No);
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
		System.out.println("조회수 : " + article.hit);

		HashMap<String, Integer> resultMap = article.get_likes_and_hates();
		System.out.println("좋아요 :" + resultMap.get("like"));
		System.out.println("싫어요 :" + resultMap.get("hate"));

	}

	public void print_articles(ArrayList<Article> articles) {
		System.out.println("===게시물 목록====");
		for (int i = 0; i < articles.size(); i++) {
			System.out.println(">>>번호 : " + articles.get(i).No);
			System.out.println("제목 : " + articles.get(i).title);

			String str = articles.get(i).date;
			String[] arr = str.split(" ");
			System.out.println("작성일 : " + arr[0]);

			System.out.println("조회수 : " + articles.get(i).hit);

		}
	}

	public Article get_article_by_No(int No) {
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

	public void make_test_data() {

		HashMap<String, Integer> likes1 = new HashMap<>();
		likes1.put("cha1", 1);
		likes1.put("cha2", 2);
		likes1.put("cha3", 1);

		HashMap<String, Integer> likes2 = new HashMap<>();
		likes2.put("cha1", 2);
		likes2.put("cha2", 2);
		likes2.put("cha3", 2);

		HashMap<String, Integer> likes3 = new HashMap<>();
		likes3.put("cha1", 1);
		likes3.put("cha2", 1);
		likes3.put("cha3", 1);

		Article article1 = new Article();
		article1.No = 1;
		article1.title = "제목1";
		article1.body = "내용1";
		article1.witer = "작성자1";
		article1.date = "2020-07-21 09:04:00";
		article1.hit = 20;
		article1.LikesAndHates = likes1;

		Article article2 = new Article(2, "제목2", "내용2", "작성자2", "2020-07-28 11:11:00", 5, likes2);
		Article article3 = new Article(3, "제목3", "내용3", "작성자3", "2020-07-30 12:11:01", 30, likes3);

		articles.add(article1);
		articles.add(article2);
		articles.add(article3);

		lastArticleNo = 4;

		Reply r1 = new Reply(1, 1, "댓글1", "작성자1", Test.getCurrentDate());
		Reply r2 = new Reply(2, 1, "댓글2", "작성자2", Test.getCurrentDate());
		Reply r3 = new Reply(3, 2, "댓글3", "작성자3", Test.getCurrentDate());

		lastReplyNo = 4;

		replies.add(r1);
		replies.add(r2);
		replies.add(r3);

	}
}
