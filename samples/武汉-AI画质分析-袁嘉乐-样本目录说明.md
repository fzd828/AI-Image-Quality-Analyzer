# 验证样本说明

本目录保存图片质量分析 App 的验证样本。当前最终提交使用的是可追溯的外部/公共样本和派生格式样本，不把这些样本写成 Redmi K70E 自拍样本。

完整来源、作者、许可、准备方式和人工预期见 `samples/武汉-AI画质分析-袁嘉乐-样本来源说明.md`。最终 App 分数、设备、截图和人工判断对比见 `logs/武汉-AI画质分析-袁嘉乐-最终验证日志.csv` 与 `deliverables/docs/武汉-AI画质分析-袁嘉乐-验证证据.md`。

## 目录用途

| 目录 | 用途 |
|---|---|
| `clear/` | 清晰样本，验证主体细节和正常画质评分。 |
| `blur/` | 模糊样本，包含失焦、运动模糊或长曝光运动场景。 |
| `overexposed/` | 过曝样本，验证高亮裁剪和曝光风险提示。 |
| `underexposed/` | 欠曝样本，验证暗部占比和低曝光风险提示。 |
| `noisy/` | 噪点样本，验证夜景、高 ISO、噪点与纹理混淆等局限。 |
| `counterexamples/` | 反例候选，用于说明规则式评分无法理解所有拍摄意图。 |
| `formats/` | JPEG、PNG、WebP 格式兼容样本。 |
| `large/` | 大图样本，用于验证安全降采样和分析耗时。 |
| `originals/` | 预留原始拍摄样本目录，本轮最终证据不依赖该目录。 |

## 最终样本覆盖

| 类别 | 当前样本数 | 是否满足比赛对比要求 | 说明 |
|---|---:|---|---|
| 清晰 | 3 | 是 | Wikimedia 外部样本。 |
| 模糊 | 3 | 是 | Wikimedia 外部样本，含失焦和运动模糊。 |
| 过曝 | 3 | 是 | Wikimedia/NASA 相关样本，最终反例包含 `overexposed_sky_02.jpg`。 |
| 欠曝 | 3 | 是 | Wikimedia/NASA 相关样本。 |
| 噪点 | 3 | 是 | Wikimedia/NIND 样本，最终反例包含 `noisy_hong_kong_night_01.jpg`。 |
| 格式 | 3 | 是 | 由清晰样本派生出 JPEG、PNG、WebP。 |
| 反例候选 | 3 | 是 | 用于说明夜景、散景、彩色灯光、日落等主观场景局限。 |
| 大图 | 1 | 是 | NASA 5400 x 2700 大图，用于验证降采样。 |

## 证据边界

- 样本来源字段和运行设备字段不同：样本可以来自 Wikimedia/NIND/NASA，运行设备可以是 Android Emulator 或 Redmi K70E。
- Redmi K70E 截图是代表真机运行证据，不表示所有样本都由 Redmi 自拍，也不表示所有样本都已真机全量跑完。
- 手机截图生成的 JPEG 副本只用于证明 Redmi 端 JPEG 可选取、预览和分析，不作为 repo 原始 JPEG 样本的同图对比。
- `logs/武汉-AI画质分析-袁嘉乐-最终验证日志.csv` 是唯一最终结构化验证日志。

## 文件命名

样本文件使用小写英文路径和类别前缀，便于 Android、脚本和 ZIP 打包稳定处理。例如：

```text
clear_butterfly_01.jpg
blur_out_of_focus_03.jpg
overexposed_sky_02.jpg
underexposed_sun_01.jpg
noisy_hong_kong_night_01.jpg
format_clear_webp_01.webp
large_blue_marble_january_01.jpg
```

## 与文档的对应关系

- 样本来源：`samples/武汉-AI画质分析-袁嘉乐-样本来源说明.md`
- 最终验证数据：`logs/武汉-AI画质分析-袁嘉乐-最终验证日志.csv`
- 截图索引：`screenshots/武汉-AI画质分析-袁嘉乐-截图证据清单.md`
- 验证结论：`deliverables/docs/武汉-AI画质分析-袁嘉乐-验证证据.md`
- 反例分析：`deliverables/docs/武汉-AI画质分析-袁嘉乐-反例分析.md`
