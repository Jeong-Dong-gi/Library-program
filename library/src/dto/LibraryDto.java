package dto;

import java.io.Serializable;

public class LibraryDto implements Serializable{
	
	private String book;
	private String writer;
	private String publicationYear;

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	public LibraryDto(String book, String writer, String publicationYear) {
		this.book = book;
		this.writer = writer;
		this.publicationYear = publicationYear;
	}

	public LibraryDto(String book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "책 이름:" + book + ", 저자:" + writer + ", 출판 연도:" + publicationYear;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LibraryDto) {
			LibraryDto l = (LibraryDto) obj;
			return book.equals(l.book) && writer.equals(l.writer) && publicationYear.equals(l.publicationYear);
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
