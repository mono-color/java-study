package ch14_jdbc_jsp;

import java.util.ArrayList;
import java.util.Scanner;

import ch14_jdbc_jsp.model.StudentVO;
import ch14_jdbc_jsp.model.WordGameVO;
import ch14_jdbc_jsp.service.StudentService;
import ch14_jdbc_jsp.service.WordGameService;

public class KoongPaGo {

	public static void main(String[] args) {
		
		System.out.println(" _   __                            ______         _____        \n" + 
				"| | / /                            | ___ \\       |  __ \\       \n" + 
				"| |/ /   ___    ___   _ __    __ _ | |_/ /  __ _ | |  \\/  ___  \n" + 
				"|    \\  / _ \\  / _ \\ | '_ \\  / _` ||  __/  / _` || | __  / _ \\ \n" + 
				"| |\\  \\| (_) || (_) || | | || (_| || |    | (_| || |_\\ \\| (_) |\n" + 
				"\\_| \\_/ \\___/  \\___/ |_| |_| \\__, |\\_|     \\__,_| \\____/ \\___/ \n" + 
				"                              __/ |                            \n" + 
				"                             |___/                             ");
		StudentService stuService = StudentService.getInstance();
		WordGameService wordService = WordGameService.getInstance();
		
		ArrayList<WordGameVO> wordGameList = wordService.getWordList();
		ArrayList<String> wordList = new ArrayList<>();
		for(int i = 0; i < wordGameList.size(); i++) {
			wordList.add(wordGameList.get(i).getWords());
		}
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("행동을 선택해주세요.");
			System.out.println("1. 로그인 | 2. 회원가입 | 3. 종료");
			System.out.print(">>> ");
			
			int command = 0;
			try {
				command = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("숫자로 입력해주세요.");
				continue;
			}
			
			if(command == 1) {
				// 로그인
				System.out.println("아이디를 입력해주세요.");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요.");
				System.out.print(">>> ");
				String pw = sc.nextLine();
				
				StudentVO login = stuService.loginStu(id);
				
				if(pw.equals(login.getStuPassword())) {
					// 로그인 성공
					System.out.println(login.getStuName() + "님 환영합니다.");
					
					while(true) {
						System.out.println("행동을 선택해주세요.");
						System.out.println("1. 끝말잇기 시작 | 2. 랭킹 | 3. 로그아웃");
						System.out.print(">>> ");
						
						int select = 0;
						
						try {
							select = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("숫자를 입력해주세요");
							continue;
						}
						
						if(select == 1) {
							// TODO 끝말잇기 시작
							
							// wordList에서 랜덤 인덱스의 단어를 하나 출력
							int randInt = (int)(Math.random() * wordList.size());
							String computerWord = wordList.get(randInt);
							System.out.println("제시어: " + computerWord);
							
							while(true) {
								System.out.print(">>> ");
								String inputText = sc.nextLine();
								
								// 1. inputText의 앞글자가 computerWord의 뒷글자와 같냐
								boolean checkOne = inputText.substring(0, 1)
										.equals(computerWord.substring(computerWord.length() - 1));
								// 2. inputText가 wordList에 포함되어 있는 단어냐
								boolean checkTwo = wordList.contains(inputText);
								
								if(checkOne && checkTwo) {
									// TODO 성공
									
									// inputText의 마지막 자리로 시작하는 단어들을
									// wordList에서 꺼내기
									// 컴퓨터가 대답할 수 있는 단어 목록
									ArrayList<String> possibleList = new ArrayList<>();
									for(int i = 0; i < wordList.size(); i++) {
										if(wordList.get(i).substring(0,1)
												.equals(inputText.substring(inputText.length() - 1))) {
											possibleList.add(wordList.get(i));
										}
									}
									
									// 대답할 수 있는 목록의 갯수가 0이면 컴퓨터 패배
									if(possibleList.size() == 0) {
										// 승리
										System.out.println("내가 승리한 것이지 인간이 승리한게 아니다.");
										login.setStuScore(login.getStuScore() + 1);
										stuService.updateStu(login);
										
										break;
									}
									
									int randIdx = (int)(Math.random() * possibleList.size());
									computerWord = possibleList.get(randIdx);
									System.out.println("제시어: " + computerWord);
									
								}else {
									// 실패
									System.out.println("인간이 진거지 내가 진것이 아니다.");
									break;
								}
							}
						}else if(select == 2) {
							// TODO 랭킹 보기
							ArrayList<StudentVO> stuList = stuService.getStuList();
							System.out.println("=============랭킹=============");
							for(int i = 0; i <stuList.size(); i++) {
								System.out.println((i+1) + ", " + stuList.get(i));
							} 
						}else if(select == 3) {
							// 로그아웃
							break;
						}else {
							System.out.println("잘못 입력하셨습니다.");
						}
					}
					
				}else {
					// 로그인 실패
					System.out.println("아이디 혹은 비밀번호가 다릅니다.");
				}
				
				
			}else if(command == 2) {
				// 회원가입 (Insert)
				System.out.println("아이디를 입력해주세요.");
				System.out.print(">>> ");
				String id = sc.nextLine();
				System.out.println("이름을 입력해주세요.");
				System.out.print(">>> ");
				String name = sc.nextLine();
				System.out.println("비밀번호를 입력해주세요.");
				System.out.print(">>> ");
				String pw = sc.nextLine();
				
				StudentVO temp = new StudentVO(id, name, pw, 0);
				stuService.insertStu(temp);
				
			}else if(command == 3) {
				System.out.println("종료합니다.");
				break;
			}else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}
		
	}

}
