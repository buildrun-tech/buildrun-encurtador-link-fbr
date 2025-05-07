# buildrun-encurtador-link-fbr


## Antes de rodar, gere as Chaves RSA publicas e privadas

Vá até app/src/main/resources e gere as chaves publicas e privadas

```
openssl genrsa > app.key 
openssl rsa -in app.key -pubout -out app.pub
```
