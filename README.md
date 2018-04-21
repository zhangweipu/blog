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

start nginx

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


