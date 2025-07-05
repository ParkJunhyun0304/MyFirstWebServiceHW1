package com.house;

import java.util.Scanner;

public class FurnitureManager {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        while(true) {
            int menu = selectMenu();
            if(menu == 0) break;
            if(menu == 4) {
                // create
            } else if(menu == 1) {
                // 모든 물건보기
            } else if(menu == 2) {
                // 카테고리별 물건 보기
            } else if(menu == 3) {
                // 물건 검색
            } else if(menu == 5) {
                // 물건 수정
            } else if(menu == 6) {
                // 물건 삭제
            } else if(menu == 7) {
                // 물건 저장
            }
            System.out.println(menu);

        }
    }

    public int selectMenu() {
        System.out.println("------- 오늘의 집 -------\n" +
                "1. 모든 가구 보기\n" +
                "2. 카테고리별 물건 보기\n" +
                "3. 가구 검색\n" +
                "4. 가구 추가\n" +
                "5. 가구 수정\n" +
                "6. 가구 삭제\n" +
                "7. 가구 저장\n" +
                "0. 나가기\n\n" +
                "원하는 메뉴 => ");

        return scanner.nextInt();
    }
}
