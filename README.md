# AI 图像质量分析工具

本仓库是 ISBG 2026 AI 作业大赛选题一的 Android 本地图像质量分析项目，用于展示可复现的代码、文档、样本、截图、日志和验证证据。

项目使用 Android 原生 Kotlin + Jetpack Compose 实现。App 支持从本地选择图片、预览图片和基础元数据，并通过安全降采样避免大图 OOM；分析结果包含清晰度、曝光、对比度、偏色/噪点风险等可解释指标，以及 0-100 综合质量分和诊断说明。

一句话概括：这是一个本地运行、可复现、带完整验证证据的 Android 图像质量分析工具。

## 推荐阅读顺序

- 比赛交付说明：[deliverables/README.md](deliverables/README.md)
- 可复现说明：[deliverables/docs/reproducibility.md](deliverables/docs/reproducibility.md)
- 算法说明：[deliverables/docs/algorithm-notes.md](deliverables/docs/algorithm-notes.md)
- 验证证据：[deliverables/docs/validation-evidence.md](deliverables/docs/validation-evidence.md)
- 反例分析：[deliverables/docs/counterexamples.md](deliverables/docs/counterexamples.md)
- 最终验证日志：[logs/final_validation_log.csv](logs/final_validation_log.csv)

## 仓库结构

- `app/`：Android 源码工程
- `apk/`：最终提交包中的 Debug APK 副本
- `deliverables/`：面向比赛评审的交付文档
- `samples/`：验证样本和样本来源说明
- `screenshots/`：Android Emulator 和 Redmi K70E 的运行截图证据
- `logs/`：结构化验证记录
- `openspec/`：需求提案、设计、规格和任务追踪

## 构建与验证

进入 `app/` 目录后执行：

```powershell
.\gradlew.bat --console=plain :app:testDebugUnitTest :app:assembleDebug
```

该命令会运行 Debug 单元测试并构建 Debug APK。通常 APK 输出路径为：

```text
app/app/build/outputs/apk/debug/app-debug.apk
```

## 提交形态说明

本项目的主要比赛提交物仍然是结构化 ZIP 包；GitHub 仓库用于提供可复现的代码和证据镜像。根目录最终 ZIP 及临时构建缓存不会强行提交到仓库。
