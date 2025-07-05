package com.house;

import java.util.ArrayList;
import java.util.Scanner;

public class ObejctCRUD implements  ICRUD{
    Scanner scanner = new Scanner(System.in);
    ArrayList<Object> list;

    ObejctCRUD() {
        list = new ArrayList<>();
    }

    @Override
    public Object add() {
        System.out.println("이름 : ");
        String name = scanner.nextLine();

        System.out.println("해당되는 카테고리\n" +
                "1) 침대\n : " +
                "2) 매트리스 토퍼\n : " +
                "3) 테이블/식탁/책상\n : " +
                "4) 소파\n : " +
                "5) 서랍/수납장\n : " +
                "6) 행거/옷장\n : " +
                "7) 거울\n : " +
                "8) 화장대/콘솔\n" +
                "9) 유아동기구\n" +
                "10) 야외기구\n" +
                "11) 가벽/파티션\n");
        int category = scanner.nextInt();

        System.out.println("가로길이 입력(mm) : ");
        int width = scanner.nextInt();

        System.out.println("세로길이 입력(mm) : ");
        int depth = scanner.nextInt();

        System.out.println("높이 입력(mm) : ");
        int height = scanner.nextInt();

        System.out.println("가격 입력(원) : ");
        int price = scanner.nextInt();

        return new Object(0, name, category, width, depth, height, price);
    }

    public void addObject() {

    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
}
