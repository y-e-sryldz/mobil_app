# Mobil app

Bu proje, kullanıcıların spor aktivitelerini takip etmesini, sensörlerden veri almasını ve bulut tabanlı yapay zeka ile egzersiz önerileri sunmasını sağlayan bir mobil uygulamadır. Proje, ekip üyeleri arasında görev paylaşımı ile geliştirilmiştir.

## Kullanılan Teknolojiler ve Araçlar

- **Programlama Dilleri**: Kotlin, Swift, Dart
- **Veritabanı**: Room (Android), CoreData (iOS), SQLite
- **RESTful API**: Retrofit (Android), Alamofire (iOS), Flask (Backend)
- **UI**: Jetpack Compose (Android), SwiftUI (iOS), Flutter
- **Arka Plan İşlemleri**: WorkManager, AlarmManager
- **Sensörler**: Location
- **Bağlantı**: BluetoothAdapter, BLE (Bluetooth Low Energy)
- **Kimlik Doğrulama**: OAuth, OpenID, JWT
- **Bulut Hizmetleri**: Google Firebase, AWS Rekognition, TensorFlow

  
## Özellikler

1. **Storage / Basic Data**  
   Kullanıcıların temel verilerini cihazlarında güvenli bir şekilde saklamayı sağlar.

2. **Local Database (Room / CoreData)**  
   Kullanıcı verileri, Android'de Room veya iOS'da CoreData kullanılarak saklanır. Veritabanı yapısı @Entity ve @Dao anotasyonlarıyla tanımlanır.

3. **RESTFul API (CRUD)**  
   Kullanıcı bilgilerini sunucuda saklamak ve yönetmek için Retrofit kütüphanesi ve Flask tabanlı bir API kullanılır. GET, POST, PUT, DELETE işlemleri desteklenir.

4. **UI (Compose / SwiftUI)**  
   Modern arayüz tasarımları, Jetpack Compose ve SwiftUI ile yapılmıştır. Dinamik ekran bileşenleri kullanılarak akıcı bir kullanıcı deneyimi sağlanır.

5. **Background Process / Task**  
   WorkManager ve AlarmManager kullanılarak zamanlanmış veya sürekli arka plan görevleri yerine getirilir.

6. **Broadcast Receiver / NSNotificationCenter**  
   Cihazın durum değişikliklerine (Wi-Fi, batarya durumu gibi) uygulamanın tepki vermesi sağlanır.

7. **Sensor (Motion / Location / Environment)**  
   - Akselerometre ve jiroskop sensörleri ile hareket türleri tespit edilir.  
   - GPS ile konum bilgisi alınarak antrenman rotaları oluşturulur.

8. **Connectivity (BLE / Wifi / Cellular Network / USB / NFC)**  
   BLE bağlantıları ile fitness cihazlarıyla veri alışverişi yapılabilir. Wi-Fi, hücresel ağ ve NFC özellikleri desteklenir.

9. **Authorization (OAuth / OpenID / JWT)**  
   Güvenli giriş için Google ve Facebook SDK'ları ile OAuth entegrasyonu yapılmıştır.

10. **Cloud Service (AI)**  
    Google Firebase ve TensorFlow gibi bulut tabanlı yapay zeka servisleri ile kullanıcı verileri analiz edilir ve egzersiz önerileri sunulur.

---


## Projeyi Çalıştırmak İçin

### Gereksinimler
- Android Studio / Xcode
- Flutter SDK
- Bir Firebase Projesi
- Gerekli API Anahtarları (Google, Facebook, BLE)

### Kurulum Adımları
1. **Depoyu Klonlayın**  
   ```bash
   git clone https://github.com/y-e-sryldz/mobil_app.git
   cd mobil_app

   
## EKRAN GÖRÜNTÜLERİ
### Kayıt Olma Ekranı
Kullanıcılar bu ekrandan sisteme kayıt olabilirler. Kullanıcı adı, e-posta, şifre ve rol bilgileri girilerek yeni bir kullanıcı hesabı oluşturulabilir.

###Ekran

Kullanıcılar ekranda listeleniyor.

![mobil_app](https://github.com/user-attachments/assets/d19c6297-3330-4ca9-94d3-b17157b5d3e2)

Kullanıcılar düzenleniyor

![lsite](https://github.com/user-attachments/assets/86a18b9b-70af-4d83-be46-d49648730872)
