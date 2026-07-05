# 可复现说明

## 源码与构建

Android 源码位于仓库 `app/` 目录。复现构建时进入 `app/` 后执行：

```powershell
.\gradlew.bat --console=plain :app:testDebugUnitTest :app:assembleDebug
```

该命令同时运行 Debug 单元测试并构建 Debug APK。Debug APK 通常生成在 `app/app/build/outputs/apk/debug/app-debug.apk`。WS7 不提交 APK；WS8 负责最终 APK 复制、命名和结构化 ZIP 打包。

## 证据路径

- 最终结构化日志：`logs/武汉-AI画质分析-袁嘉乐-最终验证日志.csv`
- 截图索引：`screenshots/武汉-AI画质分析-袁嘉乐-截图证据清单.md`
- 模拟器截图：`screenshots/模拟器/`
- Redmi K70E 真机代表截图：`screenshots/红米K70E/`
- 验证样本：`samples/`
- 样本来源：`samples/武汉-AI画质分析-袁嘉乐-样本来源说明.md`
- 验证结论：`deliverables/docs/武汉-AI画质分析-袁嘉乐-验证证据.md`
- 反例说明：`deliverables/docs/武汉-AI画质分析-袁嘉乐-反例分析.md`

## 追溯链路

评审可从 `app/` 源码构建 APK；用 APK 选择 `samples/` 中的样本；对照 `logs/武汉-AI画质分析-袁嘉乐-最终验证日志.csv` 查看设备、样本来源、尺寸、分析模式、耗时、子分和总分；再到 `screenshots/武汉-AI画质分析-袁嘉乐-截图证据清单.md` 查对应截图；最后阅读验证证据和反例文档，复核“代码 -> APK -> 样本 -> 日志 -> 截图 -> 文档结论”的链路。

## 边界

最终日志中包含 22 条模拟器记录和 10 条 Redmi K70E 代表真机记录。模拟器完成完整类别覆盖；Redmi K70E 用于证明真机可运行和关键样本可复现，不表示所有样本都来自 Redmi 自拍，也不表示所有样本都已在真机全量跑完。
