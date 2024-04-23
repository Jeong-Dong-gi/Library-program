package dto;


public class LoanDto {
	private String book;
	private String returnDay;
	
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getReturnDay() {
		return returnDay;
	}
	public void setReturnDay(String returnDay) {
		this.returnDay = returnDay;
	}
	@Override
	public String toString() {
		return "대출중인 책:" + book + ", 반납 날짜:" + returnDay;
	}
	
	public LoanDto(String book, String returnDay) {
		this.book = book;
		this.returnDay = returnDay;
	}
	
	public LoanDto(String book) {
		this.book = book;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LoanDto) {
			LoanDto l = (LoanDto) obj;
			return book.equals(l.book);
		}
		else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		return book.hashCode();
	}
	
	
	
	
	
}
