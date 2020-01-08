参考：https://blog.csdn.net/zhuyu19911016520/article/details/86557165

路由json如下

````json
{
    "filters": [
        {
            "name": "StripPrefix",
            "args": {
                "_genkey_0": "2"
            }
        }
    ],
    "id": "api-oss",
    "order": "1",
    "predicates": [
        {
            "args": {
                "pattern": "/api/oss/**"
            },
            "name": "Path"
        }
    ],
    "uri": "lb://mycloud-provider-oss"
}
````
官方默认提供了这些接口进行网关的管理，例如获取所有的路由：
GET http://localhost:8443/actuator/gateway/routes