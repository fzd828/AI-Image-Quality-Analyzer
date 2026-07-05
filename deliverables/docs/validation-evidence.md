# 验证证据

本文记录最终版 App 的验证证据。完整逐样本结构化记录见 `logs/final_validation_log.csv`，截图清单见 `screenshots/README.md`。

## 证据文件

- `logs/final_validation_log.csv`：最终验证日志，包含设备、样本路径、格式、来源、人工判断、分析模式、原始尺寸、分析尺寸、是否降采样、分析耗时、四项子分、综合分、匹配状态、局限说明、截图文件名。
- `screenshots/README.md`：最终截图索引，按 `模拟器/` 和 `红米K70E/` 分开列出。
- `screenshots/模拟器/`：Android Emulator `Medium_Phone` / Android API 36.1 的最终截图。
- `screenshots/红米K70E/`：Redmi K70E 真机截图。真机无法稳定长截图时，预览页和结果页拆成两张截图保存。

## 四类证据怎么读

比赛要求里的“日志 / 截图 / 数据 / 对比”在本项目中分别对应不同材料：

| 证据类型 | 对应文件 | 评审应该看什么 |
|---|---|---|
| 日志 | `logs/final_validation_log.csv` | 最完整的逐样本原始记录。每一行对应一次验证，包含设备、样本、格式、来源、尺寸、是否降采样、耗时、四项子分、综合分、匹配状态和截图路径。 |
| 截图 | `screenshots/README.md`、`screenshots/模拟器/`、`screenshots/红米K70E/` | 截图索引和实际运行画面。用于证明 App 不是只在文档里存在，而是在模拟器和 Redmi K70E 上完成选图、预览和分析。 |
| 数据 | 本文的“样本覆盖”“模拟器完整验证摘要”“Redmi K70E 真机验证摘要” | 从 CSV 中整理出的评审友好表格，展示类别覆盖、格式覆盖、大图处理、总分和主要说明。 |
| 对比 | 本文的“人工判断 / 综合分 / 匹配状态 / 主要说明”列，以及 `deliverables/docs/counterexamples.md` | 对比人工判断和 App 输出是否一致。`Match` 表示基本一致，`Partial` 表示部分一致，`Mismatch` 表示明显不一致；反例文档进一步解释为什么会错。 |

## 设备与边界

本轮证据明确区分运行设备和样本来源：

- 模拟器证据：来自 Android Emulator `Medium_Phone`，Android API 36.1。
- 真机证据：来自 Redmi K70E，属于代表样本真机验证，不等于所有样本都由 Redmi 全量跑完。
- 样本来源：多数样本来自 Wikimedia Commons 或 NIND 等外部样本集；它们不是 Redmi K70E 自拍样本。
- Redmi JPEG 格式截图：该条是手机截图/保存流程生成的 JPEG 副本，App 显示尺寸为 `1364 x 1024`，只用于证明 Redmi 端 JPEG 可选取、预览和分析；它不作为 repo 原始 `samples/formats/format_clear_jpeg_01.jpg` 的同图跨设备对比。

除启动页外，最终验证统一使用 App 内 `精细模式 2560`。

## 匹配状态说明

表格里的 `Match`、`Partial`、`Mismatch` 表示“App 评分结果”和“人工判断”之间的一致程度：

- `Match`：基本一致。人工判断和 App 总分、子分、诊断方向大体相符。
- `Partial`：部分一致。App 抓到了一部分问题，或总分大致可用，但仍有需要人工解释的偏差。
- `Mismatch`：明显不一致。人工认为存在清晰问题、噪点、曝光等风险，但 App 给出的分数或诊断方向明显偏乐观或偏悲观。

例如：`clear_butterfly_01.jpg` 人工看是清晰但颜色偏暖，App 分数可用但提示偏色风险，所以标为 `Partial`；`clear_beetle_spider_02.jpg` 主体纹理清晰，App 也给出较高分，所以标为 `Match`。

## 样本覆盖

比赛要求“清晰 / 模糊 / 过曝 / 欠曝 / 噪点等各 2-3 张，并给出评分结果与人工判断的对比”。当前完整样本覆盖由模拟器完成，Redmi K70E 作为真机代表验证补充；这能证明 App 在真机上可运行并复现关键样本，但不把 Redmi 截图包装成全量样本来源。

| 类别 | 模拟器截图 | Redmi K70E 截图 | 说明 |
|---|---:|---:|---|
| 清晰 | 3 | 1 | 满足 2-3 张样本要求；真机补充 1 张代表样本。 |
| 模糊 | 3 | 1 | 满足 2-3 张样本要求。 |
| 过曝 | 3 | 1 | 满足 2-3 张样本要求；最终反例包含 1 张过曝样本。 |
| 欠曝 | 3 | 1 | 满足 2-3 张样本要求。 |
| 噪点 | 3 | 1 | 满足 2-3 张样本要求；最终反例包含 1 张噪点夜景样本。 |
| 格式 JPEG / PNG / WebP | 3 | 3 | 证明三种核心格式可选取、预览和分析。 |
| 反例 | 2 个最终采用 | 2 个真机复现 | 最终采用 `overexposed_sky_02` 和 `noisy_hong_kong_night_01`。 |
| 大图 | 1 | 1 | 验证大图安全缩放和分析流程。 |

噪点类别不需要再强制补 Redmi 第二张：样本层面已有 3 张噪点结果；Redmi 的 1 张噪点是额外真机运行证据。如果后续想强化真机证据，可以再补 `noisy_nind_keyboard_03.jpg` 或 `noisy_nind_stefantiek_02.jpg`，但它不是满足比赛样本数量的硬性缺口。

## 最终反例

最终采用两个反例，详见 `deliverables/docs/counterexamples.md`：

| 反例 | 人工判断 | App 关键结果 | 为什么选它 |
|---|---|---|---|
| `samples/overexposed/overexposed_sky_02.jpg` | 天空和地面高亮区域明显偏白，存在局部过曝。 | 综合分 80/100，曝光 42/100，但清晰度 95、对比度 100、偏色 95。 | App 发现过曝风险，但综合分仍偏高，说明曝光问题被其他高分项稀释。 |
| `samples/noisy/noisy_hong_kong_night_01.jpg` | 夜景暗部和纹理区域噪点明显。 | 综合分 67/100，清晰度 93/100，对比度 100/100。 | 噪点和霓虹/建筑边缘被当成高频细节，说明缺少独立噪点指标。 |

这两个反例比运动模糊、艺术散景等样本更适合最终说明，因为它们更少依赖审美争议，截图中问题也更容易看懂。

## 模拟器完整验证摘要

| 样本 | 人工判断 | 综合分 | 匹配状态 | 主要说明 |
|---|---|---:|---|---|
| `samples/clear/clear_butterfly_01.jpg` | 清晰，颜色偏暖 | 69 | Partial | 可用但提示偏色风险。 |
| `samples/clear/clear_beetle_spider_02.jpg` | 主体纹理清晰 | 79 | Match | 高分符合人工判断。 |
| `samples/clear/clear_butterfly_03.jpg` | 主体清晰，色彩偏暖 | 82 | Partial | 高分合理，偏色需人工说明。 |
| `samples/blur/blur_out_of_focus_03.jpg` | 明显失焦 | 42 | Match | 清晰度仅 1/100。 |
| `samples/blur/blur_subway_motion_01.jpg` | 运动模糊但有强边缘 | 90 | Mismatch | 高对比和局部边缘导致过高分，作为局限样本保留。 |
| `samples/blur/blur_tanoura_motion_02.jpg` | 夜间长曝光运动模糊 | 39 | Match | 曝光和综合分均较低。 |
| `samples/overexposed/overexposed_photo_01.jpg` | 高亮区域偏多 | 50 | Match | App 识别过曝和清晰度风险。 |
| `samples/overexposed/overexposed_sky_02.jpg` | 天空局部过曝 | 80 | Partial | 最终反例：诊断发现过曝，但综合分仍偏高。 |
| `samples/overexposed/overexposed_space_washed_03.jpg` | 明显偏亮/冲洗感 | 43 | Match | 曝光分为 0。 |
| `samples/underexposed/underexposed_sun_01.jpg` | 暗部占比高 | 30 | Match | 欠曝和低清晰度风险明显。 |
| `samples/underexposed/underexposed_space_02.jpg` | 暗绿低对比场景 | 41 | Partial | 低对比被识别，曝光项不完全等同人工观感。 |
| `samples/underexposed/underexposed_pollen_03.jpg` | 暗背景欠曝 | 34 | Match | 曝光分为 0。 |
| `samples/noisy/noisy_hong_kong_night_01.jpg` | 夜景噪点明显 | 67 | Partial | 最终反例：清晰度和对比度偏高，噪点没有独立扣分。 |
| `samples/noisy/noisy_nind_stefantiek_02.jpg` | 高 ISO 噪点，细节较多 | 78 | Partial | 总分偏高，噪点未被强惩罚。 |
| `samples/noisy/noisy_nind_keyboard_03.jpg` | 高 ISO 键盘噪点 | 87 | Mismatch | 噪点和硬边缘抬高清晰度，作为局限样本保留。 |
| `samples/formats/format_clear_jpeg_01.jpg` | JPEG 兼容样本 | 69 | Match | JPEG 可选取、预览、分析。 |
| `samples/formats/format_clear_png_01.png` | PNG 兼容样本 | 69 | Match | PNG 可选取、预览、分析。 |
| `samples/formats/format_clear_webp_01.webp` | WebP 兼容样本 | 68 | Match | WebP 可选取、预览、分析。 |
| `samples/counterexamples/counterexample_bokeh_01.jpg` | 有意低光散景，人眼可接受 | 24 | Mismatch | 涉及艺术意图，作为局限样本保留。 |
| `samples/counterexamples/counterexample_colored_lights_02.jpg` | 彩色灯光场景 | 49 | Partial | 特殊灯光和偏色影响评分。 |
| `samples/counterexamples/counterexample_sunset_03.jpg` | 暖色日落，观感较好 | 87 | Match | App 高分，仍需说明暖色场景不能只靠偏色判断。 |
| `samples/large/large_blue_marble_january_01.jpg` | 大图安全缩放验证 | 65 | Match | 从 `5400 x 2700` 缩放到 `2560 x 1280` 后完成分析。 |

## Redmi K70E 真机验证摘要

| 样本或来源 | 人工判断 | 综合分 | 匹配状态 | 主要说明 |
|---|---|---:|---|---|
| `samples/clear/clear_butterfly_03.jpg` | 主体清晰，色彩偏暖 | 82 | Partial | 真机结果与模拟器一致。 |
| `samples/blur/blur_tanoura_motion_02.jpg` | 夜间长曝光运动模糊 | 39 | Match | 真机结果与模拟器一致。 |
| `samples/overexposed/overexposed_sky_02.jpg` | 天空局部过曝 | 80 | Partial | 真机复现最终反例：局部高光问题仍被总分弱化。 |
| `samples/underexposed/underexposed_sun_01.jpg` | 暗部占比高 | 30 | Match | 真机结果与模拟器一致。 |
| `samples/noisy/noisy_hong_kong_night_01.jpg` | 夜景噪点明显 | 67 | Partial | 真机复现最终反例：噪点样本清晰度偏高。 |
| 手机截图生成 JPEG 副本 | JPEG 兼容性样本 | 69 | Partial | 尺寸为 `1364 x 1024`，不作为 repo 原始 JPEG 同图对比。 |
| `samples/formats/format_clear_png_01.png` | PNG 兼容样本 | 69 | Match | 真机 PNG 可选取、预览、分析。 |
| `samples/formats/format_clear_webp_01.webp` | WebP 兼容样本 | 68 | Match | 真机 WebP 可选取、预览、分析。 |
| `samples/counterexamples/counterexample_sunset_03.jpg` | 暖色日落，观感较好 | 87 | Match | 真机结果与模拟器一致。 |
| `samples/large/large_blue_marble_january_01.jpg` | 大图安全缩放验证 | 65 | Match | 真机完成大图缩放和分析。 |

## 人工判断与 App 评分对比结论

整体上，App 对普通失焦、明显欠曝、明显过曝、大图缩放和格式兼容的判断较稳定；对局部过曝、夜景噪点、高纹理和特殊光线场景存在局限。

主要匹配情况：

- 清晰样本通常得分较高，但自然色彩偏暖时会触发偏色风险。
- 明显失焦和暗部占比高的样本通常得分较低。
- JPEG、PNG、WebP 均已完成选取、预览和分析验证。
- 大图样本能安全降采样到精细模式上限内完成分析。

主要局限：

- 局部过曝可能被清晰度、对比度等高分项稀释，导致综合分偏高。
- 夜景噪点可能被边缘/纹理指标误当成清晰细节，导致清晰度和对比度偏高。
- 运动模糊中的站台线条、高对比结构会抬高清晰度得分。
- App 当前没有独立噪点分，因此噪点样本只能作为风险提示和反例说明，不能证明已经具备专业噪声检测能力。
- 艺术散景、夜景氛围、暖色日落等主观审美场景无法只靠清晰度、曝光、对比、偏色四项指标完全判断。

完整逐项数据以 `logs/final_validation_log.csv` 为准。
