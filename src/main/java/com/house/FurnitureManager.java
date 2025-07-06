package com.house;

import java.util.Scanner;

public class FurnitureManager {
    Scanner scanner = new Scanner(System.in);
    FurnitureCRUD furnitureCRUD;

    FurnitureManager() {
        furnitureCRUD = new FurnitureCRUD(scanner);
    }

    public void start() {
//        furnitureCRUD.loadFile();
        furnitureCRUD.loadData(0);
        while(true) {
            int menu = selectMenu();
            if(menu == 0) break;
            if(menu == 4) {
                // 가구 추가
                furnitureCRUD.addFurniture();
            } else if(menu == 1) {
                // 모든 물건보기
                furnitureCRUD.listAll(0);
            } else if(menu == 2) {
                // 카테고리별 물건 보기
                furnitureCRUD.findByCategory();
            } else if(menu == 3) {
                // 가구 검색
                System.out.print("검색을 원하는 가구의 번호를 입력하세요 : ");
                int id = scanner.nextInt();
                furnitureCRUD.selectOne(id);
            } else if(menu == 5) {
                // 가구 수정
                furnitureCRUD.updateFurn();
            } else if(menu == 6) {
                furnitureCRUD.deleteFurn();
                // 가구 삭제
            }

        }
    }

    public int selectMenu() {
        System.out.print("======= 오늘의 집 =======\n" +
                "1. 모든 가구 보기\n" +
                "2. 카테고리별 물건 보기\n" +
                "3. 가구 검색\n" +
                "4. 가구 추가\n" +
                "5. 가구 수정\n" +
                "6. 가구 삭제\n" +
                "0. 나가기\n\n" +
                "원하는 메뉴 => ");

        return scanner.nextInt();
    }
}
