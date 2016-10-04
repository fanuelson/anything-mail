anything-mail -  Java Mail Builder
===========
by Fanuel

* Features:
  * Builder Pattern to create an email programmatically.
  * Java DSL.
  * Multiple file attachments.
  * Velocity API included


Example:
------
```java
AutenticacaoEmail authEmail = AutenticacaoEmail.configure("yourUsername", "yourPassword");

EmailConfiguration emailConfig = EmailConfiguration.configure(authEmail);

EmailService es = EmailService.create(emailConfig);

Email email = new EmailBuilder()
  	.from("yourEmail","yourPersonalName")
  	.to("emailTo")
  	.subject("subject")
  	.textBody("HELLO")
  	.addFile(new File("/path/to/file.txt"))
  	.build();

es.enviar(email);
```

By Default the project has a properties file called ***simpleEmail.properties*** under ***src/main/resources*** path with the following content:

#### simpleEmail.properties
```properties
mail.smtp.auth = true
mail.smtp.starttls.enable = true
mail.smtp.host = smtp.gmail.com
mail.smtp.port = 587
```

but if you prefer, you can create your own email properties file and pass it to ***EmailConfiguration*** like the following:

```java
AutenticacaoEmail authEmail = AutenticacaoEmail.configure("yourUsername", "yourPassword");

InputStream is = this.class.getClassLoader().getResourceAsStream(YOUR_EMAIL_PROPERTIES_FILE_NAME);
Properties properties = new Properties();
properties.load(is);

EmailConfiguration emailConfig = EmailConfiguration.configure(authEmail, properties);

EmailService es = EmailService.create(emailConfig);

Email email = new EmailBuilder()
  	.from("yourEmail","yourPersonalName")
  	.to("emailTo")
  	.subject("subject")
  	.textBody("HELLO")
  	.addFile(new File("/path/to/file.txt"))
  	.build();

es.enviar(email);
```

USING VELOCITY
===========

you can use velocity to generate the template as following:

 1 - Create the template file like the following :

***templateFile.vm***
```txt
Hello You

$someText

#foreach($book in $books)
   $foreach.count - $book 
#end


```

 2 - Create a model class with the parameters for the template:
```java
@VelocityTemplate(templateFileName="templateFile.vm")
public class ModelTemplate extends AbstractVelocityTemplate {

 	@VelocityParameter
 	private String someText = "HELLO WORLD" ;
 	
 	@VelocityParameter
 	private List<String> books = Arrays.asList("Book1", "Book2", "Book3") ;
 	
 	@Override
 	public String getTemplateDirectory() {
 		return "/path/to/template/";
 	}

}
```
NOTE: The API will search the absolute directory of the OS <br />
if your template is under /src/main/resources/ then return "/src/main/resources/" instead of return "/path/to/template"; <br />

After that you can generate the String that can be send in email
```java
VelocityGeneratorService vgs = new VelocityGeneratorService();
		
String emailHtmlBody = vgs.generateString(new ModelTemplate());

System.out.println(emailHtmlBody);
```

Full example with velocity

```java

   public static void main(String[] args) {
		String emailHtmlBody = createEmailBody();
		sendEmail(emailHtmlBody);
	}

	public static String createEmailBody() {
		VelocityGeneratorService vgs = new VelocityGeneratorService();
		String emailHtmlBody = vgs.generateString(new ModelTemplate());
		return emailHtmlBody;
	}
	
	public static void sendEmail(String body) {
		AutenticacaoEmail authEmail = AutenticacaoEmail.configure("yourUsername", "yourPassword");
		EmailConfiguration emailConfig = EmailConfiguration.configure(authEmail);
		EmailService es = EmailService.create(emailConfig);
		Email email = new EmailBuilder()
		  	.from("yourEmail","yourPersonalName")
		  	.to("emailTo")
		  	.subject("subject")
		  	.htmlBody(body)
		  	.addFile(new File("/path/to/file.txt"))
		  	.build();

		es.enviar(email);
	}
```


Instruction to add this project as maven dependency in your project:
------

add this repository in your pom.xml file

```xml

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

and add this dependency

```xml
	<dependency>
	    <groupId>com.github.fanuelson</groupId>
	    <artifactId>anything-mail</artifactId>
	    <version>LAST RELEASE</version>
	</dependency>
```
