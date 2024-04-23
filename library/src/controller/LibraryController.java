package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import dto.LibraryDto;
import dto.LoanDto;
import service.LibraryService;
import view.LibraryView;

public class LibraryController {

	Scanner sc = new Scanner(System.in);
	
	private LibraryView libraryView = new LibraryView();
	private LibraryService libraryService;
//	private LibraryDto libraryDto;
	
	
	public LibraryController(LibraryService libraryService) {
		this.libraryView = new LibraryView();
		this.libraryService = libraryService;
	}
	
	// 저장 파일 읽기 - 보유중인 책 목록
	public void fileReaderBookList() {
		BufferedReader bookList = null;
		BufferedReader bookLoanList = null;
		BufferedReader bookRequestList = null;
	try{
		bookList = new BufferedReader(new FileReader("./도서 관리 목록.txt"));
		bookLoanList = new BufferedReader(new FileReader("./대출중인 책 목록.txt"));	
		bookRequestList = new BufferedReader(new FileReader("./요청된 책 목록.txt"));
			// 읽어 올 문장을 담기 위해 변수 선언
			String line = "";
			while((line = bookList.readLine()) != null) {
				// 문자열 자르기
				String arr[] = line.split(",");
				// 책 제목에 ","가 있는 경우
				if(arr.length != 3) {
					// 책 제목을 저장할 문자열
					String arr1 = null;
					for(int i = 0; i < (arr.length-2);i++) {
						arr1 += arr[i];
					}
					// 출력 시 "책 이름:", "저자:", "출판 연도:"를 지우기
					String title = arr1.replace("책 이름", "");
					String[] arr2 = arr[arr.length-2].split(":", 2);
					String writwer = arr2[1];
					String[] arr3 = arr[arr.length-1].split(":", 2);
					String publishYear = arr3[1];
					LibraryDto libraryDto = new LibraryDto(title, writwer, publishYear);
					libraryService.addBook(libraryDto);
				}
				else {
					String[] arr1 = arr[0].split(":", 2);
					String title = arr1[1];
					String[] arr2 = arr[arr.length-2].split(":", 2);
					String writwer = arr2[1];
					String[] arr3 = arr[arr.length-1].split(":", 2);
					String publishYear = arr3[1];
					LibraryDto libraryDto = new LibraryDto(title, writwer, publishYear);
					libraryService.addBook(libraryDto);
				}
			}
			
			
			// 대출중인 책 목록
			line = "";
			while((line = bookLoanList.readLine()) != null) {
				// 문자열 자르기
				String arr[] = line.split(",");
				// 책 제목에 ","가 있는 경우
				if(arr.length != 2) {
					// 책 제목을 저장할 문자열
					String arr1 = null;
					for(int i = 0; i < (arr.length-1);i++) {
						arr1 += arr[i];
					}
					// 출력 시 "대출중인 책:"과 "반납 날짜:"를 지우기
					String title = arr1.replace("대출중인 책:", "");
					String[] arr2 = arr[arr.length-1].split(":", 2);
					String returnDay = arr2[1];
					LoanDto loanDto = new LoanDto(title, returnDay);
					libraryService.addLoanBook(loanDto);
				}
				else {
					// 출력 시 "대출중인 책:"과 "반납 날짜:"를 지우기
					String[] arr1 = arr[0].split(":", 2);
					String title = arr1[1];
					String[] arr2 = arr[1].split(":", 2);
					String returnDay = arr2[1];
					// 책 제목과 반납 날짜 불러오기
					LoanDto loanDto = new LoanDto(title, returnDay);
					libraryService.addLoanBook(loanDto);
				}
			}
			if(bookLoanList != null)
			bookLoanList.close();
			
			// 요청된 책 목록
			line = "";
			while((line = bookList.readLine()) != null) {
				// 문자열 자르기
				String arr[] = line.split(",");
				// 책 제목에 ","가 있는 경우
				if(arr.length != 3) {
					// 책 제목을 저장할 문자열
					String arr1 = null;
					for(int i = 0; i < (arr.length-2);i++) {
						arr1 += arr[i];
					}
					// 출력 시 "책 이름:", "저자:", "출판 연도:"를 지우기
					String title = arr1.replace("책 이름", "");
					String[] arr2 = arr[arr.length-2].split(":", 2);
					String writwer = arr2[1];
					String[] arr3 = arr[arr.length-1].split(":", 2);
					String publishYear = arr3[1];
					LibraryDto libraryDto = new LibraryDto(title, writwer, publishYear);
					libraryService.addRequestBook(libraryDto);
				}
				else {
					String[] arr1 = arr[0].split(":", 2);
					String title = arr1[1];
					String[] arr2 = arr[arr.length-2].split(":", 2);
					String writwer = arr2[1];
					String[] arr3 = arr[arr.length-1].split(":", 2);
					String publishYear = arr3[1];
					LibraryDto libraryDto = new LibraryDto(title, writwer, publishYear);
					libraryService.addRequestBook(libraryDto);
				}
			}
	
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(bookList != null) {
					try {
						bookList.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(bookLoanList != null) {
					try {
						bookLoanList.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(bookRequestList != null) {
					try {
						bookRequestList.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
					
			}
	}
	
	// 저장 파일 쓰기 - 프로그램 종료 시 저장
	public void fileWriterBookList() {
	BufferedWriter bookList = null;
	BufferedWriter bookLoanList = null;
	BufferedWriter bookRequestList = null;
	try {
		bookList = new BufferedWriter(new FileWriter("./도서 관리 목록.txt"));
		bookLoanList = new BufferedWriter(new FileWriter("대출중인 책 목록.txt"));	
		bookRequestList = new BufferedWriter(new FileWriter("./요청된 책 목록.txt"));
		// 보유중인 책 목록
		for(int i = 0; i < libraryService.getBookList().size();i++) {
			bookList.write(libraryService.getBookList().get(i).toString());
			bookList.newLine();
			}
		
		// 대출중인 책 목록
		for(int i = 0; i < libraryService.getBookLoanList().size();i++) {
			bookLoanList.write(libraryService.getBookLoanList().get(i).toString());
			bookLoanList.newLine();
			}
		
		// 요청된 책 목록
		for(int i = 0; i < libraryService.getBookRequestList().size();i++) {
			bookRequestList.write(libraryService.getBookRequestList().get(i).toString());
			bookRequestList.newLine();
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(bookList != null) {
				try {
					bookList.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bookLoanList != null) {
				try {
					bookLoanList.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bookRequestList != null) {
				try {
					bookRequestList.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 프로그램 실행
	public void runProgram() {
		// 파일 불러오기
		fileReaderBookList();
		// 개행 발생 시 sc.nextLine()을 사용하기 위해 변수 선언(num)
		int num = 0;
		int choice = 0;
			while(true) {
				choice = libraryView.getMenu();
				if(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6 || 
						choice == 7 || choice == 8 || choice == 9999) {
					switch(choice) {
					case 1:
						// 책 대출
						num = 1;
						loan();
						break;
						
					case 2:
						// 책 반납
						num = 1;
						bookReturn();
						break;
						
					case 3:
						// 책 요청
						num = 1;
						requestBook();
						break;
						
					case 4:
						// 보유중인 책 목록 출력
						num = 1;
						getBookList();
						break;
						
					case 5:
						// 대출 중인 책 목록 출력
						num = 1;
						getBookLoanList();
						break;
					
					case 6:
						// 요청된 책 목록 출력
						num = 1;
						bookRequestList();
						break;
						
					case 7:
						// 요청 취소
						num = 1;
						removeBookRequest();
						break;
						
					case 8:
						// 프로그램 종료
						System.out.println("프로그램을 종료합니다.");
						fileWriterBookList();
						return;
						
					case 9999:
						// 관리자 권한으로 실행
						System.out.println("비밀번호를 입력해주세요");
						// 비밀번호 설정
						String ps = "admin9999";
						// 비밀번호 입력
						String str = sc.next();
						// 개행이 있는 경우
						if(num == 1)
							sc.nextLine();
						// 비밀번호가 올바른 경우
						if(str.equals(ps))
							runAdminProgram();
						// 비밀번호가 틀린 경우
						else {
							System.out.println("비밀번호를 잘못입력했습니다.");
							num = 1;
							break;
						}
					}
				}		
				else {
					System.out.println("잘못입력하셨습니다.");
				}
			}
	}
	// 관리자 권한으로 프로그램 실행
	public void runAdminProgram() {
		System.out.println("관리자 모드로 실행됩니다.");
		while(true) {
				int choice = libraryView.getAdminMenu();
				if(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6 || 
						choice == 7 || choice == 8 || choice == 9 ||choice == 10 ||choice == 11 ||choice == 9999) {
				switch(choice) {
					case 1:
						// 책 대출
						loan();
						break;
						
					case 2:
						// 책 반납
						bookReturn();
						break;
							
					case 3:
						// 책 요청
						requestBook();
						break;
							
					case 4:
						// 보유중인 책 목록 출력
						getBookList();
						break;
						
					case 5:
						// 대출 중인 책 목록 출력
						getBookLoanList();
						break;
						
					case 6:
						// 기존의 목록에서 새로운 책 추가
						newAddBook();
						break;
						
					case 7:
						// 목록 수정
						updateBook();
						break;
						
					case 8:
						// 책 번호 또는 제목으로 목록에서 책 삭제
						removeBook();
						break;
						
					case 9:
						// 요청된 책 목록 출력
						bookRequestList();
						break;
						
					case 10:
						// 요청 취소
						removeBookRequest();
						break;
						
					case 11:
						// 프로그램 종료
						System.out.println("일반 모드로 전환됩니다.");
						return;
					}
				}
			}
	}
	
	// 책 대출
	public void loan() {
		System.out.println("책을 대출합니다.");
		System.out.println("책 번호로 대출하고 싶으면 1, 책 제목으로 대출하고 싶으면 2를 눌러주세요");
		int num = sc.nextInt();
		if(num == 1) {
			int bookIndex = libraryView.loanIndex();
			libraryService.loan(bookIndex);
		}
		
		else if(num == 2) {
			String bookTitle = libraryView.BookTitle();
			String bookWriter = libraryView.BookWriter();
			String bookPublishYear = libraryView.BookPublisher();
			LibraryDto libraryDto = new LibraryDto(bookTitle, bookWriter, bookPublishYear);
			libraryService.loan(libraryDto);
		}
		else
			System.out.println("잘못 입력하셨습니다.");
	}
	
	// 책 반납
	public void bookReturn() {
		String bookTitle = libraryView.BookTitle();
		String bookWriter = libraryView.BookWriter();
		String bookPublishYear = libraryView.BookPublisher();
		LibraryDto libraryDto = new LibraryDto(bookTitle, bookWriter, bookPublishYear);
		libraryService.bookReturnTitle(libraryDto);
	}
	
	// 책 요청
	public void requestBook() {
		String bookTitle = libraryView.BookTitle();
		String bookWriter = libraryView.BookWriter();
		String bookPublishYear = libraryView.BookPublisher();
		LibraryDto libraryDto = new LibraryDto(bookTitle, bookWriter, bookPublishYear);
		libraryService.requestBook(libraryDto);
	}
	
	// 보유중인 책 목록 출력
	public void getBookList() {
		libraryView.bookList(libraryService.getBookList());
	}
	
	// 대출 중인 책 목록 출력
	public void getBookLoanList() {
		libraryView.bookLoanList(libraryService.getBookLoanList());
	}
	
	// 요청된 책 목록 출력
	public void bookRequestList() {
		libraryView.bookRequestList(libraryService.getBookRequestList());
	}

	// 기존의 목록에 새로운 책 추가
	public void newAddBook() {
		// 책의 제목, 저자, 출판 연도 입력
		String bookTitle = libraryView.BookTitle();
		String bookWriter = libraryView.BookWriter();
		String bookPublishYear = libraryView.BookPublisher();
		LibraryDto libraryDto = new LibraryDto(bookTitle, bookWriter, bookPublishYear);
		// 배열에 책 제목 저장
		libraryService.addBook(libraryDto);
	}
	
	// 목록 수정
	public void updateBook() {
		// 목록 번호 가져오기
		int index = libraryView.updateBookIndex();
		System.out.println(libraryService.getBookList().get(index) + "를 수정합니다.");
		// 책 제목 수정 - 대출이 안되어 있는 책 중에서 수정 가능
		if(!(libraryService.getBookList().get(index).toString().contains("(대출 중)"))) {
			// 내용 수정
			String bookTitle = libraryView.BookTitle();
			String bookWriter = libraryView.BookWriter();
			String bookPublishYear = libraryView.BookPublisher();
			LibraryDto libraryDto = new LibraryDto(bookTitle, bookWriter, bookPublishYear);
			libraryService.updateBook(index, libraryDto);
		}
		else
			System.out.println("대출중인 책입니다.");
	}
	
	// 책 번호 또는 제목으로 목록에서 책 삭제
	public void removeBook() {
		System.out.println("책 번호로 삭제하고 싶으면 1, 책 제목으로 삭제하고 싶으면 2를 눌러주세요");
		int num = sc.nextInt();
		if(num == 1) {
			int bookIndex = libraryView.removeBookIndex();
			libraryService.removeBook(bookIndex);
		}
			
		else if(num == 2) {
			String bookTitle = libraryView.removeBookTitle();
			String bookWriter = libraryView.removeBookWriter();
			String bookPublishYear = libraryView.removeBookPublishYear();
			LibraryDto libraryDto = new LibraryDto(bookTitle, bookWriter, bookPublishYear);
			libraryService.removeBook(libraryDto);
		}
		else
			System.out.println("잘못입력했습니다.");
	}
	
	
	// 요청된 책 삭제
	public void removeBookRequest() {
		System.out.println("책 번호로 삭제하고 싶으면 1, 책 제목으로 삭제하고 싶으면 2를 눌러주세요");
		int num = sc.nextInt();
		if(num == 1) {
			int bookIndex = libraryView.removeBookIndex();
			libraryService.removeRequestBook(bookIndex);
		}
			
		if(num == 2) {
			String bookTitle = libraryView.removeBookTitle();
			String bookWriter = libraryView.removeBookWriter();
			String bookPublishYear = libraryView.removeBookPublishYear();
			LibraryDto libraryDto = new LibraryDto(bookTitle, bookWriter, bookPublishYear);
			libraryService.removeRequestBook(libraryDto);
		}
	}
}
