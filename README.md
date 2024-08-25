# NetworkEx - Pokemon App

## Giới thiệu

NetworkEx là một ứng dụng Android đơn giản hiển thị danh sách các Pokémon từ API của PokéAPI. Ứng dụng sử dụng Retrofit để thực hiện các yêu cầu HTTP, Hilt để tiêm phụ thuộc (Dependency Injection), và Glide để tải và hiển thị hình ảnh của các Pokémon.

## Tính năng

- Hiển thị danh sách các Pokémon với hình ảnh và số thứ tự.
- Tính năng cuộn vô hạn (infinite scroll) để tải thêm Pokémon khi người dùng cuộn đến gần cuối danh sách.
- Giao diện lưới (grid) hiển thị 2 cột với các màu nền khác nhau cho mỗi item.

## Cài đặt

### Yêu cầu hệ thống

- Android SDK 24 trở lên

### Hướng dẫn cài đặt

1. Clone repository:

    ```bash
    git clone https://github.com/kouhoang/NetworkEx.git
    ```

2. Mở dự án trong Android Studio.

3. Đồng bộ dự án với các file Gradle.

4. Chạy ứng dụng trên thiết bị Android hoặc trình giả lập.

## Sử dụng

1. Mở ứng dụng.
2. Danh sách Pokémon sẽ tự động được tải và hiển thị.
3. Cuộn xuống để tải thêm các Pokémon khác.
4. Mỗi Pokémon sẽ hiển thị tên, số thứ tự, và hình ảnh.

## Kiến trúc dự án

- **MainActivity**: Hoạt động chính hiển thị RecyclerView và xử lý tải dữ liệu từ API.
- **PokemonAdapter**: Bộ điều hợp cho RecyclerView, quản lý hiển thị danh sách Pokémon.
- **PokemonViewModel**: Quản lý logic dữ liệu và trạng thái của ứng dụng.
- **PokemonRepository**: Quản lý các tác vụ liên quan đến dữ liệu, sử dụng Retrofit để lấy dữ liệu từ API.
- **PokeApiService**: Giao diện Retrofit định nghĩa các yêu cầu HTTP đến PokéAPI.
- **Pokemon**: Lớp dữ liệu đại diện cho một Pokémon.
- **PokemonResponse**: Lớp đại diện cho phản hồi từ API chứa danh sách Pokémon.
- **NetworkModule**: Hilt module cung cấp các phụ thuộc cần thiết cho Retrofit và PokeApiService.

## Thư viện sử dụng

- **Retrofit**: Thực hiện các yêu cầu HTTP.
- **Glide**: Tải và hiển thị hình ảnh từ URL.
- **Hilt**: Tiêm phụ thuộc cho các lớp trong ứng dụng.
- **Kotlin Coroutines**: Xử lý tác vụ không đồng bộ.

