# FT Teknoloji Bootcamp Final Projesi

## Online Bilet Uygulaması

Proje microservice mimarisi olarak tasarlanmıştır. 

- api-gateway --> auth-servisten aldığı token ile servislere erişimi belirler ve yönlendirir. 

- auth-service --> Kullanıcı register ve login işlemleri.

- user-service --> Kullanıcı bilgilerini görme, role atama ve kullanıcılar ile ilgili işlemler içerir.

- journey-service --> Admin sefer ekleyebilir, iptal edebilir ve satış raporu oluşturabilir.

- ticket-search-service --> Kullanıcı şehre, tarihe ve taşıt türüne göre arama yapabilir. Bilet kodu ile sefer bilgilerine bakabilir.

- basket-service --> Kullanıcı sepete bilet ekleyebilir, silebilir ve sepetini görüntüleyebilir. Sepete ekledikleri biletleri kredi kartı veya banka transferi ile ödeme yapabilir.

- payment-service --> Ödeme işlemlerini basket-service ile senkron gerçekleştirir.

- journey-index-service --> journey-serviste kaydedilen sefer bilgilerini elasticsearch veritabanına kaydeder.

- exception-log-service --> Bütün serviste oluşan exception ve logları mongodb'ye kaydeder.

- notification-service --> Sms gönderimi, mail gönderimi ve push notification işlerini içerir.

## Proje Diagramı

## Projenin Çalıştırılması

 - Projenin ihtiyaç duyduğu teknolojiler ve veritabanları için docker-compose dosyası çalıştırılır. --> "docker-compose up -d"

 - Projedeki servisler ayağa kaldırılır. --> "localhost:9000/"
 - Projenin endpointlerine <b> API Kullanımı </b> bölümünden detaylarına ulaşabilirsiniz.

## API Kullanımı

#### Kullanıcı Kayıt İşlemi

```http
  POST http://localhost:9000/api/v1/auth/register
```
<p align="center">
    <img src ="/screenshots/register.png">
</p>

#### Kullanıcı Giriş İşlemi

```http
  POST http://localhost:9000/api/v1/auth/login
```
<p align="center">
    <img src ="/screenshots/login.png">
</p>

#### Kullanıcı Rol Değişimi İşlemi

```http
  POST http://localhost:9000/api/v1/auth/changeRole
```
<p align="center">
    <img src ="/screenshots/change role.png">
</p>

#### Admin Sefer Ekleme İşlemi

```http
  POST http://localhost:9000/api/v1/journeys/
```
<p align="center">
    <img src ="/screenshots/create journey.png">
</p>

#### Admin Sefer İptal İşlemi

```http
  POST http://localhost:9000/api/v1/journeys/cancelled
```
<p align="center">
    <img src ="/screenshots/cancelled.png">
</p>

#### Admin Satış Raporu Görüntüleme İşlemi

```http
  POST http://localhost:9000/api/v1/journeys/getSalesReport
```
<p align="center">
    <img src ="/screenshots/sales report.png">
</p>

#### Kullanıcı Şehre Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/searchByCity
```
<p align="center">
    <img src ="/screenshots/search by city.png">
</p>

#### Kullanıcı Şehre ve Taşıt Türüne Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/searchByCityAndVehicleType
```
<p align="center">
    <img src ="/screenshots/search by city and vehicle type.png">
</p>

#### Kullanıcı Şehre ve Tarihe Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/searchByCityAndDepartureDate
```
<p align="center">
    <img src ="/screenshots/search by city and departuredate.png">
</p>

#### Kullanıcı Bilet Koduna Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/{ticketCode}
```
<p align="center">
    <img src ="/screenshots/search ticket code.png">
</p>

#### Kullanıcı Sepete Ekleme İşlemleri

```http
  POST http://localhost:9000/api/v1/baskets
```
<p align="center">
    <img src ="/screenshots/sepete ekleme.png">
</p>

#### Kullanıcı Sepet Temizle İşlemi

```http
  POST http://localhost:9000/api/v1/baskets/clear
```
<p align="center">
    <img src ="/screenshots/sepet temizleme.png">
</p>

#### Kullanıcı Sepet Görüntüleme İşlemi

```http
  POST http://localhost:9000/api/v1/baskets
```
<p align="center">
    <img src ="/screenshots/sepet görüntüleme.png">
</p>

#### Kullanıcı Ödeme İşlemleri

```http
  POST http://localhost:9000/api/v1/baskets/paymentToBankAccountNumber
```
<p align="center">
    <img src ="/screenshots/payment bank transfer.png">
</p>

```http
  POST http://localhost:9000/api/v1/baskets/paymentToCreditCard
```
<p align="center">
    <img src ="/screenshots/payment credit card.png">
</p>







