<h1>Post System</h1>

<h3>Dasturni ishlatish:</h3>
Dasturni ishlatish uchun birinchi `db_schema.sql` nomi database sxemasini birinchi o'zingizni databaseingizga restore qiling database nomini iloji boricha `post` qiling, (`post` qilmasangiz dastuni ichidagi PostgressConnection classni ichidagi connectionni postni ozingizni database nomini qoying).  `/Post/src/main/java/org/example/dao/PostgresConnection.java` shu yerdagi jdbc dan ozingizni postgresql dagi username ingiz(iloji boricha `postgres` bo'lsin ) va postgresql parolini qoying.

<h4>Login:</h4>
```http request
http://localhost:8080/login?username=elbek_uzdev&password=1234
```
Login qilish uchun foydalanuvchining username linkdagi username parameter sifatida `elbek_uzdev` ni o'rniga yoziladi. Parol ham shunday password parameter bilan `1234` beriladi.
Va shunday javob paytadi, responsedagi `data` key hisoblanadi. Serverga murajaat qilayotganda har doim Header sifatida `key` kalit so'zi bilan responsedagi keyni qo'shib jo'natish talaba qilinadi.
```json
{
  "definition": "found",
  "statusCode": 110,
  "data": "ZWxiZWtfdXpkZXYmZWxiZWsyMDAzJjE2NjA5OTY2MzEyODc="
}
```

<h4>Register:</h4>
```http request
http://localhost:8080/register?firstname=Elbek&lastname=Nurmatov&username=elbek_nurmatov&password=12345
```
Registratsiya qilish uchun yuqoridagi kabi malumot jo'natish kerak bu holatda ham key qaytaradi. Natija quyidagi kabi qaytsa muvaffaqiyatli bo'ladi
```json
{
    "definition": "successfully, added",
    "statusCode": 100,
    "data": "YWJyb3JpeTcmMTIzNDUmMTY2MDk5NzIwNzM5NQ=="
}
```

<h4>Hamma postlarni olish:</h4>
```http request
http://localhost:8080/post
```
Hamma postlarni ko'rish uchun shu requestni yuborish kerak, header sifatida keyni ham jo'natish kerak keyni kalit sozi, ro'yhatdan o'tganda yoki tizimga kirganda keladi shu keyni `key` keywordi bilan jo'natish kerak
```json
{
  "definition": "found",
  "statusCode": 110,
  "data": [
    {
      "text": "zo'r",
      "user": {
        "firstname": "Elbek",
        "lastname": "Nurmatov",
        "username": "Elbek_Nurmatov",
        "id": 2
      },
      "id": 1,
      "created_time": "2022-08-19 04:41:24.48152+05"
    },
    {
      "text": "zamonasining eng zo'ri",
      "user": {
        "firstname": "Elbek",
        "lastname": "Nurmatov",
        "username": "Elbek_Nurmatov",
        "id": 2
      },
      "id": 2,
      "created_time": "2022-08-19 04:42:55.03179+05"
    }
  ]
}
```



<h4>Post qo'shish:</h4>
```http request
http://localhost:8080/post?text=shu yerga post matni yoziladi
```
Yangi post qoshish, shunday amalga oshiriladi, bunda ham headerda key borishi kerak. Natija shunday qaytishi kerak:
```json
{
  "definition": "successfully, added",
  "statusCode": 100
}
```
