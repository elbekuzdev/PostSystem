<h1>Post System</h1>

<p><h3>Dasturni ishlatish:</h3>
Dasturni ishlatish uchun birinchi <code>db_schema.sql</code> nomi database sxemasini birinchi o&#39;zingizni databaseingizga restore qiling database nomini iloji boricha <code>post</code> qiling, (<code>post</code> qilmasangiz dastuni ichidagi PostgressConnection classni ichidagi connectionni postni ozingizni database nomini qoying).  <code>/Post/src/main/java/org/example/dao/PostgresConnection.java</code> shu yerdagi jdbc dan ozingizni postgresql dagi username ingiz(iloji boricha <code>postgres</code> bo&#39;lsin ) va postgresql parolini qoying.</p>
<p><h4>Login:</h4></p>
<pre><code class="lang-http"><span class="hljs-symbol">http:</span><span class="hljs-comment">//localhost:8080/login?username=elbek_uzdev&amp;password=1234</span>
</code></pre>
<p>Login qilish uchun foydalanuvchining username linkdagi username parameter sifatida <code>elbek_uzdev</code> ni o&#39;rniga yoziladi. Parol ham shunday password parameter bilan <code>1234</code> beriladi.
Va shunday javob paytadi, responsedagi <code>data</code> key hisoblanadi. Serverga murajaat qilayotganda har doim Header sifatida <code>key</code> kalit so&#39;zi bilan responsedagi keyni qo&#39;shib jo&#39;natish talaba qilinadi.</p>
<pre><code>{
  <span class="hljs-attr">"definition"</span>: <span class="hljs-string">"found"</span>,
  <span class="hljs-attr">"statusCode"</span>: <span class="hljs-number">110</span>,
  <span class="hljs-attr">"data"</span>: <span class="hljs-string">"ZWxiZWtfdXpkZXYmZWxiZWsyMDAzJjE2NjA5OTY2MzEyODc="</span>
}
</code></pre><p><h4>Register:</h4></p>
<pre><code class="lang-http"><span class="hljs-symbol">http:</span><span class="hljs-comment">//localhost:8080/register?firstname=Elbek&amp;lastname=Nurmatov&amp;username=elbek_nurmatov&amp;password=12345</span>
</code></pre>
<p>Registratsiya qilish uchun yuqoridagi kabi malumot jo&#39;natish kerak bu holatda ham key qaytaradi. Natija quyidagi kabi qaytsa muvaffaqiyatli bo&#39;ladi</p>
<pre><code class="lang-json">{
    <span class="hljs-attr">"definition"</span>: <span class="hljs-string">"successfully, added"</span>,
    <span class="hljs-attr">"statusCode"</span>: <span class="hljs-number">100</span>,
    <span class="hljs-attr">"data"</span>: <span class="hljs-string">"YWJyb3JpeTcmMTIzNDUmMTY2MDk5NzIwNzM5NQ=="</span>
}
</code></pre>
<p><h4>Hamma postlarni olish:</h4></p>
<pre><code class="lang-http"><span class="hljs-symbol">http:</span><span class="hljs-comment">//localhost:8080/post</span>
</code></pre>
<p>Hamma postlarni ko&#39;rish uchun shu requestni yuborish kerak, header sifatida keyni ham jo&#39;natish kerak keyni kalit sozi, ro&#39;yhatdan o&#39;tganda yoki tizimga kirganda keladi shu keyni <code>key</code> keywordi bilan jo&#39;natish kerak</p>
<pre><code class="lang-json">{
  <span class="hljs-attr">"definition"</span>: <span class="hljs-string">"found"</span>,
  <span class="hljs-attr">"statusCode"</span>: <span class="hljs-number">110</span>,
  <span class="hljs-attr">"data"</span>: [
    {
      <span class="hljs-attr">"text"</span>: <span class="hljs-string">"zo'r"</span>,
      <span class="hljs-attr">"user"</span>: {
        <span class="hljs-attr">"firstname"</span>: <span class="hljs-string">"Elbek"</span>,
        <span class="hljs-attr">"lastname"</span>: <span class="hljs-string">"Nurmatov"</span>,
        <span class="hljs-attr">"username"</span>: <span class="hljs-string">"Elbek_Nurmatov"</span>,
        <span class="hljs-attr">"id"</span>: <span class="hljs-number">2</span>
      },
      <span class="hljs-attr">"id"</span>: <span class="hljs-number">1</span>,
      <span class="hljs-attr">"created_time"</span>: <span class="hljs-string">"2022-08-19 04:41:24.48152+05"</span>
    },
    {
      <span class="hljs-attr">"text"</span>: <span class="hljs-string">"zamonasining eng zo'ri"</span>,
      <span class="hljs-attr">"user"</span>: {
        <span class="hljs-attr">"firstname"</span>: <span class="hljs-string">"Elbek"</span>,
        <span class="hljs-attr">"lastname"</span>: <span class="hljs-string">"Nurmatov"</span>,
        <span class="hljs-attr">"username"</span>: <span class="hljs-string">"Elbek_Nurmatov"</span>,
        <span class="hljs-attr">"id"</span>: <span class="hljs-number">2</span>
      },
      <span class="hljs-attr">"id"</span>: <span class="hljs-number">2</span>,
      <span class="hljs-attr">"created_time"</span>: <span class="hljs-string">"2022-08-19 04:42:55.03179+05"</span>
    }
  ]
}
</code></pre>
<p><h4>Post qo&#39;shish:</h4></p>
<pre><code class="lang-http"><span class="hljs-keyword">http</span>://localhost:<span class="hljs-number">8080</span>/<span class="hljs-built_in">post</span>?<span class="hljs-keyword">text</span>=shu yerga <span class="hljs-built_in">post</span> matni yoziladi
</code></pre>
<p>Yangi post qoshish, shunday amalga oshiriladi, bunda ham headerda key borishi kerak. Natija shunday qaytishi kerak:</p>
<pre><code class="lang-json">{
  <span class="hljs-attr">"definition"</span>: <span class="hljs-string">"successfully, added"</span>,
  <span class="hljs-attr">"statusCode"</span>: <span class="hljs-number">100</span>
}
</code></pre>
