# MQL自动化

------

该项目基于python3.X和chrome（32位）环境且已安装selenium。

## 主要功能

> * 自动创建数据集(taxi和ixat)
> * 数据接入
> * 新建报表测试已整理的MQL语句并保存

------
## 项目结构
> * 在AUS目录下有四个python脚本和一个file文件夹和数据插入的sh脚本
> * file文件夹下有chromedriver.exe、mql整理测试taxi.txt

------

## 自动创建taxi和ixat数据集
> * 将file文件夹下的chromedriver放在chrome安装目录下
> * 并在createdataset.py内修改chromedriver路径和AUS访问的URL，改成与自己环境对应
> * 保存后在windows下的cmd执行命令

修改内容例子：
```
chromedriver = "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe"

URL="http://172.16.0.92:8080"
```

执行命令例子：
```
python F:\sugon\20180330\AutoTestforAUS\AUS\createdataset.py
```

## 后台接入数据
接入数据需要在**linux**下执行相应的命令，相关命令已经整合在insertdataset.sh文件里
### 1、执行脚本
>* 只需将insertdataset.sh放到服务器下
>* 进入到文件目录下执行脚本即可自动插入数据

执行命令例：
```
sh insertdataset.sh
```
插入成功，应当返回三个code码为200
如：
```
{"code":200,"desc":"admin Login Successfully","data":"CAEB3FC3685CF253527FAB7A2D4859BEadmin"}{"code":200,"desc":"bulk_insert Successfully!","data":{"sccessNum":12}}{"code":200,"desc":"bulk_insert Successfully!","data":{"sccessNum":3}}
```


### 2、插入脚本注意事项
>* 需编辑脚本将ip地址改为自己所要插入数据的服务器ip。（该处取决于aus_service.yml内的datain.transport.host，1、若配置的是ip，则插入脚本也需编辑为ip 2、若是localhost，则插入脚本也需编辑为localhost且本机仅可用 3、若是0.0.0.0 则上述两种方法都可以）
>* 如果aus登录帐号密码是系统默认，则直接执行脚本，否则，需要调用脚本内的第一条登录命令，且修改正确帐号密码，将返回的token替换进插入脚本内的另外两条命令的token


## 执行已整理的MQL语句并保存报表
testMQL.py
```
file = open('F:/sugon/20180330/AutoTestforAUS/AUS/testdata/mql整理测试taxi.txt',encoding="utf-8")
```
根据本机修改文件路径，导入mql整理测试taxi.txt。
>* 该步骤需要在taxi和ixat数据集中已有数据，即需要前面操作完成后执行
>* 在windows下的cmd执行语句

执行命令例子：
```
python F:\sugon\20180330\AutoTestforAUS\AUS\testMQL.py
```

## 注
>* 执行createdataset.py会先判断有没有taxi数据集，若有则删除后新建，但该数据集被其他地方引用则删除失败（如报表保存了taxi数据集）。
