## Dibaca yak, ini dah dibuat isi dari projectnya...

# Aplikasi Pesan Makanan & Minuman dengan Konsep OOP dan MySQL Database

## Deskripsi Proyek
#### Proyek ini merupakan implementasi aplikasi pesan makanan dan minuman menggunakan bahasa pemrograman Java dengan menerapkan konsep Pemrograman Berorientasi Objek (OOP). Aplikasi ini mencakup fitur-fitur utama seperti manajemen data pelanggan, katalog makanan dan minuman, top up saldo, serta pesan dan pembayaran menggunakan saldo pelanggan.

## Fitur Utama
- #### Manajemen Data Pelanggan:
  Pengguna dapat menambah, memperbarui, dan menghapus data diri pelanggan.
  Informasi pelanggan mencakup nama, alamat, dan saldo.

- #### Katalog Makanan & Minuman:
  Pengguna dapat melihat seluruh makanan dan minuman yang tersedia.
  Makanan dan minuman terbagi ke dalam kategori-kategori seperti main course, appetizer, dessert, soft drink, juice, dsb.
- #### Top Up Saldo:
  Pengguna dapat melakukan top up saldo akun mereka.
- #### Pesan dan Pembayaran:
  Pengguna dapat memesan makanan dan minuman.
  Pembayaran dilakukan menggunakan saldo yang dimiliki oleh pelanggan.
  Setelah berhasil melakukan transaksi, ditampilkan invoice.
  
## Implementasi Konsep OOP
#### Abstract dan Interface:
Terdapat interface MenuManagement untuk manajemen data menu.
Kelas Menu sebagai kelas abstrak yang mewakili menu dengan properti umum seperti nama, dan harga.

#### Encapsulation:
Data menu dienkapsulasi dalam kelas Menu.
Metode-metode untuk mengelola data menu diimplementasikan dalam kelas Menu.

#### Inheritance:
Kelas Food dan Beverage mewarisi sifat dan metode dari kelas abstrak Menu.

#### Polymorphism:
Metode saveToDatabase() diimplementasikan secara berbeda oleh Food dan Beverage sesuai dengan kebutuhan.

## Penggunaan Database MySQL
### Struktur Tabel:
#### - Tabel customers untuk menyimpan data pelanggan.
#### - Tabel foods dan beverages untuk menyimpan data makanan dan minuman.
#### - Tabel orders untuk menyimpan informasi pesanan.
#### - Tabel orderdetails untuk menyimpan detail dari setiap item dalam pesanan.

### Foreign Key dan Constraint:
#### - Penggunaan kunci asing untuk mengaitkan tabel orders dan order_items dengan customers, foods, dan beverages.
#### - Pengaturan ON DELETE dan ON UPDATE pada kunci asing untuk menjaga referensial integritas data.

## Cara Menjalankan Aplikasi
#### 1. Pastikan Java JDK telah terinstal.
#### 2. Pastikan MySQL server telah terinstal dan konfigurasi koneksi sesuai.
#### 3. Eksekusi skrip SQL untuk membuat tabel dan struktur database.
#### 4. Atur koneksi database pada kelas DatabaseConnection di proyek Java.
#### 5. Jalankan aplikasi dari main class.
