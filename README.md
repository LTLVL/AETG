浙江大学计算机科学与技术学院

组合测试课程报告

2023—2024学年秋冬学期




# 1  引言
本次开发的是一个组合测试用例生成系统，需要实现AETG算法，针对两个不同场景（京东笔记本电脑购物、携程订购机票）根据程序启动时用户提供的t参数，自动生成足够覆盖所有t-way的测试用例。
本项目地址：[https://github.com/LTLVL/AETG.git](https://github.com/LTLVL/AETG.git)
## 1.1  设计目的
1.实现AETG算法
2.根据用户提供的t参数，自动生成足够覆盖所有t-way的测试用例。
## 1.2  设计说明
本项目结构主要为domain包及util包。其中domain包涵盖了Laptop（10种参数）、Trip（8种参数）两个实体类，具体如下图：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701950826718-c66a634c-e59d-4150-88e8-f776bb6f58cf.png#averageHue=%23f8f4f2&clientId=ue9eef751-0ca4-4&from=paste&height=467&id=u4aa774a0&originHeight=700&originWidth=978&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=115612&status=done&style=none&taskId=u271f84d0-454a-49ed-9efe-86c5a8840b7&title=&width=652)
util包种涵盖了三个主要功能模块，分别是Algorithm：负责算法主流程，Initializer：负责初始化ucps（uncovered pairs），ResultHandler：负责处理结果，生成测试用例xlsx文件。
# 2  详细设计
## 2.1  Algorithm
Algorithm构造函数中接收参数t（t-way）与flag（flag为true代表laptop的测试用例生成，false为trip的测试用例生成），其run方法为主流程方法。
首先生成一个Initializer对象，初始化所有 t 个变量的等价类组合的用例集并存入ucps中，接着固定选取第一个测试用例（因为覆盖的ucps都一样）并插入cps，从ucps中去除掉该用例覆盖的ucp。
接着循环调用iterate方法，该方法即为AETG算法的主体：

- 生成50个空的候选测试用例；
- 确定所有候选用例的第一个参数：选择能覆盖最多ucps的参数value，如果有多个相同的，从这些参数中随机选择；
- 随机选择剩余参数；
- 比较50个候选用例，选择其中能覆盖最多ucp的用例为真实测试用例；
- 将真实测试用例插入cps，同时在ucps中去掉该用例覆盖的pairs；
- 重复迭代，直至ucps为空；

当ucps为空后即选出了所有测试用例，接着将其转化为对应的实体类集合，交给Main方法处理结果，导出xlsx文件。
## 2.2 Initialize
首先利用回溯算法进行排列组合，以laptop为例，laptop共有10个参数（参数分布为[18, 4, 2, 22, 4, 4, 4, 14, 8, 9]），如果用户输入的t为2，那么共有（45）种组合，它们分别是：[0,1]（表示选择第0个和第1个参数）、[0,2]、[0，3]......如下图所示：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701952646142-c88a9803-d590-44c6-bc18-be2f12a08bc0.png#averageHue=%23f3f1f1&clientId=ue9eef751-0ca4-4&from=paste&height=221&id=u54e3fd6a&originHeight=331&originWidth=1671&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=42788&status=done&style=none&taskId=ub038c234-d8e2-4a8d-a793-18e54369d60&title=&width=1114)
得到这45种组合后可以得出所有的t 个变量的等价类组合的用例集，在本例中共有3352个，它们是：
（[3, 2, -1, -1, -1, -1, -1, -1, -1, -1]表示第0个参数选择第3个值，第1个参数选择第2个值）
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701952780762-fa11af8b-552d-49c7-9d38-e867ed52838a.png#averageHue=%23f2f2f1&clientId=ue9eef751-0ca4-4&from=paste&height=225&id=ua22bd5bf&originHeight=337&originWidth=1634&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=38720&status=done&style=none&taskId=u36c531cf-3aea-4d59-a9d5-6004c828359&title=&width=1089.3333333333333)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701952846029-dfbaed7f-2418-4461-bd66-208c336899ed.png#averageHue=%23f3f2f2&clientId=ue9eef751-0ca4-4&from=paste&height=456&id=uaed40742&originHeight=684&originWidth=1719&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=79290&status=done&style=none&taskId=u3cec0b7c-ce15-443f-808f-bb16c83acf9&title=&width=1146)
## 2.3  ResultHandler
ResultHandler接收实体类集合（如下图所示）
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953017711-97ffdaa3-efdb-434c-8e2c-d529974a1bb9.png#averageHue=%23efedec&clientId=ue9eef751-0ca4-4&from=paste&height=497&id=uf7e52d26&originHeight=745&originWidth=2437&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=332565&status=done&style=none&taskId=ub1eb0437-6d0a-4d71-826b-b8bbb9a45e8&title=&width=1624.6666666666667)
接着利用Alibaba的easyexcel工具导出对应的xlsx文件：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953123284-aa3b5bee-9e54-465a-991f-42f366759935.png#averageHue=%23eeebe9&clientId=ue9eef751-0ca4-4&from=paste&height=615&id=ub0093b45&originHeight=922&originWidth=1248&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=269271&status=done&style=none&taskId=ud698ec1d-1719-4657-ac53-36f079c6ee2&title=&width=832)
## 2.4  Main
Main方法负责整个项目的调用，首先校验用户输入的t参数是否合法，接着生成两个Algorithm对象（分别负责laptop和trip），调用对应Algorithm对象的run方法生成实体类集合，将实体类集合交给对应的ResultHandler方法处理并导出xlsx文件。
# 3 测试与运行
## 3.1  程序测试
在程序代码基本完成后，经过不断的调试与修改，最后测试本次所设计的系统能够正常运行，能够满足基本需要，但还有部分功能可以改善，比如可以设计更加用户友好的图形化界面，可以将laptop或trip的参数改为用爬虫获取而并非手动写入（但是工作量会大大增加）。总的来说本次设计在功能上已经基本达到要求，其他细节方面有待以后完善。
## 3.2  程序运行
t为2时的运行结果（红色警告是因为本项目未使用SLF4J日志框架，不必理会）：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953407754-ec6773fe-8101-4ee3-9179-5bf4493aa830.png#averageHue=%23f7f5f4&clientId=ue9eef751-0ca4-4&from=paste&height=193&id=u70a61f70&originHeight=290&originWidth=970&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=46279&status=done&style=none&taskId=u50f1a85a-bb2f-4668-83a3-66bb610426b&title=&width=646.6666666666666)
laptop共有424个测试用例（每次个数可能上下浮动）：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953453468-9087ffb1-9cb6-4149-869e-9efb6a69615e.png#averageHue=%23edeae8&clientId=ue9eef751-0ca4-4&from=paste&height=613&id=u12b98606&originHeight=920&originWidth=1203&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=266996&status=done&style=none&taskId=u17ee4689-7673-41ed-b201-eb4104824fb&title=&width=802)
注：由于格式原因，出发日期这一列需要拉长列长才能正常显示，否则全显示为”######“。
trip共有350个测试用例：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953478262-b7ac4108-3184-41ae-a011-802bf26b634d.png#averageHue=%23f2f0ee&clientId=ue9eef751-0ca4-4&from=paste&height=612&id=ufbf49e34&originHeight=918&originWidth=1042&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=172614&status=done&style=none&taskId=u16c1c8d6-6326-4c42-be9a-289ccd36a9f&title=&width=694.6666666666666)
t为3时的运行结果:
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953771662-6ff4528d-20b9-4582-b0be-5b5a22442216.png#averageHue=%23f9f6f6&clientId=ue9eef751-0ca4-4&from=paste&height=179&id=u2837f1eb&originHeight=268&originWidth=942&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=39136&status=done&style=none&taskId=uad426f58-2b80-4a78-9dce-b39321dfaae&title=&width=628)
laptop共有6051个测试用例：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953804985-f0705956-16c5-4c59-89cd-0f1047907731.png#averageHue=%23edeae8&clientId=ue9eef751-0ca4-4&from=paste&height=611&id=uecb12b7e&originHeight=917&originWidth=1196&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=264441&status=done&style=none&taskId=ud31d0d76-b26d-46ed-a770-abc13722f81&title=&width=797.3333333333334)
trip共有2862个测试用例：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701953815153-03e82f58-7dfa-4304-b2e9-cc47c5ec5f4c.png#averageHue=%23f1efed&clientId=ue9eef751-0ca4-4&from=paste&height=608&id=u84f7b9b4&originHeight=912&originWidth=992&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=160732&status=done&style=none&taskId=u4e8e4bc7-fe54-4b15-b4fe-798cf80dc09&title=&width=661.3333333333334)
# 4．总结
本次作业总体来说是有一定难度的，工作量也很大，需要有扎实的编程基础，还需要对各种工具框架的使用烂熟于心。
在选取laptop和trip参数时，考虑到性能和时间成本，laptop只取了10个参数，分布为[18, 4, 2, 22, 4, 4, 4, 14, 8, 9]，trip取了8个参数，分布为[19, 19, 8, 5, 3, 3, 4, 2]，但程序不失一般性，任何参数组合都能给出正确结果，但是运行时间无法保证。
总的来说，本次项目耗费心血颇多，但是收获也不小，熟悉了AETG算法，也对组合测试的各个步骤有了深刻的理解，很有收获。
注：
项目运行方式：利用maven package打包后生成target文件夹（提交的文件中包含该jar包，此步骤可省略），在终端中运行java -jar .\AETG-1.0-SNAPSHOT.jar t即可（t为参数），如下图（项目默认将xlxs文件导出到D盘，如要修改，请修改ResultHandler的构造方法）：
![image.png](https://cdn.nlark.com/yuque/0/2023/png/35639742/1701954802005-53960d58-3fd8-4f82-b36f-5fba258236fc.png#averageHue=%231f1f1f&clientId=ue9eef751-0ca4-4&from=paste&height=139&id=u8e8966bb&originHeight=209&originWidth=1184&originalType=binary&ratio=1.5&rotation=0&showTitle=false&size=41453&status=done&style=none&taskId=u1d628dab-bafa-41dd-9f5b-e1567c8d2b8&title=&width=789.3333333333334)
