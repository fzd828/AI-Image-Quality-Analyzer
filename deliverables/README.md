# Image Quality Analyzer

## 项目目标

本项目是 ISBG 2026 AI 作业大赛选题一的 Android 本地图片质量分析工具。App 使用 Android 原生 Kotlin + Jetpack Compose 实现，支持用户从本地选择图片、预览图片与基础元数据，并用可解释的传统计算机视觉指标输出画质子分、0-100 综合分和诊断说明。

标题中的 "AI" 对齐原题“AI 图像识别与画质分析工具”，但本项目的核心承诺是本地、可复现、可解释的画质分析。AI 主要参与需求拆解、算法选择、代码草拟、诊断文案和文档整理，不表示接入云端大模型或部署深度学习识别模型。

## 非目标

- 不做云端 AI API、账号系统或联网服务。
- 不部署深度学习模型，不伪称具备语义识别能力。
- 不做完整相册管理、批量图片管理或复杂图表。
- 不支持 RAW；HEIC 只做兼容性说明，不作为最低交付格式。
- 不把规则式评分包装成专业摄影标准。

## 完成判据

- 可以选择本地图片，取消选择时保持稳定。
- 可以预览图片，并显示格式、尺寸、文件大小等基础信息。
- 核心支持 JPEG、PNG、WebP 的选取、预览和分析。
- 大图会按分析模式安全降采样，记录原始尺寸、分析尺寸和是否降采样，避免 OOM。
- 输出清晰度、曝光、对比度、偏色/噪点风险等子分，以及 0-100 综合分和可读诊断。
- 提供最终结构化验证日志、模拟器截图、Redmi K70E 代表真机截图、人工判断对比和反例分析。

## 构建与运行

Android 源码位于仓库的 `app/` 目录。已配置 Gradle wrapper 后，可在 `app/` 目录执行：

```powershell
.\gradlew.bat --console=plain :app:testDebugUnitTest :app:assembleDebug
```

该命令会运行 Debug 单元测试并构建 Debug APK。构建产物通常位于 `app/app/build/outputs/apk/debug/app-debug.apk`。WS7 只负责比赛必交文档包；最终 APK 复制、命名和结构化 ZIP 由 WS8 生成。

运行方式：

- 用 Android Studio 打开 `app/`，选择模拟器或真机运行。
- 或在构建 APK 后通过 Android Studio / adb 安装到设备。

## 最终包结构

最终提交以结构化 ZIP 为主，GitHub 发布为可选项。WS8 会根据本结构打包：

```text
app/                         Android 源码
apk/                         WS8 生成的最终 APK
deliverables/README.md       本说明
deliverables/docs/           必交说明文档
samples/                     验证样本
logs/final_validation_log.csv 最终结构化验证记录
screenshots/                 模拟器与 Redmi K70E 截图
```

## 必交物索引

| 必交物 | 对应文件 |
|---|---|
| README：目标 / 非目标 / 完成判据 | `deliverables/README.md` |
| 约束与决策说明 | `deliverables/docs/constraints-and-decisions.md` |
| 验证证据：日志 / 截图 / 数据 / 对比 | `deliverables/docs/validation-evidence.md`, `logs/final_validation_log.csv`, `screenshots/README.md` |
| AI 协作说明 | `deliverables/docs/ai-collaboration.md` |
| 可复现代码仓库说明 | `deliverables/docs/reproducibility.md` |

补充说明：

- 算法与局限：`deliverables/docs/algorithm-notes.md`
- 反例分析：`deliverables/docs/counterexamples.md`
- 评分维度对齐：`deliverables/docs/scoring-alignment.md`

## 证据边界

- `logs/final_validation_log.csv` 是最终结构化记录；旧的 `logs/analysis_log.csv` 不作为最终证据。
- `screenshots/模拟器/` 是 Android Emulator `Medium_Phone` / Android API 36.1 的最终截图。
- `screenshots/红米K70E/` 是 Redmi K70E 真机代表验证截图。
- Wikimedia / NIND / NASA 样本是外部或公共样本，不是 Redmi K70E 自拍样本。
- Redmi 端 JPEG 兼容性截图是手机截图/保存流程生成的 JPEG 副本，只证明真机可选取、预览和分析 JPEG。

## 可追溯链路

评审可以按以下顺序复核：

1. 代码：`app/`
2. 构建：`.\gradlew.bat --console=plain :app:testDebugUnitTest :app:assembleDebug`
3. APK：WS8 生成并放入 `apk/`
4. 样本：`samples/`
5. 日志：`logs/final_validation_log.csv`
6. 截图：`screenshots/README.md`
7. 结论：`deliverables/docs/validation-evidence.md` 和 `deliverables/docs/counterexamples.md`
