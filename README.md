# Toppahs-Cuber-Bot
![Github](https://img.shields.io/badge/Author-CodeMzt-blue) ![GitHub](https://img.shields.io/github/license/CodeMzt/Toppahs) ![GitHub repo size](https://img.shields.io/github/repo-size/CodeMzt/Toppahs) ![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/CodeMzt/Toppahs) ![GitHub last commit](https://img.shields.io/github/last-commit/CodeMzt/Toppahs)
# 许可
>本项目采用MIT开源  
```
MIT License

Copyright (c) 2021 CodeMzt

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
# 说明
本项目基于[mirai](https://github.com/mamoe/mirai)框架开发，运用双语言（Kotlin&C++）编写，采用[MIT](https://github.com/CodeMzt/Toppahs/blob/master/LICENSE)开源

本项目基本全开源，可能有少部分由于未整理完未开源，敬请期待
>C++部分的编写基于[MiraiCP](https://github.com/Nambers/MiraiCP)
# 使用流程:
## 1 配置环境
mirai需要openjdk而不是甲骨文的jdk,如下载openjdk15/openjdk11

配置[mcl](https://github.com/mamoe/mirai/blob/dev/docs/UserManual.md#%E4%BD%BF%E7%94%A8%E7%BA%AF%E6%8E%A7%E5%88%B6%E5%8F%B0%E7%89%88%E6%9C%AC)，推荐使用纯控制台
## 2 服务部署
目前此项目只用到了[scambles](https://github.com/CuberBot/scramble-server)
服务,自己部署服务
## 3 使用
### 1.克隆
 克隆此仓库，直接克隆或
```
> gh repo clone CodeMzt/Toppahs
```
>如果仅是使用可以直接前往[Releases](https://github.com/CodeMzt/Toppahs/releases)下载jar包，并跳过第2、3步
### 2.配置
 直接用IDEA打开本项目文件夹，配置好gradle
### 3.生成
 1 在RunMirai.kt改好机器人的账号密码，运行RunMiraiKt启动项，可直接在IDE内启动mirai

 2 你也可以直接运行buildPlugin启动项，生成jar文件
>3.1仅推荐用于测试bot，若要长期挂起，推荐使用3.2方法
### 4.运行
 将jar文件放到mcl启动器的plugin文件夹里,然后双击mcl.bat或
```
java -jar mcl.jar
```
 启动mirai，开始运行
# 功能
QQ魔方机器人，提供：生成打乱（1~17、minx......），wca成绩查询，学习等功能
## TODO
- [ ] 黑名单(BlackList)
- [ ] 个人永久屏蔽(PBlack)
- [x] 复读(Repeat)
- [x] 开关(Switchs)
- [x] 入群欢迎(Welcome)
- [x] 生成魔方打乱(Scramble)
- [x] WCA 成绩查询(WcaResultGet)
- [ ] WCA 排名查询(WcaRankGet)
- [x] 粗饼链接 Link
- [ ] 粗饼赛事查询(Competition)
- [ ] 粗饼赛事选手查询(CompetitionCuber)
- [ ] 生成魔方图片(Cubepic)
- [x] WCA成绩趋势图(Trend)
- [x] 学习(Learn)
- [ ] 快递(Express)
- [x] 天气(Weather)
- [ ] 群管(Admin)
# 鸣谢
感谢框架开发团队 [@Mamoe Technologies](https://github.com/mamoe) 与C++SDK开发者 [@Nambers](https://github.com/Nambers)                                                    
感谢打乱服务的提供者 [@lz1998](https://github.com/lz1998)
# Toppahs
