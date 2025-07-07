package com.house;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FurnitureCRUD implements  ICRUD{
    Scanner scanner;
    ArrayList<Furniture> list;
    final String fileName = "Furniture.txt";

    String[] categories = {
            "침대", "매트리스 토퍼", "테이블/식탁/책상", "소파",
            "서랍/수납장", "행거/옷장", "거울", "화장대/콘솔",
            "유아동기구", "야외기구", "가벽/파티션"
    };

    FurnitureCRUD(Scanner scanner) {
        list = new ArrayList<>();
        this.scanner = scanner;
    }

    @Override
    public Furniture add() {
        System.out.print("이름 : ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("해당되는 카테고리\n" +
                "1) 침대\t2) 매트리스 토퍼\n" +
                "3) 테이블/식탁/책상\t4) 소파\n" +
                "5) 서랍/수납장\t6) 행거/옷장\n" +
                "7) 거울\t8) 화장대/콘솔\n" +
                "9) 유아동기구\t10) 야외기구\n" +
                "11) 가벽/파티션\n=> ");
        int category = scanner.nextInt();

        System.out.print("가로길이 입력(mm) : ");
        int width = scanner.nextInt();

        System.out.print("세로길이 입력(mm) : ");
        int depth = scanner.nextInt();

        System.out.print("높이 입력(mm) : ");
        int height = scanner.nextInt();

        System.out.print("가격 입력(원) : ");
        int price = scanner.nextInt();

        return new Furniture(0, name, price, category, width, depth, height);
    }

    public void addFurniture() {
        Furniture furniture = (Furniture)add();
        list.add(furniture);
        System.out.println("가구가 정상적으로 등록 되었습니다.");
    }

    @Override
    public int update() {
        System.out.print("수정할 가구의 카테고리를 입력하세요 : ");
        int searchIndex = scanner.nextInt();
        ArrayList<Integer> searchList = listAll(searchIndex);

        if(searchList == null) {
            System.out.println("해당 카테고리에 등록된 가구가 없습니다.\n");
            return 0;
        }

        System.out.print("수정할 번호 선택 : ");
        int updateIndex = scanner.nextInt();

        System.out.print("가격 입력(원) : ");
        int price = scanner.nextInt();

        System.out.print("가로길이 입력(mm) : ");
        int width = scanner.nextInt();

        System.out.print("세로길이 입력(mm) : ");
        int depth = scanner.nextInt();

        System.out.print("높이 입력(mm) : ");
        int height = scanner.nextInt();

        Furniture furniture = list.get(searchList.get(updateIndex-1));

        furniture.setData(price,width,depth,height);
        System.out.println("수정이 되었습니다.\n");
        return 0;
    }

    @Override
    public int delete() {
        System.out.print("삭제할 가구의 카테고리를 입력하세요 : ");
        int searchIndex = scanner.nextInt();
        ArrayList<Integer> searchList = listAll(searchIndex);

        if(searchList == null) {
            System.out.println("해당 카테고리에 등록된 가구가 없습니다.\n");
            return 0;
        }
        System.out.print("수정할 번호 선택 : ");
        int updateIndex = scanner.nextInt();

        System.out.print("정말 삭제하시겠습니까?(Y/N) : ");
        String answer = scanner.next();

        if(answer.equalsIgnoreCase("y")) {
            System.out.println("index : " + searchList.get(updateIndex-1));
            list.remove((int)searchList.get(updateIndex-1));
            System.out.println("삭제 되었습니다.\n");
        } else if(answer.equalsIgnoreCase("n")) {
            System.out.println("삭제가 취소 되었습니다.\n");
        } else {
            System.out.println("입력값 오류\n");
        }
        return 0;
    }

    @Override
    public void selectOne(int id) {
        if(id > list.size() || id <= 0) {
            System.out.println("입력값이 잘못되었습니다.\n");
            return;
        }
        System.out.println("\n=====================");

        System.out.print((id) + " ");
        System.out.println(list.get(id-1).toString());

        System.out.println("=====================\n");
    }

    public void findByCategory() {
        int index = 1;
        System.out.println("\n=====================");

        System.out.print("검색을 원하는 카테고리의 번호를 입력하세요 : ");
        int searchIndex = scanner.nextInt();

        System.out.println(categories[searchIndex-1] +"카테고리");

        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getCategory() == searchIndex) {
                System.out.print((index) + " ");
                System.out.println(list.get(i).toString());
                index ++;
            }
        }

        System.out.println("=====================\n");
    }

    public void listAll() {
        System.out.println("\n=====================");

        for(int i=0; i<list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }

        System.out.println("=====================\n");
    }

    public ArrayList<Integer> listAll(int index) {
        int num = 1;
        ArrayList<Integer> searchList = new ArrayList<>();

        System.out.println("\n=====================");

        for(int i=0; i<list.size(); i++) {
            if(list.get(i).getCategory() == index) {
                System.out.print((num) + " ");
                System.out.println(list.get(i).toString());
                searchList.add(i);
                num++;
            }
        }

        System.out.println("=====================\n");
        if(searchList.size() == 0) {
            return null;
        } else return searchList;
    }
}
