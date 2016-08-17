anything-mail -  Java Mail Builder
===========

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
