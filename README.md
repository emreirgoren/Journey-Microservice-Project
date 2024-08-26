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

#### Kullanıcı Giriş İşlemi

```http
  POST http://localhost:9000/api/v1/auth/login
```

#### Kullanıcı Rol Değişimi İşlemi

```http
  POST http://localhost:9000/api/v1/auth/changeRole
```
#### Admin Sefer Ekleme İşlemi

```http
  POST http://localhost:9000/api/v1/journeys/
```
#### Admin Sefer İptal İşlemi

```http
  POST http://localhost:9000/api/v1/journeys/cancelled
```
#### Admin Satış Raporu Görüntüleme İşlemi

```http
  POST http://localhost:9000/api/v1/journeys/getSalesReport
```

#### Kullanıcı Şehre Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/searchByCity
```

#### Kullanıcı Şehre ve Taşıt Türüne Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/searchByCityAndVehicleType
```

#### Kullanıcı Şehre ve Tarihe Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/searchByCityAndDepartureDate
```

#### Kullanıcı Bilet Koduna Göre Sefer Arama İşlemleri

```http
  POST http://localhost:9000/api/v1/searches/{ticketCode}
```

#### Kullanıcı Sepete Ekleme İşlemleri

```http
  POST http://localhost:9000/api/v1/baskets
```

#### Kullanıcı Sepet Temizle İşlemi

```http
  POST http://localhost:9000/api/v1/baskets/clear
```

#### Kullanıcı Sepet Görüntüleme İşlemi

```http
  POST http://localhost:9000/api/v1/baskets
```

#### Kullanıcı Ödeme İşlemleri

```http
  POST http://localhost:9000/api/v1/baskets/paymentToBankAccountNumber
```


```http
  POST http://localhost:9000/api/v1/baskets/paymentToCreditCard
```








