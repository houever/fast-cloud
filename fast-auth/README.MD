# springcloud-oauth2

##### 生成秘钥

````shell
keytool -genkeypair -alias mycloud-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jtw,O=jtw,L=zurich,S=zurich,C=CH" -keypass 123456 -keystore mycloud-jwt.jks -storepass 123456
````

````shell
keytool -list -rfc --keystore mycloud-jwt.jks | openssl x509 -inform pem -pubkey


-DskipDockerBuild 跳过 build 镜像
-DskipDockerTag 跳过 tag 镜像
-DskipDockerPush 跳过 push 镜像
-DskipDocker 跳过整个阶段
````

