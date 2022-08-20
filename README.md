<h1>Post System</h1>

<p><h3>Dasturni ishlatish:</h3>
Dasturni ishlatish uchun birinchi <code>db_schema.sql</code> nomi database sxemasini birinchi o&#39;zingizni databaseingizga restore qiling database nomini iloji boricha <code>post</code> qiling, (<code>post</code> qilmasangiz dastuni ichidagi PostgressConnection classni ichidagi connectionni postni ozingizni database nomini qoying).  <code>/Post/src/main/java/org/example/dao/PostgresConnection.java</code> shu yerdagi jdbc dan ozingizni postgresql dagi username ingiz(iloji boricha <code>postgres</code> bo&#39;lsin ) va postgresql parolini qoying.</p>
<p><h4>Login:</h4>
```http request
<a href="http://localhost:8080/login?username=elbek_uzdev&amp;password=1234">http://localhost:8080/login?username=elbek_uzdev&amp;password=1234</a></p>
<pre><code>Login qilish uchun foydalanuvchining username linkdagi username parameter sifatida `elbek_uzdev` ni o'rniga yoziladi. Parol ham shunday password parameter bilan `1234` beriladi.
Va shunday javob paytadi, responsedagi `data` key hisoblanadi. Serverga murajaat qilayotganda har doim Header sifatida `key` kalit so'zi bilan responsedagi keyni qo'shib jo'natish talaba qilinadi.
```json
{
  <span class="hljs-string">"definition"</span>: <span class="hljs-string">"found"</span>,
  <span class="hljs-string">"statusCode"</span>: <span class="hljs-number">110</span>,
  <span class="hljs-string">"data"</span>: <span class="hljs-string">"ZWxiZWtfdXpkZXYmZWxiZWsyMDAzJjE2NjA5OTY2MzEyODc="</span>
}
</code></pre><p><h4>Register:</h4>
```http request
<a href="http://localhost:8080/register?firstname=Elbek&amp;lastname=Nurmatov&amp;username=elbek_nurmatov&amp;password=12345">http://localhost:8080/register?firstname=Elbek&amp;lastname=Nurmatov&amp;username=elbek_nurmatov&amp;password=12345</a></p>
<pre><code>Registratsiya qilish uchun yuqoridagi kabi malumot jo<span class="hljs-string">'natish kerak bu holatda ham key qaytaradi. Natija quyidagi kabi qaytsa muvaffaqiyatli bo'</span>ladi
```json
{
    <span class="hljs-string">"definition"</span>: <span class="hljs-string">"successfully, added"</span>,
    <span class="hljs-string">"statusCode"</span>: <span class="hljs-number">100</span>,
    <span class="hljs-string">"data"</span>: <span class="hljs-string">"YWJyb3JpeTcmMTIzNDUmMTY2MDk5NzIwNzM5NQ=="</span>
}
</code></pre><p><h4>Hamma postlarni olish:</h4>
```http request
<a href="http://localhost:8080/post">http://localhost:8080/post</a></p>
<pre><code>Hamma postlarni ko<span class="hljs-symbol">'rish</span> uchun shu requestni yuborish kerak, header sifatida keyni ham jo<span class="hljs-symbol">'natish</span> kerak keyni kalit sozi, ro<span class="hljs-symbol">'yhatdan</span> o<span class="hljs-symbol">'tganda</span> yoki tizimga kirganda keladi shu keyni `key` keywordi bilan jo<span class="hljs-symbol">'natish</span> kerak
```json
{
  <span class="hljs-string">"definition"</span>: <span class="hljs-string">"found"</span>,
  <span class="hljs-string">"statusCode"</span>: <span class="hljs-number">110</span>,
  <span class="hljs-string">"data"</span>: [
    {
      <span class="hljs-string">"text"</span>: <span class="hljs-string">"zo'r"</span>,
      <span class="hljs-string">"user"</span>: {
        <span class="hljs-string">"firstname"</span>: <span class="hljs-string">"Elbek"</span>,
        <span class="hljs-string">"lastname"</span>: <span class="hljs-string">"Nurmatov"</span>,
        <span class="hljs-string">"username"</span>: <span class="hljs-string">"Elbek_Nurmatov"</span>,
        <span class="hljs-string">"id"</span>: <span class="hljs-number">2</span>
      },
      <span class="hljs-string">"id"</span>: <span class="hljs-number">1</span>,
      <span class="hljs-string">"created_time"</span>: <span class="hljs-string">"2022-08-19 04:41:24.48152+05"</span>
    },
    {
      <span class="hljs-string">"text"</span>: <span class="hljs-string">"zamonasining eng zo'ri"</span>,
      <span class="hljs-string">"user"</span>: {
        <span class="hljs-string">"firstname"</span>: <span class="hljs-string">"Elbek"</span>,
        <span class="hljs-string">"lastname"</span>: <span class="hljs-string">"Nurmatov"</span>,
        <span class="hljs-string">"username"</span>: <span class="hljs-string">"Elbek_Nurmatov"</span>,
        <span class="hljs-string">"id"</span>: <span class="hljs-number">2</span>
      },
      <span class="hljs-string">"id"</span>: <span class="hljs-number">2</span>,
      <span class="hljs-string">"created_time"</span>: <span class="hljs-string">"2022-08-19 04:42:55.03179+05"</span>
    }
  ]
}
</code></pre><p><h4>Post qo&#39;shish:</h4>
```http request
<a href="http://localhost:8080/post?text=shu">http://localhost:8080/post?text=shu</a> yerga post matni yoziladi</p>
<pre><code>Yangi post qoshish, <span class="hljs-keyword">shunday </span>amalga oshiriladi, <span class="hljs-keyword">bunda </span>ham headerda key <span class="hljs-keyword">borishi </span>kerak. Natija <span class="hljs-keyword">shunday </span>qaytishi kerak:
```<span class="hljs-keyword">json
</span>{
  <span class="hljs-string">"definition"</span>: <span class="hljs-string">"successfully, added"</span>,
  <span class="hljs-string">"statusCode"</span>: <span class="hljs-number">100</span>
}
</code></pre>
