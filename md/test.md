# redis
###### 为什么要使用redis/缓存?
1.主要从高性能和高并发这两个问题来看待这个问题。
高性能: 假如用户第一次访问数据库中的某一些数据。这个速度比较慢,因为是从硬盘
上。将用户访问的数据存放在缓存中,这样下次访问的时候就可以直接从缓存中获取了。
操作缓存就是直接操作内存,所以速度非常快。如果数据库中对应的数据改变了以后,
对应的改变缓存中的数据即可。
```flow
st=>start: 开始
e=>end: 登录
io1=>inputoutput: 输入用户名密码
sub1=>subroutine: 数据库查询子类
cond=>condition: 是否有此用户
cond2=>condition: 密码是否正确
op=>operation: 读入用户信息

st->io1->sub1->cond
cond(yes,right)->cond2
cond(no)->io1(right)
cond2(yes,right)->op->e
cond2(no)->io1
```