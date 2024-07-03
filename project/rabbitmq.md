# rabbitmq

在docker 上安装

执行以下命令

```dock
docker pull rabbitmq:3.13.3-management


```



```dock

docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -v /home/mirror/rabbitmq/data:/var/lib/rabbitmq    --hostname myRabbit -e RABBITMQ_DEFAULT_VHOST=my_vhost  -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin rabbitmq:3.13.3-management


```

/var/log/rabbitmq/rabbit@myRabbit.log
