# バックエンド開発仕様書 (Backend & RDS Spec)

## 1. コアアーキテクチャ原則
- **JDK バージョン**: Java 25 (LTS)
- **フレームワーク**: Spring Boot 3.x
- **永続化層**: MyBatis 3 + H2 Database

## 2. エンティティと DTO 戦略
- **Entity (POJO)**: 永続化層のマッピングにのみ使用する。必ず引数なしコンストラクタ (No-Args Constructor) を含めること。
- **DTO (Record)**: 不変性 (Immutability) を保証するため、Java `record` を使用して定義すること。
- **変換ロジック**: 変換メソッドは DTO の静的ファクトリメソッド (例: `UserDTO.fromEntity(User user)`) として定義すること。
- **漏洩防止**: Controller 層から Entity を直接返却することを厳禁とする。必ず DTO に変換すること。

## 3. 依存性の注入 (Dependency Injection)
- **コンストラクタ注入の強制**: `@Autowired` によるフィールド注入を厳禁とする。
- **実装例**:
  ```java
  private final UserMapper userMapper;
  public UserServiceImpl(UserMapper userMapper) {
      this.userMapper = userMapper;
  }
  ```

## 4. MyBatis ページネーション標準
- **ネイティブ SQL ページネーション**: 追加のプラグインを導入せず、SQL の `LIMIT` と `OFFSET` を使用する。
- **Mapper シグネチャ**: `List<T> findAll(@Param("offset") int offset, @Param("limit") int limit);`
- **Service 計算式**: `offset = (page - 1) * size`。

## 5. コーディング禁則事項
- **❌ var の禁止**: 全ての変数は明示的に型を宣言すること。
