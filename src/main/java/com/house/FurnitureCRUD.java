package com.house;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FurnitureCRUD implements  ICRUD{
    final String SELECT_ALL = "SELECT * FROM Furniture";
    final String FURN_INSERT = "INSERT INTO Furniture (name, price, category, width, depth, height)" +
            "VALUES (?,?,?,?,?,?)";
    final String FURN_UPDATE = "UPDATE Furniture SET price = ?, width = ?, depth = ?, height = ? WHERE id = ?";
    final String FURN_DELETE = "DELETE FROM Furniture WHERE id = ?";
    final String SELECT_BY_CATEGORY = "SELECT * FROM Furniture WHERE category = ?";
    final String SELECT_BY_ID = "SELECT * FROM Furniture WHERE id = ?";

    Scanner scanner;
    ArrayList<Furniture> list;
//    final String fileName = "Furniture.txt";
    Connection connection;

    String[] categories = {
            "침대", "매트리스 토퍼", "테이블/식탁/책상", "소파",
            "서랍/수납장", "행거/옷장", "거울", "화장대/콘솔",
            "유아동기구", "야외기구", "가벽/파티션"
    };

    FurnitureCRUD(Scanner scanner) {
        list = new ArrayList<>();
        this.scanner = scanner;
        connection = DBConnection.getConnection();
    }

    public void loadData(int searchIndex) {
        list.clear();
        try {
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            if(searchIndex > 0) {
                preparedStatement = connection.prepareStatement(SELECT_BY_CATEGORY);
                preparedStatement.setInt(1, searchIndex);
                resultSet = preparedStatement.executeQuery();
            } else {
                preparedStatement = connection.prepareStatement(SELECT_ALL);
                resultSet = preparedStatement.executeQuery();
            }
            while(true) {
                if(!resultSet.next()) break;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int category = resultSet.getInt("category");
                int width = resultSet.getInt("width");
                int depth = resultSet.getInt("depth");
                int height = resultSet.getInt("height");
                list.add(new Furniture(id, name, price, category, width, depth, height));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int add(Object object) {
        Furniture furniture = (Furniture) object;
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FURN_INSERT);
            preparedStatement.setString(1, furniture.getName());
            preparedStatement.setInt(2, furniture.getPrice());
            preparedStatement.setInt(3, furniture.getCategory());
            preparedStatement.setInt(4, furniture.getWidth());
            preparedStatement.setInt(5, furniture.getDepth());
            preparedStatement.setInt(6, furniture.getHeight());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void addFurniture() {
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

        Furniture furniture = new Furniture(0, name, price, category, width, depth, height);
        int success = add(furniture);
        if(success == 1) {
            System.out.println("가구가 정상적으로 등록 되었습니다.");
        } else {
            System.out.println("가구 등록에 실패했습니다.");
        }
    }

    @Override
    public int update(Object object) {
        Furniture furniture = (Furniture) object;
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FURN_UPDATE);
            preparedStatement.setInt(1, furniture.getPrice());
            preparedStatement.setInt(2, furniture.getWidth());
            preparedStatement.setInt(3, furniture.getDepth());
            preparedStatement.setInt(4, furniture.getHeight());
            preparedStatement.setInt(5, furniture.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void updateFurn() {
        System.out.print("카테고리\n" +
                "1) 침대\t2) 매트리스 토퍼\t 3) 테이블/식탁/책상\n" +
                "4) 소파\t 5) 서랍/수납장\t6) 행거/옷장\n" +
                "7) 거울\t8) 화장대/콘솔\t9) 유아동기구\n" +
                "10) 야외기구\t 11) 가벽/파티션\n");
        System.out.print("수정할 가구의 카테고리를 입력하세요 : ");
        int searchIndex = scanner.nextInt();
        listAll(searchIndex);

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

        int result = update(new Furniture(list.get(updateIndex - 1).getId(), "", price, 0, width, depth, height));

        if (result == 1) {
            System.out.println("수정이 되었습니다.\n");
        } else {
            System.out.println("수정에 실패했습니다.\n");
        }
    }

    @Override
    public int delete(Object object) {
        Furniture furniture = (Furniture) object;
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FURN_DELETE);
            preparedStatement.setInt(1, furniture.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void deleteFurn() {
        System.out.print("카테고리\n" +
                "1) 침대\t2) 매트리스 토퍼\t 3) 테이블/식탁/책상\n" +
                "4) 소파\t 5) 서랍/수납장\t6) 행거/옷장\n" +
                "7) 거울\t8) 화장대/콘솔\t9) 유아동기구\n" +
                "10) 야외기구\t 11) 가벽/파티션\n");
        System.out.print("삭제할 가구의 카테고리를 입력하세요 : ");
        int searchIndex = scanner.nextInt();
        listAll(searchIndex);

        System.out.print("삭제할 번호 선택 : ");
        int updateIndex = scanner.nextInt();

        System.out.print("정말 삭제하시겠습니까?(Y/N) : ");
        String answer = scanner.next();

        if(answer.equalsIgnoreCase("y")) {
            int result = delete(new Furniture(list.get(updateIndex - 1).getId(), "", 0, 0, 0, 0, 0));
            if(result == 1) {
                System.out.println("삭제 되었습니다.\n");
            } else {
                System.out.println("삭제에 실패했습니다.\n");
            }
        } else if(answer.equalsIgnoreCase("n")) {
            System.out.println("삭제가 취소 되었습니다.\n");
        } else {
            System.out.println("입력값 오류\n");
        }
    }

    public void selectOne(int id) {
        if(id > list.size() || id <= 0) {
            System.out.println("입력값이 잘못되었습니다.\n");
            return;
        }
        System.out.println("\n=====================");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");
            int category = resultSet.getInt("category");
            int width = resultSet.getInt("width");
            int depth = resultSet.getInt("depth");
            int height = resultSet.getInt("height");

            Furniture furniture = new Furniture(id, name, price, category, width, depth, height);
            System.out.println(furniture.toString());

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("=====================\n");
    }

    public void findByCategory() {
        int index = 1;
        System.out.println("\n=====================");

        System.out.print("카테고리\n" +
                "1) 침대\t2) 매트리스 토퍼\t 3) 테이블/식탁/책상\n" +
                "4) 소파\t 5) 서랍/수납장\t6) 행거/옷장\n" +
                "7) 거울\t8) 화장대/콘솔\t9) 유아동기구\n" +
                "10) 야외기구\t 11) 가벽/파티션\n");
        System.out.print("검색을 원하는 카테고리의 번호를 입력하세요 : ");
        int searchIndex = scanner.nextInt();

        System.out.println(categories[searchIndex-1] +"카테고리");

        loadData(searchIndex);
        for(int i=0; i<list.size(); i++) {
            System.out.print((index) + " ");
            System.out.println(list.get(i).toString());
            index ++;
        }

        System.out.println("=====================\n");
    }

    public void listAll(int searchIndex) {
        loadData(searchIndex);
        System.out.println("\n=====================");

        for(int i=0; i<list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }

        System.out.println("=====================\n");
    }

//    public ArrayList<Integer> listAll(int index) {
//        int num = 1;
//        ArrayList<Integer> searchList = new ArrayList<>();
//
//        System.out.println("\n=====================");
//
//        for(int i=0; i<list.size(); i++) {
//            if(list.get(i).getCategory() == index) {
//                System.out.print((num) + " ");
//                System.out.println(list.get(i).toString());
//                searchList.add(i);
//                num++;
//            }
//        }
//
//        System.out.println("=====================\n");
//        if(searchList.size() == 0) {
//            return null;
//        } else return searchList;
//    }

//    public void loadFile() {
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
//            String line;
//            int count = 0;
//
//            while(true) {
//                line = br.readLine();
//                if(line == null) break;
//                String data[] = line.split(",");
//                String name = data[0];
//                int price = Integer.parseInt(data[1]);
//                int category = Integer.parseInt(data[2]);
//                int width = Integer.parseInt(data[3]);
//                int depth = Integer.parseInt(data[4]);
//                int height = Integer.parseInt(data[5]);
//
//                list.add(new Furniture(0, name, price, category, width, depth, height));
//                count ++;
//            }
//            br.close();
//            System.out.println(count+"개의 가구 로딩 완료\n");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void saveFile() {
//        try {
//            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
//
//            StringBuilder sb = new StringBuilder();
//            for(Furniture furniture : list) {
//                sb.append(furniture.toFileString());
//                sb.append("\n");
//            }
//            pw.write(sb.toString());
//            pw.close();
//            System.out.println("저장 완료");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
