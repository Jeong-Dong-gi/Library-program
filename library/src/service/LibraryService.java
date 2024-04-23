package service;

import java.util.List;

import dto.LibraryDto;
import dto.LoanDto;

public interface LibraryService {

	// Create(생성) - 목록에 책 추가
	void addBook(LibraryDto book);
	
	// 대출중인 책 목록 읽어오기
	public void addLoanBook(LoanDto book);
	
	// 요청된 책 목록 읽어오기
	public void addRequestBook(LibraryDto book);
	
	// 책 대출
		// 책 번호로 대출
		void loan(int index);
		
		// 책 제목으로 대출
		void loan(LibraryDto title);
		
	// 책 반납
	void bookReturnTitle(LibraryDto title);
		
	// 책 요청
	void requestBook(LibraryDto book);
	
	// 보유중인 책 목록 출력
	List<LibraryDto> getBookList();
	
	// 대출 중인 책 목록 출력
	List<LoanDto> getBookLoanList();
	
	// Update(수정) - 책 이름 수정
	void updateBook(int index, LibraryDto book);
	
	// Delete
		// Delete(번호를 이용한 삭제)
		void removeBook(int index);
		
		// Delete(책 제목을 통한 삭제)
		void removeBook(LibraryDto book);
	
	// 요청된 책 목록 출력
	List<LibraryDto> getBookRequestList();
	
	// 요청된 책 삭제
		// Delete(번호를 이용한 삭제)
		void removeRequestBook(int index);
			
		// Delete(책 제목을 통한 삭제)
		void removeRequestBook(LibraryDto book);

}
