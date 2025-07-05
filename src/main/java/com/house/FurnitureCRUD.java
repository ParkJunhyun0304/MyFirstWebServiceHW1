package com.house;

import java.util.ArrayList;
import java.util.Scanner;

public class FurnitureCRUD implements  ICRUD{
    Scanner scanner;
    ArrayList<Furniture> list;

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

        return new Furniture(0, name, category, width, depth, height, price);
    }

    public void addFurniture() {
        Furniture furniture = (Furniture)add();
        list.add(furniture);
        System.out.println("가구가 정상적으로 등록 되었습니다.");
    }

    @Override
    public int update(Furniture obj) {
        return 0;
    }

    @Override
    public int delete(Furniture obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
}
