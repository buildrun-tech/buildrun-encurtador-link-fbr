# buildrun-encurtador-link-fbr


## Antes de rodar, gere as Chaves RSA publicas e privadas

Vá até app/src/main/resources e gere as chaves publicas e privadas

```
openssl genrsa > app.key 
openssl rsa -in app.key -pubout -out app.pub
```



http POST :3000/links \
Authorization:"Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJsaW5rLXNob3J0ZW5lciIsInN1YiI6ImNjNDMyNTIzLTA3YmYtNDI0Yi05OGE0LWNhMzk3MTQyNzdkMSIsImV4cCI6MTc0ODMwNjAzNiwiZW1haWwiOiJqb2FvMkB0ZXN0ZS5jb20ifQ.NBMwwoHmyqzRkvMAjwdxyW1sObM13nP62aP91iJtXxITFFvysjs1Vv2oYYFy4sTJ9PzHkCJpe5ZFwplM3t_vAnl2EoB82ZvJY49spwSZq3Y9Q-3iTDYk7pV5YyybBPws0D5FK-CJA13IYT49Np3C6Ppy2cd9XMB9HGkUhz61Q1TFHIkOY4e0fBzF3von6ae0lLjVigqJT4d9vl0M1d4zIhsS4o6HL5WMfLZk7iiX4eqA9MW9D-g6bJ0l9ZephXzfFVeMGVROGNueX_DYmQEgJbZ3db75kbLIS_n_zV3fe14HVJ0ruhTOJG-rbTdnnGvdkux3qvddFww8CoAayEuC0A" \
Content-Type:application/json \
<<< '{ "uniqueLinkSlug": "", "originalUrl": "", "utm": { "source": "teste", "medium": "youtube", "campaign": "fbr" }, "expirationDateTime": "2025-05-25T20:55:00" }'
