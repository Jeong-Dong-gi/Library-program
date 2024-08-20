package view;

import java.util.List;
import java.util.Scanner;

import dto.LibraryDto;
import dto.LoanDto;



public class LibraryView {
	
	Scanner sc = new Scanner(System.in);
	
	// 프로그램 기능 출력
	public int getMenu() {
		System.out.println("----------------------");
		System.out.println("도서 관리 프로그램 입니다.");
		System.out.println("1. 책 대출");
		System.out.println("2. 책 반납");
		System.out.println("3. 책 요청");
		System.out.println("4. 보유중인 책 목록"); // 시간 되면 정렬 기능 추가
		System.out.println("5. 대출 중인 책 목록"); // 시간 되면 대출 기간 표시 고려
		System.out.println("6. 요청된 책 목록"); 
		System.out.println("7. 요청 취소");
		System.out.println("8. 종료");
		System.out.println("----------------------");
		System.out.println("원하는 기능을 선택해주세요");
		int choice = sc.nextInt();
		sc.nextLine();
		
		return choice;
	}
	
	// 프로그램 기능 출력
	public int getAdminMenu() {
		System.out.println("----------------------");
		System.out.println("도서 관리 프로그램 입니다.");
		System.out.println("1. 책 대출");
		System.out.println("2. 책 반납");
		System.out.println("3. 책 요청");
		System.out.println("4. 보유중인 책 목록"); // 시간 되면 정렬 기능 추가
		System.out.println("5. 대출 중인 책 목록"); // 
		System.out.println("6. 책 목록에 추가");
		System.out.println("7. 목록 수정"); 
		System.out.println("8. 목록에서 책 제거"); 
		System.out.println("9. 요청된 책 목록"); 
		System.out.println("10. 요청 취소");
		System.out.println("11. 종료");
		System.out.println("----------------------");
		System.out.println("원하는 기능을 선택해주세요");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}
	
	// 책 대출 - 책 번호
	public int loanIndex() {
		System.out.println("대출받고 싶은 책 번호를 입력하세요");
		System.out.println("-------------------------");
		int bookIndex = sc.nextInt();
		sc.nextLine();
		return bookIndex;
	}
	
	// 보유중인 책 목록 출력
	public void bookList(List<LibraryDto> libraryList) {
		// 검색을 원하는지 확인
		System.out.println("검색을 하고 싶으면 1, 목록을 보고 싶으면 2를 눌러주세요");
		System.out.println("------------------------------------------");
		System.out.print(">>");
		int num = sc.nextInt();
		sc.nextLine();
		// 검색 시 실행
		if(num == 1) {
			System.out.println("검색하고 싶은 책 제목을 입력해주세요");
			String title = sc.nextLine();
			boolean x = false;
			for(int i = 0; i < libraryList.size();i++) {
				if(libraryList.get(i).toString().contains(title)) {
					System.out.println(libraryList.get(i));
					x = true;
				}
			}
			if(x)
				System.out.println("'" + title + "'와 관련된 책을 검색하였습니다.");
			else
				System.out.println("'" + title + "'와 관련된 책은 없습니다.");
		}
		
		// 목록 출력
		else if(num == 2) {
			for(int i = 0; i < libraryList.size(); i++) {
				System.out.println(i +". " + libraryList.get(i));
			}
		}
		// 잘못 입력한 경우
		else {
			System.out.println("잘못입력하셨습니다.");
		}
	}
	
	// 대출 중인 책 목록 출력
	public void bookLoanList(List<LoanDto> libraryLoanList) {
		// 대출 중인 책이 있는지 확인
		if(libraryLoanList.isEmpty()) {
			System.out.println("대출 중인 책이 없습니다.");
		}
		// 대출 중인 책 목록 출력
		else {
			System.out.println("대출 중인 책 목록입니다.");
			for(int i = 0; i < libraryLoanList.size(); i++) {
				System.out.println(libraryLoanList.get(i));
			}
		}
	}

	// 요청된 책 목록 출력
	public void bookRequestList(List<LibraryDto> requestList) {
		if(!(requestList.isEmpty())) {
			System.out.println("요청된 책 목록입니다.");
			System.out.println("----------------");
			for(int i =0; i<requestList.size();i++) {
				System.out.println(i + ". " + requestList.get(i));
			}
		}
		else {
			System.out.println("요청되어있는 책이 없습니다.");
			return;
		}
	}
	
	// 책 제목 입력
	public String BookTitle() {
		System.out.println("책 제목을 입력하세요");
		System.out.println("---------------");
		String bookTitle = sc.nextLine();
		return bookTitle;
	}
	
	// 저자 입력
	public String BookWriter() {
		System.out.println("책의 저자를 입력하세요");
		System.out.println("----------------");
		String bookWriter = sc.nextLine();
		return bookWriter;
	}
	
	// 출판 연도 입력
	public String BookPublisher() {
		System.out.println("책의 출판 연도를 입력하세요");
		System.out.println("------------------");
		String bookPublisher = sc.nextLine();
		return bookPublisher;
	}

	// 목록 수정 - 책 번호
	public int updateBookIndex() {
		System.out.println("수정하고 싶은 책 번호를 입력하세요");
		System.out.println("-------------------------");
		int index = sc.nextInt();
		sc.nextLine();
		return index;
	}
	
	// 책 번호로 삭제
	public int removeBookIndex() {
		System.out.println("삭제하고 싶은 책 번호를 입력하세요");
		System.out.println("-------------------------");
		int bookIndex = sc.nextInt();
		sc.nextLine();
		return bookIndex;
	}
	
	// 책 제목으로 삭제 - 제목
	public String removeBookTitle() {
		System.out.println("삭제하고 싶은 책 제목을 입력하세요");
		System.out.println("-------------------------");
		String bookTitle = sc.nextLine();
		return bookTitle;
	}
	
	// 책 제목으로 삭제 - 저자
	public String removeBookWriter() {
	System.out.println("삭제하고 싶은 책의 저자를 입력하세요");
	System.out.println("--------------------------");
	String bookWriter = sc.nextLine();
	return bookWriter;
	}
	
	// 책 제목으로 삭제 - 출판 연도
	public String removeBookPublishYear() {
		System.out.println("삭제하고 싶은 책의 출판 연도를 입력하세요");
		System.out.println("--------------------------");
		String bookPublishYear = sc.nextLine();
		return bookPublishYear;
	}
}
