anything-mail -  Java Mail Builder
===========


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

Email email = new TextEmailBuilder()
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

Email email = new TextEmailBuilder()
  	.from("yourEmail","yourPersonalName")
  	.to("emailTo")
  	.subject("subject")
  	.textBody("HELLO")
  	.addFile(new File("/path/to/file.txt"))
  	.build();

es.enviar(email);
```
