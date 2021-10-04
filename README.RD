Pré-requisitos
1- Docker
2- Docker swarm cluster
3- Java11
4- Maven/Gradle

Build e deploy
1- Para build das imagens executar bash run-build-all-images.sh
2- Para deploy do ambiente executar bash run-deploy.sh
3- (opcional) Acesssar o control center 'http://127.0.0.1:9021', e criar os tópicos 'order' e 'order-persistence'
4- Acessar 'http://127.0.0.1:8082/swagger-ui.html' para recuperar um token para cliente client-id-1'
5- Acessar 'http://127.0.0.1:8080/swagger-ui.html' para gerar eventos.
6- Para acessar o dashboard Kibana 'http://127.0.0.1:5601'
7- Para escalar os serviços alterar a configuração deploy.replicas ou executar o comando 
'docker service scale nome-service=1'


