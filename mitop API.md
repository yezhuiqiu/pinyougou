# **Mitop open API**

- [入门指引](#入门指引)
  - [创建API Key](#创建API Key)
  - [接口调用方式说明](#接口调用方式说明)
- [REST API](#REST API)
  - [请求交互](#请求交互)
  - [签名认证](#签名认证)
    - [签名说明](#签名说明)
    - [签名步骤](#签名步骤)
- [REST API列表](#REST API列表)
  - [查询系统时间戳](#查询系统时间戳)
  - [查询账户资产](#查询账户资产)
  - [查询历史委托](#查询历史委托)
  - [查询当前委托](#查询当前委托)
  - [查询订单详情](#查询订单详情)
  - [批量撤销订单](#批量撤销订单)
  - [撤销订单](#撤销订单)
  - [下单](#下单)
  - [成交明细](#成交明细)
  - [所有交易对的最新 Tickers](#所有交易对的最新 Tickers)
  - [k线历史数据](#k线历史数据)
  - [交易对最新成交价](#交易对最新成交价)
  - [交易对信息](#交易对信息)
  - [单个交易对ticker信息](#单个交易对ticker信息)

## 入门指引

**欢迎使用开发者文档，Mitop提供了简单易用的API接口，通过API可以获取市场行情数据、进行交易、管理订单等功能。**

### 创建API Key

用户在 [Mitop](https://www.mitop.cc/) 注册账号后，需要在PC端登录进入 [[API管理](https://www.mitop.cc/#/apiManage)] 中创建API Key秘钥，创建完成后得到一组随机生成的Access Key与Secret Key,利用这一组数据可以进行程序化操作，单个账号最多创建5个密钥

**请不要泄露Access Key 与 Secret Key信息，以免造成资产损失,建议用户为API绑定IP地址，每个密钥最多绑定5个IP，使用英文逗号进行分隔。**

### 接口调用方式说明

**Mitop目前提供REST调用接口方式，用户可根据使用场景和实际需求调用相应的接口获取或操作数据。**

## REST API

### 请求交互

#### 介绍

REST API 提供行情查询、余额查询、币币交易、订单管理功能

所有请求基于Https协议，请求头信息中content-type需要统一设置为表单格式:

- **content-type:application/x-www-form-urlencoded**

### 签名认证

#### 签名说明

- API 请求在通过网络传输的过程中极有可能被篡改，为了确保请求未被更改，除公共接口（基础信息，行情数据,k线数据）外的私有接口均必须使用您的 **Secret Key**与 **Access Key**做签名认证，以校验参数或参数值在传输途中是否发生了更改。
- 所有需要签名认证的接口必须的参数: 
  - `timestamp 毫秒时间戳，与服务器最大时间差不能超过30秒。`
  - `access_key `



#### 签名步骤

**请求方法+请求URI+请求参数字符串， 以secret_key为秘钥，使用HmacSHA256加密,输出base64编码后的字符串即为签名。请求参数字符串：按照ASCII码的顺序对参数名进行排序。**

**以获取交易资产余额为例**

- 接口
  - GET /v1/account/assets/

- 示例API秘钥
  - access_key = d3cce376-df3d-11e9-b535-00163e041ee5
  - secret_key = tg4AcYgQvVog9JVTxkg9Z9MIS7cmo8zr

- 接口所需参数
  - account
  - timestamp （所有签名接口必须的参数）
  - access_key （所有签名接口必须的参数）

1. **参数拼接原始排序：**

   ​       account=spot&timestamp =1569036224000&access_key =d3cce376-df3d-11e9-b535-00163e041ee5

2. **按照ASCII码的顺序对参数名进行排序后：**

   ​    access_key =d3cce376-df3d-11e9-b535-00163e041ee5&account=spot&timestamp =1569036224000

3. **最终用于签名的字符串为：**

   ​    GET /v1/account/assets/access_key =d3cce376-df3d-11e9-b535-   00163e041ee5&account=spot&timestamp =1569036224000

4. **签名的字符串以secret_key为秘钥，使用HmacSHA256加密,输出base64编码后的字符为：**

      XYHrMTEPz8ToUozeTwjC0uUa9dNRq4Qnt6ZvO9tGwNg=

   签名参数 sign = XYHrMTEPz8ToUozeTwjC0uUa9dNRq4Qnt6ZvO9tGwNg=

   **使用 UTF-8 编码，且进行了 *<u>URI 编码</u>*，十六进制字符必须大写，如 “=” 会被编码为 “%3D” ，空格被编码为 “%20”。**

   **因此 sign实际为 sign =  XYHrMTEPz8ToUozeTwjC0uUa9dNRq4Qnt6ZvO9tGwNg%3D**

5. **将生成的数字签名加入到请求的路径参数里**

   - 最终发送到服务器的请求地址为:
     - https://www.mitop.cc/open/api/ v1/account/assets/access_key =d3cce376-df3d-11e9-b535-   00163e041ee5&account=spot&timestamp =1569036224000&sign =  XYHrMTEPz8ToUozeTwjC0uUa9dNRq4Qnt6ZvO9tGwNg%3D

## REST API列表

- **备注：只有需要签名的私有接口，才需要timestamp 和access_key这两个参数，公共接口不需要进行签名和添加多余的参数，直接进行普通的请求即可。**



| API                                                 | 请求方式 | 接口类型 | 签名 | 说明                     |
| --------------------------------------------------- | -------- | -------- | ---- | ------------------------ |
| [/v1/timestamp](#查询系统时间戳)                    | GET      | 私有接口 | 是   | 查询系统时间戳           |
| [/v1/account/assets](#查询账户资产)                 | GET      | 私有接口 | 是   | 查询账户资产             |
| [/v1/orders/finished](#查询历史委托)                | GET      | 私有接口 | 是   | 查询历史委托             |
| [/v1/orders/open](#查询当前委托)                    | GET      | 私有接口 | 是   | 查询当前委托             |
| [/v1/orders/detail](#查询订单详情)                  | GET      | 私有接口 | 是   | 查询订单详情             |
| [/v1/orders/cancel_batch](#批量撤销订单)            | POST     | 私有接口 | 是   | 批量撤销订单             |
| [/v1/orders/cancel](#撤销订单)                      | POST     | 私有接口 | 是   | 撤销订单                 |
| [/v1/orders](#下单)                                 | POST     | 私有接口 | 是   | 下单                     |
| [/v1/orders/matchresults](#成交明细)                | GET      | 私有接口 | 是   | 成交明细                 |
| [/v1/market/tickers](#所有交易对的最新 Tickers)     | GET      | 公共接口 | 否   | 所有交易对的最新 Tickers |
| [/v1/market/kline](#k线历史数据)                    | GET      | 公共接口 | 否   | k线历史数据              |
| [/v1/market/price](#交易对最新成交价)               | GET      | 公共接口 | 否   | 交易对最新成交价         |
| [/v1/market/info](#交易对信息)                      | GET      | 公共接口 | 否   | 交易对信息               |
| [/v1/market/{symbol}/ticker](#单个交易对ticker信息) | GET      | 公共接口 | 否   | 单个交易对ticker信息     |

### 查询系统时间戳

#### GET/v1/timestamp

##### 请求参数: 无

**返回参数说明**

| 参数名 | 类型   | 说明       |
| :----- | :----- | ---------- |
| data   | string | 毫秒时间戳 |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": 1568975621808
}
```

### 查询账户资产

#### GET/v1/account/assets

**请求参数：**

| 参数名   | 是否必选 | 类型   | 说明                                              |
| :------- | :------- | :----- | ------------------------------------------------- |
| account  | 是       | string | spot=现货交易账户,mining=挖矿账户,wallet=资金账户 |
| currency | 否       | string | 币种 如：btc                                      |

**返回参数说明**

| 参数名    | 类型   | 说明 |
| :-------- | :----- | ---- |
| currency  | string | 币种 |
| available | string | 可用 |
| frozen    | string | 冻结 |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": [{
        "currency": "btc",
        "available": "0.016333420000",
        "frozen": "0.981538460000"
    }, {
        "currency": "roll",
        "available": "0.000000000000",
        "frozen": "0.000000000000"
    }, {
        "currency": "usdt",
        "available": "2054.358886340000",
        "frozen": "7754.102619250000"
    }]
}
```

### 查询历史委托

#### GET/v1/orders/finished

**请求参数：**

| 参数名 | 是否必选 | 类型   | 说明                                                 |
| :----- | :------- | :----- | ---------------------------------------------------- |
| symbol | 是       | string | 交易对，如btc-usdt                                   |
| from   | 否       | string | 查询起始 ID                                          |
| direct | 否       | string | 查询方向，prev=查询小于from id, next=查询大于from id |
| size   | 是       | string | 查询数量, 最多500                                    |

**返回参数说明**

| 参数名          | 类型   | 说明                              |
| :-------------- | :----- | --------------------------------- |
| order_id        | string | 订单号                            |
| type            | string | 买卖方向                          |
| trade_type      | string | 订单类型, limit=限价, market=市价 |
| quantity        | string | 数量                              |
| price           | string | 价格                              |
| filled_quantity | string | 成交量                            |
| filled_amount   | string | 成交额                            |
| created_at      | string | 委托时间                          |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": [{
        "order_id": 1543,
        "type": "buy",
        "trade_type": "limit",
        "quantity": "0.0299145300",
        "price": "10000.0000000000",
        "filled_quantity": "0.0000000000",
        "filled_amount": "0.0000000000",
        "created_at": 1568623772
    }, {
        "order_id": 1542,
        "type": "buy",
        "trade_type": "limit",
        "quantity": "0.0042735000",
        "price": "10000.0000000000",
        "filled_quantity": "0.0000000000",
        "filled_amount": "0.0000000000",
        "created_at": 1568623772
    }]
}
```

### 查询当前委托

#### GET/v1/orders/open

**请求参数：**

| 参数名 | 是否必选 | 类型   | 说明                                                 |
| :----- | :------- | :----- | ---------------------------------------------------- |
| symbol | 是       | string | 交易对，如btc-usdt                                   |
| from   | 否       | string | 查询起始 ID                                          |
| direct | 否       | string | 查询方向，prev=查询小于from id, next=查询大于from id |
| size   | 是       | string | 查询数量, 最多500                                    |

**返回参数说明**

| 参数名          | 类型   | 说明                              |
| :-------------- | :----- | --------------------------------- |
| order_id        | string | 订单号                            |
| type            | string | 买卖方向                          |
| trade_type      | string | 订单类型, limit=限价, market=市价 |
| quantity        | string | 数量                              |
| price           | string | 价格                              |
| filled_quantity | string | 成交量                            |
| filled_amount   | string | 成交额                            |
| created_at      | string | 委托时间                          |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": [{
        "order_id": 1543,
        "type": "buy",
        "trade_type": "limit",
        "quantity": "0.0299145300",
        "price": "10000.0000000000",
        "filled_quantity": "0.0000000000",
        "filled_amount": "0.0000000000",
        "created_at": 1568623772
    }, {
        "order_id": 1542,
        "type": "buy",
        "trade_type": "limit",
        "quantity": "0.0042735000",
        "price": "10000.0000000000",
        "filled_quantity": "0.0000000000",
        "filled_amount": "0.0000000000",
        "created_at": 1568623772
    }]
}
```

### 查询订单详情

#### GET/v1/orders/detail

**请求参数：**

| 参数名          | 是否必选 | 类型   | 说明               |
| :-------------- | :------- | :----- | ------------------ |
| symbol          | 是       | string | 交易对，如btc-usdt |
| order_id        | 否       | string | 订单号             |
| client_order_id | 否       | string | 用户自编订单号     |

**返回参数说明**

| 参数名          | 类型   | 说明                              |
| :-------------- | :----- | --------------------------------- |
| order_id        | string | 订单号                            |
| type            | string | 买卖方向                          |
| trade_type      | string | 订单类型, limit=限价, market=市价 |
| quantity        | string | 数量                              |
| price           | string | 价格                              |
| filled_quantity | string | 成交量                            |
| filled_amount   | string | 成交额                            |
| created_at      | string | 委托时间                          |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": {
        "order_id": 1768,
        "type": "sell",
        "trade_type": "limit",
        "quantity": "0.0256410300",
        "price": "90000.0256410000",
        "filled_quantity": "0.0000000000",
        "filled_amount": "0.0000000000",
        "created_at": 1568874311
    }
}
```

### 批量撤销订单

#### POST/v1/orders/cancel_batch

**请求参数：**

| 参数名          | 是否必选 | 类型   | 说明                                           |
| :-------------- | :------- | :----- | ---------------------------------------------- |
| symbol          | 是       | string | 交易对，如btc-usdt                             |
| order_id        | 否       | string | 订单号, 多个单号用半角英文逗号分隔， 如123,222 |
| client_order_id | 否       | string | 用户自编订单号, 多个单号用半角英文逗号分隔     |

**返回参数说明**

| 参数名          | 类型   | 说明           |
| :-------------- | :----- | -------------- |
| client_order_id | string | 用户自编订单号 |
| order_id        | string | 订单号         |
| success         | string | 撤销是否成功   |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": [{
        "client_order_id": "d41d8cd98f00b204e9800998ecf8427e",
        "success": false
    }, {
        "client_order_id": "ec7ec49bf591f1fc91bceeda3a1aaa76",
        "success": false
    }]
}
```

### 撤销订单

#### POST/v1/orders/cancel

**请求参数：**

| 参数名          | 是否必选 | 类型   | 说明               |
| :-------------- | :------- | :----- | ------------------ |
| symbol          | 是       | string | 交易对，如btc-usdt |
| order_id        | 否       | string | 订单号             |
| client_order_id | 否       | string | 用户自编订单号     |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": {
    }
}
```

### 下单

#### POST/v1/orders/

**	请求参数：**

| 参数名          | 是否必选 | 类型   | 说明                                                 |
| :-------------- | :------- | :----- | ---------------------------------------------------- |
| symbol          | 是       | string | 交易对，如btc-usdt                                   |
| price           | 是       | string | 限价交易价格, 市价交易固定为0                        |
| type            | 是       | string | buy=买, sell=卖                                      |
| trade_type      | 是       | string | limit=限价交易, market=市价交易                      |
| client_order_id | 是       | string | 用户自编订单号，不超过50字节，并确保同一交易对下唯一 |
| quantity        | 是       | string | 数量，市价交易买单为交易额                           |

**返回参数说明**

| 参数名          | 类型   | 说明           |
| :-------------- | :----- | -------------- |
| order_id        | string | 订单号         |
| client_order_id | string | 用户自编订单号 |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": {
        "order_id": 1776,
        "client_order_id": "f70862b35eb32d1ac358649526edbb2e"
    }
}
```

### 成交明细

#### GET/v1/orders/matchresults

**请求参数：**

| 参数名          | 是否必选 | 类型   | 说明         |
| :-------------- | :------- | :----- | ------------ |
| symbol          | 是       | string | 交易对       |
| order_id        | 否       | string | 订单号       |
| client_order_id | 否       | string | 用户自编单号 |

**返回参数说明**

| 参数名     | 类型   | 说明     |
| :--------- | :----- | -------- |
| quantity   | string | 成交数量 |
| type       | string | 成交方向 |
| created_at | string | 成交时间 |
| price      | string | 成交价   |
| amount     | string | 成交额   |
| amoufeent  | string | 手续费   |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": [{
        "quantity": "1.0000000000",
        "type": "buy",
        "created_at": 1566542186,
        "price": "100.0000000000",
        "amount": "100.0000000000",
        "fee": "0.00300000"
    }]
}
```

### 所有交易对的最新 Tickers

#### GET/v1/market/tickers

**请求参数：无**

**返回参数说明**

| 参数名 | 类型   | 说明       |
| :----- | :----- | ---------- |
| symbol | string | 交易对     |
| price  | string | 最新价格   |
| change | string | 24h 涨跌幅 |
| high   | string | 24h 最高价 |
| low    | string | 24h 最低价 |
| volume | string | 24h 成交量 |

**返回示例**

```Python
{
    "code": 200,
    "data": [
        {
            "change": "-15.86",
            "high": "65.9500",
            "low": "52.2400",
            "price": "55.1300",
            "symbol": "ltc-usdt",
            "volume": "1644351.41"
        },
        {
            "change": "-13.12",
            "high": "193.9000",
            "low": "143.3900",
            "price": "167.3800",
            "symbol": "eth-usdt",
            "volume": "1799503.21"
        },       
        {
            "change": "-12.21",
            "high": "9600.000000",
            "low": "7700.000000",
            "price": "8366.010000",
            "symbol": "btc-usdt",
            "volume": "111689.11"
        },
        {
            "change": "0.00",
            "high": "1.000000",
            "low": "1.000000",
            "price": "1.000000",
            "symbol": "mtl-usdt",
            "volume": "0.04"
        },
        {
            "change": "-5.94",
            "high": "0.00001",
            "low": "0.00001",
            "price": "0.00001",
            "symbol": "dat-eth",
            "volume": "67573877.16"
        }
    ],
    "msg": "success"
}
```

### k线历史数据

#### GET/v1/market/kline

**请求参数：**

| 参数名 | 必选 | 类型   | 说明                                   |
| :----- | :--- | :----- | -------------------------------------- |
| market | 是   | string | 交易对                                 |
| size   | 是   | int    | k线数据条数                            |
| type   | 是   | string | 类型(1min,5min,15min,30min,60min,1day) |

**返回参数说明**

| 参数名 | 类型  | 说明   |
| :----- | :---- | ------ |
| amount | float | 成交量 |
| high   | float | 最高价 |
| low    | float | 最低价 |
| open   | float | 开盘价 |
| close  | float | 收盘价 |
| time   | int   | 时间   |

**返回示例**

```python
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "amount": 0.34438127,
            "high": 10153.29,
            "low": 10153.23,
            "open": 10153.23,
            "close": 10153.29,
            "time": 1569032100
        },
        {
            "amount": 0.033643,
            "high": 10153.37,
            "low": 10153.37,
            "open": 10153.37,
            "close": 10153.37,
            "time": 1569032160
        }
    ]
}
```

### 交易对最新成交价

#### GET/v1/market/price

**请求参数：**

| 参数名 | 必选 | 类型   | 说明   |
| :----- | :--- | :----- | ------ |
| market | 否   | string | 交易对 |

**返回参数说明**

| 参数名 | 类型   | 说明           |
| :----- | :----- | -------------- |
| market | string | 交易对         |
| price  | string | 价格           |
| change | string | 涨跌幅         |
| amount | string | 成交量         |
| hight  | string | 最高           |
| low    | string | 最低           |
| time   | string | 时间           |
| cny    | string | 折合成法币价格 |

**返回示例**

```python
//单个交易对
  {
    "code": 200,
    "msg": "success",
    "data": {
        "market": "btc/usdt",
        "price": "10153.250000",
        "change": "-0.25",
        "amount": "15401.47",
        "hight": "10187.500000",
        "low": "10068.000000",
        "time": 1569032812,
        "cny": "72494.21"
    }
}
//所有交易对
{
    "code": 200,
    "msg": "success",
    "data": [
        {
            "market": "eth/usdt",
            "price": "219.4400",
            "change": "1.11",
            "amount": "406674.11",
            "hight": "221.3800",
            "low": "212.2600",
            "time": 1569032877,
            "cny": "1566.8016"
        },
        {
            "market": "mtl/btc",
            "price": "0.00",
            "change": "-0.46",
            "amount": "47333.33",
            "hight": "0.00",
            "low": "0.00",
            "time": 1569032875,
            "cny": "2.29"
        }
    ]
}
```

### 交易对信息

#### GET/v1/market/info

**请求参数：**

| 参数名 | 必选 | 类型   | 说明   |
| :----- | :--- | :----- | ------ |
| market | 否   | string | 交易对 |

**返回参数说明**

| 参数名            | 类型   | 说明           |
| :---------------- | :----- | -------------- |
| name              | string | 交易对         |
| min_number        | string | 最小挂单数量   |
| decimals_number   | int    | 数量精度       |
| decimals_price    | int    | 价格精度       |
| fee_buy           | string | 买入手续费比率 |
| fee_sell          | string | 卖出手续费比率 |
| decimals_currency | int    | 折合成法币精度 |

**返回示例**

```python
//所有交易对信息
 {
    "code": 200,
    "msg": "success",
    "data": [
        {
            "name": "mase/usdt",
            "min_number": "0.0000000000",
            "decimals_number": 4,
            "decimals_price": 4,
            "fee_buy": "0.002",
            "fee_sell": "0.002",
            "decimals_currency": 2
        },
        {
            "name": "mtl/btc",
            "min_number": "0.0000000000",
            "decimals_number": 2,
            "decimals_price": 2,
            "fee_buy": "0.003",
            "fee_sell": "0.002",
            "decimals_currency": 2
        }
    ]
}
//单个交易对信息
{
    "code": 200,
    "msg": "success",
    "data": {
        "name": "btc/usdt",
        "min_number": "0.0010000000",
        "decimals_number": 8,
        "decimals_price": 6,
        "fee_buy": "0.002",
        "fee_sell": "0.003",
        "decimals_currency": 2
    }
}
```

### 单个交易对ticker信息

#### GET/v1/market/{symbol}/ticker

**请求参数：**

| 参数名 | 是否必选 | 类型   | 说明                                  |
| :----- | :------- | :----- | ------------------------------------- |
| symbol | 是       | string | 交易对，如btc-usdt, 参数拼接到URI当中 |

**返回参数说明**

| 参数名 | 类型   | 说明       |
| :----- | :----- | ---------- |
| symbol | string | 交易对     |
| price  | string | 最新价格   |
| change | string | 24h 涨跌幅 |
| high   | string | 24h 最高价 |
| low    | string | 24h 最低价 |
| volume | string | 24h 成交量 |
| bid    | string | 买一价     |
| ask    | string | 卖一价     |

**返回示例**

```python
{{
    "code": 200,
    "msg": "success",
    "data": {
        "symbol": "btc-usdt",
        "price": "10144.00",
        "change": "2.67",
        "high": "10322.68",
        "low": "9875.23",
        "volume": "25848.12",
        "bid": "90000.00",
        "ask": "90000.01"
    }
}
```