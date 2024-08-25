# NetworkEx - Pokemon App

## Giới thiệu

NetworkEx là một ứng dụng Android đơn giản hiển thị danh sách các Pokémon từ API của PokéAPI. Ứng dụng sử dụng Retrofit để thực hiện các yêu cầu HTTP, Hilt để tiêm phụ thuộc (Dependency Injection), Glide để tải và hiển thị hình ảnh của các Pokémon, và Room để lưu trữ dữ liệu offline.

## Tính năng

- Hiển thị danh sách các Pokémon với hình ảnh và số thứ tự.
- Tính năng cuộn vô hạn (infinite scroll) để tải thêm Pokémon khi người dùng cuộn đến gần cuối danh sách.
- Giao diện lưới (grid) hiển thị 2 cột với các màu nền khác nhau cho mỗi item.
- **Lưu trữ dữ liệu offline**: Ứng dụng lưu trữ dữ liệu Pokémon đã tải về để hiển thị khi không có kết nối mạng.

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
5. Nếu không có kết nối mạng, ứng dụng sẽ hiển thị dữ liệu Pokémon đã được lưu trữ trước đó.

## Thư viện sử dụng

- **Retrofit**: Thực hiện các yêu cầu HTTP.
- **Glide**: Tải và hiển thị hình ảnh từ URL.
- **Hilt**: Tiêm phụ thuộc cho các lớp trong ứng dụng.
- **Kotlin Coroutines**: Xử lý tác vụ không đồng bộ.
- **Room**: Lưu trữ dữ liệu offline để hiển thị khi không có kết nối mạng.
