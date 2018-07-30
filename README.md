# blog
my blog
##dd
平时笔记：
ssh 传数据到Linux 服务器先装 ssh服务命令 sudo apt-get install ssh-server
ssh 传数据 scp file root@ip:目标文件

安装jdk sudo apt-get install openjdk-8-jdk

安装mysql sudo apt-get install mysql-server

修改环境变量sudo vim /etc/environment source /etc/environment
            sudo vim ~/.bashrc
			source ~/.bashrc  在末尾添加 export PATH=/opt/EmbedSky/4.3.3/bin:$PATH
			
利用chmod修改权限：sudo chmod -R 777 tmp/  -R以下的文件夹
利用chown改变所有者：chown -R username:users Document/

解压：tar zxvf FileName.tar.gz
压缩：tar zcvf FileName.tar.gz DirName

移动文件 sudo mv file targetdirectory
启动tomcat /startup.sh shutdown.sh

删除非空文件夹 rmdir 或 rm -rf

搭建ftp服务器 sudo apt-get install vsftpd

直接修改conf目录下的server.xml文件，具体如下：

找到 Host节点 ，在其中添加：<Context path="" docBase="换成你的路径" reloadable="true"></Context>

​<Host name="localhost" appBase="webapps" unpackWARs="true" autoDeploy="true">  
    <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs" prefix="localhost_access_log."   
        suffix=".txt" pattern="%h %l %u %t "%r" %s %b" />  
    <!--在Host标签下加入Context标签，path指的是服务器url请求地址（例如127.0.0.1/data），  
        docBase指的是服务器文件的路径，reloadable指的是在omcat不重启的情况下实时同步本地目录-->          
    <Context path="/data" docBase="E:/tmp" reloadable="true" debug="0" crossContext="true"/>  
</Host>  

sftp 连接 sftp username@ip
get ［-Ppr］ remote ［local］
put ［-Ppr］ local ［remote］

$sudo apt-get install redis-server

启动 Redis
$ redis-server
查看 redis 是否启动？
$ redis-cli
以上命令将打开以下终端：

redis 127.0.0.1:6379>
127.0.0.1 是本机 IP ，6379 是 redis 服务端口。现在我们输入 PING 命令。

redis 127.0.0.1:6379> ping
PONG

jenkins 简单的集成 root 

-------------------------------------------------------------
nodejs环境搭建

1、在nodejs安装路径下，新建node_global和node_cache两个文件夹
2、设置缓存文件夹

npm config set cache "D:\vueProject\nodejs\node_cache"
　设置全局模块存放路径 npn就放在这了
npm config set prefix "D:\vueProject\nodejs\node_global"

三、基于 Node.js 安装cnpm（淘宝镜像）

npm install -g cnpm --registry=https://registry.npm.taobao.org
//node
如果出现以下错误：
npm err! Error: connect ECONNREFUSED 127.0.0.1:8087 
解决办法为：
$ npm config set proxy null

卸载模块
我们可以使用以下命令来卸载 Node.js 模块。
$ npm uninstall express
卸载后，你可以到 /node_modules/ 目录下查看包是否还存在，或者使用以下命令查看：
$ npm ls

查看安装模块
 npm list -g
npm install express          # 本地安装
npm install express -g   # 全局安装 多了一个-g
升级nodejs
npm install npm -g
npm install -g nodemon
数据库
#以PostgreSql为例
npm install mysql --save
sudo npm install knex --save
sudo npm install pg --save

模块
fs 文件的操作
events 模块，并通过实例化 EventEmitter 类来绑定和监听事件
Stream(流)
管道流 readerStream.pipe(writerStream);
链式流 fs.createReadStream('input.txt')
      .pipe(zlib.createGzip())
      .pipe(fs.createWriteStream('input.txt.gz'))
压缩文件
模块
module.exports = function() {
  // ...
}

路由：router  serve 
http.createServe(){
}等操作

定义了一个 Buffer 类，该类用来创建一个专门存放二进制数据的缓存区。

回掉 function 就是最后的一个参数 就是回掉函数 是吧
例子：fs.readFile('input.txt', function (err, data) {
    if (err) return console.error(err);
    console.log(data.toString());
});
//koa 路由相关
有全局和非全局之分
npm install koa -g
//node
四、设置环境变量（非常重要）

五、安装Vue

cnpm install vue -g

六、安装vue命令行工具，即vue-cli 脚手架

cnpm install vue-cli -g

七、新项目的创建

1.打开存放新建项目的文件夹

打开开始菜单，输入 CMD，或使用快捷键 win+R，输入 CMD，敲回车，弹出命令提示符。打开你将要新建的项目目录

2.根据模版创建新项目

在当前目录下输入“vue init webpack-simple 项目名称（使用英文）”。

vue init webpack-simple mytest

3、安装工程依赖模块

定位到mytest的工程目录下，安装该工程依赖的模块，这些模块将被安装在：mytest\node_module目录下，node_module文件夹会被新建，而且根据package.json的配置下载该项目的modules

cd mytest
cnpm install

4、运行该项目，测试一下该项目是否能够正常工作，这种方式是用nodejs来启动。

npm run build

cnpm run dev

npm i axios

npm install --save axios vue-axios

start nginx

点击按钮跳转
this.$router.go('/login');//其中login是你定义的一个路由模块 会刷新
$router.push({path:'/index'});
methods:{
  clickFn:function(){
    this.$router.push({path:'/index'});
}

sudo apt-get install nginx
sudo /etc/init.d/nginx start
Ubuntu安装之后的文件结构大致为：
所有的配置文件都在/etc/nginx下，并且每个虚拟主机已经安排在了/etc/nginx/sites-available下
程序文件在/usr/sbin/nginx
日志放在了/var/log/nginx中
并已经在/etc/init.d/下创建了启动脚本nginx
默认的虚拟主机的目录设置在了/var/www/nginx-default (有的版本 默认的虚拟主机的目录设置在了/var/www, 请参考/etc/nginx/sites-available里的配置)

apache的默认文档根目录是在ubuntu上的/var/www目录 ,配置文件是/ etc/apache2/apache2.conf。配置存储在的子目录在/etc/apache2目录。

apt-get install php7.0 libapache2-mod-php7.0

/etc/init.d/apache2 restart

正则表达式
^one 匹配one开头的  ^one$  匹配one 无效的不知道为啥

solr环境的搭建：：
下载解压：solr


python

list 用[] tup 用() set{}



shell
例子
#!/bin/bash

######################################################################
# Tomcat自动部署脚本
#  先拷贝tomcat的war包到Linux服务器，然后
# Auhthor: zdw
# Usage: auto_deploy.sh [projName] [deploy] | 发布项目：
       #auto_deploy.sh [start|stop|restart] | 启动 or 停止 or 重启 tomcat
#需要修改的地方：	 



## FTP 上传路径
##ftpPath=/usr/local/tomcat/temp //成本系统公用的tomcat目录
#fWar=${ftpPath}/${projName}.war//成本系统的包名

## tomcat 路径
#tomcatPath=/usr/local/tomcat       //后台tomat路径
#tPath=${tomcatPath}/webapps/${projName} //后台tomat路径的项目名
#tWar=${tomcatPath}/webapps/${projName}.war // 后台tomat路径的项目包名
	   
##########################################################################
#输入tomcatPath地址：



deploy()
{
	export tomcatPath=/home/zijiwan/apache1
	#输入projName项目名称：
	export projName=dragonshard
	echo "tomcat path:       ${tPath}"
	echo "tomcat war:       ${tWar}"
	echo "ftp path:       ${ftpPath}"
	echo "ftp war:       ${fWar}"

	echo 'step1) stop tomcat'
	bash ${tomcatPath}/bin/shutdown.sh
	echo 'stop tomcat finished...Done! '

	echo 'step2) start tomcat'
	rm -rf ${tPath}
	rm -rf ${tWar}
	cp ${fWar} ${tWar}
	echo "copy ${projName}.war file ... Done!"

	bash ${tomcatPath}/bin/startup.sh
	echo 'Done!'

	echo 'show logs? y/n'
	read command
	if [ "${command}" == "y" ]; then
		tail -f ${tomcatPath}/logs/catalina.out
	fi
}


if [ "$2" == "deploy" ]; then
    deploy
fi

文件头部
#!/bin/bash

echo 输出

chmod +x ./test.sh  #使脚本具有执行权限
./test.sh  #执行脚本

readonly myUrl  将变量置为只读
unset variable_name 删除变量
变量类型
运行shell时，会同时存在三种变量：

1) 局部变量 局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量。
2) 环境变量 所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量。
3) shell变量 shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行
截取字符串
string="runoob is a great site"
echo ${string:1:4} # 输出 unoo
查找子串的位置
string="runoob is a great company"
echo `expr index "$string" is`  # 输出 8
数组
array_name=(value0 value1 value2 value3)
读取数组
${数组名[下标]}
# 取得数组元素的个数
length=${#array_name[@]}
# 或者
length=${#array_name[*]}
# 取得数组单个元素的长度
lengthn=${#array_name[n]}

echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第一个参数为：$1";
echo "第二个参数为：$2";
echo "第三个参数为：$3";

很多地方是反引号 而不是单引号


git config --global merge.ours.driver true  
echo 'email.json merge=ours' >> .gitattributes  

//跨域
 String origin =request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Accept,token,X-Requested-With");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write("{\"code\":\"0\",\"data\":\"\",\"msg\":\"该用户无权限\"}");
			

			
ORCL
//在查询中进行判断
select case when aa='1'
		then 'aa'
		when aa='2'
		then 'bb'
		else
		'dd'
		end as 'sss'
from table;




import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * DTO 与 Entity转换接口
 *
 * 注解参数：
 * 字段名不同时进行转换， target是DTO字段，source是Entity字段
 * @Mapping(target = "personName", source = "name")
 *
 * DTO中存在Entity中没有的字段
 * @Mapping(target = "hand",  ignore = true)
 *
 **/
@Mapper(componentModel = "spring")
public interface ConvertMapper {
    ConvertMapper INSTANCE = Mappers.getMapper(ConvertMapper.class);

    /**
     * ---------------------------
     * Demo
     * ---------------------------
     */
    DemoAddDTO entityToDTO(Demo entity);
    void updateEntityFromDTO(DemoAddDTO demoAddDTO, @MappingTarget Demo entity);
    void updateEntityFromDTO(List<DemoAddDTO> demoAddDTOList, @MappingTarget List<Demo> entity);
    void updateEntityFromDTO(DemoUpdateDTO demoUpdateDTO, @MappingTarget Demo entity);
    void updateEntityFromDTO(DemoSearchListDTO demoSearchListDTO, @MappingTarget Demo entity)












