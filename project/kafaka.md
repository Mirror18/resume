````
### download images
```bash
docker pull wurstmeister/zookeeper
docker pull wurstmeister/kafka
docker pull sheepkiller/kafka-manager
```



### run zookepper
```bash
docker run -d --name zookeeper --publish 2181:2181 \
  --volume /etc/localtime:/etc/localtime \
  --restart=always \
  wurstmeister/zookeeper
```


### run kafka

```bash
docker run --name kafka \
  -p 9092:9092 \
  --link zookeeper:zookeeper \
  -e KAFKA_ADVERTISED_HOST_NAME=192.168.1.8 \
  -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
  -d  wurstmeister/kafka  
```

### run kafka manager
```bash
docker run -d \
  --link zookeeper:zookeeper \
  -p 9000:9000  \
  -e ZK_HOSTS="zookeeper:2181" \
  hlebalbau/kafka-manager:stable \
  -Dpidfile.path=/dev/null
```
如果想设置webui 的权限，可以这样设置
```bash
KAFKA_MANAGER_AUTH_ENABLED: "true"
KAFKA_MANAGER_USERNAME: username
KAFKA_MANAGER_PASSWORD: password
```


web ui in `localhost:9000`
````