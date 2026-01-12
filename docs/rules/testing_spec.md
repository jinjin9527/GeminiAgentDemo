# 単体テスト仕様書 (Unit Testing Spec)

## 1. テストアーキテクチャ
- **ツールチェーン**: JUnit 5 + Mockito + AssertJ。
- **軽量化原則**: 単体テストでの `@SpringBootTest` の使用や Spring コンテキストの起動を厳禁とする。
- **拡張**: `@ExtendWith(MockitoExtension.class)` を使用すること。

## 2. 命名規則と構造
- **命名フォーマット**: `should_[期待される結果]_when_[条件]`。
- **AAA パターン**: テスト本体は Arrange (準備), Act (実行), Assert (検証) の3パートに明確に分けること。

## 3. アサーションスタイル
- **AssertJ**: 流れるようなインターフェース (Fluent Interface) を使用すること。
- **例**: `assertThat(result).hasSize(1);`

## 4. 禁則事項
- **❌ var の禁止**: テストコード内においても `var` の使用を禁止する。
