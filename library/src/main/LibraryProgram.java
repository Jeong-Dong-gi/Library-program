package main;

import controller.LibraryController;
import service.LibraryList;

public class LibraryProgram {

	public static void main(String[] args) {
		
		// 프로그램 시작
		LibraryController libraryController =
				new LibraryController(new LibraryList());
		
		// 프로그램 실행
		libraryController.runProgram();
	}
}
