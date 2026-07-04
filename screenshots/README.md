# 截图证据清单

本目录保存最终验证证据截图。截图按设备分目录管理，避免把模拟器证据和 Redmi K70E 真机证据混在一起。

重要说明：

- `模拟器/`：Android Emulator `Medium_Phone`，Android API 36.1。
- `红米K70E/`：Redmi K70E 真机截图。
- 当前最终验证统一使用 App 内的 `精细模式 2560`，启动页除外。
- 结构化分数、人工判断和截图对应关系记录在 `logs/final_validation_log.csv`。
- 不要把 Wikimedia / NIND 外部样本写成 Redmi K70E 自拍样本；它们只是样本来源，设备字段表示运行 App 的设备。

## 模拟器最终截图

| 类别 | 截图文件 | 对应样本 |
|---|---|---|
| 启动页 | `模拟器/模拟器_最终_启动页.png` | App 首页 |
| 清晰 | `模拟器/模拟器_最终_清晰_蝴蝶01_结果.png` | `samples/clear/clear_butterfly_01.jpg` |
| 清晰 | `模拟器/模拟器_最终_清晰_甲虫蜘蛛02_结果.png` | `samples/clear/clear_beetle_spider_02.jpg` |
| 清晰 | `模拟器/模拟器_最终_清晰_蝴蝶03_结果.png` | `samples/clear/clear_butterfly_03.jpg` |
| 模糊 | `模拟器/模拟器_最终_模糊_失焦03_结果.png` | `samples/blur/blur_out_of_focus_03.jpg` |
| 模糊 | `模拟器/模拟器_最终_模糊_地铁运动01_结果.png` | `samples/blur/blur_subway_motion_01.jpg` |
| 模糊 | `模拟器/模拟器_最终_模糊_旋舞运动02_结果.png` | `samples/blur/blur_tanoura_motion_02.jpg` |
| 过曝 | `模拟器/模拟器_最终_过曝_村庄照片01_结果.png` | `samples/overexposed/overexposed_photo_01.jpg` |
| 过曝 | `模拟器/模拟器_最终_过曝_火车天空02_结果.png` | `samples/overexposed/overexposed_sky_02.jpg` |
| 过曝 | `模拟器/模拟器_最终_过曝_冲洗胶片03_结果.png` | `samples/overexposed/overexposed_space_washed_03.jpg` |
| 欠曝 | `模拟器/模拟器_最终_欠曝_云中太阳01_结果.png` | `samples/underexposed/underexposed_sun_01.jpg` |
| 欠曝 | `模拟器/模拟器_最终_欠曝_暗绿空间02_结果.png` | `samples/underexposed/underexposed_space_02.jpg` |
| 欠曝 | `模拟器/模拟器_最终_欠曝_花粉03_结果.png` | `samples/underexposed/underexposed_pollen_03.jpg` |
| 噪点 | `模拟器/模拟器_最终_噪点_香港夜景01_结果.png` | `samples/noisy/noisy_hong_kong_night_01.jpg` |
| 噪点 | `模拟器/模拟器_最终_噪点_街边雕像02_结果.png` | `samples/noisy/noisy_nind_stefantiek_02.jpg` |
| 噪点 | `模拟器/模拟器_最终_噪点_键盘03_结果.png` | `samples/noisy/noisy_nind_keyboard_03.jpg` |
| 格式 | `模拟器/模拟器_最终_格式_JPEG_蝴蝶01_结果.png` | `samples/formats/format_clear_jpeg_01.jpg` |
| 格式 | `模拟器/模拟器_最终_格式_PNG_蝴蝶01_结果.png` | `samples/formats/format_clear_png_01.png` |
| 格式 | `模拟器/模拟器_最终_格式_WebP_蝴蝶01_结果.png` | `samples/formats/format_clear_webp_01.webp` |
| 反例 | `模拟器/模拟器_最终_反例_散景车内01_结果.png` | `samples/counterexamples/counterexample_bokeh_01.jpg` |
| 反例 | `模拟器/模拟器_最终_反例_彩色灯光02_结果.png` | `samples/counterexamples/counterexample_colored_lights_02.jpg` |
| 反例 | `模拟器/模拟器_最终_反例_日落峡谷03_结果.png` | `samples/counterexamples/counterexample_sunset_03.jpg` |
| 大图 | `模拟器/模拟器_最终_大图_蓝色星球01_结果.png` | `samples/large/large_blue_marble_january_01.jpg` |

## Redmi K70E 真机截图

真机无法稳定长截图时，预览信息和结果信息拆成两张截图保存：`预览` 用于证明样本图和格式，`结果` 用于记录评分。

| 类别 | 预览截图 | 结果截图 | 对应样本 |
|---|---|---|---|
| 启动页 | `红米K70E/红米K70E_最终_启动页.jpg` | - | App 首页 |
| 清晰 | `红米K70E/红米K70E_最终_清晰_蝴蝶03_预览.jpg` | `红米K70E/红米K70E_最终_清晰_蝴蝶03_结果.jpg` | `samples/clear/clear_butterfly_03.jpg` |
| 模糊 | `红米K70E/红米K70E_最终_模糊_旋舞运动02_预览.jpg` | `红米K70E/红米K70E_最终_模糊_旋舞运动02_结果.jpg` | `samples/blur/blur_tanoura_motion_02.jpg` |
| 过曝 | `红米K70E/红米K70E_最终_过曝_火车天空02_预览.jpg` | `红米K70E/红米K70E_最终_过曝_火车天空02_结果.jpg` | `samples/overexposed/overexposed_sky_02.jpg` |
| 欠曝 | `红米K70E/红米K70E_最终_欠曝_云中太阳01_预览.jpg` | `红米K70E/红米K70E_最终_欠曝_云中太阳01_结果.jpg` | `samples/underexposed/underexposed_sun_01.jpg` |
| 噪点 | `红米K70E/红米K70E_最终_噪点_香港夜景01_预览.jpg` | `红米K70E/红米K70E_最终_噪点_香港夜景01_结果.jpg` | `samples/noisy/noisy_hong_kong_night_01.jpg` |
| 格式 | `红米K70E/红米K70E_最终_格式_JPEG_蝴蝶01_预览.jpg` | `红米K70E/红米K70E_最终_格式_JPEG_蝴蝶01_结果.jpg` | 手机截图生成的 JPEG 副本，非 repo 原始 JPEG 样本 |
| 格式 | `红米K70E/红米K70E_最终_格式_PNG_蝴蝶01_预览.jpg` | `红米K70E/红米K70E_最终_格式_PNG_蝴蝶01_结果.jpg` | `samples/formats/format_clear_png_01.png` |
| 格式 | `红米K70E/红米K70E_最终_格式_WebP_蝴蝶01_预览.jpg` | `红米K70E/红米K70E_最终_格式_WebP_蝴蝶01_结果.jpg` | `samples/formats/format_clear_webp_01.webp` |
| 反例 | `红米K70E/红米K70E_最终_反例_日落峡谷03_预览.jpg` | `红米K70E/红米K70E_最终_反例_日落峡谷03_结果.jpg` | `samples/counterexamples/counterexample_sunset_03.jpg` |
| 大图 | `红米K70E/红米K70E_最终_大图_蓝色星球01_预览.jpg` | `红米K70E/红米K70E_最终_大图_蓝色星球01_结果.jpg` | `samples/large/large_blue_marble_january_01.jpg` |

## 已替换 / 删除的旧截图

以下旧截图已由用户删除，不再作为最终证据引用：

- `emulator_noisy_hong_kong_night_result.png`
- `emulator_noisy_nind_keyboard_result.png`
- `emulator_noisy_nind_stefantiek_result.png`

对应的新截图见上方 `模拟器/` 中文命名的噪点组。
