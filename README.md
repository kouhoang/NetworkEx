# NetworkEx - Pokemon App

## Giới thiệu

NetworkEx là một ứng dụng Android đơn giản hiển thị danh sách các Pokemon từ API của PokeAPI. Ứng dụng sử dụng `Retrofit` để thực hiện các yêu cầu HTTP và `Picasso` để tải và hiển thị hình ảnh của các Pokemon.

## Tính năng

- Hiển thị danh sách các Pokemon với hình ảnh và số thứ tự.
- Tính năng cuộn vô hạn (infinite scroll) để tải thêm Pokemon khi người dùng cuộn đến gần cuối danh sách.
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
3. Đồng bộ dự án với Gradle files.
4. Chạy ứng dụng trên thiết bị Android hoặc trình giả lập.

## Sử dụng

1. Mở ứng dụng.
2. Danh sách Pokemon sẽ tự động được tải và hiển thị.
3. Cuộn xuống để tải thêm các Pokemon khác.
4. Mỗi Pokemon sẽ hiển thị tên, số thứ tự, và hình ảnh.

## Kiến trúc dự án

- **MainActivity**: Hoạt động chính hiển thị `RecyclerView` và xử lý tải dữ liệu từ API.
- **PokemonAdapter**: Bộ điều hợp cho `RecyclerView`, quản lý hiển thị danh sách Pokemon.
- **PokeApiService**: Giao diện Retrofit định nghĩa các yêu cầu HTTP đến PokeAPI.
- **Pokemon**: Lớp dữ liệu đại diện cho một Pokemon.
- **PokemonResponse**: Lớp đại diện cho phản hồi từ API chứa danh sách Pokemon.

## Thư viện sử dụng

- **Retrofit**: Thực hiện các yêu cầu HTTP.
- **Picasso**: Tải và hiển thị hình ảnh từ URL.
- **Kotlin Coroutines**: Xử lý tác vụ không đồng bộ.

