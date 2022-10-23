# 配置

## [小程序配置app.json](https://developers.weixin.qq.com/miniprogram/dev/framework/config.html#%E5%85%A8%E5%B1%80%E9%85%8D%E7%BD%AE)

小程序根目录下的`app.json`文件用来对微信小程序进行全局配置。文件内容作为一个`JSON`对象，有以下属性

### 配置项目

| 属性 | 类型 | 必填 | 描述 | 最低版本 |
|:----:|:----:|:----:|:----:|:----:|
| [entryPagePath](#entryPagePath) | string | 否 | 小程序默认启动首页 | - |
| [pages](#pages) | string[] | 是 | 页面路径列表 | - |
| [window](#window) | object | 否 | 全局的默认窗口表现 | - |

#### entryPagePath

指定小程序的默认启动路径（首页），常见情景是从微信聊天列表页下拉启动、小程序列表启动等。如果不填将默认为`pages`列表的第一项。不支持带页面路径参数。

```json
{
  "entryPagePath": "pages/index/index"
}
```

> pages

用于指定小程序由哪些页面组成，每一项都对应一个页面的路径（含文件名）信息。文件名不需要写文件后缀，框架会自动去寻找对应位置的`.json`,`.wxml`,`.wxss`四个文件进行处理。

未指定`entryPagePath`时，数组的第一项代表小程序的初始页面（首页）。

> 小程序中新增/减少页面，都需要对pages数组进行修改

如开发目录为:

```
├── app.js
├── app.json
├── app.wxss
├── pages
│   │── index
│   │   ├── index.wxml
│   │   ├── index.js
│   │   ├── index.json
│   │   └── index.wxss
│   └── logs
│       ├── logs.wxml
│       └── logs.js
└── utils
```

则需要在`app.json`中写

```json
{
  "pages": ["pages/index/index", "pages/logs/logs"]
}
```

#### window

> 定义小程序所有页面的顶部北京颜色，文字颜色定义等

## [工具配置project.config.json](https://developers.weixin.qq.com/miniprogram/dev/devtools/projectconfig.html)

| 属性 | 类型 | 默认值 | 描述 | 最低版本 |
|:----:|:----:|:----:|:----:|:----:|
| navigationBarBackgroundColor | HexColor | #000000 | 导航栏背景颜色, 如`#000000` | - |
| navigationBarTextStyle | string | white | 导航栏标题颜色，仅支持`black`/`white` | - |
| navigationBarTitleText | string | - | 导航栏标题文字内容 | - |
| navigationStyle | string | default | 导航栏样式，仅支持以下值：`default`默认样式`custom`自定义导航栏，只保留右上角叫浪按钮。参见注2 | iOS/Android 微信客户端 6.6.0，Windows 微信客户端不支持 |
| homeButton | boolean | default | 在非首页、非页面栈最底层页面或非tabbar内页面中的导航栏展示home键 | 微信客户端 8.0.24 |
| backgroundColor | HexColor | #ffffff | 窗口的背景色 | - |
| backgroundTextStyle | string | dark | 下拉loading的样式，仅支持`dark`/`light` | - |
| backgroundColorTop | string | #ffffff | 顶部窗口的背景色, 仅iOS支持 | 微信客户端 6.5.16 |
| backgroundColorBottom | string | #ffffff | 底部窗口的背景色, 仅iOS支持 | 微信客户端 6.5.16 |
| enablePullDownRefresh | boolean | false | 是否开启全局的下拉刷新。
详情见[Page.onPullDownRefresh](https://developers.weixin.qq.com/miniprogram/dev/reference/api/Page.html#onpulldownrefresh) | - |
| onReachBottomDistance | number | 50 | 页面上拉触底时间触发时距页面底部距离，单位为px。
详情见[Page.onReachBottom](https://developers.weixin.qq.com/miniprogram/dev/reference/api/Page.html#onreachbottom) | - |
| pageOrientation | string | portrait | 屏幕旋转设置，支持 `auto` / `portrait` / `landscape`
详情见[响应显示区域变化](https://developers.weixin.qq.com/miniprogram/dev/framework/view/resizable.html) | 2.4.0 (auto) / 2.5.0 (landscape) |
| restartStrategy | string | portrait | 屏幕旋转设置，支持 `auto` / `portrait` / `landscape`
详情见[响应显示区域变化](https://developers.weixin.qq.com/miniprogram/dev/framework/view/resizable.html) | 2.4.0 (auto) / 2.5.0 (landscape) |

