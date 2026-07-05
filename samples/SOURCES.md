# 样本来源清单

本文记录 `samples/` 中外部对比样本的来源、许可、准备方式、人工判断和预期 App 行为。

在线样本主要来自 Wikimedia Commons、NIND 相关样本和 NASA Visible Earth。它们不是 Redmi K70E 自拍图片。比赛要求提供对比测试样本，并不要求全部样本自拍；最终提交时保留来源页面、作者和许可信息，便于评审追溯。

## 分类样本

| 文件 | 类别 | 来源页面 | 作者 | 许可 | 准备方式 | 人工判断 | 预期 App 行为 |
|---|---|---|---|---|---|---|---|
| `clear/clear_butterfly_01.jpg` | 清晰 | <https://commons.wikimedia.org/wiki/File:Lesser_grass_blue_(Zizina_otis)_on_Hibiscus_flower_Koror.jpg> | Charles J. Sharp | CC BY-SA 4.0 | 下载 Wikimedia 1920px 版本 | 清晰花朵/蝴蝶样本，曝光正常但颜色偏暖 | 清晰度和综合分应较高 |
| `clear/clear_beetle_spider_02.jpg` | 清晰 | <https://commons.wikimedia.org/wiki/File:Bee_beetle_(Trichius_fasciatus)_and_flower_crab_spider_(Thomisidae_sp).jpg> | Charles J. Sharp | CC BY-SA 4.0 | 下载 Wikimedia 1920px 版本 | 主体纹理清晰 | 清晰度较高，曝光基本正常 |
| `clear/clear_butterfly_03.jpg` | 清晰 | <https://commons.wikimedia.org/wiki/File:Large_white_(Pieris_brassicae)_underside.jpg> | Charles J. Sharp | CC BY-SA 4.0 | 下载 Wikimedia 1920px 版本 | 主体清晰，色彩偏暖但可接受 | 清晰度和对比度较高 |
| `blur/blur_subway_motion_01.jpg` | 模糊 | <https://commons.wikimedia.org/wiki/File:Man_at_Dundas_subway_station,_Toronto,_2008-05-06.jpg> | Radomianin | CC BY-SA 4.0 | 下载 Wikimedia JPEG | 运动模糊但有强边缘 | 清晰度应降低；实际验证中成为局限样本 |
| `blur/blur_tanoura_motion_02.jpg` | 模糊 | <https://commons.wikimedia.org/wiki/File:Folkloric_Tanoura_dance_performed_by_man.jpg> | Mohamed Khalil90 | CC BY-SA 4.0 | 下载 Wikimedia 1920px 版本 | 夜间长曝光运动模糊 | 清晰度和综合分应偏低 |
| `blur/blur_out_of_focus_03.jpg` | 模糊 | <https://commons.wikimedia.org/wiki/File:An_example_of_an_out_of_focus_photograph.jpg> | Artem Katranzhi | CC BY-SA 2.0 | 下载 Wikimedia 1920px 版本 | 明显失焦 | 清晰度应明显偏低 |
| `overexposed/overexposed_photo_01.jpg` | 过曝 | <https://commons.wikimedia.org/wiki/File:Overexposed_photo.JPG> | Dawid Skalec | CC BY-SA 4.0 | 下载 Wikimedia 1920px 版本 | 高亮区域偏多 | 应提示曝光风险 |
| `overexposed/overexposed_sky_02.jpg` | 过曝 | <https://commons.wikimedia.org/wiki/File:5593_with_overexposed_sky.jpg> | Hugh Llewellyn | CC BY-SA 2.0 | 下载 Wikimedia 1920px 版本 | 天空和地面局部过曝 | 应提示曝光风险；最终验证中综合分仍偏高，作为反例 |
| `overexposed/overexposed_space_washed_03.jpg` | 过曝 | <https://commons.wikimedia.org/wiki/File:S42-209-015_-_STS-042_-_Overexposed_Photograph_-_DPLA_-_c75093b81f7c49ebc0e01c7c28fed192.jpg> | NASA / Lyndon B. Johnson Space Center | Public domain | 下载 Wikimedia 1920px 版本 | 明显偏亮/冲洗感 | 应提示曝光风险 |
| `underexposed/underexposed_sun_01.jpg` | 欠曝 | <https://commons.wikimedia.org/wiki/File:Underexposed_Sun.jpg> | Danecarney | CC0 | 下载 Wikimedia 1920px 版本 | 暗部占比高，整体欠曝 | 应提示曝光风险 |
| `underexposed/underexposed_space_02.jpg` | 欠曝 | <https://commons.wikimedia.org/wiki/File:Mercury_V_mission_-_Underexposed_-_DPLA_-_819efa7aa0348d0bb3d7ba467a9f5f7a.jpg> | NASA / Lyndon B. Johnson Space Center | Public domain | 下载 Wikimedia 1920px 版本 | 暗绿低对比场景 | 应提示低对比或曝光风险 |
| `underexposed/underexposed_pollen_03.jpg` | 欠曝 | <https://commons.wikimedia.org/wiki/File:Underexposed_pollen_flower_(25413776865).jpg> | Raita Futo | CC BY 2.0 | 下载 Wikimedia 1920px 版本 | 暗背景花粉样本，欠曝明显 | 应提示曝光风险 |
| `noisy/noisy_hong_kong_night_01.jpg` | 噪点 | <https://commons.wikimedia.org/wiki/File:Hong_Kong_in_night_(noise_version).jpg> | Wilfredor | CC0 | 下载 Wikimedia 1920px 版本 | 夜景噪点明显，建筑边缘较强 | 应提示夜景/噪点风险；最终作为反例 |
| `noisy/noisy_nind_stefantiek_02.jpg` | 噪点 | <https://commons.wikimedia.org/wiki/File:NIND_stefantiek_ISOH1.jpg> | Trougnouf | CC BY 4.0 | 下载 Wikimedia JPEG | NIND 高 ISO 噪点样本，局部细节较多 | 应作为噪点局限样本 |
| `noisy/noisy_nind_keyboard_03.jpg` | 噪点 | <https://commons.wikimedia.org/wiki/File:NIND_parking-keyboard_ISOH3.jpg> | Trougnouf | CC BY 4.0 | 下载 Wikimedia 1920px 版本 | 高 ISO 键盘噪点，硬边缘很多 | 应作为噪点与边缘混淆样本 |
| `counterexamples/counterexample_bokeh_01.jpg` | 反例候选 | <https://commons.wikimedia.org/wiki/File:Out-of-focus_dashboard_and_traffic_lights_at_night_02.jpg> | A S M Jobaer | CC BY-SA 4.0 | 下载 Wikimedia 1920px 版本 | 有意夜景散景，人眼可接受 | 可能被简单指标判低 |
| `counterexamples/counterexample_colored_lights_02.jpg` | 反例候选 | <https://commons.wikimedia.org/wiki/File:Niagara_Falls_by_night_July_2010_03.JPG> | Michael Barera | CC BY-SA 4.0 | 下载 Wikimedia 1920px 版本 | 彩色夜景灯光，人眼可接受 | 可能触发偏色或曝光风险 |
| `counterexamples/counterexample_sunset_03.jpg` | 反例候选 | <https://commons.wikimedia.org/wiki/File:Grand_Canyon_Warm_Sunset_Light.jpg> | Eric Kilby | CC BY-SA 2.0 | 下载 Wikimedia 1920px 版本 | 暖色日落，主观观感较好 | 可能被误判为偏色 |

## 格式样本

| 文件 | 来源 | 许可 | 准备方式 |
|---|---|---|---|
| `formats/format_clear_jpeg_01.jpg` | 来自 `clear/clear_butterfly_01.jpg` | CC BY-SA 4.0, Charles J. Sharp | 复制 JPEG，用于格式验证 |
| `formats/format_clear_png_01.png` | 来自 `clear/clear_butterfly_01.jpg` | CC BY-SA 4.0, Charles J. Sharp | 用 Pillow 本地转换为 PNG |
| `formats/format_clear_webp_01.webp` | 来自 `clear/clear_butterfly_01.jpg` | CC BY-SA 4.0, Charles J. Sharp | 用 Pillow 本地转换为 WebP |

## 大图样本

| 文件 | 来源页面 | 作者 | 许可/使用 | 准备方式 | 人工判断 | 预期 App 行为 |
|---|---|---|---|---|---|---|
| `large/large_blue_marble_january_01.jpg` | <https://assets.science.nasa.gov/content/dam/science/esd/eo/images/bmng/bmng-topography-bathymetry/january/world.topo.bathy.200401.3x5400x2700.jpg> | NASA Visible Earth | NASA public-use imagery; credit NASA Visible Earth | 下载 5400 x 2700 JPEG | 14.58MP 大尺寸地球图，用于性能和降采样验证 | App 应安全加载、降采样分析，并在可接受时间内完成 |

## 说明

- 清晰、模糊、过曝、欠曝、噪点五类各有 3 张样本，满足比赛对比样本要求。
- 反例候选用于验证算法边界；最终采用的两个主反例见 `deliverables/docs/counterexamples.md`。
- NASA 大图样本专门用于证明大图处理和分析耗时。
- 最终 App 分数记录在 `logs/final_validation_log.csv`；本文只记录样本来源、准备方式和人工预期。
