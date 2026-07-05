# 最终提交检查清单

## 包身份

- 提交文件夹：`武汉-AI画质分析-袁嘉乐/`
- 提交压缩包：`武汉-AI画质分析-袁嘉乐.zip`
- 主要提交形式：结构化 ZIP 包
- GitHub 发布：本轮提交不强制执行，ZIP 优先

## 打包前检查

- 打包前分支：`master`
- 打包前 `git status --short`：应为空
- 需要能在 `git log --oneline -8` 看到的近期关键提交：
  - 本轮中文化与旧日志清理提交（以当前最新提交为准）
  - `174748d docs: finalize contest documentation pack`
  - `1153675 docs: refresh final validation evidence`
  - `0dcd116 ui: update app branding and launcher icon`
  - `15ed55e tune: improve diagnostic explanations`

## 构建验证

在 `app/` 目录运行：

```powershell
.\gradlew.bat --console=plain :app:testDebugUnitTest :app:assembleDebug
```

- 验证目标：看到 `BUILD SUCCESSFUL`
- APK 来源：`app/app/build/outputs/apk/debug/app-debug.apk`
- 最终 APK 计划路径：`apk/AI画质分析-debug-final.apk`

## ZIP 应包含

- `app/` Android 源码项目，不包含 Gradle/build/IDE 缓存
- `apk/AI画质分析-debug-final.apk`
- `deliverables/README.md`
- `deliverables/docs/`
- `logs/final_validation_log.csv`
- `screenshots/README.md`
- `screenshots/模拟器/`
- `screenshots/红米K70E/`
- `samples/` 和 `samples/SOURCES.md`
- `openspec/` 规划与任务闭环证据

## ZIP 不应包含

- `.git/`
- `.gradle/`
- `app/.gradle/`
- 任意 `build/` 目录
- `tmp/`
- `.idea/` 等 IDE 缓存
- 旧 APK
- 无关临时文件

## 证据边界

- `logs/final_validation_log.csv` 是唯一最终结构化验证日志。
- 现有截图只复制，不改名、不改内容。
- 现有样本图片只复制，不修改。
- Redmi K70E 截图是代表真机验证证据。
- 外部/公共样本不能写成 Redmi K70E 自拍样本。

## 代码冻结

WS8 打包不修改 App 业务逻辑、评分算法、UI 实现、样本图片、截图或最终验证日志内容。
