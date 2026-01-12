# ユーザー管理システム API 仕様書 (User Management System API Specification)

## 1. 全ユーザー取得 (Get All Users)
*   **メソッド (Method)**: `GET`
*   **パス (Path)**: `/api/users`
*   **説明 (Description)**: システム内の全ユーザーのリストを取得する。ページネーション対応。
*   **パラメータ (Parameters)**:
    *   `page` (Query, int): ページ番号 (デフォルト: 1)
    *   `size` (Query, int): 1ページあたりの件数 (デフォルト: 10)
*   **レスポンス (Response 200 OK)**:
    ```json
    [
      {
        "id": 1,
        "username": "zhangsan",
        "email": "zhangsan@example.com",
        "createdAt": "2023-10-27T10:00:00"
      }
    ]
    ```

## 2. IDによるユーザー取得 (Get User by ID)
*   **メソッド (Method)**: `GET`
*   **パス (Path)**: `/api/users/{id}`
*   **説明 (Description)**: 一意の ID を指定してユーザーの詳細情報を取得する。
*   **パラメータ (Parameters)**:
    *   `id` (Path Variable): ユーザー ID (Long)
*   **レスポンス (Response 200 OK)**:
    ```json
    {
      "id": 1,
      "username": "zhangsan",
      "email": "zhangsan@example.com",
      "createdAt": "2023-10-27T10:00:00"
    }
    ```
*   **レスポンス (Response 404 Not Found)**: 指定された ID のユーザーが存在しない場合に返却される。
