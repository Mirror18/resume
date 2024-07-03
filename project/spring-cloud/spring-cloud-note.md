# spring-cloud开发

首先先明确的是spring是Javaee 的一个轻量级开发框架，主营ioc和aop。继承JDBC、ORM、MVC等功能

spring Boot 是基于spring，提供开箱即用的积木式组件。

spring cloud 是分布式应用程序编写更方便，更容易提供一组基础设施。核心是spring框架，利用spring Boot 自动配置，实现最简化的分布式应用程序开发。



那这次目的是什么，以spring cloud为基础，从零搭建一个不间断运行的证券交易所。

依赖有什么，除了spring cloud 外，通常还需要依赖数据库，消息系统，缓存等各种组件。

数据库选择MySQL 5.7.消息系统选择 kafka .缓存选择 redis 6

## 项目架构设计

首先是项目名字为证券交易所，那么特点是什么

### 项目特点

证券交易系统的交易是基于交易对。BTC/USD，是USD购买BTC。USD是几家货币，BTC是交易资产。

证券交易系统是通过买卖双方各自的报价，按照价格优先，时间优先的顺序，对买卖双方进行撮合。

### 项目需求

仅支持BTC/USD一个交易对

不收取手续费

不考虑与银行和区块链系统对接

不考虑风控相关的需求，

仅提供web操作页面

无后台管理功能

### 系统木块

从逻辑上划分为如下模块

API模块。交易员下单。撤单的API入口

定序模块。用于对所有收到的订单进行定序

交易引擎，对定序后的订单进行撮合，清算

行情模块，将撮合输出的成交信息汇总

推送模块，将市场行情，交易结果，资产变化等信息以websocket途径推送给用户

UI 模块，给交易员提供一个web操作页面。将交易员的操作转发给后端API。



交易引擎是最核心的模块。内部客户氛围

资产模块，管理用户的资产

订单模块，管理用户的活动订单

撮合引擎，处理买卖订单，生成成交信息

清算模块，对撮合引擎输出的城郊信息进行清算，买卖双方的资产进行交换。

交易引擎是一个以事件驱动为核心的系统，输入是定序后的一个个时间，舒服这是撮合结果

## 搭建项目框架

以maven为构建工具，将每个模块作为一个maven项目管理，抽出公公逻辑放入common模块。

common：公公代码

config:配置服务器

pushL推送服务

quotation:行情服务

trading-api交易API服务

trading-engine交易引擎

trading-sequencer丁旭服务

ui用户web界面

以上就是整个项目的骨架。

每个模块各司其职。只负责对应的部分。



所以为了整合，

第一步，先整合版本和依赖管理，就是配置不写那么多了，写一个parent模块，其他模块直接从parent继承。其他的业务模块就无序指定相关组件的 版本，只有我们自己编写的组件和没有在spring Boot 和spring cloud 中引入的组件，才需要指定版本。

上面是依赖整合，现在这是还需要一个模块来进行一起编译，所以创建了build 模块。对其他的模块来说相当于有了一个根模块，对其他模块来说，

parent模块可以没有，因为可以去这个根模块中继承。



同样的，为了分类，还创建了，应该说叫文件夹吧，存储spring cloud 从复古 服务器端的配置文件。



同样为了方便运行，所以用docker作为部署环境，不过我这个docker是在虚拟机上，也就是说到时候是要放到虚拟机上运行相关服务的，不过在本地也能用，除了docker file 没法用而已，因为环境我已经搭建好了。



spring cloud config

当初在写spring Boot 的时候，一个项目，除了环境，还需要到导入配置数据。

现在是分模块的，也就是说每个模块都代表了一个完整的项目，每个项目都需要一份配置文件，那么整合，就需要继续整合配置文件，该如何整合，所以就有了spring cloud config。

提供了一个通用的分布式应用的配置解决方案，将配置分为两部分。

config server。配置服务器，负责读取所有配置，

config client 潜入到各个spring Boot 应用中，本地无配置信息，启动的时候向服务器请求配置。

所以这个才是我们简历config模块的意义，在config模块中引入 spring-cloud-config-server，然后编写一个configapplication入口，标注enableconfigserver.

在application,.yml中设置如何搜索，因为可以 本地文件，git仓库，数据库等。这里采用本地文件。需要设置配置文件的搜索路径。然后直接运行，别的模块就可以向这个服务中请求配置文件。

其他的spring Boot 应用，需要基础配置信息，再导入服务器配置信息。

并且这个应用中也需要导入 spring cloud start config依赖。

然后就是关于读入到配置都是哪些配置

- {name}-{profile}.yml
- application-{profile}.yml
- {name}.yml
- application.yml

这个就是模板，因为我们以应用名称+profile为Default进行配置的话，URL是这样的，http://localhost:8888/ui/default。他那返回一个json文件。其中name和profile不是必须提供的，上述就是优先级。

既然有优先级，那么自然就会有一些全局默认配置，这种就需要陪知道application.yml中，这个优先级最低，但是通用。如果在生产环境中没有更改配置，就会报错，刚好完成检修。



环境变量。

这个主要是启动的时候，我们虽然配置了默认配置，但也同样支持通过环境变量输入配置，但好消息是，这玩意儿不会占用，也就是相当于无感。直接当做默认使用就行。

使用Spring Cloud Config时，读取配置文件步骤如下：

1. 启动XxxApplication时，读取自身的`application.yml`，获得`name`和Config Server地址；
2. 根据`name`、`profile`和Config Server地址，获得一个或多个有优先级的配置文件；
3. 按优先级合并配置项；
4. 如果配置项中存在环境变量，则使用Xxx应用本身的环境变量去替换占位符。

环境变量通常配置一些敏感信息，如数据库连接口令。问题不大



## 设计交易引擎

一个完整的交易引擎包括资产系统、订单系统、撮合引擎和清算系统。

资产系统不仅记录了每个用户的所有资产，而且还要根据业务随时冻结和解冻用户资产。例如，下买单时，根据买入价格和买入数量，计算需要冻结的USD，然后对用户的可用USD进行冻结。

订单系统跟踪所有用户的所有订单。

撮合引擎是交易引擎中最重要的一个组件，它根据价格优先、时间优先的原则，对买卖订单进行匹配，匹配成功则成交，匹配不成功则放入订单簿等待后续成交。

清算系统则是处理来自撮合引擎的撮合结果。

```ascii
                ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
                   ┌─────────┐    ┌─────────┐
Order Request ──┼─▶│  Order  │───▶│  Match  │ │
                   └─────────┘    └─────────┘
                │       │              │      │
                        │              │
                │       ▼              ▼      │
                   ┌─────────┐    ┌─────────┐
                │  │  Asset  │◀───│Clearing │ │
                   └─────────┘    └─────────┘
                └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┘
```

最后，把上述几个组件组合起来，我们就得到了一个完善的交易引擎。

我们观察交易引擎的输入，它是一系列确定的订单序列，而交易引擎的输出则是成交信息。与此同时，交易引擎本身是一个确定性的状态机，它的内部状态包括订单集、资产表和订单簿。每当一个新的订单请求被输入后，状态机即更新状态，然后输出成交信息。

注意到交易引擎在任何一个时刻的状态都是确定的，在一个确定的状态下，继续给定一个确定的订单请求，下一个状态也是确定的，即：

交易引擎当前状态是Sn，则下一个输入On+1会将其状态更新为Sn+1。

因此，对于一组给定的输入订单集合[O1, O2, O3, ...]，交易引擎每次内部状态的更新和输出都是完全确定的，与时间无关。

我们换句话说，就是给定一组订单输入的集合，让一个具有初始状态的交易引擎去执行，获得的结果集为[R1, R2, R3, ...]，把同样的一组订单输入集合让另一个具有初始状态的交易引擎去执行，获得的结果集完全相同。

因此，要实现交易引擎的集群，可以同时运行多个交易引擎的实例，然后对每个实例输入相同的订单请求序列，就会得到完全相同的一组输出：

```ascii
                   ┌──────┐
                ┌─▶│Engine│──▶ R1, R2, R3...
                │  └──────┘
O1, O2, O3... ──┤
                │  ┌──────┐
                └─▶│Engine│──▶ R1, R2, R3...
                   └──────┘
```

可见，交易引擎是一个事件驱动的状态机。

实现交易引擎有多种方式，例如，把资产、订单等放入数据库，基于数据库事务来保证交易完整性，这种方式的缺点就是速度非常慢，TPS很低。

也可以把全部组件放在内存中，这样能轻松实现一个高性能的交易引擎，但内存的易失性会导致宕机重启后丢失交易信息，因此，基于内存的交易引擎必须要解决数据的持久化问题。

所以我们才去完全基于内存的交易引擎。

### 设计资产系统

在交易系统中，用户资产是指用户以各种方式将USD、BTC充入交易所后的余额。本节我们来实现一个用户资产系统。

用户在买入BTC时，需要花费USD，而卖出BTC后，获得USD。当用户下单买入时，系统会先冻结对应的USD金额；当用户下单卖出时，系统会先冻结对应的BTC。之所以需要有冻结这一操作，是因为判断能否下单成功，是根据用户的可用资产判断。每下一个新的订单，就会有一部分可用资产被冻结，因此，用户资产本质上是一个由用户ID和资产ID标识的二维表：

| 用户ID | 资产ID |   可用 | 冻结 |
| :----- | :----- | -----: | ---: |
| 101    | USD    | 8900.3 | 1200 |
| 101    | BTC    |    500 |    0 |
| 102    | USD    |  12800 |    0 |
| 103    | BTC    |      0 |   50 |

上述二维表有一个缺陷，就是对账很困难，因为缺少了一个关键的负债账户。对任何一个资产管理系统来说，要时刻保证整个系统的资产负债表为零。

对交易所来说，用户拥有的USD和BTC就是交易所的系统负债，只需引入一个负债账户，记录所有用户权益，就可以保证整个系统的资产负债表为零。假设负债账户以ID为1的系统用户表示，则用户资产表如下：

| 用户ID | 资产ID |     可用 | 冻结 |
| :----- | :----- | -------: | ---: |
| 1      | USD    | -22900.3 |    0 |
| 1      | BTC    |     -550 |    0 |
| 101    | USD    |   8900.3 | 1200 |
| 101    | BTC    |      500 |    0 |
| 102    | USD    |    12800 |    0 |
| 103    | BTC    |        0 |   50 |

引入了负债账户后，我们就可以定义资产的数据结构了。

在数据库中，上述表结构就是资产表的结构，将用户ID和资产ID标记为联合主键即可。

但是在内存中，我们怎么定义资产结构呢？

可以使用一个两层的`ConcurrentMap`定义如下：

```
// 用户ID -> (资产ID -> Asset)
ConcurrentMap<Long, ConcurrentMap<AssetEnum, Asset>> userAssets = new ConcurrentHashMap<>();
```

第一层`Map`的Key是用户ID，第二层`Map`的Key是资产ID，这样就可以用`Asset`结构表示资产：

```
public class Asset {
    // 可用余额:
    BigDecimal available;
    // 冻结余额:
    BigDecimal frozen;

    public Assets() {
        this(BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public Assets(BigDecimal available, BigDecimal frozen) {
        this.available = available;
        this.frozen = frozen;
    }
}
```

下一步，我们在`AssetService`上定义对用户资产的操作。实际上，所有资产操作只有一种操作，即转账。转账类型可用`Transfer`定义为枚举类：

```
public enum Transfer {
    // 可用转可用:
    AVAILABLE_TO_AVAILABLE,
    // 可用转冻结:
    AVAILABLE_TO_FROZEN,
    // 冻结转可用:
    FROZEN_TO_AVAILABLE;
}
```

转账操作只需要一个`tryTransfer()`方法，实现如下：

```
public boolean tryTransfer(Transfer type, Long fromUser, Long toUser, AssetEnum assetId, BigDecimal amount, boolean checkBalance) {
    // 转账金额不能为负:
    if (amount.signum() < 0) {
        throw new IllegalArgumentException("Negative amount");
    }
    // 获取源用户资产:
    Asset fromAsset = getAsset(fromUser, assetId);
    if (fromAsset == null) {
        // 资产不存在时初始化用户资产:
        fromAsset = initAssets(fromUser, assetId);
    }
    // 获取目标用户资产:
    Asset toAsset = getAsset(toUser, assetId);
    if (toAsset == null) {
        // 资产不存在时初始化用户资产:
        toAsset = initAssets(toUser, assetId);
    }
    return switch (type) {
    case AVAILABLE_TO_AVAILABLE -> {
        // 需要检查余额且余额不足:
        if (checkBalance && fromAsset.available.compareTo(amount) < 0) {
            // 转账失败:
            yield false;
        }
        // 源用户的可用资产减少:
        fromAsset.available = fromAsset.available.subtract(amount);
        // 目标用户的可用资产增加:
        toAsset.available = toAsset.available.add(amount);
        // 返回成功:
        yield true;
    }
    // 从可用转至冻结:
    case AVAILABLE_TO_FROZEN -> {
        if (checkBalance && fromAsset.available.compareTo(amount) < 0) {
            yield false;
        }
        fromAsset.available = fromAsset.available.subtract(amount);
        toAsset.frozen = toAsset.frozen.add(amount);
        yield true;
    }
    // 从冻结转至可用:
    case FROZEN_TO_AVAILABLE -> {
        if (checkBalance && fromAsset.frozen.compareTo(amount) < 0) {
            yield false;
        }
        fromAsset.frozen = fromAsset.frozen.subtract(amount);
        toAsset.available = toAsset.available.add(amount);
        yield true;
    }
    default -> {
        throw new IllegalArgumentException("invalid type: " + type);
    }
    };
}
```

除了用户存入资产时，需要调用`tryTransfer()`并且不检查余额，因为此操作是从系统负债账户向用户转账，其他常规转账操作均需要检查余额：

```
public void transfer(Transfer type, Long fromUser, Long toUser, AssetEnum assetId, BigDecimal amount) {
    if (!tryTransfer(type, fromUser, toUser, assetId, amount, true)) {
        throw new RuntimeException("Transfer failed");
    }
}
```

冻结操作可在`tryTransfer()`基础上封装一个方法：

```
public boolean tryFreeze(Long userId, AssetEnum assetId, BigDecimal amount) {
    return tryTransfer(Transfer.AVAILABLE_TO_FROZEN, userId, userId, assetId, amount, true);
}
```

解冻操作实际上也是在`tryTransfer()`基础上封装：

```
public void unfreeze(Long userId, AssetEnum assetId, BigDecimal amount) {
    if (!tryTransfer(Transfer.FROZEN_TO_AVAILABLE, userId, userId, assetId, amount, true)) {
        throw new RuntimeException("Unfreeze failed");
    }
}
```

可以编写一个`AssetServiceTest`，测试各种转账操作：

```
public class AssetServiceTest {
    @Test
    void tryTransfer() {
        // TODO...
    }
}
```

并验证在任意操作后，所有用户资产的各余额总和为`0`。

最后是问题解答：

### 为什么不使用数据库？

因为我们要实现的交易引擎是100%全内存交易引擎，因此所有用户资产均存放在内存中，无需访问数据库。

### 为什么要使用ConcurrentMap？

使用`ConcurrentMap`并不是为了让多线程并发写入，因为`AssetService`中并没有任何同步锁。对`AssetService`进行写操作必须是单线程，不支持多线程调用`tryTransfer()`。

但是读取Asset支持多线程并发读取，这也是使用`ConcurrentMap`的原因。如果改成`HashMap`，根据不同JDK版本的实现不同，多线程读取`HashMap`可能造成死循环（注意这不是`HashMap`的bug），必须引入同步机制。

### 如何扩展以支持更多的资产类型？

我们在`AssetEnum`中以枚举方式定义了USD和BTC两种资产，如果要扩展到更多资产类型，可以以整型ID作为资产ID，同时需要管理一个资产ID到资产名称的映射，这样可以在业务需要的时候更改资产名称。