# phizs-pay

集成支付支付，和微信支付。

支付宝

依赖包是支付宝官方demo的jar包。
下载地址：https://docs.open.alipay.com/270/106291/ 

微信支付 同理
微信官方demo的jar 包
下载地址：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=11_1

(例子中的依赖是我传到了我自己的nexus上，只需上传自己的，修改依赖即可)

开发过程中只需要把 resources中的spring-pay.xml引入到项目中即可。至于properties文件,是两种不同的配置和打包模式。自行决定就好。
