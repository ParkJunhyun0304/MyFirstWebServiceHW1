# 22100304 박준현
---

## 프로그램 설명
- 오늘의 집을 모티브로 하여 판매할 가구 등록 CRUD 프로그램입니다.
- 브랜치 설명
  * CRUD version : 비영속적 방식의 CRUD 개발 브랜치
  * withFile : local file 기반 데이터 자장 방식의 CRUD 개발 브랜치
  * withDB : SQLite DataBase 기반의 CRUD 개발 브랜치
### 기술 스택 및 개발환경
- 언어 : Java17 이상
- DB : SQLite3 (GUI : DB Browser for SQLite 사용)
- JDBC : sqlite-jdbc 라이브러리 사용 (Maven)
  ```
  <dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.49.1.0</version>
  </dependency>
  ```
### 주요기능

| 기능명 | 기능설명 |
| --- | ---------------- |
| 가구 추가 | 사용자가 입력한 이름, 가격, 카테고리, 사이즈를 통해 새로운 가구를 추가합니다. |
| 기구 조회 | 사용자는 모든가구를 조회할 수 있습니다.|
| 가구 수정 | 사용자는 카테고리별로 가구를 조회한 후, 리스트에서 가구를 선택해 해당 가구의 가격, 사이즈를 수정할 수 있습니다.|
| 가구 삭제 | 사용자는 카테고리별로 가구를 조회한 후, 리스트에서 가구를 선택해 해당 가구를 삭제할 수 있습니다.|
| 가구 검색 | 사용자는 2가지 검색 옵션으로 가구를 검색할 수 있습니다.<br>1. 카테고리별 가구 조회 2. 가구 ID값으로 조회 |

### CLI 사용 흐름 예시
======= 오늘의 집 =======<br>
1.모든 가구 보기<br>
2. 카테고리별 물건 보기<br>
3. 가구 검색<br>
4. 가구 추가<br>
5. 가구 수정<br>
6. 가구 삭제<br>
7. 가구 저장<br>
0. 나가기<br><br>
### 데이터 모델 예시
```
class Furniture {
  private int id;
  private String name;
  private int price;
  private int category;
  private int width; // 가로
  private int depth; //세로
  private int height; // 높이
}
```
### 테이블 스키마
```
CREATE TABLE "Furniture" (
	"id"	INTEGER,
	"name"	TEXT,
	"price"	INTEGER,
	"category"	INTEGER,
	"width"	INTEGER,
	"depth"	INTEGER,
	"height"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT)
);
```
### ERD
![image](https://github.com/user-attachments/assets/359df1c4-bfc8-439e-82bc-e60308d2a508)


## 실행 테스트
### 프로그램 실행 후 1번 (모든 가구보기) 메뉴 실행
![image](https://github.com/user-attachments/assets/5627c9fc-6b67-4767-9188-3335a0e14305)
### 2번(카테고리별 물건보기) 메뉴 실행
- 1번 카테고리 (침대) 선택
![image](https://github.com/user-attachments/assets/6b81eb5c-7b2e-4411-9c91-10c1748b13cf)
- 2번 카테고리 (매트리스 토퍼) 선택
![image](https://github.com/user-attachments/assets/efe6c8a7-f7c6-4441-8a30-fe36148005d4)
### 3번(가구검색) 메뉴 실행
![image](https://github.com/user-attachments/assets/1c963b33-1788-4d9d-a22d-17c9402a3ab2)
### 4번(가구추가) 메뉴 실행
- 전신 거울 (40000원)[거울] [w:250mm][d:100mm][h:1300mm] 로 입력
![image](https://github.com/user-attachments/assets/7831db17-5eb2-4c05-94e2-8f03504d8b2c)
### 추가 성공 확인을 위한 전체 리스트 출력 (1번)
![image](https://github.com/user-attachments/assets/d5e7a8c5-f47a-4da6-bb74-465cdb85432c)
### 가구 수정
- 등록된 가구가 없는 카테고리(4번) 선택시 예외처리
![image](https://github.com/user-attachments/assets/a5742a28-8cc4-486b-b5d5-6e48549c86b1)
- 전신 거울 (40000원)[거울] [w:250mm][d:100mm][h:1300mm]
-> 전신 거울 (14000원)[거울] [w:250mm][d:100mm][h:1800mm] 로 수정

![image](https://github.com/user-attachments/assets/1a0f129d-6680-4f6c-b943-1d22fc52cacd)
- 수정후 데이터 조회

![image](https://github.com/user-attachments/assets/a0ca21f4-c231-4b70-abc3-51f5be6fdd4f)
### 가구 삭제
- 등록된 가구가 없는 카테고리(5번) 선택시 예외처리

![image](https://github.com/user-attachments/assets/77991f8a-5b93-4c0b-87df-d3d79a4d1208)
- 매트리스킹 삭제

![image](https://github.com/user-attachments/assets/fc9dc5f9-0aaa-44a4-85a4-6cb0a7002176)
- 삭제후 데이터 조회

![image](https://github.com/user-attachments/assets/9f87d908-eb45-4de0-8495-8f15cc91f1d5)
### 종료

![image](https://github.com/user-attachments/assets/d3dc9103-e6ec-4e5f-a28f-c213b04ef263)





