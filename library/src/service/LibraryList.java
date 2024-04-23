package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dto.LibraryDto;
import dto.LoanDto;

// 메모리에 책 목록을 저장
public class LibraryList implements LibraryService {

	// 책 목록
	private List<LibraryDto> bookList = new ArrayList<>();
	// 대출 받은 책 목록 및 날짜
	private List<LoanDto> bookLoanList = new ArrayList<>();
	// 요청받은 책 목록
	private List<LibraryDto> bookRequestList = new ArrayList<>();
	// 날짜 형식 지정
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	
	
	// 책 대출
	// 책 번호로 대출
	@Override
	public void loan(int index) {
		
		// 책 번호를 가진 책의 여부 확인
		if(index > (bookList.size()-1)) {
			System.out.println("책 번호" + index + "은 없습니다.");
			return;
		}
		// 음수를 받은 경우
		else if(index < 0) {
			System.out.println("숫자를 잘못입력했습니다.");
			return;
		}
		// 이미 대출 중인 책은 대출 불가
		else if(bookList.get(index).getBook().contains("(대출 중)")) {
			System.out.println("대출 중인 책입니다.");
			return;
		}
		// 책을 대출하고 대출 표시
		else {
			// 오늘 날짜 입력
			String today = dateFormat.format(cal.getTime());
			// cal에 저장되어 있는 날짜가 오늘 날짜와 다른 경우
			if(!today.equals(String.valueOf(cal))) {
				// cal을 오늘 날짜로 변경
				cal = Calendar.getInstance();
			}
			// 대출 기간 14일 안에 반납
			cal.add(Calendar.DATE, 13);
			String returnTime = dateFormat.format(cal.getTime());
			// 대출 받은 책과 반납 날짜 저장
			LoanDto loanDto = new LoanDto(bookList.get(index).getBook(), returnTime);
			bookLoanList.add(loanDto);
			// 대출 표시
			String title = bookList.get(index).getBook();
			title = title + " (대출 중)";
			LibraryDto libraryDto = new LibraryDto(title, bookList.get(index).getWriter(), bookList.get(index).getPublicationYear());
			System.out.println("'" + bookList.get(index) + "'를 대출합니다.");
			bookList.set(index, libraryDto);
		}
	}

	// 책 제목으로 대출
	@Override
	public void loan(LibraryDto book) {
		// 책이 없는 경우를 위한 변수
		boolean x = true;
		for(int i = 0; i <bookList.size();i++) {
			// 대출 중인지 확인 - 동일한 제목을 가진 책 확인 전에 먼저 확인해야 함
			if(bookList.get(i).getBook().contains("(대출 중)") && bookList.get(i).getWriter().equals(book.getWriter()) &&
					bookList.get(i).getPublicationYear().equals(book.getPublicationYear())) {
				System.out.println("대출 중인 책입니다.");
				x = false;
			}
			// 책 대출
			if(bookList.get(i).equals(book)) {
				// 오늘 날짜 입력
				String today = dateFormat.format(cal.getTime());
				// cal에 저장되어 있는 날짜가 오늘 날짜와 다른 경우
				if(!today.equals(String.valueOf(cal))) {
					// cal을 오늘 날짜로 변경
					cal = Calendar.getInstance();
				}
				// 대출 기간 14일 안에 반납
				cal.add(Calendar.DATE, 13);
				// String 타입으로 반납 기간 입력
				String returnTime = dateFormat.format(cal.getTime());
				// 대출 받은 책과 반납 날짜 저장
				LoanDto loanDto = new LoanDto(book.getBook(),returnTime);
				bookLoanList.add(loanDto);
				// 책을 대출하고 대출 표시
				String title = book.getBook() + " (대출 중)";
				LibraryDto libraryDto = new LibraryDto(title, bookList.get(i).getWriter(), bookList.get(i).getPublicationYear());
				bookList.set(i,libraryDto);
				System.out.println("'" + book.getBook() + "'를 대출합니다.");
				x = false;
			}
		}
		// 책이 없는 경우
		if(x){
			System.out.println("보유하고 있지 않은 책입니다.");
		}
	}
	
	// 책 반납
	@Override
	public void bookReturnTitle(LibraryDto book) {
		for(int i = 0; i < bookList.size(); i++) {
			// 대출중인 책 목록에서 여부 확인
			if(bookList.get(i).getBook().equals(book.getBook() + " (대출 중)") && bookList.get(i).getWriter().equals(book.getWriter()) &&
					bookList.get(i).getPublicationYear().equals(book.getPublicationYear())) {
				System.out.println("반납되었습니다.");
				// bookLoanList에서 반납받은 책 삭제하기
				for(int j = bookLoanList.size()-1;j >= 0; j--) {
					if(bookLoanList.get(j).getBook().equals(book.getBook())) {
						bookLoanList.remove(j);
					}
				}
				// bookList에서 대출 받은 책 수정
				LibraryDto libraryDto = new LibraryDto(book.getBook(), bookList.get(i).getWriter(), bookList.get(i).getPublicationYear());
				bookList.set(i, libraryDto);
				return;
			}
		}
		System.out.println("대출중인 책이 아닙니다.");
	}
	
	// 책 요청
	@Override
	public void requestBook(LibraryDto book) {
		// 책이 목록에 포함되어 있는지 확인
		for(int i = 0; i<bookList.size();i++) {
			if(book.equals(bookList.get(i))) {
				System.out.println("보유중인 책 입니다.");
				return;
				}
			}
		// 책이 대출중인 책 목록에 있는지 확인
		for(int i = 0; i < bookLoanList.size();i++) {
			if(book.getBook().equals(bookLoanList.get(i).getBook())) {
				System.out.println("대출중인 책입니다.");
				return;
			}
		}
		System.out.println("책을 요청하였습니다.");
		bookRequestList.add(book);
	}
	
	// 보유중인 책 목록 출력
	@Override
	public List<LibraryDto> getBookList() {
		return bookList;
	}
	
	// 대출 중인 책 목록 출력
	@Override
	public List<LoanDto> getBookLoanList() {
		return bookLoanList;
	}

	// 요청된 책 목록 출력
	@Override
	public List<LibraryDto> getBookRequestList() {
		return bookRequestList;
	}

	// Create(생성) - 목록에 책 추가
	@Override
	public void addBook(LibraryDto book) {
		// 책을 보유하고 있는지 확인 -> 보유 중이면 추가 x
		for(int i = 0; i < bookList.size();i++) {
			if(book.equals(bookList.get(i))) {
				System.out.println("보유하고 있는 책입니다.");
				return;
			}
		}
		// 대출 중이면 추가 x
		for(int i = 0; i<bookLoanList.size();i++) {
			if(book.getBook().equals(bookLoanList.get(i).getBook())) {
				System.out.println("대출중인 책입니다.");
				return;
			}
		}
		bookList.add(book);
	}
	
	// 대출중인 책 목록 읽어오기
	public void addLoanBook(LoanDto book) {
		bookLoanList.add(book);
	}
	
	// 요청된 책 목록 읽어오기
	public void addRequestBook(LibraryDto book) {
		bookRequestList.add(book);
	}

	// Update(수정) - 책 이름 수정
	@Override
	public void updateBook(int index, LibraryDto book) {
		// 보유중인 책 목록에서 수정
		bookList.set(index, book);
	}

	// Delete(삭제) - 책 번호
	@Override
	public void removeBook(int index) {
		// 책 번호가 없는 경우
		if(index >= bookList.size() || index < 0)
			System.out.println("없는 책 번호입니다.");
		// 대출 중인 경우 삭제 x
		else if(bookList.get(index).toString().contains("(대출 중)"))
			System.out.println("대출중인 책입니다.");
		else {
			System.out.println(index + "번째 " + bookList.get(index) + "을 삭제합니다.");
			bookList.remove(index);	
		}
	}

	// Delete(삭제) - 책 제목
	@Override
	public void removeBook(LibraryDto book) {
		// 책이 없는 경우
		boolean x = true;
		// 대출중인 경우 삭제 x
		for(int i = 0; i<bookLoanList.size();i++) {
			if(bookLoanList.get(i).getBook().equals(book.getBook())) {
				System.out.println("대출 중입니다.");
				x = false;
			}
			// 대출되어있지 않은 경우 삭제
			for(int j = 0; j<bookList.size(); j++) {
			if(bookList.get(j).equals(book)) {
				System.out.println(i + "번째 " + bookList.get(j) + "을 삭제합니다.");
				bookList.remove(book);
				x = false;
				}
			}
		}
		// 책이 없는 경우
		if(x)
			System.out.println("없는 책입니다.");
	}


	// Delete(삭제) - 요청 취소 - 책 번호
	@Override
	public void removeRequestBook(int index) {
		// 목록에 있는지 확인
		if(index >= 0 && index < bookRequestList.size()) {
		System.out.println(index + "번째 " + "'" + bookRequestList.get(index) + "'을 삭제합니다.");
		bookRequestList.remove(index);
		}
		else
			System.out.println("요청된 목록에 없습니다.");
	}

	// Delete(삭제) - 요청 취소 - 책 제목
	@Override
	public void removeRequestBook(LibraryDto book) {
		// 요청된 목록에 요청 취소한 책이 있는지 확인
		boolean x = true;
		// 목록에 있는지 확인
		for(int i = 0; i < bookRequestList.size();i++) {
			if(bookRequestList.get(i).equals(book)) {
				System.out.println(i + "번째 " + "'" + bookRequestList.get(i) + "'을 삭제합니다.");
				bookRequestList.remove(book);
				x = false;
			}	
		}
		if(x)
			System.out.println("요청된 목록에 없습니다.");
	}
}
