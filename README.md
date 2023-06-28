# Apa itu servlete ?
Servlet merupakan standarisasi java web, seperti JDBC sebagai standarisasi koneksi java ke database. Dengan menggunakan servlet kita bisa menjalankan applikasi java web di web server manapun yang sudah mengikuti standarisasi Servlet.  
Refrence : https://www.jcp.org/en/jsr/detail?id=304  

# Servlet API
Servlet merupakan standarisasi java yang low level, maksud nya saat kita menggunakan Servlet itu banyak sekali yang kita harus siapkan untuk menjalankan servlet. Maka dari itu untuk sekarang ini jarang sekali developer yang membangun applikasi java web yang langusung menggunakan servlet, kebanyakan mereka para developer maupun enginner menggunakan web framework seperti Spring framework.

# Java Web Server
Servlet merupakan spesifikasi atau standarisasi pada java, maka jikalau kita ingin menggunakanya maka kita membutuhkan implementasi nya. Implementasi Servlet itu dalam bentuk web server, ada banyak sekali web server yang telah mengimplementasi servlet, diantaranya yaitu :
* Apache Tomcat
* Jetty
* Glassfish
* Wildfly
* Dan masih banyak lagi

# Apache Tomcat
Apache Tomcat merupakan Web server yang mengimplenetasi standarisasi Servlet dan juga Apache Tomcat ini merupakan web server yang sangat populer untuk developer Java.
Refrence : https://tomcat.apache.org/

# Kelbihan Apache Tomcat
Apache Tomcat merupakan applikasi web server yang opensource dan Multi Platform, sehingga kita bisa menjalankan secara gratis di OS manapun. Selain itu Apache Tomcat sangat ringan dan tidak membutuhkan resource hardware yaang besar untuk menjalankanya.

# Instalasi Tomcat
Untuk menginstall nya sebenarnya ada beberapa cara, namun kali ini saya akan menggunakan file archive.  
Untuk instalasinya kita bisa download terlebih dahulu apache tomcat nya :
https://tomcat.apache.org/download-10.cgi  
Setelah selesai download maka kita Unzip terlebih dahulu dengan printah :
``` sh
unzip apache-tomcat-10.1.10 
```
setelah itu kita untuk pengguna mac atau linux, perlu mengubah permision unutk file catalina.sh agar bisa di eksekuis dengan perintah
``` sh
chmod +x ./bin/catalina.sh
```
untuk menjalankan Apache tomcat nya, jalankan command berikut ini :
``` sh
./bin/catalina.sh run
```
setelah itu untuk mengecek apakah Apache Tomcat telah berjalan, kita bisa mengecek nya pada web browser dengan url :  
http://localhost:8080

# WAR file
Saat kita membuat applikasi java, kita akan menggunakan jar file(Java Archive) file, tapi kusus applikasi Java Web based kita akan membuat distribusi file dalam bentuk WAR (Web Archive) file.

# Perbedaan Jar dan War file.
War file dan Jar file sebenarnya sama, berisikan class - class java yang sudah di compile menjadi binary code. Perbedaan Jar dan War file adalah War file tidak memiliki main class sedangkan Jar file membutuhkan Main class. War file tidak membutuhkan Main class karena War file akan dijalankan oleh Web Server.

# Membuat Project
Untuk membuat projectnya kita bisa kunjungin :
* https://https://start.spring.io/
* tambahakan dependency Lombok dan spring web
* Setelah itu, hapus semua file class generated yang ada.

# Servlet
Servlet merupakan Interface utama dari Servlet API dan juga tempat dimana logika kode program kita di tempatkan.  
HTTP Request yang masuk ke Java Web akan diterima oleh Servlet dan Http Response nya akan dibut di Servlet.
Rfrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/Servlet.html

# Http Servlet
Kita tidak perlu meng implementasi Servlet secara manual, kita dapat mengimplementasikanya dengan membuat class turunanya dari Servlet.  
salahsatu class turunan servlet yang bisa kita gunakan adalah HttpServlet.  
Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/http/HttpServlet.html

# WebServlet Annotation
Setelah kita mebuat class turunan dari Servlet kita perlu meregistrasikan class tersebut ke Java Web agar bisa di akses, untuk meregistrasi kanya kita bisa menambahkan annotation @WebServlet tersebut.  
Contoh :  
``` java
@WebServlet(urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello Wolrd");
    }    
}

```
Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/annotation/WebServlet.html


# Membuat War file
Sebelum membuat War file nya, kita perlau menghapus Unit testnya dan meghapus springbot plugin nya :
``` xml
<!-- hapus plugin ini di file pom.xml -->
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
			<configuration>
				<excludes>
					<exclude>
						<groupId>org.projectlombok</groupId>
						<artifactId>lombok</artifactId>
					</exclude>
				</excludes>
			</configuration>
		</plugin>
	</plugins>
</build>
```
untuk membuat war file kita bisa menggunakan perintah
``` sh
mvn package
```
Hasil dari War file dari project yang kita buat berapa pada file target

# Deploy War file ke Tomcat
Untuk deploy war file ke Apache tomcat, kita harus memindahkan atau meng copy file war yang telah kita package(ada di folder target) lalu pindahkan war file tersebut ke dalam file webapps yang ada di Apache Tomcat.

# Undeploy
Untuk Undeploy War file nya, kita bisa lakukan dengan cara menghapus war file dan file extrac nya yang bernama sama dengan file warnya pada folder webapps.

# Embeded Apache Tomcat
Proses deployment yang sebelumnya kita lakukan itu sebenarnya tidak lah evektif, karena setiap kita ingin melihat perubahan dalam kode kita, kit harus mempackage dan memindahkan war file tersebut ke folder webapps di Apache Tomcat. Oleh karna itu dalam proses development kita dapat menggunakan Embeded Tomcat yang terdapat pada Spring Framework.
Untuk melakukan hal tersesebu, berikut ini langkah-langkahnya :
* Membuat Main class
* Tabahkan annotasi @SpringBootApplication dan @ServletComponentScan
* Dan pada main method kita jalankan class yang baru kita buat tersebut dengan menggunakan class SringBootApplication.run();
Contoh : 
``` java
/**
 * Digunakan untuk meng Secan semua class Servlet yang kita buat
 */
@ServletComponentScan
/*
 * Untuk menjalankan Embeded Tomcat
 */
@SpringBootApplication
public class BelajarServletApp {
    public static void main(String... args) {
        SpringApplication.run(BelajarServletApp.class, args);
    }
}
```

# Singleton
Saat ktia menajlankan Java Web, Object Servlet hanya akan dibuat 1x(Singleton), maka dari itu HTTP Request yang masuk akan selalu menggunakan Object Servlet yang sama. Hal tersebut perlu di perhatikan karena jika kode yang kita buat tidak thread safe, maka data dari http request tidak konsisten dan rentan tertukar dengan request lainya.  
Maka dari itu kode yang kita buat harus Thread safe.
contoh thread unsafe :
``` java
@Slf4j
@WebServlet(urlPatterns = {"/unsafe"})
public class ServletThreadUnsafe extends HttpServlet {
    
    private String restpose = "";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.restpose = "Hello "+req.getParameter("name");
        try {
            Thread.sleep(Long.parseLong(req.getParameter("sleep")));
        } catch (InterruptedException ITX) {
            log.error(ITX.getMessage());
        }
        resp.getWriter().println(this.restpose);
    }
}
```
contoh thread safe :
``` java
@Slf4j
@WebServlet(urlPatterns = {"/safe"})
public class ServletThreadSafe extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respose = new String().concat("Hello "+req.getParameter("name"));
        try {
            Thread.sleep(Long.parseLong(req.getParameter("sleep")));
        } catch (InterruptedException ITX) {
            log.error(ITX.getMessage());
        }
        resp.getWriter().println(respose);
    }
}
```

# Http Mehod
Http method seperti POST, GET,PUT, DELETE, PATCH dan lain-lain akan di handle oleh Servlet.  
Saat kita menggunakan HttpServlet pada method service(HttpServerRequest, HttpServerResponse) sudah ada logic untuk menghandle tiap-tiap Http method.  
Namun jika kita ingin membuat logic untuk jenis Http mehtod tertentu, kita bisa langsung meng overide Http method dengan menggunakan mehtod dengan prefix do dan di akhiri dengan nama Http methodnya misalnya doGet(), doPost(), doDelete(), doPut() dan lain-lain.
Contoh :
``` java
@WebServlet(urlPatterns = {"/todo"})
public class HttpMethodServlet extends HttpServlet {
    
    private List<String> datas = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(this.datas);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("todo");
        if (data != null) {
            this.datas.add(data);
            resp.getWriter().println("Success Add "+data);
        } else {
            resp.getWriter().println("parameter todo must exist!");
        }
    }
}
```
Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/http/HttpServlet.html

# Http Request
Semua Requst yang masuk pada Servlet akan disimpan pada Object ServletRequst, Namun saat kita menggunakan HttpServlet object tersebut akan dikonversi menjadi HttpServletRequst.  
Kita bisa mendapatkan banyak informasi dari HttpServletRequest misalnya seperti Header, Parameter, Body, Cookie, Session Dan lain-lain.
Contoh :
``` java
@WebServlet(urlPatterns = {"/request"})
public class RequestServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Method : "+req.getMethod());
        resp.getWriter().println("Query String : "+req.getQueryString());
        resp.getWriter().println("Request URI : "+req.getRequestURI());
        resp.getWriter().println("Context path : "+req.getContextPath());
        resp.getWriter().println("Servlet Path : "+req.getServletPath());
    }
}
```
Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/http/HttpServletRequest.html

# Http Response
Pada parameter method service terdapat 2 parameter yaitu :
* HttpServletRequest, ini merupakan representasi dari Http request
* HttpServletResponse, ini merupakan representasi dari Http response
``` java
@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { }
```
# Response body
Untuk mengirim response untuk body pada HttpServleteResponse, kita dapat memanfaatkan PrintWriter method getWriter()  
method tersebut digunakan untuk menulis data apapun kedalam response body pada HTTP Response.
Contoh :
``` java
@WebServlet(urlPatterns = {"/body"})
public class ResponseBodyServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = """
                <body>
                    <html>
                        <h1>
                            Hello Wolrd !
                        </h1>
                    </html>
                </body>
                """;
        resp.getWriter().println(html);
    }
}
```

# Respose Header
HttpServletResponse juga memiliki beberapa method yang bisa kita gunakan untuk megubah Respose Header.  
Berikut ini merupakan method-method yang bisa kita gunakan :
* setHeader(name, value) => untuk mengubah header
* setIntHeader(name, int) => mengubah header dengan nilai
* setDateHeader(name, long) => mengubah header dengan nilai date (milis)

Contoh :
``` java
@WebServlet(urlPatterns = {"/json"})
public class ResponseHeaderServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataJson = """
                {
                    "name" : "Alliano",
                    "email" : "allianoanoanymous@gmail.com",
                    "address" : {
                        "country" : "Indonesia", 
                        "stret" : "Mawar", 
                        "province" : "Bandung"
                    }
                }
                """;
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(dataJson);
    }
}
```

# Response Status
untuk mengubah Http Response Status menggunakan HttpServletResponse, untuk melakukanya kita bisa menggunakan method setStatus(name, code).
contoh :
``` java
@WebServlet(urlPatterns = {"/not-found"})
public class ReqNotFoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(404);
        resp.getWriter().println("<h1>NOT FOUND</h1>");
    }
}
```

# Request param
Untuk mengambil data dari parameter yang dikirimkan kita bisa menggunakan HttpServletRequest, Berikut ini beberapa method yang dapat kita gunakan :
| mehod						|	deskripsi										|
|---------------------------|---------------------------------------------------|
| getParameter(name)		| mengambil parameter berdasarkan nama parameter	|
| getParameterNames()		| mengambil semua parameter yang ada				|
| getParameterMap()			| mengambil semua parameter beserta value nya		|
| getParameterValue(name)	| mengambil parameter yang value nya lebih dari 1	|

Contoh :
``` java
@WebServlet(urlPatterns = {"/query"})
public class QueryServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] numbers = req.getParameterValues("number");
        resp.getWriter().println("Total Number is "+Stream.of(numbers).mapToInt(Integer::parseInt).sum());
    }
}
```
# Request Header
Untuk mengambil Request Header yang dikirimkan, kita dapat megambilnya dari Object HttpServletRequest, ada beberapa method yang dapat kita manfaatkan, diantaranya yaitu :
| method                | deskripsi                                        |
|-----------------------|--------------------------------------------------|
| getHeader(name)       | mengambil value header berdasarkan nama          |
| getHeaderValues(name) | mengambil value header lebih dari 1              |
| getHeaderNames()      | mengambil semua nama header                      |
| getDateHeader(name)   | mengambil yang memiliki nilai Date (timemilis)   |
| getIntHeader(name)    | mengambil header yang memiliki value berupa Int  |

Header name itu tidak case sensitiv, misalnya content-type dengan Content-Type itu sama.
Contoh :
``` java

``` 
 
 # Form Request
 Kita juga bisa menggunakan method getParameter() untuk mendapatkan informasi data yang dikirimkan pada form request.  
 langkah pertama kita siapkan dulu halaman html pada folder resources/html
``` html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form page</title>
    <link rel="stylesheet" href="../css/form.css">
</head>
<body>
    <form action="/form" method="post">
        <div class="form-input">
            <div id="input-first-name">
                <input type="text" placeholder="first name" id="first-name" name="first-name">
            </div>
            <div class="input-last-name">
                <input type="text" name="last-name" id="lastname" placeholder="last name">
            </div>
            <button type="submit">Send</button>
        </div>
    </form>
</body>
</html>
```
setelah itu buat class seperti dibawah ini
``` java
@WebServlet(urlPatterns = {"/form"})
public class FormHtmlServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of(FormHtmlServlet.class.getResource("/html/form.html").getPath());
        String html = Files.readString(path);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        resp.getWriter().println("Hello "+firstName+" "+lastName);
    }
}
```
# Request Body
Untuk membaca request body kita dapat menggunakan mehhod di HttpServletRequest dengan method berikut ini :
| method              | deskripsi                                         |
|---------------------|---------------------------------------------------|
| getInputStream()    | mengambil request body dalam bentuk input Stream  |
| getBufferedReader() | mengambil request body denganbentuk buffer reader |

contoh :
class JsonUtil
``` java
public class JsonUtil {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
```
class Hello
``` java
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Hello {
    
    private String fistName;

    private String lastName;
}
```
``` java
@WebServlet(urlPatterns = {"/api/hello"})
public class ApiServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = JsonUtil.getObjectMapper().readValue(req.getInputStream(), Hello.class);
        String sayHello = "Hello ".concat(hello.getFistName()+" "+hello.getLastName());
        Map<String, String> response = Map.of("data", sayHello);

        String jsonResponse = JsonUtil.getObjectMapper().writeValueAsString(response);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(jsonResponse);
    }
}
```

# Upload File
Servlet juga mendukung penaganan Upload file dengan menggunakan HttpServletRequest dengan nama method :
| method        | deskripsi                             |
|---------------|---------------------------------------|
| getPart(name) | mengambil file berdasarkan nama       |
| getParts()    | mengambil semua file yang di upload   |

File upload di representasikan dengan interface Part.  
Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/http/Part.html  
Saat kita membuat class turunan Servlet yang mana class tersebut akan menerima upload file, maka class turunan tersebut harus kita annotasi dengan @MultipartConfig sebagai penanda bahwa class turunan Servlet yang kita buat tersebut boleh menerima File.  
Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/annotation/MultipartConfig.html

contoh :
``` html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>form-upload</title>
</head>
<body>
    <div class="input-file">
        <form action="/upload" method="post" enctype="multipart/form-data">
            <div class="inputFile">
                <p>upload file</p>
                <input type="file" name="file" id="file">
                <button type="submit">kirim</button>
            </div>
        </form>
    </div>
</body>
</html>
```

``` java
@WebServlet(urlPatterns = {"/upload"}) @MultipartConfig
public class FormUploadFileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of(FormHtmlServlet.class.getResource("/html/form-upload.html").getPath());
        String html = Files.readString(path);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           Part file = req.getPart("file");
           Path fileLocation = Path.of("upload/"+ UUID.randomUUID().toString()+file.getSubmittedFileName());
           Files.copy(file.getInputStream(), fileLocation);
           resp.getWriter().println(fileLocation.toString());
        } catch (IOException IOX) {
            resp.getWriter().println(IOX.getMessage());

       }
    }
}
```

# Download File
Untuk melakukan download file pada Servlet, kita bisa memanfaatkan method getOutputStream yang ada pada HttpServletRequest.  

contoh :
``` java
@WebServlet(urlPatterns = {"/download"})
public class DownloadFileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("upload/".concat(req.getParameter("file")));
        byte[] fileByts = Files.readAllBytes(path);

        String force = req.getParameter("force");
        if ("true".equals(force)) {
            // jika kita ingin langsung download dan tidak mau melihat filenya pada browser, kita bisa set headernya seperti ini
            resp.setHeader("Content-Disposition", "Attachment; FileName=\"" + path.getFileName() + "\"");
        }
        // getOutputStream() digunakan untuk menampilkan file yang didownload di browser
        resp.getOutputStream().write(fileByts);
    }
}
```

# Cokie
Cokie adalah informasi yang dibuat di sisi server dan disimpan pada client(browser).  
Cokie akan selalu dikirim ulang oleh browser setiap melakukan request.  
Untuk membuat cookie pada servlet kita bisa menggunakan method addCookie(cookie) milik interface HttpServletResponse.  
Dan untuk mendapatkan cokie yang dikirimkan oleh browser kita bisa menggunakan method getCookies() milik interface HttpServletRequest.  
Contoh :
``` java
@WebServlet(urlPatterns = {"/cookie"})
public class CookieServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cookieName = req.getParameter("name");
        String cookieValue = req.getParameter("value");
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        resp.addCookie(cookie);
        resp.getWriter().println("Success create cookie "+cookieName+" : "+cookieValue);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                resp.getWriter().println(cookie.getName()+ " : "+cookie.getValue());
            }
        }
    }
}
```
Untuk menghapus cookie pada sisi server kita bisa menggunakan method setMaxAge(-1)  

Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/http/Cookie.html

# Redirect
Unutk melakukan redirect pada umumnya kita cukup megset status code dan header nya ke page yang di tuju.  
Namun unutk melakukan redirect pada servlet kita bisa menggunakan method sendRedirect(page) milik HttpServltResponse.  
contoh : 
``` java
@WebServlet
public class RedirectServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // untuk status code nya otomatis di seting oleh servlet
        resp.sendRedirect("/form");    
    }
}
```

# Session
Session adalah informasi yang disimpan disisi server untuk digunakan kembali oleh client.  
misalnya ketika user melakukan login, dan kita perlu menyimpan informasi user yang telah login tersebut agar request yang dilakukan selanjutnya tidak memerlukan autentikasi.  
Data session disimpan pada memory Java Web, maka dari itu kadang ketika kita mambuat web yang berjalan di beberapa server, hal ini lumayan menyulitkan unutk melakukan management session nya. oelh karna itu membuat session sangat direkomendasikan menggunakan Cookie atau disimpan di database.

# Http Session
Representasi session pada servlet adalah class HttpSession.  
Untuk membuat session kita bisa menggunakan HttpServletRequest.getSession(isAutoCreate), paremater nya berupa boolean, jika true maka session akan dibuatkan secara otomatis.  

Refrence : https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/http/HttpSession.html

# HttpSession Methods
Penggunaan Httpsession mirip seperti penggunakan HashMap, yang mana kita bisa menyimpan key dan valeu, berikut ini adalah method-method yang dapat kita gunakan :
| method                    | deskripsi                          |
|---------------------------|------------------------------------|
| setAttribute(key, value)  | menyimpan session dengan key value |
| getAttribute(name)        | unutk mengambil data session       |
| removeAttribute(name)     | untuk menghapus session            |
| invalidate()              | untuk menghapus semua sessio       |

conotoh :
``` java
@WebServlet(urlPatterns = {"/session/login"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        HttpSession session = req.getSession(true);
        session.setAttribute("username", username);
        resp.getWriter().print("Login success! ".concat(username));
    }
}
```
melihat session nya
``` java
@WebServlet(urlPatterns = {"/session/get"})
public class SessionServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String username = (String)session.getAttribute("username");
        if(username == null){
            resp.getWriter().println("You are not login");
        }
        else {
            resp.getWriter().println("You has login ".concat(username));
        }
    }
}
```
menghapus semua session
``` java
@WebServlet(urlPatterns = {"/session/logout"})
public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);    
        session.invalidate();
        resp.getWriter().print("You has Logout!");
    }
}
```

# Filter
Filter merupakan fitur yang dimiliki servlet yang digunakan sebagai komponen yang bisa kita tempatkan sebelum request diterima oleh Servlet.  
Pada bahasa pemograman lain biasanya disebut Middleware.  
Kita bisa merigistrasikan URL tertentu untuk di filter. Secara otomatis setiap melakukan request Filter akan dijalankan dan kita bisa menetukan apakah request diizinkan atau akan kita tolak.  
Filter juga bisa berlapis, artinya kita bisa membuat lebih dari satu filter Untuk URL tertentu.
![interceptor](https://github.com/alliano/Java-Servlet/blob/master/servlet/img/interceptor.jpg)

# HttpFilter
Filter direperesentasikan interface Filter. Filter juga memiliki implementasi Class nya yaitu HttpFileter.
Untuk membuat filter kita harus mengannotasi class fileter kita dengan annotasi @WebFilter.  
contoh :
``` java
@WebFilter(urlPatterns = {"/*"}) @Slf4j
public class UrlFilter extends HttpFilter {
    
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Request recive from URL ".concat(request.getRequestURI()));
        // chain ini digunakan jikalau kita ingin melanjutkan request
        // jikalau tidak inigin melanjutkan request maka tidak perlu di chain.dofilter
        chain.doFilter(request, response);
    }
}
```
contoh lagi :
``` java
@WebFilter(urlPatterns = {"/*"})
public class LoginFilter extends HttpFilter {
    
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request.getRequestURI().equals("/session/login")){
            chain.doFilter(request, response);
        }
        else {
            HttpSession session = request.getSession(true);
            String username = (String) session.getAttribute("username");
            if(username == null) {
                response.setStatus(401);
                response.getWriter().println("You should login!");
            }
            else {
                
                chain.doFilter(request, response);
            }
        }
    }
}
```
Refrence :  
* https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/Filter.html
* https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/http/HttpFilter.html
* https://tomcat.apache.org/tomcat-10.0-doc/servletapi/jakarta/servlet/annotation/WebFilter.html